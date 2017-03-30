package com.example.android.inclassassignmentafterclass_mengqid;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mDatabase;
    private DatabaseReference userRef;
    private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
mDatabase = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // com.example.android.inclassassignmentafterclass_mengqid.User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    userRef = mDatabase.getReference(user.getUid());

                    final TextView nameField = (TextView) findViewById(R.id.name_display);
                    userRef.child("name").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //This method is called once with the initial value and again
                            //whenever data at this location is updated
                            String value = dataSnapshot.getValue(String.class);
                            Log.d(TAG, "Value is: " + value);
                            if (value == null) nameField.setText("Not set");
                            else nameField.setText(value);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //Failed to read value
                            //log.w(TAG, "Failed to read value.", error.toException());
                        }
                    });

                } else {
                    // com.example.android.inclassassignmentafterclass_mengqid.User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void setName(View view)
    {
        EditText name = (EditText)findViewById(R.id.name);
        userRef.child("name").setValue(name.getText().toString());
        Toast.makeText(this,"Name Updated!",Toast.LENGTH_SHORT).show();
        name.setText("");
    }

    public void signOut(View view)
    {
        mAuth.signOut();
    }

}

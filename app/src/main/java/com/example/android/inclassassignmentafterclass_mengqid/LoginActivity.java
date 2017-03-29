package com.example.android.inclassassignmentafterclass_mengqid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.CallLog.Calls.NEW;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();

        String key = "";
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        String username = sharedPref.getString(key,"Not set" );

        TextView usernameDisplay = (TextView) findViewById(R.id.name_display);
        usernameDisplay.setText(username);
    }

    public void setName(View view)
    {
        EditText editText = (EditText)findViewById(R.id.name);
        String username = editText.getText().toString();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("", username);
        editor.commit();

        editText.setText("");
        TextView usernameDisplay = (TextView) findViewById(R.id.name_display);
        usernameDisplay.setText(username);

    }

}

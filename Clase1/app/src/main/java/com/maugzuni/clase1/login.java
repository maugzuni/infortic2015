package com.maugzuni.clase1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText user,password;//cajas de texto en el xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText)findViewById(R.id.us);
        password = (EditText)findViewById(R.id.pass);
    }

    public void Login(View view){
        if(user.getText().toString().equals("mauricio") && password.getText().toString().equals("12345678"))
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(login.this, "Datos Invalidos", Toast.LENGTH_SHORT).show();
        }


    }
}

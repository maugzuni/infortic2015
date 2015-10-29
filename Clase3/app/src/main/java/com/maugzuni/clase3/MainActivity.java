package com.maugzuni.clase3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {private TextView info;
    //Estos dos elementos,vienen de facebook(Agregado en el archivo buil.gradle)
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //--------------------------------------------//
        FacebookSdk.sdkInitialize(this);//El SDK necesita ser inicializado antes de usar cualquiera de sus métodos,pasando el contexto de la actividad(Activity)
        callbackManager = CallbackManager.Factory.create();//inizializamos el CallbackManager
        //---------------------------------------------//
        setContentView(R.layout.activity_main);

        info = (TextView) findViewById(R.id.info);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        //--------------------------------------------------//
        loginButton.setReadPermissions(Arrays.asList("public_profile, email"));//Asignamos permisos

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Creamos el GraphRequest
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override //Si se completa el login
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                try {// facebook nos respondera con un JsonObject(object)
                                    String id = object.getString("id");//Obtenemos el id
                                    URL imgUrl = new URL("https://graph.facebook.com/" + id + "/picture?type=large");//Obtener imagen de perfil
                                    String name = object.getString("name");//Obtenemos en nombre
                                    String email = object.getString("email");//Obtenemos el mail
                                    Log.e("foto", imgUrl.toString());
                                    Log.e("usuario", name);
                                    Log.e("email", email);
                                    Intent i = new Intent(getApplicationContext(), Main2Activity.class);//Enviamos al usuario a otro activity
                                    i.putExtra("foto", imgUrl.toString());
                                    i.putExtra("usuario", name);
                                    i.putExtra("email",email);

                                    startActivity(i);
                                } catch (JSONException e) {
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();

                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {//Si se cancela la solicitus de login
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {//Si ocurre un error
                info.setText("Login attempt failed.");
            }
        });
    }

    //---------------------------------------------------------------------------//

    //---------------------------------------------------------------------------//

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

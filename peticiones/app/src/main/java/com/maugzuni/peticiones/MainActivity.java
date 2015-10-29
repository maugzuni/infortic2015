package com.maugzuni.peticiones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void peticion (View view)
    {
        final RequestQueue queue = Volley.newRequestQueue(this);//Debemos pasar el contexto de la actividad(Activity)

        //creamos una instancia de StringRequest
        //Recibe tres parametros
        //1)Metodo[Get-POST]
        //2)URL[Server]Se encuentra en la interface Value
        //3)Respuesta[]
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "()", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//Si la respuesta es positiva,si todo salio OK
                Log.e("Respuesta",response);
            }
        }, new Response.ErrorListener() {//En el caso de que ocurra un error X
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error: ", error.getMessage() + "");//Se mostrara el error producido
            }
        });
        queue.add(stringRequest);
    }
}

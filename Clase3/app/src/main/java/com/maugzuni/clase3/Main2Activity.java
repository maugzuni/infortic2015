package com.maugzuni.clase3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class Main2Activity extends AppCompatActivity {
    private String usuario,email,foto;
    TextView Usuario,Email;
    ImageView Foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Usuario = (TextView)findViewById(R.id.textView);
        Email = (TextView)findViewById(R.id.textView2);
        Foto = (ImageView)findViewById(R.id.imageView);
        usuario = getIntent().getStringExtra("usuario");
        email = getIntent().getStringExtra("email");
        foto = getIntent().getStringExtra("foto");
        Usuario.setText(usuario);
        Email.setText(email);
        Picasso.with(this).load(foto).into(Foto);
        InsertarDB();
    }
    public void InsertarDB(){
        final RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest =
                new StringRequest(Request.Method.POST, "http://10.34.29.183/ITP/registro.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error de Conexion", Toast.LENGTH_LONG).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("user", usuario);
                        params.put("email", email);
                        params.put("photo", foto);
                        return params;
                    }
                };
        queue.add(stringRequest);

    }








}
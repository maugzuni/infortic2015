package com.maugzuni.clase3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class Main2Activity extends AppCompatActivity {

    TextView Nombre,Email;
    ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String imagen,nombre,email;

        Nombre = (TextView)findViewById(R.id.textView);
        Email = (TextView)findViewById(R.id.textView2);
        foto = (ImageView)findViewById(R.id.imageView);

        imagen = getIntent().getStringExtra("foto");
        nombre = getIntent().getStringExtra("usuario");
        email = getIntent().getStringExtra("email");

        Nombre.setText(nombre);
        Email.setText(email);
        Picasso.with(this).load(imagen).into(foto);

    }
}

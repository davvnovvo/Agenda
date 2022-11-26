package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.db.DbContactos;
import com.example.agenda2.R;


public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono;
    Button btnGuarda, btnSorpresa;
    MediaPlayer mp, mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnSorpresa = findViewById(R.id.btnSorpresa);
        mp = MediaPlayer.create(this, R.raw.sorpresa);
        mp2 = MediaPlayer.create(this, R.raw.tontito);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);

                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {
                    long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtTelefono.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "CONTACTO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR EL CONTACTO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    mp2.start();
                    Toast.makeText(NuevoActivity.this, "DEBE COMPLETAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnSorpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });

    }

    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
    }

}
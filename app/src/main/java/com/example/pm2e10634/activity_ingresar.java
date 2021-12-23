package com.example.pm2e10634;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pm2e10634.Configuracion.SQLiteConexion;
import com.example.pm2e10634.Configuracion.Transacciones;

public class activity_ingresar extends AppCompatActivity {

    Spinner spin;
    EditText txtnombre,txttelefono,txtnota;
    Button btnguardar,btncontactos,btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);


        spin = (Spinner) findViewById(R.id.spin);
        txtnombre = (EditText) findViewById(R.id.txtnombre);
        txttelefono = (EditText) findViewById(R.id.txttelefono);
        txtnota = (EditText) findViewById(R.id.txtnota);

        btnEliminar = (Button) findViewById(R.id.btneliminar);
        btnguardar = (Button) findViewById(R.id.btnguardar);
        btncontactos = (Button) findViewById(R.id.btncontactos);


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R
                .array.combo_dias, android.R.layout.simple_spinner_item);

        spin.setAdapter(adapter);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar();
            }
        });

        btncontactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Lista.class);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar();
            }
        });





    }

    public boolean validar(){

        boolean retorno=true;


        String c1=txtnombre.getText().toString();
        String c2=txttelefono.getText().toString();
        String c3=txtnota.getText().toString();

       if(c1.isEmpty()){

           txtnombre.setError("Este campo no puede estar vacio");
           retorno = false;
       }
        if(c2.isEmpty()){

            txttelefono.setError("Este campo no puede estar vacio");

            android:
            retorno = false;
        }
        if(c3.isEmpty()){

            txtnota.setError("Este campo no puede estar vacio");
            retorno = false;
        }

        else {


            agregarcontactos();

        }






        return retorno;
    }






    private void agregarcontactos(){

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.pais, spin.getSelectedItem().toString());
        valores.put(Transacciones.nombre, txtnombre.getText().toString());
        valores.put(Transacciones.telefono, txttelefono.getText().toString());
        valores.put(Transacciones.nota,txtnota.getText().toString());

        Long resultado = db.insert(Transacciones.tablaContactos,Transacciones.id,valores);

        Toast.makeText(getApplicationContext(), "Contacto Guardado : Codigo : " + resultado.toString(), Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();
    }

    private void eliminar(){
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] parametros = {txttelefono.getText().toString()};

        db.delete(Transacciones.tablaContactos,Transacciones.telefono+"=?", parametros);
        Toast.makeText(getApplicationContext(), "Usuario eliminado " , Toast.LENGTH_LONG).show();
        db.close();
    }


    private void LimpiarPantalla() {

        txtnombre.setText("");
        txttelefono.setText("");
        txtnota.setText("");
    }


}
package com.example.pm2e10634;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pm2e10634.Configuracion.SQLiteConexion;
import com.example.pm2e10634.Configuracion.Transacciones;
import com.example.pm2e10634.tablas.Contactos;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {


    SQLiteConexion conexion;
    ListView listacontactos;
    ArrayList<Contactos> lista;
    ArrayList<String> ArregloContactos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);



        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        listacontactos = (ListView) findViewById(R.id.listacontactos);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloContactos);
        listacontactos.setAdapter(adp);



    }

    private void ObtenerListaPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Contactos list_personas = null;
        lista = new ArrayList<Contactos>();

        //cursor de la base de datos : nos apoya en recorrer  la info de la tabla a la cual consultamos

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaContactos, null);

        //recorremos la info del cursor
        while (cursor.moveToNext()){

            list_personas = new Contactos();

            list_personas.setId(cursor.getInt(0));
            list_personas.setNombre(cursor.getString(1));
            list_personas.setPais(cursor.getString(2));
            list_personas.setTelefono(cursor.getString(3));
            list_personas.setNota(cursor.getString(4));

            lista.add(list_personas);

        }

        db.close();

        filllist();

    }

    private void filllist() {
        ArregloContactos = new ArrayList<String>();

        for (int i = 0;i < lista.size(); i++)
        {
            ArregloContactos.add(lista.get(i).getId()+ "|"
                    +lista.get(i).getPais()+ "|"
                    +lista.get(i).getNombre()+ "|"
                    +lista.get(i).getTelefono()+ "|"
                    +lista.get(i).getNota());
        }


    }








}
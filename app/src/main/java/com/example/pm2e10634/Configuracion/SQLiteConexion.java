package com.example.pm2e10634.Configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteConexion extends SQLiteOpenHelper {
   public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version)
   {
       super(context, dbname, factory, version);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(Transacciones.CreateTableContactos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL(Transacciones.DROPTableContactos);
       onCreate(db);

    }
}

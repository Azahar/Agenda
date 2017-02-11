package com.azaharzafra.directorio;

/**
 * Created by Azahar on 06/12/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper{

    private String crearDB = "CREATE TABLE Contactos (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, number INTEGER)";

    public MySQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearDB);
    }
    public void onUpgrade(SQLiteDatabase db,  int oldVersion, int newVersion) {
        //Elimino anterior tabla
        db.execSQL("DROP TABLE IF EXISTS Contactos");
        //Creo nueva tabla
        db.execSQL(crearDB);
    }
    public void insertarContacto(String n, int t) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(db != null){
            ContentValues registro = new ContentValues();
            registro.put("name", n);
            registro.put("number", t);
            System.out.println(registro.get("name"));
            if(db.update("Contactos", registro, "name=?", new String[]{n}) == 0){
                db.insert("Contactos", null, registro);
                System.out.println("Hola");
            }
            db.close();
        }
    }
    public List<Contacto> buscarContacto(String n) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] registro = {"id", "name", "number"};

        List<Contacto> lista = new ArrayList<>();

        /* ESTRUCTURA CONSULTA
        query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs,
        String groupBy, String having, String orderBy, String limit, CancellationSignal cancellationSignal)*/

        String[] aproxName = {"%"+n+"%"};

        Cursor cursor = db.query(true, "Contactos", registro, "name LIKE ?", aproxName, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Contacto contact = new Contacto();
                contact.setName(cursor.getString(1));
                contact.setNumber(cursor.getInt(2));
                lista.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

}

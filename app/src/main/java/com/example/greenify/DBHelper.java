package com.example.greenify;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "greenify_db";
    private static final int DATABASE_VERSION = 1;

    // Tabla de usuarios (para almacenar las credenciales)
    public static final String TABLE_USER = "user";  // Cambiar a public
    public static final String COLUMN_ID = "id";     // Cambiar a public
    public static final String COLUMN_USERNAME = "username";  // Cambiar a public
    public static final String COLUMN_PASSWORD = "password";  // Cambiar a public

    // Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear las tablas cuando se crea la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT" +
                ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    // Actualizar la base de datos (en caso de cambio de versión)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}

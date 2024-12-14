package com.example.greenify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserRepository {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    // Constructor
    public UserRepository(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Abrir la base de datos
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Cerrar la base de datos
    public void close() {
        dbHelper.close();
    }

    // Insertar un nuevo usuario
    public long insertUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_USERNAME, username);
        values.put(DBHelper.COLUMN_PASSWORD, password);

        return database.insert(DBHelper.TABLE_USER, null, values);
    }

    // Obtener un usuario por nombre de usuario
    public Cursor getUserByUsername(String username) {
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_USERNAME, DBHelper.COLUMN_PASSWORD};
        String selection = DBHelper.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};

        return database.query(DBHelper.TABLE_USER, columns, selection, selectionArgs, null, null, null);
    }

    // Eliminar un usuario
    public void deleteUser(String username) {
        String whereClause = DBHelper.COLUMN_USERNAME + " = ?";
        String[] whereArgs = {username};
        database.delete(DBHelper.TABLE_USER, whereClause, whereArgs);
    }
}

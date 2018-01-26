// David Arias de Diego 11021
// Ana María Martínez Sidera 11257
// Pamela Isui Hernández García 14916

package com.example.pharmaetsii;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AbrirBD extends SQLiteOpenHelper {
	
	// Creamos la tabla medicina que tiene las siguientes columnas.
	public static final String tabla = "_medicina";
	
	// Es necesario un identificador para tener un número de la cantidad de
	// filas que vamos introduciendo
	public static final String id = "_id";
	public static final String nombre = "_nombre";
	public static final String fechafinal = "fechafinal";
	public static final String ma = "ma";
	public static final String tarde = "tarde";
	public static final String noche = "noche";

	private static final String DATABASE_NAME = "gestion.db";
	private static final int DATABASE_VERSION = 1;
	private static final String crear_tabla = " CREATE TABLE " + tabla + "("
			+ id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + nombre
			+ " TEXT NOT NULL, " + fechafinal + " TEXT, " + ma
			+ " TEXT NOT NULL, " + tarde + " TEXT NOT NULL, " + noche
			+ " TEXT NOT NULL " + ");";

	public AbrirBD(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(crear_tabla);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("AbrirBD", "Actulizar BD: " + oldVersion + " -> " + newVersion);
		db.execSQL("DROP TABLE IF EXISTS " + tabla);
		onCreate(db);
	}
}
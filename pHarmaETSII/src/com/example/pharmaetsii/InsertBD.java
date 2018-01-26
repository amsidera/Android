// David Arias de Diego 11021
// Ana María Martínez Sidera 11257
// Pamela Isui Hernández García 14916

package com.example.pharmaetsii;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

public class InsertBD extends AsyncTask<String, Integer, Long> {
	private AbrirBD abrir;

	public InsertBD(AbrirBD a) {
		abrir = a;
	}

	@Override
	protected Long doInBackground(String... params) {
		// Vamos a insertar una nueva fila a nuestra tabla. Para ello definimos
		// las columnas que tiene nuestra base de datos
		final String nombre = params[0];
		final String fechafinal = params[1];
		final String ma = params[2];
		final String tarde = params[3];
		final String noche = params[4];
		
		//Abrimos la base de datos
		SQLiteDatabase bd = abrir.getWritableDatabase();
		
		//Introducimos los valores de los identificadores correspondientes
		ContentValues values = new ContentValues();
		values.put(AbrirBD.nombre, nombre);
		values.put(AbrirBD.fechafinal, fechafinal);
		values.put(AbrirBD.ma, ma);
		values.put(AbrirBD.tarde, tarde);
		values.put(AbrirBD.noche, noche);

		return bd.insert(AbrirBD.tabla, null, values);
	}
	
	//Si no se ha podido introducir una nueva fila nos sale un mensaje de error
	@Override
	protected void onPostExecute(Long result) {
		if (result == -1) {
			Log.e("Insertar", "Error al insertar en la BD");

		}
	}

}
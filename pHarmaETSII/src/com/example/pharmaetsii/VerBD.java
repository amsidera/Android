// David Arias de Diego 11021
// Ana María Martínez Sidera 11257
// Pamela Isui Hernández García 14916

package com.example.pharmaetsii;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.TextView;

public class VerBD extends AsyncTask<AbrirBD, Integer, String> {
	TextView text;

	public VerBD(TextView t) {
		text = t;
	}

	// Le damos formato a lo que queremos que vean cuando pulse
	// el botón "Ver base de datos". Llamamos a AbrirBD para obtener los datos de
	// la tabla Medicamentos.
	@Override
	protected String doInBackground(AbrirBD... params) {
		AbrirBD db = params[0];
		SQLiteDatabase ver = db.getReadableDatabase();
		//Obtenemos los valores de la tabla
		String[] columns = { AbrirBD.id, AbrirBD.nombre, AbrirBD.fechafinal,
				AbrirBD.ma, AbrirBD.tarde, AbrirBD.noche };
		Cursor cursor = ver.query(AbrirBD.tabla, columns, null, null, null,
				null, null);
		StringBuffer res = new StringBuffer("Medicamentos\n");
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			//Le damos el formato correcto para que la persona lo pueda leer mejor
			res.append(cursor.getInt(0));
			res.append(":Nombre=>");
			res.append(cursor.getString(1));
			res.append("\n Fecha final: ");
			res.append(cursor.getString(2));
			res.append("\n Ha seleccionado por la mañana: ");
			res.append(cursor.getString(3));
			res.append("\n Ha seleccionado por la tarde: ");
			res.append(cursor.getString(4));
			res.append("\n Ha seleccionado por la noche: ");
			res.append(cursor.getString(5));
			res.append(".\n");
			cursor.moveToNext();
		}
		cursor.close();
		return res.toString();
	}

	@Override
	protected void onPostExecute(String result) {
		text.setText(result.toString());
	}

}

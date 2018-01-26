// David Arias de Diego 11021
// Ana María Martínez Sidera 11257
// Pamela Isui Hernández García 14916

package com.example.pharmaetsii;

import com.example.pharmaetsii.R;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;

import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements
		ListView.OnItemClickListener, DrawerListener {

	private Button agrega;

	private DrawerLayout cajon;
	private ListView opciones;
	private ActionBarDrawerToggle toggle;
	TextView texto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Genera el intento para recibir en String la edicion de texto en
		// el EditText de nombre en Pastilla1.java
		Intent inte = getIntent();
		String mensaje = inte.getStringExtra(Pastilla1.EXTRA_MESSAGE);

		setContentView(R.layout.activity_main);

		agrega = (Button) findViewById(R.id.button1);
		// Coloca el la edición en el button1 para ver el nombre la pastilla
		// editado
		agrega.setText(mensaje);

		// Definimos actionBar
		android.app.ActionBar actionBar = getActionBar();
		// Título de la Activity
		actionBar.setTitle("Agregar");
		String[] valores = getResources().getStringArray(R.array.cajon);
		cajon = (DrawerLayout) findViewById(R.id.drawer_layout);
		opciones = (ListView) findViewById(R.id.left_drawer);
		opciones.setAdapter(new ArrayAdapter<String>(this,
				R.layout.plantilla_cajon, valores));
		opciones.setOnItemClickListener(this);

		cajon.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		toggle = new ActionBarDrawerToggle(this, cajon, R.drawable.ic_drawer,
				R.string.drawer_open, R.string.drawer_close);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		cajon.setDrawerListener((DrawerListener) this);

	}

	// Cuando pulsamos una de las etiquetas
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		switch (arg2) {
		case 0:
			Ctres(); //Lanzamos MainActivity 
			break;
		case 1:
			Ccinco(); 
			break;
		case 2:
			Toast.makeText(getApplicationContext(), "Buscando...",
					Toast.LENGTH_SHORT).show();
			break;
		case 3:
			Cseis();
			break;
		case 4:
			Toast.makeText(getApplicationContext(), "Salir",
					Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		cajon.closeDrawer(opciones);
	}

	// Lanzamos la Activity: MainActivity.class
	public void Ctres() {
		Intent ntres = new Intent(this, MainActivity.class);
		startActivity(ntres);
	}

	// Lanzamos la Activity: Verbasededatos
	public void Ccinco() {
		Intent ncinco = new Intent(this, VerBDActivity.class);
		startActivity(ncinco);
	}

	// Lanzamos la Activity: ComousarApp
	public void Cseis() {
		Intent nseis = new Intent(this, ComoUsarApp.class);
		startActivity(nseis);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		toggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		toggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (toggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDrawerClosed(View arg0) {
	}

	@Override
	public void onDrawerOpened(View arg0) {
	}

	@Override
	public void onDrawerSlide(View arg0, float arg1) {
	}

	@Override
	public void onDrawerStateChanged(int arg0) {
	}

	// Edición del OnClick del botón de Agrega Medicamento
	public void editar(View view) {
		Intent editar = new Intent(this, Pastilla1.class);
		startActivity(editar);
	}

}

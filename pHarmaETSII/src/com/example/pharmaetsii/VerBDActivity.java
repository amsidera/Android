// David Arias de Diego 11021
// Ana María Martínez Sidera 11257
// Pamela Isui Hernández García 14916

package com.example.pharmaetsii;

import com.example.pharmaetsii.VerBD;
import com.example.pharmaetsii.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//Vemos la base de datos de nuestra pastillero
public class VerBDActivity extends Activity implements
		ListView.OnItemClickListener, DrawerListener {

	TextView texto;
	// Para poder abrir la base de datos
	private AbrirBD db;
	// Para poder configurar el actionbar
	private DrawerLayout cajon;
	private ListView opciones;
	private ActionBarDrawerToggle toggle;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verbasededatos);

		db = new AbrirBD(this.getApplicationContext());

		// Definimos actionBar
		android.app.ActionBar actionBar = getActionBar();
		// Título de la Activity
		actionBar.setTitle("Ver base de datos");
		// Array para definir las diferentes etiquetas que vamos tenemos en el
		// cajon del actionbar
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

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.pastilla1, menu);
	// return true;
	// }
//Si pulsamos el botón Ver base de datos:
	public void onConsultarData(View view) {
		TextView text = (TextView) findViewById(R.id.resultado);
		VerBD ver = new VerBD(text);
		ver.execute(db);
	}
	//Cuando pulsamos una de las etiquetas
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		switch (arg2) {
		case 0:
			Ctres();
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
			Toast.makeText(getApplicationContext(), "Salir", Toast.LENGTH_SHORT)
					.show();
			finish();
			break;
		default:
			break;
		}
		cajon.closeDrawer(opciones);
	}
//Lanzamos la Activity: MainActivity.class
	public void Ctres() {
		Intent ntres = new Intent(this, MainActivity.class);
		startActivity(ntres);
	}
	//Lanzamos la Activity: Verbasededatos
	public void Ccinco() {
		Intent ncinco = new Intent(this, VerBDActivity.class);
		startActivity(ncinco);
	}
	//Lanzamos la Activity: ComousarApp
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

}

// David Arias de Diego 11021
// Ana Mar�a Mart�nez Sidera 11257
// Pamela Isui Hern�ndez Garc�a 14916

package com.example.pharmaetsii;

import java.util.Calendar;
import com.example.pharmaetsii.InsertBD;
import com.example.pharmaetsii.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Pastilla1 extends Activity implements
		ListView.OnItemClickListener, DrawerListener {

	TextView texto;

	private DrawerLayout cajon;
	private ListView opciones;
	private ActionBarDrawerToggle toggle;
	private Calendar calendar;
	private TextView dateViewFin;
	private int year, month, day;
	private AbrirBD db;
	public final static String EXTRA_MESSAGE = "com.example.pharmaetsii.MESSAGE";

	TextView textAlarmPrompt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pastilla1);
		db = new AbrirBD(this.getApplicationContext());

		dateViewFin = (TextView) findViewById(R.id.fechaFin);

		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		showDate(year, month + 1, day);
		android.app.ActionBar actionBar = getActionBar();

		actionBar.setTitle("Formulario");

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
	
	// necesario para generar el DatePicker

	@SuppressWarnings("deprecation")
	public void setFechaFin(View v) {
		showDialog(999);
		Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		if (id == 999) {
			return new DatePickerDialog(this, myDateListener, year, month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener()

	{

		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			// arg1 = year
			// arg2 = month
			// arg3 = day
			showDate(arg1, arg2 + 1, arg3);
		}
	};

	// se escribe la fecha de fin como cadena
	
	private void showDate(int year, int month, int day) {
		dateViewFin.setText(new StringBuilder().append(day).append("/")
				.append(month).append("/").append(year));
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// funci�n asociada al checkbox ma�ana, devuelve verdadero si tiene un tic

	public boolean manana(View v) {
		CheckBox tic = (CheckBox) findViewById(R.id.checkbox_manana);
		if (tic.isChecked()) {
			return true;
		} else
			return false;
	}

	public boolean tarde(View v) {
		CheckBox tic = (CheckBox) findViewById(R.id.checkbox_tarde);
		if (tic.isChecked()) {
			return true;
		} else
			return false;
	}

	public boolean noche(View v) {
		CheckBox tic = (CheckBox) findViewById(R.id.checkbox_noche);
		if (tic.isChecked()) {
			return true;
		} else
			return false;
	}

	// Cuando pulsamos una de las etiquetas
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

	// M�todos asociados al actionBar, que implementa una interfaz

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

	// M�todo al pulsar el bot�n Guardar
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressLint("NewApi")
	public void comprobar(View v) {

		EditText t;
		t = (EditText) findViewById(R.id.Nombre);
		final String nombre = t.getText().toString();
		//Insertamos el nombre en la base de datos
		
		TextView t6;
		t6 = (TextView) findViewById(R.id.fechaFin);
		final String fechafinal = t6.getText().toString();

		CheckBox t3;
		t3 = (CheckBox) findViewById(R.id.checkbox_manana);
		String ma = "0";
		if (t3.isChecked()) {
			ma = "1";
		}

		CheckBox t4;
		t4 = (CheckBox) findViewById(R.id.checkbox_tarde);
		String tarde = "0";
		if (t4.isChecked()) {
			tarde = "1";
		}

		CheckBox t5;
		t5 = (CheckBox) findViewById(R.id.checkbox_noche);
		String noche = "0";
		if (t5.isChecked()) {
			noche = "1";
		}

		InsertBD in = new InsertBD(db);
		in.execute(nombre, fechafinal, ma, tarde, noche);
		//Insertamos todos los campos anteriores en la base de datos
		//Mostramos un mensaje de confirmaci�n
		Toast.makeText(getApplicationContext(), "Medicamento insertado",
				Toast.LENGTH_SHORT).show();
		Ctres();

		Calendar cal = Calendar.getInstance();
		AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);

		// Se genera el intento para enviar al MainActivity lo escrito por los usuarios en forma de String por medio EXTRA_MESSAGE, al final se inicia la actividad
		Intent inte = new Intent(this, MainActivity.class);
		EditText cuadro = (EditText) findViewById(R.id.Nombre);
		String nombr = cuadro.getText().toString();
		inte.putExtra(EXTRA_MESSAGE, nombr);
		startActivity(inte);

		// si se marca la opci�n de ma�ana

		if (manana(v)) {

			// en el caso de que ya haya pasado la hora, se a�ade un d�a m�s, de manera an�loga se realizar�a la comprobaci�n de si ha llegado a la fecha final
			
			// para comprobar la fecha final se comparar�a el d�a y mes del sistema con lo guardado como fecha final, y se configura la alarma hasta que la fecha y d�a sean iguales

			if (cal.get(Calendar.HOUR_OF_DAY) >= 8 ) {
				cal.add(Calendar.DATE, 1);
				cal.set(Calendar.HOUR_OF_DAY, 8); // hora de la alarma
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 00);
			}
			
			// en el mismo d�a
			else{ 
			cal.set(Calendar.HOUR_OF_DAY, 8); // hora de la alarma
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 00);
			}
			
			// Crea un nuevo PendingIntent y lo asocia con la clase AlarmActivity
			
			Intent intenT = new Intent(this, AlarmActivity.class);
			PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 11021, intenT, PendingIntent.FLAG_UPDATE_CURRENT); // un c�digo caracter�stico para cada  alarma
				
			// Se configura una alarma que se repita cada 24*3600*1000 ms (cada d�a)
			am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 24 * 3600 * 1000, pendingIntent1);
			

			// Genera las notificaciones y las opciones de las mismas
			// Se crean tres intentos para las tres Activities diferentes a las que nos enviaran cada opcion d elas notificaciones.
			Intent notifica1 = new Intent(this, Pastilla1.class);
			Intent notifica2 = new Intent(this, ConsejosActivity.class);
			Intent notifica3 = new Intent(this, ComoUsarApp.class);

			// Se generan los tres nuevos PendingIntent correspondientes a los tres intentos anteriores.
			PendingIntent pIntent1 = PendingIntent.getActivity(this, 0,
					notifica1, 0);
			PendingIntent pIntent2 = PendingIntent.getActivity(this, 0,
					notifica2, 0);
			PendingIntent pIntent3 = PendingIntent.getActivity(this, 0,
					notifica3, 0);
			// Se crea la notificaci�n como tal
			Notification n = new Notification.Builder(this)
					// Titulo de la notificaci�n
					.setContentTitle(
							"Pronto pHarmaETSII te recordar� tu pastilla")
					// Descripci�n de la notificaci�n
					.setContentText(
							"Mientras puedes ver consejos para una vida saludable")
					// Icono principal de l anotificaci�n
					.setSmallIcon(R.drawable.ic_launcher)
					// Intento principal de la notificaci�n
					.setContentIntent(pIntent1)
					// Edicion de las tres opciones que nos da la notificaci�n
					// (icono,Titulo,intento).
					.addAction(R.drawable.edit, "Editar", pIntent1)
					.addAction(R.drawable.consejos, "Consejos", pIntent2)
					.addAction(R.drawable.mas, "M�s", pIntent3).build();

			NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

			// Se termina la notificaci�n, para eliminarla despues de dar click
			// en la parte principal de ella
			n.flags |= Notification.FLAG_AUTO_CANCEL;
			nm.notify(0, n);

		}

		// An�logo al caso de la ma�ana, con un identificador distinto

		if (tarde(v)) {
			
			if (cal.get(Calendar.HOUR_OF_DAY) >= 15 ) {
				cal.add(Calendar.DATE, 1);
				cal.set(Calendar.HOUR_OF_DAY, 15); // hora de la alarma
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 00);
			}
			
			// en el mismo d�a
			else{ 
			cal.set(Calendar.HOUR_OF_DAY, 15); // hora de la alarma
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 00);
			}

			// Create a new PendingIntent and add it to the AlarmManager
			Intent intenT = new Intent(this, AlarmActivity.class);
			PendingIntent pendingIntent1 = PendingIntent.getActivity(this,
					11273, intenT, PendingIntent.FLAG_UPDATE_CURRENT);
			am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
					24 * 3600 * 1000, pendingIntent1);

			// Genera las notificaciones y las opciones de las mismas
			// Se crean tres intentos para las tres Activities diferentes a las
			// que nos
			// enviaran cada opcion d elas notificaciones.
			Intent notifica1 = new Intent(this, Pastilla1.class);
			Intent notifica2 = new Intent(this, ConsejosActivity.class);
			Intent notifica3 = new Intent(this, ComoUsarApp.class);

			// Se generan los tres nuevos PendingIntent correspondientes a los
			// tres intentos anteriores.
			PendingIntent pIntent1 = PendingIntent.getActivity(this, 0,
					notifica1, 0);
			PendingIntent pIntent2 = PendingIntent.getActivity(this, 0,
					notifica2, 0);
			PendingIntent pIntent3 = PendingIntent.getActivity(this, 0,
					notifica3, 0);
			// Se crea la notificaci�n como tal
			Notification n = new Notification.Builder(this)
					// Titulo de la notificaci�n
					.setContentTitle(
							"Pronto pHarmaETSII te recordar� tu pastilla")
					// Descripci�n de la notificaci�n
					.setContentText(
							"Mientras puedes ver consejos para una vida saludable")
					// Icono principal de l anotificaci�n
					.setSmallIcon(R.drawable.ic_launcher)
					// Intento principal de la notificaci�n
					.setContentIntent(pIntent1)
					// Edicion de las tres opciones que nos da la notificaci�n
					// (icono,Titulo,intento).
					.addAction(R.drawable.edit, "Editar", pIntent1)
					.addAction(R.drawable.consejos, "Consejos", pIntent2)
					.addAction(R.drawable.mas, "M�s", pIntent3).build();

			NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

			// Se termina la notificaci�n, para eliminarla despues de dar click
			// en la parte principal de ella
			n.flags |= Notification.FLAG_AUTO_CANCEL;
			nm.notify(0, n);

		}
		if (noche(v)) {

			// en el caso de que ya haya pasado la hora, se a�ade un d�a m�s
			
			if (cal.get(Calendar.HOUR_OF_DAY) >= 21 ) {
				cal.add(Calendar.DATE, 1);
				cal.set(Calendar.HOUR_OF_DAY, 21); // hora de la alarma
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 00);
			}
			
			// en el mismo d�a
			else{ 
			cal.set(Calendar.HOUR_OF_DAY, 21); // hora de la alarma
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 00);
			}
			// Create a new PendingIntent and add it to the AlarmManager
			Intent intenT = new Intent(this, AlarmActivity.class);
			PendingIntent pendingIntent1 = PendingIntent.getActivity(this,
					11145, intenT, PendingIntent.FLAG_UPDATE_CURRENT);

			am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
					24 * 3600 * 1000, pendingIntent1);

			// Genera las notificaciones y las opciones de las mismas
			// Se crean tres intentos para las tres Activities diferentes a las
			// que nos
			// enviaran cada opcion d elas notificaciones.
			Intent notifica1 = new Intent(this, Pastilla1.class);
			Intent notifica2 = new Intent(this, ConsejosActivity.class);
			Intent notifica3 = new Intent(this, ComoUsarApp.class);

			// Se generan los tres nuevos PendingIntent correspondientes a los
			// tres intentos anteriores.
			PendingIntent pIntent1 = PendingIntent.getActivity(this, 0,
					notifica1, 0);
			PendingIntent pIntent2 = PendingIntent.getActivity(this, 0,
					notifica2, 0);
			PendingIntent pIntent3 = PendingIntent.getActivity(this, 0,
					notifica3, 0);
			// Se crea la notificaci�n como tal
			Notification n = new Notification.Builder(this)
					// Titulo de la notificaci�n
					.setContentTitle(
							"Pronto pHarmaETSII te recordar� tu pastilla")
					// Descripci�n de la notificaci�n
					.setContentText(
							"Mientras puedes ver consejos para una vida saludable")
					// Icono principal de l anotificaci�n
					.setSmallIcon(R.drawable.ic_launcher)
					// Intento principal de la notificaci�n
					.setContentIntent(pIntent1)
					// Edicion de las tres opciones que nos da la notificaci�n
					// (icono,Titulo,intento).
					.addAction(R.drawable.edit, "Editar", pIntent1)
					.addAction(R.drawable.consejos, "Consejos", pIntent2)
					.addAction(R.drawable.mas, "M�s", pIntent3).build();

			NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

			// Se termina la notificaci�n, para eliminarla despues de dar click
			// en la parte principal de ella
			n.flags |= Notification.FLAG_AUTO_CANCEL;
			nm.notify(0, n);
		}

	}

	public void borrar(View v) {
		Intent intent = new Intent(this, AlarmActivity.class);
		PendingIntent.getActivity(this, 11021, intent,
				PendingIntent.FLAG_UPDATE_CURRENT).cancel();
		PendingIntent.getActivity(this, 11273, intent,
				PendingIntent.FLAG_UPDATE_CURRENT).cancel();
		PendingIntent.getActivity(this, 11145, intent,
				PendingIntent.FLAG_UPDATE_CURRENT).cancel();

	}

}

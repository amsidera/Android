// David Arias de Diego 11021
// Ana María Martínez Sidera 11257
// Pamela Isui Hernández García 14916

package com.example.pharmaetsii;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

public class FlashActivity extends Activity {
	// tiempo que se mantiene la imagen inicial en milisegundos
	private static final long ESPERA = 1500;
	public final static String EXTRA_MESSAGE = "com.example.pharmaetsii.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pantalla_flash);
		TimerTask task = new TimerTask() {
			@Override
			// se genera un Intent con la pantalla principal
			public void run() {
				
				Intent inte = new Intent().setClass(FlashActivity.this, 
						MainActivity.class);
				String nombr = "Agregar medicamento"; // inicialmente el botón tendrá el título agregar medicamento
				inte.putExtra(EXTRA_MESSAGE, nombr);
				startActivity(inte);
				
				finish();

			}
		};

		Timer timer = new Timer();
		timer.schedule(task, ESPERA);
	}
}

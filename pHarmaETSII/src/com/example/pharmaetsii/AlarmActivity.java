// David Arias de Diego 11021
// Ana María Martínez Sidera 11257
// Pamela Isui Hernández García 14916

package com.example.pharmaetsii;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class AlarmActivity extends Activity  {
    
	private MediaPlayer alarma; // clase que se encarga de la canción cuando se crea la actividad
	
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alarm);
        alarma = MediaPlayer.create(this,R.raw.azucar);
        // comienza la canción
        alarma.start();
        
        
    }

    // si se pulsa el botón de parar
    
    public void pararAlarma(View v) {
        alarma.stop(); // se para la canción
        finish(); // se termina la actividad
    }

	
    
}

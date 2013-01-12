package com.br.rodrigo.concursei;

import java.io.IOException;

import com.br.rodrigo.concursei.util.DataBaseHelper;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;


public class Splash extends Activity {

    private MediaPlayer mPlayer;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //mPlayer = MediaPlayer.create(Splash.this, R.pasta.nomeArq);
        //mPlayer.start();
        
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        // CRIA O BANCO DE DADOS
        try
        {
        	dbHelper.createDataBase();
        }catch(IOException ioe)
        {
        	Log.v("DB_HELPER", ioe.getMessage());
        }
        // ABRE O BANCO DE DADOS
        try
        {
        	dbHelper.openDataBase();
        }catch(SQLException sqle)
        {
        	Log.v("DB_HELPER", sqle.getMessage());
        }
        
		Thread tempo = new Thread()
		{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				
				try
				{
					sleep(5000);
				}catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}finally
				{
					Class intentClass;
					try
					{
						intentClass = Class.forName("com.br.rodrigo.concursei.Menu");
						Intent abreMenu = new Intent(Splash.this, intentClass);
						startActivity(abreMenu);
					} catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}
				}
			}
		};
		
		tempo.start();
    }
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		mPlayer.release();
		finish();
	}	
    
}

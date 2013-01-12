package com.br.rodrigo.concursei;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Intent;


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

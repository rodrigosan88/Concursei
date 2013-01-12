package com.br.rodrigo.concursei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Menu extends Activity
{
	
	// Testando um teste.... 2xD
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
	}
	
	public void iniciar(View v)
	{
		Log.v("DEBUG", "Iniciar");
		Intent intent = new Intent(this, Iniciar.class);
		startActivity(intent);
	}
	
}

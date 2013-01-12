package com.br.rodrigo.concursei;

import com.br.rodrigo.concursei.util.DataBaseHelper;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class Iniciar extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iniciar);
		
		Log.v("INICIOU", Iniciar.class.getName());
		
		DataBaseHelper dbHelp = new DataBaseHelper(this);
		Cursor cursor = dbHelp.pesquisar();
		Log.v("DEBUG", cursor.getString(1));
	}
	
}

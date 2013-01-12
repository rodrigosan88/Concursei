package com.br.rodrigo.concursei.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{

	private static String DB_PATH = "/data/data/com.br.rodrigo.concursei/databases/";
	private static String DB_NAME = "testedb";
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	
	public DataBaseHelper(Context context)
	{
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}
	
	 /**
     * Cria uma base de dados vazia no sistema e reescreve com a base pr—pria
     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist)
    	{
    		//Se o banco j‡ existe n‹o faz nada
    	}
    	else
    	{
        	this.getReadableDatabase();
 
        	try 
        	{
    			copyDataBase();
    		}
        	catch (IOException e)
    		{
        		throw new Error("Error copying database");
        	}
    	}
    }
 
    /**
     * Checa se o banco j‡ existe para evitar que o arquivo seja copiado a cada vez que a aplica‹o iniciar
     * @return true se j‡ existe, false se n‹o existe
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try
    	{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
    		//Banco n‹o existe ainda
    	}
 
    	if(checkDB != null)
    	{
    		checkDB.close();
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copia o banco da pasta de assets local para o banco recŽm criado na pasta do sistema
     * onde este poder‡ ser acessado e manipulado.
     * */
    private void copyDataBase() throws IOException{
 
    	Log.v("DEBUG", "Copiando Base de dados...");
    	// Abre o BD local como um input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Caminho do BD criado
    	String outFileName = DB_PATH + DB_NAME;
 
    	// Abre o BD vazio como output Stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	// Transfere os bytes do input para o output
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	// Fecha o stream
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    	
    	Log.v("DEBUG", "Copiando Base de dados...OK!");
 
    }
 
    public void openDataBase() throws SQLException{
 
    	// Abre o BD
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
 
        // Add your public helper methods to access and get content from the database.
       // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
       // to you to create adapters for your views.
	
	public Cursor pesquisar()
	{
		openDataBase();
		return myDataBase.rawQuery("SELECT * FROM Caderno WHERE id_caderno = ?", new String[] {"1"});
	}
}

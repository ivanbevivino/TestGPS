package com.example.testgps.archivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.content.Context;
import android.util.Log;

public class Carchivos {
	String nArchivo="GPS.txt";
	Context ctx;
	FileOutputStream fos;
	FileInputStream fis;

	//	public Carchivos (){
	//		super();
	//		this.ctx = getApplicationContext();
	//	}

	public Carchivos (Context ctx){
		super();
		this.ctx = ctx; 
	}

	public void escribir (String textoarchivo){

		textoarchivo = textoarchivo +"\n";

		try{
			fos=ctx.openFileOutput(nArchivo, Context.MODE_APPEND);
			fos.write(textoarchivo.getBytes());
			fos.close();
		}catch(FileNotFoundException e){
			Log.e("",""+e.getMessage());
		}catch(IOException ex){
			Log.e("",""+ex.getMessage());
		}


	}

	public String leer (){
		String lectura="";

		try {
			fis=ctx.openFileInput(nArchivo);
			int i=0;
			char caracter='a';
			do{
				i=fis.read();
				if(i!='\n'){
					caracter=(char)i;
					//lectura=lectura+(char)i;
					lectura=lectura+caracter;
				}
				if (caracter=='.'){
					lectura=lectura+"\n";

				}
			}while(i<0);
			lectura+="\n";
		}catch(Exception e){}
		return (lectura);
	}

}

package com.example.androidasynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChamadaRemota extends AsyncTask<String, Void, String> {

    public ChamadaRemotaInterface chamadaRemotaInterface;

    public ChamadaRemota(ChamadaRemotaInterface chamadaRemotaInterface) {
        this.chamadaRemotaInterface = chamadaRemotaInterface;
    }


    @Override
    protected String doInBackground(String[] s) {

        URL url;
        HttpURLConnection urlConnection = null;

        String resultado = new String() ;
        try{
            url = new URL("http://10.0.2.2:80//JSONIntegracao///json_rest_livros.php");

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();

                resultado += current;
            }

        }catch (Exception e) {
            Log.d("erro acessando remoto", e.getMessage());
            e.printStackTrace();
        }finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }


        return resultado;
    }

    @Override
    protected void onPostExecute(String s) {
        chamadaRemotaInterface.retornoChamadaRemota(s);
    }

}

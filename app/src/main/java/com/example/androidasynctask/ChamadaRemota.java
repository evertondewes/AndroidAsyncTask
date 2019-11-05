package com.example.androidasynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
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
        BufferedReader reader;
        String resultado = new String();
        try {
            url = new URL("http://10.0.2.2:80//JSONIntegracao///json_rest_livros.php");

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            resultado = result.toString();

            if (reader != null) {
                reader.close();
            }
        } catch (Exception e) {
            Log.d("erro acessando remoto", e.getMessage());
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
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

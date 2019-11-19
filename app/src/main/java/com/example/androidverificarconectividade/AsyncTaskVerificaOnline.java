package com.example.androidverificarconectividade;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskVerificaOnline extends AsyncTask {


    public AsyncTaskVerificaOnlineRetornoInterface activitiRetorno;

    public AsyncTaskVerificaOnline(AsyncTaskVerificaOnlineRetornoInterface activitiRetorno) {
        this.activitiRetorno = activitiRetorno;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        URL url;
        HttpURLConnection urlConnection = null;
        BufferedReader reader;
        String resultado = new String();
        try {
            url = new URL("http://10.0.2.2:80//JSONIntegracao///testa_online.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(100);


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
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

        }

        return resultado;
    }

    @Override
    protected void onPostExecute(Object o) {
        if(o != null){
            this.activitiRetorno.setOnline(true);
        } else {
            this.activitiRetorno.setOnline(false);
        }
    }
}

package com.example.androidverificarconectividade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AsyncTaskVerificaOnlineRetornoInterface {

    public static Handler h = null;
    public static ImageView iv = null;

    public static AsyncTaskVerificaOnline verificaAtivo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.iv = findViewById(R.id.iv);

        MainActivity.h = new Handler();

//        MainActivity.verificaAtivo = new AsyncTaskVerificaOnline(this);

        final MainActivity main = this;

        MainActivity.h.post(new Runnable() {
            @Override
            public void run() {

                try {

                    MainActivity.verificaAtivo = new AsyncTaskVerificaOnline(main);

                    Log.d("Status", MainActivity.verificaAtivo.getStatus().name());

                    MainActivity.verificaAtivo.execute();

                    Thread.sleep(1000);

                    h.postDelayed(this, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public void setOnline(boolean resultado) {
        if (resultado) {
            MainActivity.iv.setImageResource(R.drawable.f_on);
        } else {
            MainActivity.iv.setImageResource(R.drawable.f_off);
        }
    }
}

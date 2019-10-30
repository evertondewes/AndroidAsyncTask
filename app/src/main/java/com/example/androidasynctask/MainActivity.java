package com.example.androidasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ChamadaRemotaInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void consultar(View view) {
        ChamadaRemota r = new ChamadaRemota(this);
        r.execute();

    }

    public void mostraRetorno(String resposta){
        TextView txResultado = findViewById(R.id.txResultado);

        txResultado.setText(txResultado.getText() + "\n" + resposta);
    }

    @Override
    public void retornoChamadaRemota(String s) {
        this.mostraRetorno(s);
    }
}

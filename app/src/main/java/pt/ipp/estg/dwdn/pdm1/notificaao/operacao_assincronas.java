package pt.ipp.estg.dwdn.pdm1.notificaao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class operacao_assincronas extends AppCompatActivity {

    private Button button01;
    private Button button02;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacao_assincronas);
        button01 = findViewById(R.id.button01);
        button02 = findViewById(R.id.button02);
        textView = findViewById(R.id.textView2);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("Botão 1");
                            }
                        });
                    }
                }).start();
            }
        });
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extracted();
            }
        });
    }

    private void extracted() {
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Botão 2");
                    }
                });
                return null;
            }
        }.execute();
    }

}
package com.damasofc.myform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    Button btnShare;
    ImageButton btnCheque;
    String mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initComponents();
    }
    private void initComponents(){
        btnCheque = (ImageButton) findViewById(R.id.btnCheque);
        btnShare = (Button) findViewById(R.id.btnShare);
        btnShare.setVisibility(View.INVISIBLE);
        btnCheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bn = getIntent().getExtras();
                String name = bn.getString("nombre");
                int edad = bn.getInt("edad");
                String tipo = bn.getString("tipo");
                if(tipo.startsWith("G")){
                    mensaje = "Hola "+ name +" Como llevas esos "+edad+" años? #MyForm";
                    Toast.makeText(ThirdActivity.this,mensaje, Toast.LENGTH_SHORT).show();
                }else{
                    mensaje = "Espero verte pronto "+ name +" antes de que cumplas "+(edad+1)+". #MyForm";
                    Toast.makeText(ThirdActivity.this,mensaje, Toast.LENGTH_SHORT).show();
                }
                btnShare.setVisibility(View.VISIBLE);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Respuesta:");
                intent.putExtra(Intent.EXTRA_TEXT,mensaje);
                startActivity(Intent.createChooser(intent,"Compartir via:"));
            }
        });
    }
}

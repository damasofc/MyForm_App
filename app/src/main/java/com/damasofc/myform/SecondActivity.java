package com.damasofc.myform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView txtSeekBar;
    SeekBar seekBar;
    Button btnNext2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initComponents();
    }
    private void initComponents(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        txtSeekBar = (TextView) findViewById(R.id.txtSeekBar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        btnNext2 = (Button) findViewById(R.id.btnNext2);
        radioGroup.getCheckedRadioButtonId();
        seekBar.setProgress(16);
        txtSeekBar.setText(String.valueOf(16));
        seekBar.setMax(80);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtSeekBar.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekBar.getProgress() < 16 || seekBar.getProgress() > 60){
                    btnNext2.setVisibility(View.INVISIBLE);
                    String mensaje = seekBar.getProgress() > 60?getResources().getString(R.string.mensajeEdadMayor):getResources().getString(R.string.mensajeEdadMenor);
                    Toast.makeText(SecondActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                }
                else{
                    btnNext2.setVisibility(View.VISIBLE);
                }
            }
        });
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("nombre",getIntent().getExtras().getString("name"));
                intent.putExtra("edad",seekBar.getProgress());
                RadioButton rb = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                intent.putExtra("tipo",rb.getText().toString());
                startActivity(intent);
            }
        });
    }
}

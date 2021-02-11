package com.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/*
Assignment #: HW01
FileName: MainActivity.java
Full Name of the student: Anoosh Hari and Dayakar Ravuri - 29 A
 */

public class MainActivity extends AppCompatActivity {

    Float tip;
    EditText billAmount;
    RadioGroup radioGroup;
    TextView tipamount;
    TextView totalamount;
    SeekBar seekBar;
    TextView progressValue;
    Button exitbutton;

    private static DecimalFormat df = new DecimalFormat("0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = findViewById(R.id.billamount);
        radioGroup = findViewById(R.id.radioGroup);
        tipamount = findViewById(R.id.tipamount);
        totalamount = findViewById(R.id.totalamount);
        seekBar = findViewById(R.id.seekBar);
        progressValue = findViewById(R.id.progressbar);
        exitbutton = findViewById(R.id.button);

        seekBar.setEnabled(false);

        progressValue.setText(seekBar.getProgress()+"%");

        billAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    radioButtonActivity(checkedId);
                } catch (Exception e){
                    callToast();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                try {
                    seekBar.setEnabled(false);
                    radioButtonActivity(checkedId);
                } catch (Exception e){
                    callToast();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    progressValue.setText(progress+"%");
                    if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton4){
                        tip = Float.parseFloat(progress+"")*(0.01F)*(Float.parseFloat(billAmount.getText()+""));
                        totalamount.setText(df.format(tip + Float.parseFloat(billAmount.getText()+"")));
                        tipamount.setText(df.format(tip));
                    }
                } catch (Exception e){
                    callToast();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                try {
                    if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton4){
                        tip = Float.parseFloat(seekBar.getProgress()+"")*(0.01F)*(Float.parseFloat(billAmount.getText()+""));
                        totalamount.setText(df.format(tip + Float.parseFloat(billAmount.getText()+"")));
                        tipamount.setText(df.format(tip));
                    }
                } catch (Exception e){
                    callToast();
                }
            }
        });

        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeApplication();
            }
        });
    }

    private void radioButtonActivity(int checkedId) {
        if(checkedId == R.id.radioButton1){
            tip = Float.parseFloat(billAmount.getText()+"")*(0.1F);
            totalamount.setText(df.format(tip + Float.parseFloat(billAmount.getText()+"")));
            tipamount.setText(df.format(tip));
        }else if(checkedId == R.id.radioButton2){
            tip = Float.parseFloat(billAmount.getText()+"")*(0.15F);
            totalamount.setText(df.format(tip + Float.parseFloat(billAmount.getText()+"")));
            tipamount.setText(df.format(tip));
        } else if(checkedId == R.id.radioButton3){
            tip = Float.parseFloat(billAmount.getText()+"")*(0.18F);
            totalamount.setText(df.format(tip + Float.parseFloat(billAmount.getText()+"")));
            tipamount.setText(df.format(tip));
        } else if(checkedId == R.id.radioButton4){
            seekBar.setEnabled(true);
            tip = Float.parseFloat(seekBar.getProgress()+"")*(0.01F)*(Float.parseFloat(billAmount.getText()+""));
            totalamount.setText(df.format(tip + Float.parseFloat(billAmount.getText()+"")));
            tipamount.setText(df.format(tip));
        }
    }

    public void callToast(){
        totalamount.setText("");
        tipamount.setText("");
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
    }

    public void closeApplication(){
        this.finishAffinity();
    }
}
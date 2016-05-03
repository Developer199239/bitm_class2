package com.faravy.bitmtrainer401.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView showRsult;
    double num=0.0;
    double tempNum=0.0;
    Character operator;
    String input="";
    boolean point=false;
    int previousClear=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showRsult=(TextView) findViewById(R.id.showResult);

    }

    public void btn7Clicked(View view){
        insertNum(7);
    }
    public void btn8Clicked(View view){
        insertNum(8);
    }

    public void btn9Clicked(View view){
        insertNum(9);
    }
    public void btn4Clicked(View view){
        insertNum(4);
    }

    public void btn5Clicked(View view){
        insertNum(5);
    }

    public void btn6Clicked(View view){
        insertNum(6);
    }

    public void btn1Clicked(View view){
        insertNum(1);
    }
    public void btn2Clicked(View view){
        insertNum(2);
    }

    public void btn3Clicked(View view){
        insertNum(3);
    }
    public void btn0Clicked(View view){
        insertNum(0);
    }

    public void btnPoint(View view){
        insertNum(999);
    }

    public void btnInvers(View view){

    }


    public void btnClearClicked(View view){
        input="";
        num=0;
        tempNum=0;
        showRsult.setText("");

    }

    public void btnPlusClicked(View view){

        operation();
        operator='+';
    }

    public void btnMinusClicked(View view){
        operation();
        operator='-';
    }

    public void btnEqualClicked(View view){
        previousClear=1;
       if (operator=='+'){
           num=tempNum+num;
       }
        else  if (operator=='-'){
           num=num-tempNum;
       }
       else  if (operator=='*'){
           num=num*tempNum;
       }
       else  if (operator=='/'){
           num=tempNum/num;
       }
        else if(operator=='%'){
           num=num%tempNum;
       }
        showRsult.setText(""+num);


    }

    public void btnDivideClicked(View view){
        operation();
        operator='/';
    }
    public void btnMultiClicked(View view){
        operation();
        operator='*';
    }

    public void btnBack(View view){
        input=""+num;
        if(input.length()>1)
        {
            input=input.substring(0,input.length()-1);
            showRsult.setText(input);
            num=Double.parseDouble(input);
        }

        else if(input.length()==1)
        {
            input="";
            showRsult.setText("");
            num=0;
            Toast.makeText(MainActivity.this,"Input is Empty",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this,"Input is Empty",Toast.LENGTH_SHORT).show();
        }
    }

    public void btnMod(View view){
        operator='%';
        operation();



    }

    private void insertNum(int i){
        if(previousClear==1)
        {
            input="";
            previousClear=0;
        }
        if(input.length()==1 && input.equals("0") && i==0)
        {
            showRsult.setText("0");
            input="";
        }


        else {

            if(input.equals("0"))
                input="";
            if(i==999 && point==false){
                input+=".";
                point=true;
            }
            else {
                input = input + Integer.toString(i);
            }
            num = Double.parseDouble(input);
            showRsult.setText(input);

        }


    }

    private  void operation(){
        tempNum=num;
        input="";
    }

}

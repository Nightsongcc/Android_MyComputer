package com.example.nightsong;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v4.view.KeyEventDispatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText text;
    boolean clean = true;
    Button button_add;
    Button button_subtract;
    Button button_multiply;
    Button button_divide;
    Button button_equal;
    Button button_point;

    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    Button button_0;

    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_add = findViewById(R.id.button_add);
        button_subtract = findViewById(R.id.button_subtract);
        button_multiply = findViewById(R.id.button_multiply);
        button_divide = findViewById(R.id.button_divide);
        button_equal = findViewById(R.id.button_equal);
        button_point = findViewById(R.id.button_point);

        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_0 = findViewById(R.id.button_0);

        text = findViewById(R.id.text);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_0.setOnClickListener(this);
        button_add.setOnClickListener(this);
        button_subtract.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_equal.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
       input = text.getText().toString();
        switch (v.getId()){
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
            case R.id.button_0:
            case R.id.button_point:
                if(clean){
                    clean = false;
                    input = "";
                    text.setText("");
                }
                text.setText(input+((Button)v).getText()+"");
                break;
            case R.id.button_add:
            case R.id.button_subtract:
            case R.id.button_multiply:
            case R.id.button_divide:
                if(clean){
                    clean = false;

                    text.setText("");
                }
                text.setText(input+" "+((Button)v).getText()+" ");
                break;
            case R.id.button_equal:
                getResult();
                break;
        }

    }
    private void getResult(){
        String exp=text.getText().toString();
        if(exp==null||exp.equals("")){
            return;
        }
        if(!exp.contains("")){
            return;
        }
        if(clean){
            clean=false;
            input="";
            return;
        }
        clean=true;

        double result=0;
        String s1=exp.substring(0,exp.indexOf(" "));
        //运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //运算符后的数字
        String s2=exp.substring(exp.indexOf(" ")+3);
        if(!s1.equals("")&&!s2.equals("")){
            //如果包含小数点的运算
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                //如果是+
                result=d1+d2;
            }else if(op.equals("-")){
                //如果是-
                result=d1-d2;
            }else if(op.equals("*")){
                //如果是*
                result=d1*d2;
            }else if(op.equals("/")){
                if(d2==0){
                    //如果被除数是0
                    result=0;//则结果为0
                }else {
                    //否则执行正常运算
                    result=d1/d2;
                }
            }
            if(!s1.contains(".") &&!s2.contains(".")&&!op.equals("/")){
                //如果是整数类型
                int r=(int)result;//都是整形
                text.setText(r+"");
            }else {
                text.setText(result+"");
            }
        }else  if(!s1.equals("")&& s2.equals("")){
            //如果只输入运算符前的数字
            text.setText(exp);//直接返回当前输入内容
        }else if (s1.equals("")&& !s2.equals("")){
            //如果是只输入运算符后面的数
            double d2 =Double.parseDouble(s2);
            //运算符当前没有输入数字
            if(op.equals("+")){
                result= 0 + d2;
            }else  if(op.equals("-")){
                result= 0 - d2;
            }else if (op.equals("*")){
                result= 0;
            }else  if(op.equals("/")){
                result= 0;
            }
            if(!s1.contains(".")&&!s2.contains(".")){
                int r=(int) result;
                text.setText(r+"");}
            else {
                text.setText(result+"");
            }
        }else {
            text.setText("");
        }
    }
}




package com.bilgetech.nerdesiniz;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;

/**
 * TODO: implement
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);



        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();


        final LinearLayout layout=(LinearLayout)findViewById(R.id.background_layout);
        final Button loginBtn=(Button)findViewById(R.id.loginBtn);
        final EditText nameEt= (EditText) findViewById(R.id.name_et);
        final EditText roomEt= (EditText) findViewById(R.id.room_et);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.loginRG);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                intent.putExtra("name",nameEt.getText());
                intent.putExtra("room",roomEt.getText());
                startActivity(intent);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.yellowRb:
                        layout.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.color.yellow) );
                        loginBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.yellow) );
                        editor.putString("color","yellow");
                        editor.commit();
                        break;
                    case R.id.greenRb:
                        layout.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.color.green) );
                        loginBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.green) );
                        editor.putString("color","green");
                        editor.commit();
                        break;
                    case R.id.redRb:
                        layout.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.color.red) );
                        loginBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.red) );
                        editor.putString("color","red");
                        editor.commit();
                        break;
                    case R.id.blueRb:
                        layout.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.color.blue) );
                        loginBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.blue) );
                        editor.putString("color","blue");
                        editor.commit();


                }
            }
        });

    }


}

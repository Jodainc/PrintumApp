package com.kotan.printum.Ui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kotan.printum.R;

public class Welcoe extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcoe);
        button = (Button) findViewById(R.id.button3) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent10 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent10);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent10 = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent10);
    }

}

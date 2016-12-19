package com.kotan.printum.Ui.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kotan.printum.Model.Users;
import com.kotan.printum.Model.Users1;
import com.kotan.printum.Network.RegisServi;
import com.kotan.printum.Network.RestRegis;
import com.kotan.printum.Network.RestService;
import com.kotan.printum.R;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
public class Regiss extends AppCompatActivity {
    private Button button;
    private EditText editTextnit;
    private EditText editTextcel;
    private EditText editTextEmail;
    private EditText editTextNomre;
    private EditText editTextApeli;
    private EditText editTextDir;
    private EditText editTextcell;
    private RestRegis restRegis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_regiss);
        editTextnit = (EditText) findViewById(R.id.editText4);
        editTextcel = (EditText) findViewById(R.id.editText5);
        editTextEmail = (EditText) findViewById(R.id.editText6);
        editTextNomre = (EditText) findViewById(R.id.editText7);
        editTextApeli = (EditText) findViewById(R.id.editText8);
        editTextDir = (EditText) findViewById(R.id.editText9);
        editTextcell = (EditText) findViewById(R.id.editText10);
        restRegis = new RestRegis();
        button = (Button) findViewById(R.id.button) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nit = editTextnit.getText().toString();
                String cel = editTextcel.getText().toString();
                String email = editTextEmail.getText().toString();
                String nombre = editTextNomre.getText().toString();
                String Apeli = editTextApeli.getText().toString();
                String dir = editTextDir.getText().toString();
                String cell = editTextcell.getText().toString();
                if( !(nit.isEmpty()&& cel.isEmpty() && email.isEmpty() && nombre.isEmpty()&& Apeli.isEmpty()&& cell.isEmpty() && dir.isEmpty())){
                    Users1 a = new Users1(email,Apeli,"~/Content/StatePho/1.jpg",dir,nombre,cell,Integer.parseInt(cel)
                            ,Integer.parseInt(nit));
                 restRegis.getService().createUser(a, new Callback<Users1>() {
                     @Override
                     public void success(Users1 users1, Response response) {
                         Log.i("Entro::","");
                     }

                     @Override
                     public void failure(RetrofitError error) {
                         Log.i("Saleee::","");
                     }
                 });
                    Intent Itn1 =  new Intent(getApplicationContext(), Welcoe.class);
                    startActivity(Itn1);
                }







            }
        });
    }
}

package com.example.ufuk.remainingusage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

import model.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.PasswordService;
import service.ServiceGenerator;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class LoginActivity extends AppCompatActivity {

    private Button giris_Button ;
    private EditText telno ;
    private EditText password ;

    PasswordService passwordService;
    String stringcoming ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        giris_Button = (Button) findViewById(R.id.btn_giris);
        telno = (EditText) findViewById(R.id.txt_telno);
        password = (EditText) findViewById(R.id.txt_password);

        giris_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String md5Password =  new String(Hex.encodeHex(DigestUtils.sha(password.getText().toString())));
                //Log.d("MD5***********", "onClick: "+md5Password);
                passwordService = ServiceGenerator.createService(PasswordService.class);

                Call<Login> userCall = passwordService.getPasswordInfo(telno.getText().toString(),md5Password);

                userCall.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {

                        if (response.isSuccessful()) {

                            if (response.body() != null) {
                                stringcoming = response.body().getValue();//deger burada
                                Log.d("LOGINNNNNNNNNN", "onResponse: "+stringcoming);
                                //stringgetting = stringcoming.get(0); burası yok bu sefer
                                if (stringcoming.equals("Y"))
                                {
                                    String mesaj = telno.getText().toString();
                                    Intent intent = new Intent(LoginActivity.this , SecimActivity.class);
                                    intent.putExtra("cust_id" , mesaj);
                                    startActivity(intent);
                                }
                                else if (stringcoming.equals("N"))
                                {
                                    Toast.makeText(getApplicationContext(), "Şifre veya telefon numarasıs yanlış", Toast.LENGTH_LONG).show();
                                }
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Hata oluştu", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Hata oluştu", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}

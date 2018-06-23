package com.example.ufuk.remainingusage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.CustomerService;
import service.ServiceGenerator;

public class SecimActivity extends AppCompatActivity {

    private TextView ad ;
    private TextView tarife ;
    private Button button;

    CustomerService customerService;
    List<Customer> customerList = new ArrayList<>();

    Customer customer;

    String extra = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secim);

        ad = (TextView) findViewById(R.id.abone_ad);
        tarife = (TextView) findViewById(R.id.abone_tarife);
        button = (Button) findViewById(R.id.btn_devamet);


        Bundle bundle = getIntent().getExtras(); // intentteki parametreleri alır bundle a atar.
        if(bundle.getString("cust_id") != null)
        {
            extra = bundle.getString("cust_id"); // ilk activityden gelen intentteki stringteki extra değikenıne attı.
        }

        //servis olusturma
        customerService = ServiceGenerator.createService(CustomerService.class);

        //ilk activityden gelen intentin putExtrasındaki değeri parametre olarak web servise veriyoruz.
        Call<List<Customer>> userCall = customerService.getCustomerInfo(extra);


        userCall.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        customerList = response.body();

                         customer = customerList.get(0);

                        ad.setText(customer.getFIRSTNAME() + " " + customer.getLASTNAME());
                        tarife.setText(customer.getTARIFFNAME());
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Hata oluştu", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Hata oluştu", Toast.LENGTH_LONG).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gidecek = extra.toString();
                Intent intent = new Intent(SecimActivity.this , SonActivity.class);
                intent.putExtra("cust_id" , gidecek);
                startActivity(intent);
            }
        });

    }
}

package com.example.ufuk.remainingusage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import model.Remains;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.RemainsService;
import service.ServiceGenerator;

public class SonActivity extends AppCompatActivity {

    private  String   coming_phone_num;
    private  TextView  textView;

    private  String total_data_string ;
    private  Float  total_data_float ;
    private  String total_voice_string ;
    private  Float total_voice_float ;
    private  String total_sms_string ;
    private  Float  total_sms_float ;

    private  String remain_data_string;
    private  Float remain_data_float ;
    private  String remain_voice_string ;
    private  Float remain_voice_float ;
    private  String remain_sms_string ;
    private  Float remain_sms_float ;

    private Float used_data;
    private Float used_voice;
    private Float used_sms;

    PieChart pieChartData;
    PieChart pieChartVoice;
    PieChart pieChartSMS;

    RemainsService remainsService;

    List<Remains> remainsList = new ArrayList<>();

    Remains remains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_son);


        pieChartData = (PieChart) findViewById(R.id.chart);
        pieChartVoice = (PieChart) findViewById(R.id.chart2);
        pieChartSMS = (PieChart) findViewById(R.id.chart3);
        textView = (TextView) findViewById(R.id.txt_date);


        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("cust_id") != null)
        {
            coming_phone_num = bundle.getString("cust_id");
        }

        remainsService = ServiceGenerator.createService(RemainsService.class);

        Call<List<Remains>> userCall = remainsService.getCustomerRemains(coming_phone_num);

        userCall.enqueue(new Callback<List<Remains>>() {
            @Override
            public void onResponse(Call<List<Remains>> call, Response<List<Remains>> response) {


                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        remainsList = response.body();

                        remains = remainsList.get(0);

                        total_data_string = remains.getuSABLE_DATA();
                        total_sms_string = remains.getuSABLE_SMS();
                        total_voice_string = remains.getuSABLE_VOICE();
                        remain_data_string = remains.getrEMAIN_DATA();
                        remain_sms_string = remains.getrEMAIN_SMS();
                        remain_voice_string = remains.getrEMAIN_VOICE();


                        total_data_float = Float.parseFloat(total_data_string);
                        total_sms_float = Float.parseFloat(total_sms_string);
                        total_voice_float = Float.parseFloat(total_voice_string);
                        remain_data_float = Float.parseFloat(remain_data_string);
                        remain_sms_float = Float.parseFloat(remain_sms_string);
                        remain_voice_float = Float.parseFloat(remain_voice_string);

                        used_data = total_data_float - remain_data_float;
                        used_voice = total_voice_float - remain_voice_float;
                        used_sms = total_sms_float - remain_sms_float;

                        textView.setText("Son okuma tarihi :" + remains.getlAST_READ());


                        loadPieChart();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Hata oluştu", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Remains>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Hata oluştu", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadPieChart()
    {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(used_data, 0));
        entries.add(new Entry(remain_data_float, 1));

        PieDataSet dataset = new PieDataSet(entries, "");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("GB Used");
        labels.add("GB Remain");

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.JOYFUL_COLORS);

        pieChartData.setDescription("Using Internet");
        pieChartData.setData(data);

        pieChartData.animateY(5000);




        ArrayList<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(used_voice, 0));
        entries2.add(new Entry(remain_voice_float, 1));

        PieDataSet dataset2 = new PieDataSet(entries2, "");

        ArrayList<String> labels2 = new ArrayList<String>();
        labels2.add("DK Used");
        labels2.add("DK Remain");

        PieData data2 = new PieData(labels2, dataset2);
        dataset2.setColors(ColorTemplate.PASTEL_COLORS);

        pieChartVoice.setDescription("Using Minutes");
        pieChartVoice.setData(data2);

        pieChartVoice.animateY(5000);




        ArrayList<Entry> entries3 = new ArrayList<>();
        entries3.add(new Entry(used_sms, 0));
        entries3.add(new Entry(remain_sms_float, 1));

        PieDataSet dataset3 = new PieDataSet(entries3, "");

        ArrayList<String> labels3 = new ArrayList<String>();
        labels3.add("SMS Used");
        labels3.add("SMS Remain");

        PieData data3 = new PieData(labels3, dataset3);
        dataset3.setColors(ColorTemplate.LIBERTY_COLORS);

        pieChartSMS.setDescription("Using SMS");
        pieChartSMS.setData(data3);

        pieChartSMS.animateY(5000);
    }
}

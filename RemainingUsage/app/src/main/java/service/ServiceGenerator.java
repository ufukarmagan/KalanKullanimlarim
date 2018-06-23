package service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by UFUK on 21.6.2017.
 */

public class ServiceGenerator {

    public static String BASE_URL = "http://10.0.3.2:8080/MobileService/web/";
    public static Retrofit retrofit;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    public static <S> S createService(Class<S> serviceClass) {
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    public static Retrofit getRetrofit()
    {
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit;

    }


}
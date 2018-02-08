package codeutsava.wsso.splash_screen.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import codeutsava.wsso.helper.Urls;
import codeutsava.wsso.splash_screen.api.SplashScreenRequestApi;
import codeutsava.wsso.splash_screen.model.data.SplashScreenData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class RetrofitSplashScreenProvider implements SplashScreenProvider{

    private Retrofit retrofit;
    private SplashScreenRequestApi splashScreenRequestApi;

    public RetrofitSplashScreenProvider(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        splashScreenRequestApi = retrofit.create(SplashScreenRequestApi.class);
    }

    @Override
    public Observable<SplashScreenData> insertFcm(String fcm, String lat,String longitude,String access_token) {
        return splashScreenRequestApi.insertFcm(fcm,lat,longitude,access_token);
    }
}

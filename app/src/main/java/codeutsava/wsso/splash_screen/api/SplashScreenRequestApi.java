package codeutsava.wsso.splash_screen.api;



import codeutsava.wsso.helper.Urls;
import codeutsava.wsso.splash_screen.model.data.SplashScreenData;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface SplashScreenRequestApi {
    @FormUrlEncoded
    @POST(Urls.REQUEST_SPLASH_SCREEN)
    Observable<SplashScreenData> insertFcm(@Field("fcm") String fcm,@Field("latitude") String lat,@Field("longitude") String longitude, @Field("token") String access_token);

}

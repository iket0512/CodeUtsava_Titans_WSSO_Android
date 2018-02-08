package codeutsava.wsso.splash_screen.model;



import codeutsava.wsso.splash_screen.model.data.SplashScreenData;
import rx.Observable;


public interface SplashScreenProvider {
    Observable<SplashScreenData> insertFcm(String fcm, String lat,String longitude,String access_token);
}

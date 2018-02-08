package codeutsava.wsso.splash_screen.presenter;


import codeutsava.wsso.splash_screen.model.SplashScreenProvider;
import codeutsava.wsso.splash_screen.model.data.SplashScreenData;
import codeutsava.wsso.splash_screen.view.SplashScreenView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashScreenPresenterImpl implements SplashScreenPresenter {

    SplashScreenView splashScreenView;
    SplashScreenProvider splashScreenProvider;

    public SplashScreenPresenterImpl(SplashScreenView splashScreenView, SplashScreenProvider splashScreenProvider){
        this.splashScreenView = splashScreenView;
        this.splashScreenProvider = splashScreenProvider;
    }

    @Override
    public void insertFcm(String fcm,String lat,String longitude,String access_token) {
        splashScreenView.showProgressBar(true);
        Observable<SplashScreenData> splashScreenDataObservable = splashScreenProvider.insertFcm(fcm,lat,longitude,access_token);
        splashScreenDataObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<SplashScreenData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                splashScreenView.showMessage("Error");
            }

            @Override
            public void onNext(SplashScreenData splashScreenData) {

                if(splashScreenData.isSuccess()){
                    splashScreenView.fcmInsertStatus(splashScreenData);
                    splashScreenView.showProgressBar(false);

                }else{
                    splashScreenView.showMessage(splashScreenData.getMessage());
                }
            }
        });
    }
}

package codeutsava.wsso.splash_screen.view;


import codeutsava.wsso.splash_screen.model.data.SplashScreenData;

public interface SplashScreenView {

    void showMessage(String message);

    void fcmInsertStatus(SplashScreenData splashScreenData);

    void showProgressBar(boolean show);
}

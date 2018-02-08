package codeutsava.wsso.splash_screen.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import codeutsava.wsso.Posts.view.Posts;
import codeutsava.wsso.R;
import codeutsava.wsso.helper.MyApplication;
import codeutsava.wsso.helper.SharedPrefs;
import codeutsava.wsso.splash_screen.model.RetrofitSplashScreenProvider;
import codeutsava.wsso.splash_screen.model.data.SplashScreenData;
import codeutsava.wsso.splash_screen.presenter.SplashScreenPresenter;
import codeutsava.wsso.splash_screen.presenter.SplashScreenPresenterImpl;

public class SplashScreen extends Activity implements SplashScreenView, LocationListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    SharedPrefs sharedPrefs;
    MyApplication myApplication;
    ProgressBar progressBar, splashProgressBar;
    LocationManager locationManager;
    double latitude;
    double longitude;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPrefs = new SharedPrefs(this);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        if(!isGooglePlayServicesAvailable())
        {
            Log.d("Error","Google play services not available");
            finish();
        }
        getLocation();
        splashProgressBar = (ProgressBar) findViewById(R.id.splash_progress_bar);
        SplashScreenPresenter splashScreenPresenter = new SplashScreenPresenterImpl(this, new RetrofitSplashScreenProvider());

        Toast.makeText(SplashScreen.this, "Latitude:"+latitude+"\nLongitude"+longitude, Toast.LENGTH_LONG).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("LOCATION:", "" + latitude + "::::" + longitude+"fcm-"+MyApplication.getFcm());
                Intent in = new Intent(SplashScreen.this, Posts.class);
                startActivity(in);
                finish();
            }
        }, 5000);
//        splashScreenPresenter.insertFcm(MyApplication.fcm_token,""+latitude,""+longitude,sharedPrefs.getAccessToken());
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    private void getLocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            buildGoogleApiClient();
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
        else
            Log.d("location is null",""+bestProvider);
        }
        catch (Exception e)
        {
            Log.d("location is null"," ");
            e.printStackTrace();
        }

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(SplashScreen.this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void fcmInsertStatus(final SplashScreenData splashScreenData) {
         if (splashScreenData.isSuccess()) {
            sharedPrefs.setFCM(MyApplication.fcm_token);

                Intent in = new Intent(SplashScreen.this, Posts.class);
                startActivity(in);
                finish();
        }
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            splashProgressBar.setVisibility(View.VISIBLE);
        } else {
            splashProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("on location","here");
        latitude=location.getLatitude();
        longitude=location.getLongitude();
//        Log.d("LOCATION:", "" + latitude + "::::" + longitude+"fcm-"+MyApplication.getFcm());
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status,this, 0).show();
            return false;
        }
    }
}



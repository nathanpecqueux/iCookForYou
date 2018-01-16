package fr.univ_littoral.nathan.myapplication;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by alexd on 16/01/2018.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(1000);
    }
}

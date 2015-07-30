package androidhive.info.materialdesign.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidhive.info.materialdesign.R;

/**
 * Created by a.stankiewicz on 2015-07-28.
 */
public class SplashScreenActivity extends Activity {

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        i = new Intent(this, MainActivity.class);

        //getSupportActionBar().hide();

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    startActivity(i);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}

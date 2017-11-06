package com.intrinio.androiddemo;

import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intrinio.realtime.RealTimeClient;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String username = "YOUR_INTRINIO_API_USERNAME";
        String password = "YOUR_INTRINIO_API_PASSWORD";

        try (RealTimeClient client = new RealTimeClient(username, password, RealTimeClient.Provider.QUODD)) {
            client.registerQuoteHandler(quote ->
                    System.out.println(quote.toString())
            );
            String[] channels = new String[]{"BAC.NB"};
            client.join(channels);
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

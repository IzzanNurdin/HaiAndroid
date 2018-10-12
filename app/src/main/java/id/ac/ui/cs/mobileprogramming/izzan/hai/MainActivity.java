package id.ac.ui.cs.mobileprogramming.izzan.hai;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.SimpleDateFormat;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView clock = (TextView) findViewById(R.id.clock);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy\nhh:mm:ss a");
                                String dateView = dateFormat.format(date);

                                clock.setText(dateView);

                                TextView ipView = (TextView) findViewById(R.id.ipView);
                                WifiManager wifiManager;
                                wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                                if(wifiManager.getConnectionInfo().getIpAddress() == 0) {
                                    ipView.setText("Wifi not connected");
                                } else {
                                    String ip = String.valueOf(wifiManager.getConnectionInfo().getIpAddress());
                                    ipView.setText(ip);
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }

    public void startService(View view) {
        Intent intent = new Intent(this, MainService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, MainService.class);
        stopService(intent);
//        @BindView(R.id.textService)
    }
}

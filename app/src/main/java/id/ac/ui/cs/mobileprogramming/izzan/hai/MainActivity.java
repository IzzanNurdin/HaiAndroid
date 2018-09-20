package id.ac.ui.cs.mobileprogramming.izzan.hai;

import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends Activity {

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
                                WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
                                String ip = String.valueOf(wm.getConnectionInfo().getIpAddress());

                                ipView.setText(ip);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }
}

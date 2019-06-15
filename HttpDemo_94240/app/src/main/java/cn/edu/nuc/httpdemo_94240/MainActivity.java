package cn.edu.nuc.httpdemo_94240;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button btnSendRequest = null;
    private TextView tvResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSendRequest = findViewById(R.id.btnSendRequest);
        tvResponse = findViewById(R.id.tvResponse);

        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithHttpURLConnection();
            }

            private void sendRequestWithHttpURLConnection() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection connection = null;
                        BufferedReader reader = null;

                        try {
                            URL url = new URL("http://www.nuc.edu.cn");

                            connection  = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(5000);
                            connection.setReadTimeout(5000);

                            InputStream inputStream = connection.getInputStream();
                            reader = new BufferedReader(new InputStreamReader(inputStream));

                            StringBuilder responseStr = new StringBuilder();
                            String line = null;

                            while((line = reader.readLine()) != null){
                                responseStr.append(line);
                            }
                            showResponse(responseStr.toString());

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            if(reader != null){
                                try {
                                    reader.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(connection != null){
                                connection.disconnect();
                            }
                        }
                    }

                    private void showResponse(String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvResponse.setText(toString());
                            }
                        });
                    }
                }).start();
            }
        });
    }
}

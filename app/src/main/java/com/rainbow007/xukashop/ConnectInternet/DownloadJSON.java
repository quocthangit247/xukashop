package com.rainbow007.xukashop.ConnectInternet;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rainbow007 on 3/15/18.
 */

public class DownloadJSON extends AsyncTask<String, Void, String> {

    String duongdan;
    List<HashMap<String, String>> attrs;
    StringBuilder dulieu;

    public DownloadJSON(String URL) {
        this.duongdan = URL;
    }

    public DownloadJSON(String URL, List<HashMap<String, String>> attrs) {
        this.duongdan = URL;
        this.attrs = attrs;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(duongdan);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (attrs.size() != 0) {
                methodPost(httpURLConnection);
            } else {
                methodGet(httpURLConnection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dulieu.toString();
    }

    private String methodGet(HttpURLConnection httpURLConnection) {
        String data = "";
        InputStream inputStream = null;
        try {
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            dulieu = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                dulieu.append(line);
            }
            data = dulieu.toString();
            bufferedReader.close();
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private String methodPost(HttpURLConnection httpURLConnection) {
        String data = "";
        String key = "";
        String value = "";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();
            int count = attrs.size();
            for (int i = 0; i < count; i++) {
                for (Map.Entry<String, String> values : attrs.get(i).entrySet()) {
                    key = values.getKey();
                    value = values.getValue();

                }
                builder.appendQueryParameter(key, value);
            }

            String query = builder.build().getEncodedQuery();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(streamWriter);

            writer.write(query);
            writer.flush();
            writer.close();
            streamWriter.close();
            outputStream.close();

            data = methodGet(httpURLConnection);


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
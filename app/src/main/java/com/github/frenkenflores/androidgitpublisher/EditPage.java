package com.github.frenkenflores.androidgitpublisher;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.Intent;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPut;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.params.HttpParamsNames;
import cz.msebera.android.httpclient.util.EntityUtils;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
public class EditPage extends AppCompatActivity {
    private EditText editText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_edit_page);
        Intent intent = getIntent();
        String url = intent.getStringExtra("link");
        try {
            //  curl   -H "Accept: application/vnd.github.raw"   -H "Authorization: Bearer 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"   https://api.github.com/repos/ghalghai/ghalgahi_mott/contents/app/src/main/assets/dict.txt
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            get.setHeader("Accept", "application/vnd.github.raw");
            get.setHeader("Authorization", "Bearer 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");
            HttpResponse responseGet = client.execute(get);
            HttpEntity resEntityGet = responseGet.getEntity();
            String resp = "";
            if (resEntityGet != null) {
                //do something with the response
                resp = EntityUtils.toString(resEntityGet);
                Log.i("GET ", resp);
                editText = (EditText) findViewById(R.id.edit_text_view);
                editText.setText(resp);
            }
            Button editButton = (Button) findViewById(R.id.edit_button);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    get.setHeader("Accept", "application/vnd.github+json");
                    get.setHeader("Authorization", "Bearer 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");
                    HttpResponse responseGet = null;
                    try {
                        responseGet = client.execute(get);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    HttpEntity resEntityGet = responseGet.getEntity();
                    String resp2 = null;
                    if (resEntityGet != null) {
                        //do something with the response
                        try {
                            resp2 = EntityUtils.toString(resEntityGet);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i("GET ", resp2);
                        resp2 = resp2.split("html\":\"")[1].split("\"")[0].split("/blob/master")[0];
                        Log.i("GET2 ", resp2);
//                        editText = (EditText) findViewById(R.id.edit_text_view);
//                        editText.setText(resp2);
                    }
                    Log.i("LOG", url.split("/contents/")[0] + "/contributors");
                    HttpGet get2 = new HttpGet(url.split("/contents/")[0] + "/contributors");
                    get2.setHeader("Accept", "application/vnd.github+json");
                    get2.setHeader("Authorization", "Bearer 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");
                    responseGet = null;
                    try {
                        responseGet = client.execute(get2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    resEntityGet = responseGet.getEntity();
                    String resp3 = null;
                    if (resEntityGet != null) {
                        //do something with the response
                        try {
                            resp3 = EntityUtils.toString(resEntityGet);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i("GET3 ", resp3);
                    }
//                    AsyncTask asyncTask = new AsyncTask() {
//                        @SuppressLint("StaticFieldLeak")
//                        @Override
//                        protected Object doInBackground(Object[] objects) {
//                            URL new_url = null;
//                            try {
//                                new_url = new URL(url);
//                            } catch (MalformedURLException e) {
//                                e.printStackTrace();
//                            }
//                            HttpURLConnection connection = null;
//                            try {
//                                connection = (HttpURLConnection) new_url.openConnection();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                connection.setRequestMethod("PUT");
//                            } catch (ProtocolException e) {
//                                e.printStackTrace();
//                            }
//                            connection.setDoOutput(true);
//                            connection.setRequestProperty("Accept", "application/vnd.github.raw");
//                            connection.setRequestProperty("Authorization", "Bearer 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");
//                            connection.setRequestProperty("FrenkenFlores", "request");
//                            OutputStreamWriter osw = null;
//                            try {
//                                osw = new OutputStreamWriter(connection.getOutputStream());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
////                            '{"message":"my commit message","committer":{"name":"Monalisa Octocat","email":"octocat@github.com"},"content":"bXkgbmV3IGZpbGUgY29udGVudHM="}'
//                            try {
//                                String req = new String("{\"message\":\"Edit file\",\"committer\":{\"name\":\"FrenkenFlores\",\"email\":\"saifualdin.evloev@gmail.com\"},\"content\":\"bXkgbmV3IGZpbGUgY29udGVudHM=\",\"sha\":\"bddf1c7de79471573c761e281d5d02e23b70d9c0\"}");
//                                Log.i("PUT", req);
//                                osw.write(req);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                osw.flush();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                osw.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                System.err.println(connection.getResponseCode());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            return null;
//                        }
//                    };
//                    asyncTask.execute();
//                    curl -X PUT -H "Accept: application/vnd.github+json" -H "Authorization: Bearer <YOUR-TOKEN>" https://api.github.com/repos/OWNER/REPO/contents/PATH -d '{"message":"my commit message","committer":{"name":"Monalisa Octocat","email":"octocat@github.com"},"content":"bXkgbmV3IGZpbGUgY29udGVudHM="}'

//                    Intent openBrowser = new Intent(Intent.ACTION_VIEW);
//                    openBrowser.setData(Uri.parse(resp2));
//                    startActivity(openBrowser);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
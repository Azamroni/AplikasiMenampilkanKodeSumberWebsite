package com.example.ronnygep.aplikasipenampilankodesumberwibsite;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Terhubung_Internet extends AsyncTask<String,Void,String> {

    Context ctx;
    public Terhubung_Internet(Context ct){
    ctx=ct;
    }


    @Override
    protected String doInBackground(String... strings) {
        String s1=strings[0];
        InputStream in;
        try {
            URL myurl=new URL(s1);
            HttpURLConnection mycon=(HttpURLConnection)myurl.openConnection();
            mycon.setReadTimeout(10000);
            mycon.setConnectTimeout(20000);
            mycon.setRequestMethod("GET");
            mycon.connect();
            in=mycon.getInputStream();
            BufferedReader mybuf=new BufferedReader(new InputStreamReader(in));
            StringBuilder st=new StringBuilder();
            String line="";
            while((line=mybuf.readLine())!=null)
            {
                st.append(line+"  \n");
            }
            mybuf.close();
            in.close();
            return  st.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.mytext.setText(s);

    }
}

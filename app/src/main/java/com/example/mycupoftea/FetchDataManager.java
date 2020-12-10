package com.example.mycupoftea;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
public class FetchDataManager extends AppCompatActivity
{
    /**
     * Reads in JSON available at URL and returns it in string format
     * @param URL webpage containing JSON
     * @return JSON string
     */
    static String getJSONString(String URL)
    {
        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;
        String jsonString = null;
        try
        {
            Uri builtUri = Uri.parse(URL);

            httpURLConnection = (HttpURLConnection) ((new URL(builtUri.toString()).openConnection()));
            httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            Log.d("FetchDataManager-->", "Response Code: " + httpURLConnection.getResponseCode() + "");

            InputStream inputStream = httpURLConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String line;
            while((line = reader.readLine())!= null)
            {
                builder.append(line);
                builder.append("\n");
            }
            if(builder.length() == 0)
            {
                return null;
            }
            httpURLConnection.disconnect();
            jsonString = builder.toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(httpURLConnection != null)
            {
                httpURLConnection.disconnect();
            }
            if(reader != null)
            {
                try
                {
                    reader.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return jsonString;
    }
    /**
     * reads in a local raw JSON file and returns it in string format
     * Note: For purposes of this assignment
     * @param fileResourceID locked to raw resource
     * @return JSON string
     */
    public static String getJSONString(Context context, @RawRes int fileResourceID)
    {
        InputStream is = context.getResources().openRawResource(fileResourceID);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try
        {
            Reader reader = null;
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            int n;
            while ((n = reader.read(buffer)) != -1)
            {
                writer.write(buffer, 0, n);
            }
            is.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return writer.toString();
    }
}

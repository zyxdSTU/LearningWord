package util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Util {
    public static void write(String fileName, List<String> lines) throws  Exception{
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        FileOutputStream bis = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter =new OutputStreamWriter(bis,"UTF-8");
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
        for(String line : lines) {
            writer.write(line + '\n');
            writer.flush();
        }
        writer.close();
    }

    public static List<String> read(String fileName) throws Exception{
        File file = new File(fileName);
        FileInputStream bis = new FileInputStream(file);
        InputStreamReader inputStreamReader =new InputStreamReader(bis,"UTF-8");
        BufferedReader reader = new BufferedReader(inputStreamReader);
        List<String> lines = new ArrayList<>();
        String line = "";
        while((line = reader.readLine()) != null) {
            //lines.add(line);
            lines.add(EncodingUtils.getString(line.getBytes("utf-8"), "utf-8"));
        }
        reader.close();
        return lines;
    }

    //读入raw里面的文件
    public static List<String> readInternal(InputStream inputStream) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> lines = new ArrayList<>();
        String line = "";
        while((line = reader.readLine()) != null) {
            //Log.d("Debug", line);
            //lines.add(line);
            lines.add(EncodingUtils.getString(line.getBytes("utf-8"), "utf-8"));
        }
        return lines;
    }

    /**发送请求**/
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}

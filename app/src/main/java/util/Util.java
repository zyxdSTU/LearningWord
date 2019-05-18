package util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static void write(String fileName, List<String> lines) throws  Exception{
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for(String line : lines) {
            writer.write(line + '\n');
            writer.flush();
        }
        writer.close();
    }

    public static List<String> read(String fileName) throws Exception{
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();
        String line = "";
        while((line = reader.readLine()) != null) {
            //lines.add(line);
            lines.add(EncodingUtils.getString(line.getBytes("utf-8"), "utf-8"));
        }
        return lines;
    }
}

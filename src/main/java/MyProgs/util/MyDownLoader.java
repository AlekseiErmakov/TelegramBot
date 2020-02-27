package MyProgs.util;

import javax.print.DocFlavor;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.stream.Stream;

public class MyDownLoader {
    public static void downloadFile(String adress,String path){
        try{
            URL url = new URL(adress);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(path);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String downloadString(String adress){
        String result = "";
        InputStream inp = getInputStream(adress);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inp))){
            String inputlane;
            while ((inputlane = reader.readLine()) != null){
                sb.append(inputlane);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    private static InputStream getInputStream(String adress){
        InputStream inputStream = null;
        try {
            URL url = new URL(adress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type","aplication/json");
            con.setConnectTimeout(1000);
            con.setReadTimeout(1000);
            inputStream = con.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}

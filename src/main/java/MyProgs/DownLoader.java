package MyProgs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownLoader implements Runnable{

    private void downloadXML(){
        try{
            URL url = new URL("https://www.cbr-xml-daily.ru/daily_utf8.xml");
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("Valute.xml");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                downloadXML();
                Thread.sleep(1000*60*60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

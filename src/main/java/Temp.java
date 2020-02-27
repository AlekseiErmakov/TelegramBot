import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Temp {
    public static void main(String[] args) {
        Temp t = new Temp();
        t.run();
    }
    public void run(){
        PrintStream consoleStream = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PrintStream stream = new PrintStream(outputStream);

        System.setOut(stream);

        print("Let's do something");

        String result = outputStream.toString();


        System.setOut(consoleStream);

        System.out.println(result.toLowerCase());
        result = result.toUpperCase();
        System.out.println(result);

    }


    public void print(String request){
        System.out.println(request);
    }
}

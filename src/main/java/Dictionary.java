

import lombok.Getter;

import java.io.PrintWriter;
import java.util.*;


public class Dictionary {

    private List<String> greetigs;

    private List<String> farewels;

    private List<String> commands;

    private Map<String,String> secret;

    public Dictionary(){
        greetigs = makeGreetings();
        farewels = makeFarewels();
        secret = makeSecretAns();
        commands = makeCommand();
    }
    public boolean isFrase(String request){
        request = request.toLowerCase();
        boolean g = greetigs.contains(request);
        boolean f = farewels.contains(request);
        boolean s = secret.containsKey(request);
        return g || f || s;
    }
    public boolean isCommand(String request){
        return commands.contains(request);
    }
    public String getAnswer(String request){
        request = request.toLowerCase();
        if (greetigs.contains(request)){
            return greetigs.get(getRandom(greetigs));
        }else if (farewels.contains(request)){
            return farewels.get(getRandom(farewels));
        } else{
            return secret.get(request);
        }
    }
    private List<String> makeGreetings(){
        String[] greet = {"привет","прива","здорово","здорова","здравствуйте","привет бот","здравствуй","хай",
                "дороу","привки","добрый день","добрый вечер","доброй ночи","доброе устро","hello","здоровеньки булы",
                "хех, ну здравствуй","guten tag","хауди хо","че как"};
        return Arrays.asList(greet);
    }
    private List<String> makeFarewels(){
        String[] farewel = {"пока","прощай","асталависта бейби","до свидания","увидимся","бывай","удвчи",
                "до скорой встречи","good bay","до свидания"};
        return Arrays.asList(farewel);
    }
    private Map<String,String> makeSecretAns(){
        Map<String,String> secret = new HashMap<>();
        secret.put("да прибудет с тобой сила","да прибудет с тобой сила");
        secret.put("валар моргулис","валар дохаэрис");
        secret.put("valar morghulis","valar dahaeris");
        secret.put("что мы говорим богу смерти","не сегодня");
        secret.put("вперед черепахи","кавабунга!!!!");
        return secret;
    }
    private List<String> makeCommand(){
        String[] commands = {"/commands","/reverse","/math","/piramide","/emoji","/factorial"};
        return Arrays.asList(commands);
    }
    private int getRandom(List<String> col){
        Random r = new Random();
        return r.nextInt(col.size()-1);
    }


}

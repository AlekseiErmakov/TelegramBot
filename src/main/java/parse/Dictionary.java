package parse;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.*;


public class Dictionary {

    private List<String> greetigs;

    private List<String> farewels;



    private Map<String,String> secret;

    public Dictionary(){
        greetigs = makeGreetings();
        farewels = makeFarewels();
        secret = makeSecretAns();


    }

    public boolean isGreeting(String request){
        return greetigs.contains(request);
    }
    public boolean isFarewell(String request){
        return greetigs.contains(request);
    }
    public boolean isSecretAnswer(String request){
        return secret.containsKey(request);
    }
    public boolean isKnown(String request){
        return isGreeting(request) || isFarewell(request) || isSecretAnswer(request);
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

    public String getAnswer(String request){
        if (isGreeting(request)){
            return greetigs.get(getRandom(greetigs));
        } else if (isFarewell(request)){
            return farewels.get(getRandom(farewels));
        } else if (isSecretAnswer(request)){
            return secret.get(request);
        }
        return "";
    }
    private int getRandom(List<String> col){
        Random r = new Random();
        return r.nextInt(col.size()-1);
    }


}

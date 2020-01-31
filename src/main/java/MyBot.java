import MyProgs.util.DownLoader;
import parse.RequestParser;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import personal.MyPersonal;


public class MyBot extends TelegramLongPollingBot implements MyPersonal {

    private RequestParser requestParser;
    private Thread downlowder;

    public static void main(String[] args)  {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new MyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    public MyBot(){
        requestParser = new RequestParser();
        downlowder = new Thread(new DownLoader());
        downlowder.start();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()){

                Message inMessage = update.getMessage();
                SendMessage outMessage = new SendMessage();
                outMessage.setChatId(inMessage.getChatId());
                outMessage.setText(requestParser.getResult(inMessage.getText()));

                execute(outMessage);
            }
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOTNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }


}

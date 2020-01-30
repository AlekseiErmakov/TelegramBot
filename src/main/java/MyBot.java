import parse.RequestParser;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import personal.BotName;


public class MyBot extends TelegramLongPollingBot implements BotName {

    private RequestParser requestParser;

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

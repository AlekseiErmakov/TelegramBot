import MyProgs.util.DOMxmlReader;

import org.telegram.telegrambots.meta.api.methods.groupadministration.KickChatMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatTitle;
import org.telegram.telegrambots.meta.api.methods.pinnedmessages.PinChatMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.ChatPhoto;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;
import parse.Dictionary;
import parse.RequestParserExt;
import personal.MyPersonal;


public class MyBot extends TelegramLongPollingBot implements MyPersonal {

    private RequestParserExt requestParser;
    private Thread valuteUpdaiter;
    private Dictionary dict;

    public static void main(String[] args)  {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new MyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    public MyBot()  {
        requestParser = new RequestParserExt();
        dict = new Dictionary();
        valuteUpdaiter = new Thread(new DOMxmlReader());
        valuteUpdaiter.start();


    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()){
                Message inMessage = update.getMessage();
                SendMessage outMessage = new SendMessage();
                String userRequest = inMessage.getText();
                outMessage.setChatId(inMessage.getChatId());
                System.out.println(inMessage.getChatId());
                if (isCommand(userRequest)){
                    outMessage.setText(requestParser.getResult(inMessage.getText()));
                    execute(outMessage);
                } else {
                    String answer = dict.getAnswer(inMessage.getText());
                    if (!answer.equals("")){
                        outMessage.setText(answer);
                        execute(outMessage);
                    }
                }

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

    private boolean isCommand(String request){
        return request.startsWith("/");
    }


}

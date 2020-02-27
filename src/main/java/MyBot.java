import MyProgs.Emoji;
import MyProgs.util.DOMxmlReader;



import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChat;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatDescription;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatTitle;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import parse.Dictionary;
import parse.RequestParserExt;
import personal.MyPersonal;


public class MyBot extends TelegramLongPollingBot implements MyPersonal {

    private RequestParserExt requestParser;
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
        Thread t = new Thread(new DOMxmlReader());
        t.start();
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage outMessage;
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {

                Message inMessage = update.getMessage();

                String answer = getAnswer(inMessage);
                if (!answer.equals("")){
                    outMessage = getOutMessage(inMessage,answer);
                    execute(outMessage);
                }
                SendMessage mes = new SendMessage();
                mes.setChatId(inMessage.getChatId());
                mes.setText(Emoji.ENVELOPE.toString());
                execute(mes);
            }

        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    private SendMessage getOutMessage(Message inMessage, String answer){
        SendMessage message = new SendMessage();
        message.setChatId(inMessage.getChatId());
        message.setText(answer);
        return message;
    }
    private String getAnswer(Message inMessage){
        String userRequest = inMessage.getText();
        String answer = "";
       if (isCommand(userRequest)){
           answer = requestParser.getResult(userRequest);
       } else if (dict.isKnown(userRequest)) {
           answer = dict.getAnswer(userRequest);
       }
       return answer;
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

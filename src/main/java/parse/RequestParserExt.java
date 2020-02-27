package parse;

import MyProgs.*;
import MyProgs.inteface.Program;


import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class RequestParserExt {
    Dictionary dic;
    Map<Commands,Program> comandMap;


    public RequestParserExt(){
        dic = new Dictionary();
        comandMap = getComMap();
    }

    public String getResult(String request){
        String userCommand = getCommand(request);
        String result;
        for (Map.Entry<Commands,Program> pair : comandMap.entrySet()){
            Commands command = pair.getKey();
            if (userCommand.equals(command.toString())){
                return comandMap.get(command).getResult(request);
            }
        }
        result = getCommandList();
        return result;
    }
    private String getCommand(String request){
        String[] temp = request.split("\\s+");
        return temp[0].substring(1);
    }

    private Map<Commands, Program> getComMap(){
        Map<Commands,Program> commands = new EnumMap<>(Commands.class);
        commands.put(Commands.PASSWORD, new RandomPasProg());
        commands.put(Commands.RCAT, new RandomCatProg());
        commands.put(Commands.VALUTE, new ValuteProg());
        commands.put(Commands.FACTORIAL, new FactorialProg());
        commands.put(Commands.REVERSE, new ReverseProg());
        return commands;
    }
    public String getCommandList(){
        StringBuilder sb = new StringBuilder();
        for (Commands commands : Commands.values()){
            sb.append(commands.getDescription());
            sb.append("\n");
        }
        return sb.toString();
    }


}

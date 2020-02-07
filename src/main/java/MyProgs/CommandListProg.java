package MyProgs;

import MyProgs.inteface.AbsProg;
import parse.Dictionary;

public class CommandListProg extends AbsProg {
    Dictionary dictionary;
    public CommandListProg(){
        dictionary = new Dictionary();
    }
    @Override
    public String getResult(String request) {
        return dictionary.getCommandList();
    }
}

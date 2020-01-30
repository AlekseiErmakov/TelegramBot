package MyProgs;

import MyProgs.Program;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class StartProg implements Program {
    private Map<String,Program> pMap;
    private Program program;



    @Override
    public String getCommand() {
        return NOTIFICATION;
    }

    @Override
    public String getResult(String request) {
        String result = NOTIFICATION;
        pMap = getProgs();
        if (pMap.containsKey(request)){
            program = pMap.get(request);
            return program.getCommand();
        }else if (program != null && !pMap.containsKey(request)){
            result = program.getResult(request);
        }
        return result;
    }

    private Map<String,Program> getProgs(){
        Map<String,Program> pMap = new HashMap<>();
        Program progCommand = new CommandProg();
        pMap.put(ANOUTHER,progCommand);
        pMap.put(REVERSE,new ReverseProg());
        return pMap;
    }
}

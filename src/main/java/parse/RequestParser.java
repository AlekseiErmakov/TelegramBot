package parse;

import MyProgs.FactorialProg;
import MyProgs.RandomPasProg;
import MyProgs.ReverseProg;
import MyProgs.ValuteProg;
import MyProgs.inteface.Program;


public class RequestParser {
    private Program program;
    private Dictionary dictionary;
    private boolean isRun = false;
    private static final String STOP = "/stop";
    private static final String REVERSE = "/reverse";
    private static final String FACTORIAL = "/factorial";
    private static final String PASSWORD = "/password";
    private static final String VALUTE = "/valute";
    private static final String PROGSTOP = "Программа остановлена!";
    private static final String NONESTOP = "Мне нечего остановить!";

    public RequestParser() {
        dictionary = new Dictionary();

    }

    public String getResult(String request) {
        if (isRun && request.equals(STOP)) {
            isRun = false;
            return PROGSTOP;
        } else if (request.equals(STOP)) {
            return NONESTOP;
        } else if (dictionary.isCommand(request)) {
            program = getProgram(request);
            isRun = true;
            return program.getCommand();
        } else if (!isRun && dictionary.isFrase(request)) {
            return dictionary.getAnswer(request);
        } else if (isRun) {
            return program.getResult(request);
        }
        return "Даже и не знаю что сказать";
    }

    private Program getProgram(String request) {
        Program program = null;
        switch (request) {
            case (REVERSE):
                program = new ReverseProg();
                break;
            case (FACTORIAL):
                program = new FactorialProg();
                break;
            case (PASSWORD):
                program = new RandomPasProg();
                break;
            case (VALUTE):
                program = new ValuteProg();
                break;
        }
        return program;
    }

}

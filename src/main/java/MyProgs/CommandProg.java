package MyProgs;

public class CommandProg implements Program {
    @Override
    public String getCommand() {
        return GREETINGS;
    }

    @Override
    public String getResult(String request) {
        return START;
    }
}

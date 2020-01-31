package MyProgs;

import MyProgs.bean.Valute;
import MyProgs.data.ValuteList;
import MyProgs.inteface.Program;
import MyProgs.util.DOMxmlReader;

import java.util.ArrayList;
import java.util.List;

public class ValuteProg implements Program {
    private static final String TITLE = "Напиши название валюты и я скажу ее курс или /currencies для получения списка";
    private static final String LIST = "/currencies";
    private static final String LISTTITLE = "Вот список валют: ";
    private ValuteList valuteList;
    private DOMxmlReader readerXML;
    private List<String> valutes;
    public ValuteProg(){
        readerXML = new DOMxmlReader();
        valuteList = ValuteList.getInstance();
    }
    @Override
    public String getCommand() {
        return TITLE;
    }

    @Override
    public String getResult(String request) {
        if (request.equals(LIST)){
            return LISTTITLE + "\n" + getValuteComandList();
        } else {
            for (Valute valute : valuteList.getList()){
                if (request.equalsIgnoreCase(valute.getName())){
                    return valute.toString();
                }
            }
            request = request.toLowerCase();
            String Start = getStart(request,valuteList.getList());
            String End = getEnd(request,valuteList.getList());
            String Sub = getContain(request,valuteList.getList());

            if (!Start.equals("") && !End.equals("")){
                return Start + End;
            } else if(!Start.equals("")){
                return Start;
            } else if(!End.equals("")){
                return End;
            } else if (!Sub.equals("") && End.equals("") && End.equals("")){
                return Sub;
            }

        }
        return "такой валюты нет";
    }
    private String getValuteComandList(){
        valutes = new ArrayList<>();
        for (Valute valute : valuteList.getList()) {
            valutes.add(valute.getNominal() + " " + valute.getName());
        }
        return getList(valutes);
    }
    private String getList(List<String> strings){
        StringBuilder sb = new StringBuilder();
        for (String str : strings){
            sb.append(str);
            sb.append("\n");
        }
        return sb.toString();
    }
    private boolean isValute(String value, String request){
        String[] temp = value.split("\\s+");
        return request.length() > 2 && temp[temp.length-1].length() > 2 &&
                request.substring(0,3).equals(temp[temp.length-1].substring(0,3));
    }
    private String getContain(String request , List<Valute> list){
        StringBuilder sb = new StringBuilder();
        for (Valute valute : list){
            String name = valute.getName().toLowerCase();
            if (isValute(name,request)){
                sb.append(valute.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    private String getStart(String request , List<Valute> list){
        StringBuilder sb = new StringBuilder();
        for (Valute valute : list){
            String name = valute.getName().toLowerCase();
            if (request.length() < name.length() && name.startsWith(request)){
                sb.append(valute.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    private String getEnd(String request , List<Valute> list){
        StringBuilder sb = new StringBuilder();
        for (Valute valute : list){
            String name = valute.getName().toLowerCase();
            if (request.length() < name.length() && name.endsWith(request)){
                sb.append(valute.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    private String getsub(String request, List<Valute> list){
        StringBuilder sb = new StringBuilder();
        return "";
    }
}

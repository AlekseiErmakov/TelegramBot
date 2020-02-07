package MyProgs;

import MyProgs.bean.Valute;
import MyProgs.data.ValuteList;
import MyProgs.inteface.AbsProg;
import MyProgs.inteface.Program;
import MyProgs.util.DOMxmlReader;

import java.util.ArrayList;
import java.util.List;

public class ValuteProg extends AbsProg {
    private static final String ADRESS = "https://www.cbr-xml-daily.ru/daily_utf8.xml";

    private static final String LIST = "/currencies";
    private static final String LISTTITLE = "Вот список валют: ";
    private ValuteList valuteList;
    private DOMxmlReader readerXML;
    private List<String> valutes;
    public ValuteProg(){
        valuteList = ValuteList.getInstance();
    }


    @Override
    public String getResult(String request) {
        String userRequest = getUserRequest(request);
        if (userRequest.equals(LIST)){
            return LISTTITLE + "\n" + getValuteComandList();
        } else {
            for (Valute valute : valuteList.getList()){
                if (userRequest.equalsIgnoreCase(valute.getName())){
                    return valute.toString();
                }
            }
            userRequest = userRequest.toLowerCase();
            String Start = getStart(userRequest,valuteList.getList());
            String End = getEnd(userRequest,valuteList.getList());
            String Sub = getContain(userRequest,valuteList.getList());

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

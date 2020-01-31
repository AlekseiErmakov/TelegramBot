package MyProgs;

import MyProgs.bean.Valute;
import MyProgs.data.ValuteList;
import MyProgs.inteface.Program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
            StringBuilder sbST = new StringBuilder();
            StringBuilder sbEn = new StringBuilder();
            StringBuilder sub = new StringBuilder();
            List<String> tempList = new ArrayList<>();
            for (Valute valute : valuteList.getList()){
                if (valute.getName().toLowerCase().startsWith(request)){
                    sbST.append(valute);
                    sbST.append("\n");
                }
                if (valute.getName().toLowerCase().endsWith(request)){
                    sbEn.append(valute);
                    sbEn.append("\n");
                }
                if (isValute(valute.getName(),request)){
                    tempList.add(valute.toString());
                }
            }
            if (tempList.size()>0){
                for (String valute : tempList){
                    sub.append(valute);
                    sub.append("\n");
                }
            }
            if (!sbST.toString().equals("")){
                return sbST.toString();
            } else if(!sbEn.toString().equals("")){
                return sbEn.toString();
            } else if (!sub.toString().equals("") && sbEn.toString().equals("") && sbST.toString().equals("")){
                return sub.toString();
            }

        }
        return "такой валюты нет";
    }
    private String getValuteComandList(){
        valutes = new ArrayList<>();
        for (Valute valute : valuteList.getList()) {
            valutes.add(valute.getName());
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
}

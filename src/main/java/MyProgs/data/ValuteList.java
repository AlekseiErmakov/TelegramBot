package MyProgs.data;

import MyProgs.bean.Valute;


import java.util.ArrayList;
import java.util.List;

public class ValuteList {
    private List<Valute> list;
    private static ValuteList valuteList;
    private ValuteList(){
        list = new ArrayList<>();
    }
    public static ValuteList getInstance(){
        if (valuteList == null){
            valuteList = new ValuteList();
        }
        return valuteList;
    }
    public Valute getValute(String name){
        for (Valute valute : list){
            if (name.equals(valute.getName())){
                return  valute;
            }
        }
        return null;
    }
    public void addValute(Valute valute){
        list.add(valute);
    }
    public List<Valute> getList(){
        return list;
    }

}

import MyProgs.ValuteProg;
import MyProgs.bean.Valute;
import MyProgs.data.ValuteList;
import MyProgs.inteface.Program;
import org.junit.Test;

public class ValuteTest {


    @Test
    public void test(){
        Program program = new ValuteProg();
        String[] va = {"манат", "доллар", "Евро", "рубль", "гривен"};
        System.out.println(program.getResult("Евро"));

        for (String str : va){
            System.out.println(program.getResult(str));
        }
        ValuteList valuteList = ValuteList.getInstance();

        Valute valute = new Valute(978,"EUR","Евро");
        valute.setNominal(1);
        valute.setValue(69.4151);
        for (Valute valute1 : valuteList.getList()){
            if (valute1.equals(valute)){
                System.out.println("Evro");
            }
        }
        System.out.println(valuteList.getValute("Евро"));
        System.out.println(program.getResult("Евро"));

        System.out.println(valute);
    }

}

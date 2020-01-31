import MyProgs.ValuteProg;
import MyProgs.bean.Valute;
import MyProgs.data.ValuteList;
import MyProgs.inteface.Program;
import org.junit.Test;

public class ValuteTest {


    @Test
    public void test(){
        Program program = new ValuteProg();
        String[] va = { "доллар"};


        for (String str : va){
            System.out.println(program.getResult(str));
        }





    }

}

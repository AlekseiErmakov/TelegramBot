package MyProgs;

import MyProgs.inteface.AbsProg;
import MyProgs.inteface.Program;

import java.io.ByteArrayOutputStream;
import java.util.Random;

public class RandomPasProg extends AbsProg {

    @Override
    public String getResult(String request) {
        String result = "Некорректное число";
        String userRequest = getUserRequest(request);
        try {
            int num = Integer.parseInt(userRequest);
            if (num > 0){
                result = getPassword(num);
            }else {
                result = "Число должнно быть больше 0";
            }

        }catch (Exception ex){
            return result;
        }
        return result;
    }
    private String getPassword(int num) {
        StringBuilder sb = new StringBuilder();
        int k;
        char x;
        boolean k1 = false;
        boolean k2 = false;
        boolean k3 = false;
        Random r = new Random();
        for (int i = 0 ; i  < num; i++){
            k =(int)(1 + Math.random()*3);
            if(k == 1){
                x = (char)(r.nextInt(26) + 'a');
                sb.append(x);
                k1 = true;
            }else if(k == 2){
                x = (char)(r.nextInt(26) + 'A');
                sb.append(x);
                k2 = true;
            } else{
                x = (char)(r.nextInt(9) + '0');
                sb.append(x);
                k3 = true;
            }
        }
        if (k1 && k2 && k3){
            return sb.toString();
        } else{
            return getPassword(num);
        }

    }
}

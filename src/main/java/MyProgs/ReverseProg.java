package MyProgs;

import MyProgs.inteface.AbsProg;
import MyProgs.inteface.Program;

public class ReverseProg extends AbsProg {


    @Override
    public String getResult(String message) {
        String result = "Неверный ввод";
        String userRequest = getUserRequest(message);
        if (!userRequest.equals("")){
            result = changeStr(userRequest);
        }

        return result;
    }
    private String changeStr(String string){
        String result = "";
        String str = string.replaceAll("[^\\p{L}]", " ");
        String[] temp = str.split("\\s+");
        int j = 0;
        for (int i = 0; i < string.length(); i++){
            if(j < temp.length && temp[j].length() + i <= string.length() && string.substring(i,i+temp[j].length()).equals(temp[j])){
                result += reverse(temp[j]);
                i += temp[j].length()-1;
                j++;
            } else {
                result += string.substring(i,i+1);
            }
        }
        result = result.toLowerCase();
        result = result.substring(0,1).toUpperCase()+result.substring(1);
        return result;
    }
    private String reverse(String word){
        StringBuilder sb = new StringBuilder();
        for(int i = word.length()-1; i >= 0;i--){
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }
}

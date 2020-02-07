package MyProgs.inteface;

public abstract class AbsProg implements Program{
    @Override
    public String getUserRequest(String request) {
        String[] temp = request.split("\\s+");
        StringBuilder sb = new StringBuilder();
        if (temp.length > 1){
            for (int i = 1; i < temp.length; i++ ){
                if (i < temp.length-1){
                    sb.append(temp[i]);
                    sb.append(" ");
                } else{
                    sb.append(temp[i]);
                }
            }
        }
        return sb.toString();
    }
}

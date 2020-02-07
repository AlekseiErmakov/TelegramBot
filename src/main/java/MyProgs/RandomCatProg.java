package MyProgs;

import MyProgs.inteface.AbsProg;

import MyProgs.util.MyDownLoader;




public class RandomCatProg extends AbsProg {
    private static final String address = "https://aws.random.cat/meow";


    @Override
    public String getResult(String request) {
        String temp = MyDownLoader.downloadString(address);
        temp = temp.replaceAll("\\\\","");
        temp = temp.substring(temp.indexOf('h'),temp.length()-2);
        return temp;
    }

}

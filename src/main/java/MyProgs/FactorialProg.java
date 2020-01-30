package MyProgs;

import MyProgs.inteface.Program;

public class FactorialProg implements Program {
    @Override
    public String getCommand() {
        return "Напиши число и я посчитаю его факториал";
    }

    @Override
    public String getResult(String request) {
        String result = "";
        try {
            int num = Integer.parseInt(request);
            if (num > 0){
                result = String.valueOf(factorial(num));
            } else {
                result = "Число может быть только положительным";
            }

        }catch (Exception ex){
            result = "Некорректные данные";
        }
        return result;
    }
    public int factorial(int n) {
        int result = 1;
        if (n == 1 || n == 0) {
            return result;
        }
        result = n * factorial(n-1);
        return result;
    }
}

package parse;

public enum  Commands {
    VALUTE{
        @Override
        public String getDescription() {
            String desc = "\"название валюты на русском языке\" и я отправлю ее курс";
            return super.getDescription() + desc;
        }
    },PASSWORD{
        @Override
        public String getDescription() {
            String desc = "\"число от 1 до 20\" и я сгенерирую пароль этой";
            return super.getDescription() + desc;
        }
    }
    ,RCAT{
        @Override
        public String getDescription() {
            String desc = "и я оправлю тебе случайную картинку с котиком";
            return super.getDescription()+desc;
        }
    },FACTORIAL{
        @Override
        public String getDescription() {
            String desc = "\"число\" и я поситаю его факториал";
            return super.getDescription()+desc;
        }
    },COMMANDS{
        @Override
        public String getDescription() {
            String desc = "и я отправлю тебе список команд";
            return super.getDescription()+desc;
        }
    },REVERSE{
        @Override
        public String getDescription() {
            String desc = "\"текст\" и я переверну в нем все слова наобарот";
            return super.getDescription()+desc;
        }
    };

    @Override
    public String toString() {
        return name().toLowerCase();
    }
    public String getDescription(){
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(name().toLowerCase());
        sb.append(" ");
        return sb.toString();
    }
}

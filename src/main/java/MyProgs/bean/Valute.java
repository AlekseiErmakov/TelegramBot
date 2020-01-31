package MyProgs.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode

public class Valute {
    private final int numCode;
    private final String charCode;
    @Setter
    private int nominal;
    private final String name;
    @Setter
    private double value;

    public Valute( int numCode, String charCode, String name){
        this.numCode = numCode;
        this.charCode = charCode;
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nominal);
        sb.append(" ");
        sb.append(name);
        sb.append(" ");
        sb.append("=");
        sb.append(" ");
        sb.append(value);
        sb.append(" ");
        sb.append("руб.");
        return sb.toString();
    }
}

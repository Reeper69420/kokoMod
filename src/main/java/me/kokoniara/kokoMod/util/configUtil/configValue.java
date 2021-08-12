package me.kokoniara.kokoMod.util.configUtil;

public class configValue {
    public String name;
    public boolean bolvalue;
    public int intvalue;
    public double doublevalue;

    public configValue(String name, int intvalue){
        this.name = name;
        this.intvalue = intvalue;
    }

    public configValue(String name, double doublevalue){
        this.name = name;
        this.doublevalue = doublevalue;
    }

    public configValue(String name, boolean bolvalue){
        this.name = name;
        this.bolvalue = bolvalue;
    }

}

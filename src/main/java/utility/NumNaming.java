package utility;

public class NumNaming {

    public String getName(int count){

        String str = "" + count;
        String padding = "000";

        return padding.substring(0, padding.length() - str.length()) + str;
    }

}

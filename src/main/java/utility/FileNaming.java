package utility;

public class FileNaming {

    public String getName(int count, boolean moreThanOnce) {
        NumNaming numNaming = new NumNaming();
        String numCount = "";
        if (moreThanOnce) {
            numCount = "-" + numNaming.getName(count);
        }
        return numCount;
    }
}

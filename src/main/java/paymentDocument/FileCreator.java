package paymentDocument;

import enums.Bic;
import utility.DateFormat;
import utility.FileNaming;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class FileCreator {

    String numCount;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void writeToFile(String environment, String date, String currency, String amount,
                            Bic bic, String creditorAccount, String creditorName, int count, boolean moreThanOnce) {

        String path = "Files/";

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            DateFormat newDateFormat = new DateFormat();
            String titleDate = newDateFormat.getDate(date, "MM-dd");
            String executionDate = newDateFormat.getDate(date, "YYMMdd");

            FileNaming fileNaming = new FileNaming();
            numCount = fileNaming.getName(count, moreThanOnce);

            FileWriter myWriter = new FileWriter(path + environment + "-TGT2-MT103-" + titleDate + "T" +
                    ZonedDateTime.now(ZoneId.of("Europe/Vilnius")).format(DateTimeFormatter.ofPattern("HH.mm.ss"))
                    + numCount + ".so");
            Payment payment = new Payment();
            myWriter.write(payment.getTextBody(executionDate, currency, amount, bic, creditorAccount, creditorName));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println(e.getClass().getName());
            e.printStackTrace();
        }
    }
}

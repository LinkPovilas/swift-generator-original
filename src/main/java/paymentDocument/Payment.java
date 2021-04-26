package paymentDocument;

import enums.Bic;
import utility.RandomString;

public class Payment {

    public String getTextBody(String date, String currency, String amount, Bic bic, String creditorAccount,
                              String creditorName) {

        //ToDo should read swiftTemplate.txt as resource stream instead.
        String longString = "{1:F01!BIC!AXXX4782631362}{2:O1030908160111NDEAFI2XAXXX49660467441601110908N}{3:{103:TGT}{113:NYNN}{108:xxxxx}{115:080829080829LT0000000668605307}}{4:\n" +
                ":20:!Random!\n" +
                ":23B:CRED\n" +
                ":32A:!Date!!Currency!!Amount!,\n" +
                ":33B:!Currency!!Amount!,\n" +
                ":50K:/FI2412003000036707\n" +
                "Testinis NDEA Suomija\n" +
                ":52A:NDEAFI2XXXX\n" +
                ":57A:!BIC!XXX\n" +
                ":59:/!CreditorAccount!\n" +
                "!CreditorName!\n" +
                ":70:Test payment from Target2\n" +
                ":71A:SHA\n" +
                "-}";

        RandomString randomString = new RandomString();
        String text = longString;
        text = text.replaceAll("!Date!", date);
        text = text.replaceAll("!Random!", randomString.getAlphaNumericString(20));
        text = text.replaceAll("!Currency!", currency);
        text = text.replaceAll("!Amount!", amount);
        text = text.replaceAll("!BIC!", bic.toString());
        text = text.replaceAll("!CreditorAccount!", creditorAccount);
        text = text.replaceAll("!CreditorName!", creditorName);

        return text;
    }
}

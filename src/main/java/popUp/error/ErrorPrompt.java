package popUp.error;

import javax.swing.*;
import java.util.List;
import java.util.regex.Pattern;

public class ErrorPrompt {

    public void checkError(JPanel parent, List<JTextField> list) throws Exception {

        String
                date = list.get(0).getText(), name = list.get(1).getText(),
                iban = list.get(2).getText(), amount = list.get(3).getText(),
                number = list.get(4).getText();

        Pattern numPattern = Pattern.compile("\\d+");
        Pattern checkForZeroes = Pattern.compile("^[1-9][0-9]*$");

        if (date.isEmpty() && number.isEmpty() && name.isEmpty() && iban.isEmpty() && amount.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "System Error ( ͡° ͜ʖ ͡°)");
            throw new Exception("All the fields are empty");
        }

        if (Integer.parseInt(number) > 500) {
            JOptionPane.showMessageDialog(parent, "Restricted to maximum: 500 files");
            throw new Exception("Too many files requested error");
        }

        if (!checkForZeroes.matcher(number).matches()) {
            JOptionPane.showMessageDialog(parent, "Sanity error");
            throw new Exception("Sanity error");
        }

        if (date.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Date is empty");
            throw new Exception("Date is empty");
        }

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Name is empty");
            throw new Exception("Name is empty");
        }

        if (iban.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "iban is empty");
            throw new Exception("iban is empty");
        }

        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Amount is empty");
            throw new Exception("Amount is empty");
        }

        if (!numPattern.matcher(amount).matches()) {
            JOptionPane.showMessageDialog(parent, "Wrong amount format. Please enter only Whole Numbers.");
            throw new Exception("Wrong amount format");
        }

        if (!checkForZeroes.matcher(amount).matches()) {
            JOptionPane.showMessageDialog(parent, "Wrong amount format. Amount cannot start with \"0\".");
            throw new Exception("Wrong amount format");
        }
    }
}

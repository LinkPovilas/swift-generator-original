package panelElements;

import restrictions.LengthRestriction;
import restrictions.NumericFieldRestrictions;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class FieldSettings {

    public void setDocFields(List<JTextField> list) {

        JTextField
                date = list.get(0), name = list.get(1),
                iban = list.get(2), amount = list.get(3),
                number = list.get(4);

        date.setDocument(new LengthRestriction(10));
        date.setText(LocalDate.now().toString());
        name.setDocument(new LengthRestriction(35));
        iban.setDocument(new LengthRestriction(31));
        number.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        amount.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        number.setText("1");
        ((AbstractDocument) number.getDocument()).setDocumentFilter(new NumericFieldRestrictions(3));
        ((AbstractDocument) amount.getDocument()).setDocumentFilter(new NumericFieldRestrictions(20));
    }
}

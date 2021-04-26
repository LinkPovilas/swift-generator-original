package restrictions;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericFieldRestrictions extends DocumentFilter {

    private final int length;

    public NumericFieldRestrictions(int length) {
        this.length = length;
    }

    private boolean isNumeric(String text) {
        try {
            if (text.trim().isEmpty()) {
                return true;
            }

            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        if (isNumeric(text)) {
            if (this.length > 0 && fb.getDocument().getLength() + text.length() > this.length) {
                return;
            }
            super.insertString(fb, offset, text, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isNumeric(text)) {
            if (this.length > 0 && fb.getDocument().getLength() + text.length() > this.length) {
                return;
            }
            super.replace(fb, offset, length, text, attrs);
        }
    }
}

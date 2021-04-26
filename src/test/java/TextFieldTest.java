import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import panelElements.FieldSettings;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class TextFieldTest {

    final JTextField
            date = new JTextField(6), name = new JTextField(20),
            iban = new JTextField(20), amount = new JTextField(13),
            number = new JTextField(2);

    final List<JTextField> textFields = Arrays.asList(date, name, iban, amount, number);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testTextFieldInputs() {
        FieldSettings fieldSettings = new FieldSettings();
        fieldSettings.setDocFields(textFields);

        //Text field returns current date
        assertTextFieldNotEmpty(date);

        //Empty on launch
        assertTextFieldIsEmpty(name);
        assertTextFieldIsEmpty(iban);
        assertTextFieldIsEmpty(amount);

        //Text field contains "1" initially
        assertTextFieldEqualsTo(number, "1");

        //Text field reset to empty, then to "1"
        number.setText("");
        assertTextFieldIsEmpty(number);
        number.setText("1");
        assertTextFieldEqualsTo(number, "1");

        //Text field length restriction is 3
        number.setText("");
        number.setText("1234");
        assertTextFieldIsEmpty(number);

        number.setText("123");
        assertTextFieldEqualsTo(number, "123");

        //Text field restricted to 10 characters
        date.setText("ShouldBe10NotMore");
        assertTextFieldIsEmpty(date);

        date.setText("ShouldBe10");
        assertTextFieldEqualsTo(date, "ShouldBe10");

        //Whole numbers only or reset to Empty
        amount.setText("11.00");
        assertTextFieldIsEmpty(amount);
        amount.setText("ABCabc");
        assertTextFieldIsEmpty(amount);
        amount.setText(".");
        assertTextFieldIsEmpty(amount);
        amount.setText("101");
        assertTextFieldNotEmpty(amount);
        amount.setText("");
        assertTextFieldIsEmpty(amount);
    }

    public void assertTextFieldNotEmpty(JTextField text) {
        Assert.assertFalse("Text field " + text + " is empty", text.getText().isEmpty());
    }

    public void assertTextFieldIsEmpty(JTextField text) {
        Assert.assertTrue("Text field " + text + " is empty", text.getText().isEmpty());
    }

    public void assertTextFieldEqualsTo(JTextField text, String expectedText) {
        Assert.assertEquals("Text field " + text + " is not equal to: "
                + expectedText, text.getText(), expectedText);
    }
}
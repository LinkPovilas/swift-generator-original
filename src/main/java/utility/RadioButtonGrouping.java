package utility;

import javax.swing.*;
import java.util.List;

public class RadioButtonGrouping {

    public Box addToBoxAndButtonGroup(List<JRadioButton> list, String name) {
        Box box = Box.createHorizontalBox();
        ButtonGroup buttonGroup = new ButtonGroup();
        for (JRadioButton radioButton : list) {
            box.add(radioButton);
            buttonGroup.add(radioButton);
        }
        box.setBorder(BorderFactory.createTitledBorder(name));
        return box;
    }
}

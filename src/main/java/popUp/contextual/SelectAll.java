package popUp.contextual;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import java.awt.event.ActionEvent;

public class SelectAll extends TextAction {

    public SelectAll() {
        super("Select All");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control A"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextComponent component = getFocusedComponent();
        component.selectAll();
        component.requestFocusInWindow();
    }
}


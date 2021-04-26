package popUp.contextual;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.util.List;

public class ContextMenu {

    public void enablePopUp(JPopupMenu menu) {
        Action cut = new DefaultEditorKit.CutAction();
        cut.putValue(Action.NAME, "Cut");
        cut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
        menu.add(cut);

        Action copy = new DefaultEditorKit.CopyAction();
        copy.putValue(Action.NAME, "Copy");
        copy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
        menu.add(copy);

        Action paste = new DefaultEditorKit.PasteAction();
        paste.putValue(Action.NAME, "Paste");
        paste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
        menu.add(paste);

        Action selectAll = new SelectAll();
        menu.add(selectAll);
    }

    public void setComponentPopUp(JPopupMenu menu, List<JTextField> list) {
        for (JTextField jTextField : list) {
            jTextField.setComponentPopupMenu(menu);
        }
    }
}

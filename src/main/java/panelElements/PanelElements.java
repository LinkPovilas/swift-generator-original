package panelElements;

import utility.RadioButtonGrouping;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class PanelElements extends JFrame {

    public JPanel setPanelElements(List<JRadioButton> jRadioButtonList, JTextField number,
                                   List<JTextField> jTextFieldList, JComboBox<String> currency, JButton jButton) {
        JTextField
                date = jTextFieldList.get(0), name = jTextFieldList.get(1),
                iban = jTextFieldList.get(2), amount = jTextFieldList.get(3);

        JPanel panel = new JPanel(new GridBagLayout());
        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel amountPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new GridBagLayout());

        RadioButtonGrouping grouping = new RadioButtonGrouping();
        addItem(topPanel, grouping.addToBoxAndButtonGroup(jRadioButtonList, "Environment"),
                0, 0, 2, 1, GridBagConstraints.NORTH);

        addItem(mainPanel, new JLabel("Date:"), 0, 1, 1, 1, GridBagConstraints.WEST);
        addItem(mainPanel, new JLabel("Name:"), 0, 2, 1, 1, GridBagConstraints.WEST);
        addItem(mainPanel, new JLabel("IBAN:"), 0, 3, 1, 1, GridBagConstraints.WEST);
        addItem(mainPanel, new JLabel("Number of files:  "), 3, 1, 1, 1,
                GridBagConstraints.WEST);
        addItem(mainPanel, number, 3, 1, 1, 1, GridBagConstraints.EAST);
        addItem(mainPanel, date, 1, 1, 2, 1, GridBagConstraints.WEST);
        addItem(mainPanel, name, 1, 2, 4, 1, GridBagConstraints.EAST);
        addItem(mainPanel, iban, 1, 3, 4, 1, GridBagConstraints.EAST);

        amountPanel.setBorder(BorderFactory.createTitledBorder("Amount"));
        addItem(amountPanel, amount, 0, 0, 1, 2, GridBagConstraints.WEST);
        addItem(amountPanel, currency, 1, 0, 1, 1, GridBagConstraints.WEST);
        addItem(buttonPanel, jButton, 0, 0, 3, 1, GridBagConstraints.CENTER);

        addItem(panel, topPanel, 0, 1, 2, 1, GridBagConstraints.NORTH);
        addItem(panel, mainPanel, 0, 2, 1, 1, GridBagConstraints.CENTER);
        addItem(mainPanel, amountPanel, 0, 4, 4, 1, GridBagConstraints.EAST);
        addItem(panel, buttonPanel, 0, 4, 2, 1, GridBagConstraints.SOUTH);

        return panel;
    }

    private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        p.add(c, gc);
    }

}

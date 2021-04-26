import enums.Currencies;
import panelElements.PanelElements;
import panelElements.FieldSettings;
import panelElements.ParentPanel;
import paymentDocument.FileCreator;
import popUp.contextual.ContextMenu;
import popUp.error.ErrorPrompt;
import utility.Environment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

    final JRadioButton
            alt = new JRadioButton("ALT"), alv = new JRadioButton("ALV"),
            aee = new JRadioButton("AEE"), flt = new JRadioButton("FLT"),
            flv = new JRadioButton("FLV"), fee = new JRadioButton("FEE");

    final JTextField
            date = new JTextField(6), name = new JTextField(20),
            iban = new JTextField(20), amount = new JTextField(13),
            number = new JTextField(2);

    final List<JTextField> textFields = Arrays.asList(date, name, iban, amount, number);

    final List<JRadioButton> environments = Arrays.asList(alt, alv, aee, flt, flv, fee);

    final JComboBox<String> currencyList = new JComboBox<>(Arrays.stream(
            Currencies.values()).map(Enum::toString).toArray(String[]::new));

    final JButton generateButton = new JButton("Generate");

    final JMenuItem reset = new JMenuItem("Reset");

    String selectedRadioBtn = "ALT";

    final ParentPanel parentPanel = new ParentPanel();

    public void launchGui() {

        this.setTitle("SWIFT Generator");
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu file = new JMenu("File");
        menuBar.add(file);
        file.add(reset);

        alt.setSelected(true);
        FieldSettings fieldSets = new FieldSettings();
        fieldSets.setDocFields(textFields);

        addToListener(reset);
        addToListener(environments);
        addToListener(generateButton);

        JPopupMenu menu = new JPopupMenu();
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.enablePopUp(menu);
        contextMenu.setComponentPopUp(menu, textFields);

        PanelElements panel = new PanelElements();
        JPanel panel2 = panel.setPanelElements(environments, number, textFields, currencyList, generateButton);
        this.add(panel2);

        parentPanel.setParent(panel2);

        this.pack();
        this.setVisible(true);
    }

    public void addToListener(JMenuItem jMenuItem) {
        jMenuItem.addActionListener(this);
    }

    public void addToListener(List<JRadioButton> list) {
        for (JRadioButton radioButton : list) {
            radioButton.addActionListener(this);
        }
    }

    public void addToListener(JButton jButton) {
        jButton.addActionListener(this);
    }

    public void getRadioBtnValue(String button) {
        selectedRadioBtn = button;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JRadioButton) {
            JRadioButton radioButton = (JRadioButton) e.getSource();
            if (radioButton.isSelected()) {
                getRadioBtnValue(radioButton.getText());
            }
        }

        if (e.getSource() == reset) {
            date.setText(LocalDate.now().toString());
            //ToDO fix bug if 3 characters are present then cannot replace with a numeric
            number.setText("");
            number.setText("1");
            name.setText("");
            iban.setText("");
            amount.setText("");
        }

        if (e.getSource() == generateButton) {

            int times = Integer.parseInt(number.getText());

            boolean error;

            try {
                ErrorPrompt errorPrompt = new ErrorPrompt();
                errorPrompt.checkError(parentPanel.getParent(), textFields);
                error = false;
            } catch (Exception x) {
                //ToDO move this to ErrorPrompt class
                if (x.getClass().getName().equals("java.time.format.DateTimeParseException")) {
                    JOptionPane.showMessageDialog(parentPanel.getParent(), "You've entered: " + "\"" +
                            date.getText() + "\"" + ". Utility.DateFormat format should be YYYY-MM-DD");
                }
                x.printStackTrace();
                error = true;
            }

            if (!error) {
                int result = JOptionPane.showConfirmDialog(parentPanel.getParent(),
                        "Write " + times + " file(s)?", "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        FileCreator fileCreator = new FileCreator();
                        Environment environment = new Environment();
                        String selectedCurrency = Objects.requireNonNull(currencyList.getSelectedItem()).toString();
                        int count = 1;
                        boolean moreThanOne;
                        moreThanOne = times > 1;
                        while (count <= times) {
                            fileCreator.writeToFile(
                                    selectedRadioBtn,
                                    date.getText(),
                                    selectedCurrency,
                                    amount.getText(),
                                    environment.setBic(selectedRadioBtn),
                                    iban.getText(),
                                    name.getText(),
                                    count,
                                    moreThanOne);
                            count = count + 1;
                        }
                        JOptionPane.showMessageDialog(parentPanel.getParent(), "Accepted");
                    } catch (Exception x) {
                        x.printStackTrace();
                    }
                } else if (result == JOptionPane.CANCEL_OPTION) {
                    System.out.println("Client declined");
                } else {
                    System.out.println("Client closed confirmation window");
                }
            }
        }
    }
}

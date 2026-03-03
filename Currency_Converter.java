import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Currency_Converter extends JFrame implements ActionListener {

    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;
    private JButton convertButton;
    private HashMap<String, Double> rates;

    public Currency_Converter() {

        setTitle("Currency Converter");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Currency Rates (Base USD)
        rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("INR", 83.0);
        rates.put("EUR", 0.92);
        rates.put("GBP", 0.78);
        rates.put("JPY", 150.0);

        // Main Panel with Gradient
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(36, 198, 220),
                        0, getHeight(), new Color(81, 74, 157));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 15);

        // Title
        JLabel title = new JLabel("Currency Converter");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;

        // Amount
        gbc.gridy++;
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setFont(labelFont);
        amountLabel.setForeground(Color.WHITE);
        mainPanel.add(amountLabel, gbc);

        gbc.gridx = 1;
        amountField = new JTextField(12);
        amountField.setFont(fieldFont);
        mainPanel.add(amountField, gbc);

        // From Currency
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(labelFont);
        fromLabel.setForeground(Color.WHITE);
        mainPanel.add(fromLabel, gbc);

        gbc.gridx = 1;
        fromCurrency = new JComboBox<>(rates.keySet().toArray(new String[0]));
        fromCurrency.setFont(fieldFont);
        mainPanel.add(fromCurrency, gbc);

        // To Currency
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(labelFont);
        toLabel.setForeground(Color.WHITE);
        mainPanel.add(toLabel, gbc);

        gbc.gridx = 1;
        toCurrency = new JComboBox<>(rates.keySet().toArray(new String[0]));
        toCurrency.setFont(fieldFont);
        mainPanel.add(toCurrency, gbc);

        // Convert Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        convertButton.setBackground(new Color(255, 140, 0));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        convertButton.addActionListener(this);

        mainPanel.add(convertButton, gbc);

        // Result Label
        gbc.gridy++;
        resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultLabel.setForeground(Color.YELLOW);
        mainPanel.add(resultLabel, gbc);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double fromRate = rates.get(from);
            double toRate = rates.get(to);

            double usd = amount / fromRate;
            double converted = usd * toRate;

            resultLabel.setText("Converted Amount: " + String.format("%.2f", converted) + " " + to);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid number!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Currency_Converter().setVisible(true);
        });
    }
}
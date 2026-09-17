import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;

public class PasswordGeneratorGUI extends JFrame {

    // Character sets
    private static final String UPPERCASE =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String LOWERCASE =
            "abcdefghijklmnopqrstuvwxyz";

    private static final String NUMBERS =
            "0123456789";

    private static final String SPECIAL =
            "!@#$%^&*()-_=+[]{}<>?";

    // Secure random generator
    private final SecureRandom random = new SecureRandom();

    // GUI components
    private JPasswordField passwordField;
    private JSpinner lengthSpinner;

    private JCheckBox uppercaseCheck;
    private JCheckBox lowercaseCheck;
    private JCheckBox numbersCheck;
    private JCheckBox specialCheck;

    private JProgressBar strengthBar;
    private JLabel strengthLabel;

    // Constructor
    public PasswordGeneratorGUI() {

        setTitle("Secure Password Generator");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        createGUI();
    }

    // Create GUI
    private void createGUI() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(
                new EmptyBorder(20, 25, 20, 25)
        );

        // ==============================
        // TITLE
        // ==============================

        JLabel title = new JLabel(
                "SECURE PASSWORD GENERATOR",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        mainPanel.add(title, BorderLayout.NORTH);

        // ==============================
        // CENTER PANEL
        // ==============================

        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        // ==============================
        // PASSWORD FIELD
        // ==============================

        JPanel passwordPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        JLabel passwordLabel =
                new JLabel("Password:");

        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        passwordField.setEditable(false);

        // Hide password initially
        passwordField.setEchoChar('•');

        JButton showButton =
                new JButton("Show");

        passwordPanel.add(
                passwordLabel,
                BorderLayout.WEST
        );

        passwordPanel.add(
                passwordField,
                BorderLayout.CENTER
        );

        passwordPanel.add(
                showButton,
                BorderLayout.EAST
        );

        centerPanel.add(passwordPanel);

        centerPanel.add(
                Box.createVerticalStrut(20)
        );

        // ==============================
        // PASSWORD LENGTH
        // ==============================

        JPanel lengthPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        JLabel lengthLabel =
                new JLabel("Password Length:");

        lengthSpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                12,
                                4,
                                50,
                                1
                        )
                );

        lengthPanel.add(lengthLabel);
        lengthPanel.add(lengthSpinner);

        centerPanel.add(lengthPanel);

        // ==============================
        // CHARACTER OPTIONS
        // ==============================

        JLabel optionTitle =
                new JLabel("Character Options");

        optionTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        centerPanel.add(optionTitle);

        uppercaseCheck =
                new JCheckBox(
                        "Uppercase Letters (A-Z)",
                        true
                );

        lowercaseCheck =
                new JCheckBox(
                        "Lowercase Letters (a-z)",
                        true
                );

        numbersCheck =
                new JCheckBox(
                        "Numbers (0-9)",
                        true
                );

        specialCheck =
                new JCheckBox(
                        "Special Characters (!@#$)",
                        true
                );

        centerPanel.add(uppercaseCheck);
        centerPanel.add(lowercaseCheck);
        centerPanel.add(numbersCheck);
        centerPanel.add(specialCheck);

        centerPanel.add(
                Box.createVerticalStrut(15)
        );

        // ==============================
        // PASSWORD STRENGTH
        // ==============================

        JLabel strengthTitle =
                new JLabel("Password Strength");

        strengthTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        centerPanel.add(strengthTitle);

        strengthBar =
                new JProgressBar(0, 100);

        strengthBar.setValue(0);

        strengthBar.setStringPainted(true);

        strengthBar.setString("0%");

        centerPanel.add(strengthBar);

        strengthLabel =
                new JLabel(
                        "Strength: Not Generated"
                );

        strengthLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        centerPanel.add(strengthLabel);

        centerPanel.add(
                Box.createVerticalStrut(20)
        );

        // ==============================
        // BUTTONS
        // ==============================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );

        JButton generateButton =
                new JButton(
                        "Generate Password"
                );

        JButton copyButton =
                new JButton("Copy");

        JButton clearButton =
                new JButton("Clear");

        buttonPanel.add(generateButton);
        buttonPanel.add(copyButton);
        buttonPanel.add(clearButton);

        centerPanel.add(buttonPanel);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ==============================
        // BUTTON ACTIONS
        // ==============================

        // Generate password
        generateButton.addActionListener(
                e -> generatePassword()
        );

        // Copy password
        copyButton.addActionListener(
                e -> copyPassword()
        );

        // Clear
        clearButton.addActionListener(
                e -> clearAll()
        );

        // Show / Hide password
        showButton.addActionListener(e -> {

            if (passwordField.getEchoChar() == 0) {

                // Hide password
                passwordField.setEchoChar('•');

                showButton.setText("Show");

            } else {

                // Show password
                passwordField.setEchoChar((char) 0);

                showButton.setText("Hide");
            }
        });

        add(mainPanel);
    }

    // ==============================
    // GENERATE PASSWORD
    // ==============================

    private void generatePassword() {

        int length =
                (Integer) lengthSpinner.getValue();

        String characters = "";

        // Add selected character types

        if (uppercaseCheck.isSelected()) {

            characters += UPPERCASE;
        }

        if (lowercaseCheck.isSelected()) {

            characters += LOWERCASE;
        }

        if (numbersCheck.isSelected()) {

            characters += NUMBERS;
        }

        if (specialCheck.isSelected()) {

            characters += SPECIAL;
        }

        // Check character selection

        if (characters.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select at least one character type.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Generate password

        StringBuilder password =
                new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index =
                    random.nextInt(
                            characters.length()
                    );

            password.append(
                    characters.charAt(index)
            );
        }

        // Display password

        passwordField.setText(
                password.toString()
        );

        // Calculate password strength

        calculateStrength(
                password.toString()
        );
    }

    // ==============================
    // PASSWORD STRENGTH
    // ==============================

    private void calculateStrength(
            String password
    ) {

        int score = 0;

        int length =
                password.length();

        // ------------------------------
        // Length score
        // ------------------------------

        if (length >= 8) {

            score += 20;
        }

        if (length >= 12) {

            score += 20;
        }

        if (length >= 16) {

            score += 10;
        }

        // ------------------------------
        // Character variety
        // ------------------------------

        // Uppercase
        if (password.matches(
                ".*[A-Z].*"
        )) {

            score += 15;
        }

        // Lowercase
        if (password.matches(
                ".*[a-z].*"
        )) {

            score += 15;
        }

        // Number
        if (password.matches(
                ".*[0-9].*"
        )) {

            score += 10;
        }

        // Special character
        if (password.matches(
                ".*[^a-zA-Z0-9].*"
        )) {

            score += 10;
        }

        // Maximum score
        if (score > 100) {

            score = 100;
        }

        // Update progress bar

        strengthBar.setValue(score);

        strengthBar.setString(
                score + "%"
        );

        // ------------------------------
        // Strength classification
        // ------------------------------

        if (score < 40) {

            strengthLabel.setText(
                    "Strength: Weak"
            );

        } else if (score < 70) {

            strengthLabel.setText(
                    "Strength: Medium"
            );

        } else {

            strengthLabel.setText(
                    "Strength: Strong"
            );
        }
    }

    // ==============================
    // COPY PASSWORD
    // ==============================

    private void copyPassword() {

        // Get password from JPasswordField
        String password =
                new String(
                        passwordField.getPassword()
                );

        // Check password

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Generate a password first.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        // Copy to clipboard

        StringSelection selection =
                new StringSelection(password);

        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        selection,
                        null
                );

        JOptionPane.showMessageDialog(
                this,
                "Password copied to clipboard!",
                "Copied",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ==============================
    // CLEAR
    // ==============================

    private void clearAll() {

        passwordField.setText("");

        strengthBar.setValue(0);

        strengthBar.setString("0%");

        strengthLabel.setText(
                "Strength: Not Generated"
        );
    }

    // ==============================
    // MAIN METHOD
    // ==============================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            PasswordGeneratorGUI app =
                    new PasswordGeneratorGUI();

            app.setVisible(true);
        });
    }
}

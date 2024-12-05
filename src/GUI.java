import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class GUI extends JFrame {
    private MedlemsOversigt medlemsOversigt;


    public GUI() {
        // Initialize MedlemsOversigt object
        medlemsOversigt = new MedlemsOversigt();

        // Set up the main window
        setTitle("Delfinen Club Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a tabbed pane for different sections
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs
        tabbedPane.addTab("Medlemmer", createMedlemTab());
        tabbedPane.addTab("Trænere", createTrainerTab());
        tabbedPane.addTab("Restance", createCompetitionTab());

        // Add the tabbed pane to the window
        add(tabbedPane, BorderLayout.CENTER);
    }

    // Create Medlem Tab
    private JPanel createMedlemTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // JTextPane for displaying members with improved layout
        JTextPane medlemmerTextPane = new JTextPane();
        medlemmerTextPane.setEditable(false);
        updateMedlemmerTextArea(medlemmerTextPane);

        // Scroll pane for member list
        JScrollPane scrollPane = new JScrollPane(medlemmerTextPane);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel for adding new members
        JPanel addMemberPanel = new JPanel(new GridLayout(9, 2, 10, 10)); // Increased spacing between fields
        addMemberPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        JTextField navnField = new JTextField();
        JTextField cprField = new JTextField(); // CPR field
        JTextField tlfNrField = new JTextField();
        JTextField mailField = new JTextField();

        // Radio buttons for Aktiv/Passiv
        JRadioButton aktivButton = new JRadioButton("Aktiv", true);
        JRadioButton passivButton = new JRadioButton("Passiv");
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(aktivButton);
        statusGroup.add(passivButton);

        // Radio buttons for Motionist/Konkurrence
        JRadioButton motionistButton = new JRadioButton("Motionist", true);
        JRadioButton konkurranceButton = new JRadioButton("Konkurrence Deltager");
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(motionistButton);
        typeGroup.add(konkurranceButton);

        // Add fields to the panel
        addMemberPanel.add(new JLabel("Navn:"));
        addMemberPanel.add(navnField);
        addMemberPanel.add(new JLabel("CPR:"));
        addMemberPanel.add(cprField);
        addMemberPanel.add(new JLabel("Tlf Nr:"));
        addMemberPanel.add(tlfNrField);
        addMemberPanel.add(new JLabel("Mail:"));
        addMemberPanel.add(mailField);
        addMemberPanel.add(new JLabel("Aktiv Status:"));
        addMemberPanel.add(aktivButton);
        addMemberPanel.add(new JLabel(""));
        addMemberPanel.add(passivButton);
        addMemberPanel.add(new JLabel("Medlemstype:"));
        addMemberPanel.add(motionistButton);
        addMemberPanel.add(new JLabel(""));
        addMemberPanel.add(konkurranceButton);

        // Add panel and button to the bottom
        panel.add(addMemberPanel, BorderLayout.NORTH);

        // Add button at the bottom
        JButton addButton = new JButton("Tilføj Medlem");
        addButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String navn = navnField.getText().trim();
                    String cprNumber = cprField.getText().trim(); // Get CPR input
                    String tlfNrInput = tlfNrField.getText().trim();
                    String mail = mailField.getText().trim();

                    if (navn.isEmpty() || cprNumber.isEmpty() || tlfNrInput.isEmpty() || mail.isEmpty()) {
                        JOptionPane.showMessageDialog(GUI.this, "Alle felter skal udfyldes!", "Fejl", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int tlfNr = Integer.parseInt(tlfNrInput);

                    boolean aktiv = aktivButton.isSelected();
                    boolean motionist = motionistButton.isSelected();

                    // Create a new Medlem object with the provided input
                    Medlem newMedlem = new Medlem(
                            navn,
                            new CPR(cprNumber),    // CPR object with proper validation
                            tlfNr,                 // Phone number (int)
                            mail,                  // Email
                            LocalDate.now(),       // Registration date (current date)
                            aktiv,                 // Aktiv/Passiv status
                            motionist,             // Motionist/Konkurrance status
                            medlemsOversigt.getAntalMedlemmere() + 1, // Member ID
                            new Betalinger(),      // Pass a new Betalinger object
                            false                  // Assume no restance for new members
                    );

                    // Add member to the list
                    medlemsOversigt.addMedlemmerToMedlemmerOversigt(newMedlem);

                    // Update display
                    updateMedlemmerTextArea(medlemmerTextPane);

                    // Clear form fields after adding a member
                    navnField.setText("");
                    cprField.setText("");
                    tlfNrField.setText("");
                    mailField.setText("");

                    JOptionPane.showMessageDialog(GUI.this, "Medlem tilføjet!", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GUI.this, "Tlf Nr skal være et gyldigt nummer!", "Fejl", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(addButton, BorderLayout.SOUTH);

        return panel;
    }

    // Helper method to update the text area with formatted member data
    private void updateMedlemmerTextArea(JTextPane medlemmerTextPane) {
        StringBuilder sb = new StringBuilder();

        // Use monospaced font for consistent alignment (like Apple's SF Mono or another monospace font)
        Font font = new Font("Monospaced", Font.PLAIN, 14); // You can use "SF Mono" or "Courier New" as alternatives

        // Create a StyledDocument for rich text formatting
        StyledDocument doc = new DefaultStyledDocument();

        // Define a bold style for the header
        SimpleAttributeSet boldStyle = new SimpleAttributeSet();
        StyleConstants.setBold(boldStyle, true);

        // Header row with consistent spacing for alignment
        try {
            // Apply bold style to the header row
            doc.insertString(doc.getLength(), String.format("%-25s %-20s %-15s %-15s %-20s\n",
                    "Navn", "CPR", "Tlf", "Status", "Type"), boldStyle);

            // Add a line for separation (lighter color for subtle elegance)
            doc.insertString(doc.getLength(), "-------------------------------------------------------------------------------------\n", null);

            // Format the rows with alternating light and dark backgrounds for better readability
            boolean isEvenRow = true;
            for (Medlem medlem : medlemsOversigt.getMedlemmerOversigt()) {
                doc.insertString(doc.getLength(), String.format("%-25s %-20s %-15s %-15s %-20s\n",
                        medlem.navn,
                        medlem.cpr.toString(),
                        medlem.telNr,
                        medlem.getMedlemStatus(),
                        medlem.getMedlemsType()
                ), null);
                isEvenRow = !isEvenRow;
            }

            // Set the rich text content into the JTextPane
            medlemmerTextPane.setText("");
            medlemmerTextPane.setStyledDocument(doc);

            // Set the font for the entire text
            medlemmerTextPane.setFont(font); // Set the modern monospaced font
            medlemmerTextPane.setCaretPosition(0); // Move the cursor to the top of the text area
            medlemmerTextPane.setBackground(Color.WHITE); // White background for text area
            medlemmerTextPane.setForeground(new Color(50, 50, 50)); // Dark text color for contrast

            // Optional: Set some padding within the text area for a more spaced-out feel
            medlemmerTextPane.setMargin(new Insets(10, 10, 10, 10)); // Add padding around the text area
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    // Create Trainer Tab
    private JPanel createTrainerTab() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Trainer Management (Coming Soon)"));
        return panel;
    }

    // Create Competition Tab
    private JPanel createCompetitionTab() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Competition Management (Coming Soon)"));
        return panel;
    }

}
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class GUI extends JFrame
{
    private MedlemsOversigt medlemsOversigt;
    private TraenerOversigt traenerOversigt;
    private Restance restance;


    public GUI()
    {
        // Initialize MedlemsOversigt object
        medlemsOversigt = new MedlemsOversigt();
        traenerOversigt = new TraenerOversigt();
        restance = new Restance();

        // Set up the main window
        setTitle("Delfinen Club Management");
        setSize(1200, 1500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a tabbed pane for different sections
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs
        tabbedPane.addTab("Medlemmer", createMedlemTab());
        tabbedPane.addTab("Trænere", createTrainerTab());
        tabbedPane.addTab("Restance", createRestanceTab());

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
        JPanel addMemberPanel = new JPanel(new GridLayout(10, 2, 10, 10)); // Increased the grid rows by 1 to fit the button
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

        // Add button directly under "Konkurrance Deltager"
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

        // Add the button to the panel below "Konkurrance Deltager"
        addMemberPanel.add(new JLabel("")); // Empty label to fill space
        addMemberPanel.add(addButton);

        // Add the addMemberPanel to the NORTH section of the main panel
        panel.add(addMemberPanel, BorderLayout.NORTH);

        return panel;
    }

    // Helper method to update the text area with formatted member data
    private void updateMedlemmerTextArea(JTextPane medlemmerTextPane)
    {
        StringBuilder sb = new StringBuilder();

        // Use monospaced font for consistent alignment (like Apple's SF Mono or another monospace font)
        Font font = new Font("Monospaced", Font.PLAIN, 14); // You can use "SF Mono" or "Courier New" as alternatives

        // Create a StyledDocument for rich text formatting
        StyledDocument doc = new DefaultStyledDocument();

        // Define a bold style for the header
        SimpleAttributeSet boldStyle = new SimpleAttributeSet();
        StyleConstants.setBold(boldStyle, true);

        // Header row with consistent spacing for alignment
        try
        {
            // Apply bold style to the header row
            doc.insertString(doc.getLength(), String.format("%-25s %-20s %-15s %-15s %-20s\n",
                    "Navn", "CPR", "Tlf", "Status", "Type"), boldStyle);

            // Add a line for separation (lighter color for subtle elegance)
            doc.insertString(doc.getLength(), "-------------------------------------------------------------------------------------\n", null);

            // Format the rows with alternating light and dark backgrounds for better readability
            boolean isEvenRow = true;
            for (Medlem medlem : medlemsOversigt.getMedlemmerOversigt())
            {
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
        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }
    }












    // Create Trainer Tab
    private JPanel createTrainerTab()
    {
        JPanel panel = new JPanel(new BorderLayout());

        // JTextPane for displaying trainers
        JTextPane trainerTextPane = new JTextPane();
        trainerTextPane.setEditable(false);
        updateTrainerTextArea(trainerTextPane);

        // Scroll pane for trainer list
        JScrollPane scrollPane = new JScrollPane(trainerTextPane);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel for adding new trainers with improved layout (similar to Medlem tab)
        JPanel addTrainerPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // Using GridLayout for alignment
        addTrainerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel

        JTextField navnField = new JTextField();
        JTextField telNrField = new JTextField();
        JTextField mailField = new JTextField();
        JTextField disciplinField = new JTextField();

        // Add fields to the panel in a similar way as in the Medlem tab
        addTrainerPanel.add(new JLabel("Navn:"));
        addTrainerPanel.add(navnField);
        addTrainerPanel.add(new JLabel("Telefon Nr:"));
        addTrainerPanel.add(telNrField);
        addTrainerPanel.add(new JLabel("Mail:"));
        addTrainerPanel.add(mailField);
        addTrainerPanel.add(new JLabel("Disciplin:"));
        addTrainerPanel.add(disciplinField);

        // Add button for adding trainer
        JButton addButton = new JButton("Tilføj Træner");
        addButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    String navn = navnField.getText().trim();
                    String telNrInput = telNrField.getText().trim();
                    String mail = mailField.getText().trim();
                    String disciplin = disciplinField.getText().trim();

                    if (navn.isEmpty() || telNrInput.isEmpty() || mail.isEmpty() || disciplin.isEmpty())
                    {
                        JOptionPane.showMessageDialog(GUI.this, "Alle felter skal udfyldes!", "Fejl", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int telNr = Integer.parseInt(telNrInput);

                    // Create a new Traener object with the provided input
                    Traener newTraener = new Traener(
                            navn,
                            new CPR("123456-1234"),    // Use a placeholder CPR
                            telNr,                    // Phone number (int)
                            mail,                     // Email
                            disciplin                 // Disciplin
                    );

                    // Add trainer to the list
                    traenerOversigt.addTraenerToTraenerOversigt(newTraener);

                    // Update display
                    updateTrainerTextArea(trainerTextPane);

                    // Clear form fields after adding a trainer
                    navnField.setText("");
                    telNrField.setText("");
                    mailField.setText("");
                    disciplinField.setText("");

                    JOptionPane.showMessageDialog(GUI.this, "Træner tilføjet!", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(GUI.this, "Telefon nummer skal være et gyldigt nummer!", "Fejl", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add the button to the panel
        addTrainerPanel.add(new JLabel("")); // Empty label to fill space
        addTrainerPanel.add(addButton);

        // Add addTrainerPanel to the NORTH section of the main panel
        panel.add(addTrainerPanel, BorderLayout.NORTH);

        return panel;
    }

    // Helper method to create label and text field pairs for the form
    private JPanel createFieldPanel(String labelText, JTextField textField)
    {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Left-aligned fields
        fieldPanel.add(new JLabel(labelText));
        fieldPanel.add(textField);
        return fieldPanel;
    }

    // Helper method to update the text area with formatted trainer data
    private void updateTrainerTextArea(JTextPane trainerTextPane)
    {
        StringBuilder sb = new StringBuilder();

        // Use monospaced font for consistent alignment (like Apple's SF Mono or another monospace font)
        Font font = new Font("Monospaced", Font.PLAIN, 14);

        // Create a StyledDocument for rich text formatting
        StyledDocument doc = new DefaultStyledDocument();

        // Define a bold style for the header
        SimpleAttributeSet boldStyle = new SimpleAttributeSet();
        StyleConstants.setBold(boldStyle, true);

        // Header row with consistent spacing for alignment
        try
        {
            // Apply bold style to the header row
            doc.insertString(doc.getLength(), String.format("%-20s %-10s %-28s %-15s\n", "Navn", "Tlf", "Email", "Disciplin"), boldStyle);

            // Add a line for separation
            doc.insertString(doc.getLength(), "-------------------------------------------------------------------------------\n", null);

            // Format the rows with alternating light and dark backgrounds for better readability
            for (Traener traener : traenerOversigt.getTraenerListe())
            {
                doc.insertString(doc.getLength(), String.format("%-20s %-10s %-28s %-15s\n",
                        traener.getNavn(),
                        traener.getTelNr(),
                        traener.getMail(),
                        traener.getDisiplin() // Display disciplin
                ), null);
            }

            // Set the rich text content into the JTextPane
            trainerTextPane.setText("");
            trainerTextPane.setStyledDocument(doc);

            // Set the font for the entire text
            trainerTextPane.setFont(font); // Set the modern monospaced font
            trainerTextPane.setCaretPosition(0); // Move the cursor to the top of the text area
            trainerTextPane.setBackground(Color.WHITE); // White background for text area
            trainerTextPane.setForeground(new Color(50, 50, 50)); // Dark text color for contrast

        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }
    }








    private JPanel createRestanceTab() {
        JPanel panel = new JPanel(new BorderLayout());

        // JTextPane for displaying members with restance
        JTextPane restanceTextPane = new JTextPane();
        restanceTextPane.setEditable(false);
        updateRestanceTextArea(restanceTextPane); // Update to show restance list

        // Scroll pane for restance list
        JScrollPane scrollPane = new JScrollPane(restanceTextPane);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel for adding new members to restance (same layout as Medlem tab)
        JPanel addRestancePanel = new JPanel(new GridLayout(10, 2, 10, 10)); // Increased grid rows to match Medlem tab
        addRestancePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
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
        JRadioButton konkurranceButton = new JRadioButton("Konkurrance Deltager");
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(motionistButton);
        typeGroup.add(konkurranceButton);

        // Add fields to the panel (same as Medlem tab)
        addRestancePanel.add(new JLabel("Navn:"));
        addRestancePanel.add(navnField);
        addRestancePanel.add(new JLabel("CPR:"));
        addRestancePanel.add(cprField);
        addRestancePanel.add(new JLabel("Tlf Nr:"));
        addRestancePanel.add(tlfNrField);
        addRestancePanel.add(new JLabel("Mail:"));
        addRestancePanel.add(mailField);
        addRestancePanel.add(new JLabel("Aktiv Status:"));
        addRestancePanel.add(aktivButton);
        addRestancePanel.add(new JLabel(""));
        addRestancePanel.add(passivButton);
        addRestancePanel.add(new JLabel("Medlemstype:"));
        addRestancePanel.add(motionistButton);
        addRestancePanel.add(new JLabel(""));
        addRestancePanel.add(konkurranceButton);

        // Add button directly under "Konkurrance Deltager"
        JButton addButton = new JButton("Tilføj Medlem til Restance");
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

                    // Create a new Medlem object for restance (set to true for restance)
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
                            true                   // Set to true for restance
                    );

                    // Add member to the restance list
                    restance.addMedlemToRestance(newMedlem);

                    // Update display
                    updateRestanceTextArea(restanceTextPane);

                    // Clear form fields after adding a member
                    navnField.setText("");
                    cprField.setText("");
                    tlfNrField.setText("");
                    mailField.setText("");

                    JOptionPane.showMessageDialog(GUI.this, "Medlem tilføjet til Restance!", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GUI.this, "Tlf Nr skal være et gyldigt nummer!", "Fejl", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add the button to the panel below "Konkurrance Deltager"
        addRestancePanel.add(new JLabel("")); // Empty label to fill space
        addRestancePanel.add(addButton);

        // Add the addRestancePanel to the NORTH section of the main panel
        panel.add(addRestancePanel, BorderLayout.NORTH);

        return panel;
    }

    // Helper method to update the text area with formatted member data for restance
    private void updateRestanceTextArea(JTextPane restanceTextPane) {
        StringBuilder sb = new StringBuilder();

        // Use monospaced font for consistent alignment
        Font font = new Font("Monospaced", Font.PLAIN, 14); // You can use "SF Mono" or "Courier New" as alternatives

        // Create a StyledDocument for rich text formatting
        StyledDocument doc = new DefaultStyledDocument();

        // Define a bold style for the header
        SimpleAttributeSet boldStyle = new SimpleAttributeSet();
        StyleConstants.setBold(boldStyle, true);

        // Header row with consistent spacing for alignment, adding "Skylder"
        try {
            // Apply bold style to the header row
            doc.insertString(doc.getLength(), String.format("%-25s %-20s %-15s %-15s %-20s %-10s\n",
                    "Navn", "CPR", "Tlf", "Status", "Type", "Skylder"), boldStyle);

            // Add a line for separation (lighter color for subtle elegance)
            doc.insertString(doc.getLength(), "---------------------------------------------------------------------------------------------------------------\n", null);

            // Format the rows with alternating light and dark backgrounds for better readability
            boolean isEvenRow = true;

            // Create an instance of Betalinger to calculate the amount owed
            Betalinger betalinger = new Betalinger();

            // Iterate through all members and filter those with restance
            boolean noMembersInRestance = true;  // Flag to handle case when there are no members with restance
            for (int i = 0; i < medlemsOversigt.getMedlemmerOversigt().size(); i++) {
                Medlem medlem = medlemsOversigt.getMedlemmerOversigt().get(i); // Access each member by index
                if (medlem.getRestance()) {  // Check if member is in restance
                    noMembersInRestance = false;

                    // Calculate the amount owed for the member
                    int skylder = betalinger.udregnRestance(medlem);

                    // Format member data, including "Skylder" value
                    doc.insertString(doc.getLength(), String.format("%-25s %-20s %-15s %-15s %-20s %-10d\n",
                            medlem.navn,
                            medlem.cpr.toString(),
                            medlem.telNr,
                            medlem.getMedlemStatus(),
                            medlem.getMedlemsType(),
                            skylder
                    ), null);

                    isEvenRow = !isEvenRow;
                }
            }

            // If there are no members in restance, show a message
            if (noMembersInRestance) {
                doc.insertString(doc.getLength(), "No members in restance\n", null);
            }

            // Set the rich text content into the JTextPane
            restanceTextPane.setText("");
            restanceTextPane.setStyledDocument(doc);

            // Set the font for the entire text
            restanceTextPane.setFont(font); // Set the modern monospaced font
            restanceTextPane.setCaretPosition(0); // Move the cursor to the top of the text area
            restanceTextPane.setBackground(Color.WHITE); // White background for text area
            restanceTextPane.setForeground(new Color(50, 50, 50)); // Dark text color for contrast

            // Optional: Set some padding within the text area for a more spaced-out feel
            restanceTextPane.setMargin(new Insets(10, 10, 10, 10)); // Add padding around the text area
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    }
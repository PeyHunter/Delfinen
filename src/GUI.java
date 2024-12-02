//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.time.LocalDate;
//
//public class GUI extends JFrame {
//    private MedlemsOversigt medlemsOversigt;
//
//    public GUI() {
//        // Initialize MedlemsOversigt object
//        medlemsOversigt = new MedlemsOversigt();
//
//        // Set up the main window
//        setTitle("Delfinen Club Management");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Create a tabbed pane for different sections
//        JTabbedPane tabbedPane = new JTabbedPane();
//
//        // Add tabs
//        tabbedPane.addTab("Medlemmer", createMedlemTab());
//        tabbedPane.addTab("Trænere", createTrainerTab());
//        tabbedPane.addTab("Konkurrencer", createCompetitionTab());
//
//        // Add the tabbed pane to the window
//        add(tabbedPane, BorderLayout.CENTER);
//    }
//
//    // Create Medlem Tab
//    private JPanel createMedlemTab() {
//        JPanel panel = new JPanel(new BorderLayout());
//
//        // Text area to display members with improved layout
//        JTextArea medlemmerTextArea = new JTextArea();
//        medlemmerTextArea.setEditable(false);
//        medlemmerTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Use monospaced font for alignment
//        updateMedlemmerTextArea(medlemmerTextArea);
//
//        // Scroll pane for member list
//        JScrollPane scrollPane = new JScrollPane(medlemmerTextArea);
//        panel.add(scrollPane, BorderLayout.CENTER);
//
//        // Panel for adding new members
//        JPanel addMemberPanel = new JPanel(new GridLayout(9, 2, 5, 5));
//        JTextField navnField = new JTextField();
//        JTextField cprField = new JTextField(); // New CPR field
//        JTextField tlfNrField = new JTextField();
//        JTextField mailField = new JTextField();
//
//        // Radio buttons for Aktiv/Passiv
//        JRadioButton aktivButton = new JRadioButton("Aktiv", true);
//        JRadioButton passivButton = new JRadioButton("Passiv");
//        ButtonGroup statusGroup = new ButtonGroup();
//        statusGroup.add(aktivButton);
//        statusGroup.add(passivButton);
//
//        // Radio buttons for Motionist/Konkurrence
//        JRadioButton motionistButton = new JRadioButton("Motionist", true);
//        JRadioButton konkurranceButton = new JRadioButton("Konkurrence Deltager");
//        ButtonGroup typeGroup = new ButtonGroup();
//        typeGroup.add(motionistButton);
//        typeGroup.add(konkurranceButton);
//
//        // Add fields to the panel
//        addMemberPanel.add(new JLabel("Navn:"));
//        addMemberPanel.add(navnField);
//        addMemberPanel.add(new JLabel("CPR:"));
//        addMemberPanel.add(cprField);
//        addMemberPanel.add(new JLabel("Tlf Nr:"));
//        addMemberPanel.add(tlfNrField);
//        addMemberPanel.add(new JLabel("Mail:"));
//        addMemberPanel.add(mailField);
//        addMemberPanel.add(new JLabel("Aktiv Status:"));
//        addMemberPanel.add(aktivButton);
//        addMemberPanel.add(new JLabel(""));
//        addMemberPanel.add(passivButton);
//        addMemberPanel.add(new JLabel("Medlemstype:"));
//        addMemberPanel.add(motionistButton);
//        addMemberPanel.add(new JLabel(""));
//        addMemberPanel.add(konkurranceButton);
//
//        // Add panel and button to the bottom
//        panel.add(addMemberPanel, BorderLayout.NORTH);
//
//        // Add button at the bottom
//        JButton addButton = new JButton("Tilføj Medlem");
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String navn = navnField.getText();
//                String cprNumber = cprField.getText(); // Get CPR input
//                int tlfNr = Integer.parseInt(tlfNrField.getText());
//                String mail = mailField.getText();
//                boolean aktiv = aktivButton.isSelected();
//                boolean motionist = motionistButton.isSelected();
//
//                // Add new member with provided CPR
//                Medlem newMedlem = new Medlem(
//                        navn,
//                        new CPR(cprNumber), // Assuming CPR constructor can handle a string input
//                        tlfNr,
//                        mail,
//                        LocalDate.now(),
//                        aktiv,
//                        motionist,
//                        medlemsOversigt.getAntalMedlemmere() + 1
//                );
//
//                // Add member to the list
//                medlemsOversigt.addMedlemmerToMedlemmerOversigt(newMedlem);
//
//                // Update display
//                updateMedlemmerTextArea(medlemmerTextArea);
//
//                // Clear form fields after adding a member
//                navnField.setText("");
//                cprField.setText("");
//                tlfNrField.setText("");
//                mailField.setText("");
//            }
//        });
//        panel.add(addButton, BorderLayout.SOUTH);
//
//        return panel;
//    }
//
//    // Helper method to update the text area with formatted member data
//    private void updateMedlemmerTextArea(JTextArea medlemmerTextArea) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("%-20s %-15s %-5s %-10s %-10s\n", "Navn", "CPR", "Tlf", "Status", "Type"));
//        sb.append("--------------------------------------------------------------\n");
//        for (Medlem medlem : medlemsOversigt.getMedlemmerOversigt()) {
//            sb.append(String.format("%-20s %-15s %-5s %-10s %-10s\n",
//                    medlem.navn,
//                    medlem.cpr.toString(),
//                    medlem.telNr,
//                    medlem.getMedlemStatus(),
//                    medlem.getMedlemsType()
//            ));
//        }
//        medlemmerTextArea.setText(sb.toString());
//    }
//
//    // Create Trainer Tab
//    private JPanel createTrainerTab() {
//        JPanel panel = new JPanel();
//        panel.add(new JLabel("Trainer Management (Coming Soon)"));
//        return panel;
//    }
//
//    // Create Competition Tab
//    private JPanel createCompetitionTab() {
//        JPanel panel = new JPanel();
//        panel.add(new JLabel("Competition Management (Coming Soon)"));
//        return panel;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                GUI gui = new GUI();
//                gui.setVisible(true);
//            }
//        });
//    }
//}
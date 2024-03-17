import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BankAdminPanel extends JFrame implements ActionListener {
    JButton viewTransactions, manageCustomers, manageAccounts, notification, logout;
    JPanel detailsPanel;
    private JTextField searchField;
    private JButton searchButton;

    BankAdminPanel() {
        setTitle("Bank Admin Panel");
        setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/bank logo.png"));
        setIconImage(icon.getImage());

        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(new Color(235, 8, 83));
        headingPanel.setPreferredSize(new Dimension(800, 100));
        headingPanel.setLayout(new BorderLayout());

        JLabel headingLabel = new JLabel("Admin Panel");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.add(headingLabel, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 1, 0, 10));
        leftPanel.setBackground(new Color(40, 39, 39));

        ImageIcon logoIcon = new ImageIcon("Icons/bankIcon.png");
        Image imgSize = logoIcon.getImage().getScaledInstance(140, 100, Image.SCALE_DEFAULT);
        ImageIcon logoIcon2 = new ImageIcon(imgSize);
        JLabel logoLabel = new JLabel(logoIcon2);
        headingPanel.add(logoLabel, BorderLayout.WEST);

        viewTransactions = createButton("View Transactions");
        manageCustomers = createButton("Manage Customers");
        manageAccounts = createButton("Manage Accounts");
        notification = createButton("Notification");
        logout = createButton("Logout");

        leftPanel.add(viewTransactions);
        leftPanel.add(manageCustomers);
        leftPanel.add(manageAccounts);
        leftPanel.add(notification);
        leftPanel.add(logout);

        headingPanel.add(leftPanel, BorderLayout.SOUTH);
        add(headingPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);

        detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.setBackground(new Color(240, 240, 240));

        // Title Label
        JLabel titleLabel = new JLabel("Details Panel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(80, 80, 80));
        titleLabel.setBounds(350, 100, 150, 30);
        detailsPanel.add(titleLabel);

        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBackground(new Color(255, 255, 255));
        searchField.setBounds(200, 200, 350, 30);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        searchButton = new JButton("Search User");
        searchButton.addActionListener(this);
        searchButton.setBackground(new Color(235, 8, 83));
        searchButton.setForeground(Color.white);
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setBounds(550, 200, 150, 30);
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(205, 8, 74));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(235, 8, 83));
            }
        });

        detailsPanel.add(searchField, getConstraints(0, 1, 1, 1));
        detailsPanel.add(searchButton, getConstraints(1, 1, 1, 1));

        add(detailsPanel, BorderLayout.CENTER);
        setSize(1200, 700);
        setVisible(true);
        setLocation(50, 20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(235, 8, 83));
        button.setForeground(Color.white);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(205, 8, 74));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(235, 8, 83));
            }
        });

        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == viewTransactions) {
            setVisible(false);
            new ViewTransactions().setVisible(true);
        } else if (ae.getSource() == manageCustomers) {
            setVisible(false);
            new ManageCustomersPanel().setVisible(true);
        } else if (ae.getSource() == manageAccounts) {
            setVisible(false);
            new ManageAccountsPanel().setVisible(true);
        } else if (ae.getSource() == notification) {
            setVisible(false);
            new NotificationPage().setVisible(true);
        } else if (ae.getSource() == logout) {
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to logout?");
            if (choice == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Login().setVisible(true);
            }
        } else if (ae.getSource() == searchButton) {
            String cardNumber = searchField.getText();
            if (!cardNumber.isEmpty()) {
                fetchUserData(cardNumber);
            } else {
                updateDetailsPanel("Please enter a card number.");
            }
        }
    }

    private void fetchUserData(String cardNumber) {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM signupthree WHERE Card_Number = '" + cardNumber + "'";
            ResultSet rs = conn.a.executeQuery(query);

            if (rs.next()) {
                String formNo = rs.getString("formNo");
                String accountType = rs.getString("AccountType");

                // First set of details
                JLabel titleLabel = new JLabel("User Details");
                titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
                titleLabel.setBounds(420, 100, 150, 30);

                JLabel cardLabel = new JLabel("Card Number: " + cardNumber);
                cardLabel.setFont(new Font("Arial", Font.BOLD, 14));
                cardLabel.setBounds(200, 200, 250, 30);
                JLabel formNoLabel = new JLabel("Form Number: " + formNo);
                formNoLabel.setFont(new Font("Arial", Font.BOLD, 14));
                formNoLabel.setBounds(600, 200, 350, 30);
                JLabel accountTypeLabel = new JLabel("Account Type: " + accountType);
                accountTypeLabel.setFont(new Font("Arial", Font.BOLD, 14));
                accountTypeLabel.setBounds(200, 280, 350, 30);

                detailsPanel.removeAll();

                detailsPanel.add(titleLabel, getConstraints(0, 0, 2, 1));
                detailsPanel.add(formNoLabel, getConstraints(0, 1, 2, 1));

                // Query for additional details
                String query1 = "SELECT * FROM signup WHERE formNo = '" + formNo + "'";
                ResultSet rs1 = conn.a.executeQuery(query1);

                if (rs1.next()) {
                    // Additional details
                    String name = rs1.getString("name");
                    String fatherName = rs1.getString("Father_Name");
                    String genderUser = rs1.getString("gender");
                    String DobUser = rs1.getString("DoB");
                    String EmailUser = rs1.getString("email");

                    JLabel nameLabel = new JLabel("Name: " + name);
                    nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    nameLabel.setBounds(200, 240, 350, 30);
                    JLabel emailLabel = new JLabel("Email ID: " + EmailUser);
                    emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    emailLabel.setBounds(600, 240, 350, 30);
                    JLabel genderLabel = new JLabel("Gender: " + genderUser);
                    genderLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    genderLabel.setBounds(600, 280, 350, 30);
                    JLabel DoBLabel = new JLabel("Date of Bath: " + DobUser);
                    DoBLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    DoBLabel.setBounds(200, 320, 350, 30);
                    JLabel fatherNameLabel = new JLabel("Father's Name: " + fatherName);
                    fatherNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    fatherNameLabel.setBounds(600, 320, 350, 30);

                    detailsPanel.add(nameLabel, getConstraints(0, 2, 2, 1));
                    detailsPanel.add(fatherNameLabel, getConstraints(0, 3, 2, 1));
                    detailsPanel.add(genderLabel, getConstraints(0, 4, 2, 1));
                    detailsPanel.add(DoBLabel, getConstraints(0, 5, 2, 1));
                    detailsPanel.add(emailLabel, getConstraints(0, 6, 2, 1));
                }
                detailsPanel.add(cardLabel, getConstraints(0, 7, 2, 1));
                detailsPanel.add(accountTypeLabel, getConstraints(0, 8, 2, 1));
                detailsPanel.revalidate();
                detailsPanel.repaint();
            } else {
                updateDetailsPanel("User not found for Card Number: " + cardNumber);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            updateDetailsPanel("Error fetching user data");
        }
    }

    private void updateDetailsPanel(String message) {
        detailsPanel.removeAll();
        JLabel detailsLabel = new JLabel(message);
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsLabel.setBounds(300, 150, 700, 30);
        detailsPanel.add(detailsLabel);
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }

    public static void main(String[] args) {
        new BankAdminPanel();
    }
}

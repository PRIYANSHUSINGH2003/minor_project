import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManageAccountsPanel extends JFrame implements ActionListener {
    JButton viewTransactions, manageCustomers, Details_Panel, manageAccounts, notification, logout;
    JPanel detailsPanel;
    private JTextField searchField;
    private JButton searchButton;

    ManageAccountsPanel() {
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

        ImageIcon logoIcon = new ImageIcon("Icons/bank logo.png");
        Image imgSize = logoIcon.getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT);
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
        JLabel titleLabel = new JLabel("Manage Accounts Panel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(80, 80, 80));
        titleLabel.setBounds(250, 100, 450, 30);
        detailsPanel.add(titleLabel);

        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBackground(new Color(255, 255, 255));
        searchField.setBounds(230, 200, 320, 30);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        searchButton = new JButton("Search Account");
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

        // Additional components for managing accounts can be added here

        add(detailsPanel, BorderLayout.CENTER);
        setSize(1200, 700);
        setVisible(true);
        setLocation(50, 20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        // Handle actions based on button clicks
        if (ae.getSource() == viewTransactions) {
            setVisible(false);
            new ViewTransactions().setVisible(true);
        } else if (ae.getSource() == manageCustomers) {
            setVisible(false);
            new ManageCustomersPanel().setVisible(true);
        } else if (ae.getSource() == manageAccounts) {
            setVisible(false);
            new ManageAccountsPanel().setVisible(true);
            manageAccountsFunction();
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
                manageAccountsFunction();
            } else {
                updateDetailsPanel("Please enter a card number.");
            }
        } else if (ae.getSource() == Details_Panel) {
            setVisible(false);
            new BankAdminPanel().setVisible(true);
        }
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

    private void manageAccountsFunction() {
        try {
            String cardNumber = searchField.getText();
            Conn conn = new Conn();
            String query = "SELECT * FROM signupthree WHERE Card_Number = '" + cardNumber + "'";
            ResultSet rs = conn.a.executeQuery(query);
    
            if (rs.next()) {
                // Extract and display account details
                String formNo = rs.getString("formNo");
                String accountType = rs.getString("AccountType");
    
                // Additional query to fetch more details from another table (assuming formNo is
                // the common field)
                String query1 = "SELECT * FROM signup WHERE formNo = '" + formNo + "'";
                ResultSet rs1 = conn.a.executeQuery(query1);
    
                if (rs1.next()) {
                    // Display the retrieved data in the details panel
                    String accountHolder = rs1.getString("name"); // Corrected rs1 here
                    updateDetailsPanels(cardNumber, accountHolder, accountType);
                    Details_Panel = new JButton("Details Panel ");
                    Details_Panel.addActionListener(this);
                    Details_Panel.setBackground(new Color(235, 8, 83));
                    Details_Panel.setForeground(Color.white);
                    Details_Panel.setFont(new Font("Arial", Font.BOLD, 14));
                    Details_Panel.setBounds(750, 500, 250, 30);
                    detailsPanel.add(Details_Panel);
    
                    detailsPanel.revalidate();
                    detailsPanel.repaint();
                } else {
                    updateDetailsPanel("No account data found.");
                }
    
                rs1.close();
            } else {
                updateDetailsPanel("No account data found.");
            }
    
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            updateDetailsPanel("Error fetching account data");
        }
    }
    
    private void updateDetailsPanels(String cardNumber, String accountHolder, String accountType) {
        detailsPanel.removeAll();

        JLabel data1Label = new JLabel("Card Number: ");
        data1Label.setFont(new Font("Arial", Font.BOLD, 16));
        data1Label.setForeground(new Color(80, 80, 80));
        data1Label.setBounds(300, 150, 700, 30);
        detailsPanel.add(data1Label);
        JLabel datasql1 = new JLabel(cardNumber);
        datasql1.setFont(new Font("Arial", Font.PLAIN, 14));
        datasql1.setForeground(new Color(80, 80, 80));
        datasql1.setBounds(450, 150, 700, 30);
        detailsPanel.add(datasql1);
        JLabel data2Label = new JLabel("Account Number: ");
        data2Label.setFont(new Font("Arial", Font.BOLD, 16));
        data2Label.setForeground(new Color(80, 80, 80));
        data2Label.setBounds(300, 180, 700, 30);
        detailsPanel.add(data2Label);
        JLabel datasql2 = new JLabel("ACC" + (int) (Math.random() * 1000000));
        datasql2.setFont(new Font("Arial", Font.PLAIN, 14));
        datasql2.setForeground(new Color(80, 80, 80));
        datasql2.setBounds(450, 180, 700, 30);
        detailsPanel.add(datasql2);
        JLabel data3Label = new JLabel("Account Holder: ");
        data3Label.setFont(new Font("Arial", Font.BOLD, 16));
        data3Label.setForeground(new Color(80, 80, 80));
        data3Label.setBounds(300, 210, 700, 30);
        detailsPanel.add(data3Label);
        JLabel datasql3 = new JLabel(accountHolder);
        datasql3.setFont(new Font("Arial", Font.PLAIN, 14));
        datasql3.setForeground(new Color(80, 80, 80));
        datasql3.setBounds(450, 210, 700, 30);
        detailsPanel.add(datasql3);
        JLabel data4Label = new JLabel("Account Type: ");
        data4Label.setFont(new Font("Arial", Font.BOLD, 16));
        data4Label.setForeground(new Color(80, 80, 80));
        data4Label.setBounds(300, 240, 700, 30);
        detailsPanel.add(data4Label);
        JLabel datasql4 = new JLabel(accountType);
        datasql4.setFont(new Font("Arial", Font.PLAIN, 14));
        datasql4.setForeground(new Color(80, 80, 80));
        datasql4.setBounds(450, 240, 700, 30);
        detailsPanel.add(datasql4);

        JLabel headingLabel = new JLabel("Account Details");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 25));
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        headingLabel.setForeground(new Color(80, 80, 80));
        headingLabel.setBounds(100, 50, 700, 30);
        detailsPanel.add(headingLabel);

        detailsPanel.revalidate();
        detailsPanel.repaint();
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
        new ManageAccountsPanel();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManageCustomersPanel extends JFrame implements ActionListener {
    JButton viewTransactions, detailsPanelButton, manageCustomers, manageAccounts, notification, logout;
    JPanel detailsPanel;
    private JTextField searchField;
    private JButton searchButton;

    ManageCustomersPanel() {
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
        JLabel titleLabel = new JLabel("Manage Customers Panel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(80, 80, 80));
        titleLabel.setBounds(250, 100, 450, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailsPanel.add(titleLabel, BorderLayout.NORTH);

        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBounds(230, 200, 320, 30);
        searchField.setBackground(new Color(255, 255, 255));
        searchField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
        BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        detailsPanel.add(searchField);

        searchButton = new JButton("Search User");
        searchButton.addActionListener(this);
        searchButton.setBackground(new Color(235, 8, 83));
        searchButton.setBounds(550, 200, 150, 30);
        searchButton.setForeground(Color.white);
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(205, 8, 74));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(235, 8, 83));
            }
        });

        detailsPanel.add(searchButton);

        detailsPanelButton = new JButton("Details Panel");
        detailsPanelButton.addActionListener(this);
        detailsPanelButton.setBackground(new Color(235, 8, 83));
        detailsPanelButton.setBounds(750, 500, 250, 30);
        detailsPanelButton.setForeground(Color.white);
        detailsPanelButton.setFont(new Font("Arial", Font.BOLD, 14));
        detailsPanelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                detailsPanelButton.setBackground(new Color(205, 8, 74));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                detailsPanelButton.setBackground(new Color(235, 8, 83));
            }
        });

        detailsPanel.add(detailsPanelButton, BorderLayout.SOUTH);

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
            manageCustomersFunction();
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
                manageCustomersFunction();
            } else {
                updateDetailsPanel("Please enter a card number.");
            }
        } else if (ae.getSource() == detailsPanelButton) {
            setVisible(false);
            new BankAdminPanel().setVisible(true);
        }
    }

    private void manageCustomersFunction() {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM signup " +
                    "LEFT JOIN signuptwo ON signup.formNo = signuptwo.formNo " +
                    "LEFT JOIN signupthree ON signup.formNo = signupthree.formNo " +
                    "WHERE signup.formNo = signuptwo.formNo AND signup.formNo = signupthree.formNo";
            ResultSet rs = conn.a.executeQuery(query);

            StringBuilder customerData = new StringBuilder();

            while (rs.next()) {
                String formNo = rs.getString("formNo");
                String name = rs.getString("name");
                String fatherName = rs.getString("father_name");
                String dob = rs.getString("DoB");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                // Add more fields as needed

                // Append the retrieved data to the StringBuilder
                customerData.append("Form Number: ").append(formNo)
                        .append("\nName: ").append(name)
                        .append("\nFather's Name: ").append(fatherName)
                        .append("\nDate of Birth: ").append(dob)
                        .append("\nGender: ").append(gender)
                        .append("\nEmail: ").append(email)
                        .append("\n--------------------------------------------------------\n\n");
            }

            if (customerData.length() > 0) {
                // Display the retrieved data in the details panel
                updateDetailsPanels(customerData.toString());
            } else {
                updateDetailsPanel("No matching customer data found.");
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            updateDetailsPanel("Error fetching customer data: " + e.getMessage());
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
    private void updateDetailsPanels(String message) {
        detailsPanel.removeAll();
        JLabel detailsLabel = new JLabel(message);
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel Title = new JLabel("All Record");
        Title.setBounds(300, 30, 300, 30);
        Title.setFont(new Font("Arial", Font.BOLD, 24));
        detailsPanel.add(Title);
        JTextArea detailsTextArea = new JTextArea(message);
        detailsTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        scrollPane.setBounds(40, 60, 950, 500);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        detailsPanel.add(scrollPane, BorderLayout.CENTER);
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    public static void main(String[] args) {
        new ManageCustomersPanel();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NotificationPage extends JFrame implements ActionListener {
    JButton viewTransactions, manageCustomers, manageAccounts, notification, logout;
    JButton noticeButton, updateButton, offerButton;
    JPanel detailsPanel, noticePanel;

    public NotificationPage() {
        setTitle("Bank Admin Panel");
        setLayout(new BorderLayout());

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

        noticeButton = createButton("Notice");
        updateButton = createButton("Update");
        offerButton = createButton("Offer");
        noticeButton.setBounds(100, 100, 400, 150);
        noticeButton.setFont(new Font("Helvetica", Font.BOLD, 25));
        updateButton.setBounds(510, 100, 400, 150);
        updateButton.setFont(new Font("Helvetica", Font.BOLD, 25));
        offerButton.setBounds(100, 260, 400, 150);
        offerButton.setFont(new Font("Helvetica", Font.BOLD, 25));

        detailsPanel.add(noticeButton);
        detailsPanel.add(updateButton);
        detailsPanel.add(offerButton);

        noticePanel = new JPanel();
        noticePanel.setLayout(null);
        noticePanel.setBackground(new Color(240, 240, 240));

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

    private void showNoticePanel() {
        JFrame noticeFrame = new JFrame("Notice");
        noticeFrame.setLayout(null);
        noticeFrame.setSize(900, 500);
        noticeFrame.getContentPane().setBackground(new Color(238, 255, 230));
    
        JLabel titleLabel = new JLabel("Notice");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 33, 33));
        titleLabel.setBounds(350, 20, 150, 30);
        noticeFrame.add(titleLabel);
    
        String noticeText = "Date: [Date]\n\n" +
                "To,\n[Recipient's Name/Department]\n[Organization/Institution Name]\n" +
                "[Address Line 1]\n[Address Line 2]\n[City, State, Zip Code]\n\n" +
                "Subject: [Subject of the Notice]\n\n" +
                "Dear [Recipient's Name],\n\n" +
                "This is to inform you that [provide details or reason for the notice]. " +
                "The purpose of this notice is to [explain the nature of the notice, e.g., convey information, provide instructions, announce an event, etc.].\n\n" +
                "[Include additional details as needed]\n\n" +
                "Please take note of the following:\n\n" +
                "1. [Point 1]\n2. [Point 2]\n3. [Point 3]\n   ...\n\n" +
                "[Conclude the notice with any additional information or contact details]\n\n" +
                "For any queries or clarifications, please feel free to contact [Contact Person] at [Contact Information].\n\n" +
                "Thank you for your attention and cooperation.\n\n" +
                "Sincerely,\n\n[Your Name]\n[Your Position]\n[Organization]\n";
    
        JTextArea noticeTextArea = new JTextArea(noticeText);
        noticeTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        noticeTextArea.setEditable(false);
        noticeTextArea.setBounds(100, 60, 700, 300);
        noticeTextArea.setLineWrap(true);
        noticeTextArea.setWrapStyleWord(true);
    
        JScrollPane noticeScrollPane = new JScrollPane(noticeTextArea);
        noticeScrollPane.setBounds(100, 60, 700, 300);
        noticeScrollPane.setBorder(BorderFactory.createEmptyBorder());
        noticeScrollPane.setBackground(new Color(255, 255, 255));
    
        noticeFrame.add(noticeScrollPane);
    
        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(33, 33, 33));
        okButton.setForeground(Color.white);
        okButton.setFont(new Font("Arial", Font.BOLD, 14));
        okButton.addActionListener(e -> noticeFrame.setVisible(false));
        okButton.setBounds(360, 400, 100, 30);
    
        noticeFrame.add(okButton);
    
        noticeFrame.setLocationRelativeTo(this);
        noticeFrame.setVisible(true);
    }    

    private static void showUpdateInfoDialog() {
        String updateInfo = "Version 1.1.0\nRelease Date: 2023-01-01\n\n"
                + "This update includes:\n- Bug fixes\n- Performance improvements\n- New features";

        JOptionPane.showMessageDialog(null, updateInfo, "Update Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showOfferDialog() {
        String offerMessage = "Limited Time Offer!\n\n" +
                "Enjoy special benefits on our services!\n" +
                "Offer valid until [Expiry Date].\n\n" +
                "Terms and conditions apply.";

        JOptionPane.showMessageDialog(this, offerMessage, "Special Offer", JOptionPane.INFORMATION_MESSAGE);
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
        } else if (ae.getSource() == noticeButton) {
            showNoticePanel();
        } else if (ae.getSource() == updateButton){
            showUpdateInfoDialog();
        } else if (ae.getSource() == offerButton){
            showOfferDialog();
        } else if (ae.getSource() == logout) {
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to logout?");
            if (choice == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Login().setVisible(true);
            }
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

    public static void main(String[] args) {
        new NotificationPage();
    }
}

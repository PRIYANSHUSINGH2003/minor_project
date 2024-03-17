import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {

    JButton exit, b2;
    JLabel text;

    MiniStatement(String pinnumber) {

        super("Mini Statement");
        setLayout(null);
        getContentPane().setBackground(new Color(235, 220, 220));
        setSize(400, 600);
        setUndecorated(true);
        setLocation(20, 20);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/bank logo.png"));
        setIconImage(icon.getImage());
        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(61, 90, 128));
        topPanel.setBounds(0, 0, 400, 60);
        add(topPanel);

        text = new JLabel();
        text.setBounds(20, 140, 400, 200);
        add(text);

        JLabel bank_Name = new JLabel("Indian Bank");
        bank_Name.setBounds(150, 20, 300, 60);
        bank_Name.setFont(new Font("Arial", Font.BOLD, 20));
        bank_Name.setForeground(Color.WHITE);
        topPanel.add(bank_Name);

        JLabel card_number = new JLabel();
        card_number.setBounds(20, 80, 300, 20);
        add(card_number);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.a.executeQuery("select * from login where PIN_Number = '" + pinnumber + "'");
            while (rs.next()) {
                card_number.setText("Card Number:  " + rs.getString("Card_Number").substring(0, 4) + "XXXXXXXX"
                        + rs.getString("Card_Number").substring(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            int balance = 0;
            Conn conn2 = new Conn();
            ResultSet rs = conn2.a.executeQuery("SELECT * FROM bank where PIN_Number = '" + pinnumber + "'");
            while (rs.next()) {
                text.setText(text.getText() + "<html>" + rs.getString("Date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                        + rs.getString("Type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                        + rs.getString("Amount") + "<br><br><html>");
                if (rs.getString("Type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("Amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("Amount"));
                }
            }
            JLabel l4 = new JLabel("Your total Balance is Rs " + balance);
            l4.setBounds(20, 400, 300, 20);
            add(l4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        exit = new JButton("Exit");
        exit.setBounds(20, 500, 100, 25);
        exit.addActionListener(this);
        add(exit);

    }

    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new MiniStatement("").setVisible(true);
    }

}
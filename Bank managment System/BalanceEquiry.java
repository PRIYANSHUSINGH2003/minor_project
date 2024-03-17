import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class BalanceEquiry extends JFrame implements ActionListener {

    JButton Back;
    JLabel Total_amount;
    String pinnumber;

    BalanceEquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/bank logo.png"));
        setIconImage(icon.getImage());
        
        ImageIcon Img = new ImageIcon(ClassLoader.getSystemResource("Icons/ATM4.jpg"));
        Image Img2 = Img.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon Img3 = new ImageIcon(Img2);
        JLabel image = new JLabel(Img3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        Back = new JButton("BACK");
        Back.setBounds(400, 370, 150, 30);
        Back.addActionListener(this);
        image.add(Back);

        int balance = 0;
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.a.executeQuery("select * from bank where PIN_Number = '" + pinnumber + "'");
            while (rs.next()) {
                if (rs.getString("Type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("Amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("Amount"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        Total_amount = new JLabel("Your Current Account Balance is Rs " + balance);
        Total_amount.setFont(new Font("System", Font.BOLD, 15));
        Total_amount.setBounds(240, 250, 700, 35);
        image.add(Total_amount);

        setSize(900, 720);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEquiry("").setVisible(true);
    }
}

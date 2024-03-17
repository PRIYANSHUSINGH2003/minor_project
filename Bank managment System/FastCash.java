import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton Rs_100, Rs_500, Rs_1000, Rs_2000, Rs_5000, Rs_10000, back;
    String pinnumber;

    FastCash(String pinnumber) {
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

        JLabel text = new JLabel("SELECT WITHDRAW AMOUNT");
        text.setFont(new Font("System", Font.BOLD, 17));
        text.setBounds(265, 200, 700, 25);
        image.add(text);

        Rs_100 = new JButton("Rs 100");
        Rs_100.setBounds(205, 285, 150, 30);
        Rs_100.addActionListener(this);
        image.add(Rs_100);

        Rs_500 = new JButton("Rs 500");
        Rs_500.setBounds(400, 285, 150, 30);
        Rs_500.addActionListener(this);
        image.add(Rs_500);

        Rs_1000 = new JButton("Rs 1000");
        Rs_1000.setBounds(205, 315, 150, 30);
        Rs_1000.addActionListener(this);
        image.add(Rs_1000);

        Rs_2000 = new JButton("Rs 2000");
        Rs_2000.setBounds(400, 315, 150, 30);
        Rs_2000.addActionListener(this);
        image.add(Rs_2000);

        Rs_5000 = new JButton("Rs 5000");
        Rs_5000.setBounds(205, 345, 150, 30);
        Rs_5000.addActionListener(this);
        image.add(Rs_5000);

        Rs_10000 = new JButton("Rs 10000");
        Rs_10000.setBounds(400, 345, 150, 30);
        Rs_10000.addActionListener(this);
        image.add(Rs_10000);

        back = new JButton("BACK");
        back.setBounds(400, 375, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 720);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton) ae.getSource()).getText().substring(3);
            Conn conn = new Conn();
            try {
                ResultSet rs = conn.a.executeQuery("select * from bank where PIN_Number = '" + pinnumber + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("Type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("Amount"));
                    }else{
                        balance -= Integer.parseInt(rs.getString("Amount"));
                    }
                }
                if(ae.getSource() != back && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                Date date = new Date();
                String query = "insert into bank values('"+pinnumber+"','"+date+"','Withdraw','"+amount+"')";
                conn.a.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs "+ amount + " Debited Successfully");

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String x[]) {
        new FastCash("");
    }
}

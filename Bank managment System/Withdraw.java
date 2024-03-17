import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Withdraw extends JFrame implements ActionListener{
    JButton withdraw,back;
    JTextField  amount;

    String pinnumber;
    Withdraw(String pinnumber){
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/bank logo.png"));
        setIconImage(icon.getImage());
        
        ImageIcon Img = new ImageIcon(ClassLoader.getSystemResource("Icons/ATM4.jpg"));
        Image Img2 = Img.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon Img3 = new ImageIcon(Img2);
        JLabel image = new JLabel(Img3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("ENTER THE AMOUNT YOU WANT TO WITHDRAW");
        text.setFont(new Font("System", Font.BOLD, 12));
        text.setBounds(215, 200, 700, 25);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 15));
        amount.setBounds(215, 240, 300, 25);
        image.add(amount);

        withdraw = new JButton("Withdrow Amount");
        withdraw.setBounds(400, 345, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("BACK");
        back.setBounds(400, 375, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 720);
        setLocation(300, 0);
        // setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == withdraw){
            String Deposit_amount = amount.getText();
            Date date = new Date(); 
            if(Deposit_amount.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
            } else {
                try {
                    Conn conn = new Conn();
                    String query = "insert into bank values ('"+pinnumber+"','"+date+"','Withdraw','"+Deposit_amount+"')";
                    conn.a.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Rs " + Deposit_amount + " Withdraw Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }else if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    public static void main(String x[]){
        new Withdraw("");
    }
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PinChange extends JFrame implements ActionListener {
    JPasswordField text1, text2;
    JButton Change, back;
    JLabel Change_PIN, New_PIN, Re_Enter_PIN;
    String pinnumber;

    PinChange(String pinnumber) {
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

        Change_PIN = new JLabel("CHANGE YOUR PIN");
        Change_PIN.setFont(new Font("System", Font.BOLD, 16));
        Change_PIN.setBounds(265, 200, 700, 25);
        image.add(Change_PIN);

        New_PIN = new JLabel("New PIN:");
        New_PIN.setFont(new Font("System", Font.BOLD, 12));
        New_PIN.setBounds(240, 240, 150, 35);
        image.add(New_PIN);


        Re_Enter_PIN = new JLabel("Re-Enter New PIN:");
        Re_Enter_PIN.setFont(new Font("System", Font.BOLD, 12));
        Re_Enter_PIN.setBounds(240, 270, 200, 35);
        image.add(Re_Enter_PIN);

        text1 = new JPasswordField();
        text1.setFont(new Font("Raleway", Font.BOLD, 15));
        text1.setBounds(350, 240, 180, 25);
        image.add(text1);

        text2 = new JPasswordField();
        text2.setFont(new Font("Raleway", Font.BOLD, 15));
        text2.setBounds(350, 270, 180, 25);
        image.add(text2);

        Change = new JButton("CHANGE");
        Change.setBounds(400, 345, 150, 30);
        Change.addActionListener(this);
        image.add(Change);

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
        try {
            String new_PIN = text1.getText();
            String re_PIN = text2.getText();

            if (!new_PIN.equals(re_PIN)) {
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }

            if (ae.getSource() == Change) {
                if (text1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                }
                if (text2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                }

                Conn conn = new Conn();
                String query1 = "update bank set PIN_Number = '" + re_PIN + "' where PIN_Number = '" +pinnumber + "' ";
                String query2 = "update login set PIN_Number = '" + re_PIN + "' where PIN_Number = '" + pinnumber + "' ";
                String query3 = "update signupthree set PIN_Number = '" + re_PIN + "' where PIN_Number = '" + pinnumber + "' ";

                conn.a.executeUpdate(query1);
                conn.a.executeUpdate(query2);
                conn.a.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(re_PIN).setVisible(true);

            } else if (ae.getSource() == back) {
                new Transactions(pinnumber).setVisible(true);
                setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
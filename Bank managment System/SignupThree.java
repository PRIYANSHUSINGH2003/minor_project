import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton Saving, Fixed, Current, Recurring;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formNo;
    String email;
    public SignupThree(String formNo, String email) {
        this.formNo = formNo;
        this.email = email;

        setLayout(null);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/bank logo.png"));
        setIconImage(icon.getImage());
        
        JLabel Page_3 = new JLabel("Page 3: Account Details");
        Page_3.setFont(new Font("Raleway", Font.BOLD, 20));
        Page_3.setBounds(280, 40, 400, 40);
        add(Page_3);

        JLabel Type = new JLabel("Account Type");
        Type.setFont(new Font("Raleway", Font.BOLD, 20));
        Type.setBounds(100, 140, 200, 30);
        add(Type);

        Saving = new JRadioButton("Saving Account");
        Saving.setFont(new Font("Raleway", Font.BOLD, 16));
        Saving.setBackground(Color.WHITE);
        Saving.setBounds(100, 180, 150, 20);
        add(Saving);

        Fixed = new JRadioButton("Fixed Deposit Account");
        Fixed.setFont(new Font("Raleway", Font.BOLD, 16));
        Fixed.setBackground(Color.WHITE);
        Fixed.setBounds(350, 180, 250, 20);
        add(Fixed);

        Current = new JRadioButton("Current Account");
        Current.setFont(new Font("Raleway", Font.BOLD, 16));
        Current.setBackground(Color.WHITE);
        Current.setBounds(100, 220, 250, 20);
        add(Current);

        Recurring = new JRadioButton("Recurring Deposit Account");
        Recurring.setFont(new Font("Raleway", Font.BOLD, 16));
        Recurring.setBackground(Color.WHITE);
        Recurring.setBounds(350, 220, 250, 20);
        add(Recurring);

        ButtonGroup groupAccount = new ButtonGroup();
        groupAccount.add(Saving);
        groupAccount.add(Fixed);
        groupAccount.add(Current);
        groupAccount.add(Recurring);

        JLabel card = new JLabel("Card Number:");
        card.setFont(new Font("Raleway", Font.BOLD, 22));
        card.setBounds(100, 300, 200, 30);
        add(card);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-7148");
        number.setFont(new Font("Raleway", Font.BOLD, 22));
        number.setBounds(330, 300, 300, 30);
        add(number);

        JLabel Card_Detail = new JLabel("Your 16 Digit Card Number");
        Card_Detail.setFont(new Font("Raleway", Font.BOLD, 9));
        Card_Detail.setBounds(100, 326, 300, 20);
        add(Card_Detail);

        JLabel Pin = new JLabel("Pin Number:");
        Pin.setFont(new Font("Raleway", Font.BOLD, 22));
        Pin.setBounds(100, 350, 200, 30);
        add(Pin);

        JLabel Pin_Detail = new JLabel("Your 4 Digit Pin Number");
        Pin_Detail.setFont(new Font("Raleway", Font.BOLD, 9));
        Pin_Detail.setBounds(100, 376, 300, 20);
        add(Pin_Detail);

        JLabel Pin_number = new JLabel("XXXX");
        Pin_number.setFont(new Font("Raleway", Font.BOLD, 22));
        Pin_number.setBounds(330, 350, 300, 30);
        add(Pin_number);

        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100, 420, 200, 30);
        add(services);

        c1 = new JCheckBox("ATM Card");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c1.setBounds(100, 460, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setBounds(350, 460, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setBounds(100, 500, 200, 30);
        add(c3);

        c4 = new JCheckBox("EMAIL & SMS Alerts");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setBounds(350, 500, 200, 30);
        add(c4);

        c5 = new JCheckBox("E-Statement");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setBounds(100, 540, 200, 30);
        add(c5);

        c6 = new JCheckBox("Cheque Book");
        c6.setBackground(Color.WHITE);
        c6.setFont(new Font("Raleway", Font.BOLD, 16));
        c6.setBounds(350, 540, 200, 30);
        add(c6);

        c7 = new JCheckBox("I Hereby declares thet the above entered details are correct to the best of my knowledge");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBounds(100, 590, 800, 30);
        add(c7);

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250, 630, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(420, 630, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setSize(800, 820);
        setLocation(350, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accountType = null;
            if (Saving.isSelected()) {
                accountType = "Saving Account";
            } else if (Fixed.isSelected()) {
                accountType = "Fixed Deposit Account";
            } else if (Current.isSelected()) {
                accountType = "Current Account";
            } else if (Recurring.isSelected()) {
                accountType = "Recurring Deposit Account";
            }
            Random random = new Random();
            String cardnumber = "" + Math.abs((random.nextLong() % 90000000L) + 50408936000000000L);
            String pinnumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);
            String facility = "";
            if (c1.isSelected()) {
                facility = facility + " ATM Card";
            } else if (c2.isSelected()) {
                facility = facility + " Internet Banking";
            } else if (c3.isSelected()) {
                facility = facility + " Mobile Banking";
            } else if (c4.isSelected()) {
                facility = facility + " EMAIL & SMS Alerts";
            } else if (c5.isSelected()) {
                facility = facility + " E-Statement";
            } else if (c6.isSelected()) {
                facility = facility + " Cheque Book";
            }

            try {
                if (accountType.equals("")) {
                    JOptionPane.showMessageDialog(null, "Account Type is Required");
                } else {
                    Conn conn = new Conn();
                    String query = "insert into signupthree values ('" + formNo + "','" + accountType + "','"
                            + cardnumber + "','" + pinnumber + "','" + facility + "');";
                    String query2 = "insert into login values ('" + formNo + "','" + cardnumber + "','" + pinnumber
                            + "');";
                    conn.a.executeUpdate(query);
                    conn.a.executeUpdate(query2);

                    
                    JOptionPane.showMessageDialog(null, "Card Number: " + cardnumber + "\n PIN Number: " + pinnumber);
                    setVisible(false);
                    new Deposit(pinnumber).setVisible(false);
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String x[]) {
        new SignupThree("","");
    }
}

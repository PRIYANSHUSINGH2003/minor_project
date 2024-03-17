import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton signIN,clear,signUp,adminPanel;
    JTextField carTextField;
    JPasswordField pinTextField;
    Login(){
        setTitle("AUTOMATED TELLER MACHINE");

        setLayout(null);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/bank logo.png"));
        setIconImage(icon.getImage());

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Icons/logo.jpg"));
        Image imgSize = img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(imgSize);
        JLabel label = new JLabel(img3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        adminPanel = new JButton("Admin Panel");
        adminPanel.setBounds(650, 10, 130, 30); // Position the button on the left side
        adminPanel.setBackground(Color.BLACK);
        adminPanel.setForeground(Color.WHITE);
        adminPanel.addActionListener(this);
        add(adminPanel);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD,30));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardno = new JLabel("Card No: ");
        cardno.setFont(new Font("Raleway", Font.BOLD,30));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);

        carTextField = new JTextField();
        carTextField.setBounds(300, 150, 250, 30);
        carTextField.setFont(new Font("Arial", Font.BOLD,20));
        add(carTextField);

        JLabel pinNo = new JLabel("PIN: ");
        pinNo.setFont(new Font("Osward", Font.BOLD,30));
        pinNo.setBounds(120, 220, 250, 30);
        add(pinNo);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 250, 30);
        add(pinTextField);

        signIN = new JButton("SIGN IN");
        signIN.setBounds(300,300,100,30);
        signIN.setBackground(Color.BLACK);       
        signIN.setForeground(Color.WHITE);
        signIN.addActionListener(this);
        add(signIN);

        clear = new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.BLACK);       
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        signUp = new JButton("SIGN UP");
        signUp.setBounds(300,350,230,30);
        signUp.setBackground(Color.BLACK);       
        signUp.setForeground(Color.WHITE);
        signUp.addActionListener(this);
        add(signUp);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 420);
        setVisible(true);
        setLocation(350, 200);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == clear){
            carTextField.setText("");
            pinTextField.setText("");
        }else if(ae.getSource() == signIN){
            Conn conn = new Conn();
            String cardnumber = carTextField.getText();            
            String pinnumber = pinTextField.getText();
            String query = "select * from login where Card_Number = '"+cardnumber+"' and PIN_Number = '"+pinnumber+"'";
            try {
                ResultSet rs = conn.a.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Loading(pinnumber).setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN Number");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }else if(ae.getSource() == signUp){
            setVisible(false);
            new SignupOne().setVisible(true);
        }else if(ae.getSource() == adminPanel){
            String username = JOptionPane.showInputDialog("Enter username:");
            String password = JOptionPane.showInputDialog("Enter password:");
    
            if ("admin".equals(username) && "password".equals(password)) {
                setVisible(false);
                new BankAdminPanel().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        }
    }
    public static void main(String x[]){
        new Login();
    }
}

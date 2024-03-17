import java.awt.*;
import javax.swing.*;
import java.util.*; 
import java.awt.event.*;

public class SignupOne extends JFrame implements ActionListener{
    JTextField nameTextField,fathTextField,DoBTextField,emailTextField,addressTextField,cityTextField,stateTextField,pincodeTextField;
    JButton next;
    JRadioButton male,female,other,married,unmarried;
    long random;
    String collectedEmail;
    public SignupOne(){
        setLayout(null);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Icons/bank logo.png"));
        setIconImage(icon.getImage());

        Random ran = new Random();
        random = Math.abs(ran.nextLong() % 9000L + 1000L);
        JLabel formNO = new JLabel("APPLICATION FORM NO. " + random);
        formNO.setFont(new Font("Raleway", Font.BOLD,38));
        formNO.setBounds(140, 20, 600, 40);
        add(formNO);

        JLabel personalDetails = new JLabel("Page 1: Personal Details");
        personalDetails.setFont(new Font("Raleway", Font.BOLD,22));
        personalDetails.setBounds(290, 80, 400, 30);
        add(personalDetails);

        JLabel name = new JLabel("Name: ");
        name.setFont(new Font("Raleway", Font.BOLD,20));
        name.setBounds(100, 140, 100, 30);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway",Font.BOLD,14));
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);

        JLabel Father = new JLabel("Father: ");
        Father.setFont(new Font("Raleway", Font.BOLD,20));
        Father.setBounds(100, 190, 200, 30);
        add(Father);

        fathTextField = new JTextField();
        fathTextField.setFont(new Font("Raleway",Font.BOLD,14));
        fathTextField.setBounds(300, 190, 400, 30);
        add(fathTextField);

        JLabel DoB = new JLabel("Date of Birth: ");
        DoB.setFont(new Font("Raleway", Font.BOLD,20));
        DoB.setBounds(100, 240, 200, 30);
        add(DoB);

        DoBTextField = new JTextField();
        DoBTextField.setFont(new Font("Raleway",Font.BOLD,14));
        DoBTextField.setBounds(300, 240, 400, 30);
        add(DoBTextField);

        JLabel gender = new JLabel("Gender: ");
        gender.setFont(new Font("Raleway", Font.BOLD,20));
        gender.setBounds(100, 290, 200, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Raleway",Font.BOLD,14));
        male.setBackground(Color.WHITE);
        male.setBounds(300, 290, 60, 30);
        add(male);
        
        female = new JRadioButton("Female");
        female.setFont(new Font("Raleway",Font.BOLD,14));
        female.setBackground(Color.WHITE);
        female.setBounds(450, 290, 100, 30);
        add(female);

        other = new JRadioButton("Other");
        other.setFont(new Font("Raleway",Font.BOLD,14));
        other.setBackground(Color.WHITE);
        other.setBounds(630, 290, 100, 30);
        add(other);

        ButtonGroup genGroup = new ButtonGroup();
        genGroup.add(male);
        genGroup.add(female);
        genGroup.add(other);

        JLabel Email = new JLabel("Email ID: ");
        Email.setFont(new Font("Raleway", Font.BOLD,20));
        Email.setBounds(100, 340, 200, 30);
        add(Email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway",Font.BOLD,14));
        emailTextField.setBounds(300, 340, 400, 30);
        add(emailTextField);

        JLabel marital = new JLabel("Marital Status: ");
        marital.setFont(new Font("Raleway", Font.BOLD,20));
        marital.setBounds(100, 390, 200, 30);
        add(marital);

        married = new JRadioButton("Married");
        married.setFont(new Font("Raleway",Font.BOLD,14));
        married.setBackground(Color.WHITE);
        married.setBounds(300, 390, 100, 30);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setFont(new Font("Raleway",Font.BOLD,14));
        unmarried.setBackground(Color.WHITE);
        unmarried.setBounds(450, 390, 100, 30);
        add(unmarried);

        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);

        JLabel address = new JLabel("Address: ");
        address.setFont(new Font("Raleway", Font.BOLD,20));
        address.setBounds(100, 440, 200, 30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway",Font.BOLD,14));
        addressTextField.setBounds(300, 440, 400, 30);
        add(addressTextField);

        JLabel City = new JLabel("City: ");
        City.setFont(new Font("Raleway", Font.BOLD,20));
        City.setBounds(100, 490, 200, 30);
        add(City);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway",Font.BOLD,14));
        cityTextField.setBounds(300, 490, 400, 30);
        add(cityTextField);

        JLabel state = new JLabel("State: ");
        state.setFont(new Font("Raleway", Font.BOLD,20));
        state.setBounds(100, 540, 200, 30);
        add(state);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Raleway",Font.BOLD,14));
        stateTextField.setBounds(300, 540, 400, 30);
        add(stateTextField);

        JLabel pincode = new JLabel("Pin Code: ");
        pincode.setFont(new Font("Raleway", Font.BOLD,20));
        pincode.setBounds(100, 590, 200, 30);
        add(pincode);

        pincodeTextField = new JTextField();
        pincodeTextField.setFont(new Font("Raleway",Font.BOLD,14));
        pincodeTextField.setBounds(300, 590, 400, 30);
        add(pincodeTextField);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850,800);
        setLocation(350,10);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        String formNO = "" + random;
        String name = nameTextField.getText();
        String father = fathTextField.getText();
        String DoB = DoBTextField.getText();
        String gender = null;
        if(male.isSelected()){
            gender = "Male";
        }else if(female.isSelected()){
            gender = "Female";
        }else if(other.isSelected()){
            gender = "Other";
        }

        String email = emailTextField.getText();
        String marital = null;
        if(married.isSelected()){
            marital = "Married";
        }else if(unmarried.isSelected()){
            marital = "Unmarried";
        }
        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pin = pincodeTextField.getText();

        try {
            if(name.equals("")){
                JOptionPane.showMessageDialog(null, "Name is Required");
            } else {
                Conn c = new Conn();
                String query = "insert into signup values ('"+formNO+"','"+name+"','"+father+"','"+DoB+"','"+gender+"','"+email+"','"+marital+"','"+address+"','"+city+"','"+state+"','"+pin+"');";
                c.a.executeUpdate(query);
                collectedEmail = email;
                setVisible(false);
                new SignupTwo(formNO,collectedEmail).setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String x[]){
        new SignupOne();
    }
}

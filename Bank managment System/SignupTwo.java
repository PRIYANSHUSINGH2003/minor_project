import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {
    JTextField PanTextField, AadharTextField;
    JComboBox religion, CategoryField, IncomeField, QualificationField, OccupationField;
    JButton next;
    JRadioButton seniorNo, seniorYes, exisitingYes, exisitingNo;
    String formNo, collectedEmail;

    public SignupTwo(String formNo, String email) {
        this.formNo = formNo;
        this.collectedEmail = email;

        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel AdditionalDetails = new JLabel("Page 2: Additional Details");
        AdditionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        AdditionalDetails.setBounds(290, 80, 400, 30);
        add(AdditionalDetails);

        JLabel Religion = new JLabel("Religion: ");
        Religion.setFont(new Font("Raleway", Font.BOLD, 20));
        Religion.setBounds(100, 140, 100, 30);
        add(Religion);

        String valReligion[] = { "Hindu", "Muslim", "Sikh", "Christian", "Other" };
        religion = new JComboBox(valReligion);
        religion.setFont(new Font("Raleway", Font.BOLD, 14));
        religion.setBounds(300, 140, 400, 30);
        religion.setBackground(Color.WHITE);
        add(religion);

        JLabel Category = new JLabel("Category: ");
        Category.setFont(new Font("Raleway", Font.BOLD, 20));
        Category.setBounds(100, 190, 200, 30);
        add(Category);

        String valcategory[] = { "General", "OBC", "SC", "ST", "Other" };
        CategoryField = new JComboBox(valcategory);
        CategoryField.setFont(new Font("Raleway", Font.BOLD, 14));
        CategoryField.setBounds(300, 190, 400, 30);
        CategoryField.setBackground(Color.WHITE);
        add(CategoryField);

        JLabel Income = new JLabel("Income: ");
        Income.setFont(new Font("Raleway", Font.BOLD, 20));
        Income.setBounds(100, 240, 200, 30);
        add(Income);

        String valIncome[] = { "Null", "< 1,50,000", "< 2,50,000", "< 5,00,000", "Upto 10,00,000" };
        IncomeField = new JComboBox(valIncome);
        IncomeField.setFont(new Font("Raleway", Font.BOLD, 14));
        IncomeField.setBounds(300, 240, 400, 30);
        IncomeField.setBackground(Color.WHITE);
        add(IncomeField);

        JLabel Educational = new JLabel("Educational");
        Educational.setFont(new Font("Raleway", Font.BOLD, 20));
        Educational.setBounds(100, 290, 200, 30);
        add(Educational);

        JLabel Qualification = new JLabel("Qualification: ");
        Qualification.setFont(new Font("Raleway", Font.BOLD, 20));
        Qualification.setBounds(100, 315, 200, 30);
        add(Qualification);

        String valQualification[] = { "Non Graduation", "Graduate", "Post Graduation", "Doctrate", "Diploma", "Other" };
        QualificationField = new JComboBox(valQualification);
        QualificationField.setFont(new Font("Raleway", Font.BOLD, 14));
        QualificationField.setBounds(300, 315, 400, 30);
        QualificationField.setBackground(Color.WHITE);
        add(QualificationField);

        JLabel Occupation = new JLabel("Occupation: ");
        Occupation.setFont(new Font("Raleway", Font.BOLD, 20));
        Occupation.setBounds(100, 390, 200, 30);
        add(Occupation);

        String valOccupation[] = { "Salaried", "Self Employed", "Bussiness", "Student", "Retired", "Other" };
        OccupationField = new JComboBox(valOccupation);
        OccupationField.setFont(new Font("Raleway", Font.BOLD, 14));
        OccupationField.setBounds(300, 390, 400, 30);
        OccupationField.setBackground(Color.WHITE);
        add(OccupationField);

        JLabel PAN = new JLabel("PAN Number: ");
        PAN.setFont(new Font("Raleway", Font.BOLD, 20));
        PAN.setBounds(100, 440, 200, 30);
        add(PAN);

        PanTextField = new JTextField();
        PanTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        PanTextField.setBounds(300, 440, 400, 30);
        add(PanTextField);

        JLabel Aadhar = new JLabel("Aadhar Number: ");
        Aadhar.setFont(new Font("Raleway", Font.BOLD, 20));
        Aadhar.setBounds(100, 490, 200, 30);
        add(Aadhar);

        AadharTextField = new JTextField();
        AadharTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        AadharTextField.setBounds(300, 490, 400, 30);
        add(AadharTextField);

        JLabel Senior = new JLabel("Senior Citizen: ");
        Senior.setFont(new Font("Raleway", Font.BOLD, 20));
        Senior.setBounds(100, 540, 200, 30);
        add(Senior);

        seniorYes = new JRadioButton("Yes");
        seniorYes.setFont(new Font("Raleway", Font.BOLD, 14));
        seniorYes.setBackground(Color.WHITE);
        seniorYes.setBounds(300, 540, 100, 30);
        add(seniorYes);

        seniorNo = new JRadioButton("No");
        seniorNo.setFont(new Font("Raleway", Font.BOLD, 14));
        seniorNo.setBackground(Color.WHITE);
        seniorNo.setBounds(450, 540, 100, 30);
        add(seniorNo);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);

        JLabel Exisiting = new JLabel("Exisiting Account: ");
        Exisiting.setFont(new Font("Raleway", Font.BOLD, 20));
        Exisiting.setBounds(100, 590, 200, 30);
        add(Exisiting);

        exisitingYes = new JRadioButton("Yes");
        exisitingYes.setFont(new Font("Raleway", Font.BOLD, 14));
        exisitingYes.setBackground(Color.WHITE);
        exisitingYes.setBounds(300, 590, 100, 30);
        add(exisitingYes);

        exisitingNo = new JRadioButton("No");
        exisitingNo.setFont(new Font("Raleway", Font.BOLD, 14));
        exisitingNo.setBackground(Color.WHITE);
        exisitingNo.setBounds(450, 590, 100, 30);
        add(exisitingNo);

        ButtonGroup exisitingGroup = new ButtonGroup();
        exisitingGroup.add(exisitingYes);
        exisitingGroup.add(exisitingNo);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String religion_String = (String) religion.getSelectedItem();
        String category_String = (String) CategoryField.getSelectedItem();
        String Income_String = (String) IncomeField.getSelectedItem();
        String Educational_String = (String) QualificationField.getSelectedItem();
        String Occupation_String = (String) OccupationField.getSelectedItem();
        String Senior = null;
        if (seniorYes.isSelected()) {
            Senior = "Yes";
        } else if (seniorNo.isSelected()) {
            Senior = "No";
        }
        String Exisiting = null;
        if (exisitingYes.isSelected()) {
            Exisiting = "Yes";
        } else if (exisitingNo.isSelected()) {
            Exisiting = "No";
        }
        String pan = PanTextField.getText();
        String aadhar = AadharTextField.getText();

        try {
            Conn c = new Conn();
            String query = "insert into signuptwo values ('" + formNo + "','" + religion_String + "','"
                    + category_String + "','" + Income_String + "','" + Educational_String + "','" + Occupation_String
                    + "','" + pan + "','" + aadhar + "','" + Senior + "','" + Exisiting + "');";
            c.a.executeUpdate(query);
            // Signup Three
            setVisible(false);
            new SignupThree(formNo, collectedEmail).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String x[]) {
        new SignupTwo("", "");
    }
}

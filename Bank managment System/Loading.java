import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Loading extends JFrame implements Runnable {

    private JPanel contentPane;
    private JProgressBar progressBar;
    private String pinnumber;
    private int s;
    private Thread th;

    public static void main(String[] args) {
        new Loading("").setVisible(true);
    }

    public void setUploading() {
        setVisible(false);
        th.start();
    }

    public void run() {
        try {
            for (int i = 0; i <= 100; i++) {
                s = i;
                progressBar.setValue(i);
                Thread.sleep(50);
            }
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Loading(String pinnumber) {
        this.pinnumber = pinnumber;
        th = new Thread(this);

        setBounds(600, 300, 600, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Add background image using a JLabel
        ImageIcon backgroundImage = new ImageIcon("Icons/Sky.jpg"); // Replace with your image path
        JLabel backgroundLabel = new JLabel(backgroundImage);
        contentPane.add(backgroundLabel, BorderLayout.CENTER);

        JLabel lbllibraryManagement = new JLabel("Bank Management System Application");
        lbllibraryManagement.setForeground(new Color(255, 255, 255));
        lbllibraryManagement.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        lbllibraryManagement.setBounds(60, 46, 700, 35);
        backgroundLabel.add(lbllibraryManagement);

        progressBar = new JProgressBar();
        progressBar.setFont(new Font("Tahoma", Font.BOLD, 12));
        progressBar.setStringPainted(true);
        progressBar.setBounds(130, 135, 300, 25);
        backgroundLabel.add(progressBar);

        JLabel lblNewLabel_2 = new JLabel("Please Wait....");
        lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setBounds(200, 165, 150, 20);
        backgroundLabel.add(lblNewLabel_2);

        setUndecorated(true);
        setLocation(350, 200);
        setOpacity(0.0f); // Initial opacity set to 0
        fadeIn();
        setUploading();
    }

    // Fade-in animation
    private void fadeIn() {
        Timer timer = new Timer(20, new ActionListener() {
            private float opacity = 0.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacity < 1.0f) {
                    setOpacity(opacity);
                    opacity += 0.05f;
                } else {
                    setOpacity(1.0f);
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
}

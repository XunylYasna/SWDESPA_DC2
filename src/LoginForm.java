import javax.swing.*;

public class LoginForm {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel label1;

    public LoginForm(){
        panel1.setVisible(true);
    }

    public static void main(String args[]){
        LoginForm lgf = new LoginForm();

        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        label1 = new JLabel("test");
    }
}

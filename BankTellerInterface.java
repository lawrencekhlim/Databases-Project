import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankTellerInterface extends JFrame implements ActionListener{
	public BankTellerInterface() {
        initUI();
    }

    public final void initUI() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setSize(500, 500);

	   JLabel title = new JLabel();
       title.setText("Bank Teller");
       title.setVisible(true);
       
       JPanel screen = new JPanel();
        screen.setLayout(new FlowLayout());
        screen.setOpaque(false);
        screen.setBackground(Color.WHITE);
        screen.setSize(150,150);


        JLabel instruction  = new JLabel();
        JTextField userInput  = new JTextField();
        userInput.setVisible(false);
        userInput.setPreferredSize(new Dimension(150,25));
        instruction.setText("Choose an action");
        screen.add(instruction);
        screen.add(userInput);
        //screen.add(submitButton);
        screen.setVisible(true);



        JPanel gridButtons = new JPanel();
        screen.setLayout(new GridLayout(3,3));
        gridButtons.setVisible(true);


        JButton checkTransButton = new JButton();
        checkTransButton.setText("Enter Check Transaction");
        gridButtons.add(checkTransButton);

        JButton genMSButton = new JButton();
        genMSButton.setText("Generate Monthly Statement");
        gridButtons.add(genMSButton);

        JButton listClosedButton = new JButton();
        listClosedButton.setText("List Closed Accounts");
        gridButtons.add(listClosedButton);

        JButton genDTERButton = new JButton();
        genDTERButton.setText("Generate DTER");
        gridButtons.add(genDTERButton);

        JButton customerReportButton = new JButton();
        customerReportButton.setText("Customer Report");
        gridButtons.add(customerReportButton);

        JButton addInterestButton = new JButton();
        addInterestButton.setText("Add Interest");
        gridButtons.add(addInterestButton);

        JButton createAcctButton = new JButton();
        createAcctButton.setText("Create Account");
        gridButtons.add(createAcctButton);

        JButton delAcctButton = new JButton();
        delAcctButton.setText("Delete Closed Accounts and Customers");
        gridButtons.add(delAcctButton);

        JButton delTransButton = new JButton();
        delTransButton.setText("Delete Transactions");
        gridButtons.add(delTransButton); 

        getContentPane().add(title);
        getContentPane().add(gridButtons);
        getContentPane().add(screen);
  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
		public void actionPerformed(ActionEvent e){
				System.out.println("Oui!");
		}
    public static void main(String[] args) {
        BankTellerInterface ex = new BankTellerInterface();
        ex.setVisible(true);
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//transaction types
//0 - deposit
//1 - top up
//2 - withdrawal
//3 -  purchase
//4 - transfer
//5 - collect
//6 - pay friend
//7 - wire
//8 - write-check
//9 - accrue interest
//10 - fees

public class ATMInterface extends JFrame implements ActionListener{

    public ATMInterface() {
        initUI();
    }

    public final void initUI() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setSize(500, 500);

        JPanel screen = new JPanel();
        screen.setLayout(new FlowLayout());
        screen.setOpaque(false);
        screen.setBackground(Color.WHITE);
        screen.setSize(150,150);

        JButton submitButton = new JButton();
        submitButton.setText("Submit");
        


        JPanel gridButtons = new JPanel();
        screen.setLayout(new GridLayout(4,2));
        gridButtons.setVisible(true);

        JButton depositButton = new JButton();
        depositButton.setText("Deposit");
        gridButtons.add(depositButton);

        JButton topDownButton = new JButton();
        topDownButton.setText("Top Down");
        gridButtons.add(topDownButton);

        JButton withdrawlButton = new JButton();
        withdrawlButton.setText("Withdrawl");
        gridButtons.add(withdrawlButton);

        JButton purchaseButton = new JButton();
        purchaseButton.setText("Purchase");
        gridButtons.add(purchaseButton);

        JButton transferButton = new JButton();
        transferButton.setText("Transfer");
        gridButtons.add(transferButton);

        JButton collectButton = new JButton();
        collectButton.setText("Collect");
        gridButtons.add(collectButton);

        JButton wireButton = new JButton();
        wireButton.setText("Wire");
        gridButtons.add(wireButton);


        JButton payButton = new JButton();
        payButton.setText("Pay Friend");
        gridButtons.add(payButton);

        


       
       JLabel title = new JLabel();
       title.setText("ATM");
       title.setVisible(true);
       

        JLabel instruction  = new JLabel();
        JTextField userInput  = new JTextField();
        userInput.setPreferredSize(new Dimension(150,25));
        instruction.setText("Enter Pin: ");

        screen.add(instruction);
        screen.add(userInput);
        screen.add(submitButton);
        screen.setVisible(true);

        getContentPane().add(title);
        getContentPane().add(screen);
        getContentPane().add(gridButtons);

        
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
		public void actionPerformed(ActionEvent e){
				System.out.println("Oui!");
		}
    public static void main(String[] args) {
        ATMInterface ex = new ATMInterface();
        ex.setVisible(true);
    }
}
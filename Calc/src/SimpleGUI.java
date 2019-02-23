import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleGUI extends JFrame {

    private JButton button = new JButton("OK");
    private JTextField input = new JTextField("", 5);
    private JLabel label = new JLabel("Enter an expression");

    public SimpleGUI () {
        super("Calculator");
        this.setBounds(100, 100, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(label);
        container.add(input);
        button.addActionListener(new ButtonEvent ());
        container.add(button);
    }

    class ButtonEvent implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            String message = "";
            String strIn = input.getText();
            strIn = strIn.replaceAll("\\s+","");        //Избавляемся от пробелов
            message += "Button was pressed\n";
            message += "Your expression: " + strIn + "\n";
            Calculation calc = new Calculation();
            message += "Result: " + calc.calculate(strIn);
            JOptionPane.showMessageDialog(null, message, "Results", JOptionPane.PLAIN_MESSAGE);
        }
    }
}

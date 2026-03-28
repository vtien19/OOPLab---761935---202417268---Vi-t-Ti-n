import javax.swing.JOptionPane;

public class CalculateDouble {
    public static void main(String[] args) {
        String strNum1, strNum2;
        strNum1 = JOptionPane.showInputDialog(null, "Enter first number: ");
        strNum2 = JOptionPane.showInputDialog(null, "Enter second number: ");

        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;

        String result;

        if(num2 != 0){
            double quotient = num1/num2;
            result = "Sum: " + sum + "\nDifference: " + difference + "\nProduct: " + product + "\nQuotient: " + quotient;
        }
        else
            result = "Sum: " + sum + "\nDifference: " + difference + "\nProduct: " + product + "\nQuotient: Cannot divide by zero!";

        JOptionPane.showMessageDialog(null, result);
        System.exit(0);
    }
}
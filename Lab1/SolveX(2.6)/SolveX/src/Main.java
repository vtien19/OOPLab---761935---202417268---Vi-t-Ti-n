import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String choice = JOptionPane.showInputDialog(
                "1. Linear equation\n" +
                        "2. Linear system\n" +
                        "3. Quadratic equation");

        int option = Integer.parseInt(choice);
        if (option == 1) {
            new LinearEquation().solve();
        }
        else if (option == 2) {
            new LinearSystem().solve();
        }
        else if (option == 3) {
            new QuadraticEquation().solve();
        }

        System.exit(0);
    }
}
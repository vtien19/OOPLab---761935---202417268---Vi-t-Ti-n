import javax.swing.JOptionPane;

public class QuadraticEquation {

    public void solve() {

        double a = Double.parseDouble(JOptionPane.showInputDialog("Enter a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Enter b:"));
        double c = Double.parseDouble(JOptionPane.showInputDialog("Enter c:"));

        if (a == 0) {
            double x = -c / b;
            JOptionPane.showMessageDialog(null, "Linear equation x = " + x);
        } else {

            double delta = b * b - 4 * a * c;

            if (delta > 0) {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                JOptionPane.showMessageDialog(null, "x1 = " + x1 + "\nx2 = " + x2);
            } else if (delta == 0) {
                double x = -b / (2 * a);
                JOptionPane.showMessageDialog(null, "Double root x = " + x);
            } else {
                JOptionPane.showMessageDialog(null, "No real root");
            }
        }
    }
}
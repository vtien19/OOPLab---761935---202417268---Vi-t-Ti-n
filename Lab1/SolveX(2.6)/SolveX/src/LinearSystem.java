import javax.swing.JOptionPane;

public class LinearSystem {
    public void solve() {

        double a11 = Double.parseDouble(JOptionPane.showInputDialog("a11:"));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("a12:"));
        double b1 = Double.parseDouble(JOptionPane.showInputDialog("b1:"));

        double a21 = Double.parseDouble(JOptionPane.showInputDialog("a21:"));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("a22:"));
        double b2 = Double.parseDouble(JOptionPane.showInputDialog("b2:"));

        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if (D != 0) {
            double x = D1 / D;
            double y = D2 / D;
            JOptionPane.showMessageDialog(null, "x = " + x + "\ny = " + y);
        } else if (D1 == 0 && D2 == 0) {
            JOptionPane.showMessageDialog(null, "Infinite solutions");
        } else {
            JOptionPane.showMessageDialog(null, "No solution");
        }
    }
}
import javax.swing.JOptionPane;

public class LinearEquation {
    public void solve(){
        double a = Double.parseDouble(JOptionPane.showInputDialog("Enter a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Enter b:"));

        if (a == 0) {
            if (b == 0) {
                JOptionPane.showMessageDialog(null, "Infinite solutions");
            } else {
                JOptionPane.showMessageDialog(null, "No solution");
            }
        } else {
            double x = -b / a;
            JOptionPane.showMessageDialog(null, "x = " + x);
        }
    }
}

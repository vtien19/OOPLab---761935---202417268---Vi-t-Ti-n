import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter number elements: ");
        int n = input.nextInt();

        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            System.out.println("Enter element " + (i + 1) + ": ");
            arr[i] = input.nextInt();
        }

        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += arr[i];
        }

        double avg = (double) sum/n;

        Arrays.sort(arr);

        System.out.println("Sorted arrays: " + Arrays.toString(arr));
        System.out.println("Sum = " + sum);
        System.out.println("Average = " + avg);
    }
}
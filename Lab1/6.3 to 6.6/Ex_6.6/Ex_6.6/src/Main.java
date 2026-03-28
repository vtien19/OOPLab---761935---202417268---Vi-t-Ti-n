import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Nhap so dong: ");
        int rows = input.nextInt();

        System.out.print("Nhap so cot: ");
        int cols = input.nextInt();

        int[][] A = new int[rows][cols];
        int[][] B = new int[rows][cols];
        int[][] C = new int[rows][cols];

        System.out.println("Nhap ma tran A:");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                A[i][j] = input.nextInt();
            }
        }

        System.out.println("Nhap ma tran B:");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                B[i][j] = input.nextInt();
            }
        }

        // cong ma tran
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        System.out.println("Ma tran tong:");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}
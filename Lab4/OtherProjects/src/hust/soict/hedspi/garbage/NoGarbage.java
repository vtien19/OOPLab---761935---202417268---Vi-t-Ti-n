package hust.soict.hedspi.garbage;

import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) throws Exception{
        String filename = "bai6.pdf";
        byte[] inputBytes = Files.readAllBytes(Paths.get(filename));

        long startTime = System.currentTimeMillis();
        // Sử dụng StringBuilder
        StringBuilder outputStringBuilder = new StringBuilder();
        for(byte b : inputBytes){
            outputStringBuilder.append((char)b);
        }
        String outputString = outputStringBuilder.toString();
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }
}

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            split(str);
        }
    }
    
    private static void split(String str) {
        int max = 8;
        while (str.length() >= max) {
            String s = str.substring(0, max);
            System.out.println(s);
            str = str.substring(max);
        }
        
        if (str.length() > 0) {
            int needZeros = max - str.length();
            StringBuilder sb = new StringBuilder(str);
            for (int i = 0; i < needZeros; i++) {
                sb.append("0");
            }
            System.out.println(sb.toString());
        }
    }
}

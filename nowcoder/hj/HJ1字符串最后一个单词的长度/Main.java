import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        
        int len = str.length();
        int count = 0;
        
        for (int i = len - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ') {
                break;
            }
            count++;
        }
        
        System.out.println(count);
    }
}

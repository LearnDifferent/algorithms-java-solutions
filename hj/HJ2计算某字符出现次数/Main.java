import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pool = sc.nextLine().toLowerCase();
        char target = sc.nextLine().toLowerCase().charAt(0);
        
        int count = 0;
        
        for (int i = 0; i < pool.length(); i++) {
            if (pool.charAt(i)  == target) {
                count++;
            }
        }
        
        System.out.println(count);
    }
}

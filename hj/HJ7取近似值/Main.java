import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float num = sc.nextFloat();
        System.out.println(num - (int) num >= 0.5 ? (int) (num + 1) : (int) num);
    }   
}

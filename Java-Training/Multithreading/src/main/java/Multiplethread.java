import java.util.Scanner;

public class Multiplethread extends Thread{
    @Override
    public void run() {
        if (this.getName().equals("ADD")) {
            add();
        } else if (this.getName().equals("PRINT")) {
            print();
        } else if (this.getName().equals("BANK")) {
            bank();
        }
    }

    private void add() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter 2 numbers:");
        int first = sc.nextInt();
        int second = sc.nextInt();
        System.out.println("Addition Completed");
    }

    private void print() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hello All");
        }
    }

    private void bank() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter acc no.");
        System.out.println("Enter password: ");
        int acc = sc.nextInt();
        int pw = sc.nextInt();
    }
}

 class Main {
    public static void main(String[] args) {
        Multiplethread m1 = new Multiplethread();
        Multiplethread m2 = new Multiplethread();
        Multiplethread m3 = new Multiplethread();

        m1.setName("ADD");
        m2.setName("PRINT");
        m3.setName("BANK");

        m1.run();
        m2.run();
        m3.run();
    }
}

import java.util.Scanner;

public class DrivingLicenseSystem {
    public static void main(String[] args) {
        checkAge();
    }

    public static void checkAge() {
        Scanner sc = new Scanner(System.in);
        int numOfAttempts = 0;
        while (numOfAttempts < 3) {
            try {
                System.out.println("Enter your age: ");
                int age = sc.nextInt();
                if (age < 18) {
                    throw new UnderAgeException();
                } else if (age > 60) {
                    throw new OverAgeException();
                } else {
                    System.out.println("Please collect your driver's license");
                    break;
                }
            } catch (Exception e) {
                numOfAttempts++;
                System.out.println(e.getMessage());
                System.out.println("Remaining attempts: " + (3 - numOfAttempts));
                if (numOfAttempts == 3) {
                    System.out.println("You have used up all your attempts. Program terminating.");
                }
            }
        }
    }
}

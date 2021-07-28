import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class representing the user interface
 */
public class Ui {
    Scanner sc;
    Menu menu;
    Order order;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.menu = new Menu();
        this.order = new Order();
    }

    public void run() {
        printMenu();
        getOrder();
        getTipPercentage();
        printOrder();
    }

    /**
     * Displays the menu
     */
    private void printMenu() {
        System.out.println("SlNo     Item     Price\n------------------------------");
        for (int i = 1; i <= menu.getSize(); i++) {
            System.out.println(menu.getItem(i));
        }
    }

    /**
     * Gets users' orders
     */
    private void getOrder() {
        boolean isOrdering = true;
        while (isOrdering) {
            System.out.println("Enter the SlNo of the item:");
            int slno = sc.nextInt();
            System.out.println("Enter the quantity:");
            int quantity = sc.nextInt();
            order.addToOrder(menu.getItem(slno), quantity);
            System.out.println("Would you like to continue ordering? Y/N");
            String continueOrder = sc.next();
            if (continueOrder.toUpperCase(Locale.ROOT).equals("N")) {
                isOrdering = false;
            }
        }
    }

    /**
     * Gets the tip percentage from the user
     */
    private void getTipPercentage() {
        System.out.println("How much would you like to tip in %?");
        int tipPercentage = sc.nextInt();
        order.setTipPercentage(tipPercentage);
    }

    /**
     * Prints the final order
     */
    private void printOrder() {
        DecimalFormat formatter = new DecimalFormat("0.000");
        String orderDetails = "Order Details \n------------------------------ \n Item     Price     " +
            "Quantity \n";
        orderDetails += order.toString();
        orderDetails += "------------------------------";
        System.out.println(orderDetails);
        System.out.println("Items:           $" + formatter.format(order.getSubtotal()));
        System.out.println("Tax (6.75%):     $" + formatter.format(order.getTaxAmount()));
        System.out.println("Subtotal:        $" + formatter.format(order.getSubtotalWithTax()));
        System.out.println("Tip (" + order.getTipPercentage() +
            "%) :      $" + formatter.format(order.getTipAmount()) );
        System.out.println("Total:           $" + formatter.format(order.getGrandTotal()));

    }
}

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class representing the user's order
 */
public class Order {
    private HashMap<Food, Integer> order;
    private final double TAX = 0.0675;
    private double tipPercentage;

    public Order() {
        this.order = new HashMap<>();
    }

    /**
     * Adds a food item and quantity to the order HashMap
     *
     * @param food     Food object
     * @param quantity int representing the quantity of food object
     */
    public void addToOrder(Food food, int quantity) {
        if (this.order.containsKey(food)) {
            Integer existingQty = this.order.get(food);
            this.order.put(food, Integer.sum(existingQty, Integer.valueOf(quantity)));
        } else {
            this.order.put(food, Integer.valueOf(quantity));
        }
    }

    /**
     * Calculates the subtotal of the order
     *
     * @return subtotal of order
     */
    public int getSubtotal() {
        int subtotal = 0;

        Iterator orderIterator = order.entrySet().iterator();
        while (orderIterator.hasNext()) {
            Map.Entry currentElement = (Map.Entry) orderIterator.next();
            Food currentFood = (Food) currentElement.getKey();
            Integer currentQuantity = (Integer) currentElement.getValue();
            subtotal += currentQuantity.intValue() * currentFood.getPrice();
        }

        return subtotal;
    }

    /**
     * Calculates the subtotal including tax
     *
     * @return subtotal including tax
     */
    public double getSubtotalWithTax() {
        double subtotal = (double) getSubtotal();
        return subtotal + (subtotal * TAX);
    }

    /**
     * Calculates the final total including tax and tips
     *
     * @return final total including tax and tips
     */
    public double getGrandTotal() {
        double subtotalWithTax = getSubtotalWithTax();
        return getSubtotalWithTax() + getTipAmount();
    }

    /**
     * Sets the tip percentage of the order
     *
     * @param tipPercentage the tip percentage as an int
     */
    public void setTipPercentage(int tipPercentage) {
        if (tipPercentage < 10) {
            this.tipPercentage = 0.10;
        } else {
            this.tipPercentage = (double) tipPercentage / 100;
        }
    }

    /**
     * Returns the tip percentage as int
     *
     * @return tip percentage as int
     */
    public int getTipPercentage() {
        return (int) (this.tipPercentage * 100);
    }

    /**
     * Returns the tip amount in $
     *
     * @return tip amount in $ as a double
     */
    public double getTipAmount() {
        return getSubtotalWithTax() * (tipPercentage);
    }

    /**
     * Returns the tax amount in $
     *
     * @return tax amount in $ as a double
     */
    public double getTaxAmount() {
        return getSubtotal() * TAX;
    }

    @Override
    public String toString() {
        String orderString = "";
        Iterator orderIterator = order.entrySet().iterator();
        while (orderIterator.hasNext()) {
            Map.Entry currentElement = (Map.Entry) orderIterator.next();
            Food currentFood = (Food) currentElement.getKey();
            Integer currentQuantity = (Integer) currentElement.getValue();
            orderString += currentFood.orderString() + "        " + currentQuantity.toString() + "\n";
        }

        return orderString;
    }
}

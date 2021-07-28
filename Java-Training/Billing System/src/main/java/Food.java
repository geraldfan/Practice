/**
 * Class representing a food
 */
public class Food {
    private final int id;
    private final String name;
    private final int price;

    public Food(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String orderString() {
        return this.name + "     $" + this.price;
    }
    @Override
    public String toString() {
        String foodString = "";
        foodString += this.id + "        " + this.name;
        for (int i = 0; i < 10 - this.name.length(); i++) {
            foodString += " ";
        }
        foodString += "$" + this.price;
        return foodString;
    }
}

import java.util.HashMap;

/**
 * Class representing the menu
 */
public class Menu {
    private HashMap<Integer, Food> menu;

    public Menu() {
        this.menu = new HashMap<>();
        menu.put(1, new Food(1, "Coffee", 1));
        menu.put(2, new Food(2, "Burger", 2));
        menu.put(3, new Food(3, "Fries", 3));
        menu.put(4, new Food(4, "Cake", 5));
        menu.put(5, new Food(5, "Tea", 2));
    }

    public Food getItem(int id) {
        return menu.get(id);
    }

    public int getSize() {
        return menu.size();
    }

}

import java.util.*;
class Product {
    //Defining product names and its prices for our website
    private String name;
    private double price;
    public Product(String name, double price) {
        //this keyword is used for accesing it as current instances according to our initializations
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}
class ShoppingCart {
    //Adding the selected items to the shopping cart
    private List<Product> items;
    public ShoppingCart() {
        items = new ArrayList<>();
    }
    public void addItem(Product product) {
        items.add(product);
    }
    //Calculating total amount to pay for our selected items
    public double calculateTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }
    //Returns all Selected items
    public List<Product> getItems() {
        return items;
    }
}
//Defining a main class for our e-commerce web app
public class ECommerceWebApp {
    //Initializing the products,new shopping cart which are declared public in above classes
    private static List<Product> products = new ArrayList<>();
    private static ShoppingCart cart = new ShoppingCart();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        initializeProducts();
        while (true) {
            System.out.println("1. Browse Products");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    browseProducts();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    checkout();
                    break;
                case 4:
                    System.out.println("Thank you for shopping with us!Visit again soon sir/madam");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    //Intializing the products for our website-ecommerce
    private static void initializeProducts() {
        products.add(new Product("IPhone",80000));
        products.add(new Product("MacBook Air",140000));
        products.add(new Product("Apple Tablet",80000));
        products.add(new Product("Play Station",190000));
        products.add(new Product("Vegetables and Fruits(Grapes,Orange,Onions,Tomatoes,Potatoes)",2000));
        products.add(new Product("Airpods",2999));
        products.add(new Product("Shirts",4000));
    }
    //Shows all products avail
    private static void browseProducts() {
        System.out.println("Available Products:");
        //Loops until it gets all available products which are instantiated in above method
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
        }
        System.out.print("Enter the product number to add to cart (0 to go back): ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= products.size()) {
            Product selectedProduct = products.get(choice - 1);
            cart.addItem(selectedProduct);
            System.out.println(selectedProduct.getName() + " added to cart.");
        }
    }
    //Displays your cart of selected items and the total money
    private static void viewCart() {
        List<Product> items = cart.getItems();
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } 
        else {
            System.out.println("Your Cart:");
            for (Product item : items) {
                System.out.println("- " + item.getName() + " - $" + item.getPrice());
            }
            System.out.println("Total: $" + cart.calculateTotal());
        }
    }
    //Checkouts from website
    private static void checkout() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
        } 
        else {
            System.out.println("Total: $" + cart.calculateTotal());
            System.out.println("Thank you for shopping with us! Your order has been placed.Visit again soon sir/madam");
            cart = new ShoppingCart(); // Reset cart after checkout
        }
    }
}

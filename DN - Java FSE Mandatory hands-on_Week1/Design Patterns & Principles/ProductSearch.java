import java.util.Arrays;
import java.util.Comparator;

class Product {
    private final String productId;
    private final String productName;
    private final String category;

    // Constructor
    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId +
               ", Name: " + productName +
               ", Category: " + category;
    }
}

public class ProductSearch {

    // Linear Search
    static Product linearSearch(Product[] products, String targetName) {

        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(targetName)) {
                return product;
            }
        }

        return null;
    }

    // Binary Search
    static Product binarySearch(Product[] products, String targetName) {

        int left = 0;
        int right = products.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int comparison = targetName.compareToIgnoreCase(products[mid].getProductName());

            if (comparison == 0) {
                return products[mid];
            }
            else if (comparison > 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        // Array for Linear Search
        Product[] products = {
                new Product("P101", "Wireless Mouse", "Electronics"),
                new Product("P102", "Mechanical Keyboard", "Electronics"),
                new Product("P103", "Running Shoes", "Footwear"),
                new Product("P104", "Gaming Monitor", "Electronics"),
                new Product("P105", "Leather Wallet", "Accessories")
        };

        String searchItem = "Gaming Monitor";

        // Linear Search
        Product linearResult = linearSearch(products, searchItem);

        System.out.println("===== LINEAR SEARCH =====");

        if (linearResult != null)
            System.out.println("Product Found: " + linearResult);
        else
            System.out.println("Product Not Found");

        // Create Sorted Array for Binary Search
        Product[] sortedProducts = products.clone();

        Arrays.sort(sortedProducts,
                Comparator.comparing(Product::getProductName, String.CASE_INSENSITIVE_ORDER));

        // Binary Search
        Product binaryResult = binarySearch(sortedProducts, searchItem);

        System.out.println("\n===== BINARY SEARCH =====");

        if (binaryResult != null)
            System.out.println("Product Found: " + binaryResult);
        else
            System.out.println("Product Not Found");
    }
}
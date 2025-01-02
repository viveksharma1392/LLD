import java.util.*;
import java.util.stream.Collectors;


// ------------------- Model Layer -------------------

/**
 * Product class that represents an inventory product.
 */
class Product {
    private String name;
    private String sku;
    private String description;
    private int quantity;

    public Product(String name, String sku, String description, int quantity) {
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int quantity) {
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
        } else {
            throw new IllegalArgumentException("Insufficient stock");
        }
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
}

/**
 * Order class that represents a customer order.
 */
class Order {
    private String orderId;
    private List<OrderItem> items;
    private boolean isProcessed;

    public Order(String orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.isProcessed = false;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void markAsProcessed() {
        this.isProcessed = true;
    }
}

/**
 * OrderItem class that represents a specific product in an order.
 */
class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

// ------------------- Data Layer -------------------

/**
 * Inventory class (Singleton) responsible for storing products and managing inventory.
 */
class Inventory {
    private static Inventory instance;
    private Map<String, Product> productCatalog;

    private Inventory() {
        productCatalog = new HashMap<>();
    }

    public static synchronized Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addProduct(Product product) {
        productCatalog.put(product.getSku(), product);
    }

    public Product getProduct(String sku) {
        return productCatalog.get(sku);
    }

    public Collection<Product> getAllProducts() {
        return productCatalog.values();
    }
}

// ------------------- Controller Layer -------------------

/**
 * ProductController responsible for managing the product catalog.
 */
interface ProductController {
    void addProductToCatalog(Product product);
    Product getProductDetails(String sku);
}

class ProductControllerImpl implements ProductController {

    private Inventory inventory;

    public ProductControllerImpl() {
        inventory = Inventory.getInstance();
    }

    @Override
    public void addProductToCatalog(Product product) {
        inventory.addProduct(product);
    }

    @Override
    public Product getProductDetails(String sku) {
        return inventory.getProduct(sku);
    }
}

/**
 * OrderController responsible for processing orders.
 */
interface OrderController {
    Order createOrder(String orderId);
    void addItemToOrder(Order order, Product product, int quantity);
    void processOrder(Order order);
}
//TODO: have separate cart and order processing system (only order creation)
//TODO: Inventory schema can be explored further with various use cases and concurrency
//TODO: explore locking of multiproduct in multiuser environment
//HLD - CQRS pattern
class OrderControllerImpl implements OrderController {
    private Inventory inventory;

    public OrderControllerImpl() {
        inventory = Inventory.getInstance();
    }

    @Override
    public Order createOrder(String orderId) {
        return new Order(orderId);
    }

    //Assuming that inventory is getting updated frequently
    // so no need to validate inventory
    @Override
    public void addItemToOrder(Order order, Product product, int quantity) {
        OrderItem orderItem = new OrderItem(product, quantity);
        order.addItem(orderItem);
    }

    @Override
    public void processOrder(Order order) {
        if (order.isProcessed()) {
            System.out.println("Order already processed");
            return;
        }

        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            product.decreaseQuantity(quantity);
        }

        order.markAsProcessed();
        System.out.println("Order " + order.getOrderId() + " processed successfully.");
    }
}

// ------------------- Observer Pattern for Stock Management -------------------

/**
 * StockAlert interface for observers to notify when stock levels are low.
 */
interface StockAlertObserver {
    void onStockLow(Product product);
}

/**
 * StockAlertService to send alerts when stock is low.
 */
class StockAlertService implements StockAlertObserver {

    @Override
    public void onStockLow(Product product) {
        System.out.println("ALERT: Stock is low for product: " + product.getName() + ", SKU: " + product.getSku());
    }
}

/**
 * InventoryListener that checks stock and alerts when necessary.
 */
class InventoryListener {

    private static final int LOW_STOCK_THRESHOLD = 5;
    private List<StockAlertObserver> observers;

    public InventoryListener() {
        observers = new ArrayList<>();
    }

    public void addObserver(StockAlertObserver observer) {
        observers.add(observer);
    }

    public void checkStockLevels() {
        Inventory inventory = Inventory.getInstance();
        for (Product product : inventory.getAllProducts()) {
            if (product.getQuantity() <= LOW_STOCK_THRESHOLD) {
                notifyObservers(product);
            }
        }
    }

    private void notifyObservers(Product product) {
        for (StockAlertObserver observer : observers) {
            observer.onStockLow(product);
        }
    }
}

// ------------------- Main Class -------------------

public class InventoryManagementSystem {

    public static void main(String[] args) {
        // Initialize controllers
        ProductController productController = new ProductControllerImpl();
        OrderController orderController = new OrderControllerImpl();

        // Add products to inventory
        //TODO: product should store only product metadata
        Product product1 = new Product("Laptop", "L001", "High-end gaming laptop", 10);
        Product product2 = new Product("Smartphone", "S001", "Latest model smartphone", 3);

        productController.addProductToCatalog(product1);
        productController.addProductToCatalog(product2);

        // Create an order
        Order order = orderController.createOrder("ORD123");
        orderController.addItemToOrder(order, product1, 2);
        orderController.addItemToOrder(order, product2, 1);

        // Process order
        orderController.processOrder(order);

        // Monitor stock levels
        InventoryListener inventoryListener = new InventoryListener();
        inventoryListener.addObserver(new StockAlertService());
        inventoryListener.checkStockLevels(); // This will trigger a low stock alert for the smartphone

    }
}



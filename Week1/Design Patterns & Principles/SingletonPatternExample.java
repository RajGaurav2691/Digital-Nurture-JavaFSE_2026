// ==========================================
// 1. DEFINE THE SINGLETON CLASS (Non-public)
// ==========================================
class Logger {

    // A. Create a private static instance of itself
    private static Logger instance;

    // B. Ensure the constructor of Logger is private
    private Logger() {
        // Prevents instantiation from outside the class
    }

    // C. Provide a public static method to get the instance of the Logger class
    public static Logger getInstance() {
        // Lazy initialization: Create the instance only when requested for the first time
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Utility method to simulate logging a message
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// ==========================================
// 2. TEST THE SINGLETON IMPLEMENTATION (Public)
// ==========================================
public class SingletonPatternExample {
    public static void main(String[] args) {
        System.out.println("--- Testing Singleton Pattern Implementation ---");

        // Request the Logger instance multiple times
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Use the instances to log messages
        logger1.log("Initializing core application components...");
        logger2.log("Database connection established successfully.");

        System.out.println("\n--- Memory Validation Check ---");
        System.out.println("Logger 1 Memory Hashcode: " + logger1.hashCode());
        System.out.println("Logger 2 Memory Hashcode: " + logger2.hashCode());

        // Strictly verify that both references point to the exact same object instance
        if (logger1 == logger2) {
            System.out.println("\nSUCCESS: Strictly verified. Both references point to the exact same instance.");
        } else {
            System.out.println("\nFAILURE: Design flaw detected. Multiple instances exist.");
        }
    }
}
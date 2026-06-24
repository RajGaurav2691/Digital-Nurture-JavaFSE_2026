// ==========================================
// 1. DEFINE PRODUCT INTERFACE
// ==========================================
interface Document {
    void open();
    void close();
}

// ==========================================
// 2. CREATE CONCRETE PRODUCTS
// ==========================================
class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("[Word] Opening DOCX file: Initializing layouts and text fields.");
    }

    @Override
    public void close() {
        System.out.println("[Word] Closing DOCX file: Saving changes to disk.");
    }
}

class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("[PDF] Opening PDF file: Rendering vector graphics and text layers.");
    }

    @Override
    public void close() {
        System.out.println("[PDF] Closing PDF file: Releasing read-only memory lock.");
    }
}

// ==========================================
// 3. DEFINE CREATOR CLASSES (FACTORIES)
// ==========================================
abstract class DocumentFactory {
    // The core Factory Method that subclasses must implement
    public abstract Document createDocument();

    // An optional helper method to perform operations on the product
    public void processDocument() {
        Document doc = createDocument();
        doc.open();
        // Perform standard processing operations here if required
        doc.close();
    }
}

// Concrete Creators overriding the Factory Method
class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

// ==========================================
// 4. TEST THE FACTORY IMPLEMENTATION
// ==========================================
public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        System.out.println("--- Testing Factory Method Pattern Implementation ---\n");

        // Client wants to work with a Word Document
        System.out.println("Scenario A: User requests a Word Document...");
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.close();
        
        System.out.println("\n------------------------------------------------");

        // Client wants to work with a PDF Document
        System.out.println("Scenario B: User requests a PDF Document...");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.close();

        System.out.println("\n------------------------------------------------");
        
        // Testing execution using the build-in creator processing logic
        System.out.println("Scenario C: Utilizing automated factory processing...");
        wordFactory.processDocument();
    }
}
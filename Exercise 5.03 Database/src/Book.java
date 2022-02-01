//**********Book**********//
import javax.swing.JFrame;

/**
 *
 * @author Patrick He
 */
public class Book extends JFrame {
    
    //instance variables
    private String title;
    private String author;
    private int quantity;
    private static int numBooks = 0;
    
    //Constructor
    Book(String t, String a, int q) {
        title = t;
        author = a;
        if(q >= 0) { //Checks if it is greater or equal to 0
          quantity = q;  
        }
        numBooks++;
    }

    //getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity>=0) {
            this.quantity = quantity;
        }
    }

    public static int getNumBooks() {
        return numBooks;
    }

    public void setNumBooks(int numBooks) {
        if(numBooks>=0) {
            this.numBooks = numBooks;
        }
    }
    
    @Override
    public String toString() {
        return getTitle()+"|"+getAuthor()+"|"+getQuantity();
    }
}

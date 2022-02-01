//**********DatabaseGUI**********//

//imports
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Stacie Torres
 * @author Patrick He
 */
public class DatabaseGUI extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new DatabaseGUI(); //Runs the GUI
    } // end main method

    //instance variables
    private JPanel listPanel;
    private JLabel mainLabel1, mainLabel2, titlelabel, authorLabel, quantityLabel, sortOptionLabel; //Jlabels for Text or Images
    private JTextField titleTextField, authorTextField, quantityTextField; //for user input
    private JButton saveBtn, sortByTitle, sortByAuthor; //button to click after user input, to save info
    private Font f1 = new Font("SansSerif", Font.BOLD, 36);
    private Font f2 = new Font("SansSerif", Font.BOLD, 50);
    private Book[] books = new Book[50]; //make an array of your objects for your database

    //Constructor
    public DatabaseGUI() {
        setSize(1600, 1000);
        setLayout(null);
        Container c = getContentPane(); //instead of a JPanel - easier to set coordinated of labels, textFields and buttons
        c.setBackground(new Color(0, 0, 204));

        //Title at the north-west of the screen
        mainLabel1 = new JLabel("Submit Book ");
        mainLabel1.setFont(f2);
        mainLabel1.setBounds(30, 100, 400, 40);
        c.add(mainLabel1);

        //Requirement for entry #1
        titlelabel = new JLabel("Title:");
        titlelabel.setFont(f1);
        titlelabel.setBounds(30, 200, 220, 40);
        titlelabel.setBackground(new Color(0, 255, 255));
        titlelabel.setOpaque(true);
        c.add(titlelabel);
        titleTextField = new JTextField(""); //Text field for user input
        titleTextField.setFont(f1);
        titleTextField.setBounds(270, 200, 300, 40);
        c.add(titleTextField);

        //Requirement for entry #2
        authorLabel = new JLabel("Author:");
        authorLabel.setBounds(30, 300, 220, 40);
        authorLabel.setFont(f1);
        authorLabel.setBackground(new Color(0, 255, 255));
        authorLabel.setOpaque(true);
        c.add(authorLabel);
        authorTextField = new JTextField(""); //Text field for user input
        authorTextField.setFont(f1);
        authorTextField.setBounds(270, 300, 300, 40);
        c.add(authorTextField);

        //Requirement for entry #3
        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(30, 400, 220, 40);
        quantityLabel.setFont(f1);
        quantityLabel.setBackground(new Color(0, 255, 255));
        quantityLabel.setOpaque(true);
        c.add(quantityLabel);
        quantityTextField = new JTextField(""); //Text field for user input
        quantityTextField.setFont(f1);
        quantityTextField.setBounds(270, 400, 300, 40);
        c.add(quantityTextField);

        //Save all button
        saveBtn = new JButton("SAVE ENTRY");
        saveBtn.setBounds(270, 500, 300, 40);
        saveBtn.setFont(f1);
        saveBtn.addActionListener(this);
        c.add(saveBtn);

        //Title at the north-middle part of the screen
        mainLabel2 = new JLabel("Book Database");
        mainLabel2.setFont(f2);
        mainLabel2.setBounds(630, 100, 500, 40);
        c.add(mainLabel2);

        //Sort option title at the north-east of the screen
        sortOptionLabel = new JLabel("Sort By: ");
        sortOptionLabel.setFont(f1);
        sortOptionLabel.setBounds(1260, 200, 300, 40);
        c.add(sortOptionLabel);

        //Sort by title button at the north-east of the screen
        sortByTitle = new JButton("Title");
        sortByTitle.setBounds(1260, 250, 300, 40);
        sortByTitle.setFont(f1);
        sortByTitle.addActionListener(this);
        c.add(sortByTitle);

        //Sort by title button at the north-east of the screen
        sortByAuthor = new JButton("Author");
        sortByAuthor.setBounds(1260, 300, 300, 40);
        sortByAuthor.setFont(f1);
        sortByAuthor.addActionListener(this);
        c.add(sortByAuthor);

        //Red panel in the center of the page, library list will appear here
        listPanel = new JPanel(new GridLayout(50, 0));
        listPanel.setBounds(630, 200, 600, 700);
        listPanel.setBackground(Color.RED);
        c.add(listPanel);

        readBooks();
        displayBooks();

        setVisible(true); //Set visible true
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Stop the build
    }

    //If a button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveBtn) { //if the save button was pressed
            String title = titleTextField.getText(); //set title 
            String author = authorTextField.getText(); //set author
            int quantity = Integer.parseInt(quantityTextField.getText()); //set quantity
            String temp;

            try {
                FileWriter fw = new FileWriter("Book.txt", true); //Create file to store information
                PrintWriter pw = new PrintWriter(fw);
                pw.println(title + "|" + author + "|" + quantity);
                pw.close();

                FileReader fr = new FileReader("Book.txt");
                BufferedReader br = new BufferedReader(fr); //Reads the file

                //Puts input into array
                books[Book.getNumBooks()] = new Book(title, author, quantity);

                //Displays input in myBooks
                JLabel myBooks = new JLabel(title + "|" + author + "|" + quantity);
                listPanel.add(myBooks);
                listPanel.revalidate();
                listPanel.repaint();
                pw.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == sortByTitle) { //If sortByTitle is clicked
            sortByTitle();
        }
        if (e.getSource() == sortByAuthor) { //If sortByAuthor is clicked
            sortByAuthor();
        }
    }

    //Reads books in Book file and puts them in array
    public void readBooks() {

        File f = new File("C:\\Users\\pheth\\OneDrive\\Documents\\NetBeansProjects\\Exercise 5.03 Database\\Book.txt");
        String t, a;
        int q;

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();

            while (str != null) { //While there are still things in text file
                t = str.substring(0, str.indexOf("|")); //The title
                a = str.substring(str.indexOf("|") + 1, str.lastIndexOf("|")); //The author
                q = Integer.parseInt(str.substring(str.lastIndexOf("|") + 1, str.length())); //The quantity

                books[Book.getNumBooks()] = new Book(t, a, q); //Puts it in array
                str = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    //Sort the library by title A-Z, uppercase letters have priority
    public void sortByTitle() {
        Book temp = new Book("", "", 0);

        //Changes the orders of the entries until they are in order
        for (int j = Book.getNumBooks() - 1; j > 0; j--) {
            for (int i = 0; i < j - 1; i++) {
                if (books[i] != null && books[i + 1] != null) {
                    if (books[i].getTitle().compareTo(books[i + 1].getTitle()) > 0) {
                        temp = books[i];
                        books[i] = books[i + 1];
                        books[i + 1] = temp;
                    }
                }
            }
        }
        displayBooks(); //Calls displayBooks to display ordered books
        this.repaint();
    }

    //Sorts by author A-Z, uppercase letters have priority
    public void sortByAuthor() {
        Book temp = new Book("", "", 0);

        //Changes entries orders until they are in order
        for (int j = Book.getNumBooks() - 1; j > 0; j--) {
            for (int i = 0; i < j - 1; i++) {
                if (books[i] != null && books[i + 1] != null) {
                    if (books[i].getAuthor().compareTo(books[i + 1].getAuthor()) > 0) {
                        temp = books[i];
                        books[i] = books[i + 1];
                        books[i + 1] = temp;
                    }
                }
            }
        }
        displayBooks(); //Calls displayBooks to display ordered books
        this.repaint();
    }

    //Displays books
    public void displayBooks() {

        listPanel.removeAll(); //Remove what is currently on the panel

        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) { //If there aren't any more books
                break;
            }
            //Print out the book
            JLabel myBooks = new JLabel(books[i].getTitle() + " | " + books[i].getAuthor() + " | " + books[i].getQuantity());
            listPanel.add(myBooks);
            listPanel.revalidate();
            listPanel.repaint();
        }
    }
}

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

public class LibraryContents extends JFrame {
    JLabel[] labels = new JLabel[50];

    public void libraryContents(Book[] b) {
        setBounds(1700, 10, 400, 1000);
        setLayout(null);
        Container c = getContentPane();
        c.setBackground(new Color(0, 0, 200, 40));
        
        for (int i = 0; i < b.length; i++) {
            if(b[i]==null) {
                break;
            }
            labels[i] = new JLabel(labels[i].toString());
            labels[i].setBounds(10, 50*i, 200, 40);
            c.add(labels[i]);
        }
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
    }
}


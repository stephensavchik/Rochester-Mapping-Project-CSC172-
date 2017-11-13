package PROJECT4;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * Created by steph on 4/18/2017.
 */
public class tester
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Graph g = new Graph("C:\\Users\\steph\\OneDrive\\Documents\\Intellij Projects\\CSC172\\IdeaProjects\\compSci172\\src\\PROJECT4\\MONROE.txt");

        JFrame frame = new JFrame("PROJECT4");
        canvas can = new canvas(g);
        frame.add(can);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }
}

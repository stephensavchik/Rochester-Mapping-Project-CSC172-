package PROJECT4;

/**
 * Created by steph on 4/25/2017.
 */
import javax.swing.JComponent;
import java.awt.*;
import java.util.ArrayList;

public class canvas extends JComponent {
    Graph myGraph;
    int height, width;

    public canvas(Graph gr) {
        super();
        myGraph = gr;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        height = getHeight(); //912 (longitude coordinates)
        width = getWidth(); //968 (latitude coordinates)
        g.setColor(Color.BLACK);

        for (int i = 0; i < myGraph.nodes.size(); i++) {
            for (int j = 1; j < myGraph.nodes.get(i).size(); j++) {

                g2d.drawLine(applyLatScaling(myGraph.nodes.get(i).get(0).latt), applyLongScaling(myGraph.nodes.get(i).get(0).longitude),
                        applyLatScaling(myGraph.nodes.get(i).get(j).latt), applyLongScaling(myGraph.nodes.get(i).get(j).longitude));
            }
        }
        ArrayList<Edge> edg = myGraph.primsAlgorithm();
        //System.out.println("Size: " + edg.size());

        g.setColor(Color.MAGENTA);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        for (int i = 0; i < edg.size(); i++)
        {
            g2d.drawLine(applyLatScaling(edg.get(i).start.latt), applyLongScaling(edg.get(i).start.longitude),
                    applyLatScaling(edg.get(i).end.latt), applyLongScaling(edg.get(i).end.longitude));
        }

        System.out.println("Edges in Minimum Weight Spanning Tree: " + edg);


        System.out.println("\n\nDijkstra's Path: " + myGraph.Dijkstras(myGraph.nodes.get(myGraph.intersectionNum.get("ANDERSON")).get(0), myGraph.nodes.get(myGraph.intersectionNum.get("CSB")).get(0)));

        g.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(10));
        for (int i = 0; i < myGraph.dijkstra.size() - 1; i++) {
            //System.out.println(myGraph.dijkstra.get(i));

            try
            {
                Node p1 = myGraph.dijkstra.get(i);
                Node p2 = myGraph.dijkstra.get(i + 1);

                g.drawLine(applyLatScaling(p1.latt), applyLongScaling(p1.longitude), applyLatScaling(p2.latt), applyLongScaling(p2.longitude));
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Final Node Reached");
            }
        }
        g.setColor(Color.BLACK);
    }

    public int applyLongScaling (double value)
    {
        return ((int) ((value - myGraph.minLong)*(height / (myGraph.maxLong - myGraph.minLong))));
    }
    public int applyLatScaling (double value)
    {
        return ((int) ((value - myGraph.minLat)*(width / (myGraph.maxLat - myGraph.minLat))));
    }






}

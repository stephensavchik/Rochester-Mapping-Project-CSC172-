package PROJECT4;

import java.util.Comparator;

/**
 * Created by steph on 4/12/2017.
 */
public class Edge implements Comparable<Edge>//represents roads
{
    String name;
    Node start;
    Node end;
    double weight;

    public Edge()
    {

    }

    public Edge(String name, Node start, Node end)
    {
        this.name = name;
        this.start = start;
        this.end = end;
        weight = Math.sqrt((Math.pow((start.latt - end.latt),2) + Math.pow((start.longitude - end.longitude), 2))); //distance formula

    }

    public String toString ()
    {
        return "Name:" + name;
    }
    //@Override
    public int compareTo(Edge other)
    {
        if (this.weight > other.weight)
            return 1;
        else if ((this.weight < other.weight))
            return -1;
        else
            return 0;
    }


}

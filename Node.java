package PROJECT4;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by steph on 4/12/2017.
 */
public class Node //acts as an intersection
{
    String name;
    double latt;
    double longitude;
    public ArrayList<Edge> edges;
    public Node(String name, double latitude, double longitude)
    {
        this.name = name;
        this.latt = latitude;
        this.longitude = longitude;
        edges = new ArrayList<>();

    }
    public Node()
    {
        edges = new ArrayList<>();
    }
    public String toString()
    {
        return "Name: " + name + ", Latitude: " + latt + ", Longitude: " + longitude;
    }

    public boolean equals(Node other)
    {
        return (this.name.equals(other.name));
    }
}

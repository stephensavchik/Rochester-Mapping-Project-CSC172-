package PROJECT4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by steph on 4/12/2017.
 */
public class Graph
{

    File file;
    Hashtable<String,Node> intersections = new Hashtable<>();//support quick lookup of intersections(nodes) by name
    Hashtable<String, Integer> intersectionNum = new Hashtable<>();
    public ArrayList<LinkedList<Node>> nodes = new ArrayList<>();
    public ArrayList<Edge> myedges = new ArrayList<>();
    public double maxLong = -Double.MAX_VALUE, minLong = Double.MAX_VALUE;
    public double maxLat = -Double.MAX_VALUE, minLat = Double.MAX_VALUE;
    public double[] distances;
  //  boolean[] visited;
    public HashSet<Node> visited = new HashSet<>();
    public HashSet<Node> unvisited = new HashSet<>();
    public HashMap<Node, Node> predecessors = new HashMap<>();

    public ArrayList<Node> dijkstra = new ArrayList<>();


    public Graph(String filepath) throws FileNotFoundException
    {
        file = new File(filepath);
        Scanner fileScanner = new Scanner(file);

        String[] strings;
        int counter = 0;
        while (fileScanner.hasNext())
        {//while more lines exist
            Node newNode = new Node();
            LinkedList newList = new LinkedList();

            String str = fileScanner.nextLine();

            //parse each line individually
                strings = str.split("\\s+");
                if (strings[0].equals("i"))
                {//intersection
                    newNode.name = strings[1];
                    newNode.latt = Double.parseDouble(strings[2]);
                    newNode.longitude = Double.parseDouble(strings[3]);


                    if(newNode.latt > maxLat)
                        maxLat = newNode.latt;

                    if(newNode.longitude > maxLong)
                        maxLong = newNode.longitude;


                    if(newNode.latt < minLat)
                        minLat = newNode.latt;

                    if(newNode.longitude < minLong)
                        minLong = newNode.longitude;

                    newList.add(newNode);
                    unvisited.add(newNode);
                    nodes.add(newList);
                    intersections.put(newNode.name, newNode);
                    intersectionNum.put(newNode.name, counter);//uses the name of the intersection as a key for its
                    //corresponding integer value, used later to add each node contained in each edge to the linked list
                    counter++;
                }
                else if (strings[0].equals("r"))
                {
                    String name1 = strings[1];
                    Node n1 = intersections.get(strings[2]);
                    Node n2 = intersections.get(strings[3]);
                    Edge e = new Edge(name1, n1, n2);
                    nodes.get(intersectionNum.get(n1.name)).get(0).edges.add(e);
                    nodes.get(intersectionNum.get(n2.name)).get(0).edges.add(e);
                    myedges.add(e);
                }


        }

        for (Edge ed: myedges) //adds the start and end node of each edge into the array list of linked lists
            //to complete the graph structure and to allow for easy look-up
        {
            nodes.get(intersectionNum.get(ed.start.name)).add(ed.end);
            nodes.get(intersectionNum.get(ed.end.name)).add(ed.start);
        }


//        for (LinkedList<Node> t: nodes)
//            System.out.println(t);
//        System.out.println();
            distances = new double[nodes.size()];
//        Dijkstras(nodes.get(0).get(0), nodes.get(10).get(0));

//        ArrayList<Edge> cool = spanningTree();
//
//        for (int i = 0; i < cool.size(); i++)
//        {
//            System.out.println(cool.get(i));
//        }

    }

    public LinkedList<Node> Dijkstras (Node node1, Node node2)
    {
        System.out.println(node1.name + " " + node2.name);
        for(int i = 0; i < distances.length; i++)
            distances[i] = Double.MAX_VALUE;
        distances[intersectionNum.get(node1.name)] = 0;
        //visited.add(node1);

        Node evalNode = new Node();
        while(!(unvisited.isEmpty()))
        {
            evalNode = getLowestNode();
            unvisited.remove(evalNode);
            visited.add(evalNode);
            updateNeighbors(evalNode);

        }

//        for (int i = 0; i < distances.length; i++)
//        {
//            System.out.println(distances[i]);
//        }

        return makePath(node2);

    }

    public Node getLowestNode()
    {
       double min = Double.MAX_VALUE;
       int lowestIndex = -1;

       for (int i = 0; i < distances.length; i++)
       {
           if((distances[i] <= min) && !(visited.contains(nodes.get(i).get(0))))
           {
               min = distances[i];
               lowestIndex = i;
           }
       }
       return nodes.get(lowestIndex).get(0); //returns node corresponding to the index of the lowest distance
    }

    void updateNeighbors (Node nodeBoi)
    {
        double newDistance = 0;
        Node current = new Node();
        for(Edge ed: nodeBoi.edges)
        {

            if(ed.start.equals(nodeBoi))
                current = ed.end;
            else
                current = ed.start;

            newDistance = ed.weight + distances[(intersectionNum.get(nodeBoi.name))];
            if (newDistance < distances[intersectionNum.get(current.name)]) {
                distances[intersectionNum.get(current.name)] = newDistance;
                predecessors.put(current, nodeBoi);
            }
        }
    }

    LinkedList<Node> makePath(Node end)
    {
        LinkedList<Node> path = new LinkedList<>();
        Node step = end;
        if (predecessors.get(step) == null)
            return null;
        path.add(step);
        while (predecessors.get(step) != null){
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        LinkedList<Node> temp = path;
        for (Node n :temp)
            dijkstra.add(n);
        return path;
    }

    ArrayList<Edge> primsAlgorithm()
    {
        ArrayList<Edge> primsEdges = new ArrayList<>();
        ArrayList<Node> primsNodes = new ArrayList<>();

        primsNodes.add(nodes.get(0).get(0));
        int counter = 0;
        Edge shortest;
        //System.out.println(nodes.size());
        while (primsNodes.size() < nodes.size())
        {
            shortest = new Edge();
            shortest.weight = Double.MAX_VALUE;
            for (int i = 0; i < primsNodes.size(); i++)
            {
                for (int j = 0; j < primsNodes.get(i).edges.size(); j++)
                {
                    if((primsNodes.get(i).edges.get(j).weight < shortest.weight) && (primsNodes.contains(primsNodes.get(i).edges.get(j).start))
                            ^ (primsNodes.contains(primsNodes.get(i).edges.get(j).end)))
                        shortest = primsNodes.get(i).edges.get(j);
                }
            }

            primsEdges.add(shortest);

            if(primsNodes.contains(shortest.start) && !primsNodes.contains(shortest.end))
                primsNodes.add(shortest.end);

            if(primsNodes.contains(shortest.end) && !primsNodes.contains(shortest.start))
               primsNodes.add(shortest.start);

        }

//        System.out.println(primsNodes.size());
//        System.out.println(primsEdges.size());
        return primsEdges;
   }




   // ArrayList<Edge> spanningTree()
  //  {
//        ArrayList<Edge> spanningEdges = new ArrayList<>();
//        marked = new ArrayList<>();
//        PriorityQueue<Edge> boi = new PriorityQueue<>(myedges.size(), Edge::compareTo);
//
//        for(int i = 0; i < myedges.size(); i++)
//            boi.add(myedges.get(i));
//        Edge current;
//
//        while(!boi.isEmpty())
//        {
//            current = boi.poll();
//            if((cyclic(current)) == false)
//            {
//                if(!(marked.contains(current.start)))
//                    marked.add(current.start);
//                if (!(marked.contains(current.end)))
//                    marked.add(current.end);
//
//                spanningEdges.add(current);
//            }
//        }
//        return spanningEdges;
//    }
//
//    public boolean cyclic (Edge current)
//    {
//        Node n1 = current.start;
//        Node n2 = current.end;
//        visited1.clear();
//        //visited1.add(n2);
//
//        return connectedToOriginal(n1, n2, 1);


    //}

//    public boolean connectedToOriginal(Node current, Node original, int callNum)
//    {
//        if(current.name.equals(original.name) && callNum!=2)
//        {
//            //System.out.println("here");
//            return true;
//        }
//        else
//        {
//            for (int i = 1; i < nodes.get(intersectionNum.get(current.name)).size(); i++)
//            {
//                if ((marked.contains(nodes.get(intersectionNum.get(current.name)).get(i))) &&
//                        !(visited1.contains(nodes.get(intersectionNum.get(current.name)).get(i)))) {
//                    visited1.add(nodes.get(intersectionNum.get(current.name)).get(i));
//                    return connectedToOriginal(nodes.get(intersectionNum.get(current.name)).get(i), original, (callNum + 1));
//                }
//            }
//        }
//       return false;
//    }








}

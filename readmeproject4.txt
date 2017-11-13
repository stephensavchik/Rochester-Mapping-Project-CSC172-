Stephen Savchik
ssavchik@u.rochester.edu
4/29/17

I worked really hard on this :)


Classes and Files contained in this project
-Graph.java
-canvas.java
-Edge.java
-tester.java
-Node.java
-UR.txt
-MONROE.txt
-nys.txt

In order to test the functionality of this program simply run the 'tester.java' class which contains a main method.
The entire map is displayed in black 
The minimum weight spanning tree is contained in magenta
The shortest path between two nodes is contained in a thicker red Line. 

In order to change the test nodes from the current "GILBERT-LONG"
to "HOPEMAN", simply replace these String names with any two other names of intersections which can be found in the corresponding text file.

In order to change which text file the program reads from, simply change the parameter of the graph constructor in the 'tester.java' class:

UR.txt : "C:\\Users\\steph\\OneDrive\\Documents\\Intellij Projects\\CSC172\\IdeaProjects\\compSci172\\src\\PROJECT4\\UR.txt"
MONROE.txt: "C:\\Users\\steph\\OneDrive\\Documents\\Intellij Projects\\CSC172\\IdeaProjects\\compSci172\\src\\PROJECT4\\MONROE.txt"
nys.txt:  "C:\\Users\\steph\\OneDrive\\Documents\\Intellij Projects\\CSC172\\IdeaProjects\\compSci172\\src\\PROJECT4\\nys.txt"
 

Synopsis:

The primary obstacle in creating this project was getting Dijkstra's algorithm to
function within our created graph. Scanning in the information did not take very long
and neither did organizing it into a usable format. It took us the longest to get Dijkstra's
to trace to the correct point efficiently, but even after getting to that point we had to 
implement a way to trace the path back to where it came from. To overcome this final obstacle
we ended up using a HashMap structure to keep track of what each target node's predecessor was
along the path. From there we interativley called the HashMap on the preceding nodes until we
arrived at the start, forming a list of the path. 

For the MST, we used Prim's algorithm to find and overlay MST on the full graph. This involved
creating two array lists of edge and node, both containing the nodes and edges (respectively)
contained in the shortest spanning path of the map. This was achieved by first adding a random
node into the node array. Then we cycled through every edge of that node using the edge array
instance variable in the Node class. In each iteration of cycling through the available nodes,
the shortest edge is found through simple comparison. This edge is then added to the list
of edges contained in the MST. This process is repeated until every node in the graph is contained
in the MST, and the edges connecting all of these nodes are printed in a different color on 
the graph.

The runtime for this project works smoothly in the ur.txt sample, but inevitably increases once 
the massive amounts of data are imported from the other two city text files. Due to the raw mass 
if the monroe and nys data files, our algorithm has to essentially travel through all possible
paths, and thus increases dramatically.
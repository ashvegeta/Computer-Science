class GraphNode {
    int vertex;
    GraphNode next;

    GraphNode(int vertex) {
        this.vertex = vertex;
        this.next = null;
    }
}

class Graph {
    GraphNode graph[];
    int vertices;

    Graph(int vertices) {
        graph = new GraphNode[vertices];
        for (int i = 0; i < vertices; i++)
            graph[i] = new GraphNode(i);
    }

    void addEdge(int a, int b) {
        if (a == b) {
            System.out.println("Cant add adge between same nodes\n");
            return;
        }

        if (a < 0 || a >= graph.length) {
            System.out.println("vertex out of bounds\n");
            System.exit(-1);
        }

        GraphNode temp = graph[a];

        while (temp.next != null) {
            if (temp.next.vertex == b) {
                System.out.println("Edge " + a + "->" + b + " already exists");
                return;
            }

            temp = temp.next;
        }

        temp.next = new GraphNode(b);

    }
}

public class AdjacencyList {
    public static void main(String[] args) {
        Graph g = new Graph(5); // here paramter 0 suggests bi-directional graph

        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(2, 4);
        g.addEdge(0, 4);
    }
}

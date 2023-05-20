class Graph {
    boolean graph[][], bidirection;

    Graph(int vertices, boolean bidirection) {
        this.graph = new boolean[vertices][vertices];
        this.bidirection = bidirection;
    }

    void addEdge(int a, int b) {
        if (graph[a][b]) {
            if (bidirection)
                System.out.println("Edge " + a + "<->" + b + " already exists");
            else
                System.out.println("Edge " + a + "->" + b + " already exists");
            return;
        }

        graph[a][b] = true;
        if (bidirection)
            graph[b][a] = true;
    }
}

public class AdjacencyMatrix {
    public static void main(String[] args) {
        Graph g = new Graph(5, true); // here paramter 0 suggests bi-directional graph

        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(2, 4);
        g.addEdge(4, 0);
    }
}

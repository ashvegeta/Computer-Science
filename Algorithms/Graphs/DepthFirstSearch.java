class GraphNode {
    int vertex;
    GraphNode next;

    GraphNode(int vertex) {
        this.vertex = vertex;
        this.next = null;
    }
}

class Graph {
    int vertices;
    GraphNode graph[];

    Graph(int vertices) {
        graph = new GraphNode[vertices];

        for (int i = 0; i < vertices; i++)
            graph[i] = new GraphNode(i);

        this.vertices = vertices;
    }

    public void addEdge(int a, int b) {
        if (a == b) {
            System.out.println("Cant add edge between same nodes\n");
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
        System.out.println("Added Edge: " + a + "->" + b);
    }

    public GraphNode DFS(int vertex, int searchVal) {
        if (vertex < 0 || vertex >= graph.length) {
            System.out.println("\nVertex" + vertex + " out of bounds");
            return null;
        }

        return _DFS(vertex, searchVal, new boolean[this.vertices]);
    }

    GraphNode _DFS(int vertex, int searchVal, boolean[] visited) {
        if (vertex == searchVal) {
            System.out.println("Node " + searchVal + " found");
            return graph[vertex];
        }

        if (visited[vertex])
            return null;

        visited[vertex] = true;
        GraphNode node = null;

        while (graph[vertex].next != null) {
            node = _DFS(graph[vertex].next.vertex, searchVal, visited);
            if (node != null)
                break;
        }

        return node;
    }

}

public class DepthFirstSearch {
    public static void main(String[] args) {
        Graph g = new Graph(5);

        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(2, 4);
        g.addEdge(0, 4);

        GraphNode val = g.DFS(0, 4);

        if (val == null) {
            System.out.println("Element not found");
        }
    }
}
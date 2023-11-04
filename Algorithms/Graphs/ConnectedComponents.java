import java.util.HashSet;

class GraphNode {
    int vertex;
    GraphNode next;
    int id;

    GraphNode(int vertex) {
        this.vertex = vertex;
        this.next = null;
    }

    GraphNode(int vertex, int id) {
        this.id = id;
        this.vertex = vertex;
        this.next = null;
    }
}

class Graph {
    int vertices;
    GraphNode graph[];
    HashSet<Integer> IDset;

    Graph(int vertices) {
        graph = new GraphNode[vertices];
        IDset = new HashSet<>();

        for (int i = 0; i < vertices; i++)
            graph[i] = new GraphNode(i, 0);

        IDset.add(0);
        this.vertices = vertices;
    }

    Graph(int vertices, int[] ID_arr) {
        graph = new GraphNode[vertices];
        IDset = new HashSet<>();

        for (int i = 0; i < vertices; i++) {
            graph[i] = new GraphNode(i, ID_arr[i]);
            IDset.add(ID_arr[i]);
        }

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

        temp.next = new GraphNode(b, graph[b].id);
    }

    public void DFS(int vertex, boolean[] visited, int[] ConnectedComps) {
        if (vertex < 0 || vertex >= graph.length) {
            System.out.println("\nVertex" + vertex + " out of bounds");
            return;
        }

        _DFS(vertex, visited, ConnectedComps);
    }

    void _DFS(int vertex, boolean[] visited, int[] ConnectedComps) {
        visited[vertex] = true;
        ConnectedComps[graph[vertex].id]++;
        GraphNode temp = graph[vertex];

        while (temp.next != null) {
            if (!visited[temp.next.vertex])
                _DFS(temp.next.vertex, visited, ConnectedComps);
            temp = temp.next;
        }
    }

    int[] findConnectedComponents() {
        int[] ConnectedComps = new int[IDset.size()];
        boolean[] visited = new boolean[this.vertices];

        for (GraphNode node : graph) {
            if (!visited[node.vertex])
                DFS(node.vertex, visited, ConnectedComps);
        }

        return ConnectedComps;
    }

}

public class ConnectedComponents {
    public static void main(String[] args) {
        Graph g = new Graph(18, new int[] { 0, 1, 3, 3, 0, 1, 2, 2, 0, 3, 3, 2, 4, 0, 0, 3, 1, 1 });

        g.addEdge(0, 4);
        g.addEdge(0, 8);
        g.addEdge(0, 14);
        g.addEdge(0, 13);
        g.addEdge(4, 8);
        g.addEdge(8, 14);
        g.addEdge(13, 14);

        g.addEdge(1, 5);
        g.addEdge(5, 16);
        g.addEdge(5, 17);

        g.addEdge(6, 7);
        g.addEdge(6, 11);
        g.addEdge(7, 11);

        g.addEdge(2, 9);
        g.addEdge(2, 15);
        g.addEdge(3, 9);
        g.addEdge(9, 15);
        g.addEdge(10, 15);

        System.out.println("");

        int[] ConnectedComponents = g.findConnectedComponents();

        System.out.println("");

        for (int i = 0; i < g.IDset.size(); i++) {
            System.out.println("component ID: " + i + " ; Count: " + ConnectedComponents[i]);
        }
    }
}
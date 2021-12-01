import java.util.*;
  
class Graph_DFS {
    private int V; 
    private static LinkedList<Integer> adj[];
  
    Graph_DFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<Integer>();
    }
  
    void addEdge(int v, int w) {
        adj[v].add(w); 
    }
  
    static private void DFSUtil(int v, boolean visited[])
    {
        visited[v] = true;
        System.out.print(v + " ");
  
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) DFSUtil(n, visited);
        }
    }
  
    void DFS(int v) {
        DFSUtil(v, new boolean[V]);
    }
    
    public static void main(String args[])
    {
    	Graph_DFS g = new Graph_DFS(4);
  
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 1);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
   
        g.DFS(2);
    }
}

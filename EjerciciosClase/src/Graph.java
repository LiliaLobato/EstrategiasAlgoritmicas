import java.util.*;
  
public class Graph<T> {

  enum Color {
    White, Gray, Black;
  }

  private class Vertex {
    T object;
    List<Vertex> neighbors;
    Set<T> neighborSet;
    Color color = Color.White;
    Vertex parent = null;
    int distance;
    int dT, fT;
    
    public Vertex(T obj) {
      this.object = obj;
      this.neighbors   = new ArrayList<>();
      this.neighborSet = new HashSet<>();
    }
    // Si object = "AA", neighbors = ["BB", "CC"]
    // Se asumen las siguientes aristas: ("AA", "BB"), ("AA, "CC")

    // Formato: "B->[A,C,D]"
    public String toString() {
      // return this.object + "->" + this.neighborSet;
      //return String.format("<%s, %d, %s, %s>\n", object, distance, color, parent == null? "none" : parent.object);
      return String.format("<%s, %2d, %2d, %s, %s>\n", object, dT, fT, color, parent == null? "none" : parent.object);
    }
  }

  private Map<T, Vertex> vertexMap = new HashMap<>();

  // A --> B --> C --> D
  //         \-> E --> F 
  public boolean addVertex(T obj) {
    if (vertexMap.containsKey(obj)) {
      return false;
    }
    vertexMap.put(obj, new Vertex(obj));
    return true;
  }

  public boolean addEdge(T obj1, T obj2) {
    if (!vertexMap.containsKey(obj1) || !vertexMap.containsKey(obj2)) return false;

    Vertex obj1Vertex = vertexMap.get(obj1);
    Vertex obj2Vertex = vertexMap.get(obj2);

    if(obj1Vertex.neighborSet.contains(obj2)) return false;
    
    obj1Vertex.neighbors.add(obj2Vertex);
    obj2Vertex.neighbors.add(obj1Vertex);
    obj1Vertex.neighborSet.add(obj2);
    obj2Vertex.neighborSet.add(obj1);

    return true;
  }
  
  public boolean addArc(T obj1, T obj2){
    if (!vertexMap.containsKey(obj1) || !vertexMap.containsKey(obj2)) return false;

    Vertex obj1Vertex = vertexMap.get(obj1);
    Vertex obj2Vertex = vertexMap.get(obj2);

    if(obj1Vertex.neighborSet.contains(obj2)) return false;

    obj1Vertex.neighbors.add(obj2Vertex);
    obj1Vertex.neighborSet.add(obj2);

    return true;
  }

  public String toString() {
    String str = "";
    for(Vertex v : vertexMap.values()) {
      str += v + "";
    }
    return str;
  }

  private void visitAllFrom(Vertex vertex, Set<T> visited) {
		if(visited.contains(vertex.object)) return;
		System.out.println(vertex.object);
		visited.add(vertex.object);
		for(Vertex n : vertex.neighbors) {
			visitAllFrom(n, visited);
		}
	}
	
	public void visitAllFrom(T start) {
		if(!vertexMap.containsKey(start)) return;
		Set<T> visited = new HashSet<>();
		Vertex startVertex = vertexMap.get(start);
		visitAllFrom(startVertex, visited);		
	}

  public void BFS(T start) {
    if(!vertexMap.containsKey(start)) return;
    Vertex startVertex = vertexMap.get(start);
    // Setup all vertices
    for(Vertex vertex: vertexMap.values()) {
      vertex.color= Color.White;
      vertex.parent=null;
      vertex.distance=Integer.MAX_VALUE;
    }
    // Setup start vertex
    startVertex.color = Color.Gray;
    startVertex.distance = 0;
    startVertex.parent = null;
    
    Queue<Vertex> queue = new ArrayDeque<>();
    queue.offer(startVertex);
    
    // Iterative step - while the queue is not empty
    while(!queue.isEmpty()){
      Vertex u = queue.poll();
      for(Vertex v : u.neighbors){
        if(v.color == Color.White){
          v.distance = u.distance + 1;
          v.color = Color.Gray;
          v.parent = u;
          queue.offer(v);
        }
      }
      u.color = Color.Black;
    }
  }

  private int time = 0;

  private void DFS_visit(Vertex v) {
    time += 1;
    v.dT = time;
    v.color = Color.Gray;
    for (Vertex u : v.neighbors) {
      if (u.color == Color.White) {
        u.parent = v;
        DFS_visit(u);
      }
    }
    v.color = Color.Black;
    time += 1;
    v.fT = time;    
  }

  private void DFS_init() {
    time = 0;
    for(Vertex vertex: vertexMap.values()) {
      vertex.color = Color.White;
      vertex.parent = null;
      vertex.dT = Integer.MAX_VALUE;
      vertex.fT = Integer.MAX_VALUE;
    }
  }

  public void DFS() {
    DFS_init();    
    for (Vertex v : vertexMap.values()) {
      if (v.color == Color.White) {
        DFS_visit(v);
      }
    }
  }
  
  public void DFS(T start) {
    if(!vertexMap.containsKey(start)) return;
    DFS_init();
    Vertex startVertex = vertexMap.get(start);
    DFS_visit(startVertex);
  }

  public static void main(String[] args) {
    Graph<Character> graph1 = new Graph<>();
    graph1.addVertex('A');
    graph1.addVertex('B');
    graph1.addVertex('C');
    graph1.addVertex('D');
    graph1.addVertex('E');
    graph1.addVertex('F');
    graph1.addVertex('G');
    graph1.addVertex('H');
    graph1.addArc('A', 'B');
    graph1.addArc('A', 'D');
    graph1.addArc('B', 'E');
    graph1.addArc('D', 'B');
    graph1.addArc('D', 'G');
    graph1.addArc('E', 'C');
    graph1.addArc('E', 'D');
    graph1.addArc('F', 'D');
    graph1.addArc('F', 'G');
    graph1.addArc('F', 'H');
    graph1.addArc('G', 'A');
    graph1.addArc('H', 'E');
    graph1.DFS();
    System.out.println(graph1);
    graph1.DFS('A');
    System.out.println(graph1);
  }

	public static void main2(String[] args) {
    Graph<Character> graph1 = new Graph<>();
    Graph<String>    graph2 = new Graph<>();
    Graph<Integer>   graph3 = new Graph<>();

    graph1.addVertex('A');
    graph1.addVertex('B');
    graph1.addVertex('C');
    graph1.addVertex('D');
    graph1.addVertex('E');
    graph1.addVertex('F');
    graph1.addVertex('G');
    graph1.addVertex('H');
    graph1.addVertex('I');
    graph1.addVertex('J');

    graph1.addEdge('A', 'B');
    graph1.addEdge('A', 'C');
    graph1.addEdge('A', 'D');
    graph1.addEdge('B', 'C');
    graph1.addEdge('B', 'E');
    graph1.addEdge('C', 'D');
    graph1.addEdge('C', 'E');
    graph1.addEdge('C', 'F');
    graph1.addEdge('D', 'F');
    graph1.addEdge('E', 'G');
    graph1.addEdge('E', 'H');
    graph1.addEdge('F', 'H');
    graph1.addEdge('I', 'J');
    
    graph1.BFS('A');
    System.out.println(graph1);
    // A->[B], B->[A,C,D], C->[B], D->[B]

    /*graph2.addVertex("AA");
    graph2.addVertex("BB");
    graph2.addVertex("CC");
    graph2.addVertex("DD");
    graph2.addArc("AA", "BB");
    graph2.addArc("BB", "CC");
    graph2.addArc("BB", "DD");
    System.out.println(graph2);
    // AA->[BB], BB->[CC,DD], CC->[], DD->[]

    for(int i = 1; i <= 10; i ++) graph3.addVertex(i);
		graph3.addArc(1, 3);  graph3.addArc(3, 5); graph3.addArc(5, 7); graph3.addArc(7,  9);
		graph3.addArc(2, 4);  graph3.addArc(4, 6); graph3.addArc(6, 8); graph3.addArc(8, 10);
		graph3.addArc(1, 10); graph3.addArc(2, 9);
		graph3.addArc(5, 1);  graph3.addArc(6, 2);
		
		graph3.visitAllFrom(1);  // Imprimir: 1, 3, 5, 7, 9, 10
		System.out.println();
		graph3.visitAllFrom(2);  // Imprimir: 2, 4, 6, 8, 9, 10

    System.out.println(graph3);*/
	}

}

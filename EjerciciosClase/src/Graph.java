import java.util.*;

public class Graph<T> {

	private class Vertex <T extends Comparable<T>>{
		T object;
		List<Vertex> neighbors;
		Set<T> neighborSet;

		public Vertex(T object) {
			this.object = object;
			this.neighbors = new ArrayList<>();
			this.neighborSet = new HashSet<>();
		}
		
		public String toString() {
			return this.object + "->" + this.neighborSet + "\n";
		}

	}
	
	private class Edge implements Comparable<Edge>{
		double peso;
		T from = null;
		T to = null;

		public Edge(T obj1, T obj2, double weight) {

			int res = ((Comparable) obj1).compareTo( obj2);
			if(res < 0) {
				this.from = obj1;
				this.to = obj2;
			} else {
				this.from = obj2;
				this.to = obj1;
			}
			this.peso = weight;
		}
		
		public String toString() {
			return "<" + this.from + ", " + this.to + ", " + this.peso + "> ";
		}

		public int compareTo(Edge v) {
			if(this.peso < v.peso) return -1;
			if(this.peso > v.peso) return  1;
			return 0;
		}
		
	}
	
	public class CustomComparator implements Comparator<Edge> {
	    @Override
	    public int compare(Edge o1, Edge o2) {
	        return o1.compareTo(o2);
	    }
	}

	private Map<T, Vertex> vertexMap = new HashMap<>();
    List<Edge> edgeList = new ArrayList<>();

	public boolean addVertex(T obj) {
		if (vertexMap.containsKey(obj)) return false;
		vertexMap.put(obj, new Vertex((Comparable) obj));
		return true;
	}

	public boolean addEdge(T obj1, T obj2, double weight) {
		if (!vertexMap.containsKey(obj1) || !vertexMap.containsKey(obj2)) return false;
		Vertex vertex1 = vertexMap.get(obj1);
		Vertex vertex2 = vertexMap.get(obj2);
		if (vertex1.neighborSet.contains(obj2)) return false;
		vertex1.neighbors.add(vertex2);
		vertex2.neighbors.add(vertex1);
		vertex1.neighborSet.add(obj2);
		vertex2.neighborSet.add(obj1);
		edgeList.add(new Edge(obj1, obj2, weight));
		return true;
	}

	public String toString() {
		String str = "";
		for (Vertex v : vertexMap.values()) {
			str += v + "";
		}
		return str;
	}
	
	public void getedgeList() {
		System.out.println(edgeList);
	}
		
	List<Edge> E = new ArrayList<>();
	ArrayList<ArrayList<Vertex> > F = new ArrayList<ArrayList<Vertex>>(); //<- tiene todas las aristas del grafo
	
	public List<Edge> kruskal(){
		
	    List<Edge> A = new ArrayList<>();
	    ArrayList<Vertex> Ftemp = new ArrayList<Vertex>();

		for (Vertex v : vertexMap.values()) {
			Ftemp.clear();
			Ftemp.add(v);
			F.add( (ArrayList<Graph.Vertex>) Ftemp.clone());
			for (Edge e : edgeList) {
				if(v.object == e.from) {
					E.add(e);
				}
			}
		}

		//Tenemos que ordenar el edgeList
		Collections.sort(edgeList, new CustomComparator());
		
		//recorremos para encontrar la unión mas pequeña
		while(F.size() > 1) {
			Edge min = E.get(0);
			for (Edge e : E) {
				if(e.peso < min.peso) {
					min = e;
				}
			}
			//sacamos de F min.from 
			E.remove(min);
			if(union(min.from, min.to)) {
				A.add(min);
			}
		}

		return A;
	}
	
	private boolean union(T from, T to) {
		//revisa que los set retornados no sean los mismos
		if(findSet(to) != findSet(from)) {
			List<Vertex> unionList = new ArrayList<Vertex>(findSet(from));
			unionList.addAll(findSet(to));
			F.remove(findSet(from));
			F.remove(findSet(to));
			F.add( (ArrayList<Graph.Vertex>) ((ArrayList<Graph.Vertex>) unionList).clone());
			return true;
		}
		return false;		
	}

	private List<Graph.Vertex> findSet(T from) {
		//retorna donde está este index
		for(List<Vertex> innerList : F) {
		    for(Vertex v : innerList) {
		        if(v.object == from) {
		        	return innerList;
		        }
		    }
		}
		return null;
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
	    graph1.addVertex('I');
	    graph1.addEdge('B', 'A', 4);
	    graph1.addEdge('A', 'H', 8);
	    graph1.addEdge('B', 'C', 8);
	    graph1.addEdge('B', 'H', 11);
	    graph1.addEdge('C', 'F', 4);
	    graph1.addEdge('C', 'D', 7);
	    graph1.addEdge('D', 'E', 9);
	    graph1.addEdge('D', 'F', 14);
	    graph1.addEdge('E', 'F', 10);
	    graph1.addEdge('G', 'I', 6);
	    graph1.addEdge('G', 'F', 2);
	    graph1.addEdge('G', 'H', 1);
	    graph1.addEdge('H', 'I', 7);
	    graph1.addEdge('I', 'C', 2);
	    System.out.println(graph1);
	    System.out.println(graph1.kruskal());
	}


}

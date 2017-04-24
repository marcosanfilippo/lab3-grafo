package p3.b;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import graph.real.*;

public class Main {

	public static void main(String[] args) {
		
		Graph g = null;
		//g = Loader.getGraphDB(g);
		
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		
		LinkedList<Edge> edges = new LinkedList<Edge>();
		Map<String, Node> nodes = new HashMap<String, Node>();
		
	    Edge ab = new Edge("B", 6);
	    Edge ad = new Edge("D", 1);
	    edges.add(ab);
		edges.add(ad);
	    a.setAdjList(edges);
	    edges.clear();
	    
	    Edge ba = new Edge("A", 6);
	    Edge bd = new Edge("D", 2);
	    Edge be = new Edge("E", 2);
	    Edge bc = new Edge("A", 5);
	    edges.add(ba);
	    edges.add(bd);
	    edges.add(be);
	    edges.add(bc);
	    b.setAdjList(edges);
	    edges.clear();
	    
	    Edge ed = new Edge("D",1);
	    Edge eb = new Edge("B",2);
	    Edge ec = new Edge("C",5);
	    edges.add(ed);
	    edges.add(eb);
	    edges.add(ec);
	    e.setAdjList(edges);
	    
	    nodes.put("A", a);
	    nodes.put("B", b);
	    nodes.put("C", c);
	    nodes.put("D", d);
	    nodes.put("E", e);
	    
		g = new Graph(nodes);
		
		DijkstraAlgorithm da = new DijkstraAlgorithm(g, "A");
		List<Node> traversedNodes = da.getPathTo("E");
		System.out.println("A->E");
		System.out.println(da.getDistanceTo("E"));
		for(Node n : traversedNodes)
			System.out.println(n.getId());
		
		
		System.out.println("***");
		System.out.println(g.getAllNodes().size());
		
		
//		for(Node n : g.getAllNodes()){
//			System.out.println("Node: " + n.getId() + " can reach:");
//			for(Edge e : n.getAdjList())
//				System.out.println("    DST: " + e.getDst() /*+ ", cost:"*/);
//		}
		
		
	}
	
}

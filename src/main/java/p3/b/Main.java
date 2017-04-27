package p3.b;

import graph.real.*;

public class Main {

	public static void main(String[] args) {
		
		Graph g = null;
		g = Loader.getGraphDB(g);

		System.out.println("***");
		System.out.println(g.getAllNodes().size());
		
		
//		for(Node n : g.getAllNodes()){
//			System.out.println("Node: " + n.getId() + " can reach:");
//			for(Edge e : n.getAdjList())
//				System.out.println("    DST: " + e.getDst() /*+ ", cost:"*/);
//		}
		
		
	}
}

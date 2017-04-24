package graph.real;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	private final List<Node> allNodes;
	
	public Graph(){
		allNodes = new ArrayList<Node>();
	}
	
	public Graph(List<Node> nodeList){
		allNodes = nodeList;
	}

	public List<Node> getAllNodes() {
		return allNodes;
	}
	
}

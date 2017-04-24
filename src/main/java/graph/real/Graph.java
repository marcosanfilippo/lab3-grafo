package graph.real;

import java.util.HashMap;
import java.util.Map;

public class Graph {
	
	public Map<String, Node> allNodes;

	public Graph(){
		allNodes = new HashMap<String,Node>();
	}
	
	public Graph(Map<String,Node> nodeList){
		allNodes = nodeList;
	}

	public Map<String,Node> getAllNodes() {
		return allNodes;
	}
	
	public Node getNode(String id) {
		return allNodes.get(id);
	}
}

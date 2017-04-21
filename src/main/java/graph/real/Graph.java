package graph.real;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	
	public List<String> allNodes;
	
	public Graph(){
		allNodes = new ArrayList<String>();
	}
	
	public Graph(ArrayList<String> a){
		allNodes = a;
	}

}

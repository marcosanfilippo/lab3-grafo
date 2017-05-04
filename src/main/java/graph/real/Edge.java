package graph.real;

public class Edge {
	
	private String dst; 
	private String line;	//line == null indicates a near stop (250 meters) belonging to another line
	private Double cost;

	public Edge(String dst) {
		this.dst = dst;
	}
	
	public Edge(String dst, String line) {
		this.dst = dst;
		this.line = line;
	}
	
	public Edge(String dst, String line, Double cost) {
		this.dst = dst;
		this.line = line;
		this.cost = cost;
	}
	
	public Edge(String dst, Double cost) {
		this.dst = dst;
		this.cost = cost;
	}
	

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double c) {
		this.cost = c;
	}
	
	public String getLine() {
		return line;
	}
	
	public void setLine(String line) {
		this.line = line;
	}

}

package graph.real;

public class Edge {
	
	private String dts;
	private int cost;

	public Edge(String dts) {
		this.dts = dts;
		this.cost = 1;
	}
	
	public Edge(String dts, int cost) {
		this.dts = dts;
		this.cost = cost;
	}

	public String getDts() {
		return dts;
	}

	public void setDts(String dts) {
		this.dts = dts;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int c) {
		this.cost = c;
	}

}

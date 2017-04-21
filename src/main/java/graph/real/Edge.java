package graph.real;

public class Edge {
	
	private String src, dts;
	private int cost;

	public Edge(String src, String dts) {
		//TODO remove me
		this.src = src;
		this.dts = dts;
		this.cost = 1;
	}
	
	public Edge(String src, String dts, int cost) {
		this.src = src;
		this.dts = dts;
		this.cost = cost;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
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

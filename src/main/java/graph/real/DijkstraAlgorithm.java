package graph.real;

import java.util.*;


public class DijkstraAlgorithm {
    
    private Graph graph;
    private String sourceNodeId;
    private HashMap<String, String> predecessors;
    private HashMap<String, Integer> distances; 
    private PriorityQueue<Node> availableNodes;
    private HashSet<Node> visitedNodes; 
    
    /**
     * This constructor initializes this Dijkstra object and executes
     * Dijkstra's algorithm on the graph given the specified sourceNodeId.
     * After the algorithm terminates, the shortest a-b paths and the corresponding
     * distances will be available for all nodes b in the graph.
     * 
     * @param graph The Graph to traverse
     * @param sourceNodeId The source node id
     * @throws IllegalArgumentException If the specified initial node is not in the Graph
     */
    public DijkstraAlgorithm(Graph graph, String sourceNodeId){
        this.graph = graph;
        Set<String> nodeIds = this.graph.getAllNodes().keySet();

        if(!nodeIds.contains(sourceNodeId)){
            throw new IllegalArgumentException("The graph doesn't contain the source node.");
        }
        
        this.sourceNodeId = sourceNodeId;
        this.predecessors = new HashMap<String, String>();
        this.distances = new HashMap<String, Integer>();
        this.availableNodes = new PriorityQueue<Node>(nodeIds.size(), new Comparator<Node>(){
        	
            public int compare(Node one, Node two){
                int weightOne =  DijkstraAlgorithm.this.distances.get(one.getId());
                int weightTwo = DijkstraAlgorithm.this.distances.get(two.getId());
                return weightOne - weightTwo;
            }
        });
        
        this.visitedNodes = new HashSet<Node>();
        
        //for each node in the graph
        //assume it has distance infinity denoted by Integer.MAX_VALUE
        for(String nId : nodeIds){
            this.predecessors.put(nId, null);
            this.distances.put(nId, Integer.MAX_VALUE);
        }
        
        //the distance from the source node to itself is 0
        this.distances.put(sourceNodeId, 0);
        
        Node  sourceNode = this.graph.getNode(sourceNodeId);
        List<Edge> sourceNodeNeighbors = sourceNode.getAdjList();
        System.out.println(sourceNodeNeighbors.size());
        System.out.println(sourceNode.getId());
        for(Edge e : sourceNodeNeighbors){
        	
            Node other = this.graph.getNode(e.getDst()); 
            this.predecessors.put(other.getId(), sourceNodeId);
            this.distances.put(other.getId(), e.getCost());
            this.availableNodes.add(other);
        }
        
        this.visitedNodes.add(sourceNode);
        
        //now apply Dijkstra's algorithm to the Graph
        processGraph();
        
    }
    
    /**
     * This method applies Dijkstra's algorithm to the graph using the Node
     * specified by sourceNodeId as the starting point.
     * 
     * @post The shortest a-b paths as specified by Dijkstra's algorithm and 
     *       their distances are available 
     */
    private void processGraph(){
        
        //as long as there are Edges to process
        while(this.availableNodes.size() > 0){
            
            //pick the cheapest vertex
            Node next = this.availableNodes.poll();
            int distanceToNext = this.distances.get(next.getId());
            
            //and for each available neighbor of the chosen vertex
            List<Edge> nextNeighbors = next.getAdjList();     
            for(Edge e : nextNeighbors){
                Node other = this.graph.getNode(e.getDst());
                if(this.visitedNodes.contains(other)){
                    continue;
                }
                
                //we check if a shorter path exists
                //and update to indicate a new shortest found path
                //in the graph
                int currentWeight = this.distances.get(other.getId());
                int newWeight = distanceToNext + e.getCost();
                
                if(newWeight < currentWeight){
                    this.predecessors.put(other.getId(), next.getId());
                    this.distances.put(other.getId(), newWeight);
                    this.availableNodes.remove(other);
                    this.availableNodes.add(other);
                }
                
            }
            
            // finally, mark the selected node as visited 
            // so we don't revisit it
            this.visitedNodes.add(next);
        }
    }
    
    
    /**
     * 
     * @param destinationId The node whose shortest path from the initial node is desired
     * @return LinkedList<Node> A sequence of Node objects starting at the 
     *         initial node and terminating at the node specified by destinationId.
     *         The path is the shortest path specified by Dijkstra's algorithm.
     */
    public List<Node> getPathTo(String destinationId){
        LinkedList<Node> path = new LinkedList<Node>();
        path.add(graph.getNode(destinationId));
        
        while(!destinationId.equals(this.sourceNodeId)){
            Node predecessor = graph.getNode(this.predecessors.get(destinationId));
            destinationId = predecessor.getId();
            path.add(0, predecessor);
        }
        return path;
    }
    
    
    /**
     * 
     * @param destinationId The node to determine the distance from the initial one
     * @return int The distance from the initial node to the node specified by destinationId
     */
    public int getDistanceTo(String destinationId){
        return this.distances.get(destinationId);
    }
       
 }

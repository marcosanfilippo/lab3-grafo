package p3.b;

import java.sql.*;
import java.util.*;

import org.bson.Document;

import graph.real.*;
import maps.route.MinPath;
import maps.route.MinPathEdge;
import mongo.real.MinPathCollection;

public class Loader {
    
    public static Graph getGraphDB (Graph g) {
    	return parseFromDB(g);
    }
    
    private static Graph parseFromDB(Graph g){
    	List<Node> nodeList = new ArrayList<Node>();
    	String connectionUrl = "jdbc:postgresql://localhost:5432/trasporti";
    	try {
    		Class.forName("org.postgresql.Driver");
    		Connection conn = DriverManager.getConnection(connectionUrl,"postgres","ai-user-password"); 
    		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    		 
    		ResultSet nodeRS = stmt.executeQuery("SELECT id, ST_AsText(latlng) FROM BusStop ORDER BY id");
    		while(nodeRS.next())
        		nodeList.add(new Node(nodeRS.getString(1), nodeRS.getString(2))); // starts from 1, not 0.
    		
    		//ResultSet stopRS = stmt.executeQuery("SELECT stopid, lineid, sequencenumber "
    			//+"FROM BusLineStop ORDER BY stopid, lineid, sequencenumber");
    		ResultSet sequenceRS = stmt.executeQuery("SELECT lineid, sequencenumber, stopid "
    			+"FROM BusLineStop ORDER BY lineid, sequencenumber, stopid");
    
    		//TODO
    		//Siamo sicuri che questo esplora tutto lo spazio delle soluzioni?
    		// Data una fermata F
    		//		A) Creo un arco verso ogni fermata vicina (1 Hop) raggiungibile tramite un BUS
    		//		B) Creo un arco verso ogni fermata raggiungibile a piedi ( distanza <= 250 )
    		
    		while(sequenceRS.next()){ //Per ogni BusLineStop
    			for(Node n : nodeList){ //Per ogni fermata (??)
					if(n.getId().equals(sequenceRS.getString("stopid"))) //Se L'id della fermata coincide con l'id di BusLineStop
					{
						String line = sequenceRS.getString("lineid");
						String stopId = sequenceRS.getString("stopid");
						
						//Se c'è un successivo
						// la sua adiacenza è il nodo che segue sulla stessa linea, con sequence number più grande (sono già ordinati per sequenceNumber)
						if(sequenceRS.next()){
							String nextLine = sequenceRS.getString("lineid");
							String nextStop = sequenceRS.getString("stopid");
							int cost = 0;
							//Se sono sulla stessa linea
							if(line.compareToIgnoreCase(nextLine)==0){
								//can add its next, because the bus line is the same
								System.out.println("Linea: " + line + ", " + n.getId() + " -> " + nextStop + ", ");
								
								//adding Edge(stopID, lineID) --> Edge(destination, line);
								n.getAdjList().add(new Edge(nextStop, nextLine,cost));
							//Altrimenti verifico se la fermata successiva in BusLineStop (?) è nel raggio di 250m
							} else { //check if the distance between nodes is less than 250 m
								Statement stmt1 = conn.createStatement();
								ResultSet temp = stmt1.executeQuery("SELECT id,ST_Distance(latlng,ST_GeographyFromText('"+ n.getLatLng() +"')) as dista FROM BusStop WHERE id = '"+nextStop+"'");
								if ( temp.next() )
								{
									Double distanza = temp.getDouble("dista");
									if ( distanza <= 250 )
									{
										n.getAdjList().add(new Edge(nextStop,null,cost));
									}
								}
								temp.close();
								stmt1.close();
							}
							if(!sequenceRS.isLast())
								sequenceRS.previous(); //return to the previous line to process its node!
							break;
						}	
					}
				}
    		}
    		
    		/*
    		//TODO SLOW: compattabile in una sola query forse?
			for(Node n : nodeList)
			{ //Per ogni fermata
				//Altrimenti verifico se la fermata successiva in BusLineStop (perchè?) è nel raggio di 250m
				Statement stmt1 = conn.createStatement();
				ResultSet temp = stmt1.executeQuery("SELECT id,ST_Distance_Sphere('"+ n.getLatLng() +"')) as dista FROM BusStop WHERE id = '"+nextStop+"' AND ST_Distance_Sphere('"+ n.getLatLng() +"') <= 250 ");
				while ( temp.next() )
				{
					Double distanza = temp.getDouble("dista");
					String stopId = temp.getString("id");
					Integer cost = 0;
					n.getAdjList().add(new Edge(stopId,null,cost));
				}
			}
    		*/
    		
    		Map<String,Node> nodeMap = new HashMap<String,Node>();
    		for(Node n:nodeList) nodeMap.put(n.getId(), n);
    		g = new Graph(nodeMap);
    		
			MinPathCollection mpc = new MinPathCollection("percorsi");
			mpc.drop("percorsi");
			
			int i = 0 ;
			List<Document> ld = new ArrayList<Document>();
    		for(Node sourceNode : nodeList)
    		{
    			String source = sourceNode.getId();
    	        long timestampStart = System.nanoTime();

    			System.out.println("Init dijk from "+source);
    			DijkstraAlgorithm djsa = new DijkstraAlgorithm(g,source);
    			
    			
    			
    			for(Node destinationNode : nodeList)
    			{
    				String destination = destinationNode.getId();
    				if ( source.equals(destination) )
    				{
    					continue;
    				}
    				//System.out.println("From "+source+" To:"+destination);
   
        			List<Node> listNode = djsa.getPathTo(destination);
        			
        			if ( listNode.size() == 0 )
        			{
        				throw new Exception("Unable to find Path from "+source+" to "+destination);
        			}
        			
        			//TODO
        			//List<Edge> listEdge = djsa.getPathTo(destination);
        			
    				ArrayList<MinPathEdge> edges = new ArrayList<MinPathEdge>();
    				
    				for(Node n : listNode)
    				{
    					//TODO costo e linea
    					edges.add(new MinPathEdge(n.getId(),null, 0));
    				}
    				
    				/*
    				for(Edge e : listEdge)
    				{
    					edges.add(new MinPathEdge(e.getDst(),e.getLine(), e.getCost));
    				}
    				*/
        			
        			MinPath mp = new MinPath();
        			
        			mp.setIdSource(source);
        			mp.setIdDestination(destination);
        			mp.setEdges(edges);
        	
        			//mpc.addDocument("percorsi", mp.getMongoDbDocument());
        			ld.add(mp.getMongoDbDocument());
    			}
    			if ( ld.size() > 10000) //Fast way
    			{
        	        System.out.println("Dump...");

	    			mpc.addDocument("percorsi", ld);
	    			ld.clear();
	    			
        	        long timestampFinish = System.nanoTime();
        	        
        	        double timeStep = (timestampFinish -timestampStart)/1000/1000;
        	        double timeLeft = timeStep*(nodeList.size()-i)/1000/60;
        	        
        	        
        	        System.out.println("Estimated time to finish (step: "+timeStep+" msec): "+((int)timeLeft)+" minutes ( "+ ((int)timeLeft/60)+" hours). Stop left:"+(nodeList.size()-i));
    			}
    			
    	        
    	        i++;
    		}
    		if ( ld.size() > 0 )
    		{
    			mpc.addDocument("percorsi", ld);
    			ld.clear();
    		}
    	} catch (Exception e) {    		
    		e.printStackTrace();
    		System.exit(1);
    	}
    	
    	return g;
    }
}

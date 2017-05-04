package graph.real;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CacheManager {

	HashMap<String,HashMap<String,List<Node>>> cache;
	
	public CacheManager()
	{
		cache = new HashMap<String,HashMap<String,List<Node>>>();
	}
	
	public void addInsideCache(List<Node> origPath)
	{
		for(int i=0 ; i < origPath.size(); i++ )
		{
			HashMap<String,List<Node>> intMap = new HashMap<String,List<Node>>();

			for ( int j=i+1 ; j < origPath.size(); j++)
			{
				ArrayList<Node> path = new ArrayList<Node>();
				path.add(origPath.get(j));

				for ( int k = i+1 ; k < j; k++)
				{
					path.add(origPath.get(k));
				}
				
				intMap.put(origPath.get(j).getId(), path);
			}
			
			cache.put(origPath.get(i).getId(), intMap);
		}
	}
	
	public List<Node> pathTo(String srcNodeId, String dstNodeId)
	{
		HashMap<String,List<Node>> map = cache.get(srcNodeId);
		
		if ( map != null )
		{
			return map.get(dstNodeId);
		}
		return null;
	}
}

import java.util.*;


public class Vertex 
{
	int id;	//dans [0, N-1]
	double lat; //latitude
	double lon;	//longitude
	int N; //nombre total d'intersections
	List<Edge> eAccess; //liste des arêtes accessibles
	List<Vertex> vAccess; //liste des noeuds accessibles
	int visite;
	
	public Vertex(int i, double la, double lo)
	{
		id=i;
		lat=la;
		lon=lo;
		eAccess=new ArrayList<Edge>();
		vAccess=new ArrayList<Vertex>();
		visite=0;
	}
	
	public void addVoisin(Edge e, Vertex v)
	{
		eAccess.add(e);
		vAccess.add(v);
	}
	
	public String toString()
	{
		String s=Integer.toString(id);
		return s;
	}
	
	
	
	
	
	
}

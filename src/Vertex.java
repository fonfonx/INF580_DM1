
public class Vertex 
{
	int id;	//dans [0, N-1]
	double lat; //latitude
	double lon;	//longitude
	int N; //nombre total d'intersections
	Edge[] eAccess; //tableau des arêtes accessibles
	Vertex[] vAccess; //tableau des noeuds accessibles
	
	public Vertex(int i, double la, double lo)
	{
		id=i;
		lat=la;
		lon=lo;
	}
	
	public void setVoisinsDim(int k)
	{
		eAccess=new Edge[k];
		vAccess=new Vertex[k];
	}
	
	
	
	
}

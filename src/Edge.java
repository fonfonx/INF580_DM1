
public class Edge 
{
	int id; //dans [0,N-1]
	int N; //nombre total d'arêtes
	Vertex A; //noeud de départ
	Vertex B; //noeud d'arrivée
	boolean DS; //double sens, si 0 arête de A vers B
	int cout; //coût en s
	int dist; //distance en m
	boolean visite; //si on est déjà passé par cette rue
	boolean virtualVisite;
	
	//constructeur
	public Edge(int i, Vertex AA, Vertex BB, boolean ds, int c, int d)
	{
		id=i;
		A=AA;
		B=BB;
		DS=ds;
		cout=c;
		dist=d;
		visite=false;
		virtualVisite=false;
	}
	
	public boolean visited()
	{
		return visite;
	}
	
	public String toString()
	{
		return Integer.toString(id);
	}
}
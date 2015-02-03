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
	
	//pour la méthode moins naïve
	int voitures;
	int[] soustab;
	int[] indices; //contient l'indice du croisement dans le nouveau fichier paris
	
	
	public Vertex(int i, double la, double lo)
	{
		id=i;
		lat=la;
		lon=lo;
		eAccess=new ArrayList<Edge>();
		vAccess=new ArrayList<Vertex>();
		visite=0;
		voitures=8;
		soustab=new int[voitures];
		indices=new int[voitures];
		
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
	
	public int nbLibres()
	{
		int rep=0;
		for (int i=0; i<eAccess.size(); i++)
		{
			if (!(eAccess.get(i).visite||eAccess.get(i).virtualVisite))
			{
				rep++;
			}
		}
		return rep;
	}
	
	public float fracLibres()
	{
		return (float)nbLibres()/eAccess.size();
	}
	
	//pour la partie moins naïve
	public void setSousGraphe(int i)
	{
		soustab[i]++;
	}
	
	public void setAllSousGraphes()
	
	{
		for (int i=0; i<voitures; i++)
		{
			setSousGraphe(i);
		}
	}
	
	public boolean isInSousGraphe(int i)
	{
		return soustab[i]>=1;
	}
	
	
	
	
	
	
}

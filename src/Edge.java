
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
	int nbVisites;
	
	//pour la méthode moins naïve
	int voitures;
	boolean[] soustab;
	boolean place; //pour savoir si on a placé l'arête dans un sous-graphe
	
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
		nbVisites=0;
		
		voitures=8;
		place=false;
		soustab=new boolean[voitures];
		for (int k=0; k<voitures; k++)
		{
			soustab[k]=false;
		}
	}
	
	public boolean visited()
	{
		return visite;
	}
	
	public String toString()
	{
		return Integer.toString(id);
	}
	
	public Vertex autreExt(Vertex v) throws Exception
	{
		if (A==v)
		{
			return B;
		}
		if (B==v)
		{
			return A;
		}
		else
		{
			throw new Exception("non");
		}
	}
	
	public boolean putInSousGraphe(int i)
	{
		if (A.isInSousGraphe(i) && B.isInSousGraphe(i))
		{
			soustab[i]=true;
			place=true;
			return true;
		}
		return false;
	}
	
	public boolean isInSousGraphe(int i)
	{
		return soustab[i];
	}
}
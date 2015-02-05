import java.util.Comparator;


public class Edge implements Comparable<Edge>
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
	
	public void place(int i)
	{
		soustab[i]=true;
		A.setSousGraphe(i);
		B.setSousGraphe(i);
		place=true;
	}
	
	public int traitement(int[] choix, int d, int[] cout)
	{
		int min=2000000;
		int coul=10;
		for (int k=0; k<choix.length; k++)
		{
			if (choix[k]==d && cout[k]<min)
			{
				min=cout[k];
				coul=k;
			}
		}
		place(coul);
		return coul;
	}
	
	public int sensunique(int coul)
	{
		if (DS)
		{
			return 0;
		}
		boolean pa=false;
		boolean pb=false;
		boolean pbb=false;
		boolean paa=false;
		for (int i=0; i<B.eAccess.size();i++)
		{
			if (/*B.eAccess.get(i).DS&&*/ (!B.eAccess.get(i).place&& B.eAccess.get(i).id!=id))
			{
				B.eAccess.get(i).place(coul);
				pb=true;
				break;
				//if (!B.eAccess.get(i).DS) {B.eAccess.get(i).sensunique(coul);}
			}
			if (B.eAccess.get(i).isInSousGraphe(coul)) {pbb=true;}
		}
		
		for (int i=0; i<A.eAccess.size();i++)
		{
			if (A.eAccess.get(i).DS&& (!A.eAccess.get(i).place) && A.eAccess.get(i).id!=id)
			{
				A.eAccess.get(i).place(coul);
				pa=true;
				break;
			}
			if (A.eAccess.get(i).isInSousGraphe(coul)) {paa=true;}
		}
		
		int a=pa?1:0;
		int b=pb?1:0;
		if (!((pa||paa)&&(pb||pbb))) {System.out.println("raté!"+a+b);} //else {System.out.println("réussi!");}
		return a+b;
	}

	/*@Override
	public int compare(Edge arg0, Edge arg1) {
		// TODO Auto-generated method stub
		return arg0.dist-arg1.dist;
	}*/

	@Override
	public int compareTo(Edge arg0) {
		// TODO Auto-generated method stub
		if (DS&&arg0.DS || ((!DS)&&(!arg0.DS)))
		{
			return cout-arg0.cout;
		}
		else
		{
			return (!DS?1:-1);
		}
		//return cout-arg0.cout;
		//return 0;
	}
}
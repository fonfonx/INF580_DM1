import java.io.*;


public class Graph 
{
	Vertex[] V;
	Edge[] E;
	int N; //nombre d'intersections
	int M; //nombre de rues
	int T; //temps total
	int C; //nombre de véhicules
	int S; //indice du départ
	
	public static int INT(String s)
	{
		return Integer.parseInt(s);
	}
	
	public static int[] INT(String[] tab)
	{
		int l=tab.length;
		int rep[]=new int[l];
		for (int i=0; i<l; i++)
		{
			rep[i]=INT(tab[i]);
		}
		return rep;
	}
	
	public static double DBL(String s)
	{
		return Double.parseDouble(s);
	}
	
	public static double[] DBL(String[] tab)
	{
		int l=tab.length;
		double rep[]=new double[l];
		for (int i=0; i<l; i++)
		{
			rep[i]=DBL(tab[i]);
		}
		return rep;
	}
	
	public void setDim(int n, int m, int t, int c, int s)
	{
		N=n;
		M=m;
		T=t;
		C=c;
		S=s;
		V=new Vertex[n];
		E=new Edge[m];
	}
	
	public void setDim(String[] tabinits)
	{
		if (tabinits.length==5)
		{
			int[] tabinit=INT(tabinits);
			setDim(tabinit[0], tabinit[1], tabinit[2], tabinit[3], tabinit[4]);
		}
		else
		{
			System.out.println("erreur d'init");
		}
	}
	
	public void init(String fichier)
	{
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String line=br.readLine();
			String[] tabinit=line.split(" ");
			setDim(tabinit);
			int k=0;
			double[] lv; //tableau pour les lignes représentant des vertex
			int[] le; //tableau pour les lignes représentant des edges
			while ((line=br.readLine())!=null)
			{
				k++;
				if (k<=N)
				{
					lv=DBL(line.split(" "));
					Vertex v=new Vertex(k-1, lv[0], lv[1]);
					V[k-1]=v;
				}
				else
				{
					le=INT(line.split(" "));
					Edge e=new Edge(k-1-N, V[le[0]], V[le[1]], (le[2]==2), le[3], le[4]);
					E[k-1-N]=e;
					
				}
				
			}
			br.close(); 
		}
		catch(IOException e){
			System.out.println("Erreur d'initialisation du graphe!");
			System.out.println(e.getMessage());
		}
		
	}
}

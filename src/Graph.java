import java.io.*;
import java.util.*;


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
					//ajout des voisins
					V[le[0]].addVoisin(e, V[le[1]]);
					if (le[2]==2)
					{
						V[le[1]].addVoisin(e, V[le[0]]);
					}
				}

			}
			br.close();
		}
		catch(IOException e){
			System.out.println("Erreur d'initialisation du graphe!");
			System.out.println(e.getMessage());
		}

	}

	/********************************************
	 ************METHODE MOINS NAIVE**************
	 *********************************************/


	//diviser le graphe en n composantes connexes
	public void divise(int n)
	{
		//parcours du graphe
		double latmin=90.0;
		double latmax=0.0;
		double longmin=90.0;
		double longmax=0.0;
		double lt;
		double lg;
		for (int i=0; i<N; i++)
		{
			lt=V[i].lat;
			lg=V[i].lon;
			if (lt>latmax) {latmax=lt;}
			if (lt<latmin) {latmin=lt;}
			if (lg>longmax) {longmax=lg;}
			if (lg<longmin) {longmin=lg; }
		}
		double latmoy=(latmin+latmax)/2;
		double longmoy=(longmin+longmax)/2;

		double rayon=Math.sqrt((latmax-latmoy)*(latmax-latmoy)+(longmax-longmoy)*(longmax-longmoy));

		File[] tab = new File[C];
		tab[0] = new File ("paris1.txt");
		tab[1] = new File ("paris2.txt");
		tab[2] = new File ("paris3.txt");
		tab[3] = new File ("paris4.txt");
		tab[4] = new File ("paris5.txt");
		tab[5] = new File ("paris6.txt");
		tab[6] = new File ("paris7.txt");
		tab[7] = new File ("paris8.txt");
		//nb de rues et de croisements dans chaque quartier
		int[] rues = new int[C];
		int[] crois = new int[C];

		Edge[] aretes=new Edge[M];
		for (int i=0; i<M; i++)
		{
			aretes[i]=E[i];
		}
		Arrays.sort(aretes);

		FileWriter[] tabfw = new FileWriter[8];
		try{
			for (int i=0; i<8; i++)
			{
				tabfw[i]=new FileWriter(tab[i]);
			}
			
			PriorityQueue<Edge>[] voisins = (PriorityQueue<Edge>[]) new PriorityQueue[C];
			for (int i=0; i<8; i++)
			{
				voisins[i]=new PriorityQueue<Edge>();
			}
			int[] debut = new int[C];
			debut[0]=1293;
			debut[1]=2579;
			debut[2]=1714;
			debut[3]=4673;
			debut[4]=4985;
			debut[5]=12379;
			debut[6]=2349;
			debut[7]=4600;
			
			Vertex a,b;
			Edge e;
			for (int k=0; k<C; k++)
			{
				e=E[debut[k]];
				e.place(k);
				a=e.A;
				b=e.B;
				voisins[k].addAll(a.eAccess);
				voisins[k].addAll(b.eAccess);
			}
			
			//calcul des tailles...
			for (int i=0; i<N; i++)
			{
				for (int k=0; k<C; k++)
				{
					if (V[i].isInSousGraphe(k))
					{
						crois[k]++;
					}
				}
			}
			
			for (int i=0; i<M; i++)
			{
				for (int k=0; k<C; k++)
				{
					if (E[i].isInSousGraphe(k))
					{
						rues[k]++;
					}
				}
			}

			//ecriture de la 1re ligne
			for (int k=0; k<C; k++)
			{
				tabfw[k].write(crois[k]+" "+rues[k]+" "+T+" "+1+" "+0+"\n");
			}

			//ecriture des croisements
			for (int k=0; k<C; k++)
			{
				int ligne=0;
				for (int i=0; i<N; i++)
				{
					if (V[i].isInSousGraphe(k))
					{
						tabfw[k].write(V[i].lat+" "+V[i].lon+"\n");
						V[i].indices[k]=ligne;
						ligne++;
					}
				}
			}

			//ecriture des rues
			for (int k=0; k<C; k++)
			{
				Edge e;
				int ds;
				for (int i=0;i<M; i++)
				{
					e=E[i];
					ds=e.DS?2:1;
					if (!e.place){	System.out.println(e.place);}
					if (e.isInSousGraphe(k))
					{
						tabfw[k].write(e.A.indices[k]+" "+e.B.indices[k]+" "+ds+" "+e.cout+" "+e.dist+"\n");
					}
				}
			}

			for (int k=0; k<C; k++)
			{
				tabfw[k].close();
			}

			System.out.println("ecriture des fichiers paris finie");


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur d'écriture des fichiers paris intermédiaires");
		}

	}
}

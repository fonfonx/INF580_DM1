import java.io.*;
import java.util.*;

public class Algorithm
{
	Graph g;
	Car[] cars;
	int N; //nombre de voitures

	public Algorithm(Graph gg, Car[] cc)
	{
		g=gg;
		N=cc.length;
		cars=cc;
	}

	public void output()
	{
		File f = new File ("output.txt");
		File[] tab = new File[8];
		tab[0] = new File ("f1.txt");
		tab[1] = new File ("f2.txt");
		tab[2] = new File ("f3.txt");
		tab[3] = new File ("f4.txt");
		tab[4] = new File ("f5.txt");
		tab[5] = new File ("f6.txt");
		tab[6] = new File ("f7.txt");
		tab[7] = new File ("f8.txt");

		try
		{
			FileWriter fw = new FileWriter (f);
			FileWriter[] tabfw = new FileWriter[8];
			for (int i=0; i<8; i++)
			{
				tabfw[i]=new FileWriter(tab[i]);
				tabfw[i].write(String.valueOf(1));
				tabfw[i].write("\n");
			}
			fw.write(String.valueOf(N));
			fw.write("\n");

			for (int i=0; i<N; i++)
			{
				fw.write (String.valueOf (cars[i].travel.size()));
				fw.write ("\n");
				tabfw[i].write(String.valueOf (cars[i].travel.size()));
				tabfw[i].write("\n");
				for (int j=0;j<cars[i].travel.size();j++)
				{
					fw.write(cars[i].travel.get(j).toString());
					fw.write("\n");
					tabfw[i].write(cars[i].travel.get(j).toString());
					tabfw[i].write("\n");
				}
			}

			fw.close();
			for (int i=0; i<8; i++)
			{
				tabfw[i].close();
			}
		}
		catch (IOException exception)
		{
			System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
	}

	public void run() throws Exception
	{
		int t=0;
		while (t<g.T)
		{
			if (t%1000==0)
			{
				System.out.println((float)t/540+" %");
			}

			for (int i=0; i<N; i++)
			{
				cars[i].nextMove(t,i);
			}			
			t++;
		}
		int distance=0;
		int dd;
		for (int i=0; i<N;i++)
		{
			dd=cars[i].getDist();
			System.out.println("La voiture "+cars[i].getName()+" a parcouru "+dd+" m!");
			distance=distance+dd;
		}
		System.out.println("Total distance: "+distance);
		output();
		System.out.println("ecriture finie");
	}

	static int NN=0;
	static int nnn=0;
	static double m=0.0;
	static int fintot=0;

	public void tsp(int tps_restant, Vertex act, int dist, List<Edge> path, int max, List<Edge> best)
	{
		if (tps_restant<=0)
		{
			//System.out.println(nnn++);
			if (dist>=0.95*max)
			{
				System.out.println("oui");
				best=path;
				return;
			}
			else
			{
				
				NN++;
				if ((dist*1.0)/(1.0*max)>m){m=(dist*1.0)/(1.0*max);System.out.println(m);}
				if (NN%100000==0)
				{
					System.out.println(NN);System.out.println(m);
					for (Edge e: path)
					{
						System.out.println(e.toString());
					}
					try {
						Thread.sleep(1000000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		else
		{
			//System.out.println(nnn++);
			//System.out.println("lol");
			if (act.eAccess.size()==0) {fintot++; if (fintot%100000==0) {System.out.println("fintot"+fintot); System.out.println(m);} if ((dist*1.0)/(1.0*max)>m){m=(dist*1.0)/(1.0*max);System.out.println(m);}}
			Edge e;
			List<Edge> pp;
			boolean fait=false;
			boolean av;
			for (int i=0; i<act.eAccess.size();i++)
			{
				e=act.eAccess.get(i);
				if (!e.visited())
				{
					fait=true;
					path.add(e);
					av=e.visite;
					e.visite=true;
					e.nbVisites++;
					try {
						tsp(tps_restant-e.cout,e.autreExt(act),dist+e.dist,path,max,best);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("autre");
					}
					path.remove(e);
					e.visite=av;
					e.nbVisites--;
				}
			}
			if (!fait)
			{

				for (int i=0; i<act.eAccess.size();i++)
				{
					e=act.eAccess.get(i);
					if (e.nbVisites<4)
					{
						path.add(e);
						av=e.visite;
						e.visite=true;
						e.nbVisites++;
						try {
							tsp(tps_restant-e.cout,e.autreExt(act),dist,path,max,best);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println("autre");
						}
						e.visite=av;
						e.nbVisites--;
						path.remove(e);
					}
				}
				//System.out.println("finfor"+tps_restant+" "+dist);
			}
		}
	}


}

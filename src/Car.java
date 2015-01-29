import java.util.*;

public class Car 
{
	private int id;
	private String name;
	private boolean onVertex;
	private Edge edge;
	private Vertex vertex;
	public List<Vertex> travel;
	private int dist;
	private int prop;
	private Edge nextEdge;
	public int sum;

	public Car(int ii, String nn, boolean oo, Edge ee, Vertex vv)
	{
		id=ii;
		name=nn;
		onVertex=oo;
		vertex=vv;
		edge=ee;
		dist=0;
		travel=new ArrayList<Vertex>();
		travel.add(vv);
		prop=0;
		vv.visite++;

	}

	public int getDist()
	{
		return dist;
	}

	public String getName()
	{
		return name;
	}

	public int getId()
	{
		return id;
	}

	public boolean isOnVertex()
	{
		return onVertex;
	}

	public void updateNewVertex()
	{
		if (prop==edge.cout)
		{
			onVertex=true;
			prop=0;
			if (edge.A==vertex)
			{
				vertex=edge.B;
			}
			else
			{
				vertex=edge.A;
			}
			add(edge,vertex);
		}
	}

	public void add(Edge e, Vertex v)
	{
		if (v==e.B || v==e.A)
		{
			travel.add(v);
			if (!e.visited())
			{
				dist=dist+e.dist;
			}
			e.visite=true;
			v.visite++;
			e.nbVisites++;

		}
	}

	public float interet(Vertex actuel, int iter, int itermax, int dist, int cout, Edge first) throws Exception
	{
		if (iter==0)
		{
			if (cout==0)
			{
				System.out.println("BUUUUUUUUUUUUUUUUUUG");
			}
			return (float)dist/(float)cout;
		}
		else
		{
			ArrayList<Float> l=new ArrayList<Float>();
			ArrayList<Edge> le= new ArrayList<Edge>();
			Edge ea;
			boolean ancienVisite;
			for (int i=0; i<actuel.eAccess.size();i++)
			{
				ea=actuel.eAccess.get(i);
				if (iter==itermax)
				{
					first=ea;
				}
				//booleen indiquant si une arête non explorée est libre
				boolean libre=(actuel.nbLibres()>=1);
				int visite=(ea.virtualVisite || ea.visite)?0:1;
				//coef multiplicatif pour l'heuristique
				float coef=libre?5.0f:1.0f;
				coef=coef/(actuel.vAccess.get(i).visite+1);
				coef=coef*(1.0f+(float)iter/4.0f);
				ancienVisite=ea.virtualVisite;
				ea.virtualVisite=true;
				float d=coef*interet(actuel.vAccess.get(i),iter-1, itermax, dist+visite*ea.dist,cout+ea.cout,first);
				ea.virtualVisite=ancienVisite;
				if (libre)
				{
					if (visite==1)
					{
						l.add(d);
						le.add(first);
					}
				}
				else
				{
					l.add(d);
					le.add(first);
				}
			}
			float rep=Utils.max(l);
			if (iter==itermax)
			{
				int indice=Utils.argmax(l);
				Edge e=le.get(indice);
				nextEdge=e;
			}
			return rep;




		}
	}

	public void nextMove(int t, int i) throws Exception
	{
		if (onVertex)
		{
			int prof=Utils.PROFONDEUR;
			float d=interet(vertex,prof,prof,0,0,null);
			edge=nextEdge;
			prop++;
			onVertex=false;
			updateNewVertex();
		}
		else
		{
			prop++;
			updateNewVertex();
		}
	}


}

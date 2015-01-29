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
				//System.out.println(libre);
				int visite=(ea.virtualVisite || ea.visite)?0:1;
				float coef=libre?5.0f:1.0f;
				coef=coef/(actuel.vAccess.get(i).visite+1);
				coef=coef*(1.0f+(float)iter/4.0f);
				ancienVisite=ea.virtualVisite;
				ea.virtualVisite=true;
				float d=coef*interet(actuel.vAccess.get(i),iter-1, itermax, dist+visite*ea.dist,cout+ea.cout,first);
				ea.virtualVisite=ancienVisite;
				//boolean trop=(actuel.vAccess.get(i).visite>=2*actuel.vAccess.get(i).vAccess.size()+1);
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
				/*
				//int trop=(actuel.vAccess.get(i).visite>=2*actuel.vAccess.get(i).vAccess.size()+1)?0:1;
				
				//assert(ea.A==actuel.vAccess.get(i)||ea.B==actuel.vAccess.get(i));
				//float d=trop*interet(actuel.vAccess.get(i),iter-1,itermax, dist+visite*trop*ea.dist,cout+ea.cout,first);
				
				assert(d==trop||trop==1);
				ea.virtualVisite=ancienVisite;
				l.add(d);
				le.add(first);
				if (ea.nbVisites>=10)
				{
					assert(d==0);
				}
				*/
				//System.out.println("Ajout de: "+d+" associé à l'arête "+first.toString());
			}
			float rep=Utils.max(l);
			if (iter==itermax)
			{
				int indice=Utils.argmax(l);
				Edge e=le.get(indice);
				nextEdge=e;
				//System.out.println("!!!!!!!!!!!! Choix de "+e.toString()+ " avec interet= "+rep+ "!!!!!!!!!!!!!!!!");
			}
			return rep;




		}
	}

	public void nextMove(int t, int i) throws Exception
	{
		//System.out.println("temps: "+t+ "voiture: "+i);
		if (onVertex)
		{
			int prof=Utils.PROFONDEUR;
			//System.out.println("BEGIN!!");
			float d=interet(vertex,prof,prof,0,0,null);
			/*System.out.println("END!!");
			System.out.println();
			System.out.println();*/
			//System.out.println(d);
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

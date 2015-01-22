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
				e.visite=true;
			}
			
		}
	}

	public float interet(Vertex actuel, int iter, int itermax, int dist, int cout, Edge first)
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
			for (int i=0; i<actuel.eAccess.size();i++)
			{
				if (iter==itermax)
				{
					first=actuel.eAccess.get(i);
				}
				actuel.eAccess.get(i).virtualVisite=false;
				int visite=actuel.eAccess.get(i).virtualVisite || actuel.eAccess.get(i).visite?0:1;
				float d=interet(actuel.vAccess.get(i),iter-1,itermax, dist+visite*actuel.eAccess.get(i).dist,cout+actuel.eAccess.get(i).cout,first);
				actuel.eAccess.get(i).virtualVisite=true;
				l.add(d);
				le.add(first);

			}
			float rep=Utils.max(l);
			int indice=Utils.argmax(l);
			Edge e=le.get(indice);
			nextEdge=e;
			return rep;




		}
	}

	public void nextMove()
	{
		if (onVertex)
		{
			int prof=Utils.PROFONDEUR;
			float d=interet(vertex,prof,prof,0,0,null);
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

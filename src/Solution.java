
public class Solution {

	public static void main(String[] args) {
		System.out.println("Construction du Graphe");
		Graph g=new Graph();
		g.init("paris_54000.txt");
		System.out.println("Le graphe a été initié");
		Car[] tab=new Car[g.C];
		for (int i=0; i<g.C; i++)
		{
			tab[i]=new Car(i,Utils.colors[i],true,null,g.V[g.S]);
		}
		Algorithm a=new Algorithm(g,tab);
		a.run();
		
		System.out.println("La solution a été trouvée");
		int v=0;
		for (int i=0;i<g.E.length; i++)
		{
			if (g.E[i].visited())
			{
				v++;
			}
		}
		System.out.println("visite: "+v);

	}

}

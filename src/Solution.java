
public class Solution {

	public static void main(String[] args) {
		System.out.println("Construction du Graphe");
		Graph g=new Graph();
		g.init("paris_54000.txt");
		System.out.println("Le graphe a été initié");
		System.out.println("La solution n'est pas encore trouvée");
		Vertex v=g.V[4516];
		for (int i=0; i<v.vAccess.size();i++)
		{
			System.out.println(v.vAccess.get(i).toString());
		}

	}

}

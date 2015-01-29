import java.util.*;

public class Utils {

	public final static int PROFONDEUR=12;
	
	public static String[] colors={"Fred","Greenlee","Pinkney","Bluebell","Willem","Greydom","Yellowsubmarine","SiriusBlack"};
	
	public static float max(List<Float> l)
	{
		if (l.size()==0)
		{
			return 0;
		}
		float max=l.get(0);
		for (int i=1; i<l.size();i++)
		{
			if (l.get(i)>max)
			{
				max=l.get(i);
			}
		}
		return max;
	}
	
	public static int argmax(List<Float> l)
	{
		if (l.size()==0)
		{
			System.out.println("vide! arg");
			return 0;
		}
		float max=l.get(0);
		int arg=0;
		for (int i=1; i<l.size();i++)
		{
			if (l.get(i)>max)
			{
				max=l.get(i);
				arg=i;
			}
		}
		return arg;
	}
}


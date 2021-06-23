package data;

import java.util.Comparator;

public class ComparatorTop10 implements Comparator<Empresa>
{
	@Override
	public int compare(Empresa e1, Empresa e2)
	{
	    return ((Double) e1.getPatrimonio()).compareTo((Double)e2.getPatrimonio());
	}
}

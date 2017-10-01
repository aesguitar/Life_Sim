package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import life.Occupation;
import life.PrevOcc;

public class OccupationList {

	private ArrayList<Occupation> OccList = new ArrayList<Occupation>();

	public OccupationList(File f) throws DocumentException {
		SAXReader sr = new SAXReader();
		Document d = sr.read(f);
		Element r = d.getRootElement();
		for(Iterator<Element> it = r.elementIterator(); it.hasNext();)
		{
			Element i = it.next();
			int id = Integer.parseInt(i.attributeValue("ID"));
			String name = i.attributeValue("Name");
			String title = i.attributeValue("Title");
			double exp = Double.parseDouble(i.attributeValue("Experience"));
			double salary = Double.parseDouble(i.attributeValue("Salary"));
			ArrayList<PrevOcc> po = new ArrayList<PrevOcc>();
			for(Iterator<Element> it2 = i.element("Previous").elementIterator(); it2.hasNext();)
			{
				Element i2 = it2.next();
				if(i2.attributeValue("Name") != null)
					
				{
					String n2 = i2.attributeValue("Name");
					String t2 = i2.attributeValue("Title");
					boolean req = i2.attributeValue("Required").equalsIgnoreCase("yes");
					po.add(new PrevOcc(n2, t2, req));
				}
			}
			OccList.add(new Occupation(id, name, title, exp, salary, po));
		}
	}

	public Occupation get(int index)
	{
		return OccList.get(index);
	}
	
	//returns the first job found matching the given name and title, null if none found
	public Occupation getByNameTitle(String name, String title)
	{
		for(Occupation i : OccList)
			if(i.getName().equalsIgnoreCase(name)&&i.getTitle().equalsIgnoreCase(title))
			{
				return i;
			}
		return null;
	}

	public ArrayList<Occupation> getArrayList()
	{
		return OccList;
	}

	public static void main(String[] args)
	{
		try {
			OccupationList ol = new OccupationList(new File("occupations.xml"));
			ArrayList<Occupation> a = ol.getArrayList();
			for(Occupation o : a)
			{
				System.out.println(o.toString());
			}
			
			System.out.println("\n"+ol.getByNameTitle("none", "none"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

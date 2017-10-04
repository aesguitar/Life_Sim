package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import life.Stat;

public class StatsList {

	private ArrayList<Stat> sl = new ArrayList<Stat>();
	
	public StatsList(File stat_names){
		Scanner in = null;
		try {
			in = new Scanner(stat_names);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id = 0;
		String name;
		while(in.hasNextLine())
		{
			id = in.nextInt();
			name = in.next();
			sl.add(new Stat(name, id));
		}
		in.close();
		setStat(0,100);
		setStat(8, utils.Utils.expRandom(0, 100, 2.5, 100));
	}
	
	public ArrayList<Stat> getList()
	{
		return sl;
	}
	
	public Stat getStat(int id)
	{
		return sl.get(id);
	}
	
	public void setStat(int id, double value)
	{
		sl.get(id).setValue(value);
	}
	
	public void setStat(String name, double value) throws Exception
	{
		boolean found = false;
		for(Stat i: sl)
		{
			if(i.getName().equals(name))
			{
				sl.get(i.getId()).setValue(value);
				found = true;
			}
		}
		if(!found)
			throw new Exception("Stat does not exist.");
	}
	
	public static void main(String args[])
	{
		StatsList s = new StatsList(utils.constants.STAT_NAMES);
		
		for(Stat i : s.getList())
		{
			System.out.println(i.toString());
		}
	}

}

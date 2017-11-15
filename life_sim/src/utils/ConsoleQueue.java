package utils;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.text.Text;

public class ConsoleQueue {

	private ArrayList<String> q = new ArrayList<String>();

	public ConsoleQueue(String message)
	{
		q.add(message);
	}

	public ConsoleQueue()
	{

	}
	
	public void add(String msg)
	{
		q.add(msg);
	}

	public String getTop()
	{
		return q.get(q.size()-1);
	}

	public String get(int index)
	{
		return q.get(index);
	}

	public void removeTop()
	{
		if(!q.isEmpty())
			q.remove(q.size()-1);
	}
	
	public void remove(int index)
	{
		if(!q.isEmpty() && index<=q.size()-1)
			q.remove(index);
	}
	
	public int getSize()
	{
		return q.size();
	}
	
	public void reverseOrder()
	{
		Collections.reverse(q);
	}
	
	public boolean isEmpty()
	{
		return q.isEmpty();
	}
	
	public void printConsole()
	{
		System.out.println("*********Start console dump**********");
		for(String i : q)
		{
			System.out.println(i);
		}
		System.out.println("***********End console dump.************");
	}

}

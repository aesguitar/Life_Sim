package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class BuildOccupationFile {

	public static Document buildOccupationFile() {
		Document d = DocumentHelper.createDocument();

		Element root = d.addElement("Root");
		int id = 1;

		Scanner in = new Scanner(System.in);

		//Job -> job name, title, experience, salary, previous titles (element) -> job title, required?
		//experience in years
		while(true)
		{
			Element j = root.addElement("Job");
			j.addAttribute("ID", Integer.toString(id));

			System.out.print("\nJob name: ");
			String name = in.nextLine();
			j.addAttribute("Name", name);

			System.out.print("Job title: ");
			String title = in.nextLine();
			j.addAttribute("Title", title);

			System.out.print("Experience: ");
			String experience = in.nextLine();
			j.addAttribute("Experience", experience);

			System.out.print("Salary: ");
			String salary = in.nextLine();
			j.addAttribute("Salary", salary);

			System.out.print("Add previous titles (y,n): ");
			String prev = in.nextLine();

			int pi = 1;
			Element p = j.addElement("Previous"); //Can be empty

			if(prev.toLowerCase().equalsIgnoreCase("y"))
			{
				while(true)
				{
					Element pp = p.addElement("Job");
					pp.addAttribute("ID", Integer.toString(pi));

					System.out.print("\nName: ");
					String pName = in.nextLine();
					pp.addAttribute("Name", pName);

					System.out.print("Job title: ");
					String pTitle = in.nextLine();
					pp.addAttribute("Title", pTitle);

					System.out.print("Required? (yes/no): ");
					String req = in.nextLine();
					pp.addAttribute("Required", req);

					System.out.print("Another? (y/n): ");
					String t = in.nextLine();
					if(t.equalsIgnoreCase("n"))
						break;
					pi++;
				}
			}

			System.out.print("\nAnother? (y/n): ");
			String t = in.nextLine();
			if(t.equalsIgnoreCase("n"))
				break;
			id++;
		}
		in.close();
		return d;
	}
	
	public static Document buildOccupationFile(Scanner sc) {
		Document d = DocumentHelper.createDocument();

		Element root = d.addElement("Root");
		int id = 1;

		Scanner in = sc;

		//Job -> job name, title, experience, salary, previous titles (element) -> job title, required?
		//experience in years
		while(true)
		{
			Element j = root.addElement("Job");
			j.addAttribute("ID", Integer.toString(id));

			System.out.print("\nJob name: ");
			String name = in.nextLine();
			j.addAttribute("Name", name);

			System.out.print("Job title: ");
			String title = in.nextLine();
			j.addAttribute("Title", title);

			System.out.print("Experience: ");
			String experience = in.nextLine();
			j.addAttribute("Experience", experience);

			System.out.print("Salary: ");
			String salary = in.nextLine();
			j.addAttribute("Salary", salary);

			System.out.print("Add previous titles (y,n): ");
			String prev = in.nextLine();

			int pi = 1;
			Element p = j.addElement("Previous"); //Can be empty

			if(prev.toLowerCase().equalsIgnoreCase("y"))
			{
				while(true)
				{
					Element pp = p.addElement("Job");
					pp.addAttribute("ID", Integer.toString(pi));

					System.out.print("\nName: ");
					String pName = in.nextLine();
					pp.addAttribute("Name", pName);

					System.out.print("Job title: ");
					String pTitle = in.nextLine();
					pp.addAttribute("Title", pTitle);

					System.out.print("Required? (yes/no): ");
					String req = in.nextLine();
					pp.addAttribute("Required", req);

					System.out.print("Another? (y/n): ");
					String t = in.nextLine();
					if(t.equalsIgnoreCase("n"))
						break;
					pi++;
				}
			}

			System.out.print("\nAnother? (y/n): ");
			String t = in.nextLine();
			if(t.equalsIgnoreCase("n"))
				break;
			id++;
		}
		in.close();
		return d;
	}

	public static Document buildFromFile(URL url) throws DocumentException
	{
		SAXReader sr = new SAXReader();
		Document d = sr.read(url);
		Element r = d.getRootElement();
		Element lastJob = (Element)(r.node(r.nodeCount()-1));
		int id = Integer.parseInt(lastJob.attribute("ID").getStringValue())+1;
		
		
		

		Scanner in = new Scanner(System.in);

		System.out.print("Start new file? (y/n) ");
		
		if(!in.nextLine().equalsIgnoreCase("y"))
		{
			System.out.println("\n Previous Job:");
			System.out.println(lastJob.asXML() + "\n");
			//Job -> job name, title, experience, salary, previous titles (element) -> job title, required?
			//experience in years
			while(true)
			{
				Element j = r.addElement("Job");
				j.addAttribute("ID", Integer.toString(id));

				System.out.print("\nJob name: ");
				String name = in.nextLine();
				j.addAttribute("Name", name);

				System.out.print("Job title: ");
				String title = in.nextLine();
				j.addAttribute("Title", title);

				System.out.print("Experience: ");
				String experience = in.nextLine();
				j.addAttribute("Experience", experience);

				System.out.print("Salary: ");
				String salary = in.nextLine();
				j.addAttribute("Salary", salary);

				System.out.print("Add previous titles (y,n): ");
				String prev = in.nextLine();

				int pi = 1;
				Element p = j.addElement("Previous"); //Can be empty

				if(prev.toLowerCase().equalsIgnoreCase("y"))
				{
					while(true)
					{
						Element pp = p.addElement("Job");
						pp.addAttribute("ID", Integer.toString(pi));

						System.out.print("\nName: ");
						String pName = in.nextLine();
						pp.addAttribute("Name", pName);

						System.out.print("Job title: ");
						String pTitle = in.nextLine();
						pp.addAttribute("Title", pTitle);

						System.out.print("Required? (yes/no): ");
						String req = in.nextLine();
						pp.addAttribute("Required", req);

						System.out.print("Another? (y/n): ");
						String t = in.nextLine();
						if(t.equalsIgnoreCase("n"))
							break;
						pi++;
					}
				}

				System.out.print("\nAnother? (y/n): ");
				String t = in.nextLine();
				if(t.equalsIgnoreCase("n"))
					break;
				id++;
			}
		}
		else
		{
			System.out.println("Starting from scratch...");
			return buildOccupationFile(in);
		}
		in.close();
		return d;

	}

	public static void main(String[] Args)
	{
		URL u = null;
		try {
			u = new URL("file:\\C:\\Users\\Alex\\eclipse-workspace\\life_sim\\occupations.xml");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		Document d = null;
		try {		
			System.out.println("Adding to file.");
			d = buildFromFile(u);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			System.out.println("File not found. Building new file.");
			d = buildOccupationFile();
		}


		try {
			FileWriter f = new FileWriter("occupations.xml");
			XMLWriter writer = new XMLWriter(f);
			writer.write( d );
			writer.close();

		}
		catch(IOException e) {e.printStackTrace();}
	}

}




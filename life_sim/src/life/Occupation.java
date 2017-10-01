package life;

import java.util.ArrayList;


public class Occupation {
	
	private int id;
	private String name;
	private String title;
	private double experience;
	private double salary;
	private ArrayList<PrevOcc> reqs;

	public Occupation(int id, String name, String title, double experience, double salary, ArrayList<PrevOcc> reqs) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setTitle(title);
		this.setExperience(experience);
		this.setSalary(salary);
		this.setReqs(reqs);
		this.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public ArrayList<PrevOcc> getReqs() {
		return reqs;
	}

	public void setReqs(ArrayList<PrevOcc> reqs) {
		this.reqs = reqs;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean equals(Occupation compareTo)
	{
		return this.id == compareTo.getId();
	}
	
	public String toString()
	{
		String first = String.format("(%s, %s, %f, %f, ", this.name, this.title, this.experience, this.salary);
		String second = "";
		for(PrevOcc i : this.reqs)
		{
			second = second.concat(i.toString()).concat(", ");
		}
		return first.concat(second).concat(")");
	}

}

package life;

public class Stat {
	private String name;
	private double value;
	private int id;

	public Stat(String name, int id) {
		setName(name);
		value = Math.random()*100;
		this.setId(id);
	}
	
	public Stat(String name, double value, int id)
	{
		setName(name);
		setValue(value);
		setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String toString()
	{
		return String.format("[%d. %s: %f]", id, name, value);
	}

	public int getId() {
		return id;
	}

	public void setId(int id2) {
		this.id = id2;
	}
	
}

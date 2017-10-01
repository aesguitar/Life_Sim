package life;

public class PrevOcc {

	private String name;
	private String title;
	private boolean required;
	private double time;
	
	public PrevOcc(String name, String title, boolean required) {
		this.name = name;
		this.title = title;
		this.setRequired(required);
		time = 0;
	}
	
	public PrevOcc(String name, String title, double time) {
		this.name = name;
		this.title = title;
		this.time = time;
		required = false;
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

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public String toString()
	{
		return String.format("[%s, %s, %f, %b]", this.name, this.title, this.time, this.required);
	}

}

package life;

import java.io.FileNotFoundException;
import java.util.Scanner;

import utils.StatsList;

public class Person {

	private final String MALE = "Male", FEMALE = "Female";
	public final int NONE = 0, BIRTH = 1, OCCUPATION_CHANGE = 2, DEATH = 3;

	private String name; 
	private int age;
	private String gender;
	private boolean dead;
	private double[] deathChances;
	private Occupation currOcc;
	private StatsList sl;
	
	private boolean newEvent = false;
	private int eventType = 0;
	
	public Person(String name)
	{
		
		this.name = name;
		age = 0;

		boolean dead = (Math.random()<.001); 

		if(dead)
			System.out.println("Congratulations, you died at birth!");

		if(Math.random() > .52)
			setGender(FEMALE);
		else
			setGender(MALE);

		deathChances = new double[115];
		try {
			setChanceValues();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		currOcc =utils.constants.OCC_LIST.getByNameTitle("none", "none");
		sl = new StatsList(utils.constants.STAT_NAMES);
		utils.Statics.sl = sl;
	}

	private void setChanceValues() throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(utils.constants.DEATH_CHANCES);
		for(int i = 0; i < deathChances.length; i++)
		{
			deathChances[i] = in.nextDouble();
		}
		in.close();

	}

	public String getName()
	{
		return name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void ageOneYear()
	{
		if(!dead)
		{
			age++;
		}

		if(deadThisYear())
		{
			dead = true;
			newEvent = true;
			eventType = DEATH;
		}
		else
		{
			if(age == 4)
			{
				setCurrOcc(utils.constants.OCC_LIST.getByNameTitle("Kindergarten", "Student"));
				newEvent = true;
				setEventType(OCCUPATION_CHANGE);
			}
			else if(age == 6)
			{
				setCurrOcc(utils.constants.OCC_LIST.getByNameTitle("Elementary School", "Student"));
				newEvent = true;
				setEventType(OCCUPATION_CHANGE);
			}
			else if(age == 11)
			{
				setCurrOcc(utils.constants.OCC_LIST.getByNameTitle("Middle School", "Student"));
				newEvent = true;
				setEventType(OCCUPATION_CHANGE);
			}
			else if(age == 14)
			{
				setCurrOcc(utils.constants.OCC_LIST.getByNameTitle("High School", "Student"));
				newEvent = true;
				setEventType(OCCUPATION_CHANGE);
			}
			else if(age == 18)
			{
				setCurrOcc(utils.constants.OCC_LIST.getByNameTitle("College", "Student"));
				newEvent = true;
				setEventType(OCCUPATION_CHANGE);
			}
			else if((age < 4 || age > 22) && !currOcc.equals(utils.constants.OCC_LIST.getByNameTitle("none", "none")))
			{
				setCurrOcc(utils.constants.OCC_LIST.getByNameTitle("none", "none"));
				newEvent = true;
				setEventType(OCCUPATION_CHANGE);
			}
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private boolean deadThisYear()
	{
		return (Math.random()<getDeathChance()&&!dead);
	}

	private double getDeathChance()
	{

		return deathChances[age];
	}

	public boolean getDead()
	{
		return dead;
	}

	public static void main(String[] args)
	{
		double l = 45;
		double k = .098;
		double x0 = 88;
		double l2 = 80;
		double k2 = -1.2;
		double x02 = -2.1;

		int age = 1;

		for(; age < 150; age++)
		{
			double chance = .01*( l2/(1 + Math.exp(-k2*(age-x02))) + l/( 1+ Math.exp(-k*(age-x0))));
			System.out.printf("Chance to die at %d is: %.3f%%\n",age, chance*100);
		}
	}

	public boolean isNewEvent() {
		return newEvent;
	}

	public void setNewEvent(boolean newEvent) {
		this.newEvent = newEvent;
	}

	public Occupation getCurrOcc() {
		return currOcc;
	}

	public void setCurrOcc(Occupation currOcc) {
		this.currOcc = currOcc;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

}

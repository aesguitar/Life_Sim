package utils;

import java.io.File;

public class constants {
	
	public static final File DEATH_CHANCES = new File("deathChances"); 
	public static final File OCCUPATIONS = new File("occupations.xml");
	public static final File STAT_NAMES = new File("stat_names");
	public static final File MAIN_UI = new File("TestUI.fxml");
	public static final File STATS_DISPLAY = new File("StatsDisplay.fxml");
	
	public static final OccupationList OCC_LIST = new OccupationList(OCCUPATIONS);
}

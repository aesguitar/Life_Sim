package test;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import life.Person;



public class TestUIController {

	private Person p;

	@SuppressWarnings("rawtypes")
	private ObservableList console;

	@FXML
	private Button testButton, newLife, statsButton; 

	@FXML 
	private TextFlow textOutput;

	@FXML
	private ScrollPane sPane1;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize()
	{
		console = textOutput.getChildren();

		testButton.addEventHandler(Event.ANY, new EventHandler()
		{
			public void handle(Event e)
			{
				if(e.getEventType().equals(MouseEvent.MOUSE_CLICKED))
				{
					if(p != null)
					{
						if(!p.getDead())
						{
							p.ageOneYear();
							if(p.getAge() == 1)
								addTextToConsole(String.format("You aged one year. You are %d year old.", p.getAge()));
							else
								addTextToConsole(String.format("You aged one year. You are %d years old.", p.getAge()));

							System.out.printf("new event: %b\n", p.isNewEvent());
							if(p.isNewEvent())
							{
								if(p.getEventType() == p.DEATH)
								{
									addTextToConsole(String.format("\t%s has died at %d years old.", p.getName(), p.getAge()));
								}
								else if(p.getEventType() == p.OCCUPATION_CHANGE)
								{
									addTextToConsole(String.format("\t%s has started %s as a %s.", p.getName(), p.getCurrOcc().getName(), p.getCurrOcc().getTitle()));
								}

								p.setNewEvent(false);
								p.setEventType(p.NONE);
							}

						}
						else
						{
							addTextToConsole(String.format("%s is dead at %d years old.", p.getName(),p.getAge()));
						}
					}
					else
					{
						addTextToConsole("No person was created.");
					}

				}
			}
		});

		newLife.addEventHandler(Event.ANY, new EventHandler()
		{
			public void handle(Event e)
			{
				if(e.getEventType().equals(MouseEvent.MOUSE_CLICKED))
				{
					p = new Person("Alex Scott");
					clearConsole();
					addTextToConsole(String.format("You are born. Your name is %s.", p.getName()));
					addTextToConsole(String.format("You are a %s.", p.getGender()));
				}
			}
		});

		statsButton.addEventHandler(Event.ANY, new EventHandler()
		{
			public void handle(Event e)
			{
				if(e.getEventType().equals(MouseEvent.MOUSE_CLICKED))
				{
					if(p != null)
					{
						Parent root;

						try {
							root = FXMLLoader.load(getClass().getResource(utils.constants.STATS_DISPLAY.getName()));
							Stage stage = new Stage();
							stage.setScene(new Scene(root));
							stage.show();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					else
					{
						addTextToConsole("Person not yet created.");
					}
				}
			}
		});


	}

	@SuppressWarnings("unchecked")
	//Adds the specified string to the console and add a new line character
	public void addTextToConsole(String text)
	{
		System.out.printf("Adding output to console. \"%s\"\n", text);
		console.add(new Text(text.concat("\n")));
		console.add(new Text(""));
		//console.remove(console.size()-1);
		sPane1.setVvalue(1);
	}

	@SuppressWarnings("unchecked")
	public void clearConsole()
	{
		System.out.println("Clearing Console.");
		console.removeAll(console);
	}

}

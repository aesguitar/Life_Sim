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
import utils.ConsoleQueue;



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
		ConsoleQueue cq = new ConsoleQueue();

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
							cq.add("You aged one year.");
							if(p.getAge() == 1)
								cq.add(String.format("You are %d year old.", p.getAge()));
							else
								cq.add(String.format("You are %d years old.", p.getAge()));

							System.out.printf("new event: %b\n", p.isNewEvent());
							if(p.isNewEvent())
							{
								if(p.getEventType() == p.DEATH)
								{
									cq.add(String.format("\t%s has died at %d years old.", p.getName(), p.getAge()));
								}
								else if(p.getEventType() == p.OCCUPATION_CHANGE)
								{
									cq.add(String.format("\t%s has started %s as a %s.", p.getName(), p.getCurrOcc().getName(), p.getCurrOcc().getTitle()));
								}

								p.setNewEvent(false);
								p.setEventType(p.NONE);
							}
						}
						else
						{
							cq.add(String.format("%s is dead at %d years old.", p.getName(),p.getAge()));
						}
					}
					else
					{
						cq.add("No person was created.");
					}

					printQueueToConsole(cq);

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
		if(console.size()>0)
			console.remove(console.size()-1);
		System.out.printf("Adding output to console. \"%s\"\n", text);
		console.add(new Text(text.concat("\n")));
		console.add(new Text("\n\n\n"));
		//console.remove(console.size()-1);
		sPane1.setVvalue(1);
	}

	@SuppressWarnings("unchecked")
	public void addTextToConsole(String text, int spacing)
	{
		if(console.size()>0)
			console.remove(console.size()-1);
		System.out.printf("Adding output to console. \"%s\"\n", text);
		console.add(new Text(text.concat("\n")));
		console.add(new Text(utils.Utils.newLineSpacing(spacing)));
		console.add(new Text("\n\n\n"));
		//console.remove(console.size()-1);
		sPane1.setVvalue(1);
	}

	public void printQueueToConsole(ConsoleQueue cq)
	{
		//cq.printConsole();
		int spacing = cq.getSize();
		cq.reverseOrder();
		//cq.printConsole();
		if(!cq.isEmpty())
		{
			for(int i = spacing-1; i > 0; i--)
			{
				addTextToConsole(cq.getTop());
				cq.removeTop();
			}
			addTextToConsole(cq.getTop(),spacing);
			cq.removeTop();
		}
	}

	@SuppressWarnings("unchecked")
	public void clearConsole()
	{
		System.out.println("Clearing Console.");
		console.removeAll(console);
	}

}

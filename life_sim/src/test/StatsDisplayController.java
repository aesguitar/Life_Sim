package test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import life.Stat;

public class StatsDisplayController {

	@FXML
	private Button close;

	@FXML
	private BarChart<Number, String> chart;

	@FXML
	private NumberAxis numAx;

	@FXML
	private CategoryAxis catAx;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize()
	{	
		
		
		
		ObservableList<XYChart.Series<Number, String>> data = FXCollections.observableArrayList(new XYChart.Series("Stats", FXCollections.<XYChart.Data<Number, String>>emptyObservableList()));
		
		XYChart.Series<Number, String> xy = new XYChart.Series<Number, String>();
		
		for(Stat s : utils.Statics.sl.getList())
		{
			xy.getData().add(0,new XYChart.Data<Number, String>(s.getValue(), s.getName()));	
		}
		data.add(0,xy);
		data.remove(1);
		
		//chart = new BarChart<Number, String>(numAx, catAx,data);
		chart.setData(data);
		numAx.setAutoRanging(false);
		numAx.setUpperBound(100);
		
		chart.paddingProperty().set(new Insets(0,0,0,15));
		

		close.addEventHandler(Event.ANY, new EventHandler()
		{
			public void handle(Event e)
			{
				if(e.getEventType().equals(MouseEvent.MOUSE_CLICKED))
				{
					close.getScene().getWindow().hide();
				}
			}
		});


	}


}

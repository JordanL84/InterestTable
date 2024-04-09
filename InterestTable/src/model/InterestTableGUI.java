package model;

import javafx.event.EventHandler;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text; 
import java.text.*;

public class InterestTableGUI extends Application {
	
	private TextField prinText, rateText;
	private Text text;
	private Slider slider;
	
	public void start(Stage stage) {
		
		//Top
		text = new Text();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(425, 125);
		scrollPane.setContent(text);
		
		//Bottom
		Button button1 = new Button("SimpleInterest");
		Button button2 = new Button("CompoundInterest");
		Button button3 = new Button("BothInterests");
		HBox hBox = new HBox(5);
		hBox.getChildren().addAll(button1, button2, button3);
		hBox.setAlignment(Pos.CENTER);
		
		//Implementing functionality
		button1.setOnAction(new ButtonHandler());
		button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				double prin = Double.parseDouble(prinText.getText());
				double rate = Double.parseDouble(rateText.getText());
				String output = "Principal: " + NumberFormat.getCurrencyInstance().format(prin) +
						", Rate: " + rateText.getText();
				output += "\nYear, Compound Interest Amount";
				
				for (int i = 1; i <= slider.getValue(); i++) {
					double amount = Interest.calculateCompundInterest(prin, rate, i);
					output += "\n" + i + "-->" + NumberFormat.getCurrencyInstance().format(amount);
				}
				
				text.setText(output);
			}
		});
		button3.setOnAction(e -> {
			double prin = Double.parseDouble(prinText.getText());
			double rate = Double.parseDouble(rateText.getText());
			String output = "Principal: " + NumberFormat.getCurrencyInstance().format(prin) +
					", Rate: " + rateText.getText();
			output += "\nYear, Simple Interest Amount, Compound Interest Amount";
			
			for (int i = 1; i <= slider.getValue(); i++) {
				double simpleAmount = Interest.calculateSimpleInterest(prin, rate, i);
				double compundAmount = Interest.calculateCompundInterest(prin, rate, i);
				output += "\n" + i + "-->" + NumberFormat.getCurrencyInstance().format(simpleAmount);
				output += "-->" + NumberFormat.getCurrencyInstance().format(compundAmount);
			}
			
			text.setText(output);
		});
		
		//Center1 (top center)
		Label prinLabel = new Label("Principal:");
		prinText = new TextField();
		prinText.setPrefSize(100, 1);
		Label rateLabel = new Label("Rate(Percentage):");
		rateText = new TextField();
		rateText.setPrefSize(100, 1);
		HBox center1 = new HBox(5);
		center1.getChildren().addAll(prinLabel, prinText, rateLabel, rateText);
		center1.setAlignment(Pos.CENTER);
				
		//Center2 (bottom center)
		Label numYears = new Label("Number of Years");
		slider = new Slider();
		slider.setMin(1);
		slider.setMax(25);
		slider.setMajorTickUnit(4);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setValue(25);
		HBox center2 = new HBox(5);
		center2.getChildren().addAll(numYears, slider);
		center2.setAlignment(Pos.CENTER);
				
		//Center
		BorderPane centerPane = new BorderPane();
		centerPane.setTop(center1);
		centerPane.setBottom(center2);
				
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(scrollPane);
		borderPane.setBottom(hBox);
		borderPane.setCenter(centerPane);
		
		
		Scene scene = new Scene(borderPane, 425, 250, Color.BLACK);
		stage.setTitle("Interest Table Calculator");
		stage.setScene(scene);
		stage.show();
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			double prin = Double.parseDouble(prinText.getText());
			double rate = Double.parseDouble(rateText.getText());
			String output = "Principal: " + NumberFormat.getCurrencyInstance().format(prin) +
					", Rate: " + rateText.getText();
			output += "\nYear, Simple Interest Amount";
			
			for (int i = 1; i <= slider.getValue(); i++) {
				double amount = Interest.calculateSimpleInterest(prin, rate, i);
				output += "\n" + i + "-->" + NumberFormat.getCurrencyInstance().format(amount);
			}
			
			text.setText(output);
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}

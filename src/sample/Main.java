package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;

import javax.xml.soap.Node;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Ticketautomat ticket = new Ticketautomat();

        primaryStage.setTitle("Ticketautomat");
        Button btn1 = new Button("Fahrpreis anzeigen");
        Button btn2 = new Button("Geld einwerfen");
        Button btn3 = new Button("Differenz ermitteln");
        Button btn4 = new Button("Ticket drucken");
        TextArea txt1 = new TextArea("->Dein Fahrpreis<-");
        TextArea txt2 = new TextArea("->Bisher gezahlt<-");
        TextArea txt3 = new TextArea("->Differenz-");

        final ToggleGroup radioGroup = new ToggleGroup();
        radioGroup.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {
                    @Override
                    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                        RadioButton rb = (RadioButton)radioGroup.getSelectedToggle();
                        if(rb != null){
                            String s = rb.getText();
                            ticket.ticketAuswahl(s);
                            ticket.setTicketToFahrpreis();
                            txt1.setText(ticket.getTicket() + " Preis: " + ticket.getFahrpreis() + " Euro");
                        }
                    }
                }
        );


        RadioButton rbtn1 = new RadioButton("Ermaessigt");
        rbtn1.setToggleGroup(radioGroup);
        RadioButton rbtn2 = new RadioButton("Kurzstrecke");
        rbtn2.setToggleGroup(radioGroup);
        RadioButton rbtn3 = new RadioButton("Normal");
        rbtn3.setToggleGroup(radioGroup);

        ObservableList<Geld> options1 = FXCollections.observableList(Geld.toList());

        ComboBox combo = new ComboBox(options1);
        combo.setValue("10 Cent");

        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(event.getSource() == btn2){
                    Geld g = (Geld)combo.getValue();
                    ticket.setEingezahlt(g);
                    txt2.setText("Eingezahlt: " + ticket.getEingezahlt());
                }
            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(event.getSource() == btn3){
                    txt3.setText("Differenz: " + ticket.calcDiff());
                }
            }
        });





        StackPane root = new StackPane();
        VBox grid = new VBox();
        grid.setSpacing(5);
        grid.getChildren().addAll(rbtn1, rbtn2, rbtn3, btn1, txt1, combo, btn2, txt2, btn3, txt3, btn4);


        primaryStage.setScene(new Scene(grid, 300, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

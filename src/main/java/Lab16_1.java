import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Lab16_1 extends Application {
    private Text text = new Text();
    private CheckBox chkBold = new CheckBox("Bold");
    private CheckBox chkItalic = new CheckBox("Italic");
    private HBox checkBoxPane = new HBox(15);

    TextField tfMessage = new TextField();
    private VBox updateContainer = new VBox(10);

    //Add the option buttons
    RadioButton rbRed = new RadioButton("Red");
    RadioButton rbGreen = new RadioButton("Green");
    RadioButton rbBlue = new RadioButton("Blue");
    RadioButton rbBlack = new RadioButton("Black");
    RadioButton rbOrange = new RadioButton("Orange");

    HBox paneForRadioButtons = new HBox(20);

    public void start(Stage stage) {
        BorderPane mainScreen = new BorderPane();

        // Add checkboxes
        createCheckBoxContainer();

        // Create the right pane
        createRightPane();

        createRadioButtons();

        createListeners();
        rbBlack.setSelected(true);

        update();

        VBox paneForButtons = new VBox(checkBoxPane, paneForRadioButtons);

        mainScreen.setCenter(text);
        mainScreen.setBottom(paneForButtons);
        mainScreen.setRight(updateContainer);

        //usual stuff
        Scene scene = new Scene(mainScreen, 700, 500);
        stage.setTitle("Select a Font");
        stage.setScene((scene));
        stage.show();
    }

    private void createRadioButtons() {
        ToggleGroup group = new ToggleGroup();
        rbRed.setToggleGroup(group);
        rbGreen.setToggleGroup(group);
        rbBlue.setToggleGroup(group);
        rbBlack.setToggleGroup(group);
        rbOrange.setToggleGroup(group);

        paneForRadioButtons.getChildren().addAll(rbRed, rbGreen, rbBlue, rbBlack, rbOrange);
    }

    private void createCheckBoxContainer() {
        checkBoxPane.getChildren().addAll(chkBold, chkItalic);
        checkBoxPane.setPadding(new Insets(15));
        checkBoxPane.setAlignment(Pos.CENTER);
    }

    private void createListeners() {
        chkBold.setOnAction(e -> update());
        chkItalic.setOnAction(e -> update());

        tfMessage.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                case TAB:
                    text.setText(tfMessage.getText());
                    break;
            }
        });

        // create listeners for the radio buttons
        rbRed.setOnAction(e -> text.setFill(Color.RED));
        rbGreen.setOnAction(e -> text.setFill(Color.GREEN));
        rbBlue.setOnAction(e -> text.setFill(Color.BLUE));
        rbBlack.setOnAction(e -> text.setFill(Color.BLACK));
        rbOrange.setOnAction(e -> text.setFill(Color.ORANGE));
    }

    private void update() {
        // when something changes, this method will repaint the screen

        // determine the weight of the font
        FontWeight fontWeight = (chkBold.isSelected()) ?
                FontWeight.BOLD : FontWeight.NORMAL;
        // determine if it is italic or reg
        FontPosture fontPosture = (chkItalic.isSelected()) ?
                FontPosture.ITALIC : FontPosture.REGULAR;

        text.setFont(Font.font("Arial", fontWeight, fontPosture, 28));
    }

    private void createRightPane() {
        Label optionsLabel = new Label("Set Text String");
        optionsLabel.setFont(Font.font("Arial", 20));

        tfMessage.setText("Programming is Fun");
        text.setText("Programming is Fun");

        updateContainer.getChildren().addAll(optionsLabel, tfMessage);

        updateContainer.setAlignment(Pos.CENTER);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
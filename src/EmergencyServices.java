import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.util.Duration;
/**
 * 
 * EmergencyServices application that takes in calls and allows the user to input the emergency details into the application table and write them to a file.
 *
 * @author Ashleigh
 */
public class EmergencyServices extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Set labels and titles for window
        Label response = new Label("\nResponse Placeholder\n");
        Label title = new Label("Emergency Services\n\n");
        title.setPadding(new Insets(10, 10, 10, 10));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.CADETBLUE);
        response.setTextFill(Color.RED);
        

        //EmergencyCall is the data type, set in the EmergencyCall class
        //Create an array of calls which are visible to the eye
        //Take in name and last name in a form field
        ObservableList<EmergencyCall> contactList = FXCollections.observableArrayList();
        VBox buttonHolder = new VBox();
        VBox deleteCont = new VBox();
        buttonHolder.setPadding(new Insets(10, 50, 50, 10));
        buttonHolder.setSpacing(10);
        Label loadCall = new Label("Incoming Call..");
        Label actions = new Label("Select a call from the table to remove it");
        Button button = new Button("Accept Next Call");
        button.setStyle("-fx-background-color: #98FB98; -fx-text-fill: #333333");
        buttonHolder.getChildren().add(loadCall);
        buttonHolder.getChildren().add(button);
        final Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #FF4500	; -fx-text-fill: #ffffff");
        buttonHolder.getChildren().add(deleteCont);
        deleteCont.getChildren().add(actions);
        deleteCont.getChildren().add(deleteButton);
        deleteCont.setPadding(new Insets(50, 0, 0, 0));
        actions.setPadding(new Insets(10, 0, 10, 0));

        // Create a table to list calls
        TableView<EmergencyCall> emerCalls = new TableView<>(contactList);

        //Take in a Contact, return a String in column
        TableColumn<EmergencyCall, String> fName = new TableColumn<>("First Name");
        // Associate data model name from our class (firstName)
        fName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        // Add it to my first column
        emerCalls.getColumns().add(fName);
        System.out.println(fName);

        TableColumn<EmergencyCall, String> lName = new TableColumn<>("Last Name");
        lName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emerCalls.getColumns().add(lName);

        TableColumn<EmergencyCall, String> callId = new TableColumn<>("Call ID");
        callId.setCellValueFactory(new PropertyValueFactory<>("callId"));
        emerCalls.getColumns().add(callId);

        TableColumn<EmergencyCall, String> date = new TableColumn<>("Call Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        emerCalls.getColumns().add(date);

        TableColumn<EmergencyCall, String> category = new TableColumn<>("Category");
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        emerCalls.getColumns().add(category);

        TableColumn<EmergencyCall, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        emerCalls.getColumns().add(description);

        TableColumn<EmergencyCall, String> address = new TableColumn<>("Address");
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        emerCalls.getColumns().add(address);

        emerCalls.setPrefWidth(400);
        emerCalls.setPrefHeight(300);

        // Allow table values to be selectable and be captured
        TableView.TableViewSelectionModel<EmergencyCall> callerSelContact
                = emerCalls.getSelectionModel();

        // Add Header
        Label headerLabel = new Label("Emergency Details");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        GridPane secondaryLayout = new GridPane();
        //secondaryLayout.getChildren().add(secondLabel);

        secondaryLayout.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add Name Label & Text Field
        Label nameLabel = new Label(" First Name : ");
        secondaryLayout.add(nameLabel, 0, 1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        secondaryLayout.add(nameField, 1, 1);

        Label lnameLabel = new Label(" Last Name : ");
        secondaryLayout.add(lnameLabel, 0, 2);

        // Add Name Text Field
        TextField lnameField = new TextField();
        lnameField.setPrefHeight(40);
        secondaryLayout.add(lnameField, 1, 2);

        // Add Emergency Description Label
        Label emergencyLabel = new Label(" Emergency Description : ");
        secondaryLayout.add(emergencyLabel, 0, 3);

        // Add Emergency Description Text Field
        TextArea emergencyField = new TextArea();
        emergencyField.setPrefHeight(80);
        secondaryLayout.add(emergencyField, 1, 3);
        emergencyField.setStyle("text-area-background: transparent;");

        // Add Address Label
        Label addressLabel = new Label(" Address : ");
        secondaryLayout.add(addressLabel, 0, 4);

        // Add Address Text Field
        TextArea addressField = new TextArea();
        addressField.setPrefHeight(80);
        secondaryLayout.add(addressField, 1, 4);

        // Add Emergency Category Label
        Label emergencyCategoryLabel = new Label(" Emergency Category : ");
        secondaryLayout.add(emergencyCategoryLabel, 0, 5);
        ChoiceBox<String> dropdown = new ChoiceBox<>();

        // Add Emergency Category Dropdown
        dropdown.getItems().addAll("Police", "Ambulance", "Fire Station");
        dropdown.getSelectionModel().select(0);
        secondaryLayout.add(dropdown, 1, 5);

        Label labelresponse = new Label();
        secondaryLayout.add(labelresponse, 0, 6);
        
        
        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        secondaryLayout.add(submitButton, 0, 6, 2, 1);
        GridPane.setHalignment(submitButton, HPos.RIGHT);
        GridPane.setMargin(submitButton, new Insets(20, 20, 20, 20));
        
        

        submitButton.setOnAction(e
                -> {
            // Get values from fields and save them as variable
            String newName = nameField.getText();
            String newLName = lnameField.getText();
            String newCat = dropdown.getValue();
            String newDesc = emergencyField.getText();
            String addressEmer = addressField.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String theDate  = dateFormat.format(new Date());
            
            if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
                labelresponse.setText("Please fill in all of the fields");
            }
            else  {
                 //Create a new object with all of the filed values
                EmergencyCall emergency = new EmergencyCall(newName, newLName, "", theDate, addressEmer, newCat, newDesc);
                // Add object values to table row
                contactList.add(emergency);
                // Write the object values to the text file
                writeList(contactList);
                // Close the form window on submit
                ((Node) (e.getSource())).getScene().getWindow().hide();
                // Clear the field values
                nameField.clear();
                lnameField.clear();
                emergencyField.clear();
                addressField.clear();
            }
        });
        //Set the size of the form window
        Scene secondScene = new Scene(secondaryLayout, 800, 400);

        // New stage (Form)
        Stage newWindow = new Stage();
        newWindow.setTitle("Caller Input");
        newWindow.setScene(secondScene);
        secondScene.getStylesheets().add(EmergencyServices.class.getResource("styles.css").toExternalForm());

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);
        newWindow.show();
        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);
        response.setFont(Font.font("Arial", 14));

        //Add elements to the primary stage
        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setLeft(buttonHolder);
        root.setCenter(emerCalls);

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Emergency Services");
        primaryStage.setScene(scene);
        emerCalls.setPadding(new Insets(10, 10, 10, 10));
        //Add stylesheet
        scene.getStylesheets().add(EmergencyServices.class.getResource("styles.css").toExternalForm());
        primaryStage.show();
        
        // Hide the Form window until call is answered on button click
        newWindow.hide();
        button.setOnAction((ActionEvent event) -> {
            newWindow.show();
        });

        //Button flashing animation
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), evt -> button.setVisible(false)),
                new KeyFrame(Duration.seconds(1.0), evt -> button.setVisible(true)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Action to delete call form table
        deleteButton.setOnAction(e -> {
            EmergencyCall selectedItem = emerCalls.getSelectionModel().getSelectedItem();
            emerCalls.getItems().remove(selectedItem);
        });
    }

        
    
    // Write array of emergency calls to textfile, expecting the list of emergency calls as a parameter
    static void writeList(List<EmergencyCall> emerListIn) {

        try (
                // Create an EmercencyCall object and asssociate it with input.txt
                FileWriter inputFile = new FileWriter("callList.txt");
                // Wrap the EmercencyCall object in the emerWriter object
                PrintWriter emerWriter = new PrintWriter(inputFile);) {
            // Loop through the EmercencyCalls and print the values in the textfile
            for (Iterator<EmergencyCall> it = emerListIn.iterator(); it.hasNext();) {
                EmergencyCall item = it.next();
                emerWriter.println("CallId:" + item.getCallId());
                emerWriter.println("First Name: " + item.getFirstName());
                emerWriter.println("Last Name: " + item.getLastName());
                emerWriter.println("Address: " + item.getAddress());
                emerWriter.println("Category: " + item.getCategory());
                emerWriter.println("Description: " + item.getDescription());
                emerWriter.println("Date: " + item.getDate());
                emerWriter.println();
            }
        } // Print error message if an exception has been thrown
        catch (IOException e) {
            System.out.println("error writing the file");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

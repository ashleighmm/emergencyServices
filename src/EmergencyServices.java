
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
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

public class EmergencyServices extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label response = new Label("\nResponse Placeholder\n");
        Label title = new Label("Incoming Calls\n");
        //Label showCallID = new Label("Caller ID: ");
        Label showDate = new Label("Call Time: ");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.CADETBLUE);


        //acceptCall();
        //EmergencyCall is the data type, set in the EmergencyCall class
        //Create an array of calls which are visible to the eye
        //Take in name and last name in a form field
        ObservableList<EmergencyCall> contactList = FXCollections.observableArrayList();

        VBox buttonHolder = new VBox();
        buttonHolder.setPadding(new Insets(10, 50, 50, 50));
        buttonHolder.setSpacing(10);
        Label loadCall = new Label("Incoming Call..");
        Button button = new Button("Accept");
        buttonHolder.getChildren().add(loadCall);
        buttonHolder.getChildren().add(button);
        

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

        emerCalls.setPrefWidth(300);
        emerCalls.setPrefHeight(300);

        // Allow table values to be selectable and be captured
        TableView.TableViewSelectionModel<EmergencyCall> callerSelContact
                = emerCalls.getSelectionModel();

        // Get the index of the selected caller
        //callerSelContact.selectedIndexProperty().addListener((ObservableValue<? extends Number> changed, Number oldVal, Number newVal) -> {
//        callerSelContact.selectedIndexProperty().addListener((ObservableValue<? extends Number> changed, Number oldVal, Number newVal) -> {
//            int index = (int) newVal;
        // get the caller Id number of the selected contact in the array of objects with the index value
        //response.setText("The id number for the contact selected is "
        //      +contactList.get(index).getCallId());
        // Print out the ID and time of the call
        // Create new window for caller input form 
        //Label secondLabel = new Label("Caller ID: " + contactList.setCallId());
        // Add Header
        Label headerLabel = new Label("Emergency Details");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        GridPane secondaryLayout = new GridPane();
        //secondaryLayout.getChildren().add(secondLabel);

        secondaryLayout.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add Name Label & Text Field
        Label nameLabel = new Label("First Name : ");
        secondaryLayout.add(nameLabel, 0, 1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        secondaryLayout.add(nameField, 1, 2);

        Label lnameLabel = new Label("Last Name : ");
        secondaryLayout.add(lnameLabel, 0, 2);

        // Add Name Text Field
        TextField lnameField = new TextField();
        lnameField.setPrefHeight(40);
        secondaryLayout.add(lnameField, 1, 3);

        // Add Emergency Description Label
        Label emergencyLabel = new Label("Emergency Description : ");
        secondaryLayout.add(emergencyLabel, 0, 3);

        // Add Emergency Description Text Field
        TextArea emergencyField = new TextArea();
        emergencyField.setPrefHeight(80);
        secondaryLayout.add(emergencyField, 1, 4);

        // Add Address Label
        Label addressLabel = new Label("Address : ");
        secondaryLayout.add(addressLabel, 0, 3);

        // Add Address Text Field
        TextArea addressField = new TextArea();
        addressField.setPrefHeight(80);
        secondaryLayout.add(addressField, 1, 4);

        // Add Emergency Category Label
        Label emergencyCategoryLabel = new Label(" Emergency Category : ");
        secondaryLayout.add(emergencyCategoryLabel, 0, 4);

        ChoiceBox<String> dropdown = new ChoiceBox<>();

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
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        submitButton.setOnAction(e
                -> {
            String newName = nameField.getText();
            String newLName = lnameField.getText();
            String newCat = dropdown.getValue();
            String emergencyDesc = emergencyField.getText();
            String addressEmer = addressField.getText();

            if (nameField.getText() != null) {
                labelresponse.setText(nameField.getText() + " "
                        + lnameField.getText() + " needs "
                        + dropdown.getValue() + " response ");

                
                contactList.add(new EmergencyCall(newName, newLName, "", "", addressEmer, newCat, emergencyDesc));

            }
            //writeList(contactList);
            ((Node)(e.getSource())).getScene().getWindow().hide();
            nameField.clear();
            lnameField.clear();
            emergencyField.clear();
            addressField.clear();
            primaryStage.toFront();
        });

        Scene secondScene = new Scene(secondaryLayout, 800, 800);
        secondaryLayout.toFront();

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Caller Input");
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);
        newWindow.show();
        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        //showDate.setText("Call Time: " + contactList.get(index).getDate());
        
//        });

        response.setFont(Font.font("Arial", 14));

        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setLeft(buttonHolder);
        //root.setBottom(response);
        root.setCenter(emerCalls);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Emergency Services");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        button.setOnAction((ActionEvent event) -> {
            newWindow.show();
            primaryStage.toBack();
        });
    }
    
    
    

    
    

    public boolean acceptCall() {
        //Set up the alert box that appears when a call comes in to accept or reject it
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Emergency Services");
        alert.setHeaderText("Incoming Call...");
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Accept", ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Reject", ButtonData.CANCEL_CLOSE);

        // Add the buttons to the alert box
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        // Do something when Accept button is selected
        Optional<ButtonType> option = alert.showAndWait();
        return buttonTypeOne.equals(option.get());
    }

    // Write array of cars to textfile, expecting the list of cars as a parameter
    static void writeList(List<EmergencyCall> carListIn) {

        try (
                // Create a carFile object and asssociate it with Cars.txt
                FileWriter inputFile = new FileWriter("input.txt");
                // Wrap the carFile object in the carWriter object
                PrintWriter callWriter = new PrintWriter(inputFile);) {
            // Loop through the Cars and print the values in the textfile
            for (EmergencyCall item : carListIn) {
                callWriter.println("First Name: " + item.getFirstName());
                callWriter.println("Last Name: " + item.getLastName());
                callWriter.println("Category:" + item.getCategory());
                callWriter.println();
                callWriter.println("Hello");
            }
        } // Print error message if an exception has been thrown
        catch (IOException e) {
            System.out.println("error writing the file");
        }

    }

    static void readList(List<EmergencyCall> carListIn) {
        String tempFirstName;
        String tempLastName;
        String tempId;
        boolean endOfFile = false;

        // use try-with-resources to ensure file is closed safely
        try (
                FileInputStream carFile = new FileInputStream("Cars.bin");
                DataInputStream carStream = new DataInputStream(carFile);) {
            while (endOfFile == false) {
                try {
                    tempFirstName = carStream.readUTF();
                    tempLastName = carStream.readUTF();
                    tempId = carStream.readUTF();
                    //carListIn.add(new EmergencyCall(newName, newLName, id, "", addressEmer, newCat, emergencyDesc));
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nThere are currently no records");
        } catch (IOException e) {
            System.out.println("There was a problem reading the file");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void WriteObjectToFile(Object serObj) {

        try {

            FileOutputStream fileOut = new FileOutputStream("input.txt");

            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

}
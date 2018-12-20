import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
/**
 * 
 * EmergencyCall class getters and setters
 *
 * @author Ashleigh
 */
public final class EmergencyCall implements Serializable {
    private static final long serialVersionUID = 1L;

    //SimpleStringProperty class creates a string property that is readable and writable to the wrapped string value
    private final SimpleStringProperty firstName = new SimpleStringProperty("");
    private final SimpleStringProperty lastName = new SimpleStringProperty("");
    private final SimpleStringProperty callerId = new SimpleStringProperty("");
    private final SimpleStringProperty date = new SimpleStringProperty("");
    private final SimpleStringProperty address = new SimpleStringProperty("");
    private final SimpleStringProperty category = new SimpleStringProperty("");
    private final SimpleStringProperty description = new SimpleStringProperty("");

    public EmergencyCall(String firstName, String lastName, String callId, String date, String address, String category, String description) {
        setFirstName(firstName);
        setLastName(lastName);
        setCallId(callId);
        setDate(date);
        setAddress(address);
        setCategory(category);
        setDescription(description);
    }

    

    public String getDate() {
        //Date mydate = new Date(); 

        // Displaying the current date and time 
        //System.out.println("System date : "+ mydate.toString() ); 
        //return mydate;
        return date.get();
        
    }

    public void setDate(String newDate) {
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
       String theDate  = dateFormat.format(new Date());
       date.set(newDate);
    }

//    public String getCallId() {
//        String callId = UUID.randomUUID().toString();
//        return callId;
//    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String fName) {
        lastName.set(fName);
    }
    
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String addr) {
        address.set(addr);
    }
    
    //set a randomly generated secure ID for each caller
    public void setCallId(String callId) {
        callerId.set(UUID.randomUUID().toString());
    }
    
    public String getCallId()
    {
         return callerId.get();
    }
    
    public String getCategory() {
        return category.get();
    }

    public void setCategory(String cat) {
        category.set(cat);
    }
    
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String desc) {
        description.set(desc);
    }

}

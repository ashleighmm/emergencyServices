
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ashleigh
 */
public final class EmergencyCall {

   // public String callId;
//    public Date date;

    //SimpleStringProperty class creates a string property that is readable and writable to the wrapped string value
    private final SimpleStringProperty firstName = new SimpleStringProperty("");
    private final SimpleStringProperty lastName = new SimpleStringProperty("");
    private final SimpleStringProperty callerId = new SimpleStringProperty("");
    private final SimpleStringProperty date = new SimpleStringProperty("");
    private final SimpleStringProperty address = new SimpleStringProperty("");
    private final SimpleStringProperty category = new SimpleStringProperty("");
    private final SimpleStringProperty emergency = new SimpleStringProperty("");

    public EmergencyCall(String firstName, String lastName, String callId, String date, String address, String category, String emergency) {
        setFirstName(firstName);
        setLastName(lastName);
        setCallId(callId);
        setDate(date);
        setAddress(address);
        setCategory(category);
        setEmergency(emergency);
    }

    

    public String getDate() {
        //Date mydate = new Date(); 

        // Displaying the current date and time 
        //System.out.println("System date : "+ mydate.toString() ); 
        //return mydate;
        return date.get();
    }

    public void setDate(String newDate) {
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
    
     public String getEmergency() {
        return emergency.get();
    }

    public void setEmergency(String emerg) {
        emergency.set(emerg);
    }
   

}

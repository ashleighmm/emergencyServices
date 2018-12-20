
/**
 *
 * @author Ashleigh
 */

public class Police{
    private EmergencyCall refClassObject;

    public Police(){
    }

    public Police(EmergencyCall refClassObject){
        this.refClassObject = refClassObject;
    }

    public void setReferenceClass(EmergencyCall refClassObject){
        this.refClassObject = refClassObject;
    }

    public EmergencyCall getReferenceClass(){
        return refClassObject;
    }
}
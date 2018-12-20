/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
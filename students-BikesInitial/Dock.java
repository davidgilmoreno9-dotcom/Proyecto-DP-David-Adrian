import java.util.ArrayList;
import java.util.List;
/**
 * Model a dock unit within a {@link BikeStation}.
 * It tracks its charging capabilities, fee, and the electric bikes it has recharged.
 * @author David J. Barnes and Michael Kölling
 * @author DP classes 
 * @version 2026.10.13
 */
public class Dock
{
    private String idDock;
    private int chargingSpeed;
    private float chargingFee;
    private ArrayList<ElectricBike> bikesRecharged;
    private float amountCollected;
    private boolean free;

    /**
     * Constructor for objects of class Dock.
     * @param id. The id of the dock.
     * @param speed. The charging speed of the dock.
     * @param fee. The charging fee of the dock.
     */
    public Dock(String id, int speed, float fee)
    {
        this.idDock = id;
        this.chargingSpeed = speed;
        this.chargingFee = fee;
        this.bikesRecharged = new ArrayList<>();
        this.amountCollected = 0;
        this.free = true;
    }

    /**
     * Add an {@link ElectricBike} to the bikesRecharged list.
     * @param  EV. An {@link ElectricBike} to add.
     */
    public void addBike(ElectricBike EV)
    {
        bikesRecharged.add(EV);
    }
    
    /**
     * Change the value of amountCollected to amount.
     * @param amount. New amount of amountCollected.
     */
    public void setAmountCollected(float amount){
        amountCollected = amount;
    }
    
    /**
     * Returns a string representation of the dock, including its ID, speed, fee, and the number of EVs recharged.
     * @return A string representation of the dock.
     */
    public String toString()
    {
        return idDock + " " + chargingSpeed + " " + chargingFee + " " + bikesRecharged;
    }    
    
    /**
     * Returns the identifier of this dock.
     *
     * @return the dock's identifier
     */
    public String getIdDock()
    {
        return idDock;
    }
    
    /**
     * @return The charging speed of this dock.
     */
    public int getChargingSpeed(){
        return chargingSpeed;
    }

    /**
     * @return The charging fee of the dock.
     */
    public float getChargingFee(){
        return chargingFee;
    }

    /**
     * @return The total number of {@link ElectricBike}s that have been recharged by this dock.
     */
    public int getNumerEVRecharged(){
        return bikesRecharged.size();
    }

    /**
     * Simulates the charging process for an {@link ElectricBike}.
     * Increases the amount collected and registers the bike as recharged.
     * //TODO complete the JavaDoc comments
     */
    public float recharge(int charge, ElectricBike EV){
        addBike(EV);
        return charge * chargingFee;
    }

    //TODO: Complete this code

}

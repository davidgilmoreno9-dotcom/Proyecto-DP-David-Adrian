import java.util.ArrayList;
import java.util.List;
/**
 * Model a dock unit within a {@link BikeStation}.
 * It tracks its charging capabilities, fee, and the electric bikes it has recharged.
 * @author David J. Barnes and Michael Kölling
 * @author DP classes 
 * @author Adrián Serrat Leal
 * @version 2026.11.15
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
    public void addBike(ElectricBike ev){
        bikesRecharged.add(ev);
    }
    
    /**
     * Change the value of amountCollected to amount.
     * @param amount. New amount of amountCollected.
     */
    public void setAmountCollected(float amount){
        amountCollected = amount;
    }
    
    /**
     * Change the value of amountCollected to amount.
     * @param amount. New amount of amountCollected.
     */
    public void setOcupedDock(){
        free = false;
    }
    
    
    /**
     * Returns a string representation of the dock, including its ID, speed, fee, and the number of EVs recharged.
     * @return A string representation of the dock.
     */
    @Override
    public String toString(){
         return "(Dock: " + idDock + ", " + chargingSpeed + "wh, " 
             + String.format(java.util.Locale.US, "%.1f", chargingFee) + "€, "
             + getNumberEVRecharged() + ", "
             + String.format(java.util.Locale.US, "%.1f", amountCollected) + "€)";
    }    
    
    /**
     * Returns the identifier of this dock.
     *
     * @return the dock's identifier
     */
    public String getIdDock(){
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
    public int getNumberEVRecharged(){
        return bikesRecharged.size();
    }
    
    /**
     * @return The list of recharged bikes.
     */
    public ArrayList<ElectricBike> getBikesRecharged()
    {
        return bikesRecharged;
    }
    /**
     * @return the amount collected of this dock.
     */
    public boolean getDockFree(){
        return free;
    }

    /**
     * Simulates the charging process for an {@link ElectricBike}.
     * Increases the amount collected and registers the bike as recharged.
     * @return cost of the recharge.
     */
    public float recharge(int charge, ElectricBike ev){
        addBike(ev);
        float cost = charge * chargingFee;
        amountCollected += cost;
        return cost;
    }
    

}

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Model the common elements of an Electric bike (EB) that operates 
 * within the simulation, moving towards a target and potentially recharging.
 * * @author David J. Barnes and Michael Kölling
 * @author DP classes
 * @author Adrián Serrat Leal
 * @version 2026.10.13
 */
public class ElectricBike 
{
    private String idBike;
    private EBCompany company;
    private Location location;
    private Location rechargingLocation;
    private Location targetLocation;
    private int idleCount;
    private int batteryCapacity;
    private int batteryLevel;
    private int wsCharged;
    private int chargesCount;
    private float chargesCost;
    private ArrayList<Dock> docks;
    private int arrivalStep = -1;
    
    /**
     * Constructor of class ElectricBike.
     * @param company The EB's operating company. Must not be null.
     * @param location The EB's starting {@link Location}. Must not be null.
     * @param targetLocation The EB's final destination {@link Location}. Must not be null.
     * @param id The identificator of the bike.
     * @param batteryCapacity The maximum capacity of the battery.
     * @throws NullPointerException If company, location, or targetLocation is null.
     */
    public ElectricBike(EBCompany company, Location location, Location targetLocation, String id, int batteryCapacity)
    {
           if (company == null || location == null || targetLocation == null){
               throw new NullPointerException("company, location and targetLocation must not be null");    
           } 
           this.idBike = id;
           this.company = company;
           this.location = location;
           this.targetLocation = targetLocation;
           this.rechargingLocation = null;
           this.batteryCapacity = batteryCapacity;
           this.arrivalStep = -1;
           this.idleCount = 0;
           this.batteryLevel = batteryCapacity;
           this.wsCharged = 0;
           this.chargesCount = 0;
           this.chargesCost = 0;
           this.docks = new ArrayList<>();
    }
   
    /**
     * @return The arrivalStep of the electric bike.
     */
    public int getArrivalStep(){
        return arrivalStep;
    }
    
    /**
     * @return The docks visited by the bike.
     */
    public ArrayList<Dock> getDocks(){
        return docks;
    }
    
    /**
     * @return The id of the electric bike.
     */
    public String getIdBike(){
        return idBike;    
    }

    /**
     * Get the the bikes location.
     * @return Where this bike is located.
     */
    public Location getLocation(){
        return location;  
    }
    
    /**
     * Get the final target location.
     * @return Where this bike is ultimately headed.
     */
    public Location getTargetLocation(){
        return targetLocation;  
    }

    /**
     * Get the temporary recharging location.
     * @return The {@link Location} of the next {@link BikeStation} to visit, or null if no recharge is planned.
     */
    public Location getRechargingLocation(){
        return rechargingLocation;
    }
    

    /**
     * Set the current location.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location){
        if (location == null){
            throw new NullPointerException("New location cannot be null");
        }
        this.location = location;
        calculateRoute();
    }

    
    /**
     * Set the required final target location.
     * @param location Where to go. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setTargetLocation(Location location){
        if (location == null){
            throw new NullPointerException("The new targetLocation can not be null");
        }
        targetLocation = location;
        arrivalStep = -1;
        calculateRoute();
    }

    /**
     * Calculates the optimal route for the bike.
     * If there is enough battery to reach the target, it changes the rechargingLocation to null.
     * If there isn't enough battery to reach the target, it attempts to find an intermediate 
     * {@link BikeStation} and sets it as the {@code rechargingLocation}.
     */
    public void calculateRoute(){
        if (enoughBattery(targetLocation)) {
            rechargingLocation = null;
        } else {
            BikeStation best = company.findBestStation(this);
            rechargingLocation = (best != null) ? best.getLocation() : null;
        }
    }

    /**
     * Gets a string representation of the planned route, including the recharging stop if one exists.
     * @return A string showing the route: {@code currentLocation -> [rechargingLocation ->] targetLocation}.
     */
    public String getStringRoute(){
        if (rechargingLocation == null){
            return location.toString() + "->" + targetLocation.toString();
        }
        return location.toString() + "->" + rechargingLocation.toString() + "->" + targetLocation.toString();
    }

    /**
     * Checks whether the current battery level is sufficient to reach a given {@link Location}.
     * The bike consumes {@code EBCompany.MOVINGCOST}ws per step so it could travel up to {@code batteryLevel / EBCompany.MOVINGCOST} steps.
     * @param endLocation, The {@link Location} checked as destiny.
     * @return {@code true} if the destiny is reachable and {@code false} otherwise.
     */
    public boolean enoughBattery(Location endLocation)
    {
        return (batteryLevel / EBCompany.MOVINGCOST >= this.location.distance(endLocation));
    }

    /**
     * Get the Manhattan-like distance to the final target location from the current location.
     * @return The distance to the target location.
     */
    public int distanceToTheTargetLocation()
    {
        return location.distance(targetLocation);
    }

    /**
     * Simulates the recharging process when the bike arrives at its
     * {@code rechargingLocation}.
     *
     * <p>  If the station has a free {@link Dock}, the bike occupies it, the battery
     *      is charged to full capacity, the cost is calculated, and the station is
     *      marked as visited. If there is no free dock, the method returns without
     *      making any change.</p>
     *
     * <p>  On a successful recharge, {@code wsCharged}, {@code idleCount},
     *      {@code chargesCount} and {@code chargesCost} are updated accordingly.</p>
     */
    public Dock recharge(){
        BikeStation station = company.getBikeStation(rechargingLocation);
        Dock currentDock = station.getFreeDock();
        if (currentDock == null){
            return null;
        }
        this.docks.add(currentDock);
        int charge = batteryCapacity - batteryLevel;
        float cost = currentDock.recharge(charge, this);
        this.wsCharged += charge;
        this.batteryLevel = batteryCapacity;
        this.chargesCount++;
        this.chargesCost += cost;
        calculateRoute();
        return currentDock;
    } 

    /**
     * Reduces the battery level by the cost of one movement step (defined in {@link EBCompany#MOVINGCOST}).
     */
    public void reduceBatteryLevel(){
        this.batteryLevel -= EBCompany.MOVINGCOST;   
    }

    /**
     * Carries out a single step of the bike's actions.
     * Moves one step towards the target (recharging or final) or stays idle.
     * First, recalculates the route. If no recharge is needed, it notifies when 
     * approaching the target destination and moves forward. 
     * If a recharge is needed, it checks if the bike is at the station to use a free dock and moves towards it. 
     * Finally, it prints the bike's current status to the console.
     *
     * @param step The current step of the simulation.
     */
    public void act(int step ){
        boolean stayIdle = false;
        if (rechargingLocation == null){
            if (location.isAdjacent(targetLocation) && enoughBattery(targetLocation)){
                System.out.println("(step: " + step + " - ElectricBike: " + idBike + " at target destination ********)");
                arrivalStep = step;
            }
            if (location.equals(targetLocation) || !enoughBattery(targetLocation)){
                //This is only for the case the bike is created or gets assigned on its own location as destination.
                if (location.equals(targetLocation) && arrivalStep == -1) {
                    System.out.println("(step: " + step + " - ElectricBike: " + idBike + " at target destination ********)");
                    arrivalStep = step;
                }
                stayIdle = true;
            }
            else { 
                location = location.nextLocation(targetLocation);
                reduceBatteryLevel();
                idleCount = 0;
            }
            
        }
        else{
            if (enoughBattery(rechargingLocation)){
                if (location.distance(rechargingLocation) <= 1){
                    if (location.isAdjacent(rechargingLocation)){
                        location = location.nextLocation(rechargingLocation);
                        reduceBatteryLevel();
                    }
                    stayIdle = true;
                    int charge = batteryCapacity - batteryLevel;
                    Dock currentDock = recharge();
                    if (currentDock != null){
                        float cost = charge * currentDock.getChargingFee();
                        System.out.printf(java.util.Locale.US, "(step: %d - ElectricBike: %s recharges: %dwh at dock: %s with cost: %.1f€ ********)%n",
                                            step, idBike, charge, currentDock.getIdDock(), cost);
                    }
                }
                else {
                    location = location.nextLocation(rechargingLocation);
                    reduceBatteryLevel();
                    idleCount = 0;
                }
            }
            else{
                stayIdle = true;
            }
        }
        if (rechargingLocation == null){
            System.out.printf(java.util.Locale.US, "(step: %d - ElectricBike: %s, %dwh, %dwh, %d, %.1f€, %d, %s, %s)%n", step, 
                                idBike, batteryCapacity, batteryLevel, chargesCount, chargesCost, idleCount,
                                location, targetLocation);
        }
        else{
            System.out.printf(java.util.Locale.US, "(step: %d - ElectricBike: %s, %dwh, %dwh, %d, %.1f€, %d, %s, %s, %s)%n", step, 
                                idBike, batteryCapacity, batteryLevel, chargesCount, chargesCost, idleCount,
                                location, rechargingLocation, targetLocation);
        }
        if (stayIdle) {
            idleCount++;
        }
    }

    
    /**
     * Returns a detailed string representation of the electric bike.
     * @return The bike's state as 
     * {@code (ElectricBike: <id>, <cap>wh, <level>wh, <charges>, <cost>€, <idle>, <loc>[, <rechargeLoc>], <target>)}.
     */
    @Override
    public String toString(){
        String result = "(ElectricBike: " + idBike 
                      + ", " + batteryCapacity + "wh"
                      + ", " + batteryLevel + "wh"
                      + ", " + chargesCount
                      + ", " + String.format(java.util.Locale.US, "%.1f", chargesCost) + "€" //To express as 0.0€
                      + ", " + idleCount
                      + ", " + location;
        
        if (rechargingLocation != null) {
            result = result + ", " + rechargingLocation;
        }
        
        return result + ", " + targetLocation + ")";

    }

   
}

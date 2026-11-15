import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Models an Electric Vehicle Charging Station.
 * A charging station contains multiple {@link Dock} units.
 * @author DP classes 
 * @version 2026.10.13
 */
public class BikeStation
{
    private String idStation;
    private Location location;
    private ArrayList<Dock> docks;
    
    /**
     * Constructor for objects of class BikeStation.
     */
    public BikeStation(  )
    {
        idStation = "CC00";
        location = new Location (0,0);
        docks = new ArrayList<>();
    }

    /**
     * Constructor for objects of class BikeStation.
     * @param id El ID de la estacion
     * @param location La localizacion de la estacion
     */
    public BikeStation(String id, Location location)
    {
        idStation = id;
        this.location = location;
        docks = new ArrayList<>();
    }
    
    /**
     * Retrieves the first free {@link Dock} found at the station.
     * @return A free {@link Dock}, or possibly throws an exception if none are found (depending on stream implementation details).
     * **Note: The implementation assumes at least one free dock exists if called.**
     */
    public Dock getFreeDock()
    {
        for(int i = 0; i < docks.size(); i++){
            if (docks.get(i).getDockFree() == true){
                return docks.get(i);
            } 
        }
        return null;
    }
    
    /**
     * @return The total number of bikes {@link Docks}s.
     */
    public int getNumberOfDocks(){
        return docks.size();
    }
    
    /**
     * Returns the number of bikes that have been recharged in any dock of this station.
     * A bike that recharges more than once in the same station is counted each time.
     * @return The total number of recharges performed in this station's docks.
     */
    public int getNumBikesRecharged()
    {
        int total = 0;
        for (Dock d : docks) {
            total += d.getNumberEVRecharged();
        }
        return total;
    }
    /**
     * Set the current location of the charging station.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location)
    {
       this.location = location;   
    }
    
    /**
     * @return The unique identifier of the charging station.
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * @return The unique identifier of the charging station.
     */
    public String getId()
    {   
        return idStation;
    }
    
    /**
     * @return La coleccion de docks que hay en la estación.
     */
    public ArrayList<Dock> getDocks()
    {   
        return docks;
    }
    
    
    /**
     * @return el dock con el id que hemos establecido.
     */
    public Dock getDock(String id)
    {   
        for(int i = 0; i < docks.size(); i++){
            if (docks.get(i).getIdDock().equals(id)){
                return docks.get(i);
            } 
        }
        return null;
    }
    
    /**
     * Adds a new {@link Dock} to the station.
     * @param dock The new Dock unit.
     */
    public void addDock(Dock dock)
    {
        for(int i = 0; i < docks.size(); i++) {
            if (dock.getChargingSpeed() > docks.get(i).getChargingSpeed()){
                docks.add(i, dock);
                return;
            }
            else if (dock.getChargingSpeed() == docks.get(i).getChargingSpeed()){
                if (dock.getChargingFee() < docks.get(i).getChargingFee()){
                    docks.add(i, dock);
                    return;
                }
                else if (dock.getChargingFee() == docks.get(i).getChargingFee()){
                    if (dock.getIdDock().compareTo(docks.get(i).getIdDock()) < 0){
                        docks.add(i,dock);
                        return;
                    }
                }
                }        
            }
        docks.add(dock);
    }
    
    /**
     * Returns a detailed string representation of the bike station.
     * @return The bike station's state as 
     * {@code (BikeStation: <id>, <numberOfBikesRecharged>, <location>)}.
     */
    @Override
    public String toString() {
        return "(BikeStation: " + idStation + ", " + getNumBikesRecharged() + ", " + location + ")";
    }
    
    


}

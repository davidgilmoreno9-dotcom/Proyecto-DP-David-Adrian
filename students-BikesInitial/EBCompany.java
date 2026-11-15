import java.util.*;

/**
 * Models the operation of an Electric Bike (EB) Company. 
 * It manages a fleet of {@link ElectricBike}s and a network of {@link BikeStation}s.
 * * @author David J. Barnes and Michael Kölling
 * @author DP classes
 * @version 2026.10.13
 */
public class EBCompany  
{
    private String name;
    private ArrayList<ElectricBike> subscribed_bikes;
    private ArrayList<BikeStation> stations;
    public static final int MOVINGCOST = 5; 
    
    /**
     * Constructor for objects of class EVCompany.
     * @param name The name of the company.
     */
    public EBCompany(String name)
    {
        this.name = name;
        subscribed_bikes = new ArrayList<>();
        stations = new ArrayList<>();
    }
    
    /**
     * @return the name of the EBCompany 
     */
    public String getName(){
        return name;
    }

    /**
     * Adds an {@link ElectricBike} to the company's fleet.
     * @param bike The electric bike to add.
     */
    public void addElectricBike(ElectricBike bike)
    {       
        subscribed_bikes.add(bike);
    }

    
    /**
     * Adds a {@link BikeStation} to the company's network.
     * @param station The bike station to add.
     */
    public void addBikeStation(BikeStation station)
    {       
        stations.add(station);
    }
    
    
    /**
     * Retrieves a {@link BikeStation} by its unique ID.
     * @param id The ID of the station to find.
     * @return The {@link BikeStation} with the matching ID, or {@code null} if not found.
     */
    public BikeStation getBikeStation(String id)
    {
       for (int i = 0; i < stations.size();i++){
            if(stations.get(i).getId().equals(id)){
                return stations.get(i);
            }
        }
        return null;
    }
    /**
     * Finds the closest {@link BikeStation} to the given origin that the specified
     * {@link ElectricBike} can reach with its current battery level.
     * @param EV The electric bike whose current location is the route's origin
     *           and whose battery level determines which stations are reachable
     * @return The reachable {@link BikeStation} with the shortest adjusted route to targetLocation  ,
     *          or {@code null} if np station is reachable with the bike's
     *         current battery level (or if {@link #stations} is empty)
     */
    public BikeStation findBestStation(ElectricBike ev){
        return stations.stream()
                .filter(station -> ev.enoughBattery(station.getLocation()))
                .filter(station -> !ev.getLocation().equals(station.getLocation()))
                .min(Comparator.comparingInt(station -> ev.getLocation().distance(station.getLocation())
            + station.getLocation().distance(ev.getTargetLocation())))
                .orElse(null);
    }
    
    /**
     * Retrieves a {@link BikeStation} by its {@link Location}.
     * @param location The {@link Location} of the station to find.
     * @return The {@link BikeStation} at the matching location, or {@code null} if not found.
     */
    public BikeStation getBikeStation(Location location){
        for (int i = 0; i < stations.size();i++){
            if(stations.get(i).getLocation().equals(location)){
                return stations.get(i);
            }
        }
        return null;
    }
    
    
    /**
     * @return The total number of managed {@link ElectricBikes}s.
     */
    public int getNumberOfStations(){
        return stations.size();
    }
    
    /**
     * @return The total number of bikes {@link BikeStation}s.
     */
    public int getNumberOfBikes(){
        return subscribed_bikes.size();
    }
    
    /**
     * Clears all managed bikes and stations, resetting the company to an empty state.
     */
    public void reset(){
        stations.clear();
        subscribed_bikes.clear();
    }
    
}
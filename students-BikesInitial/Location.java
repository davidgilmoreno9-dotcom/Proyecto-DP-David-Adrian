import java.util.*;

/**
 * Model a location in a two-dimensional grid city using (x, y) coordinates.
 * @author David J. Barnes and Michael Kölling
 * @author DP classes
 * @author Adrián Serrat Leal and David Gil Moreno
 * @version 2026.10.13
 */
public class Location
{
    private int x;
    private int y;

    /**
     * Model a location in the city.
     * @param x The x coordinate. Must be non-negative.
     * @param y The y coordinate. Must be non-negative.
     * @throws IllegalArgumentException If a coordinate is negative.
     */
    public Location(int x, int y)
    {
        if(x < 0) {
            throw new IllegalArgumentException(
                        "Negative x-coordinate: " + x);
        }
        if(y < 0) {
            throw new IllegalArgumentException(
                        "Negative y-coordinate: " + y);
        }        
        this.x = x;
        this.y = y;
    }
    
     /**
     * Generates the next location to visit in a direct line (Chebyshev/diagonal move) 
     * from this location to the destination.
     * @param destination The target {@link Location}.
     * @return The next {@link Location} one step closer to the destination. 
     * Returns the destination if this location is already at destination.
     */
    public Location nextLocation(Location destination)
    {
        int nextX = x; int nextY = y;
        if(destination.x == nextX && destination.y == nextY){
            return destination;
        }
        if(destination.x != nextX){
            nextX = (destination.x > nextX) ? nextX + 1 : nextX - 1;
        }
        if(destination.y != nextY){
            nextY = (destination.y > nextY) ? nextY + 1 : nextY - 1;
        }
        return new Location(nextX,nextY);
    }

    /**
     * Determines the number of movements required to get from here to the destination, 
     * using the Chebyshev distance (maximum of the absolute differences of the coordinates).
     * @param destination The required destination {@link Location}.
     * @return The number of movement steps.
     */
    public int distance(Location destination)
    {
        return Math.max(Math.abs(destination.x - x), Math.abs(destination.y - y));
    }
    
    /**
     * Implement content equality for locations.
     * @param other The object to compare with.
     * @return {@code true} if the other object is a {@link Location} and has the same x and y coordinates, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other)
    {
        if (this == other){
            return true;
        }
        if (!(other instanceof Location)){
            return false;   
        }
        Location location = (Location) other;
        return x == location.x && y == location.y;
    }
    
    /**
     * @return A string representation of the location in the format "x-y".
     */
    @Override
    public String toString()
    {
        return x + "-" + y;
    }

    /**
     * Generates a hash code for the location.
     * It uses the top 16 bits for the y value and the bottom 16 bits for the x value
     * to ensure a unique hash code for most grid sizes.
     * @return A hashcode for the location.
     */
    @Override
    public int hashCode()
    {
        return (y << 16) + x;
    }

    /**
     * @return The x coordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return The y coordinate.
     */
    public int getY()
    {
        return y;
    }
    /**
     * Checks if another location is adjacent (one move away)
     * @param location The other {@link location} to check.
     * @return {@code true} if they are ajacent, {@code false} otherwise.
     */
    public boolean isAdjacent (Location location){
        return (this.distance(location) == 1);
    }
}
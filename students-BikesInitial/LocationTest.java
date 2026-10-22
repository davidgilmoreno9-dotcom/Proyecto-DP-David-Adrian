import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test implementation of the {@link Location} class.
 * Provides unit tests for methods like {@code distance()} and {@code nextLocation()}.
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2026.10.13 DP classes (adaptado a Java 8+)
 */
public class LocationTest
{
    /**
     * Default constructor for test class LocationTest.
     */
    public LocationTest()
    {
    }

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        // No setup required for Location tests
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        // No teardown required for Location tests
    }
    
    /**
     * Test the {@code distance} method of the {@link Location} class.
     * Checks distances from a central location to surrounding points.
     */
    @Test
    public void testDistance()
    {
        Location location1 = new Location(0, 0);
        Location location2 = new Location(10, 10);
        assertEquals(10, location1.distance(location2));
        assertEquals(10, location2.distance(location1));
    }
    
    /**
     * Test the {@code nextLocation} method when the destination is adjacent 
     * (one step away in any direction).
     */
    @Test
    public void testAdjacentLocations()
    {
        Location location1 = new Location(0, 0);
        Location location2 = new Location(1, 1);
        assertEquals(true, location2.isAdjacent(location1));
        assertEquals(true, location1.isAdjacent(location2));
    }
    
    /**
     * Test the {@code nextLocation} method when the destination is not adjacent 
     * (more than one step away).
     */
    @Test
    public void testNonAdjacentLocations()
    {
        Location location1 = new Location(0, 0);
        Location location2 = new Location(2, 0);
        assertEquals(false, location2.isAdjacent(location1));
        assertEquals(false, location1.isAdjacent(location2));
    }


}


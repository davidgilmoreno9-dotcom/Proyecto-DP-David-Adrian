import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class LocationTest.
 *
 * @author  Adrián Serrat Leal
 * @version 7/11/2026
 */
public class LocationTest
{
    /**
     * Default constructor for test class LocationTest
     */
    public LocationTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
     /**
     * Test that a Location object is correctly intialised
     */
    @Test
    public void testConstructorValido()
    {
        Location loc = new Location(10, 6);
        assertEquals(10, loc.getX());
        assertEquals(20, loc.getY());
    }
     /**
     * Test negative x exception.
     */
    @Test
    public void testConstructorXNegativa()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            new Location(-5, 10);
        });
    }

    /**
     * Test negative y exception.
     */
    @Test
    public void testConstructorYNegativa()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            new Location(5, -10);
        });
    }
    
    /**
     * Test if the new location does one step diagonally correctly
     */
    @Test
    public void testNextLocationDiagonal()
    {
        Location start = new Location(0, 0);
        Location destination = new Location(5, 5);
        
        Location step1 = start.nextLocation(destination);
        Location step2 = destination.nextLocation(start);
        assertEquals(new Location(1, 1), step1);
        assertEquals(new Location(4, 4), step2);
    }
    
    /**
     * Test if the new location does one step vertically correctly
     */
    @Test
    public void testNextLocationVertically()
    {
        Location location1 = new Location(0, 0);
        Location location2 = new Location(0, 5);
        
        Location step1 = location1.nextLocation(location2);
        Location step2 = location2.nextLocation(location1);
        
        assertEquals(new Location(0, 1), step1);
        assertEquals(new Location(0, 4), step2);
    }
    
    /**
     * Test if the new location does one step horizontally correctly
     */
    @Test
    public void testNextLocationHorizontal()
    {
        Location location1 = new Location(0, 0);
        Location location2 = new Location(5, 0);
        
        Location step1 = location1.nextLocation(location2);
        Location step2 = location2.nextLocation(location1);
        
        assertEquals(new Location(1, 0), step1);
        assertEquals(new Location(4, 0), step2);
    }
    /**
     * Test if the start location is the destination
     */
    @Test
    public void testNextLocationYaEnDestino()
    {
        Location start = new Location(3, 3);
        Location destination = new Location(3, 3);
        
        Location result = start.nextLocation(destination);
        
        assertSame(destination, result);
    }
    
    /**
     * Test the calculation of the distance
     */
    @Test
    public void testDistance()
    {
        Location p1 = new Location(0, 0);
        Location p2 = new Location(3, 7);
        Location p3 = new Location(4, 2);
        
        assertEquals(7, p1.distance(p2));
        assertEquals(7, p2.distance(p1));
        assertEquals(4, p1.distance(p3));
    }
    /**
     * Test if the locations one step right, up or diagonally are consider adjacent
     */
    
    @Test
    public void testIsAdjacent()
    {
        Location center = new Location(2, 2);
        Location up = new Location(2, 3);
        Location right = new Location(3, 2);
        Location diagonal = new Location(3, 3);
        Location far = new Location(4, 2);

        assertTrue(center.isAdjacent(up));
        assertTrue(center.isAdjacent(right));
        assertTrue(center.isAdjacent(diagonal));  
        assertFalse(center.isAdjacent(far));
    }
    
    @Test
    public void testEquals()
    {
        Location loc1 = new Location(8, 10);
        Location loc2 = new Location(8, 10);
        Location loc3 = new Location(10, 8);

        assertTrue(loc1.equals(loc2));  
        assertFalse(loc1.equals(loc3)); 
        assertFalse(loc1.equals(null)); 
        assertFalse(loc1.equals("Texto")); 
    }
    
    @Test
    public void testToString()
    {
        Location loc = new Location(4, 8);
        assertEquals("4-8", loc.toString());
    }
}
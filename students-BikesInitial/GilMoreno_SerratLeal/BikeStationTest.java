

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BikeStationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BikeStationTest
{
    /**
     * Default constructor for test class BikeStationTest
     */
    public BikeStationTest()
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
     * Comprueba que el constructor funciona
     */
    @Test
    public void TestConstructor(){
        BikeStation est = new BikeStation();
        Location loc = new Location (0,0);
        assertEquals("CC00",est.getId());
        assertEquals(loc ,est.getLocation());
        assertEquals(0,est.getNumberOfDocks());
    }
    
    /**
     * Comprueba que el constructor parametrizado funciona
     */
    @Test
    public void TestConstructorP(){
        Location loc = new Location (0,1);
        BikeStation est = new BikeStation("SS04",loc);
        assertEquals("SS04",est.getId());
        assertEquals(loc ,est.getLocation());
        assertEquals(0,est.getNumberOfDocks());
    }
    
    
    /**
     * Comprueba que devuelve un dock libre
     */
    @Test
    public void TestGetFreeDock(){
        Location loc = new Location (0,1);
        BikeStation est = new BikeStation("SS04",loc);
        Dock d = new Dock("CC01_02", 1, 1);
        Dock d1 = new Dock("CC01_02", 3, 1);
        Dock d2 = new Dock("CC01_02", 2, 1);
        est.addDock(d);
        est.addDock(d1);
        est.addDock(d2);
        d.setOcupedDock();
        d2.setOcupedDock();
        assertEquals(d1,est.getFreeDock());
    }
    
    
    /**
     * Comprueba que devuelve el numero de docks de una estacion
     */
    @Test
    public void TestGetNumbersOfDocks(){
        Location loc = new Location (0,1);
        BikeStation est = new BikeStation("SS04",loc);
        Dock d = new Dock("CC01_02", 1, 1);
        Dock d1 = new Dock("CC01_02", 3, 1);
        Dock d2 = new Dock("CC01_02", 2, 1);
        est.addDock(d);
        est.addDock(d1);
        est.addDock(d2);
        assertEquals(3,est.getNumberOfDocks());
    }
    
    
    /**
     * Comprueba que establece correctamnete la localicacion establecida
     */
    @Test
    public void TestSetLoacation(){
        Location loc = new Location (0,1);
        BikeStation est = new BikeStation("SS04",loc);
        Location loc2 = new Location(1,3);
        est.setLocation(loc2);
        assertEquals(loc2,est.getLocation());
    }
    
    
    /**
     * Comprueba que devuelve la cadena de docks
     */
    @Test
    public void TestGetDocks(){
        Location loc = new Location (0,1);
        BikeStation est = new BikeStation("SS04",loc);
        Dock d = new Dock("CC01_02", 1, 1);
        Dock d1 = new Dock("CC01_02", 3, 1);
        Dock d2 = new Dock("CC01_02", 2, 1);
        est.addDock(d);
        est.addDock(d1);
        est.addDock(d2);       
        assertEquals(3,est.getDocks().size());
    }
    
    
    /**
     * Comprueba que devuelve un dock por su id
     */
    @Test
    public void TestGetDock(){
        Location loc = new Location (0,1);
        BikeStation est = new BikeStation("SS04",loc);
        Dock d = new Dock("CC01_02", 1, 1);
        Dock d1 = new Dock("CC31_02", 3, 1);
        Dock d2 = new Dock("CS01_02", 2, 1);
        est.addDock(d);
        est.addDock(d1);
        est.addDock(d2);       
        assertEquals(d1,est.getDock("CC31_02"));
    }
    
    
    /**
     * Comprueba que añade los docks en orden correctamente
     */
    @Test
    public void TestAddDock(){
        Location loc = new Location (0,1);
        BikeStation est = new BikeStation("SS04",loc);
        Dock d = new Dock("CC01_02", 1, 1);
        Dock d1 = new Dock("CC31_02", 3, 1);
        Dock d2 = new Dock("CS01_02", 2, 1);
        Dock d3 = new Dock("PS01_02", 1, 4);
        Dock d4 = new Dock("GS01_02", 1, 2);
        Dock d5 = new Dock("SS01_05", 1, 3);
        Dock d6 = new Dock("CS01_03", 1, 4);
        est.addDock(d);
        est.addDock(d1);
        est.addDock(d2);   
        est.addDock(d3);
        est.addDock(d4);
        est.addDock(d5);
        est.addDock(d6);
        assertEquals(d1,est.getDocks().get(0));
        assertEquals(d2,est.getDocks().get(1));
        assertEquals(d,est.getDocks().get(2));
        assertEquals(d4,est.getDocks().get(3));
        assertEquals(d5,est.getDocks().get(4));
        assertEquals(d6,est.getDocks().get(5));
        assertEquals(d3,est.getDocks().get(6));

    }
}



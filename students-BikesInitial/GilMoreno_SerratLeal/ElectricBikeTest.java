

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ElectricBikeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ElectricBikeTest
{
    /**
     * Default constructor for test class ElectricBikeTest
     */
    public ElectricBikeTest()
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
     * Comprueba que las bicis se añaden correctamente
     */
    @Test
    public void testCreateBikes()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",60);
        assertEquals(-1,bici.getArrivalStep());
        assertEquals("AAAAAA",bici.getIdBike());
        assertEquals(loc,bici.getLocation());
        assertEquals(loc2,bici.getTargetLocation());
        assertEquals(null,bici.getRechargingLocation());
    }
    
    /**
     * Comprueba que se establece la location
     */
    @Test
    public void testSetLocation()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        Location loc3 = new Location(6,8);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",60);
        bici.setLocation(loc3);
        assertEquals(loc3,bici.getLocation());
    }
    
    /**
     * Comprueba que se establece la localizacion de destino bien
     */
    @Test
    public void testSetTargetLocation()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        Location loc3 = new Location(6,8);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",60);
        bici.setTargetLocation(loc3);
        assertEquals(loc3,bici.getTargetLocation());
    }
    
    /**
     * Comprueba que se calcula la ruta bien
     */
    @Test
    public void testCalculeRoute()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(6,6);
        Location loc3 = new Location(2,2);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",15);
        BikeStation est = new BikeStation("CC00", loc3);
        iber.addBikeStation(est);
        bici.calculateRoute();
        assertEquals(loc3,bici.getRechargingLocation());
    }
    
    /**
     * Comprueba el mensaje de el paso se hace correctamente
     */
    @Test
    public void testStringRoute()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(6,6);
        Location loc3 = new Location(2,2);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",15);
        assertEquals("1-1->6-6",bici.getStringRoute());
        BikeStation est = new BikeStation("CC00", loc3);
        iber.addBikeStation(est);
        bici.calculateRoute();
        assertEquals("1-1->2-2->6-6",bici.getStringRoute());
    }
    
     /**
     * Comprueba que se comprueba si la bici tiene bateria
     */
    @Test
    public void testEnoughBattery()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(6,6);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",15);
        assertEquals(false,bici.enoughBattery(loc2));
    }
    
     /**
     * Comprueba que se calcula la distancia hasta el destino
     */
    @Test
    public void testDistanceToTheTargetLocation()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(6,6);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",15);
        assertEquals(5,bici.distanceToTheTargetLocation());
    }
    
    /**
     * Comprueba que se encuentra el dock que cargara la bici
     */
    @Test
    public void tesRecharge()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(6,6);
        Location loc3 = new Location(2,2);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",15);
        BikeStation est = new BikeStation("CC00", loc3);
        iber.addBikeStation(est);
        Dock d = new Dock("CC01_02", 1, 1);
        est.addDock(d);
        bici.calculateRoute();
        assertEquals(d,bici.recharge());
        assertEquals(d,bici.getDocks().get(0));
        assertEquals(0,bici.getWsCharged());
        assertEquals(1,bici.getChargesCount());
        
    }
    
     /**
     * Comprueba que la betria se baja bien
     */
    @Test
    public void testReduceBatteryLevel()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(6,6);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",15);
        bici.reduceBatteryLevel();
        assertEquals(10,bici.getBatteryLevel());
    }
    
     /**
     * Comprueba que se da el paso bien
     */
    @Test
    public void testStep()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(6,6);
        Location loc3 = new Location(2,2);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",15);
        iber.addBikeStation(new BikeStation("CC00", loc3));
        bici.calculateRoute();
        bici.act(1);
        assertEquals(loc3,bici.getLocation());
        assertEquals(10,bici.getBatteryLevel());
    }
    
    /**
     * Comprueba que los datos de la bici se dan por pantalla correctamente
     */
    @Test
    public void testToString()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(6,6);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",15);
        assertEquals("(ElectricBike: AAAAAA, 15wh, 15wh, 0, 0.0€, 0, 1-1, 6-6)",bici.toString());
    }
    
}
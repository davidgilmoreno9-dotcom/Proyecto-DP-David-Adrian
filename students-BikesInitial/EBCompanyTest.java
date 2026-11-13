import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class EBCompanyTest.
 *
 * @author  David
 * @version 13/11/2026
 */
public class EBCompanyTest
{
    /**
     * Default constructor for test class EBCompanyTest
     */
    public EBCompanyTest()
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
     * Comprueba que el constructor funcione correctamente
     */
    @Test
    public void testConstructor()
    {
        EBCompany eBCompan1 = new EBCompany("bicis");
        assertEquals("bicis", eBCompan1.getName());
    }
    
    /**
     * Comprueba que las bicis se añaden correctamente
     */
    @Test
    public void testAddBikes()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",60);
        iber.addElectricBike(bici);
        assertEquals(1,iber.getNumberOfBikes());
    }
    
    /**
     * Comprueba que las estaciones se añaden correctamente
     */
    @Test
    public void testAddStations()
    {
        EBCompany iber = new EBCompany("bicis");
        BikeStation est = new BikeStation();
        iber.addBikeStation(est);
        assertEquals(1,iber.getNumberOfStations());
    }
    
    
    /**
     * Comprueba que busca bien una estación por su id
     */
    @Test
    public void testGetStations()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        BikeStation est = new BikeStation("CC01",loc);
        BikeStation est2 = new BikeStation("CC02",loc2);
        iber.addBikeStation(est);
        iber.addBikeStation(est2);
        assertEquals(est2,iber.getBikeStation("CC02"));
    }
    
    /**
     * Comprueba que busca bien la mejor estación de uuna bici según cuanta bateria tenga
     */
    @Test
    public void testGetBestStation()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        BikeStation est = new BikeStation("CC01",loc);
        BikeStation est2 = new BikeStation("CC02",loc2);
        iber.addBikeStation(est);
        iber.addBikeStation(est2);
        Location loc3 = new Location(7,7);
        Location loc4 = new Location(0,0);
        ElectricBike bici= new ElectricBike(iber,loc3,loc4,"AAAAAA",25);
        ElectricBike bici2= new ElectricBike(iber,loc3,loc4,"AAAAAB",35);
        assertEquals(est2,iber.findBestStation(bici));
        assertEquals(est,iber.findBestStation(bici2));
    }
    
    
    /**
     * Comprueba que busca bien una estacion por su localicación
     */
    @Test
     public void testGetStationsL()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        BikeStation est = new BikeStation("CC01",loc);
        BikeStation est2 = new BikeStation("CC02",loc2);
        iber.addBikeStation(est);
        iber.addBikeStation(est2);
        assertEquals(est2,iber.getBikeStation(loc2));
    }
    
    
    /**
     * Comprueba que devuelve bien el numero de estaciones
     */
    @Test
     public void testNumberOfStations()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        BikeStation est = new BikeStation("CC01",loc);
        BikeStation est2 = new BikeStation("CC02",loc2);
        BikeStation est3 = new BikeStation("CC03",loc2);
        BikeStation est4 = new BikeStation("CC04",loc2);
        iber.addBikeStation(est);
        iber.addBikeStation(est2);
        iber.addBikeStation(est3);
        iber.addBikeStation(est4);
        assertEquals(4,iber.getNumberOfStations());
    }
    
    
    /**
     * Comprueba que devuelve bien el numero de bicis
     */
    @Test
    public void testNumberOfBikes()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",60);
        ElectricBike bici2= new ElectricBike(iber,loc,loc2,"AAAAAD",60);
        ElectricBike bici3= new ElectricBike(iber,loc,loc2,"AAAAAC",60);
        ElectricBike bici4= new ElectricBike(iber,loc,loc2,"AAAAAD",60);
        iber.addElectricBike(bici);
        iber.addElectricBike(bici2);
        iber.addElectricBike(bici3);
        iber.addElectricBike(bici4);
        assertEquals(4,iber.getNumberOfBikes());
    }
    
    /**
     * Comprueba que resetea bien las estaciones y bicis
     */
    @Test
     public void testReset()
    {
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",60);
        ElectricBike bici2= new ElectricBike(iber,loc,loc2,"AAAAAD",60);
        ElectricBike bici3= new ElectricBike(iber,loc,loc2,"AAAAAC",60);
        ElectricBike bici4= new ElectricBike(iber,loc,loc2,"AAAAAD",60);
        iber.addElectricBike(bici);
        iber.addElectricBike(bici2);
        iber.addElectricBike(bici3);
        iber.addElectricBike(bici4);
        BikeStation est = new BikeStation("CC01",loc);
        BikeStation est2 = new BikeStation("CC02",loc2);
        BikeStation est3 = new BikeStation("CC03",loc2);
        BikeStation est4 = new BikeStation("CC04",loc2);
        iber.addBikeStation(est);
        iber.addBikeStation(est2);
        iber.addBikeStation(est3);
        iber.addBikeStation(est4);
        iber.reset();
        assertEquals(0,iber.getNumberOfStations());
        assertEquals(0,iber.getNumberOfBikes());
    }
}


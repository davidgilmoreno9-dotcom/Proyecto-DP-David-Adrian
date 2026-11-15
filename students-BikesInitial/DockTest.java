

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class DockTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DockTest
{
    /**
     * Default constructor for test class DockTest
     */
    public DockTest()
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
     * Comprueba que los docks se crean correctamente
     */
    @Test
    public void testCreateDock()
    {
        Dock d = new Dock("CC01_02",2,1);
        assertEquals("CC01_02",d.getIdDock());
        assertEquals(2,d.getChargingSpeed());
        assertEquals(1,d.getChargingFee());
        assertEquals(0,d.getNumberEVRecharged());
        assertEquals(true,d.getDockFree());
    }
    
     /**
     * Comprueba que las bicis se añaden correctamente
     */
    @Test
    public void testAddBike()
    {
        Dock d = new Dock("CC01_02",2,1);
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",60);
        d.addBike(bici);
        assertEquals(bici,d.getBikesRecharged().get(0));
    }
    
    /**
     * Comprueba que se cambia a ocupado bien
     */
    @Test
    public void testOcupedDock()
    {
        Dock d = new Dock("CC01_02",2,1);
        d.setOcupedDock();
        assertEquals(false,d.getDockFree());
    }
    
    /**
     * Comprueba que se establece el total recolectado
     */
    @Test
    public void testSetAmountCollected()
    {
        Dock d = new Dock("CC01_02",2,1);
        d.setAmountCollected(1);
        assertEquals(1,d.getAmountCollected());
    }
    /**
     * Comprueba que se muestra por pantalla el dock bien
     */
    @Test
    public void testToString()
    {
        Dock d = new Dock("CC01_02",2,1);
        assertEquals("(Dock: CC01_02, 2wh, 1.0€, 0, 0.0€)",d.toString());
    }
    
      /**
     * Comprueba que la funcion charge va bien
     */
    @Test
    public void testRecharge()
    {
        Dock d = new Dock("CC01_02",2,1);
        EBCompany iber = new EBCompany("bicis");
        Location loc = new Location(1,1);
        Location loc2 = new Location(3,5);
        ElectricBike bici= new ElectricBike(iber,loc,loc2,"AAAAAA",60);
        assertEquals(1,d.recharge(1,bici));
        assertEquals(1,d.getAmountCollected());
        assertEquals(bici,d.getBikesRecharged().get(0));
    }
}
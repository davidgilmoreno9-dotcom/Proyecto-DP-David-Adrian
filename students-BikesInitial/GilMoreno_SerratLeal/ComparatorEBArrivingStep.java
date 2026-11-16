import java.util.*;

/**
 * Compares two {@link ElectricBike} objects based on the step in which they
 * reached their target destination, in ascending order.
 * Bikes that have not arrived yet ({@code arrivalStep == -1}) are considered
 * to arrive last. Ties are broken by their ID in ascending order.
 * @author Adrian Serrat Leal
 * @version 2026.11.15
 */
public class ComparatorEBArrivingStep implements Comparator<ElectricBike>
{
    /**
     * Compares its two {@link ElectricBike} arguments for order.
     * The comparison is based on the arrival step; bikes that have not
     * arrived are placed after those that have. If both have the same
     * arrival step, the comparison is based on the lexicographical order
     * of their IDs.
     * @param eb1 The first electric bike to be compared.
     * @param eb2 The second electric bike to be compared.
     * @return A negative integer, zero, or a positive integer as the first
     * bike arrived earlier than, at the same step as, or later than the second.
     */
    @Override
    public int compare(ElectricBike eb1, ElectricBike eb2)
    {
        int step1 = (eb1.getArrivalStep() < 0) ? Integer.MAX_VALUE : eb1.getArrivalStep();
        int step2 = (eb2.getArrivalStep() < 0) ? Integer.MAX_VALUE : eb2.getArrivalStep();
        int result = Integer.compare(step1, step2);
        if (result != 0) {
            return result;
        }
        return eb1.getIdBike().compareTo(eb2.getIdBike());
    }
}
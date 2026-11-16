import java.util.*;

/**
 * Compares two {@link BikeStation} objects based on the number of bikes
 * recharged in their docks, in descending order.
 * Ties are broken by their ID in ascending order.
 * This can be used for sorting lists of bike stations in the final report.
 * @author Adrian Serrat Leal
 * @version 2026.11.15
 */
public class ComparatorBikeStationNumRecharged implements Comparator<BikeStation>
{
    /**
     * Compares its two {@link BikeStation} arguments for order.
     * The comparison is based on the number of bikes recharged in the
     * station (descending). If both have the same number, the comparison
     * is based on the lexicographical order of their IDs (ascending).
     * @param st1 The first bike station to be compared.
     * @param st2 The second bike station to be compared.
     * @return A negative integer, zero, or a positive integer as the first
     * station has recharged more, the same, or fewer bikes than the second.
     */
    @Override
    public int compare(BikeStation st1, BikeStation st2)
    {
        int result = Integer.compare(st2.getNumBikesRecharged(), st1.getNumBikesRecharged());
        if (result != 0) {
            return result;
        }
        return st1.getId().compareTo(st2.getId());
    }
}
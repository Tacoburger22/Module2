import java.util.*;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Isaac Weiss (icw0001@auburn.edu)
 * @version 2020-09-19
 */
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
    private Selector() { }

    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the minimum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp) {

        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        Iterator<T> itr = coll.iterator();
        T min = itr.next();
        while (itr.hasNext()) {
            T nextElement = itr.next();
            if (comp.compare(nextElement, min) < 0) {
                min = nextElement;
            }
        }
        return min;
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the maximum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp) {

        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        Iterator<T> itr = coll.iterator();
        T max = itr.next();
        while (itr.hasNext()) {
            T nextElement = itr.next();
            if (comp.compare(nextElement, max) > 0) {
                max = nextElement;
            }
        }
        return max;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth minimum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {

        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty() || k < 1) {
            throw new NoSuchElementException();
        }
        // Make a duplicate to edit
        ArrayList<T> copy = new ArrayList<>(coll.size());
        copy.addAll(coll);
        copy.sort(comp);
        // Finds kth minimum, first in list if k = 1
        // Ex. 1, 2, 2, 3, 4, 5, 6, 6, 8
        if (k == 1) {
            return copy.get(0);
        }
        int distinctValues = 1;
        T previousElement = copy.get(0);
        T kmin = null;
        for (int i = 1; i < copy.size(); i++) {
            T t = copy.get(i);
            if (!t.equals(previousElement)) {
                distinctValues++;
                if (distinctValues == k) {
                    kmin = t;
                }
            }
            previousElement = copy.get(i);
        }

        if (k > distinctValues) {
            throw new NoSuchElementException();
        }

        return kmin;
    }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth maximum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty() || k < 1) {
            throw new NoSuchElementException();
        }
        // Make a duplicate to edit
        ArrayList<T> copy = new ArrayList<>(coll.size());
        copy.addAll(coll);
        copy.sort(comp);
        // Finds kth minimum, first in list if k = 1
        // Ex. 1, 2, 2, 3, 4, 5, 6, 6, 8
        int lastIndex = copy.size() - 1;
        if (k == 1) {
            return copy.get(lastIndex);
        }
        int distinctValues = 1;
        T previousElement = copy.get(lastIndex);
        T kmax = null;
        for (int i = lastIndex - 1; i >= 0; i--) {
            T t = copy.get(i);
            if (!t.equals(previousElement)) {
                distinctValues++;
                if (distinctValues == k) {
                    kmax = t;
                }
            }
            previousElement = copy.get(i);
        }

        if (k > distinctValues) {
            throw new NoSuchElementException();
        }

        return kmax;
    }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the range values are selected
     * @param low     the lower bound of the range
     * @param high    the upper bound of the range
     * @param comp    the Comparator that defines the total order on T
     * @return        a Collection of values between low and high
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                                      Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        ArrayList<T> rangeList = new ArrayList<>();
        for (T t : coll) {
            if (comp.compare(t, low) >= 0 && comp.compare(t, high) <= 0) {
                rangeList.add(t);
            }
        }
        if (rangeList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return rangeList;
    }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the ceiling value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the ceiling value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        boolean found = false;
        T ceiling = null;
        for (T nextElement : coll) {
            if (!found && comp.compare(nextElement, key) >= 0) {
                ceiling = nextElement;
                found = true;
            } else if (comp.compare(nextElement, key) >= 0 && comp.compare(nextElement, ceiling) <= 0)
                ceiling = nextElement;
        }
        if (!found) {
            throw new NoSuchElementException();
        }
        return ceiling;

    }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the floor value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the floor value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }
        boolean found = false;
        T floor = null;
        for (T nextElement : coll) {
            if (!found && comp.compare(nextElement, key) <= 0) {
                floor = nextElement;
                found = true;
            } else if (comp.compare(nextElement, key) <= 0 && comp.compare(nextElement, floor) >= 0)
                floor = nextElement;
        }
        if (!found) {
            throw new NoSuchElementException();
        }
        return floor;
    }
}

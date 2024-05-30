package structure;
import java.util.Arrays;

/**
 * A disjoint set making use of union-find algorithms.
 *
 * @author Giovan Cervantes
 * @version 1.0
 */
public class DisjointSets {
    private int[] sets;

    /**
     * Constructor for the DisjointSets object.
     * Initializes the sets array with a size taken in as the
     * parameter numElements. Each index is then assigned -1.
     * This signifies:
     * 1) Each element is its own root.
     * 2) The current height of each set is 0.
     *
     * @param numElements, total num of elements in array.
     */
    public DisjointSets(int numElements) {
        sets = new int[numElements];
        Arrays.fill(sets, -1);
    }

    /**
     * Finds the root of a given element.
     * If the element is the root, then the element is simply returned.
     * Else, a recursive call is made until the root is reached,
     * identified by whether the value is positive or negative.
     *
     * @param element, the value to find the root of.
     * @return an int representing the root of the given element.
     */
    public int find(int element) {
        if(sets[element] < 0) { //is this the root?
            return element;
        } else { //search recursively for the root.
            return find(sets[element]);
        }
    }

    /**
     * This method will join the two values if they aren't already
     * part of the same set.
     *
     * @param first, first value.
     * @param second, second value.
     * @return true if successfully joined, false otherwise.
     */
    public boolean union(int first, int second) {
        int rootOne = find(first);
        int rootTwo = find(second);

        if(rootOne == rootTwo) { //same set
            return false;
        }

        if(sets[rootOne] > sets[rootTwo]) { //rootTwo has the larger height
            sets[rootOne] = rootTwo;
        } else if(sets[rootOne] < sets[rootTwo]) { //rootOne has the larger height
            sets[rootTwo] = rootOne;
        } else { //same height, so height increases by one
            sets[rootTwo] = rootOne;
            sets[rootOne]--;
        }
        return true;
    }

    /**
     * Checks whether the two values are a part of the same set by
     * comparing roots.
     * if roots are equal -> same set.
     * else -> not a part of the same set.
     *
     * @param first, first value to check for root.
     * @param second, second value to check for root.
     * @return returns true if a part of the same set, and false otherwise.
     */
    public boolean sameSet(int first, int second) {
        return find(first) == find(second);
    }

    @Override
    public String toString() {
        return Arrays.toString(sets);
    }
}

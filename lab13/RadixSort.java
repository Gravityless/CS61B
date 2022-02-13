import java.util.LinkedList;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        String[] sorted = new String[asciis.length];
        int maxLen = 0;
        for (int i = 0; i < asciis.length; i++) {
            sorted[i] = asciis[i];
            maxLen = maxLen > asciis[i].length() ? maxLen : asciis[i].length();
        }

        for (int i = maxLen - 1; i >= 0 ; i--) {
            sortHelperLSD(sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int[] count = new int[128];
        String[] temp = new String[asciis.length];

        for (int i = 0; i < asciis.length; i++) {
            if (asciis[i].length() <= index) count[0]++;
            else count[asciis[i].charAt(index)]++;
        }

        for (int i = 1; i < 128; i++) {
            count[i] += count[i - 1];
        }

        for (int i = asciis.length - 1; i >= 0; i--) {
            if (asciis[i].length() > index) {
                temp[count[asciis[i].charAt(index)] - 1] = asciis[i];
                count[asciis[i].charAt(index)]--;
            } else {
                temp[count[0] - 1] = asciis[i];
                count[0]--;
            }
        }

        System.arraycopy(temp, 0, asciis, 0, asciis.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] asciis = {"hello", "good", "rice", "dumplings", "stone", "tommorrow", "need", "to", "remove", "parenthesis", "and"};
        String[] sorted = sort(asciis);
        System.out.println(sorted);
    }
}

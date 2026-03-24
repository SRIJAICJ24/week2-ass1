import java.util.Arrays;

public class Problem5_AccountIDLookup {

    static class SearchResult {
        int index;
        int comparisons;

        SearchResult(int index, int comparisons) {
            this.index = index;
            this.comparisons = comparisons;
        }
    }

    public static SearchResult linearSearchFirst(String[] arr, String target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                return new SearchResult(i, comparisons);
            }
        }
        return new SearchResult(-1, comparisons);
    }

    public static SearchResult linearSearchLast(String[] arr, String target) {
        int comparisons = 0;
        int lastIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                lastIndex = i;
            }
        }
        return new SearchResult(lastIndex, comparisons);
    }

    public static SearchResult binarySearchExact(String[] arr, String target) {
        int low = 0, high = arr.length - 1, comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                return new SearchResult(mid, comparisons);
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return new SearchResult(-1, comparisons);
    }

    public static int lowerBound(String[] arr, String target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upperBound(String[] arr, String target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].compareTo(target) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int countOccurrences(String[] arr, String target) {
        int first = lowerBound(arr, target);
        int last = upperBound(arr, target);
        return last - first;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC", "accB"};

        SearchResult firstLinear = linearSearchFirst(logs, "accB");
        SearchResult lastLinear = linearSearchLast(logs, "accB");

        System.out.println("Linear Search First accB: index=" + firstLinear.index +
                ", comparisons=" + firstLinear.comparisons);
        System.out.println("Linear Search Last accB: index=" + lastLinear.index +
                ", comparisons=" + lastLinear.comparisons);

        Arrays.sort(logs);
        System.out.println("\nSorted logs: " + Arrays.toString(logs));

        SearchResult binary = binarySearchExact(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("Binary Search accB: index=" + binary.index +
                ", comparisons=" + binary.comparisons);
        System.out.println("Occurrences of accB: " + count);
    }
}

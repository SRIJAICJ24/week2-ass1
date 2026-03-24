import java.util.Arrays;

public class Problem6_RiskThresholdBinaryLookup {

    static class SearchResult {
        int index;
        int comparisons;

        SearchResult(int index, int comparisons) {
            this.index = index;
            this.comparisons = comparisons;
        }
    }

    public static SearchResult linearSearch(int[] arr, int target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                return new SearchResult(i, comparisons);
            }
        }
        return new SearchResult(-1, comparisons);
    }

    public static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static Integer floorValue(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer floor = null;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return arr[mid];
            } else if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floor;
    }

    public static Integer ceilingValue(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer ceil = null;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return arr[mid];
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                ceil = arr[mid];
                high = mid - 1;
            }
        }
        return ceil;
    }

    public static int binaryComparisonsForFloorCeiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1, comparisons = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;
            if (arr[mid] == target) {
                return comparisons;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return comparisons;
    }

    public static void main(String[] args) {
        int[] unsortedRisks = {50, 10, 100, 25};
        int target = 30;

        SearchResult linear = linearSearch(unsortedRisks, target);
        System.out.println("Linear Search threshold=30: index=" + linear.index +
                ", comparisons=" + linear.comparisons);

        int[] sortedRisks = {10, 25, 50, 100};
        System.out.println("\nSorted risks: " + Arrays.toString(sortedRisks));

        int insertPos = insertionPoint(sortedRisks, target);
        Integer floor = floorValue(sortedRisks, target);
        Integer ceil = ceilingValue(sortedRisks, target);
        int comps = binaryComparisonsForFloorCeiling(sortedRisks, target);

        System.out.println("Insertion Point for 30: " + insertPos);
        System.out.println("Floor(30): " + floor);
        System.out.println("Ceiling(30): " + ceil);
        System.out.println("Binary comparisons: " + comps);
    }
}

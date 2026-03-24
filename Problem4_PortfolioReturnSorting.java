import java.util.Arrays;

public class Problem4_PortfolioReturnSorting {

    static class Asset {
        String name;
        double returnRate;
        double volatility;

        Asset(String name, double returnRate, double volatility) {
            this.name = name;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        @Override
        public String toString() {
            return name + "(return=" + returnRate + "%, vol=" + volatility + ")";
        }
    }

    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareDescThenVol(arr[j], pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    static int compareDescThenVol(Asset a, Asset b) {
        if (Double.compare(a.returnRate, b.returnRate) != 0) {
            return Double.compare(b.returnRate, a.returnRate);
        }
        return Double.compare(a.volatility, b.volatility);
    }

    public static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = low + (high - low) / 2;

        if (arr[low].returnRate > arr[mid].returnRate) swap(arr, low, mid);
        if (arr[low].returnRate > arr[high].returnRate) swap(arr, low, high);
        if (arr[mid].returnRate > arr[high].returnRate) swap(arr, mid, high);

        return mid;
    }

    public static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Asset[] assets1 = {
            new Asset("AAPL", 12, 4.5),
            new Asset("TSLA", 8, 7.1),
            new Asset("GOOG", 15, 3.9),
            new Asset("MSFT", 15, 2.8)
        };

        Asset[] assets2 = Arrays.copyOf(assets1, assets1.length);

        mergeSort(assets1, 0, assets1.length - 1);
        System.out.println("Merge Sort by return ascending:");
        System.out.println(Arrays.toString(assets1));

        quickSort(assets2, 0, assets2.length - 1);
        System.out.println("\nQuick Sort by return DESC + volatility ASC:");
        System.out.println(Arrays.toString(assets2));
    }
}

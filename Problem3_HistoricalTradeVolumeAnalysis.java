import java.util.Arrays;

public class Problem3_HistoricalTradeVolumeAnalysis {

    static class Trade {
        String id;
        int volume;

        Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) {
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

    public static void quickSortDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionDesc(arr, low, high);
            quickSortDesc(arr, low, pivotIndex - 1);
            quickSortDesc(arr, pivotIndex + 1, high);
        }
    }

    public static int partitionDesc(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static Trade[] mergeTwoSortedTradeLists(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    public static int totalVolume(Trade[] trades) {
        int sum = 0;
        for (Trade t : trades) {
            sum += t.volume;
        }
        return sum;
    }

    public static void main(String[] args) {
        Trade[] tradesAsc = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };

        Trade[] tradesDesc = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };

        mergeSort(tradesAsc, 0, tradesAsc.length - 1);
        System.out.println("Merge Sort ascending:");
        System.out.println(Arrays.toString(tradesAsc));

        quickSortDesc(tradesDesc, 0, tradesDesc.length - 1);
        System.out.println("\nQuick Sort descending:");
        System.out.println(Arrays.toString(tradesDesc));

        Trade[] morning = {
            new Trade("m1", 100),
            new Trade("m2", 300)
        };

        Trade[] afternoon = {
            new Trade("a1", 200),
            new Trade("a2", 400)
        };

        Trade[] merged = mergeTwoSortedTradeLists(morning, afternoon);
        System.out.println("\nMerged sorted lists:");
        System.out.println(Arrays.toString(merged));
        System.out.println("Total Volume: " + totalVolume(merged));
    }
}

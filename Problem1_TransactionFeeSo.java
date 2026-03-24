import java.util.ArrayList;
import java.util.List;

public class Problem1_TransactionFeeSorting {

    static class Transaction {
        String id;
        double fee;
        String timestamp;

        Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    static class BubbleResult {
        List<Transaction> sorted;
        int passes;
        int swaps;

        BubbleResult(List<Transaction> sorted, int passes, int swaps) {
            this.sorted = sorted;
            this.passes = passes;
            this.swaps = swaps;
        }
    }

    public static BubbleResult bubbleSortByFee(List<Transaction> transactions) {
        List<Transaction> list = new ArrayList<>(transactions);
        int n = list.size();
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return new BubbleResult(list, passes, swaps);
    }

    public static List<Transaction> insertionSortByFeeAndTimestamp(List<Transaction> transactions) {
        List<Transaction> list = new ArrayList<>(transactions);

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compareByFeeThenTimestamp(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        return list;
    }

    static int compareByFeeThenTimestamp(Transaction a, Transaction b) {
        if (Double.compare(a.fee, b.fee) != 0) {
            return Double.compare(a.fee, b.fee);
        }
        return a.timestamp.compareTo(b.timestamp);
    }

    public static List<Transaction> findHighFeeOutliers(List<Transaction> transactions) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.fee > 50.0) {
                outliers.add(t);
            }
        }
        return outliers;
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));
        transactions.add(new Transaction("id4", 25.0, "09:15"));
        transactions.add(new Transaction("id5", 60.0, "11:00"));

        BubbleResult bubble = bubbleSortByFee(transactions);
        System.out.println("Bubble Sort by fee ascending:");
        System.out.println(bubble.sorted);
        System.out.println("Passes: " + bubble.passes + ", Swaps: " + bubble.swaps);

        List<Transaction> insertion = insertionSortByFeeAndTimestamp(transactions);
        System.out.println("\nInsertion Sort by fee + timestamp:");
        System.out.println(insertion);

        List<Transaction> outliers = findHighFeeOutliers(transactions);
        System.out.println("\nHigh-fee outliers (> 50):");
        System.out.println(outliers.isEmpty() ? "None" : outliers);
    }
}

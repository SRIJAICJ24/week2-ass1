import java.util.Arrays;

public class Problem2_ClientRiskScoreRanking {

    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + "(risk=" + riskScore + ", balance=" + accountBalance + ")";
        }
    }

    public static int bubbleSortByRiskAscending(Client[] clients) {
        int swaps = 0;
        int n = clients.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return swaps;
    }

    public static void insertionSortByRiskDescThenBalance(Client[] clients) {
        for (int i = 1; i < clients.length; i++) {
            Client key = clients[i];
            int j = i - 1;

            while (j >= 0 && compareDesc(clients[j], key) > 0) {
                clients[j + 1] = clients[j];
                j--;
            }
            clients[j + 1] = key;
        }
    }

    static int compareDesc(Client a, Client b) {
        if (a.riskScore != b.riskScore) {
            return Integer.compare(b.riskScore, a.riskScore);
        }
        return Double.compare(a.accountBalance, b.accountBalance);
    }

    public static void printTopRiskClients(Client[] clients, int k) {
        System.out.println("Top " + k + " highest-risk clients:");
        for (int i = 0; i < Math.min(k, clients.length); i++) {
            System.out.println((i + 1) + ". " + clients[i]);
        }
    }

    public static void main(String[] args) {
        Client[] clients1 = {
            new Client("clientC", 80, 5000),
            new Client("clientA", 20, 8000),
            new Client("clientB", 50, 3000),
            new Client("clientD", 80, 2000)
        };

        Client[] clients2 = Arrays.copyOf(clients1, clients1.length);

        int swaps = bubbleSortByRiskAscending(clients1);
        System.out.println("Bubble Sort (risk ascending):");
        System.out.println(Arrays.toString(clients1));
        System.out.println("Swaps: " + swaps);

        insertionSortByRiskDescThenBalance(clients2);
        System.out.println("\nInsertion Sort (risk DESC + accountBalance ASC):");
        System.out.println(Arrays.toString(clients2));

        System.out.println();
        printTopRiskClients(clients2, 10);
    }
}

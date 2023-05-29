import java.util.Scanner;

public class gridTravel {
    public static long travel(int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 && n == 1)
            return 1;

        return travel(m - 1, n) + travel(m, n - 1);
    }

    public static long travel_memoized(int m, int n, long[][] memo) {
        if (memo[m][n] != 0)
            return memo[m][n];
        if (m == 0 || n == 0)
            return 0;
        if (m == 1 && n == 1)
            return 1;

        memo[m][n] = travel(m - 1, n) + travel(m, n - 1);
        return memo[m][n];
    }

    public static long travel_tab(int m, int n) {
        if (m < 1 || n < 1)
            return 0;

        long[][] tab = new long[m + 1][n + 1];
        tab[1][1] = 1;

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i == j && j == 1)
                    continue;

                tab[i][j] = tab[i - 1][j] + tab[i][j - 1];
            }
        }

        return tab[m][n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter starting point (m,n): ");
        int m = sc.nextInt();
        int n = sc.nextInt();
        long start, total_ways;
        double runtime;
        sc.close();

        start = System.nanoTime();
        total_ways = travel(m, n);
        runtime = System.nanoTime() - start;
        System.out.println("Grid Travel Normal: " + total_ways + "; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        total_ways = travel_memoized(m, n, new long[m + 1][n + 1]);
        runtime = System.nanoTime() - start;
        System.out.println("Grid Travel Memoized: " + total_ways + "; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        total_ways = travel_tab(m, n);
        runtime = System.nanoTime() - start;
        System.out.println("Grid Travel Tabulation: " + total_ways + "; Time Taken: " + runtime + " ns");
    }
}

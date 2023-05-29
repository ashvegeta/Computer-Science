import java.util.Scanner;

public class fibonacci {
    public static long fib(int n) {
        if (n == 0)
            return 0;
        if (n <= 2)
            return 1;

        return fib(n - 1) + fib(n - 2);
    }

    public static long fib_memo(int n, long[] memo) {
        if (n <= 0)
            return 0;
        if (n <= 2)
            return 1;
        if (n < memo.length && memo[n] != 0)
            return memo[n];

        memo[n] = fib(n - 1) + fib(n - 2);

        return memo[n];
    }

    public static long fib_tab(int n) {
        if (n <= 0)
            return 0;
        else if (n <= 2)
            return 1;

        int tab[] = new int[n + 1];
        tab[0] = 0;
        tab[1] = tab[2] = 1;

        for (int i = 3; i <= n; i++)
            tab[i] = tab[i - 1] + tab[i - 2];

        return tab[n];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Number: ");
        int no = sc.nextInt();
        long start, sum;
        double runtime;
        sc.close();

        start = System.nanoTime();
        sum = fib(no);
        runtime = System.nanoTime() - start;
        System.out.println("Fibonacci Normal: " + sum + "; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        sum = fib_memo(no, new long[no + 1]);
        runtime = System.nanoTime() - start;
        System.out.println("Fibonacci Memoized: " + sum + "; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        sum = fib_tab(no);
        runtime = System.nanoTime() - start;
        System.out.println("Fibonacci Tabulation: " + sum + "; Time Taken: " + runtime + " ns");
    }
}
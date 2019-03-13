package part1.week1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Chris Qiu
 */
@Slf4j
public class PercolationHelper {
    public static void print(boolean[] sites) {
        int m = sites.length - 1;
        int n = (int) Math.sqrt(m);
        log.info("n: {}", n);
        for (int k = 0; k < n; k++) {
            for (int i = 1; i <= n; i++) {
                int j = k * n + i;
                if (sites[j]) {
                    System.out.printf(" %04d ", j);
                } else {
                    System.out.print(" .... ");
                }
            }
            System.out.println();
        }
    }
}

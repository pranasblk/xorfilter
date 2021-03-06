package org.xorfilter.gcs;

import java.util.Random;

import org.junit.Test;
import org.xorfilter.gcs.Sort;

public class SortTest {

    public static void main(String... args) {
        new SortTest().sortUnsigned();
        int count = 128 * 1024 * 1024; // * 1024 * 1024;
        for(int test = 0; test < 4; test++) {
            System.out.println("test " + test);
            Random r = new Random(test);
            long[] data = new long[count];
            long sum = 0;
            for(int i = 0; i < data.length; i++) {
                long x = r.nextLong();
                sum += x;
                data[i] = x;
            }
            long time = System.nanoTime();
            Sort.sortUnsigned(data);
            time = System.nanoTime() - time;
            System.out.println("sorted in " + (time / 1_000_000_000.) + " secs");
            long sum2 = 0;
            for(long x : data) {
                sum2 += x;
            }
            if (sum != sum2) {
                throw new AssertionError("sum changed");
            }
            for (int i = 1; i < data.length; i++) {
                if (Long.compareUnsigned(data[i - 1], data[i]) > 0) {
                    throw new AssertionError("index " + i);
                }
            }
            System.out.println("compared");
        }
    }

    @Test
    public void sortUnsigned() {
        Random r = new Random(1);
        for (int test = 0; test < 1000; test++) {
            int len = r.nextInt(10);
            long[] data = new long[len];
            for (int i = 0; i < len; i++) {
                data[i] = r.nextInt(5) - 2;
            }
            Sort.sortUnsigned(data);
            // sortUnsignedSimple(data);
            for (int i = 1; i < data.length; i++) {
                if (Long.compareUnsigned(data[i - 1], data[i]) > 0) {
                    throw new AssertionError("index " + i);
                }
            }
        }
    }

}

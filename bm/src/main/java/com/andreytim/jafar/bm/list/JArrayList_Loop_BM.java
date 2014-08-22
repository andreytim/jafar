package com.andreytim.jafar.bm.list;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.Benchmark;
import com.andreytim.jafar.core.list.JArrayList;
import com.andreytim.jafar.core.list.prim.JList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Launch command:
 * > java -jar <path>/bm-<version>-jmh.jar -v extra -i 10 -wi 5 -f 1 -prof gc ".*Loop_BM.*"
 *
 * Created by shpolsky on 09.08.14.
 */
@SuppressWarnings("unused")
@State(Scope.Thread)
public class JArrayList_Loop_BM {

    private static final Random RAND = new Random();
    private static final int SIZE = 1000000;

    private static final int[] INT_ARR = new int[SIZE];
    private static final List<Integer> INT_AL = new ArrayList<Integer>();
    private static final List<Integer> INT_JAL = new JArrayList<Integer>();
    private static final JList<Integer> INT_JJAL = new JArrayList<Integer>();

    static {
        fill(INT_ARR);
        fill(INT_AL, SIZE);
        fill(INT_JAL, SIZE);
        fill(INT_JJAL, SIZE);
    }

    private volatile int dummy;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void intArrForeachLoop() {
        dummy = 0;
        for (int i : INT_ARR) {
            dummy += i;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void intArrayListForeachLoop() {
        dummy = 0;
        for (int i : INT_AL) {
            dummy += i;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void intJArrayListForeachLoop() {
        dummy = 0;
        for (int i : INT_JAL) {
            dummy += i;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void intJJArrayListForeachLoop() {
        dummy = 0;
        for (int i : INT_JJAL) {
            dummy += i;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void intJJArrayListIdxForLoop() {
        dummy = 0;
        for (int i = 0; i < INT_JJAL.size(); i++) {
            dummy += INT_JJAL.getInt(i);
        }
    }

    private static void fill(List<Integer> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(RAND.nextInt());
        }
    }

    private static void fill(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = RAND.nextInt();
        }
    }
}

package com.andreytim.jafar.bm.list;

import cern.colt.list.IntArrayList;
import com.andreytim.jafar.core.list.JArrayList;
import com.andreytim.jafar.core.list.prim.JList;
import gnu.trove.list.array.TIntArrayList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Launch command:
 * > java -jar <path>/bm-<version>-jmh.jar -v extra -i 10 -wi 5 -f 1 -prof gc ".*Int_Loop_BM.*"
 *
 * Created by shpolsky on 09.08.14.
 */
@SuppressWarnings("unused")
@State(Scope.Thread)
public class JArrayList_Int_Loop_BM {

    private static final Random RAND = new Random();
    private static final int SIZE = 1000000;

    private static final int[] INT_ARR = new int[SIZE];
    private static final List<Integer> INT_AL = new ArrayList<>();
    private static final List<Integer> INT_JAL = new JArrayList<>();
    private static final JList<Integer> INT_JJAL = new JArrayList<>();
    private static final IntArrayList INT_CAL = new IntArrayList();
    private static final it.unimi.dsi.fastutil.ints.IntArrayList INT_FUAL =
            new it.unimi.dsi.fastutil.ints.IntArrayList();
    private static final TIntArrayList INT_TAL = new TIntArrayList();

    static {
        BmListUtils.fill(INT_ARR);
        BmListUtils.fill(INT_AL, SIZE);
        BmListUtils.fill(INT_JAL, SIZE);
        BmListUtils.fill(INT_JJAL, SIZE);
        BmListUtils.fill(INT_CAL, SIZE);
        BmListUtils.fill(INT_FUAL, SIZE);
        BmListUtils.fill(INT_TAL, SIZE);
    }

    private volatile int dummy;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaArrayForeachLoop() {
        dummy = 0;
        for (int i : INT_ARR) {
            dummy += i;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaArrayListForeachLoop() {
        dummy = 0;
        for (int i : INT_AL) {
            dummy += i;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jArrayListForeachLoop() {
        dummy = 0;
        for (int i : INT_JAL) {
            dummy += i;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListForeachLoop() {
        dummy = 0;
        for (int i : INT_JJAL) {
            dummy += i;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListIdxForLoop() {
        dummy = 0;
        for (int i = 0; i < INT_JJAL.size(); i++) {
            dummy += INT_JJAL.getInt(i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void coltArrayListIdxForLoop() {
        dummy = 0;
        for (int i = 0; i < INT_CAL.size(); i++) {
            dummy += INT_CAL.get(i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void fastutilArrayListIdxForLoop() {
        dummy = 0;
        for (int i = 0; i < INT_FUAL.size(); i++) {
            dummy += INT_FUAL.getInt(i);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void fastutilArrayListForeachLoop() {
        dummy = 0;
        for (int i : INT_FUAL) {
            dummy += i;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void troveArrayListIdxForLoop() {
        dummy = 0;
        for (int i = 0; i < INT_TAL.size(); i++) {
            dummy += INT_TAL.get(i);
        }
    }
}

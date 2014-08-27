package com.andreytim.jafar.bm.list;

import cern.colt.list.IntArrayList;
import com.andreytim.jafar.core.list.JArrayList;
import com.andreytim.jafar.core.list.prim.JArrayListInt;
import com.andreytim.jafar.core.list.prim.JList;
import gnu.trove.list.array.TIntArrayList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Launch command:
 * > java -jar <path>/bm-<version>-jmh.jar -v extra -i 10 -wi 5 -f 1 -prof gc ".*Int_Loop_BM.*"
 *
 * Created by shpolsky on 09.08.14.
 */
@SuppressWarnings("unused")
@State(Scope.Thread)
public class ArrayList_Int_Loop_BM {

    private static final int SIZE = 1_000_000;

    private List<Integer> INT_AL = new ArrayList<>();
    private List<Integer> INT_JAL = new JArrayList<>();
    private JList<Integer> INT_JJAL = new JArrayList<>();
    private JList<Integer> INT_JJIAL = new JArrayListInt();
    private IntArrayList INT_CAL = new IntArrayList();
    private it.unimi.dsi.fastutil.ints.IntArrayList INT_FUAL = new it.unimi.dsi.fastutil.ints.IntArrayList();
    private TIntArrayList INT_TAL = new TIntArrayList();

    private volatile int dummy;

    @Setup(Level.Iteration)
    public void fill() {
        IntListUtils.refill(INT_AL, SIZE);
        IntListUtils.refill(INT_JAL, SIZE);
        IntListUtils.refill(INT_JJAL, SIZE);
        IntListUtils.refill(INT_JJIAL, SIZE);
        IntListUtils.refill(INT_CAL, SIZE);
        IntListUtils.refill(INT_FUAL, SIZE);
        IntListUtils.refill(INT_TAL, SIZE);
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

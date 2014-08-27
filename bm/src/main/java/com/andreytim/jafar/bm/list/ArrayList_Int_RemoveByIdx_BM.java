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
 * > java -jar <path>/bm-<version>-jmh.jar -v extra -i 10 -wi 5 -f 1 -prof gc ".*Int_RemoveByIdx_BM.*"
 *
 * Created by shpolsky on 09.08.14.
 */
@SuppressWarnings("unused")
@State(Scope.Thread)
public class ArrayList_Int_RemoveByIdx_BM {

    private static final Random RAND = new Random();
    private static final int SIZE = 1_000_000;
    private static final int REMOVALS_NUMBER = 100;

    private final List<Integer> INT_AL = new ArrayList<>();
    private final List<Integer> INT_JAL = new JArrayList<>();
    private final JList<Integer> INT_JJAL = new JArrayList<>();
    private final IntArrayList INT_CAL = new IntArrayList();
    private final it.unimi.dsi.fastutil.ints.IntArrayList INT_FUAL =  new it.unimi.dsi.fastutil.ints.IntArrayList();
    private final TIntArrayList INT_TAL = new TIntArrayList();

    @Setup(Level.Invocation)
    public void fill() {
        IntListUtils.refill(INT_AL, SIZE);
        IntListUtils.refill(INT_JAL, SIZE);
        IntListUtils.refill(INT_JJAL, SIZE);
        IntListUtils.refill(INT_CAL, SIZE);
        IntListUtils.refill(INT_FUAL, SIZE);
        IntListUtils.refill(INT_TAL, SIZE);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaArrayListRemove() {
        for (int i = 0; i < REMOVALS_NUMBER; i++) {
            INT_AL.remove(RAND.nextInt(INT_AL.size()));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jArrayListRemove() {
        for (int i = 0; i < REMOVALS_NUMBER; i++) {
            INT_JAL.remove(RAND.nextInt(INT_JAL.size()));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListRemove() {
        for (int i = 0; i < REMOVALS_NUMBER; i++) {
            INT_JJAL.remove(RAND.nextInt(INT_JJAL.size()));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void coltArrayListRemove() {
        for (int i = 0; i < REMOVALS_NUMBER; i++) {
            INT_CAL.remove(RAND.nextInt(INT_CAL.size()));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void fastutilArrayListRemove() {
        for (int i = 0; i < REMOVALS_NUMBER; i++) {
            INT_FUAL.remove(RAND.nextInt(INT_FUAL.size()));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void troveArrayListRemove() {
        for (int i = 0; i < REMOVALS_NUMBER; i++) {
            INT_TAL.remove(RAND.nextInt(INT_TAL.size()), 1);
        }
    }
}

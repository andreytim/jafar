package com.andreytim.jafar.bm.list;

import cern.colt.list.IntArrayList;
import com.andreytim.jafar.core.list.JArrayList;
import com.andreytim.jafar.core.list.prim.JArrayListInt;
import com.andreytim.jafar.core.list.prim.JList;
import gnu.trove.list.array.TIntArrayList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Launch command:
 * > java -jar <path>/bm-<version>-jmh.jar -v extra -i 10 -wi 5 -f 1 -prof gc ".*Int_Add_BM.*"
 *
 * Created by shpolsky on 09.08.14.
 */
@SuppressWarnings("unused")
@State(Scope.Thread)
public class ArrayList_Int_Add_BM {

    private static final Random RAND = new Random();
    private static final int SIZE = 1_000_000;
    private static final int[] PREGENERATED_RANDS = new int[SIZE];
    static {
        for (int i = 0; i < SIZE; i++) {
            PREGENERATED_RANDS[i] = RAND.nextInt();
        }
    }

    private List<Integer> INT_AL;
    private List<Integer> INT_JAL;
    private JList<Integer> INT_JJAL;
    private JList<Integer> INT_JJIAL;
    private IntArrayList INT_CAL;
    private it.unimi.dsi.fastutil.ints.IntArrayList INT_FUAL;
    private TIntArrayList INT_TAL;

    @Setup(Level.Invocation)
    public void init() {
        INT_AL = new ArrayList<>();
        INT_JAL = new JArrayList<>();
        INT_JJAL = new JArrayList<>();
        INT_JJIAL = new JArrayListInt();
        INT_CAL = new IntArrayList();
        INT_FUAL =  new it.unimi.dsi.fastutil.ints.IntArrayList();
        INT_TAL = new TIntArrayList();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaArrayListAdd() {
        for (int r : PREGENERATED_RANDS) {
            INT_AL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jArrayListAdd() {
        for (int r : PREGENERATED_RANDS) {
            INT_JAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListAdd() {
        for (int r : PREGENERATED_RANDS) {
            INT_JJAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListIntAdd() {
        for (int r : PREGENERATED_RANDS) {
            INT_JJIAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void coltArrayListAdd() {
        for (int r : PREGENERATED_RANDS) {
            INT_CAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void fastutilArrayListAdd() {
        for (int r : PREGENERATED_RANDS) {
            INT_FUAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void troveArrayListAdd() {
        for (int r : PREGENERATED_RANDS) {
            INT_TAL.add(r);
        }
    }
}

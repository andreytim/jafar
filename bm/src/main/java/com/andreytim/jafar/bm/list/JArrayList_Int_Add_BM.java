package com.andreytim.jafar.bm.list;

import cern.colt.list.IntArrayList;
import com.andreytim.jafar.core.list.JArrayList;
import com.andreytim.jafar.core.list.prim.JArrayListInt;
import com.andreytim.jafar.core.list.prim.JList;
import gnu.trove.list.array.TIntArrayList;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Launch command:
 * > java -jar <path>/bm-<version>-jmh.jar -v extra -i 10 -wi 5 -f 1 -prof gc ".*Add_BM.*"
 *
 * Created by shpolsky on 09.08.14.
 */
@SuppressWarnings("unused")
public class JArrayList_Int_Add_BM {

    private static final Random RAND = new Random();
    private static final int SIZE = 1000000;
    private static final int[] PREGENERATED_RANDS = new int[SIZE];
    static {
        for (int i = 0; i < SIZE; i++) {
            PREGENERATED_RANDS[i] = RAND.nextInt();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void intArrayAdd() {
        int[] arr = new int[SIZE];
        int i = 0;
        for (int r : PREGENERATED_RANDS) {
            arr[i++] = r;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaArrayListAdd() {
        List<Integer> list = new ArrayList<>();
        for (int r : PREGENERATED_RANDS) {
            list.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jArrayListAdd() {
        List<Integer> list = new JArrayList<>();
        for (int r : PREGENERATED_RANDS) {
            list.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListAdd() {
        JList<Integer> list = new JArrayList<>();
        for (int r : PREGENERATED_RANDS) {
            list.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListIntAdd() {
        JList<Integer> list = new JArrayListInt();
        for (int r : PREGENERATED_RANDS) {
            list.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void coltArrayListAdd() {
        IntArrayList clist = new IntArrayList();
        for (int r : PREGENERATED_RANDS) {
            clist.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void fastutilArrayListAdd() {
        it.unimi.dsi.fastutil.ints.IntArrayList fulist = new it.unimi.dsi.fastutil.ints.IntArrayList();
        for (int r : PREGENERATED_RANDS) {
            fulist.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void troveArrayListAdd() {
        TIntArrayList tlist = new TIntArrayList();
        for (int r : PREGENERATED_RANDS) {
            tlist.add(r);
        }
    }
}

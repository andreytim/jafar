package com.andreytim.jafar.bm.list;

import cern.colt.list.BooleanArrayList;
import com.andreytim.jafar.core.list.JArrayList;
import com.andreytim.jafar.core.list.prim.JArrayListBoolean;
import com.andreytim.jafar.core.list.prim.JList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Launch command:
 * > java -jar <path>/bm-<version>-jmh.jar -v extra -i 10 -wi 5 -f 1 -prof gc ".*Boolean_Add_BM.*"
 *
 * Created by shpolsky on 09.08.14.
 */
@SuppressWarnings("unused")
@State(Scope.Thread)
public class ArrayList_Boolean_Add_BM {

    private static final Random RAND = new Random();
    private static final int SIZE = 1_000_000;
    private static final boolean[] PREGENERATED_RANDS = new boolean[SIZE];
    static {
        for (int i = 0; i < SIZE; i++) {
            PREGENERATED_RANDS[i] = (RAND.nextInt() & 1) == 0;
        }
    }

    private List<Boolean> BOOLEAN_AL;
    private List<Boolean> BOOLEAN_JAL;
    private JList<Boolean> BOOLEAN_JJAL;
    private JList<Boolean> BOOLEAN_JJIAL;
    private BooleanArrayList BOOLEAN_CAL;
    private it.unimi.dsi.fastutil.booleans.BooleanArrayList BOOLEAN_FUAL;

    @Setup(Level.Invocation)
    public void init() {
        BOOLEAN_AL = new ArrayList<>();
        BOOLEAN_JAL = new JArrayList<>();
        BOOLEAN_JJAL = new JArrayList<>();
        BOOLEAN_JJIAL = new JArrayListBoolean();
        BOOLEAN_CAL = new BooleanArrayList();
        BOOLEAN_FUAL =  new it.unimi.dsi.fastutil.booleans.BooleanArrayList();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaArrayListAdd() {
        for (boolean r : PREGENERATED_RANDS) {
            BOOLEAN_AL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jArrayListAdd() {
        for (boolean r : PREGENERATED_RANDS) {
            BOOLEAN_JAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListAdd() {
        for (boolean r : PREGENERATED_RANDS) {
            BOOLEAN_JJAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListBooleanAdd() {
        for (boolean r : PREGENERATED_RANDS) {
            BOOLEAN_JJIAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void coltArrayListAdd() {
        for (boolean r : PREGENERATED_RANDS) {
            BOOLEAN_CAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void fastutilArrayListAdd() {
        for (boolean r : PREGENERATED_RANDS) {
            BOOLEAN_FUAL.add(r);
        }
    }
}
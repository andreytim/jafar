package com.andreytim.jafar.bm.list;

import cern.colt.list.ByteArrayList;
import com.andreytim.jafar.core.list.JArrayList;
import com.andreytim.jafar.core.list.prim.JArrayListByte;
import com.andreytim.jafar.core.list.prim.JList;
import gnu.trove.list.array.TByteArrayList;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Launch command:
 * > java -jar <path>/bm-<version>-jmh.jar -v extra -i 10 -wi 5 -f 1 -prof gc ".*Byte_Add_BM.*"
 *
 * Created by shpolsky on 09.08.14.
 */
@SuppressWarnings("unused")
@State(Scope.Thread)
public class ArrayList_Byte_Add_BM {

    private static final Random RAND = new Random();
    private static final int SIZE = 1_000_000;
    private static final byte[] PREGENERATED_RANDS = new byte[SIZE];
    static {
        for (int i = 0; i < SIZE; i++) {
            PREGENERATED_RANDS[i] = (byte) RAND.nextInt();
        }
    }

    private List<Byte> BYTE_AL;
    private List<Byte> BYTE_JAL;
    private JList<Byte> BYTE_JJAL;
    private JList<Byte> BYTE_JJIAL;
    private ByteArrayList BYTE_CAL;
    private it.unimi.dsi.fastutil.bytes.ByteArrayList BYTE_FUAL;
    private TByteArrayList BYTE_TAL;

    @Setup(Level.Invocation)
    public void init() {
        BYTE_AL = new ArrayList<>();
        BYTE_JAL = new JArrayList<>();
        BYTE_JJAL = new JArrayList<>();
        BYTE_JJIAL = new JArrayListByte();
        BYTE_CAL = new ByteArrayList();
        BYTE_FUAL =  new it.unimi.dsi.fastutil.bytes.ByteArrayList();
        BYTE_TAL = new TByteArrayList();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaArrayListAdd() {
        for (byte r : PREGENERATED_RANDS) {
            BYTE_AL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jArrayListAdd() {
        for (byte r : PREGENERATED_RANDS) {
            BYTE_JAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListAdd() {
        for (byte r : PREGENERATED_RANDS) {
            BYTE_JJAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void jjArrayListByteAdd() {
        for (byte r : PREGENERATED_RANDS) {
            BYTE_JJIAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void coltArrayListAdd() {
        for (byte r : PREGENERATED_RANDS) {
            BYTE_CAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void fastutilArrayListAdd() {
        for (byte r : PREGENERATED_RANDS) {
            BYTE_FUAL.add(r);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void troveArrayListAdd() {
        for (byte r : PREGENERATED_RANDS) {
            BYTE_TAL.add(r);
        }
    }
}
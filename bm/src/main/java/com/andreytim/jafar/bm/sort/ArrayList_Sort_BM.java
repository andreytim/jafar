package com.andreytim.jafar.bm.sort;

import com.andreytim.jafar.bm.list.IntListUtils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by shpolsky on 28.08.14.
 */
@State(Scope.Thread)
public class ArrayList_Sort_BM {

    private static final int SIZE = 100_000;

    private List<Integer> INT_AL_SORTED = new ArrayList<>();
    private List<Integer> INT_AL_SORTED_REVERSED = new ArrayList<>();
    private List<Integer> INT_AL_RANDOM = new ArrayList<>();

    @Setup(Level.Invocation)
    public void fill() {
        IntListUtils.refillArithmeticProgression(INT_AL_SORTED, SIZE, 1, 1);
        IntListUtils.refillArithmeticProgression(INT_AL_SORTED_REVERSED, SIZE, SIZE, -1);
        IntListUtils.refill(INT_AL_RANDOM, SIZE);
    }

    // Standart Collections Sort

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaCollectionsSort_Sorted() {
        Collections.sort(INT_AL_SORTED);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaCollectionsSort_SortedReversed() {
        Collections.sort(INT_AL_SORTED_REVERSED);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaCollectionsSort_Random() {
        Collections.sort(INT_AL_RANDOM);
    }
}

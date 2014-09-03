package com.andreytim.jafar.bm.sort;

import com.andreytim.jafar.algo.sort.Sort;
import com.andreytim.jafar.algo.sort.SortUtils;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by shpolsky on 28.08.14.
 */
@SuppressWarnings("unused")
@State(Scope.Thread)
public abstract class SortBenchmark {

    private static final int SIZE = 10_000;

    private List<Integer> INT_AL_ASC;
    private List<Integer> INT_AL_DESC;
    private List<Integer> INT_AL_RANDOM;

    @Setup(Level.Invocation)
    public void fill() {
        INT_AL_ASC = SortUtils.getAscSortedIntArrayList(SIZE);
        INT_AL_DESC = SortUtils.getDescSortedIntArrayList(SIZE);
        INT_AL_RANDOM = SortUtils.getRandomIntArrayList(SIZE);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sort_ASC() {
        getSort().sort(INT_AL_ASC);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sort_DESC() {
        getSort().sort(INT_AL_DESC);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void sort_RANDOM() {
        getSort().sort(INT_AL_RANDOM);
    }

    protected abstract Sort getSort();
}

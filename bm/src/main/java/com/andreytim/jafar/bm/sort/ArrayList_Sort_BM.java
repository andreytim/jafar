package com.andreytim.jafar.bm.sort;

import com.andreytim.jafar.algo.sort.*;
import org.openjdk.jmh.annotations.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Launch command:
 * > java -jar <path>/bm-<version>-jmh.jar -v extra -i 10 -wi 5 -f 1 ".*Sort_BM.*"
 * Created by shpolsky on 28.08.14.
 */
@SuppressWarnings("unused")
@State(Scope.Thread)
public class ArrayList_Sort_BM {

    private static final int SIZE = 10_000;
    private static final Sort SELECTION_SORT = new SelectionSort();
    private static final Sort INSERTION_SORT = new InsertionSort();
    private static final Sort INSERTION_SIP_SORT = new InsertionSwapInPlaceSort();

    private List<Integer> INT_AL_ASC;
    private List<Integer> INT_AL_DESC;
    private List<Integer> INT_AL_RANDOM;

    @Setup(Level.Invocation)
    public void fill() {
        INT_AL_ASC = SortUtils.getAscSortedIntArrayList(SIZE);
        INT_AL_DESC = SortUtils.getDescSortedIntArrayList(SIZE);
        INT_AL_RANDOM = SortUtils.getRandomIntArrayList(SIZE);
    }

    // Standart Collections Sort

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaCollectionsSort_ASC() {
        Collections.sort(INT_AL_ASC);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaCollectionsSort_DESC() {
        Collections.sort(INT_AL_DESC);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void javaCollectionsSort_RANDOM() {
        Collections.sort(INT_AL_RANDOM);
    }

    // Selection Sort

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void selectionSort_ASC() {
        SELECTION_SORT.sort(INT_AL_ASC);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void selectionSort_DESC() {
        SELECTION_SORT.sort(INT_AL_DESC);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void selectionSort_RAND() {
        SELECTION_SORT.sort(INT_AL_RANDOM);
    }

    // Insertion Sort

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void insertionSort_ASC() {
        INSERTION_SORT.sort(INT_AL_ASC);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void insertionSort_DESC() {
        INSERTION_SORT.sort(INT_AL_DESC);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void insertionSort_RAND() {
        INSERTION_SORT.sort(INT_AL_RANDOM);
    }

    // Insertion Swap-In-Place Sort

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void insertionSwapInPlaceSort_ASC() {
        INSERTION_SIP_SORT.sort(INT_AL_ASC);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void insertionSwapInPlaceSort_DESC() {
        INSERTION_SIP_SORT.sort(INT_AL_DESC);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void insertionSwapInPlaceSort_RAND() {
        INSERTION_SIP_SORT.sort(INT_AL_RANDOM);
    }
}

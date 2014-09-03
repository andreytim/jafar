package com.andreytim.jafar.bm.sort;

import com.andreytim.jafar.algo.sort.InsertionSort;
import com.andreytim.jafar.algo.sort.Sort;

/**
 * Launch command:
 * > java -jar <path>/bm-<version>-jmh.jar -v extra -i 10 -wi 5 -f 1 ".*Sort_BM.*"
 * Created by shpolsky on 28.08.14.
 */
public class Insertion_Swap_Sort_BM extends SortBenchmark {
    @Override
    protected Sort getSort() {
        return new InsertionSort(InsertionSort.Mode.SWAPS);
    }
}

package com.andreytim.jafar.problems.topcoder.SRM641Div2;

/**
 * Created by shpolsky on 11.12.14.
 */
public class ByingTshirts {

    public int meet(int T, int[] Q, int[] P)
    {
        int qMon = 0, pMon = 0, days = 0;
        for (int i = 0; i < Q.length; i++) {
            qMon += Q[i];
            pMon += P[i];
            if (qMon >= T && pMon >= T) days++;
            if (qMon >= T) qMon -= T;
            if (pMon >= T) pMon -= T;
        }
        return days;
    }

}

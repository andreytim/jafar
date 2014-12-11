package com.andreytim.jafar.problems.topcoder.SRM641Div2;

/**
 * Created by shpolsky on 11.12.14.
 */
public class TrianglesContainOriginEasy {

    public double sign(int x1, int x2, int x3, int y1, int y2, int y3) {
        return (x1 - x3)*(y2-y3) - (x2-x3)*(y1-y3);
    }

    public boolean check(int x1, int x2, int x3, int y1, int y2, int y3) {
        boolean b1 = false, b2 = false, b3 = false;
        b1 = sign(0, x1, x2, 0, y1, y2) < 0.0;
        b2 = sign(0, x2, x3, 0, y2, y3) < 0.0;
        b3 = sign(0, x3, x1, 0, y3, y1) < 0.0;
        return (b1 == b2) && (b2 == b3);
    }

    public int count(int[] x, int[] y)
    {
        int count = 0;
        for (int i = 0; i < x.length-2; i++) {
            for (int j = i+1; j < x.length-1; j++) {
                for (int k = j+1; k < x.length; k++) {
                    if (check(x[i], x[j], x[k], y[i], y[j], y[k])) count++;
                }
            }
        }
        return count;
    }

}

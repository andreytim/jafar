package com.andreytim.jafar.problems.sortsearch;

/**
 * TopCoder:
 * Single Round Match 258 Round 1 - Division II, Level Two
 * http://community.topcoder.com/stat?c=problem_statement&pm=3970&rd=7993
 *
 * Created by shpolsky on 16.11.14.
 */
public class P78_AutoLoan {

    private static final double PRECISION = 1E-12;

    public double interestRate(double price, double monthlyPayment, int loanTerm) {
        double interestTotal = (monthlyPayment*loanTerm) - price;
        if (interestTotal < PRECISION) return 0.0;
        double lo = PRECISION, hi = interestTotal/price;
        while (hi - lo > PRECISION) {
            double balance = price;
            double interest = lo + (hi-lo)/2;
            for (int i = 0; i < loanTerm; i++) {
                balance = (1 + interest)*balance - monthlyPayment;
            }
            if (balance == 0) return interest*12;
            else if (balance > 0) hi = interest;
            else lo = interest;
        }
        return (lo + (hi-lo)/2)*12*100;
    }

    public static void test(double price, double monthlyPayment, int loanTerm) {
        System.out.printf("Input: price=%.4f, monthlyPayment=%.4f, term=%d; Result: %.8f\n",
                price, monthlyPayment, loanTerm, new P78_AutoLoan().interestRate(price, monthlyPayment, loanTerm));
    }

    public static void main(String[] args) {
        test(6800, 68, 100);
        test(2000, 510, 4);
        test(15000, 364, 48);
    }

}

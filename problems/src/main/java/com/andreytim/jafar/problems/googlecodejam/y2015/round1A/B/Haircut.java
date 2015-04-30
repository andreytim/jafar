package com.andreytim.jafar.problems.googlecodejam.y2015.round1A.B;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by tim on 28/04/15.
 */
public class Haircut {

    private static class Barber implements Comparable<Barber> {
        public final int idx;
        public final long time;
        public long currTime;
        public Barber(int idx, long time, long currTime) {
            this.idx = idx; this.time = time; this.currTime = currTime;
        }
        @Override public int compareTo(Barber other) {
            return currTime == other.currTime ? idx - other.idx : (currTime - other.currTime > 0 ? 1 : -1);
        }
    }

    public static int solve(Scanner in) {
        int B = in.nextInt(), N = in.nextInt();
        if (N <= B) return N;
        else N -= B;
        Queue<Barber> queue = new PriorityQueue<>();
        for (int i = 0; i < B; i++) {
            int t = in.nextInt();
            queue.offer(new Barber(i+1, t, t));
        }
        while (N > 1) {
            Barber curr = queue.poll();
            queue.offer(new Barber(curr.idx, curr.time, curr.currTime + curr.time));
            N--;
        }
        return queue.peek().idx;
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new FileInputStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
//        PrintStream out = System.out;
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %d\n", i+1, solve(in));
        }
        out.close();
    }

}

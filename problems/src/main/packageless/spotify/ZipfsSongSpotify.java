import java.io.PrintStream;
import java.lang.Comparable;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.TreeSet;

public class ZipfsSongSpotify {

    public static final double FP_EPSILON = 1e-10;

    public static class Song implements Comparable<Song> {
        private final int idx;
        private final String name;
        private final int zipfScore;
        private final long listeningScore;
        public Song(int idx, String name, int zi, int fi) {
            this.idx = idx;
            this.name = name;
            this.zipfScore = zi;
            this.listeningScore = fi;
        }
        @Override public String toString() { return name; }
        @Override public int compareTo(Song other) {
            double thisScore = (double) listeningScore / zipfScore;
            double otherScore = (double) other.listeningScore / other.zipfScore;
            if (Math.abs(otherScore - thisScore) < FP_EPSILON) {
                return other.idx - this.idx;
            } else if (otherScore > thisScore) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;
        int N = in.nextInt(), M = in.nextInt();
        NavigableSet<Song> heap = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            int fi = in.nextInt();
            String name = in.next();
            heap.add(new Song(i, name, N-i, fi));
            if (heap.size() > M) heap.pollLast();
        }
        for (Song s : heap) {
            out.printf("%s\n", s);
        }
    }

}
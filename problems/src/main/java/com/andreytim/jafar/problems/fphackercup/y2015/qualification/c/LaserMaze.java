package com.andreytim.jafar.problems.fphackercup.y2015.qualification.c;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by shpolsky on 10.01.15.
 */
public class LaserMaze {

    private static class Point {
        public final int x, y;
        public Point(int x, int y) { this.x = x; this.y = y; }
    }

    private static class State {
        public final Point p;
        public final int s;
        public State(int x, int y, int s) { this(new Point(x,y), s); }
        public State(Point p, int s) { this.p = p; this.s = s; }
        @Override public String toString() {
            return String.format("%d_%d_%d", p.x, p.y, s);
        }
    }

    private static class Maze {

        private final List<char[][]> states = new ArrayList<>();
        private Point start, goal;
        private int M, N;

        private Maze(){}

        public static Maze readFromInputStream(Scanner in) {
            Maze maze = new Maze();
            maze.M = in.nextInt();
            maze.N = in.nextInt();
            char[][] original = new char[maze.M][maze.N];
            for (int i = 0; i < maze.M; i++) {
                original[i] = in.next().toCharArray();
                for (int j = 0; j < maze.N; j++) {
                    if (original[i][j] == 'S') {
                        maze.start = new Point(i, j);
                        original[i][j] = '.';
                    } else if (original[i][j] == 'G') {
                        maze.goal = new Point(i, j);
                        original[i][j] = '.';
                    }
                }
            }
            maze.states.add(original);
            for (int i = 1; i < 4; i++) {
                maze.states.add(rotateTurrets(maze.states.get(i-1)));
            }
            for (int i = 0; i < 4; i++) {
                launchLasers(maze.states.get(i));
            }
            return maze;
        }

        private static char[][] rotateTurrets(char[][] maze) {
            char[][] newMaze = new char[maze.length][maze[0].length];
            for (int i = 0; i < maze.length; i++) {
                newMaze[i] = Arrays.copyOf(maze[i], maze[i].length);
            }
            Map<Character, Character> rotation = new HashMap<>();
            rotation.put('^', '>'); rotation.put('>', 'v');
            rotation.put('v', '<'); rotation.put('<', '^');
            for (int i = 0; i < newMaze.length; i++) {
                for (int j = 0; j < newMaze[0].length; j++) {
                    if (rotation.containsKey(newMaze[i][j])) {
                        newMaze[i][j] = rotation.get(newMaze[i][j]);
                    }
                }
            }
            return newMaze;
        }

        private static void launchLasers(char[][] maze) {
            Map<Character, Point> directions = new HashMap<>();
            directions.put('^', new Point(-1, 0));
            directions.put('>', new Point(0, 1));
            directions.put('v', new Point(1, 0));
            directions.put('<', new Point(0, -1));
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    if (directions.containsKey(maze[i][j])) {
                        char dir = maze[i][j];
                        maze[i][j] = '#';
                        int x = i + directions.get(dir).x, y = j + directions.get(dir).y;
                        while (x >= 0 && x <= maze.length-1 && y >= 0 && y <= maze[0].length-1) {
                            if (maze[x][y] == '#' || directions.containsKey(maze[x][y])) break;
                            maze[x][y] = '#';
                            x += directions.get(dir).x;
                            y += directions.get(dir).y;
                        }
                    }
                }
            }
        }

        public List<State> nextStates(State state) {
            int s = (state.s + 1) % 4;
            List<State> result = new ArrayList<>();
            if (state.p.x > 0 && states.get(s)[state.p.x-1][state.p.y] != '#')
                result.add(new State(state.p.x-1, state.p.y, s));
            if (state.p.x < M-1 && states.get(s)[state.p.x+1][state.p.y] != '#')
                result.add(new State(state.p.x+1, state.p.y, s));
            if (state.p.y > 0 && states.get(s)[state.p.x][state.p.y-1] != '#')
                result.add(new State(state.p.x, state.p.y-1, s));
            if (state.p.y < N-1 && states.get(s)[state.p.x][state.p.y+1] != '#')
                result.add(new State(state.p.x, state.p.y+1, s));
            return result;
        }

        public boolean isGoal(State state) {
            return state.p.x == goal.x && state.p.y == goal.y &&
                    states.get(state.s)[goal.x][goal.y] != '#';
        }

    }

    private static String solve(Scanner in) {
        String res = "impossible";
        Maze maze = Maze.readFromInputStream(in);
        State start = new State(maze.start, 0);
        Set<String> visited = new HashSet<>();
        visited.add(start.toString());
        Queue<State> queue = new LinkedList<>();
        queue.offer(start);
        Map<String, String> parents = new HashMap<>();
        parents.put(start.toString(), start.toString());
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            if (maze.isGoal(curr)) {
                int count = 0;
                String key = curr.toString();
                while (!parents.get(key).equals(key)) {
                    count++;
                    key = parents.get(key);
                }
                return "" + count;
            }
            for (State next : maze.nextStates(curr)) {
                String nextStr = next.toString();
                if (visited.add(nextStr)) {
                    queue.offer(next);
                    parents.put(nextStr, curr.toString());
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(System.in);
//        PrintStream out = System.out;
        Scanner in = new Scanner(LaserMaze.class.getResourceAsStream("in.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            out.printf("Case #%d: %s\n", i+1, solve(in));
        }
        out.close();
    }

}

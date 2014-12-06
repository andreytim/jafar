package com.andreytim.jafar.problems.hashtables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * CodeForces:
 * Round 281 - Div II, A
 * http://codeforces.com/contest/493/problem/A
 *
 * Created by shpolsky on 06.12.14.
 */
public class P96_VasyaAndFootballCF {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> teams = new HashMap<>();
        teams.put("h", reader.readLine().trim());
        teams.put("a", reader.readLine().trim());
        int N = Integer.parseInt(reader.readLine().trim());
        Set<String> yellowCards = new HashSet<>();
        Set<String> redCards = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] line = reader.readLine().trim().split(" ");
            String key = teams.get(line[1]) + line[2];
            if (line[3].equals("y")) {
                if (!yellowCards.add(key)) {
                    if (redCards.add(key)) {
                        result.add(teams.get(line[1]) + " " + line[2] + " " + line[0]);
                    }
                }
            } else if (redCards.add(key)) {
                result.add(teams.get(line[1]) + " " + line[2] + " " + line[0]);
            }
        }
        for (String r : result) {
            System.out.println(r);
        }
    }

}

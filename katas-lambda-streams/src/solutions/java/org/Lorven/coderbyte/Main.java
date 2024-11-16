package org.paumard.devoxxfr2019.A_lambdas.a_lambdas;

import java.util.*;
import java.io.*;

class Main {

    public static String ShortestPath(String[] strArr) {
        int n = Integer.parseInt(strArr[0]);
        Map<String, List<String>> graph = new HashMap<>();

        // initalisation nodes
        for (int i =1; i<=n; i++) {
            graph.put(strArr[i], new ArrayList<>());
        }

        // Build the graph from connections
        for (int i=n+1; i<strArr.length; i++ ) {
            String[] connection = strArr[i].split("-");

            if( graph.containsKey(connection[0]) && graph.containsKey(connection[1]) ) {
                graph.get(connection[0]).add(connection[1]);
                graph.get(connection[1]).add(connection[0]); // undirected
            }
        }

        // use BSF to find the shortest path
        return bsf(graph, strArr[0], strArr[n]);
    }

    private static String bsf(Map<String, List<String>> graph, String start, String end) {
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add( Collections.singletonList(start) );
        visited.add( start );

        while ( !queue.isEmpty() ) {
            List<String> path = queue.poll();
            String node = path.get( path.size() - 1 );

            if( node.equals(end) ) {
                return String.join("-", path);
            }


            if( graph.containsKey(node) ) {
                for( String neighbor : graph.get(node) ) {
                    if( !visited.contains(neighbor) ) {
                        visited.add( neighbor );
                        List<String> newPath = new ArrayList<>();
                        newPath.add(neighbor);
                        queue.add(newPath);
                    }
                }
            }
        }

        return "-1";
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.println(ShortestPath( new String[] {"5","A","B","C","D","F","A-B","A-C","B-C","C-D","D-F"} ));
    }

}
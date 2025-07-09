import java.util.*;

class Solution {
    static class Node {
        int idx, cost, state; // 노드 번호, 누적 비용, 트랩 상태 비트
        Node(int idx, int cost, int state) {
            this.idx = idx;
            this.cost = cost;
            this.state = state;
        }
    }

    static class Edge {
        int to, weight, dir; // dir: 0 = 정방향, 1 = 역방향
        Edge(int to, int weight, int dir) {
            this.to = to;
            this.weight = weight;
            this.dir = dir;
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        Map<Integer, Integer> trapIndex = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapIndex.put(traps[i], i); // 트랩 번호 → 비트 인덱스
        }

        // 양방향 간선 저장 (정방향, 역방향)
        List<Edge>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] road : roads) {
            graph[road[0]].add(new Edge(road[1], road[2], 0)); // 정방향
            graph[road[1]].add(new Edge(road[0], road[2], 1)); // 역방향
        }

        // visited[노드 번호][트랩 상태] = 방문 여부
        boolean[][] visited = new boolean[n + 1][1 << traps.length];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.add(new Node(start, 0, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.idx == end) return now.cost;
            if (visited[now.idx][now.state]) continue;
            visited[now.idx][now.state] = true;

            boolean nowActive = trapIndex.containsKey(now.idx) && ((now.state & (1 << trapIndex.get(now.idx))) != 0);

            for (Edge edge : graph[now.idx]) {
                int next = edge.to;
                boolean nextActive = trapIndex.containsKey(next) && ((now.state & (1 << trapIndex.get(next))) != 0);

                boolean isReversed = nowActive ^ nextActive;
                if ((isReversed && edge.dir == 0) || (!isReversed && edge.dir == 1)) continue;

                int nextState = now.state;
                if (trapIndex.containsKey(next)) {
                    nextState ^= (1 << trapIndex.get(next)); // 트랩 토글
                }

                pq.add(new Node(next, now.cost + edge.weight, nextState));
            }
        }

        return -1; // 도달 불가 시
    }
}

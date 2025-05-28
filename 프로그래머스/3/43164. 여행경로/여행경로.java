import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> path = new LinkedList<>();

    public String[] solution(String[][] tickets) {
        // 1. 그래프 구성
        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }

        // 2. DFS 시작 (항상 ICN에서 출발)
        dfs("ICN");

        // 3. 결과 반환
        return path.toArray(new String[0]);
    }

    private void dfs(String curr) {
        // 현재 노드에서 출발 가능한 도착지 모두 탐색
        PriorityQueue<String> pq = graph.get(curr);
        while (pq != null && !pq.isEmpty()) {
            dfs(pq.poll()); // 사전순 우선 목적지로 이동
        }
        path.addFirst(curr); // 더 이상 갈 곳 없을 때 경로 앞에 추가
    }
}
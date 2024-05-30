import java.util.*;
class Solution {
    List<List<Integer>> list;
    int answer;
    public int solution(int n, int[][] lighthouse) {
               
        answer = 0;
        
        // 리스트 생성
        list = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        // 리스트에 뱃길 추가
        for(int i=0; i<lighthouse.length; i++) {
            int[] light = lighthouse[i];
            
            // 양방향 저장
            list.get(light[0]).add(light[1]);
            list.get(light[1]).add(light[0]);
        }
        
        int tmp = DFS(1,0);
        return answer;
    }
    // 켜기 0, 끄기 1
    // 리프노드일 경우 자기 부모노드가 켜져야함.
    // 리포노드가 아닐 경우 자식 노드로 DFS진행.
    // 다음 DFS가 켜져 있으면 부모노드는 안켜도됨.
    // 다음 DFS가 꺼져있으면 부모노드는 켜주어야함. 
    public int DFS(int cur, int before) { // 현재 노드 - 이전 노드 
        
        // 리프노드면 켜주지 말고 종료.
        if(list.get(cur).size() == 1 && list.get(cur).get(0) == before) {
            return 1;
        }
        
        // 리프노드가 아니면 자식 노드 순회 
        int cnt = 0;
        for(int i=0; i<list.get(cur).size(); i++) {
            int next = list.get(cur).get(i);
            if(next == before) { // (1,2) -> (2,1) -> (1,2) 방지
                continue;
            }
            cnt += DFS(next, cur);
        }
        
        // 자식 노드가 켜져 있으면 지금 노드는 안켜줌. 
        if(cnt == 0) {
            return 1;
        }
        
        // 현재 노드 켜주기
        answer++;
        return 0;
    }
}
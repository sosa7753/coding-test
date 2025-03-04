import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
    static ArrayList<ArrayList<Integer>> graph  = new ArrayList<>();
    static int[] count;
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int i=0;i<=N;i++)
            graph.add(new ArrayList<>());
        count = new int[N+1];
        //입력되는 문제 선행관계 저장
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine()," ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);	//A는 B의 선행문제이다.
            count[B]++;	//B의 선행문제 개수 증가
        }
        //난이도가 쉬운 것부터 탐색하기 위해 우선순위 큐 사용
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //선행 문제가 없는 문제들 우선순위 큐에 저장
        for(int i=1;i<=N;i++)
            if(count[i] == 0)	//선행 문제가 없을 때
                pq.offer(i);
        //위상 정렬을 이용한 문제들 탐색
        while(!pq.isEmpty()) {
            int cur = pq.poll();	//현재 문제 풀기 완료
            sb.append(cur).append(" ");	//현재 문제 StringBuilder 저장
            //현재 문제를 풀면 이어지는 문제들 탐색
            for(int nxt : graph.get(cur)) {
                count[nxt]--;
                if(count[nxt] == 0)	//이어지는 문제도 선행문제를 다 풀었을 경우
                    pq.add(nxt);
            }
        }
        bw.write(sb.toString());	//문제 풀이 순서 BufferedWriter 저장
        bw.flush();		//결과 출력
        bw.close();
        br.close();


    }
}
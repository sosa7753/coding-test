import java.util.*;
import java.io.*;
class Main {
  static int N;
  static Map<Integer, Integer> map = new HashMap<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    st = new StringTokenizer(br.readLine());
    int start = 0;
    int answer = 0;
    for(int i=0; i<N; i++) {
      start |= (1 << i) * Integer.parseInt(st.nextToken());
      answer |= (1 << i);
    }
    start = ((1<<N) - 1) & start;
    answer = ((1<<N) - 1) & answer;

    for(int i=0; i<N; i++) {
      int v = 0;
      st = new StringTokenizer(br.readLine());
      int len = Integer.parseInt(st.nextToken());
      v |= (1<<i);
      for(int j=0; j<len; j++) { // 모든 걸 역순서로 생각
        int t = Integer.parseInt(st.nextToken()) -1;
        v |= (1<<t);
      }

      v = ((1<<N) - 1) & v;
      map.put(i, v);
    }

    System.out.print(BFS(start, answer));
  }

  public static int BFS(int start, int answer) {
    Set<Integer> set = new HashSet<>();
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{start, 0});
    set.add(start);

    while(!q.isEmpty()) {
      int[] now = q.poll();
      int cur = now[0]; int cnt = now[1];
      if(cur == answer) {
        return cnt;
      }

      for(int i=0; i<N; i++) {
        if((cur & (1<<i)) != 0) continue;

        int nc = cur ^ (map.get(i));
        if(set.contains(nc)) continue;

        set.add(nc);
        q.offer(new int[]{nc, cnt+1});
      }
    }
    return -1;
  }
}
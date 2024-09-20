import java.util.*;
import java.io.*;
class Main{
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N;
  static int L;
  static int R;
  static int[][] map;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // map 크기
    L = Integer.parseInt(st.nextToken()); // ~ 이상
    R = Integer.parseInt(st.nextToken()); // ~ 이하

    map = new int[N][N];
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = 0;
    while(true) {
      if(!solve()) {
        break;
      }
      answer++;
    }

    System.out.println(answer);
  }

  public static boolean solve() {
    boolean[][] visited = new boolean[N][N];
    List<Integer> list = new ArrayList<>();

    int cnt = -1;
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(!visited[i][j]) {
          int[] now = BFS(i, j, cnt, visited); // 칸, 합
          if(now[0] == 1) {
            continue;
          }
          list.add(now[1]/now[0]);
          cnt--;
        }
      }
    }

    if(list.isEmpty()) {
      return false;
    }
    change(list);
    return true;
  }

  public static int[] BFS(int r, int c, int cnt, boolean[][] visited) {
    int first = map[r][c];
    int sum = first;
    int num = 1;

    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {r, c});
    visited[r][c] = true;

    while(!queue.isEmpty()) {
      int[] cur = queue.poll();

      for(int i=0; i<4; i++) {
        int row = cur[0] + dr[i];
        int col = cur[1] + dc[i];

        if(row < 0 || row > N-1 || col < 0 || col > N-1) {
          continue;
        }

        if(visited[row][col]) {
          continue;
        }

        int gap = Math.abs(map[row][col] - map[cur[0]][cur[1]]);
        if(gap >=L && gap <=R) {
          visited[row][col] = true;
          queue.offer(new int[] {row, col});
          num++;
          sum += map[row][col];
        }
      }
      map[cur[0]][cur[1]] = cnt;
    }

    if(sum == first) {
      map[r][c] = first;
    }

    return new int[] {num, sum};
  }

  public static void change(List<Integer> list) {
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(map[i][j] >= 0) {
          continue;
        }

        map[i][j] = list.get(-map[i][j] - 1);
      }
    }
  }
}
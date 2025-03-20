import java.util.*;
import java.io.*;
class Main {
  static class Node {
    int r;
    int c;
    Node(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }

  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, M;
  static int[][] map;
  static int num = 2;
  static List<List<Node>> list = new ArrayList<>();
  static PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> (x[2] - y[2])); // 출발섬, 도착섬, 거리

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];

    for (int i = 0; i <= 7; i++) {
      list.add(new ArrayList<>());
    }

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // 섬 찾기
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] == 1) {
          BFS(num, i, j);
          num++;
        }
      }
    }

    DFS(); // 각 섬마다 모든 연결경로 저장하기
    System.out.print(Kruscal()); // MST 수행하기
  }

  public static void BFS(int num, int row, int col) {
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{row, col});
    map[row][col] = num;
    list.get(num).add(new Node(row, col));

    while (!q.isEmpty()) {
      int[] now = q.poll();
      int r = now[0];
      int c = now[1];

      for (int i = 0; i < 4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];

        if (nr < 0 || nr > N - 1 || nc < 0 || nc > M - 1) {
          continue;
        }

        if (map[nr][nc] != 1) {
          continue;
        }

        map[nr][nc] = num;
        q.offer(new int[]{nr, nc});
        list.get(num).add(new Node(nr, nc));
      }
    }
  }

  public static void DFS() {
    for (int i = 2; i < num; i++) {
      for (Node node : list.get(i)) {
        for (int j = 0; j < 4; j++) {
          int nr = node.r;
          int nc = node.c;
          int len = 0;
          while (nr + dr[j] < N && nr + dr[j] >= 0 && nc + dc[j] < M && nc + dc[j] >= 0) {
            nr += dr[j];
            nc += dc[j];

            if (map[nr][nc] != 0) {
              if (len >= 2 && map[nr][nc] != i) {
                pq.offer(new int[]{i, map[nr][nc], len});
              }
              break;
            }
            len++;
          }
        }
      }
    }
  }

  public static int Kruscal() {
    int result = 0;
    int cnt = 1;
    int[] parent = new int[num]; // 섬은 최대 2~7까지 있음.
    for (int i = 2; i < parent.length; i++) {
      parent[i] = i;
    }

    while (!pq.isEmpty()) {
      int[] now = pq.poll();
      int start = now[0];
      int end = now[1];
      int value = now[2];

      if (!union(start, end, parent)) {
        result += value;
        cnt++;
      }
      if (cnt == num - 2) {
        return result;
      }
    }
    return -1;
  }

  public static boolean union(int a, int b, int[] parent) {
    a = find(a, parent);
    b = find(b, parent);
    if(a == b) {
      return true;
    }

    if (a < b) { // 작은 부모로 업데이트
      parent[b] = a;
    } else {
      parent[a] = b;
    }
    return false;
  }

  public static int find(int a, int[] parent) {
    if (parent[a] == a) {
      return a;
    }
    return parent[a] = find(parent[a], parent);
  }
}
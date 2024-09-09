import java.util.*;
import java.io.*;
class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N;
  static int M;
  static int[][] map;
  static int[] arr;
  static int[][] board;
  static int min = Integer.MAX_VALUE;
  static List<int[]> list = new ArrayList<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];

    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] != 0 && map[i][j] != 6) {
          list.add(new int[] {i, j, map[i][j]});
        }
      }
    }

    arr = new int[list.size()];
    DFS(0);
    System.out.println(min);
  }

  public static void DFS(int depth) {
    if(depth == arr.length) {
      board = new int[N][M];
      for(int i=0; i<N; i++) {
        board[i] = map[i].clone();
      }
      cctv(board);
      return;
    }

    for(int i=0; i<4; i++) {
      arr[depth] = i;
      DFS(depth+1);
    }
  }

  public static void cctv(int[][] board) {
    for(int i=0; i<list.size(); i++) {
      int[] now = list.get(i);

      if(now[2] == 1) {
        plus(board, now[0], now[1], arr[i]);
      }else if(now[2] == 2) {
        plus(board, now[0], now[1], arr[i]);
        plus(board, now[0], now[1], (arr[i] + 2)%4);
      }else if(now[2] == 3) {
        plus(board, now[0], now[1], arr[i]);
        plus(board, now[0], now[1], (arr[i] + 1)%4);
      }else if(now[2] == 4) {
        plus(board, now[0], now[1], arr[i]);
        plus(board, now[0], now[1], (arr[i] + 1)%4);
        plus(board, now[0], now[1], (arr[i] + 3)%4);
      }else if(now[2] == 5) {
        plus(board, now[0], now[1], arr[i]);
        plus(board, now[0], now[1], (arr[i] + 1)%4);
        plus(board, now[0], now[1], (arr[i] + 2)%4);
        plus(board, now[0], now[1], (arr[i] + 3)%4);
      }
    }

    min = Math.min(min, check(board));
  }

  // 현재 위치에서 해당방향으로 진행하기
  public static void plus(int[][] board, int r, int c, int direct) {
    int nr = r + dr[direct];
    int nc = c + dc[direct];

    while(nr >= 0 && nr < N && nc >= 0 && nc < M) {
      if(board[nr][nc] == 0) {
        board[nr][nc] = -1;
      }else if(board[nr][nc] == 6) {
        break;
      }
      nr += dr[direct];
      nc += dc[direct];
    }
  }

  public static int check(int[][] board) {
    int result = 0;
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        if(board[i][j] == 0) {
          result++;
        }
      }
    }
    return result;
  }
}
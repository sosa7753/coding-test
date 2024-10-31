import java.util.*;
import java.io.*;
class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;

    while(true) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());

      if(n == 0 && k == 0) {
        break;
      }

      st = new StringTokenizer(br.readLine());
      int[] arr = new int[n];

      for(int i=0; i<n; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }

      sb.append(solve(arr, k)).append("\n");

    }
    System.out.print(sb);
  }

  public static int solve(int[] arr, int k) {
    if(arr.length == 1) {
      return 0;
    }
    int[] depth = new int[arr.length];
    int[] parent = new int[arr.length]; // 현재 인덱스의 부모 인덱스 
    parent[0] = -1;  
    
    int idxK = -1;
    int idx = -1; // 현재 부모 인덱스
    int pre = 0;
    for(int i=1; i<arr.length; i++) {
      if(arr[pre] + 1 != arr[i]) { // 연속된 자식이 아니면 부모 올려주기 
        idx++;
      }
      parent[i] = idx;
      depth[i] = depth[idx] + 1;

      if(arr[i] == k) {
        idxK = i;
      }
      pre = i;
    }

    // 정답
    int result = 0;
    for(int i=0; i<arr.length; i++) {
        if(depth[i] > depth[idxK]) {
            break;
        }
       // depth 같음. 부모가 다름. 부모의 부모가 같음.
      if(depth[i] == depth[idxK] && parent[i] != parent[idxK] && parent[parent[i]] == parent[parent[idxK]]) { 
        result++;
      }
    }
    return result;
  }
}
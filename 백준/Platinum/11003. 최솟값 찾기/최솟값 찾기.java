import java.util.*;
import java.io.*;
class Main {
    public static class Node {
        int value;
        int idx;
        Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        Deque<Node> dq = new LinkedList<>();
        
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=0; i<N; i++) {
            while(!dq.isEmpty() && dq.getLast().value > arr[i]) {
                dq.removeLast();
            }
            
            dq.addLast(new Node(arr[i], i));
            
            if(dq.getFirst().idx <= i-K) {
                dq.removeFirst();
            }
            
            bw.write(dq.getFirst().value + " ");
        }
        
        bw.flush();
        bw.close();       
    }
}
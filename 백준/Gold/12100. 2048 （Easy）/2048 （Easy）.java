import java.util.*;
import java.io.*;

class Main {
    static int answer = 0;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        int[][] map = new int[N][N];
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            String[] arr = str.split(" ");
            for(int j=0; j<arr.length; j++) {
                map[i][j] = Integer.parseInt(arr[j]);
            }
        }
        
        int[] go = new int[5];
        DFS(map, go, 0);     
        System.out.print(answer);
    }
    
    // DFS 로직 
    public static void DFS(int[][] map, int[] go, int cnt) {
        if(cnt == 5) {
           int[][] board = new int[N][N];
           for(int i=0; i<N; i++) {
               board[i] = Arrays.copyOf(map[i], N);
           }
           board = move(board, go);
           answer = Math.max(answer, result(board));
           return;
        }
                    
        for(int i=0; i<4; i++) {
            go[cnt] = i;
            DFS(map, go, cnt+1);
        }
    }
    // 이동하는 로직
    public static int[][] move(int[][] board, int[] go) {           
        for(int direct : go) {
            if(direct==0) { // 상
                for(int i = 0; i < N; i++) {
	                int index = 0; // 값을 넣을 위치
	                int block = 0; // 최근 블록의 값
	                for(int j = 0; j < N; j++) {
                    	// 현재 블록의 값이 0이 아니라면
	                    if(board[j][i] != 0) {
                        	// 최근 블록의 값과 현재 블록의 값이 같다면
	                        if(block == board[j][i]) {
                            	// 블록이 합쳐진다
	                        	board[index - 1][i] = block * 2; 
                                // 블록이 합쳐졌으므로 0으로 갱신
	                            block = 0;
                                // 값을 합쳤으므로 현재 블록의 값을 0으로 갱신
	                            board[j][i] = 0;
	                        }
                            // 최근 블록의 값과 현재 블록의 값이 다르다면
	                        else {
                            	// 블록 변수의 값을 현재 블록의 값으로 갱신
	                            block = board[j][i];
                                // 현재 블록의 값을 0으로 바꿔줌
	                            board[j][i] = 0;
                                // 값을 넣을 위치에 최근 블록의 값을 넣음
	                            board[index][i] = block;
                                // 값을 넣을 위치 +1
	                            index++;
	                        }
	                    }
	                }
	            }  
            }else if(direct==1) { // 우 
                for(int i = 0; i < N; i++) {
	                int index = N - 1;
	                int block = 0;
	                for(int j = N - 1; j >= 0; j--) {
	                    if(board[i][j] != 0) {
	                        if(block == board[i][j]) {
	                        	board[i][index + 1] = block * 2;
	                            block = 0;
	                            board[i][j] = 0;
	                        }
	                        else {
	                            block = board[i][j];
	                            board[i][j] = 0;
	                            board[i][index] = block;
	                            index--;
	                        }
	                    }
	                }
	            }  
            }else if(direct==2) { // 하
                for(int i = 0; i < N; i++) {
	                int index = N - 1;
	                int block = 0;
	                for(int j = N - 1; j >= 0; j--) {
	                    if(board[j][i] != 0) {
	                        if(block == board[j][i]) {
	                        	board[index + 1][i] = block * 2;
	                            block = 0;
	                            board[j][i] = 0;
	                        }
	                        else {
	                            block = board[j][i];
	                            board[j][i] = 0;
	                            board[index][i] = block;
	                            index--;
	                        }
	                    }
	                }
	            }
            }else { // 좌 
                for(int i = 0; i < N; i++) {
	                int index = 0;
	                int block = 0;
	                for(int j = 0; j < N; j++) {
	                    if(board[i][j] != 0) {
	                        if(block == board[i][j]) {
	                        	board[i][index - 1] = block * 2;
	                            block = 0;
	                            board[i][j] = 0;
	                        }
	                        else {
	                            block = board[i][j];
	                            board[i][j] = 0;
	                            board[i][index] = block;
	                            index++;
	                        }
	                    }
	                }
	            }
            }
        }
        return board;
    }
        
    // 최댓값 구하는 로직     
    public static int result(int[][] map) {
        int max = 0;
            
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                max = Math.max(map[i][j], max);
            }
        }
        return max;
    }
}
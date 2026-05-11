import java.util.*;
class Solution {
    long[] crr, drr;   
    public long[] solution(int[] arr, long l, long r) {       
        int n = arr.length;
        crr = new long[n+1]; // arr 인덱스 기점 
        for(int i=1; i<=n; i++) {
            crr[i] = crr[i-1] + arr[i-1];
        }
        
        drr = new long[n+1]; // crr 값 위치까지 구간합.
        for(int i=1; i<=n; i++) {
            drr[i] = drr[i-1] + (long)Math.pow(crr[i] - crr[i-1], 2);
        }
        
        long K = sum(arr, l, r);
        
        long C = 0;
        long len = r - l + 1;
        long cL = 1;
        long cR = len;
        while(cL <= (crr[n] - len+1) && cR <= crr[n]) {
            int left = binarySearch(cL);
            int right = binarySearch(cR);
            
            long pre = sum(arr, cL, cR);
            if(cR == crr[n]) { // 끝에 도달한 경우
                if(pre == K) {
                    C++;
                }
                break;
            }
            
            
            if(crr[left] == cL || crr[right] == cR) { // 어느 한 지점이 기점
                if(pre == K) {
                    C++;
                }
                cL++;
                cR++;
                continue;              
            }
            
            long nextLen = Math.min(crr[left+1] - cL, crr[right+1] - cR);
            if(pre == K) {
                if(arr[left] == arr[right]) {
                    C += 1 + nextLen;
                }else {
                    C++;
                }
            }else {
                long ex = arr[right] - arr[left];
                if(ex !=0 && (K-pre)%ex == 0 && (K-pre)/ex <= nextLen && (K-pre)/ex >= 0) {
                    C++; 
                }     
            }         
            
            cL += nextLen + 1;
            cR += nextLen + 1;
        }
        
        return new long[]{K, C};
    }
    
    public long sum(int[] arr, long l, long r) {
        // 범위가 속한 crr 인덱스
        int left = binarySearch(l); // l이 기점이거나 left보다 큼
        int right = binarySearch(r); // r이 기점이거나 right보다 큼

        // l이 기점이 아니면 crr[left] + 1 ~ l-1 까지 뺴기 
        long exL = 0;
        if(crr[left] == l) {
            exL = arr[left-1];  
        }else {
            exL = - (l-1 - crr[left]) * arr[left];
        }
             
        // right는 빼기
        long exR = 0;
        if(crr[right] < r) {
            exR = (r - crr[right]) * arr[right];
        }
    
       return drr[right] - drr[left] + exR + exL;
    }
    
    public int binarySearch(long t) { // 내 위치 이전 인덱스
        int L = 0;
        int R = crr.length-1;
        int answer = 0;
        while(L <= R) {
            int mid = (L + R)/2;
            
            if(crr[mid] == t) {
                return mid;
            }else if(crr[mid] > t) {
                R = mid - 1;
            }else {
                answer = mid;
                L = mid + 1;
            }
        }
        return answer;
    }
}
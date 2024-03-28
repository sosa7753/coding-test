class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        long answer = 0;
        
        int last = 0; // 마지막 집
        
        // 맨 끝에서 부터 배달과 수거 택배가 모두 0인 집은 패스
        for(int i=n-1; i>=0; i--) {
            if(deliveries[i] == 0 && pickups[i] == 0) {
                continue;
            }
            last = i;
            break;
        }
        
        if(last == 0) {
            return 0;
        }
                     
        // 모두 수거할 때까지 반복하기 
        while(last >= 0) {
            
            int tmp = last; 
            
            answer = answer + (last+1) * 2;
            
            int curP = 0; // 이번에 수거해야 할 개수 
            for(int i=last; i>=0; i--) {
                if(curP + pickups[i] > cap) { // 도착한 집까지 수거 개수가 cap 이상임.
                    pickups[i] = pickups[i] - cap + curP;
                    last = i;
                    break;
                }else { // 더 실을 수 있음.
                    curP += pickups[i];
                    pickups[i] = 0;
                    last = i-1;
                    continue;
                }                
            }
            
            int curD = 0; // 이번에 배달해야할 개수 
            for(int i=tmp; i>=0; i--) {
                if(curD + deliveries[i] > cap) { // 현재 집까지 택배가 최대 개수 이상이면,
                    deliveries[i]  = deliveries[i] - cap + curD;
                    tmp = i;
                    break;
                }else { // 현재 집 택배를 다 실을 수 있다면, 
                    curD += deliveries[i];
                    deliveries[i] = 0;
                    tmp = i-1;
                    continue;                 
                }
            }  
            
            last = Math.max(tmp, last);
        }
        
        return answer;
    }
}
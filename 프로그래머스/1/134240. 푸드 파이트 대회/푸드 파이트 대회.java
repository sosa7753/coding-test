class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();

        int[] arr = new int[food.length];
        for(int i=1; i<food.length; i++) {
            if(food[i]%2 == 1) {
                arr[i] = (food[i]-1)/2;
            }else {
                arr[i] = food[i]/2;
            }
        }
        
        for(int i=1; i<arr.length; i++) {
            for(int j=1; j<=arr[i]; j++) {
                sb.append(i);
            }
        }
        result.append(sb.toString());
        result.append("0");
        result.append(sb.reverse().toString());
        return result.toString();
    }
}
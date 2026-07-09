class Solution {
    public int rob(int[] nums) {


 if (nums == null || nums.length == 0) {
        return 0;
    }
    
    int prev2 = 0;
    int prev1 = 0;
    
    for (int num : nums) {
        int temp = Math.max(prev2 + num, prev1);
        prev2 = prev1;
        prev1 = temp;
    }
    
    return prev1;

    }
}
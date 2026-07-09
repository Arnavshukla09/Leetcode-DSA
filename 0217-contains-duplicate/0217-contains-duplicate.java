class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> dup=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(!dup.add(nums[i])){
                return true;
            }
        }
        return false;
    }
}
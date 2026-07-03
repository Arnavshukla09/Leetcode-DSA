class Solution {
    public int[] sortedSquares(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        // Find the first non-negative number
        int right = 0;
        while (right < n && nums[right] < 0) {
            right++;
        }

        int left = right - 1;
        int k = 0;

        // Merge the two halves
        while (left >= 0 && right < n) {

            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare <= rightSquare) {
                result[k++] = leftSquare;
                left--;
            } else {
                result[k++] = rightSquare;
                right++;
            }
        }

        // Copy remaining left side
        while (left >= 0) {
            result[k++] = nums[left] * nums[left];
            left--;
        }

        // Copy remaining right side
        while (right < n) {
            result[k++] = nums[right] * nums[right];
            right++;
        }

        return result;
    }
}
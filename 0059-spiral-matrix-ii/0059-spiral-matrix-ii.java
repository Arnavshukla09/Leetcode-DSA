class Solution {
    public int[][] generateMatrix(int n) {

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        int[][] result = new int[n][n];
        int value = 1;

        while (top <= bottom && left <= right) {

            // Left -> Right
            for (int i = left; i <= right; i++) {
                result[top][i] = value++;
            }
            top++;

            // Top -> Bottom
            for (int i = top; i <= bottom; i++) {
                result[i][right] = value++;
            }
            right--;

            // Right -> Left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result[bottom][i] = value++;
                }
                bottom--;
            }

            // Bottom -> Top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[i][left] = value++;
                }
                left++;
            }
        }

        // Printing (for debugging)
         System.out.println(Arrays.deepToString(result));

        return result;
    }
}
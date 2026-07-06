class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {

        int[][] ans = new int[rows * cols][2];

        // Right, Down, Left, Up
        int[][] dir = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
        };

        int r = rStart;
        int c = cStart;

        int index = 0;
        ans[index++] = new int[]{r, c};

        int step = 1;
        int d = 0;

        while (index < rows * cols) {

            // Two directions have the same step length
            for (int i = 0; i < 2; i++) {

                for (int j = 0; j < step; j++) {

                    r += dir[d][0];
                    c += dir[d][1];

                    if (r >= 0 && r < rows && c >= 0 && c < cols) {
                        ans[index++] = new int[]{r, c};

                        if (index == rows * cols)
                            return ans;
                    }
                }

                d = (d + 1) % 4;
            }

            step++;
        }

        return ans;
    }
}
class Solution {

    // Four possible directions:
    // Down, Right, Up, Left
    static final int[][] dirs = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        // Size of the square grid
        int n = grid.size();

        // If the start or destination itself contains a thief,
        // safeness factor is 0.
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1)
            return 0;

        // Copy List<List<Integer>> into a normal 2D array
        // so we can modify values directly.
        int[][] A = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = grid.get(i).get(j);
            }
        }

        // Queue used for Multi-Source BFS
        Queue<int[]> q = new LinkedList<>();

        // Put every thief into the queue.
        // Each thief acts as a starting point.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (A[i][j] == 1) {
                    q.add(new int[] { i, j });
                }
            }
        }

        // -------------------------------
        // MULTI-SOURCE BFS
        // -------------------------------
        // Goal:
        // Fill every cell with
        // (distance from nearest thief + 1)
        //
        // Example:
        //
        // Before
        // 0 0 1
        // 0 0 0
        // 0 0 0
        //
        // After
        // 3 2 1
        // 4 3 2
        // 5 4 3
        //
        // Actual distance = value - 1
        // -------------------------------

        while (!q.isEmpty()) {

            // Remove current cell
            int[] head = q.poll();

            int i = head[0];
            int j = head[1];

            // Current value
            int v = A[i][j];

            // Visit all 4 neighbours
            for (int[] d : dirs) {

                int x = i + d[0];
                int y = j + d[1];

                // Check:
                // 1. Inside grid
                // 2. Not visited before (still 0)
                if (Math.min(x, y) >= 0 &&
                    Math.max(x, y) < n &&
                    A[x][y] == 0) {

                    // Distance increases by 1
                    A[x][y] = v + 1;

                    // Continue BFS
                    q.add(new int[] { x, y });
                }
            }
        }

        /*
            Now A stores:

            distance from nearest thief + 1

            Example

            3 2 1
            4 3 2
            5 4 3

            Which actually means

            2 1 0
            3 2 1
            4 3 2
        */


        // -------------------------------
        // PRIORITY QUEUE (MAX HEAP)
        // -------------------------------
        // Stores:
        // {current path safeness, row, column}
        //
        // Always remove the path with
        // the highest safeness first.
        // -------------------------------

        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Start from top-left corner
        pq.add(new int[] { A[0][0], 0, 0 });

        while (!pq.isEmpty()) {

            int[] head = pq.poll();

            int sf = head[0];   // current path safeness
            int i = head[1];
            int j = head[2];

            // Destination reached
            if (i == n - 1 && j == n - 1)
                return sf - 1;

            // Explore neighbours
            for (int[] d : dirs) {

                int x = i + d[0];
                int y = j + d[1];

                // Valid cell and not visited yet
                if (Math.min(x, y) >= 0 &&
                    Math.max(x, y) < n &&
                    A[x][y] > 0) {

                    /*
                     Suppose current path safeness = 5

                     Next cell safeness = 3

                     New path safeness = min(5,3)=3

                     Because a path is only as safe
                     as its weakest cell.
                    */

                    pq.add(new int[] {
                        Math.min(sf, A[x][y]),
                        x,
                        y
                    });

                    /*
                     Mark as visited.

                     Example

                     4 becomes -4

                     Next time A[x][y] > 0 fails,
                     so this cell won't be added again.
                    */
                    A[x][y] *= -1;
                }
            }
        }

        // Problem guarantees a valid path,
        // so execution normally never reaches here.
        return A[n - 1][n - 1] - 1;
    }
}
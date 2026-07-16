var canPartitionGrid = function(grid) {
    let m = grid.length;
    let n = grid[0].length;

    let totalSum = 0;

    // Calculate total sum
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            totalSum += grid[i][j];
        }
    }

    // If total sum is odd → impossible
    if (totalSum % 2 !== 0) return false;

    // 🔹 Check Horizontal Cuts
    let topSum = 0;
    for (let i = 0; i < m - 1; i++) {
        let rowSum = 0;
        for (let j = 0; j < n; j++) {
            rowSum += grid[i][j];
        }
        topSum += rowSum;

        if (topSum === totalSum - topSum) return true;
    }

    // 🔹 Check Vertical Cuts
    let colSums = new Array(n).fill(0);

    // Precompute column sums
    for (let j = 0; j < n; j++) {
        for (let i = 0; i < m; i++) {
            colSums[j] += grid[i][j];
        }
    }

    let leftSum = 0;
    for (let j = 0; j < n - 1; j++) {
        leftSum += colSums[j];

        if (leftSum === totalSum - leftSum) return true;
    }

    return false;
};
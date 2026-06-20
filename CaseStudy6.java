public class NetflixCDNCacheOptimizer {

    public static void computeOptimalStorage(String[] titles, int[] sizes, int[] popularity, int capacity) {
        int n = titles.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Building the tabular matrix
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (sizes[i - 1] <= w) {
                    dp[i][w] = Math.max(popularity[i - 1] + dp[i - 1][w - sizes[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("\n🔥 [Netflix Dynamic Optimization Strategy Computed]");
        System.out.println("Total Caching Value Score Maximized To: " + dp[n][capacity] + " Points");
        System.out.print("Selected Titles for Local Server Deployment: ");

        int w = capacity;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.print("[" + titles[i - 1] + " (Size: " + sizes[i - 1] + "TB)] ");
                w -= sizes[i - 1];
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Initializing Local Edge CDN Network Architecture Initialization...");
        
        String[] titles = {"Stranger Things S5", "Wednesday S2", "The Witcher", "Squid Game S2"};
        int[] sizes = {4, 3, 2, 5};       // Resource Weights in Terabytes (TB)
        int[] popularity = {50, 40, 20, 55}; // Structural Analytical Value Metrics
        int serverCapacity = 7;           // Hardware storage threshold limit

        System.out.println("Total Edge Storage Constraint Cap: " + serverCapacity + " TB");
        computeOptimalStorage(titles, sizes, popularity, serverCapacity);
    }
}
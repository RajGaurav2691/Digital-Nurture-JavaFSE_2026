public class FinancialForecasting {
    // Recursive method to calculate future value
    public static double calculateFutureValue(double currentValue, double growthRate, int periods) {

        if (periods < 0) {
            throw new IllegalArgumentException("Periods cannot be negative.");
        }
        // Base case
        if (periods == 0) {
            return currentValue;
        }
        // Recursive case
        return calculateFutureValue(currentValue * (1 + growthRate), growthRate,
                periods - 1
        );
    }
    public static void main(String[] args) {

        double presentValue = 50000.0;
        double growthRate = 0.07;   // 7%
        int years = 15;

        double futureValue = calculateFutureValue(presentValue, growthRate, years);
        System.out.println("===== Financial Forecasting =====");
        System.out.println("Present Value : $" + presentValue);
        System.out.println("Growth Rate   : " + (growthRate * 100) + "%");
        System.out.println("Years         : " + years);
        System.out.printf("Future Value  : $%.2f%n", futureValue);
    }
}
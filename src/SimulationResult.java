import java.util.ArrayList;

public class SimulationResult {

    // averages per person per year over the entire duration of the simulation
    private double averageCostOverLifetime;
    private double averageCo2OverLifetime;
    private double averageWasteOverLifetime;

    // averages per person per year over all decades tha
    private final ArrayList<Double> averageCostPerDecade;
    private final ArrayList<Double> averageHappinessPerDecade;

    private double sustainabilityScore;

    public SimulationResult(ArrayList<CostContainer> costsPerYear, ArrayList<Double> happinessPerYear) {
        int simulationDuration = costsPerYear.size();

        averageCostOverLifetime = 0;
        averageCo2OverLifetime = 0;
        averageWasteOverLifetime = 0;

        averageCostPerDecade = new ArrayList<Double>();
        averageHappinessPerDecade = new ArrayList<Double>();

        for (int i = 0; i < simulationDuration; i++) {
            averageCostOverLifetime += costsPerYear.get(i).getCost() / simulationDuration;
            averageCo2OverLifetime += costsPerYear.get(i).getCo2() / simulationDuration;
            averageWasteOverLifetime += costsPerYear.get(i).getWaste() / simulationDuration;


            int scaleFactor;
            if (i < simulationDuration - simulationDuration % 10) {
                // data for the entire decade exists
                scaleFactor = 10;
            } else {
                // scale by the amount of years that are still simulated for in the last decade
                scaleFactor = simulationDuration % 10;
            }

            if (i % 10 == 0) {
                // add value for each beginning decade
                averageCostPerDecade.add(costsPerYear.get(i).getCost() / scaleFactor);
                averageHappinessPerDecade.add(happinessPerYear.get(i) / scaleFactor);
            } else {
                // i/10 is the index of the last element in the list
                double newValueCosts = averageCostPerDecade.get(i / 10) + costsPerYear.get(i).getCost() / scaleFactor;
                averageCostPerDecade.set(i / 10, newValueCosts);
                double newValueHappiness = averageHappinessPerDecade.get(i / 10) + happinessPerYear.get(i) / scaleFactor;
                averageHappinessPerDecade.set(i / 10, newValueHappiness);
            }
        }

        sustainabilityScore = extractSustainabilityScore();
    }

   private  double extractSustainabilityScore() {
        double scaleScore = 1.0f;
        double weightCost = 1.0f / 3;
        double weightCo2 = 1.0f / 3;
        double weightWaste = 1.0f / 3;
        double weightHappiness = 1.0f;

        double happinessByCost = 0;
        for (int i = 0; i < averageHappinessPerDecade.size(); i++) {
            happinessByCost += averageHappinessPerDecade.get(i) / averageCostPerDecade.get(i);
        }

        if(happinessByCost < 0){
            happinessByCost = 0;
        }
        double scoreCosts = weightCost * averageCostOverLifetime;
        double scoreCo2 = weightCo2 * averageCo2OverLifetime;
        double scoreWaste = weightWaste * averageWasteOverLifetime;
        double scoreHappiness = 1 + Math.sqrt(weightHappiness * happinessByCost);

       return (double) (scaleScore * Math.sqrt(1.0f/(scoreCosts + scoreCo2 + scoreWaste) * scoreHappiness));
    }


    public double getAverageCostOverLifetime() {
        return averageCostOverLifetime;
    }

    public double getAverageCo2OverLifetime() {
        return averageCo2OverLifetime;
    }

    public double getAverageWasteOverLifetime() {
        return averageWasteOverLifetime;
    }

    public ArrayList<Double> getAverageCostPerDecade() {
        return averageCostPerDecade;
    }

    public ArrayList<Double> getAverageHappinessPerDecade() {
        return averageHappinessPerDecade;
    }

    public double getSustainabilityScore() {
        return sustainabilityScore;
    }
}
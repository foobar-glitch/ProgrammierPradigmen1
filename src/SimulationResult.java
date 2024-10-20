import java.util.ArrayList;

public class SimulationResult {

    // averages per person per year over the entire duration of the simulation
    private float averageCostOverLifetime;
    private float averageCo2OverLifetime;
    private float averageWasteOverLifetime;

    // averages per person per year over all decades tha
    private final ArrayList<Float> averageCostPerDecade;
    private final ArrayList<Float> averageHappinessPerDecade;

    public SimulationResult(ArrayList<CostContainer> costsPerYear, ArrayList<Float> happinessPerYear) {
        int simulationDuration = costsPerYear.size();

        averageCostOverLifetime = 0;
        averageCo2OverLifetime = 0;
        averageWasteOverLifetime = 0;

        averageCostPerDecade = new ArrayList<Float>();
        averageHappinessPerDecade = new ArrayList<Float>();

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
                float newValueCosts = averageCostPerDecade.get(i / 10) + costsPerYear.get(i).getCost() / scaleFactor;
                averageCostPerDecade.set(i / 10, newValueCosts);
                float newValueHappiness = averageHappinessPerDecade.get(i / 10) + happinessPerYear.get(i) / scaleFactor;
                averageHappinessPerDecade.set(i / 10, newValueHappiness);
            }
        }
    }

    double extractSustainabilityScore() {
        float scaleScore = 1.0f;
        float weightCost = 1.0f / 3;
        float weightCo2 = 1.0f / 3;
        float weightWaste = 1.0f / 3;
        float weightHappiness = 1.0f;

        float happinessByCost = 0;
        for (int i = 0; i < averageHappinessPerDecade.size(); i++) {
            happinessByCost += averageHappinessPerDecade.get(i) / averageCostPerDecade.get(i);
        }

        float scoreCosts = weightCost * averageCostOverLifetime;
        float scoreCo2 = weightCo2 * averageCo2OverLifetime;
        float scoreWaste = weightWaste * averageWasteOverLifetime;
        double scoreHappiness = 1 + Math.sqrt(weightHappiness * happinessByCost);

       return scaleScore * Math.sqrt(1.0f/(scoreCosts + scoreCo2 + scoreWaste) * scoreHappiness);
    }


    public float getAverageCostOverLifetime() {
        return averageCostOverLifetime;
    }

    public float getAverageCo2OverLifetime() {
        return averageCo2OverLifetime;
    }

    public float getAverageWasteOverLifetime() {
        return averageWasteOverLifetime;
    }

    public ArrayList<Float> getAverageCostPerDecade() {
        return averageCostPerDecade;
    }

    public ArrayList<Float> getAverageHappinessPerDecade() {
        return averageHappinessPerDecade;
    }
}
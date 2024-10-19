import java.util.ArrayList;

// used to be called "MetrikClass"
public class SimulationResults {

    // averages per person per year over the entire duration of the simulation
    private float averageCostOverLifetime;
    private float averageCo2OverLifetime;
    private float averageWasteOverLifetime;

    // averages per person per year over all decades tha
    private final ArrayList<Float> averageCostPerDecade;
    private final ArrayList<Float> averageHappinessPerDecade;

    public SimulationResults(ArrayList<CostContainer> costsPerYear, ArrayList<Float> happinessPerYear) {
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
            if (i < (simulationDuration / 10) * 10) {
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
                float newValueCosts = averageCostPerDecade.get(i / 10) + costsPerYear.get(i).getCost();
                averageCostPerDecade.set(i / 10, newValueCosts /= scaleFactor);
                float newValueHappiness = averageHappinessPerDecade.get(i / 10) + happinessPerYear.get(i);
                averageHappinessPerDecade.set(i / 10, newValueHappiness /= scaleFactor);
            }
        }
    }

    public float getAverageCostOverLifetime() {return averageCostOverLifetime;}

    public float getAverageCo2OverLifetime() {return averageCo2OverLifetime;}

    public float getAverageWasteOverLifetime() {return averageWasteOverLifetime;}

    public ArrayList<Float> getAverageCostPerDecade() {return averageCostPerDecade;}

    public ArrayList<Float> getAverageHappinessPerDecade() {return averageHappinessPerDecade;}
}
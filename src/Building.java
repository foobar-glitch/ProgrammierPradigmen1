public class Building {
    private int lifetime;
    private int age;
    /* The materials of the shell of the building */
    private MaterialBag shellConstruct;
    /* The materials of the building part that has to be renovated*/
    private Apartment[] apartments;
    /* The total costs of the initial building */
    private int renovationIndex;
    private double recycleRate;
    private int numberOfResidents = 0;

    /**
     * @param buildingSpecs the specifications of the building
     * */
    public Building(BuildingSpecs buildingSpecs, ApartmentSpecs apartmentSpecs)
    {
        this.age = buildingSpecs.getAge();
        this.lifetime = buildingSpecs.getLifetime();
        this.shellConstruct = buildingSpecs.getShellConstruct();
        this.recycleRate = buildingSpecs.getRecycleRate();

        this.apartments = new Apartment[apartmentSpecs.getNumberOfApartments()];
        for(int i = 0; i < apartmentSpecs.getNumberOfApartments(); i++){
            apartments[i] = new Apartment(
                    apartmentSpecs.getMaterial(),
                    apartmentSpecs.getLifetimeApartment(),
                    apartmentSpecs.getResidentNumber(),
                    apartmentSpecs.getHappinessUpperBound());
        }

        for(Apartment apartment: apartments){
            this.numberOfResidents += apartment.getNumberOfResidents();
        }
    }


    /**
     * Depending on the household amount of material I have to renovate
     *
     * @param percentageRenovated percentage of Apartments which should be renovated
     * */
    public CostContainer renovate(double percentageRenovated) {
        CostContainer renovatingCost = new CostContainer(0, 0, 0);
        int i = this.renovationIndex;
        int amountApartments = (int) (this.apartments.length * percentageRenovated);
        CostContainer cost = new CostContainer(0f, 0f, 0f);
        for(; i < (this.renovationIndex+amountApartments) % this.apartments.length; i++){
            cost = apartments[i].renovate();
            renovatingCost = renovatingCost.addCostContainer(cost);
        }
        this.renovationIndex = i%this.apartments.length;
        return renovatingCost.multiplyContainer((double) 1/numberOfResidents);
    }

    /**
     * When demolishing you can recycle x percent of material and subtract that value
     * from the total costs
     * */
    public CostContainer demolishing(){
        CostContainer leftoverMaterialCost = shellConstruct.getTotalCost();
        CostContainer recycledProfit = new CostContainer(
                leftoverMaterialCost.getCost() * recycleRate,
                leftoverMaterialCost.getCo2() * recycleRate,
                leftoverMaterialCost.getWaste() * recycleRate
        );
        leftoverMaterialCost = leftoverMaterialCost.subtractCostContainer(recycledProfit);
        for(Apartment apartment: this.apartments){
            leftoverMaterialCost = leftoverMaterialCost.addCostContainer(apartment.demolish(recycleRate));
        }

        return leftoverMaterialCost.multiplyContainer((double) 1/numberOfResidents);
    }

    /**
     * Currently renovating all apartments with the same amount of renovating material.
     * Otherwise: Specify all apartments (by index for example) and how much they'll lose for
     * the aging.
     * return: Average cost over residents
     * */
    public CostContainer age(){
        // Renovating all apartments the same amounts
        CostContainer agingCosts = new CostContainer(0f, 0f, 0f);
        if(age==0){
            agingCosts = agingCosts.addCostContainer(shellConstruct.getTotalCost());
        }
        age++;

        for (Apartment apartment: apartments) {
            if(!apartment.update()){
                agingCosts = agingCosts.addCostContainer(apartment.renovate());

            }
            agingCosts = agingCosts.addCostContainer(apartment.currentCost());
        }
        return agingCosts.multiplyContainer((double) 1/numberOfResidents);
    }

    /**
     * Check if the age is lower than the lifetime
     * */
    public boolean checkAge(){
        return age <= lifetime ;
    }

    /**
     * @return Average Satisfaction of the Residents
     */
    public double satisfaction(){

        double s = 0.0f;
        int people = 0;
        for(int i=0; i<apartments.length; i++){
            people+=apartments[i].getNumberOfResidents();
            s+=apartments[i].getSatisfaction()*apartments[i].getNumberOfResidents();
        }

        return s/people;
    }

}

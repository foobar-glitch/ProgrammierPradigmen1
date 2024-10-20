public class Building {
    private int lifetime;
    private int age;
    /* The materials of the shell of the building */
    private MaterialBag shellConstruct;
    /* The materials of the building part that has to be renovated*/
    private Apartment[] apartments;
    /* The total costs of the initial building */
    private int renovationIndex;
    private float recycleRate;

    /**
     *
     * @param lifetime the general lifetime of the building
     * @param shellConstruct the shell construction of the building
     * @param apartments array of all apartments
     * @param recycleRate Recycle rate [0,1] when building is demolished
     */
    public Building(
            int lifetime,
            MaterialBag shellConstruct,
            Apartment[] apartments,
            float recycleRate
    ) {
        this.age = 0;
        this.lifetime = lifetime;
        this.shellConstruct = shellConstruct;
        this.apartments = apartments;
        this.recycleRate=recycleRate;
    }


    /**
     * Depending on the household amount of material I have to renovate
     *
     * @param percentageRenovated percentage of Apartments which should be renovated
     * */
    public CostContainer renovate(float percentageRenovated) {
        CostContainer renovatingCost = new CostContainer(0, 0, 0);
        int i = this.renovationIndex;
        int amountApartments = (int) (this.apartments.length * percentageRenovated);
        CostContainer cost = new CostContainer(0f, 0f, 0f);
        for(; i < (this.renovationIndex+amountApartments) % this.apartments.length; i++){
            cost = apartments[i].renovate();
            renovatingCost.addCostContainer(cost);
        }
        this.renovationIndex = i%this.apartments.length;
        return renovatingCost;
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
        leftoverMaterialCost.subtractCostContainer(recycledProfit);
        for(Apartment apartment: this.apartments){
            leftoverMaterialCost.addCostContainer(apartment.demolish(recycleRate));
        }

        return leftoverMaterialCost;
    }

    /**
     * Currently renovating all apartments with the same amount of renovating material.
     * Otherwise: Specify all apartments (by index for example) and how much they'll lose for
     * the aging.
     * */
    public CostContainer age(){
        // Renovating all apartments the same amounts
        CostContainer agingCosts = new CostContainer(0f, 0f, 0f);
        if(age==0) agingCosts.addCostContainer(shellConstruct.getTotalCost());
        age++;
        for (Apartment apartment: this.apartments) {
            if(!apartment.update()){
                agingCosts.addCostContainer(apartment.renovate());
            }
            agingCosts.addCostContainer(apartment.currentCost());
            System.out.println("UP: " + agingCosts.getCo2());
        }
        return agingCosts;
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
    public float satisfaction(){

        float s = 0.0f;
        int people = 0;
        for(int i=0; i<apartments.length; i++){
            people+=apartments[i].getNumberOfResidents();
            s+=apartments[i].getSatisfaction()*apartments[i].getNumberOfResidents();
        }

        return s/people;
    }

}

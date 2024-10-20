public class Building {
    private int lifetime;
    private int age;
    /* The materials of the shell of the building */
    private MaterialBag shellConstruct;
    /* The materials of the building part that has to be renovated*/
    private Apartment[] apartments;
    /* The total costs of the initial building */private int index;

    /**
     *
     * @param lifetime the general lifetime of the building
     * @param shellConstruct the shell construction of the building
     * @param apartments array of all apartments
     */
    public Building(
            int lifetime,
            MaterialBag shellConstruct,
            Apartment[] apartments
    ) {
        this.age = 0;
        this.lifetime = lifetime;
        this.shellConstruct = shellConstruct;
        this.apartments = apartments;

    }


    /**
     * Depending on the household amount of material I have to renovate
     *
     * @param amountApartments The number of apartments
     * */
    public CostContainer renovate(int amountApartments) {
        CostContainer renovatingCost = new CostContainer(0, 0, 0);
        int i = this.index;
        CostContainer cost;
        for(; i <= this.index+amountApartments % this.apartments.length; i++){
            cost = apartments[i].renovate();
            renovatingCost.addCostContainer(cost);
        }
        this.index = i;
        return renovatingCost;
    }

    /**
     * When demolishing you can recycle x percent of material and subtract that value
     * from the total costs
     * @param recycleRate Is the rate at which we can recycle the leftover material [0,1]
     *
     * */
    public CostContainer demolishing(float recycleRate){
        CostContainer leftoverMaterial = shellConstruct.getTotalCost();
        CostContainer recycledProfit = new CostContainer(
                leftoverMaterial.getCost() * recycleRate,
                leftoverMaterial.getCo2() * recycleRate,
                leftoverMaterial.getWaste() * recycleRate
        );
        leftoverMaterial.subtractCostContainer(recycledProfit);
        return leftoverMaterial;
    }

    /**
     * Currently renovating all apartments with the same amount of renovating material.
     * Otherwise: Specify all apartments (by index for example) and how much they'll lose for
     * the aging.
     * */
    public void age(){
        // Renovating all apartments the same amounts
        age++;
        for (Apartment apartment: this.apartments) {
            if(!apartment.update()){
                apartment.renovate();
            }
        }
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

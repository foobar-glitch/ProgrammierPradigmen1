public class Apartment {

    /* Material used to build and renovate the apartment */
    private MaterialBag constructionMaterial;

    /* Maximum Lifetime of the apartment, age and number of residents */
    private int lifetime, age, residents;

    /* Definition of the Intervall [factor, 0] in which Satisfaction
    is measured in getSatisfaction */
    private static final int  satisfactionFactor = 1;


    public Apartment(MaterialBag constructionMaterial, int lifetime, int residents){
        this.age = 0;
        this.lifetime = lifetime;
        this.residents = residents;
        this.constructionMaterial = constructionMaterial;
    }

    /**
     * Increments the Age (+1 year)
     * @return FALSE if the Apartment exceeded its lifetime
     * */
    public boolean update(){
        age+=1;
        return age<=lifetime;
    }

    /**
     * Renovates the Apartment completely
     * @return Cost of the renovation
     */
    public CostContainer renovate(){
        age=0;
        return constructionMaterial.getTotalCost();
    }

    /**
     * Calculates Residents Satisfaction depending on
     * the state of their apartment
     * @return Factor of residents' satisfaction
     */
    public float getSatisfaction(){
        double tmp = ((double) satisfactionFactor / lifetime)*(-Math.pow(age, 2)) + satisfactionFactor;
        return (float) tmp;
    }

    public int getNumberOfResidents(){return residents;}


}

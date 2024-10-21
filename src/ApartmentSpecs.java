public class ApartmentSpecs {
    private MaterialBag material;
    private int residentNumber;
    private int numberOfApartments;
    private int lifetimeApartment;
    private double happinessUpperBound;

    public ApartmentSpecs(MaterialBag material, int residentNumber, int numberOfApartments, int lifetimeApartment, double happinessUpperBound) {
        this.material = material;
        this.residentNumber = residentNumber;
        this.numberOfApartments = numberOfApartments;
        this.lifetimeApartment = lifetimeApartment;
        this.happinessUpperBound = happinessUpperBound;
    }

    public MaterialBag getMaterial() {
        return material;
    }

    public int getResidentNumber() {
        return residentNumber;
    }

    public int getNumberOfApartments() {
        return numberOfApartments;
    }

    public int getLifetimeApartment() {
        return lifetimeApartment;
    }

    public double getHappinessUpperBound() {
        return happinessUpperBound;
    }
}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public Material parseMaterial(){
        return null;
    }


    public static void main(String[] args) {
        Material wood = new Material(350.0f, 0.015f, 0.01f);
        Material concrete = new Material(100.0f, 0.15f, 0.02f);
        Material steel = new Material(1200.0f, 1.9f, 0.005f);

        /*
        * Assuming that we have 10 apartments with each 50 sqm.
        * */
        MaterialBag shellConstructionMaterial = new MaterialBag();
        shellConstructionMaterial.setMaterial(concrete, 216.0f);
        shellConstructionMaterial.setMaterial(steel, 30.0f);
        shellConstructionMaterial.setMaterial(wood, 3.75f);

        MaterialBag apartmentMaterial = new MaterialBag();
        apartmentMaterial.setMaterial(concrete, 14.4f);
        apartmentMaterial.setMaterial(steel, 2.0f);
        apartmentMaterial.setMaterial(wood, 0.875f);

        /*
        * Each apartment has 1 resident. Every apartment
        * is the same size and need the same amount of material
         */
        int residentNumber = 1, numberOfApartments = 10, lifetimeApartment = 25, lifetimeBuilding = 50;
        Apartment[] allApartments = new Apartment[numberOfApartments];
        for(int i = 0; i < numberOfApartments; i++){
            allApartments[i] = new Apartment(apartmentMaterial, lifetimeApartment, residentNumber);
        }
        
    }
}
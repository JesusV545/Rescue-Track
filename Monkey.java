
public class Monkey extends RescueAnimal{
	//defining the fields
	private double tailLength;
	private double height;
	private double bodyLength;
	private String species;
	
	//constructor
	public Monkey(String name, String gender, String age, String weight, String acquisitionDate,
            String acquisitionCountry, String trainingStatus, boolean reserved,
            String inServiceCountry, double tailLength, double height, double bodyLength, String species) {
        setName(name);
        setGender(gender);
        setAge(age);
        setWeight(weight);
        setAcquisitionDate(acquisitionDate);
        setAcquisitionLocation(acquisitionCountry);
        setTrainingStatus(trainingStatus);
        setReserved(reserved);
        setInServiceCountry(inServiceCountry);
        this.tailLength = tailLength;
        this.height = height;
        this.bodyLength = bodyLength;
        this.species = species;
	}
	
    // Getters and Setters
    public double getTailLength() {
        return tailLength;
    }

    public void setTailLength(double tailLength) {
        this.tailLength = tailLength;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(double bodyLength) {
        this.bodyLength = bodyLength;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}

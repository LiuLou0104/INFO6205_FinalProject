package Business.Pathogen;

public class Hospital {
    private String name = "";
    private double cureSpeed = 0;

    public Hospital(){}

    public Hospital(String name, double cureSpeed){
        this.name = name;
        this.cureSpeed = cureSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCureSpeed() {
        return cureSpeed;
    }

    public void setCureSpeed(double cureSpeed) {
        this.cureSpeed = cureSpeed;
    }
}

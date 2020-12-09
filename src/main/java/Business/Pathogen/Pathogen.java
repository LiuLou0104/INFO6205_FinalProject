package Business.Pathogen;

public class Pathogen {
    //toString
    private String name = "SARS-CoV-2"; // name of Pathogen
    private double K_FACTOR = 0.5; //k-factor
    private double R_FACTOR = 1.5; //r-factor 1.4-3.8

    public Pathogen(){}

    public Pathogen(String name, double k_FACTOR, double R_FACTOR){
        this.name = name;
        this.K_FACTOR = k_FACTOR;
        this.R_FACTOR = R_FACTOR;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getK_FACTOR() {
        return K_FACTOR;
    }

    public void setK_FACTOR(double k_FACTOR) {
        K_FACTOR = k_FACTOR;
    }

    public double getR_FACTOR() {
        return R_FACTOR;
    }

    public void setR_FACTOR(double r_FACTOR) {
        R_FACTOR = r_FACTOR;
    }
}

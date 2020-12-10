package Business.DrawArea;

import Business.Pathogen.Pathogen;
import Business.Pathogen.Hospital;

import java.util.Random;

public class AreaUnit {
    public static int BLOCK_LENGTH = 1;
    public static int BLOCK_WIDTH = 1;
    private double populationDensity = 0;
    private double infectSpeed = 0;
    private double infectNum = 0; // number of infect people
    private double headcount = 0; // head count in this area
    private double popFlowSpeed = 0;// percentage of flow speed (aka: number of people move out / total head count) [0, 1)
    private boolean isQuarantine = false;
    private boolean isMask = false;
    private boolean isTest = false;
    private Pathogen pathogen = new Pathogen();
    private Hospital hospital; // not used

    public AreaUnit() {
        super();
    }

    public AreaUnit(Pathogen pathogen){
        this.pathogen = pathogen; // set pathogen
        Random random = new Random();
        headcount = 5000 + random.nextDouble() * 5000;
//        headcount = 5000; // set populationDensity
        popFlowSpeed = 0.1 + 0.1 * random.nextDouble();
        //popFlowSpeed = 1000; // set popFlowSpeed
//        infectNum = random.nextDouble() * 30;
        infectNum = 0; // set infectNum
        int r1 = random.nextInt();
        if(r1 % 3 == 0) isQuarantine = true;
        int r2 = random.nextInt();
        if(r2 % 2 == 0) isMask = true;
        int r3 = random.nextInt();
        if(r3 % 2 == 0) isTest = true;
        calcInfectSpeed(); // set infectSpeed
//        System.out.println(this.toString());
    }

    public AreaUnit(Pathogen pathogen, double populationDensity, boolean isQuarantine, boolean isMask, boolean isTest) {
        this.pathogen = pathogen;
        this.populationDensity = populationDensity;
        this.isQuarantine = isQuarantine;
        this.isMask = isMask;
        this.isTest = isTest;
    }

    @Override
    public String toString() {
        return "AreaUnit{" +
                "populationDensity=" + populationDensity +
                ", infectSpeed=" + infectSpeed +
                ", infectNum=" + infectNum +
                ", headcount=" + headcount +
                ", popFlowSpeed=" + popFlowSpeed +
                ", isQuarantine=" + isQuarantine +
                ", isMask=" + isMask +
                ", isTest=" + isTest +
                ", pathogen=" + pathogen +
                ", hospital=" + hospital +
                '}';
    }

    public void calcInfectSpeed(){
        calcPopulationDensity();
        if(this.isMask && !this.isTest){
            this.infectSpeed = 0.06 * infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        } else if(!this.isMask && this.isTest){
            this.infectSpeed = 0.04 * infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        } else if(this.isMask && this.isTest){
            this.infectSpeed = 0.06 * 0.04 *  infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        } else {
            this.infectSpeed = infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        }
        this.infectSpeed *= 0.01;
        //this.infectSpeed = 1.0;
    }
  
    private void calcPopulationDensity(){
        this.populationDensity = this.headcount / (BLOCK_WIDTH * BLOCK_LENGTH);
    }

    private void calcHeadCount() {
        this.headcount = this.populationDensity * (BLOCK_WIDTH * BLOCK_LENGTH);
    }

    public double getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(double populationDensity) {
        this.populationDensity = populationDensity;
        calcHeadCount();
        if(this.isMask && !this.isTest){
            this.infectSpeed = 0.6 * infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        } else if(!this.isMask && this.isTest){
            this.infectSpeed = 0.4 * infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        } else if(this.isMask && this.isTest){
            this.infectSpeed = 0.6 * 0.4 *  infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        } else {
            this.infectSpeed = infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        }
        this.infectSpeed *= 0.01;
        //this.infectSpeed = 1.0;
    }

    public double getInfectSpeed() {
        return infectSpeed;
    }

    public void setInfectSpeed(double infectSpeed) {
        this.infectSpeed = infectSpeed;
    }

    public double getInfectNum() {
        return infectNum;
    }

    public void setInfectNum(double infectNum) {
        this.infectNum = infectNum;
        calcInfectSpeed();
    }

    public boolean isQuarantine() {
        return isQuarantine;
    }

    public void setQuarantine(boolean quarantine) {
        isQuarantine = quarantine;
    }

    public boolean isMask() {
        return isMask;
    }

    public void setMask(boolean mask) {
        isMask = mask;
    }

    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean test) {
        isTest = test;
    }

    public Pathogen getPathogen() {
        return pathogen;
    }

    public void setPathogen(Pathogen pathogen) {
        this.pathogen = pathogen;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public double getHeadcount() {
        return headcount;
    }

    public void setHeadcount(double headcount) {
        this.headcount = headcount;
    }

    public double getPopFlowSpeed() {
        return popFlowSpeed;
    }

    public void setPopFlowSpeed(double popFlowSpeed) {
        this.popFlowSpeed = popFlowSpeed;
    }
}

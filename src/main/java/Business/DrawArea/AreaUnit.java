package Business.DrawArea;

import Business.Pathogen.Pathogen;
import Business.Pathogen.Hospital;

import java.util.Random;

public class AreaUnit {
    public static int BLOCK_LENGTH = 1;
    public static int BLOCK_WIDTH = 1;
    private double populationDensity = 0; //人口密度
    private double infectSpeed = 0; //传染速度 = 人口密度 * ???  infectNum * 2^t
    private double infectNum = 0; //该地区感染人数
    private double headcount = 0; //该地区的总人数
    private double popFlowSpeed = 0;//该地区的向外人口流动速度（单位时间的人口流出量）
    private boolean isQuarantine = false; //是否隔离（旅行限制）
    private boolean isMask = false; //是否使用口罩
    private boolean isTest = false; //是否检测及溯源
    private Pathogen pathogen; //该地区的病毒种类（通过set方法传入）
    private Hospital hospital; //该地区的医院（通过set方法传入）

    public AreaUnit(Pathogen pathogen){
        this.pathogen = pathogen; // set pathogen
        Random random = new Random();
        headcount = random.nextDouble()*10000;
        //headcount = 5000; // set populationDensity
        popFlowSpeed = random.nextDouble()*5000;
        //popFlowSpeed = 1000; // set popFlowSpeed
        infectNum = random.nextDouble()*30;
        //infectNum = 10; // set infectNum
        int r1 = random.nextInt();
        if(r1 % 2 == 0) isQuarantine = true;
        int r2 = random.nextInt();
        if(r2 % 2 == 0) isMask = true;
        int r3 = random.nextInt();
        if(r3 % 2 == 0) isTest = true;
        calcInfectSpeed(); // set infectSpeed
        System.out.println(this.toString());
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

    public AreaUnit(Pathogen pathogen, double populationDensity, boolean isQuarantine, boolean isMask, boolean isTest) {
        this.pathogen = pathogen;
        this.populationDensity = populationDensity;
        this.isQuarantine = isQuarantine;
        this.isMask = isMask;
        this.isTest = isTest;
    }

    public void calcInfectSpeed(){
        calcPopulationDensity();
        //TODO 需要调整数值达到理想效果
        if(this.isMask && !this.isTest){
            this.infectSpeed = 0.6 * infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        } else if(!this.isMask && this.isTest){
            this.infectSpeed = 0.4 * infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        } else if(this.isMask && this.isTest){
            this.infectSpeed = 0.6 * 0.4 *  infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        } else {
            this.infectSpeed = infectNum * (0.005 * populationDensity * pathogen.getR_FACTOR() * 0.3 / pathogen.getK_FACTOR() * 9);
        }
        //this.infectSpeed = 1.0;
    }
  
    private void calcPopulationDensity(){
        this.populationDensity = this.headcount / (BLOCK_WIDTH * BLOCK_LENGTH);
    }

    public double getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(double populationDensity) {
        this.populationDensity = populationDensity;
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

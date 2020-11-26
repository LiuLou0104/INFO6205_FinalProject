package edu.neu.coe.info6205;

public class Block {
    private int BLOCK_LENGTH = 1;
    private int BLOCK_WIDTH = 1;
    private double populationDensity = 0; //人口密度
    private double infectSpeed = 0; //传染速度 = 人口密度 * ???
    private double infectNum = 0; //该地区感染人数
    private boolean isQuarantine = false; //是否隔离（旅行限制）
    private boolean isMask = false; //是否使用口罩
    private Pathogen pathogen; //该地区的病毒种类（通过set方法传入）
    private Hospital hospital; //该地区的医院（通过set方法传入）

    public Block(){}

    /*
    * District由Block组成，初始化District
    * */
    public Block[][] initDistrict(int districtLength, int districtWidth){
        Block[][] district = new Block[districtWidth/BLOCK_WIDTH][districtLength/BLOCK_LENGTH];
        for(int i=0;i<districtWidth/BLOCK_WIDTH;i++){
            for(int j=0;j<districtLength/BLOCK_LENGTH;j++){
                district[i][j] = new Block();
            }
        }
        return district;
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
}

package Business.DrawArea;

import Business.Pathogen.Hospital;
import Business.Pathogen.Pathogen;

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
    private Pathogen pathogen; //该地区的病毒种类（通过set方法传入）
    private Hospital hospital; //该地区的医院（通过set方法传入）

    public AreaUnit(){
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

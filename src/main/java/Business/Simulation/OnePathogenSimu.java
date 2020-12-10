package Business.Simulation;

import Business.DrawArea.Area;
import Business.DrawArea.AreaUnit;
import Business.Pathogen.Pathogen;
import Business.Plot.Plot;
import Business.Report.Chart;
import Business.Report.ChartDirectory;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class OnePathogenSimu extends Observable implements Runnable {
    Thread thread = null;
    public static final int AREA_LENGTH = 26; //The length of the area
    public static final int AREA_WIDTH = 20; //The width of the area
    AreaUnit[][] areaUnitArray;
    public int time = 1;
//    private boolean done = false;
    private boolean paused = false;
    private boolean numShowFlag = true;
    private boolean infectFlag = true;
//    OnePathogenSimu onePathogenSimu = null;
    private List<Double> infectNumList;
    private List<Integer> infectUnitsList;
    private Area area;
    private ChartDirectory chartDirectory;

    private long simuStart;

    /**
     * Constructor
     */
    public OnePathogenSimu(){
       area = new Area(AREA_LENGTH,AREA_WIDTH);
       this.areaUnitArray = area.getArea();
       this.simuStart = new Date().getTime();
       this.infectUnitsList = new ArrayList<>();
       this.infectNumList = new ArrayList<>();
       this.chartDirectory = new ChartDirectory();
    }

    public OnePathogenSimu(Pathogen pathogen) {
        area = new Area(AREA_LENGTH, AREA_WIDTH, pathogen);
        this.areaUnitArray = area.getArea();
        this.simuStart = new Date().getTime();
        this.infectNumList = new ArrayList<>();
        this.infectUnitsList = new ArrayList<>();
        this.chartDirectory = new ChartDirectory();
    }

    public OnePathogenSimu(Pathogen pathogen, double popuDensity, boolean isWearingMask, boolean isQuarantine, boolean isTest) {
        area = new Area(AREA_LENGTH, AREA_WIDTH, pathogen);
        this.areaUnitArray = area.getArea();
        this.simuStart = new Date().getTime();
        this.infectNumList = new ArrayList<>();
        this.infectUnitsList = new ArrayList<>();
        this.chartDirectory = new ChartDirectory();
    }

    /**
     * Start the simulation
     * @param componentsToDisEnabled
     */
    public void startSim(Component[] componentsToDisEnabled){
//        System.out.println("Starting the simulation");
//        onePathogenSimu = new OnePathogenSimu();
        OnePathogenSimu pathSimu = this;

        if(thread != null) return;
        thread = new Thread(this);
//        done = false;
        thread.start();

        // disable the StartSimu button
        for (Component c : componentsToDisEnabled) {
            c.setEnabled(false);
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                done = true;
                clearChanged();
                String title = pathSimu.getAreaUnitArray()[0][0].getPathogen().getName();

                Plot.drawLineChartInfectNum(pathSimu, title + " Infect Num");
                Plot.drawLineChartInfectUnits(pathSimu, title + " Epidemic Area");
                Plot.drawScatterPlot(pathSimu, title + " Epidemic Area");

                // enable the StartSimu button
                for (Component c : componentsToDisEnabled) {
                    c.setEnabled(true);
                }
                System.out.println("Simulation[" + simuStart + "] ended");
            }
        },20000);
    }

    // End Simulation
//    public void stopSim(){
//        System.out.println("Stop the simulation");
//        if (thread == null) return;
//        done = true;
//    }

    @Override
    public void run() {
        System.out.println("Simulation[" + this.simuStart + "] started");
        setChanged();
        runSimLoop();
        thread = null;
    }

    private void runSimLoop(){
        while(hasChanged()){
            updateSim();
            sleep(500);
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the simulation status to GUI Canvas
     */
    private void updateSim(){
        calcSpread();
        notifyObservers(this);
        setChanged();
    }

    // Calculate the number of units contain infect people
    public int calcInfectedUnits(){
        int counter = 0;
        for (int i = 0; i < AREA_WIDTH / AreaUnit.BLOCK_WIDTH; i++) {
            for (int j = 0; j < AREA_LENGTH / AreaUnit.BLOCK_LENGTH; j++) {
                if (areaUnitArray[i][j].getInfectNum() > 0)
                    counter++;
            }
        }
        return counter;
    }

    // Count the total number of infect people
    public double calcInfectedNum(){
        double counter = 0;
        for (int i = 0; i < AREA_WIDTH / AreaUnit.BLOCK_WIDTH; i++) {
            for (int j = 0; j < AREA_LENGTH / AreaUnit.BLOCK_LENGTH; j++) {
                counter += areaUnitArray[i][j].getInfectNum();
            }
        }
        return counter;
    }

    public double calcPopulation() {
        double counter = 0;
        for (int i = 0; i < AREA_WIDTH / AreaUnit.BLOCK_WIDTH; i++) {
            for (int j = 0; j < AREA_LENGTH / AreaUnit.BLOCK_LENGTH; j++) {
                counter += areaUnitArray[i][j].getHeadcount();
            }
        }
        return counter;
    }

    //↓
    public void currToDown(int i, int j) {
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount(); //infect rate in area[i][j]
        double headcountOutNum = areaUnitArray[i][j].getHeadcount() * areaUnitArray[i][j].getPopFlowSpeed(); // population of area[i][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount() - headcountOutNum);
        areaUnitArray[i + 1][j].setHeadcount(areaUnitArray[i + 1][j].getHeadcount() + headcountOutNum);

        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum() - infectOutNum);
        areaUnitArray[i + 1][j].setInfectNum(areaUnitArray[i + 1][j].getInfectNum() + infectOutNum);
    }

    //↑
    public void currToDownR(int i, int j) {
        double infectRate = areaUnitArray[i + 1][j].getInfectNum() / areaUnitArray[i + 1][j].getHeadcount(); //infect rate in area[i + 1][j]
        double headcountOutNum = areaUnitArray[i + 1][j].getHeadcount() * areaUnitArray[i + 1][j].getPopFlowSpeed(); // population of area[i + 1][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i + 1][j].setHeadcount(areaUnitArray[i + 1][j].getHeadcount() - headcountOutNum);
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount() + headcountOutNum);

        areaUnitArray[i + 1][j].setInfectNum(areaUnitArray[i + 1][j].getInfectNum() - infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum() + infectOutNum);
    }

    //→
    public void currToRight(int i, int j) {
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount(); //infect rate in area[i][j]
        double headcountOutNum = areaUnitArray[i][j].getHeadcount() * areaUnitArray[i][j].getPopFlowSpeed(); // population of area[i][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount() - headcountOutNum);
        areaUnitArray[i][j + 1].setHeadcount(areaUnitArray[i][j+1].getHeadcount() + headcountOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum() - infectOutNum);
        areaUnitArray[i][j + 1].setInfectNum(areaUnitArray[i][j+1].getInfectNum() + infectOutNum);
    }

    //←
    public void currToRightR(int i, int j) {
        double infectRate = areaUnitArray[i][j + 1].getInfectNum() / areaUnitArray[i][j + 1].getHeadcount(); //infect rate in area[i][j + 1]
        double headcountOutNum = areaUnitArray[i][j + 1].getHeadcount() * areaUnitArray[i][j + 1].getPopFlowSpeed(); // population of area[i][j + 1]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i][j + 1].setHeadcount(areaUnitArray[i][j + 1].getHeadcount() - headcountOutNum);
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount() + headcountOutNum);
        areaUnitArray[i][j + 1].setInfectNum(areaUnitArray[i][j + 1].getInfectNum() - infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum() + infectOutNum);
    }

    //↘
    public void currToRightBottom(int i, int j) {
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount(); //infect rate in area[i][j]
        double headcountOutNum = areaUnitArray[i][j].getHeadcount() * areaUnitArray[i][j].getPopFlowSpeed(); // population of area[i][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount() - headcountOutNum);
        areaUnitArray[i + 1][j + 1].setHeadcount(areaUnitArray[i + 1][j + 1].getHeadcount() + headcountOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum() -infectOutNum);
        areaUnitArray[i + 1][j + 1].setInfectNum(areaUnitArray[i + 1][j + 1].getInfectNum() + infectOutNum);
    }

    //↖
    public void currToRightBottomR(int i, int j) {
        double infectRate = areaUnitArray[i + 1][j + 1].getInfectNum() / areaUnitArray[i + 1][j + 1].getHeadcount(); //infect rate in area[i + 1][j + 1]
        double headcountOutNum = areaUnitArray[i + 1][j + 1].getHeadcount() * areaUnitArray[i + 1][j + 1].getPopFlowSpeed(); // population of area[i + 1][j + 1]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i + 1][j + 1].setHeadcount(areaUnitArray[i + 1][j + 1].getHeadcount() - headcountOutNum);
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount() + headcountOutNum);
        areaUnitArray[i + 1][j + 1].setInfectNum(areaUnitArray[i + 1][j + 1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }

    //↑
    public void currToUp(int i, int j) {
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount(); //infect rate in area[i][j]
        double headcountOutNum = areaUnitArray[i][j].getHeadcount() * areaUnitArray[i][j].getPopFlowSpeed(); // population of area[i][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        // TODO better to conduct population flow bidirectional simultaneously
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount() - headcountOutNum);
        areaUnitArray[i - 1][j].setHeadcount(areaUnitArray[i - 1][j].getHeadcount() + headcountOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum() - infectOutNum);
        areaUnitArray[i - 1][j].setInfectNum(areaUnitArray[i - 1][j].getInfectNum() + infectOutNum);
    }

    //↓ reverse
    public void currToUpR(int i, int j){
        double infectRate = areaUnitArray[i-1][j].getInfectNum() / areaUnitArray[i-1][j].getHeadcount(); //infect rate in area[i - 1][j]
        double headcountOutNum = areaUnitArray[i-1][j].getHeadcount() * areaUnitArray[i-1][j].getPopFlowSpeed(); // population of area[i - 1][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out
        areaUnitArray[i - 1][j].setHeadcount(areaUnitArray[i - 1][j].getHeadcount()- headcountOutNum);
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ headcountOutNum);
        areaUnitArray[i - 1][j].setInfectNum(areaUnitArray[i - 1][j].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }

    //↗
    public void currToRightTop(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount(); //infect rate in area[i][j]
        double headcountOutNum = areaUnitArray[i][j].getHeadcount() * areaUnitArray[i][j].getPopFlowSpeed(); // population of area[i][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- headcountOutNum);
        areaUnitArray[i - 1][j + 1].setHeadcount(areaUnitArray[i - 1][j + 1].getHeadcount()+ headcountOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i - 1][j + 1].setInfectNum(areaUnitArray[i - 1][j + 1].getInfectNum()+infectOutNum);
    }

    //↙
    public void currToRightTopR(int i, int j){
        double infectRate = areaUnitArray[i - 1][j + 1].getInfectNum() / areaUnitArray[i - 1][j + 1].getHeadcount(); //infect rate in area[i - 1][j + 1]
        double headcountOutNum = areaUnitArray[i - 1][j + 1].getHeadcount() * areaUnitArray[i - 1][j + 1].getPopFlowSpeed(); // population of area[i - 1][j + 1]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i - 1][j + 1].setHeadcount(areaUnitArray[i - 1][j + 1].getHeadcount()- headcountOutNum);
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ headcountOutNum);
        areaUnitArray[i - 1][j + 1].setInfectNum(areaUnitArray[i - 1][j + 1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }

    //←
    public void currToLeft(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount(); //infect rate in area[i][j]
        double headcountOutNum = areaUnitArray[i][j].getHeadcount() * areaUnitArray[i][j].getPopFlowSpeed(); // population of area[i][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- headcountOutNum);
        areaUnitArray[i][j - 1].setHeadcount(areaUnitArray[i][j - 1].getHeadcount()+ headcountOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i][j - 1].setInfectNum(areaUnitArray[i][j - 1].getInfectNum()+infectOutNum);
    }

    //→
    public void currToLeftR(int i, int j){
        double infectRate = areaUnitArray[i][j - 1].getInfectNum() / areaUnitArray[i][j - 1].getHeadcount(); //infect rate in area[i][j - 1]
        double headcountOutNum = areaUnitArray[i][j - 1].getHeadcount() * areaUnitArray[i][j - 1].getPopFlowSpeed(); // population of area[i][j - 1]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i][j - 1].setHeadcount(areaUnitArray[i][j - 1].getHeadcount()- headcountOutNum);
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ headcountOutNum);
        areaUnitArray[i][j - 1].setInfectNum(areaUnitArray[i][j - 1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }

    //↙
    public void currToLeftBottom(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount(); //infect rate in area[i][j]
        double headcountOutNum = areaUnitArray[i][j].getHeadcount() * areaUnitArray[i][j].getPopFlowSpeed(); // population of area[i][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount() - headcountOutNum);
        areaUnitArray[i + 1][j - 1].setHeadcount(areaUnitArray[i + 1][j - 1].getHeadcount()+ headcountOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum() - infectOutNum);
        areaUnitArray[i + 1][j - 1].setInfectNum(areaUnitArray[i + 1][j - 1].getInfectNum() + infectOutNum);
    }

    //↗
    public void currToLeftBottomR(int i, int j){
        double infectRate = areaUnitArray[i + 1][j - 1].getInfectNum() / areaUnitArray[i + 1][j - 1].getHeadcount(); //infect rate in area[i + 1][j - 1]
        double headcountOutNum = areaUnitArray[i + 1][j - 1].getHeadcount() * areaUnitArray[i + 1][j - 1].getPopFlowSpeed(); // population of area[i + 1][j - 1]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i + 1][j - 1].setHeadcount(areaUnitArray[i + 1][j - 1].getHeadcount()- headcountOutNum);
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ headcountOutNum);
        areaUnitArray[i + 1][j - 1].setInfectNum(areaUnitArray[i + 1][j - 1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }

    //↖
    public void currToLeftTop(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount(); //infect rate in area[i][j]
        double headcountOutNum = areaUnitArray[i][j].getHeadcount() * areaUnitArray[i][j].getPopFlowSpeed(); // population of area[i][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- headcountOutNum);
        areaUnitArray[i - 1][j - 1].setHeadcount(areaUnitArray[i - 1][j - 1].getHeadcount()+ headcountOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i - 1][j - 1].setInfectNum(areaUnitArray[i - 1][j - 1].getInfectNum()+infectOutNum);
    }

    //↘
    public void currToLeftTopR(int i, int j){
        double infectRate = areaUnitArray[i - 1][j - 1].getInfectNum() / areaUnitArray[i - 1][j - 1].getHeadcount(); //infect rate in area[i][j]
        double headcountOutNum = areaUnitArray[i - 1][j - 1].getHeadcount() * areaUnitArray[i - 1][j - 1].getPopFlowSpeed(); // population of area[i][j]
        double infectOutNum = headcountOutNum * infectRate; // infect number to flow out

        areaUnitArray[i - 1][j - 1].setHeadcount(areaUnitArray[i - 1][j - 1].getHeadcount()- headcountOutNum);
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ headcountOutNum);
        areaUnitArray[i - 1][j - 1].setInfectNum(areaUnitArray[i - 1][j - 1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }

    /*
       Calculate teh spread of the infection
       If the population flow is n in unit time, the number of infection among them will be n * infectRate
       if the area take quantatine, we assume that there won't be any population flow
     */

    public void calcSpreadAtTopLeftCorner(int i, int j) {
        if(!areaUnitArray[i][j].isQuarantine()){
            currToDown(i,j);
        }

        if(!areaUnitArray[i+1][j].isQuarantine()){
            currToDownR(i,j);
        }
        // TODO need to review
//        if(!areaUnitArray[i+1][j].isQuarantine() && !areaUnitArray[i][j].isQuarantine()){
//            currToDownR(i,j);
//        }

        if(!areaUnitArray[i][j].isQuarantine()){
            currToRight(i,j);
        }

        if(!areaUnitArray[i][j+1].isQuarantine()){
            currToRightR(i,j);
        }

        if(!areaUnitArray[i][j].isQuarantine()){
            currToRightBottom(i,j);
        }

        if(!areaUnitArray[i+1][j+1].isQuarantine()){
            currToRightBottomR(i,j);
        }
    }

    public void calcSpreadAtLeftBottom(int i, int j) {
        if(!areaUnitArray[i][j].isQuarantine()){
            currToUp(i,j);
        }

        if(!areaUnitArray[i-1][j].isQuarantine()){
            currToUpR(i,j);
        }

        if(!areaUnitArray[i][j].isQuarantine()){
            currToRight(i,j);
        }

        if(!areaUnitArray[i][j+1].isQuarantine()){
            currToRightR(i,j);
        }

        if(!areaUnitArray[i][j].isQuarantine()){
            currToRightTop(i,j);
        }

        if(!areaUnitArray[i-1][j+1].isQuarantine()){
            currToRightTopR(i,j);
        }
    }

    public void calcSpreadAtTopRightCorner(int i, int j){
        if(!areaUnitArray[i][j].isQuarantine()){
            currToLeft(i,j);
        }

        if(!areaUnitArray[i][j-1].isQuarantine()){
            currToLeftR(i,j);
        }

        if(!areaUnitArray[i][j].isQuarantine()){
            currToDown(i,j);
        }

        if(!areaUnitArray[i+1][j].isQuarantine()){
            currToDownR(i,j);
        }

        if(!areaUnitArray[i][j].isQuarantine()){
            currToLeftBottom(i,j);
        }

        if(!areaUnitArray[i+1][j-1].isQuarantine()){
            currToLeftBottomR(i,j);
        }
    }

    public void calcSpreadAtRightBottom(int i, int j) {

        if(!areaUnitArray[i][j].isQuarantine()){
            currToLeft(i,j);
        }

        if(!areaUnitArray[i][j-1].isQuarantine()){
            currToLeftR(i,j);
        }

        if(!areaUnitArray[i][j].isQuarantine()){
            currToUp(i,j);
        }

        if(!areaUnitArray[i-1][j].isQuarantine()){
            currToUpR(i,j);
        }

        if(!areaUnitArray[i][j].isQuarantine()){
            currToLeftTop(i,j);
        }

        if(!areaUnitArray[i-1][j-1].isQuarantine()){
            currToLeftTopR(i,j);
        }
    }

    public void calcSpread() {
        double counterForInfectNum = 0;
        int counterUnites = 0;
        for (int i = 0; i < AREA_WIDTH / AreaUnit.BLOCK_WIDTH; i++) {
            for (int j = 0; j < AREA_LENGTH / AreaUnit.BLOCK_LENGTH; j++) {

//                System.out.println("i " + i +", j "+ j + " infectNum " + areaUnitArray[i][j].getInfectNum() + ", head count " + areaUnitArray[i][j].getHeadcount());

                if (areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount() > 0.3) {
                    counterUnites++;
                }
                counterForInfectNum += areaUnitArray[i][j].getInfectNum();

                // calculate the spread
                if(!areaUnitArray[i][j].isQuarantine()){

                    double updateInfect = areaUnitArray[i][j].getInfectNum() + areaUnitArray[i][j].getInfectSpeed();
                    if (updateInfect > areaUnitArray[i][j].getHeadcount()) {
                        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getHeadcount());
                    } else {
                        areaUnitArray[i][j].setInfectNum(updateInfect);
                    }
                } else {
                    double updateInfect = areaUnitArray[i][j].getInfectNum();
                    if (updateInfect > areaUnitArray[i][j].getHeadcount()) {
                        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getHeadcount());
                    } else {
                        areaUnitArray[i][j].setInfectNum(updateInfect);
                    }

                }
                if(i == 0 && j == 0){
                    calcSpreadAtTopLeftCorner(i,j);
                } else if(i == AREA_WIDTH / AreaUnit.BLOCK_WIDTH - 1 && j == 0){
                    calcSpreadAtLeftBottom(i,j);
                } else if(i == 0 && j == AREA_LENGTH / AreaUnit.BLOCK_LENGTH - 1){
                    calcSpreadAtTopRightCorner(i,j);
                } else if(i == AREA_WIDTH / AreaUnit.BLOCK_WIDTH - 1 && j == AREA_LENGTH / AreaUnit.BLOCK_LENGTH - 1){
                    calcSpreadAtRightBottom(i,j);
                } else if(j == 0){
                    calcSpreadAtTopLeftCorner(i,j);
                    if(!areaUnitArray[i][j].isQuarantine()){
                        //↑
                        currToUp(i,j);
                    }
                    //↓
                    if(!areaUnitArray[i-1][j].isQuarantine()){
                        currToUpR(i,j);
                    }
                    //↗
                    if(!areaUnitArray[i][j].isQuarantine()){
                        currToRightTop(i,j);
                    }
                    //↙
                    if(!areaUnitArray[i-1][j+1].isQuarantine()){
                        currToRightTopR(i,j);
                    }
                } else if(i == 0){
                    calcSpreadAtTopRightCorner(i,j);
                    //→
                    if(!areaUnitArray[i][j].isQuarantine()){
                        currToRight(i,j);
                    }
                    //←
                    if(!areaUnitArray[i][j+1].isQuarantine()){
                        currToRightR(i,j);
                    }
                    //↘
                    if(!areaUnitArray[i][j].isQuarantine()){
                        currToRightBottom(i,j);
                    }
                    //↖
                    if(!areaUnitArray[i+1][j+1].isQuarantine()){
                        currToRightBottomR(i,j);
                    }
                } else if(j == AREA_LENGTH / AreaUnit.BLOCK_LENGTH - 1){
                    calcSpreadAtTopRightCorner(i,j);
                    //↑
                    if(!areaUnitArray[i][j].isQuarantine()){
                        currToUp(i,j);
                    }
                    //↓
                    if(!areaUnitArray[i-1][j].isQuarantine()){
                        currToUpR(i,j);
                    }
                    //↖
                    if(!areaUnitArray[i][j].isQuarantine()){
                        currToLeftTop(i,j);
                    }
                    //↘
                    if(!areaUnitArray[i-1][j-1].isQuarantine()){
                        currToLeftTopR(i,j);
                    }
                } else if(i == AREA_WIDTH / AreaUnit.BLOCK_WIDTH - 1){
                    calcSpreadAtRightBottom(i,j);
                    //→
                    if(!areaUnitArray[i][j].isQuarantine()){
                        currToRight(i,j);
                    }
                    //←
                    if(!areaUnitArray[i][j+1].isQuarantine()){
                        currToRightR(i,j);
                    }
                    //5.↗
                    if(!areaUnitArray[i][j].isQuarantine()){
                        currToRightTop(i,j);
                    }
                    //↙
                    if(!areaUnitArray[i-1][j+1].isQuarantine()){
                        currToRightTopR(i,j);
                    }
                } else {
                    calcSpreadAtTopLeftCorner(i,j);
                    calcSpreadAtRightBottom(i,j);
                    //↗
                    if(!areaUnitArray[i][j].isQuarantine()){
                        currToRightTop(i,j);
                    }
                    //↙
                    if(!areaUnitArray[i-1][j+1].isQuarantine()){
                        currToRightTopR(i,j);
                    }
                    //↙
                    if(!areaUnitArray[i][j].isQuarantine()){
                        currToLeftBottom(i,j);
                    }
                    //↗
                    if(!areaUnitArray[i+1][j-1].isQuarantine()){
                        currToLeftBottomR(i,j);
                    }

                }
                if(areaUnitArray[i][j].getInfectNum() < 0){
                    areaUnitArray[i][j].setInfectNum(0);
                }
                if(areaUnitArray[i][j].getHeadcount() < 0){
                    areaUnitArray[i][j].setHeadcount(0);
                }
            }
        }

        this.infectNumList.add(counterForInfectNum);
        this.infectUnitsList.add(counterUnites);
    }

    public List<Double> getInfectNumList() {
        return infectNumList;
    }

    public AreaUnit[][] getAreaUnitArray() {
        return areaUnitArray;
    }

    public void setAreaUnitArray(AreaUnit[][] areaUnitArray) {
        this.areaUnitArray = areaUnitArray;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Integer> getInfectUnitsList() {
        return infectUnitsList;
    }

    public ChartDirectory getChartDirectory() {
        return chartDirectory;
    }

    public void addChart(Chart chart) {
        this.chartDirectory.add(chart);
    }
}

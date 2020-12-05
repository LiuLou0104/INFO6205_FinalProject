package Business.Simulation;

import Business.DrawArea.Area;
import Business.DrawArea.AreaUnit;
import Business.Pathogen.Pathogen;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class OnePathogenSimu extends Observable implements Runnable {
    Thread thread = null;
    public static final int AREA_LENGTH = 8; //The length of the area
    public static final int AREA_WIDTH = 8; //The width of the area
    AreaUnit[][] areaUnitArray;
    public int time = 1;
    private boolean done = false;
    private boolean paused = false;
    private boolean numShowFlag = true;
    private boolean infectFlag = true;
//    OnePathogenSimu onePathogenSimu = null;
    private List<OnePathogenSimu> dataSetForPlot;
    private Area area;

    /*
    * 构造器
    * */
    public OnePathogenSimu(){
       area = new Area(AREA_LENGTH,AREA_WIDTH);
       this.areaUnitArray = area.getArea();
       this.dataSetForPlot = new ArrayList<>();
    }

    public OnePathogenSimu(Pathogen pathogen, double popuDensity, boolean isWearingMask, boolean isQuarantine, boolean isTest) {
        area = new Area(AREA_LENGTH, AREA_WIDTH, pathogen, popuDensity, isWearingMask, isQuarantine, isTest);
        this.areaUnitArray = area.getArea();
        this.dataSetForPlot = new ArrayList<>();
    }

    public AreaUnit[][] getAreaUnitArray() {
        return areaUnitArray;
    }

    public List<OnePathogenSimu> getInfectNum() {
        return this.dataSetForPlot;
    }

    //开始模拟
    public void startSim(){
        System.out.println("Starting the simulation");
//        onePathogenSimu = new OnePathogenSimu();
        if(thread != null) return;
        thread = new Thread(this);
        done = false;
        thread.start();
        //TODO 运行n时间(s)停止
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                done = true;
            }
        },5000);
    }

    //结束模拟
    public void stopSim(){
        System.out.println("Stop the simulation");
        if (thread == null) return;
        done = true;
    }

    @Override
    public void run() {
        runSimLoop();
        thread = null;
    }
    private void runSimLoop(){
        while(!done){
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
    private void updateSim(){
        calcOilSpread();
        setChanged();
        notifyObservers(this);
        this.dataSetForPlot.add(this);
    }
    //计算存在感染者的Unit数量
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
    //计算总共的感染者数量
    public double calcInfectedNum(){
        double counter = 0;
        for (int i = 0; i < AREA_WIDTH / AreaUnit.BLOCK_WIDTH; i++) {
            for (int j = 0; j < AREA_LENGTH / AreaUnit.BLOCK_LENGTH; j++) {
                counter += areaUnitArray[i][j].getInfectNum();
            }
        }
        return counter;
    }


    //↓
    public void currToDown(int i, int j){
        //area[i][j]地区的感染率
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount();
        double infectOutNum = areaUnitArray[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i+1][j].setHeadcount(areaUnitArray[i+1][j].getHeadcount()+ areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i+1][j].setInfectNum(areaUnitArray[i+1][j].getInfectNum()+infectOutNum);
    }
    //↑
    public void currToDownR(int i, int j){
        double infectRate = areaUnitArray[i+1][j].getInfectNum() / areaUnitArray[i+1][j].getHeadcount();
        double infectOutNum = areaUnitArray[i+1][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i+1][j].setHeadcount(areaUnitArray[i+1][j].getHeadcount()- areaUnitArray[i+1][j].getPopFlowSpeed());
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ areaUnitArray[i+1][j].getPopFlowSpeed());
        areaUnitArray[i+1][j].setInfectNum(areaUnitArray[i+1][j].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }
    //→
    public void currToRight(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount();
        double infectOutNum = areaUnitArray[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i][j+1].setHeadcount(areaUnitArray[i][j+1].getHeadcount()+ areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i][j+1].setInfectNum(areaUnitArray[i][j+1].getInfectNum()+infectOutNum);
    }
    //←
    public void currToRightR(int i, int j){
        double infectRate = areaUnitArray[i][j+1].getInfectNum() / areaUnitArray[i][j+1].getHeadcount();
        double infectOutNum = areaUnitArray[i][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i][j+1].setHeadcount(areaUnitArray[i][j+1].getHeadcount()- areaUnitArray[i][j+1].getPopFlowSpeed());
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ areaUnitArray[i][j+1].getPopFlowSpeed());
        areaUnitArray[i][j+1].setInfectNum(areaUnitArray[i][j+1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }
    //↘
    public void currToRightBottom(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount();
        double infectOutNum = areaUnitArray[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i+1][j+1].setHeadcount(areaUnitArray[i+1][j+1].getHeadcount()+ areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i+1][j+1].setInfectNum(areaUnitArray[i+1][j+1].getInfectNum()+infectOutNum);
    }
    //↖
    public void currToRightBottomR(int i, int j){
        double infectRate = areaUnitArray[i+1][j+1].getInfectNum() / areaUnitArray[i+1][j+1].getHeadcount();
        double infectOutNum = areaUnitArray[i+1][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i+1][j+1].setHeadcount(areaUnitArray[i+1][j+1].getHeadcount()- areaUnitArray[i+1][j+1].getPopFlowSpeed());
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ areaUnitArray[i+1][j+1].getPopFlowSpeed());
        areaUnitArray[i+1][j+1].setInfectNum(areaUnitArray[i+1][j+1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }
    //↑
    public void currToUp(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount();
        double infectOutNum = areaUnitArray[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i-1][j].setHeadcount(areaUnitArray[i-1][j].getHeadcount()+ areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i-1][j].setInfectNum(areaUnitArray[i-1][j].getInfectNum()+infectOutNum);
    }
    //↓
    public void currToUpR(int i, int j){
        double infectRate = areaUnitArray[i-1][j].getInfectNum() / areaUnitArray[i-1][j].getHeadcount();
        double infectOutNum = areaUnitArray[i-1][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i-1][j].setHeadcount(areaUnitArray[i-1][j].getHeadcount()- areaUnitArray[i-1][j].getPopFlowSpeed());
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ areaUnitArray[i-1][j].getPopFlowSpeed());
        areaUnitArray[i-1][j].setInfectNum(areaUnitArray[i-1][j].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }
    //↗
    public void currToRightTop(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount();
        double infectOutNum = areaUnitArray[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i-1][j+1].setHeadcount(areaUnitArray[i-1][j+1].getHeadcount()+ areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i-1][j+1].setInfectNum(areaUnitArray[i-1][j+1].getInfectNum()+infectOutNum);
    }
    //↙
    public void currToRightTopR(int i, int j){
        double infectRate = areaUnitArray[i-1][j+1].getInfectNum() / areaUnitArray[i-1][j+1].getHeadcount();
        double infectOutNum = areaUnitArray[i-1][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i-1][j+1].setHeadcount(areaUnitArray[i-1][j+1].getHeadcount()- areaUnitArray[i-1][j+1].getPopFlowSpeed());
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ areaUnitArray[i-1][j+1].getPopFlowSpeed());
        areaUnitArray[i-1][j+1].setInfectNum(areaUnitArray[i-1][j+1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }
    //←
    public void currToLeft(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount();
        double infectOutNum = areaUnitArray[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i][j-1].setHeadcount(areaUnitArray[i][j-1].getHeadcount()+ areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i][j-1].setInfectNum(areaUnitArray[i][j-1].getInfectNum()+infectOutNum);
    }
    //→
    public void currToLeftR(int i, int j){
        double infectRate = areaUnitArray[i][j-1].getInfectNum() / areaUnitArray[i][j-1].getHeadcount();
        double infectOutNum = areaUnitArray[i][j-1].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i][j-1].setHeadcount(areaUnitArray[i][j-1].getHeadcount()- areaUnitArray[i][j-1].getPopFlowSpeed());
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ areaUnitArray[i][j-1].getPopFlowSpeed());
        areaUnitArray[i][j-1].setInfectNum(areaUnitArray[i][j-1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }
    //↙
    public void currToLeftBottom(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount();
        double infectOutNum = areaUnitArray[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i+1][j-1].setHeadcount(areaUnitArray[i+1][j-1].getHeadcount()+ areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i+1][j-1].setInfectNum(areaUnitArray[i+1][j-1].getInfectNum()+infectOutNum);
    }
    //↗
    public void currToLeftBottomR(int i, int j){
        double infectRate = areaUnitArray[i+1][j-1].getInfectNum() / areaUnitArray[i+1][j-1].getHeadcount();
        double infectOutNum = areaUnitArray[i+1][j-1].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i+1][j-1].setHeadcount(areaUnitArray[i+1][j-1].getHeadcount()- areaUnitArray[i+1][j-1].getPopFlowSpeed());
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ areaUnitArray[i+1][j-1].getPopFlowSpeed());
        areaUnitArray[i+1][j-1].setInfectNum(areaUnitArray[i+1][j-1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }
    //↖
    public void currToLeftTop(int i, int j){
        double infectRate = areaUnitArray[i][j].getInfectNum() / areaUnitArray[i][j].getHeadcount();
        double infectOutNum = areaUnitArray[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()- areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i-1][j-1].setHeadcount(areaUnitArray[i-1][j-1].getHeadcount()+ areaUnitArray[i][j].getPopFlowSpeed());
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()-infectOutNum);
        areaUnitArray[i-1][j-1].setInfectNum(areaUnitArray[i-1][j-1].getInfectNum()+infectOutNum);
    }
    //↘
    public void currToLeftTopR(int i, int j){
        double infectRate = areaUnitArray[i-1][j-1].getInfectNum() / areaUnitArray[i-1][j-1].getHeadcount();
        double infectOutNum = areaUnitArray[i-1][j-1].getPopFlowSpeed() * infectRate; //流出的感染者数量
        areaUnitArray[i-1][j-1].setHeadcount(areaUnitArray[i-1][j-1].getHeadcount()- areaUnitArray[i-1][j-1].getPopFlowSpeed());
        areaUnitArray[i][j].setHeadcount(areaUnitArray[i][j].getHeadcount()+ areaUnitArray[i-1][j-1].getPopFlowSpeed());
        areaUnitArray[i-1][j-1].setInfectNum(areaUnitArray[i-1][j-1].getInfectNum()-infectOutNum);
        areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum()+infectOutNum);
    }





    //计算左上角的传染扩散
    public void calcOilSpreadAtTopLeftCorner(int i, int j) {

            //如果流动的人口数量是n，那么其中的感染者数量为n * infectRate
            //如果该地区采取了旅行限制措施，那么我们认为该地区的传染者不会流出

            //1.上方向下方流动
            if(!areaUnitArray[i][j].isQuarantine()){
                currToDown(i,j);
            }
            //2.下方向上方流动
            if(!areaUnitArray[i+1][j].isQuarantine()){
                currToDownR(i,j);
            }
            //3.上方向右方流动
            if(!areaUnitArray[i][j].isQuarantine()){
                currToRight(i,j);
            }
            //4.右方向上方流动
            if(!areaUnitArray[i][j+1].isQuarantine()){
                currToRightR(i,j);
            }
            //5.上方向右下流动
            if(!areaUnitArray[i][j].isQuarantine()){
                currToRightBottom(i,j);
            }
            //6.右下向上方流动
            if(!areaUnitArray[i+1][j+1].isQuarantine()){
                currToRightBottomR(i,j);
            }
    }

    //计算左下角的传染扩散
    public void calcOilSpreadAtLeftBottom(int i, int j) {
        //1.下方向上方流动
        if(!areaUnitArray[i][j].isQuarantine()){
            currToUp(i,j);
        }
        //2.上方向下方流动
        if(!areaUnitArray[i-1][j].isQuarantine()){
            currToUpR(i,j);
        }
        //3.上方向右方流动
        if(!areaUnitArray[i][j].isQuarantine()){
            currToRight(i,j);
        }
        //4.右方向上方流动
        if(!areaUnitArray[i][j+1].isQuarantine()){
            currToRightR(i,j);
        }
        //5.下方向右上流动
        if(!areaUnitArray[i][j].isQuarantine()){
            currToRightTop(i,j);
        }
        //6.右上向下方流动
        if(!areaUnitArray[i-1][j+1].isQuarantine()){
            currToRightTopR(i,j);
        }
    }

    //计算右上角的传染扩散
    public void calcOilSpreadAtTopRightCorner(int i, int j){
        //1.←
        if(!areaUnitArray[i][j].isQuarantine()){
            currToLeft(i,j);
        }
        //2.→
        if(!areaUnitArray[i][j-1].isQuarantine()){
            currToLeftR(i,j);
        }
        //3.↓
        if(!areaUnitArray[i][j].isQuarantine()){
            currToDown(i,j);
        }
        //4.↑
        if(!areaUnitArray[i+1][j].isQuarantine()){
            currToDownR(i,j);
        }
        //5.↙
        if(!areaUnitArray[i][j].isQuarantine()){
            currToLeftBottom(i,j);
        }
        //6.↗
        if(!areaUnitArray[i+1][j-1].isQuarantine()){
            currToLeftBottomR(i,j);
        }
    }

    //计算右下角的传染扩散
    public void calcOilSpreadAtRightBottom(int i, int j) {
        //1.←
        if(!areaUnitArray[i][j].isQuarantine()){
            currToLeft(i,j);
        }
        //2.→
        if(!areaUnitArray[i][j-1].isQuarantine()){
            currToLeftR(i,j);
        }
        //3.↑
        if(!areaUnitArray[i][j].isQuarantine()){
            currToUp(i,j);
        }
        //4.↓
        if(!areaUnitArray[i-1][j].isQuarantine()){
            currToUpR(i,j);
        }
        //5.↖
        if(!areaUnitArray[i][j].isQuarantine()){
            currToLeftTop(i,j);
        }
        //6.↘
        if(!areaUnitArray[i-1][j-1].isQuarantine()){
            currToLeftTopR(i,j);
        }
    }

    //计算传染扩散
    public void calcOilSpread() {
        for (int i = 0; i < AREA_WIDTH / AreaUnit.BLOCK_WIDTH; i++) {
            for (int j = 0; j < AREA_LENGTH / AreaUnit.BLOCK_LENGTH; j++) {
                //TODO store data to draw chart
                //每一次都要计算该地区的传染扩散情况
                areaUnitArray[i][j].setInfectNum(areaUnitArray[i][j].getInfectNum() + areaUnitArray[i][j].getInfectSpeed());
                if(i == 0 && j == 0){
                    calcOilSpreadAtTopLeftCorner(i,j);
                } else if(i == AREA_WIDTH / AreaUnit.BLOCK_WIDTH - 1 && j == 0){
                    calcOilSpreadAtLeftBottom(i,j);
                } else if(i == 0 && j == AREA_LENGTH / AreaUnit.BLOCK_LENGTH - 1){
                    calcOilSpreadAtTopRightCorner(i,j);
                } else if(i == AREA_WIDTH / AreaUnit.BLOCK_WIDTH - 1 && j == AREA_LENGTH / AreaUnit.BLOCK_LENGTH - 1){
                    calcOilSpreadAtRightBottom(i,j);
                } else if(j == 0){ //位于左边上
                    calcOilSpreadAtTopLeftCorner(i,j);
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
                        //area[i][j]地区的感染率
                        currToRightTop(i,j);
                    }
                    //↙
                    if(!areaUnitArray[i-1][j+1].isQuarantine()){
                        currToRightTopR(i,j);
                    }
                } else if(i == 0){ //位于上边上
                    calcOilSpreadAtTopRightCorner(i,j);
                    //→
                    if(!areaUnitArray[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        currToRight(i,j);
                    }
                    //←
                    if(!areaUnitArray[i][j+1].isQuarantine()){
                        currToRightR(i,j);
                    }
                    //↘
                    if(!areaUnitArray[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        currToRightBottom(i,j);
                    }
                    //↖
                    if(!areaUnitArray[i+1][j+1].isQuarantine()){
                        currToRightBottomR(i,j);
                    }
                } else if(j == AREA_LENGTH / AreaUnit.BLOCK_LENGTH - 1){ //位于右边上
                    calcOilSpreadAtTopRightCorner(i,j);
                    //↑
                    if(!areaUnitArray[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        currToUp(i,j);
                    }
                    //↓
                    if(!areaUnitArray[i-1][j].isQuarantine()){
                        currToUpR(i,j);
                    }
                    //↖
                    if(!areaUnitArray[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        currToLeftTop(i,j);
                    }
                    //↘
                    if(!areaUnitArray[i-1][j-1].isQuarantine()){
                        currToLeftTopR(i,j);
                    }
                } else if(i == AREA_WIDTH / AreaUnit.BLOCK_WIDTH - 1){ //位于下边上
                    calcOilSpreadAtRightBottom(i,j);
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
                    calcOilSpreadAtTopLeftCorner(i,j);
                    calcOilSpreadAtRightBottom(i,j);
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
    }


}

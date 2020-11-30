package Business.Simulation;

import Business.DrawArea.Area;
import Business.DrawArea.AreaUnit;

import java.util.Observable;

import static java.lang.Thread.sleep;

public class OnePathogenSimu extends Observable implements Runnable {
    Thread thread = null;
    public static final int AREA_LENGTH = 8; //The length of the area
    public static final int AREA_WIDTH = 8; //The width of the area
    AreaUnit[][] area;
    public int time = 1;
    private boolean done = false;
    private boolean paused = false;
    private boolean numShowFlag = true;
    private boolean infectFlag = true;
    OnePathogenSimu onePathogenSimu = null;

    /*
    * 构造器
    * */
    public OnePathogenSimu(){
       Area area = new Area(AREA_LENGTH,AREA_WIDTH);
       this.area = area.getArea();
    }

    //开始模拟
    public void startSim(){
        System.out.println("Starting the simulation");
        onePathogenSimu = new OnePathogenSimu();
        if(thread != null) return;
        thread = new Thread(this);
        done = false;
        thread.start();
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
    }
    //计算存在感染者的Unit数量
    public int calcInfectedUnits(){
        int counter = 0;
        for (int i = 0; i < AREA_WIDTH / AreaUnit.BLOCK_WIDTH; i++) {
            for (int j = 0; j < AREA_LENGTH / AreaUnit.BLOCK_LENGTH; j++) {
                if (area[i][j].getInfectNum() > 0)
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
                counter += area[i][j].getInfectNum();
            }
        }
        return counter;
    }

    //计算左上角的传染扩散
    public void calcOilSpreadAtTopLeftCorner(int i, int j) {

            //如果流动的人口数量是n，那么其中的感染者数量为n * infectRate
            //如果该地区采取了旅行限制措施，那么我们认为该地区的传染者不会流出

            //1.上方向下方流动
            if(!area[i][j].isQuarantine()){
                //area[i][j]地区的感染率
                double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                area[i+1][j].setHeadcount(area[i+1][j].getHeadcount()+area[i][j].getPopFlowSpeed());
                area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                area[i+1][j].setInfectNum(area[i+1][j].getInfectNum()+infectOutNum);
            }
            //2.下方向上方流动
            if(!area[i+1][j].isQuarantine()){
                double infectRate = area[i+1][j].getInfectNum() / area[i+1][j].getHeadcount();
                double infectOutNum = area[i+1][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                area[i+1][j].setHeadcount(area[i+1][j].getHeadcount()-area[i+1][j].getPopFlowSpeed());
                area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i+1][j].getPopFlowSpeed());
                area[i+1][j].setInfectNum(area[i+1][j].getInfectNum()-infectOutNum);
                area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
            }
            //3.上方向右方流动
            if(!area[i][j].isQuarantine()){
                //area[i][j]地区的感染率
                double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                area[i][j+1].setHeadcount(area[i][j+1].getHeadcount()+area[i][j].getPopFlowSpeed());
                area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                area[i][j+1].setInfectNum(area[i][j+1].getInfectNum()+infectOutNum);
            }
            //4.右方向上方流动
            if(!area[i][j+1].isQuarantine()){
                double infectRate = area[i][j+1].getInfectNum() / area[i][j+1].getHeadcount();
                double infectOutNum = area[i][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
                area[i][j+1].setHeadcount(area[i][j+1].getHeadcount()-area[i][j+1].getPopFlowSpeed());
                area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i][j+1].getPopFlowSpeed());
                area[i][j+1].setInfectNum(area[i][j+1].getInfectNum()-infectOutNum);
                area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
            }
            //5.上方向右下流动
            if(!area[i][j].isQuarantine()){
                //area[i][j]地区的感染率
                double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                area[i+1][j+1].setHeadcount(area[i+1][j+1].getHeadcount()+area[i][j].getPopFlowSpeed());
                area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                area[i+1][j+1].setInfectNum(area[i+1][j+1].getInfectNum()+infectOutNum);
            }
            //6.右下向上方流动
            if(!area[i+1][j+1].isQuarantine()){
                double infectRate = area[i+1][j+1].getInfectNum() / area[i+1][j+1].getHeadcount();
                double infectOutNum = area[i+1][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
                area[i+1][j+1].setHeadcount(area[i+1][j+1].getHeadcount()-area[i+1][j+1].getPopFlowSpeed());
                area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i+1][j+1].getPopFlowSpeed());
                area[i+1][j+1].setInfectNum(area[i+1][j+1].getInfectNum()-infectOutNum);
                area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
            }
    }

    //计算左下角的传染扩散
    public void calcOilSpreadAtLeftBottom(int i, int j) {
        //1.下方向上方流动
        if(!area[i][j].isQuarantine()){
            //area[i][j]地区的感染率
            double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
            double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
            area[i-1][j].setHeadcount(area[i-1][j].getHeadcount()+area[i][j].getPopFlowSpeed());
            area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
            area[i-1][j].setInfectNum(area[i-1][j].getInfectNum()+infectOutNum);
        }
        //2.上方向下方流动
        if(!area[i-1][j].isQuarantine()){
            double infectRate = area[i-1][j].getInfectNum() / area[i-1][j].getHeadcount();
            double infectOutNum = area[i-1][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i-1][j].setHeadcount(area[i-1][j].getHeadcount()-area[i-1][j].getPopFlowSpeed());
            area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i-1][j].getPopFlowSpeed());
            area[i-1][j].setInfectNum(area[i-1][j].getInfectNum()-infectOutNum);
            area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
        }
        //3.上方向右方流动
        if(!area[i][j].isQuarantine()){
            //area[i][j]地区的感染率
            double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
            double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
            area[i][j+1].setHeadcount(area[i][j+1].getHeadcount()+area[i][j].getPopFlowSpeed());
            area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
            area[i][j+1].setInfectNum(area[i][j+1].getInfectNum()+infectOutNum);
        }
        //4.右方向上方流动
        if(!area[i][j+1].isQuarantine()){
            double infectRate = area[i][j+1].getInfectNum() / area[i][j+1].getHeadcount();
            double infectOutNum = area[i][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j+1].setHeadcount(area[i][j+1].getHeadcount()-area[i][j+1].getPopFlowSpeed());
            area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i][j+1].getPopFlowSpeed());
            area[i][j+1].setInfectNum(area[i][j+1].getInfectNum()-infectOutNum);
            area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
        }
        //5.下方向右上流动
        if(!area[i][j].isQuarantine()){
            //area[i][j]地区的感染率
            double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
            double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
            area[i-1][j+1].setHeadcount(area[i-1][j+1].getHeadcount()+area[i][j].getPopFlowSpeed());
            area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
            area[i-1][j+1].setInfectNum(area[i-1][j+1].getInfectNum()+infectOutNum);
        }
        //6.右上向下方流动
        if(!area[i-1][j+1].isQuarantine()){
            double infectRate = area[i-1][j+1].getInfectNum() / area[i-1][j+1].getHeadcount();
            double infectOutNum = area[i-1][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i-1][j+1].setHeadcount(area[i-1][j+1].getHeadcount()-area[i-1][j+1].getPopFlowSpeed());
            area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i-1][j+1].getPopFlowSpeed());
            area[i-1][j+1].setInfectNum(area[i-1][j+1].getInfectNum()-infectOutNum);
            area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
        }
    }

    //计算右上角的传染扩散
    public void calcOilSpreadAtTopRightCorner(int i, int j){
        //1.←
        if(!area[i][j].isQuarantine()){
            //area[i][j]地区的感染率
            double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
            double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
            area[i][j-1].setHeadcount(area[i][j-1].getHeadcount()+area[i][j].getPopFlowSpeed());
            area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
            area[i][j-1].setInfectNum(area[i][j-1].getInfectNum()+infectOutNum);
        }
        //2.→
        if(!area[i][j-1].isQuarantine()){
            double infectRate = area[i][j-1].getInfectNum() / area[i][j-1].getHeadcount();
            double infectOutNum = area[i][j-1].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j-1].setHeadcount(area[i][j-1].getHeadcount()-area[i][j-1].getPopFlowSpeed());
            area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i][j-1].getPopFlowSpeed());
            area[i][j-1].setInfectNum(area[i][j-1].getInfectNum()-infectOutNum);
            area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
        }
        //3.↓
        if(!area[i][j].isQuarantine()){
            //area[i][j]地区的感染率
            double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
            double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
            area[i+1][j].setHeadcount(area[i+1][j].getHeadcount()+area[i][j].getPopFlowSpeed());
            area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
            area[i+1][j].setInfectNum(area[i+1][j].getInfectNum()+infectOutNum);
        }
        //4.↑
        if(!area[i+1][j].isQuarantine()){
            double infectRate = area[i+1][j].getInfectNum() / area[i+1][j].getHeadcount();
            double infectOutNum = area[i+1][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i+1][j].setHeadcount(area[i+1][j].getHeadcount()-area[i+1][j].getPopFlowSpeed());
            area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i+1][j].getPopFlowSpeed());
            area[i+1][j].setInfectNum(area[i+1][j].getInfectNum()-infectOutNum);
            area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
        }
        //5.↙
        if(!area[i][j].isQuarantine()){
            //area[i][j]地区的感染率
            double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
            double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
            area[i+1][j-1].setHeadcount(area[i+1][j-1].getHeadcount()+area[i][j].getPopFlowSpeed());
            area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
            area[i+1][j-1].setInfectNum(area[i+1][j-1].getInfectNum()+infectOutNum);
        }
        //6.↗
        if(!area[i+1][j-1].isQuarantine()){
            double infectRate = area[i+1][j-1].getInfectNum() / area[i+1][j-1].getHeadcount();
            double infectOutNum = area[i+1][j-1].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i+1][j-1].setHeadcount(area[i+1][j-1].getHeadcount()-area[i+1][j-1].getPopFlowSpeed());
            area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i+1][j-1].getPopFlowSpeed());
            area[i+1][j-1].setInfectNum(area[i+1][j-1].getInfectNum()-infectOutNum);
            area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
        }
    }

    //计算右下角的传染扩散
    public void calcOilSpreadAtRightBottom(int i, int j) {
        //1.←
        if(!area[i][j].isQuarantine()){
            //area[i][j]地区的感染率
            double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
            double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
            area[i][j-1].setHeadcount(area[i][j-1].getHeadcount()+area[i][j].getPopFlowSpeed());
            area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
            area[i][j-1].setInfectNum(area[i][j-1].getInfectNum()+infectOutNum);
        }
        //2.→
        if(!area[i][j-1].isQuarantine()){
            double infectRate = area[i][j-1].getInfectNum() / area[i][j-1].getHeadcount();
            double infectOutNum = area[i][j-1].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j-1].setHeadcount(area[i][j-1].getHeadcount()-area[i][j-1].getPopFlowSpeed());
            area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i][j-1].getPopFlowSpeed());
            area[i][j-1].setInfectNum(area[i][j-1].getInfectNum()-infectOutNum);
            area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
        }
        //3.↑
        if(!area[i][j].isQuarantine()){
            //area[i][j]地区的感染率
            double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
            double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
            area[i-1][j].setHeadcount(area[i-1][j].getHeadcount()+area[i][j].getPopFlowSpeed());
            area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
            area[i-1][j].setInfectNum(area[i-1][j].getInfectNum()+infectOutNum);
        }
        //4.↓
        if(!area[i-1][j].isQuarantine()){
            double infectRate = area[i-1][j].getInfectNum() / area[i-1][j].getHeadcount();
            double infectOutNum = area[i-1][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i-1][j].setHeadcount(area[i-1][j].getHeadcount()-area[i-1][j].getPopFlowSpeed());
            area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i-1][j].getPopFlowSpeed());
            area[i-1][j].setInfectNum(area[i-1][j].getInfectNum()-infectOutNum);
            area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
        }
        //5.↖
        if(!area[i][j].isQuarantine()){
            //area[i][j]地区的感染率
            double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
            double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
            area[i-1][j-1].setHeadcount(area[i-1][j-1].getHeadcount()+area[i][j].getPopFlowSpeed());
            area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
            area[i-1][j-1].setInfectNum(area[i-1][j-1].getInfectNum()+infectOutNum);
        }
        //6.↘
        if(!area[i-1][j-1].isQuarantine()){
            double infectRate = area[i-1][j-1].getInfectNum() / area[i-1][j-1].getHeadcount();
            double infectOutNum = area[i-1][j-1].getPopFlowSpeed() * infectRate; //流出的感染者数量
            area[i-1][j-1].setHeadcount(area[i-1][j-1].getHeadcount()-area[i-1][j-1].getPopFlowSpeed());
            area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i-1][j-1].getPopFlowSpeed());
            area[i-1][j-1].setInfectNum(area[i-1][j-1].getInfectNum()-infectOutNum);
            area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
        }
    }

    //计算传染扩散
    public void calcOilSpread() {
        for (int i = 0; i < AREA_WIDTH / AreaUnit.BLOCK_WIDTH; i++) {
            for (int j = 0; j < AREA_LENGTH / AreaUnit.BLOCK_LENGTH; j++) {
                //每一次都要计算该地区的传染扩散情况
                area[i][j].setInfectNum(area[i][j].getInfectNum() + area[i][j].getInfectSpeed());
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
                    if(!area[i][j].isQuarantine()){
                        //↑
                        //area[i][j]地区的感染率
                        double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                        double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                        area[i-1][j].setHeadcount(area[i-1][j].getHeadcount()+area[i][j].getPopFlowSpeed());
                        area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                        area[i-1][j].setInfectNum(area[i-1][j].getInfectNum()+infectOutNum);
                    }
                    //↓
                    if(!area[i-1][j].isQuarantine()){
                        double infectRate = area[i-1][j].getInfectNum() / area[i-1][j].getHeadcount();
                        double infectOutNum = area[i-1][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i-1][j].setHeadcount(area[i-1][j].getHeadcount()-area[i-1][j].getPopFlowSpeed());
                        area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i-1][j].getPopFlowSpeed());
                        area[i-1][j].setInfectNum(area[i-1][j].getInfectNum()-infectOutNum);
                        area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
                    }
                    //↗
                    if(!area[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                        double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                        area[i-1][j+1].setHeadcount(area[i-1][j+1].getHeadcount()+area[i][j].getPopFlowSpeed());
                        area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                        area[i-1][j+1].setInfectNum(area[i-1][j+1].getInfectNum()+infectOutNum);
                    }
                    //↙
                    if(!area[i-1][j+1].isQuarantine()){
                        double infectRate = area[i-1][j+1].getInfectNum() / area[i-1][j+1].getHeadcount();
                        double infectOutNum = area[i-1][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i-1][j+1].setHeadcount(area[i-1][j+1].getHeadcount()-area[i-1][j+1].getPopFlowSpeed());
                        area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i-1][j+1].getPopFlowSpeed());
                        area[i-1][j+1].setInfectNum(area[i-1][j+1].getInfectNum()-infectOutNum);
                        area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
                    }
                } else if(i == 0){ //位于上边上
                    calcOilSpreadAtTopRightCorner(i,j);
                    //→
                    if(!area[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                        double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                        area[i][j+1].setHeadcount(area[i][j+1].getHeadcount()+area[i][j].getPopFlowSpeed());
                        area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                        area[i][j+1].setInfectNum(area[i][j+1].getInfectNum()+infectOutNum);
                    }
                    //←
                    if(!area[i][j+1].isQuarantine()){
                        double infectRate = area[i][j+1].getInfectNum() / area[i][j+1].getHeadcount();
                        double infectOutNum = area[i][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j+1].setHeadcount(area[i][j+1].getHeadcount()-area[i][j+1].getPopFlowSpeed());
                        area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i][j+1].getPopFlowSpeed());
                        area[i][j+1].setInfectNum(area[i][j+1].getInfectNum()-infectOutNum);
                        area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
                    }
                    //↘
                    if(!area[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                        double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                        area[i+1][j+1].setHeadcount(area[i+1][j+1].getHeadcount()+area[i][j].getPopFlowSpeed());
                        area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                        area[i+1][j+1].setInfectNum(area[i+1][j+1].getInfectNum()+infectOutNum);
                    }
                    //↖
                    if(!area[i+1][j+1].isQuarantine()){
                        double infectRate = area[i+1][j+1].getInfectNum() / area[i+1][j+1].getHeadcount();
                        double infectOutNum = area[i+1][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i+1][j+1].setHeadcount(area[i+1][j+1].getHeadcount()-area[i+1][j+1].getPopFlowSpeed());
                        area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i+1][j+1].getPopFlowSpeed());
                        area[i+1][j+1].setInfectNum(area[i+1][j+1].getInfectNum()-infectOutNum);
                        area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
                    }
                } else if(j == AREA_LENGTH / AreaUnit.BLOCK_LENGTH - 1){ //位于右边上
                    calcOilSpreadAtTopRightCorner(i,j);
                    //↑
                    if(!area[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                        double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                        area[i-1][j].setHeadcount(area[i-1][j].getHeadcount()+area[i][j].getPopFlowSpeed());
                        area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                        area[i-1][j].setInfectNum(area[i-1][j].getInfectNum()+infectOutNum);
                    }
                    //↓
                    if(!area[i-1][j].isQuarantine()){
                        double infectRate = area[i-1][j].getInfectNum() / area[i-1][j].getHeadcount();
                        double infectOutNum = area[i-1][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i-1][j].setHeadcount(area[i-1][j].getHeadcount()-area[i-1][j].getPopFlowSpeed());
                        area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i-1][j].getPopFlowSpeed());
                        area[i-1][j].setInfectNum(area[i-1][j].getInfectNum()-infectOutNum);
                        area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
                    }
                    //↖
                    if(!area[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                        double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                        area[i-1][j-1].setHeadcount(area[i-1][j-1].getHeadcount()+area[i][j].getPopFlowSpeed());
                        area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                        area[i-1][j-1].setInfectNum(area[i-1][j-1].getInfectNum()+infectOutNum);
                    }
                    //↘
                    if(!area[i-1][j-1].isQuarantine()){
                        double infectRate = area[i-1][j-1].getInfectNum() / area[i-1][j-1].getHeadcount();
                        double infectOutNum = area[i-1][j-1].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i-1][j-1].setHeadcount(area[i-1][j-1].getHeadcount()-area[i-1][j-1].getPopFlowSpeed());
                        area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i-1][j-1].getPopFlowSpeed());
                        area[i-1][j-1].setInfectNum(area[i-1][j-1].getInfectNum()-infectOutNum);
                        area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
                    }
                } else if(i == AREA_WIDTH / AreaUnit.BLOCK_WIDTH - 1){ //位于下边上
                    calcOilSpreadAtRightBottom(i,j);
                    //→
                    if(!area[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                        double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                        area[i][j+1].setHeadcount(area[i][j+1].getHeadcount()+area[i][j].getPopFlowSpeed());
                        area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                        area[i][j+1].setInfectNum(area[i][j+1].getInfectNum()+infectOutNum);
                    }
                    //←
                    if(!area[i][j+1].isQuarantine()){
                        double infectRate = area[i][j+1].getInfectNum() / area[i][j+1].getHeadcount();
                        double infectOutNum = area[i][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j+1].setHeadcount(area[i][j+1].getHeadcount()-area[i][j+1].getPopFlowSpeed());
                        area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i][j+1].getPopFlowSpeed());
                        area[i][j+1].setInfectNum(area[i][j+1].getInfectNum()-infectOutNum);
                        area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
                    }
                    //5.↗
                    if(!area[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                        double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                        area[i-1][j+1].setHeadcount(area[i-1][j+1].getHeadcount()+area[i][j].getPopFlowSpeed());
                        area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                        area[i-1][j+1].setInfectNum(area[i-1][j+1].getInfectNum()+infectOutNum);
                    }
                    //↙
                    if(!area[i-1][j+1].isQuarantine()){
                        double infectRate = area[i-1][j+1].getInfectNum() / area[i-1][j+1].getHeadcount();
                        double infectOutNum = area[i-1][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i-1][j+1].setHeadcount(area[i-1][j+1].getHeadcount()-area[i-1][j+1].getPopFlowSpeed());
                        area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i-1][j+1].getPopFlowSpeed());
                        area[i-1][j+1].setInfectNum(area[i-1][j+1].getInfectNum()-infectOutNum);
                        area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
                    }
                } else {
                    calcOilSpreadAtTopLeftCorner(i,j);
                    calcOilSpreadAtRightBottom(i,j);
                    //↗
                    if(!area[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                        double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                        area[i-1][j+1].setHeadcount(area[i-1][j+1].getHeadcount()+area[i][j].getPopFlowSpeed());
                        area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                        area[i-1][j+1].setInfectNum(area[i-1][j+1].getInfectNum()+infectOutNum);
                    }
                    //↙
                    if(!area[i-1][j+1].isQuarantine()){
                        double infectRate = area[i-1][j+1].getInfectNum() / area[i-1][j+1].getHeadcount();
                        double infectOutNum = area[i-1][j+1].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i-1][j+1].setHeadcount(area[i-1][j+1].getHeadcount()-area[i-1][j+1].getPopFlowSpeed());
                        area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i-1][j+1].getPopFlowSpeed());
                        area[i-1][j+1].setInfectNum(area[i-1][j+1].getInfectNum()-infectOutNum);
                        area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
                    }
                    //↙
                    if(!area[i][j].isQuarantine()){
                        //area[i][j]地区的感染率
                        double infectRate = area[i][j].getInfectNum() / area[i][j].getHeadcount();
                        double infectOutNum = area[i][j].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i][j].setHeadcount(area[i][j].getHeadcount()-area[i][j].getPopFlowSpeed());
                        area[i+1][j-1].setHeadcount(area[i+1][j-1].getHeadcount()+area[i][j].getPopFlowSpeed());
                        area[i][j].setInfectNum(area[i][j].getInfectNum()-infectOutNum);
                        area[i+1][j-1].setInfectNum(area[i+1][j-1].getInfectNum()+infectOutNum);
                    }
                    //↗
                    if(!area[i+1][j-1].isQuarantine()){
                        double infectRate = area[i+1][j-1].getInfectNum() / area[i+1][j-1].getHeadcount();
                        double infectOutNum = area[i+1][j-1].getPopFlowSpeed() * infectRate; //流出的感染者数量
                        area[i+1][j-1].setHeadcount(area[i+1][j-1].getHeadcount()-area[i+1][j-1].getPopFlowSpeed());
                        area[i][j].setHeadcount(area[i][j].getHeadcount()+area[i+1][j-1].getPopFlowSpeed());
                        area[i+1][j-1].setInfectNum(area[i+1][j-1].getInfectNum()-infectOutNum);
                        area[i][j].setInfectNum(area[i][j].getInfectNum()+infectOutNum);
                    }

                }
                if(area[i][j].getInfectNum() < 0){
                    area[i][j].setInfectNum(0);
                }
                if(area[i][j].getHeadcount() < 0){
                    area[i][j].setHeadcount(0);
                }
            }
        }
    }


}
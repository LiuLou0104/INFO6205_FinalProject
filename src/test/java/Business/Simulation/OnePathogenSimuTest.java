package Business.Simulation;

import org.junit.Test;

import static org.junit.Assert.*;


public class OnePathogenSimuTest {

    @Test
    public void calcInfectedUnits() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        assertEquals(0,onePathogenSimu.calcInfectedUnits());
        onePathogenSimu.areaUnitArray[1][1].setInfectNum(1);
        assertEquals(1,onePathogenSimu.calcInfectedUnits());
    }

    @Test
    public void calcInfectedNum() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        assertEquals(0,onePathogenSimu.calcInfectedNum(),0);
        onePathogenSimu.areaUnitArray[1][1].setInfectNum(5);
        onePathogenSimu.areaUnitArray[1][2].setInfectNum(2);
        assertEquals(7,onePathogenSimu.calcInfectedNum(),0);
    }

    @Test
    public void currToDown() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[0][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[0][0].setHeadcount(20);
        onePathogenSimu.areaUnitArray[0][0].setPopFlowSpeed(8);
        onePathogenSimu.currToDown(0,0);
        assertEquals(12,onePathogenSimu.areaUnitArray[0][0].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[1][0].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[0][0].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[1][0].getInfectNum(),0);

    }

    @Test
    public void currToDownR() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[1][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[1][0].setHeadcount(20);
        onePathogenSimu.areaUnitArray[1][0].setPopFlowSpeed(8);
        onePathogenSimu.currToDownR(0,0);
        assertEquals(12,onePathogenSimu.areaUnitArray[1][0].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[0][0].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[1][0].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[0][0].getInfectNum(),0);
    }

    @Test
    public void currToRight() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[0][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[0][0].setHeadcount(20);
        onePathogenSimu.areaUnitArray[0][0].setPopFlowSpeed(8);
        onePathogenSimu.currToRight(0,0);
        assertEquals(12,onePathogenSimu.areaUnitArray[0][0].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[0][1].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[0][0].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[0][1].getInfectNum(),0);
    }

    @Test
    public void currToRightR() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[0][1].setInfectNum(10);
        onePathogenSimu.areaUnitArray[0][1].setHeadcount(20);
        onePathogenSimu.areaUnitArray[0][1].setPopFlowSpeed(8);
        onePathogenSimu.currToRightR(0,0);
        assertEquals(12,onePathogenSimu.areaUnitArray[0][1].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[0][0].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[0][1].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[0][0].getInfectNum(),0);
    }

    @Test
    public void currToRightBottom() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[0][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[0][0].setHeadcount(20);
        onePathogenSimu.areaUnitArray[0][0].setPopFlowSpeed(8);
        onePathogenSimu.currToRightBottom(0,0);
        assertEquals(12,onePathogenSimu.areaUnitArray[0][0].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[1][1].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[0][0].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[1][1].getInfectNum(),0);
    }

    @Test
    public void currToRightBottomR() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[1][1].setInfectNum(10);
        onePathogenSimu.areaUnitArray[1][1].setHeadcount(20);
        onePathogenSimu.areaUnitArray[1][1].setPopFlowSpeed(8);
        onePathogenSimu.currToRightBottomR(0,0);
        assertEquals(12,onePathogenSimu.areaUnitArray[1][1].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[0][0].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[1][1].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[0][0].getInfectNum(),0);
    }

    @Test
    public void currToUp() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[7][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[7][0].setHeadcount(20);
        onePathogenSimu.areaUnitArray[7][0].setPopFlowSpeed(8);
        onePathogenSimu.currToUp(7,0);
        assertEquals(12,onePathogenSimu.areaUnitArray[7][0].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[6][0].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[7][0].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[6][0].getInfectNum(),0);
    }

    @Test
    public void currToUpR() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[6][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[6][0].setHeadcount(20);
        onePathogenSimu.areaUnitArray[6][0].setPopFlowSpeed(8);
        onePathogenSimu.currToUpR(7,0);
        assertEquals(12,onePathogenSimu.areaUnitArray[6][0].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[7][0].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[6][0].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[7][0].getInfectNum(),0);
    }

    @Test
    public void currToRightTop() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[7][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[7][0].setHeadcount(20);
        onePathogenSimu.areaUnitArray[7][0].setPopFlowSpeed(8);
        onePathogenSimu.currToRightTop(7,0);
        assertEquals(12,onePathogenSimu.areaUnitArray[7][0].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[6][1].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[7][0].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[6][1].getInfectNum(),0);
    }

    @Test
    public void currToRightTopR() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[6][1].setInfectNum(10);
        onePathogenSimu.areaUnitArray[6][1].setHeadcount(20);
        onePathogenSimu.areaUnitArray[6][1].setPopFlowSpeed(8);
        onePathogenSimu.currToRightTopR(7,0);
        assertEquals(12,onePathogenSimu.areaUnitArray[6][1].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[7][0].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[6][1].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[7][0].getInfectNum(),0);
    }

    @Test
    public void currToLeft() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[7][7].setInfectNum(10);
        onePathogenSimu.areaUnitArray[7][7].setHeadcount(20);
        onePathogenSimu.areaUnitArray[7][7].setPopFlowSpeed(8);
        onePathogenSimu.currToLeft(7,7);
        assertEquals(12,onePathogenSimu.areaUnitArray[7][7].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[7][6].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[7][7].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[7][6].getInfectNum(),0);
    }

    @Test
    public void currToLeftR() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[7][6].setInfectNum(10);
        onePathogenSimu.areaUnitArray[7][6].setHeadcount(20);
        onePathogenSimu.areaUnitArray[7][6].setPopFlowSpeed(8);
        onePathogenSimu.currToLeftR(7,7);
        assertEquals(12,onePathogenSimu.areaUnitArray[7][6].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[7][7].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[7][6].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[7][7].getInfectNum(),0);
    }

    @Test
    public void currToLeftBottom() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[0][7].setInfectNum(10);
        onePathogenSimu.areaUnitArray[0][7].setHeadcount(20);
        onePathogenSimu.areaUnitArray[0][7].setPopFlowSpeed(8);
        onePathogenSimu.currToLeftBottom(0,7);
        assertEquals(12,onePathogenSimu.areaUnitArray[0][7].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[1][6].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[0][7].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[1][6].getInfectNum(),0);
    }

    @Test
    public void currToLeftBottomR() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[1][6].setInfectNum(10);
        onePathogenSimu.areaUnitArray[1][6].setHeadcount(20);
        onePathogenSimu.areaUnitArray[1][6].setPopFlowSpeed(8);
        onePathogenSimu.currToLeftBottomR(0,7);
        assertEquals(12,onePathogenSimu.areaUnitArray[1][6].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[0][7].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[1][6].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[0][7].getInfectNum(),0);
    }

    @Test
    public void currToLeftTop() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[7][7].setInfectNum(10);
        onePathogenSimu.areaUnitArray[7][7].setHeadcount(20);
        onePathogenSimu.areaUnitArray[7][7].setPopFlowSpeed(8);
        onePathogenSimu.currToLeftTop(7,7);
        assertEquals(12,onePathogenSimu.areaUnitArray[7][7].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[6][6].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[7][7].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[6][6].getInfectNum(),0);
    }

    @Test
    public void currToLeftTopR() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[6][6].setInfectNum(10);
        onePathogenSimu.areaUnitArray[6][6].setHeadcount(20);
        onePathogenSimu.areaUnitArray[6][6].setPopFlowSpeed(8);
        onePathogenSimu.currToLeftTopR(7,7);
        assertEquals(12,onePathogenSimu.areaUnitArray[6][6].getHeadcount(),0);
        assertEquals(8,onePathogenSimu.areaUnitArray[7][7].getHeadcount(),0);
        assertEquals(6,onePathogenSimu.areaUnitArray[6][6].getInfectNum(),0);
        assertEquals(4,onePathogenSimu.areaUnitArray[7][7].getInfectNum(),0);
    }

    @Test
    public void calcOilSpreadAtTopLeftCorner() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[0][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[0][0].setHeadcount(10);
        onePathogenSimu.areaUnitArray[0][0].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[0][1].setInfectNum(10);
        onePathogenSimu.areaUnitArray[0][1].setHeadcount(10);
        onePathogenSimu.areaUnitArray[0][1].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[1][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[1][0].setHeadcount(10);
        onePathogenSimu.areaUnitArray[1][0].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[1][1].setInfectNum(10);
        onePathogenSimu.areaUnitArray[1][1].setHeadcount(10);
        onePathogenSimu.areaUnitArray[1][1].setPopFlowSpeed(4);
        onePathogenSimu.calcOilSpreadAtTopLeftCorner(0,0);
        assertEquals(10,onePathogenSimu.areaUnitArray[0][0].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[0][0].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[0][1].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[0][1].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[1][0].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[1][0].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[1][1].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[1][1].getInfectNum(),0);

    }

    @Test
    public void calcOilSpreadAtLeftBottom() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[7][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[7][0].setHeadcount(10);
        onePathogenSimu.areaUnitArray[7][0].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[7][1].setInfectNum(10);
        onePathogenSimu.areaUnitArray[7][1].setHeadcount(10);
        onePathogenSimu.areaUnitArray[7][1].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[6][0].setInfectNum(10);
        onePathogenSimu.areaUnitArray[6][0].setHeadcount(10);
        onePathogenSimu.areaUnitArray[6][0].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[6][1].setInfectNum(10);
        onePathogenSimu.areaUnitArray[6][1].setHeadcount(10);
        onePathogenSimu.areaUnitArray[6][1].setPopFlowSpeed(4);
        onePathogenSimu.calcOilSpreadAtLeftBottom(7,0);
        assertEquals(10,onePathogenSimu.areaUnitArray[7][0].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[7][0].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[7][1].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[7][1].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[6][0].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[6][0].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[6][1].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[6][1].getInfectNum(),0);
    }

    @Test
    public void calcOilSpreadAtTopRightCorner() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[0][7].setInfectNum(10);
        onePathogenSimu.areaUnitArray[0][7].setHeadcount(10);
        onePathogenSimu.areaUnitArray[0][7].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[1][7].setInfectNum(10);
        onePathogenSimu.areaUnitArray[1][7].setHeadcount(10);
        onePathogenSimu.areaUnitArray[1][7].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[1][6].setInfectNum(10);
        onePathogenSimu.areaUnitArray[1][6].setHeadcount(10);
        onePathogenSimu.areaUnitArray[1][6].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[0][6].setInfectNum(10);
        onePathogenSimu.areaUnitArray[0][6].setHeadcount(10);
        onePathogenSimu.areaUnitArray[0][6].setPopFlowSpeed(4);
        onePathogenSimu.calcOilSpreadAtTopRightCorner(0,7);
        assertEquals(10,onePathogenSimu.areaUnitArray[0][7].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[0][7].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[1][7].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[1][7].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[1][6].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[1][6].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[0][6].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[0][6].getInfectNum(),0);
    }

    @Test
    public void calcOilSpreadAtRightBottom() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        onePathogenSimu.areaUnitArray[7][7].setInfectNum(10);
        onePathogenSimu.areaUnitArray[7][7].setHeadcount(10);
        onePathogenSimu.areaUnitArray[7][7].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[6][7].setInfectNum(10);
        onePathogenSimu.areaUnitArray[6][7].setHeadcount(10);
        onePathogenSimu.areaUnitArray[6][7].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[6][6].setInfectNum(10);
        onePathogenSimu.areaUnitArray[6][6].setHeadcount(10);
        onePathogenSimu.areaUnitArray[6][6].setPopFlowSpeed(4);
        onePathogenSimu.areaUnitArray[7][6].setInfectNum(10);
        onePathogenSimu.areaUnitArray[7][6].setHeadcount(10);
        onePathogenSimu.areaUnitArray[7][6].setPopFlowSpeed(4);
        onePathogenSimu.calcOilSpreadAtTopRightCorner(0,7);
        assertEquals(10,onePathogenSimu.areaUnitArray[7][7].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[7][7].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[6][7].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[6][7].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[6][6].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[6][6].getInfectNum(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[7][6].getHeadcount(),0);
        assertEquals(10,onePathogenSimu.areaUnitArray[7][6].getInfectNum(),0);
    }
}

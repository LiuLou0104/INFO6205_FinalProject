package Business.Simulation;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class OnePathogenSimuTest {

    @Test
    public void calcInfectedUnits() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        assertEquals(0,onePathogenSimu.calcInfectedUnits());
        onePathogenSimu.area[1][1].setInfectNum(1);
        assertEquals(1,onePathogenSimu.calcInfectedUnits());
    }

    @Test
    public void calcInfectedNum() {
        OnePathogenSimu onePathogenSimu = new OnePathogenSimu();
        assertEquals(0,onePathogenSimu.calcInfectedNum(),0);
        onePathogenSimu.area[1][1].setInfectNum(5);
        onePathogenSimu.area[1][2].setInfectNum(2);
        assertEquals(7,onePathogenSimu.calcInfectedNum(),0);
    }

    @Test
    public void currToDown() {
    }

    @Test
    public void currToDownR() {
    }

    @Test
    public void currToRight() {
    }

    @Test
    public void currToRightR() {
    }

    @Test
    public void currToRightBottom() {
    }

    @Test
    public void currToRightBottomR() {
    }

    @Test
    public void currToUp() {
    }

    @Test
    public void currToUpR() {
    }

    @Test
    public void currToRightTop() {
    }

    @Test
    public void currToRightTopR() {
    }

    @Test
    public void currToLeft() {
    }

    @Test
    public void currToLeftR() {
    }

    @Test
    public void currToLeftBottom() {
    }

    @Test
    public void currToLeftBottomR() {
    }

    @Test
    public void currToLeftTop() {
    }

    @Test
    public void currToLeftTopR() {
    }

    @Test
    public void calcOilSpreadAtTopLeftCorner() {
    }

    @Test
    public void calcOilSpreadAtLeftBottom() {
    }

    @Test
    public void calcOilSpreadAtTopRightCorner() {
    }

    @Test
    public void calcOilSpreadAtRightBottom() {
    }

    @Test
    public void calcOilSpread() {
    }
}

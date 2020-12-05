package Business.Simulation;

import Business.Report.Chart;
import Business.Report.ChartDirectory;

import java.util.ArrayList;
import java.util.List;

public class PageSimu {
    private List<OnePathogenSimu> onePathogenSimuList;
    private ChartDirectory chartDirectory;

    public PageSimu(){
        onePathogenSimuList = new ArrayList<>();
        chartDirectory = new ChartDirectory();
    }
    public void add(OnePathogenSimu onePathogenSimu){
        onePathogenSimuList.add(onePathogenSimu);
    }

    public void addChart(Chart chart) {
        this.chartDirectory.add(chart);
    }

    public List<OnePathogenSimu> getOnePathogenSimuList() {
        return onePathogenSimuList;
    }
    public void setOnePathogenSimuList(List<OnePathogenSimu> onePathogenSimuList) {
        this.onePathogenSimuList = onePathogenSimuList;
    }
}

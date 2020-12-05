package Business.Simulation;

import Business.Report.Chart;
import Business.Report.ChartDirectory;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageSimu {
    //Date toString
    private Date date;
    private List<OnePathogenSimu> onePathogenSimuList;
    private ChartDirectory chartDirectory;

    public PageSimu(){
        onePathogenSimuList = new ArrayList<>();
        date = new Date();
        chartDirectory = new ChartDirectory();
    }

    public String getDateString() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        return dateFormat.format(date);
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

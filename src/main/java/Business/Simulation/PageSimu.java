package Business.Simulation;

import Business.Report.Chart;
import Business.Report.ChartDirectory;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Business.Utilities.DateFormat.dateFormat1;

public class PageSimu {
    //Date toString
    private Date date;
    private List<OnePathogenSimu> onePathogenSimuList;

    public PageSimu(){
        date = new Date();
        onePathogenSimuList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return date.toString();
    }

    public String getDateString() {
        return date.toString();
    }

    public Date getDate() {
        return date;
    }

    public void add(OnePathogenSimu onePathogenSimu){
        onePathogenSimuList.add(onePathogenSimu);
    }

    public List<OnePathogenSimu> getOnePathogenSimuList() {
        return onePathogenSimuList;
    }
    public void setOnePathogenSimuList(List<OnePathogenSimu> onePathogenSimuList) {
        this.onePathogenSimuList = onePathogenSimuList;
    }
}

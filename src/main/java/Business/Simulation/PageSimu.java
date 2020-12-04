package Business.Simulation;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageSimu {
    //Date toString
    private Date date;
    private String dateString;
    private List<OnePathogenSimu> onePathogenSimuList;
    public PageSimu(){
        onePathogenSimuList = new ArrayList<>();
        date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        dateString = dateFormat.format(date);
    }

    public String getDateString() {
        return dateString;
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

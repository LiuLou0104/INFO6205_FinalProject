package Business.Simulation;

import java.util.ArrayList;
import java.util.List;

public class PageSimu {
    private List<OnePathogenSimu> onePathogenSimuList;
    public PageSimu(){
        onePathogenSimuList = new ArrayList<>();
    }

    public List<OnePathogenSimu> getOnePathogenSimuList() {
        return onePathogenSimuList;
    }

    public void setOnePathogenSimuList(List<OnePathogenSimu> onePathogenSimuList) {
        this.onePathogenSimuList = onePathogenSimuList;
    }
}

package main.java.Business.Simulation;

import java.util.ArrayList;
import java.util.List;

public class PageSimuDirectory {
    private List<PageSimu> pageSimuList;
    public PageSimuDirectory(){
        pageSimuList = new ArrayList<>();
    }

    public List<PageSimu> getPageSimuList() {
        return pageSimuList;
    }

    public void setPageSimuList(List<PageSimu> pageSimuList) {
        this.pageSimuList = pageSimuList;
    }
}

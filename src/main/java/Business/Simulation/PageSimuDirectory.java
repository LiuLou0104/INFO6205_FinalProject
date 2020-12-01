package Business.Simulation;

import java.util.ArrayList;
import java.util.List;

public class PageSimuDirectory {
    private List<PageSimu> pageSimuList;
    public PageSimuDirectory(){
        pageSimuList = new ArrayList<>();
    }
    public void add(PageSimu pageSimu){
        pageSimuList.add(pageSimu);
    }
    public List<PageSimu> getPageSimuList() {
        return pageSimuList;
    }
    public void setPageSimuList(List<PageSimu> pageSimuList) {
        this.pageSimuList = pageSimuList;
    }
}

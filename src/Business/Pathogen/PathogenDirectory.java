package Business.Pathogen;

import java.util.ArrayList;
import java.util.List;

public class PathogenDirectory {
    private List<Pathogen> pathogenList;
    public PathogenDirectory(){
        pathogenList = new ArrayList<>();
    }
    public void add(Pathogen pathogen){
        pathogenList.add(pathogen);
    }
    public List<Pathogen> getPathogenList() {
        return pathogenList;
    }
    public void setPathogenList(List<Pathogen> pathogenList) {
        this.pathogenList = pathogenList;
    }
}

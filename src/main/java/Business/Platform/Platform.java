package Business.Platform;

import Business.Pathogen.PathogenDirectory;
import Business.Simulation.PageSimuDirectory;

public class Platform {
    protected PageSimuDirectory pageSimuDirectory;
    protected PathogenDirectory pathogenDirectory;
    public Platform(){
        pageSimuDirectory = new PageSimuDirectory();
        pathogenDirectory = new PathogenDirectory();
    }

    public PageSimuDirectory getPageSimuDirectory() {
        return pageSimuDirectory;
    }

    public void setPageSimuDirectory(PageSimuDirectory pageSimuDirectory) {
        this.pageSimuDirectory = pageSimuDirectory;
    }

    public PathogenDirectory getPathogenDirectory() {
        return pathogenDirectory;
    }

    public void setPathogenDirectory(PathogenDirectory pathogenDirectory) {
        this.pathogenDirectory = pathogenDirectory;
    }
}

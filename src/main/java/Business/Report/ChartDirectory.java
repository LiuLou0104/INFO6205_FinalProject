package Business.Report;

import java.util.ArrayList;
import java.util.List;

public class ChartDirectory {
    
    private List<Chart> chartDirectory;

    public ChartDirectory() {
        this.chartDirectory = new ArrayList<>();
    }

    public void add(Chart chart) {
        this.chartDirectory.add(chart);
    }

    public List<Chart> getChartDirectory() {
        return this.chartDirectory;
    }

    public void setChartDirectory(List<Chart> chartDirectory) {
        this.chartDirectory = chartDirectory;
    }
}
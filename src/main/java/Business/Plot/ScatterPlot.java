package Business.Plot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;

public class ScatterPlot extends PlotAbstract {

    private String title;
    private String xAxisLable;
    private String yAxisLable;
    private boolean legend = true;
    private boolean tooltips = false;
    private boolean urls = false;

    public ScatterPlot() {
        super();
    }

    public ScatterPlot(String title, String xLable, String yLable) {
        this.title = title;
        this.xAxisLable = xLable;
        this.yAxisLable = yLable;
    }

    public JFreeChart Plot(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot(this.title,
                this.xAxisLable,  this.yAxisLable,
                dataset, PlotOrientation.VERTICAL,
                this.legend, this.tooltips, this.urls);

        return chart;
    }
}

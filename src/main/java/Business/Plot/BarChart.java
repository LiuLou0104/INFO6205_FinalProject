package Business.Plot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

public class BarChart {

    private String title;
    private String xAxisLable;
    private String yAxisLable;
    private boolean legend = true;
    private boolean tooltips = true;
    private boolean urls = false;

    public String getTitle() {
        return title;
    }

    public BarChart() {
        super();
    }

    public BarChart(String title, String xLable, String yLable) {
        this.title = title;
        this.xAxisLable = xLable;
        this.yAxisLable = yLable;
    }

    public JFreeChart Plot(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(this.title,
                this.xAxisLable,  this.yAxisLable,
                dataset, PlotOrientation.VERTICAL,
                this.legend, this.tooltips, this.urls);

        return chart;
    }
}

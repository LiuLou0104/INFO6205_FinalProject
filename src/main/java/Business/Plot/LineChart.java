package Business.Plot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChart extends PlotAbstract {
    private String title;
    private String xAxisLable;
    private String yAxisLable;
    private boolean legend = false;
    private boolean tooltips = false;
    private boolean urls = false;

    public String getTitle() {
        return title;
    }

    public LineChart() {
        super();
    }

    public LineChart(String title, String xLable, String yLable) {
        this.title = title;
        this.xAxisLable = xLable;
        this.yAxisLable = yLable;
    }

    public JFreeChart Plot(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(this.title,
                this.xAxisLable,  this.yAxisLable,
                dataset, PlotOrientation.VERTICAL,
                this.legend, this.tooltips, this.urls);

        return chart;
    }

    public JFreeChart Plot(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(this.title,
                this.xAxisLable,  this.yAxisLable,
                dataset, PlotOrientation.VERTICAL,
                this.legend, this.tooltips, this.urls);

        return chart;
    }
}

package Business.Report;

import java.util.Date;

public class Chart {

    private Date date;
    private String fileName;

    public Chart() {
        this.date = new Date();
    }

    public Chart(Date date, String fileName) {
        this.date = date;
        this.fileName = fileName;
    }
}

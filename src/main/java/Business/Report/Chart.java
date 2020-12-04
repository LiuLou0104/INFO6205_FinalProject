package Business.Report;

import java.util.Date;

public class Chart {

    private Date date;
    private String fileName;

    public Chart() {
        this.date = new Date();
    }

    public Chart(String title, String fileName) {
        this.date = new Date();
        this.fileName = fileName;
    }
}

package Business.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static void main(String args[]) {

        Date dNow = new Date( );
        System.out.println("当前时间为: " + dateFormat2(dNow));
    }

    public static String dateFormat1(Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return ft.format(date);
    }

    public static String dateFormat2(Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("DD HH-mm-ss");
        return ft.format(date);
    }

    public static String dateFormat3(Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("HH-mm-ss");
        return ft.format(date);
    }
}

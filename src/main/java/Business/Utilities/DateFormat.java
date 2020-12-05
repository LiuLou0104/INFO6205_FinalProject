package Business.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static void main(String args[]) {

        Date dNow = new Date( );
        System.out.println("当前时间为: " + DateFormat2(dNow));
    }

    public static String DateFormat1(Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return ft.format(date);
    }

    public static String DateFormat2(Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("DD hh:mm:ss");
        return ft.format(date);
    }

    public static String DateFormat3(Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
        return ft.format(date);
    }
}

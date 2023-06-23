package idv.kuan.libs.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimestampConverter {
    public static Timestamp GMTtoLocal(Timestamp gmt) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            final Date parse1 = formatter.parse(gmt.toString());

            formatter.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getID()));
            final String format2 = formatter.format(parse1);
            return Timestamp.valueOf(format2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }

}

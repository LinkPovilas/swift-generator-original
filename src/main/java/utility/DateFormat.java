package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {

    public String getDate(String date, String datePattern) {
        LocalDate localDate = LocalDate.parse(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        return formatter.format(localDate);
    }
}

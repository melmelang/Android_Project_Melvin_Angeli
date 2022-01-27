package be.ehb.boodschapen.model.util;


import androidx.room.TypeConverter;

import org.threeten.bp.LocalDate;

public class DateConverter {
    @TypeConverter
    public static String dateToString(LocalDate date){
        return (date == null)? null : date.toString();
    }

    @TypeConverter
    public static LocalDate stringToDate(String dateString){
        return (dateString == null)? null : LocalDate.parse(dateString);
    }
}

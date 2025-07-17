package com.lms.commonlib.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeUtils {
    public static LocalDate getNextDayFrom(LocalDate baseDate, DayOfWeek targetDay) {
        int currentDayValue = baseDate.getDayOfWeek().getValue(); // 1 (Mon) to 7 (Sun)
        int targetDayValue = targetDay.getValue();                // 1 (Mon) to 7 (Sun)

        int daysToAdd = (targetDayValue - currentDayValue + 7) % 7;
        daysToAdd = daysToAdd == 0 ? 7 : daysToAdd; // If same day, move to next week's same day

        return baseDate.plusDays(daysToAdd);
    }
}

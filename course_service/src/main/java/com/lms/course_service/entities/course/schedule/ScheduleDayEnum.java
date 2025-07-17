package com.lms.course_service.entities.course.schedule;

public enum ScheduleDayEnum {
    SUNDAY(7),MONDAY(1),TUESDAY(2),WEDNESDAY(3),THURSDAY(4),FRIDAY(5),SATURDAY(6)
    private final int value;

    ScheduleDayEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // Reverse mapping: int -> ScheduleDay
    public static ScheduleDayEnum fromValue(int value) {
        for (ScheduleDayEnum day : ScheduleDayEnum.values()) {
            if (day.getValue() == value) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid value for ScheduleDay: " + value);
    }
}

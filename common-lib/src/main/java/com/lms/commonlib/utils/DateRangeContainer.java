package com.lms.commonlib.utils;

import com.lms.commonlib.annotations.DateRangeValid;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


public interface DateRangeContainer {
  public LocalDate getStartDate();
  public LocalDate getEndDate();
}

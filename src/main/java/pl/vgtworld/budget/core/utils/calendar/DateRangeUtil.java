package pl.vgtworld.budget.core.utils.calendar;

import java.util.Calendar;
import java.util.Date;

public final class DateRangeUtil {

	private DateRangeUtil() {
	}

	public static MonthRangeDto findFirstAndLastDayOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1, 0, 0, 0);
		Date startDate = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDate = calendar.getTime();
		return new MonthRangeDto(startDate, endDate);
	}

}

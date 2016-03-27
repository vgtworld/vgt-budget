package pl.vgtworld.budget.core.utils.calendar;

import java.util.Date;

public class MonthRangeDto {

	private Date startDate;

	private Date endDate;

	public MonthRangeDto(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

}

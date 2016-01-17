package pl.vgtworld.budget.core.jsptags.date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeAgoTag extends SimpleTagSupport {

	private static final long SECOND = 1000;
	private static final long MINUTE = SECOND * 60;
	private static final long HOUR = MINUTE * 60;
	private static final long DAY = HOUR * 24;
	private static final long WEEK = DAY * 7;
	private static final long MONTH = DAY * 30;
	private static final long YEAR = DAY * 365;

	private Date since;

	public void setSince(Date since) {
		this.since = since;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		if (since == null) {
			out.print("<span>N/A</span>");
			return;
		}
		out.print(calculateDifference(since, new Date()));
	}

	private static String calculateDifference(Date start, Date end) {
		long difference = end.getTime() - start.getTime();
		if (difference > YEAR) {
			return calculateDifference(start, difference, YEAR, "year");
		}
		if (difference > MONTH) {
			return calculateDifference(start, difference, MONTH, "month");
		}
		if (difference > WEEK) {
			return calculateDifference(start, difference, WEEK, "week");
		}
		return calculateDifferenceAsDayOrSmaller(start, difference);
	}

	private static String calculateDifferenceAsDayOrSmaller(Date start, long difference) {
		if (difference > DAY) {
			return calculateDifference(start, difference, DAY, "day");
		}
		if (difference > HOUR) {
			return calculateDifference(start, difference, HOUR, "hour");
		}
		if (difference > MINUTE) {
			return calculateDifference(start, difference, MINUTE, "minute");
		}
		return calculateDifference(start, difference, SECOND, "second");
	}

	private static String calculateDifference(Date startDate, long difference, long step, String stepName) {
		long count = difference / step;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return buildHtml(Long.toString(count) + " " + stepName + (count > 1 ? "s" : ""), sdf.format(startDate));
	}

	private static String buildHtml(String timeAgoFormat, String datetimeFormat) {
		return "<span title=\"" + datetimeFormat + "\">" + timeAgoFormat + " ago</span>";
	}

}

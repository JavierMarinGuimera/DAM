package files;

import java.io.File;
import java.io.FileFilter;
import java.util.Calendar;
import java.util.Date;

public class MyFilter implements FileFilter {
	private static final Calendar CALENDAR = Calendar.getInstance();
	
	private Date date;

	public MyFilter(Date date) {
		this.date = date;
	}
	
	public Date getSufix() {
		return date;
	}

	public void setSufix(Date date) {
		this.date = date;
	}

	@Override
	public boolean accept(File file) {
		if (file != null) {
			CALENDAR.setTimeInMillis(file.lastModified());

			if (date.before(CALENDAR.getTime())) {
				return true;
			}
		}
		return false;
	}
}

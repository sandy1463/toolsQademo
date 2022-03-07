//package com.pages;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//
//
//public class DateUtils {
//	static String[] monthName = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
//			"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
//	public static String sMonthNamePostSubtractionOfXdays;
//	static String sYearNamePostSubtractionOfXdays;
//
//	/**
//	 * Returns the System date as String
//	 * 
//	 * @return system date
//	 */
//	public static String getSystemDate() {
//		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//		Date date = new Date();
//		String Date = dateFormat.format(date);
//		return Date;
//	}
//
//	/**
//	 * Returns month name with respect to the number of days prior to current
//	 * day.()
//	 * @param iReducedDays
//	 * @Example let's say current date is August 2, if given iReducedDays as 4
//	 *          then the method returns the month name 'July' as the month
//	 *          changed when reduced 4 days.
//	 * @return
//	 */
//	public static String getMonthMinusXdays(int iReducedDays) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DAY_OF_MONTH, -iReducedDays);
//		Date d = calendar.getTime();
//		ArrayList<String> sMonthName = new ArrayList<String>(
//				Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
//		int iMonthIndex = sMonthName.indexOf(String.valueOf(d).substring(4, 7));
//		return String.valueOf(iMonthIndex);
//	}
//	
//	/**
//	 * Returns Current day as Integer
//	 * 
//	 * @return
//	 */
//	public static int getCurrentDayAsInteger() {
//		Calendar cal = Calendar.getInstance();
//		Integer iMonth = cal.get(Calendar.DAY_OF_MONTH);
//		return iMonth;
//	}
//
//	/**
//	 * Returns the Current year in String format
//	 * 
//	 * @return
//	 */
//	public static String getCurrentYear() {
//		Calendar cal = Calendar.getInstance();
//		Integer iYear = cal.get(Calendar.YEAR);
//		return iYear.toString();
//	}
//
//	/**
//	 * Returns Current day in String format
//	 * 
//	 * @return string
//	 */
//	public static String getCurrentDayAsString() {
//		Calendar cal = Calendar.getInstance();
//		Integer iMonth = cal.get(Calendar.DAY_OF_MONTH);
//		String s = iMonth.toString();
//		return s;
//	}
//
//	/**
//	 * Returns name of the month after adding N weeks to current month
//	 * 
//	 * @param noOfWeeks
//	 * @return
//	 */
//	public static String getMonthAfterNWeeks(int noOfWeeks) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.WEEK_OF_MONTH, noOfWeeks);
//		Date d = calendar.getTime();
//		System.out.println(d);
//		return String.valueOf(d).substring(4, 7);
//
//	}
//
//	/**
//	 * Returns date after adding N weeks to current date
//	 * 
//	 * @param noOfWeeks
//	 * @return
//	 */
//	public static String getDateAfterNWeeks(int noOfWeeks) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.WEEK_OF_MONTH, noOfWeeks);
//		Date d = calendar.getTime();
//		if (!String.valueOf(d).substring(8, 10).startsWith("0")) {
//			return String.valueOf(d).substring(8, 10);
//		} else {
//			return (String.valueOf(d).substring(8, 10).substring(1));
//		}
//
//	}
//
//	/**
//	 * Returns Month name after subtracting X days
//	 * 
//	 * @param iReducedDays
//	 * @return
//	 */
//	public static String getDatePostSubtractionOfXdays(int iReducedDays) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DAY_OF_MONTH, -iReducedDays);
//		Date d = calendar.getTime();
//		sMonthNamePostSubtractionOfXdays = String.valueOf(d).substring(4, 7);
//		sYearNamePostSubtractionOfXdays = String.valueOf(d).substring(24,
//				String.valueOf(d).length());
//		if (!String.valueOf(d).substring(8, 10).startsWith("0")) {
//			return String.valueOf(d).substring(8, 10);
//		} else {
//			return (String.valueOf(d).substring(8, 10).substring(1));
//		}
//	}
//
//	/**
//	 * Returns Month name after subtracting X days
//	 * 
//	 * @param iReducedDays
//	 * @return
//	 */
//	public static String getMonthNamePostSubtractionOfXdays(int iReducedDays) {
//		sMonthNamePostSubtractionOfXdays = null;
//		getDatePostSubtractionOfXdays(iReducedDays);
//		return sMonthNamePostSubtractionOfXdays;
//	}
//
//	/**
//	 * Returns year name after subtracting X days
//	 * 
//	 * @param iReducedDays
//	 * @return
//	 */
//	public static String getYearNamePostSubtractionOfXdays(int iReducedDays) {
//		sYearNamePostSubtractionOfXdays = null;
//		getDatePostSubtractionOfXdays(iReducedDays);
//		return sYearNamePostSubtractionOfXdays;
//	}
//   
//	/**
//	 * Returns current month name as string variable
//	 * @return
//	 */
//	public static String getCurrentMonthName() {
//		Calendar cal = Calendar.getInstance();
//		Integer iMonth = cal.get(Calendar.MONTH);
//		String month = monthName[iMonth];
//		return month;
//	}
//
//	/**
//	 * Returns Index value of Current month in String format
//	 * 
//	 * @return {@link String}
//	 */
//	public static String getCurrentMonthIndexValueString() {
//		Calendar cal = Calendar.getInstance();
//		Integer iMonth = cal.get(Calendar.MONTH);
//
//		return iMonth.toString();
//	}
//
//	/**
//	 * Returns index value of current month in integer format
//	 * 
//	 * @return {@link Integer}
//	 */
//	public static Integer getCurrentMonthIndexValueInt() {
//		Calendar cal = Calendar.getInstance();
//		Integer iMonth = cal.get(Calendar.MONTH);
//		return iMonth;
//	}
//
//	/**
//	 * Returns time stamp
//	 * 
//	 * @return string
//	 */
//	public static String getTimeStamp() {
//		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String timestamp = sdf.format(cal.getTime());
//		return timestamp;
//	}
//
//	/**
//	 * Returns time stamp without spaces
//	 * 
//	 * @return
//	 */
//	public static String getTimeStampNoSpaces() {
//		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String timestamp = sdf.format(cal.getTime());
//		timestamp = timestamp.replaceAll("[^\\d.]", "");
//		return timestamp;
//	}
//
//	/**
//	 * Returns System date suitable to add as image name
//	 * 
//	 * @return string
//	 */
//	public static String getTimestampforImage() {
//		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss_dd-MM-yyyy");
//		String timestamp = sdf.format(cal.getTime());
//		return timestamp.substring(0, 8) + "_on_"
//				+ timestamp.substring(9, timestamp.length());
//	}
//
//	/**
//	 * Converts and returns the date to new date format
//	 * 
//	 * @param date
//	 * @param newDateformat
//	 * @return
//	 */
//	public static String convertFormatOfDate(String date, String newDateformat) {
//
//		final String OLD_FORMAT = "dd/MM/yyyy";
//		final String NEW_FORMAT = newDateformat;
//		String newDateString = null;
//
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
//			Date d = sdf.parse(date);
//
//			sdf.applyPattern(NEW_FORMAT);
//			newDateString = sdf.format(d);
//			System.out.println("Date before conversion : " + date);
//			System.out.println("Date after conversion : " + newDateString);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return newDateString;
//
//	}
//
//	/**
//	 * Converts Date format to new format specified
//	 * 
//	 * @param date
//	 * @param sOldFormat
//	 * @param newDateformat
//	 * 
//	 */
//	public static String convertFormatOfDate(String date, String sOldFormat,
//			String newDateformat) {
//
//		final String OLD_FORMAT = sOldFormat;
//		final String NEW_FORMAT = newDateformat;
//		String newDateString = null;
//
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
//			System.out.println("Date before conversion : " + date);
//			Date d = sdf.parse(date);
//
//			sdf.applyPattern(NEW_FORMAT);
//			newDateString = sdf.format(d);
//			System.out.println("Date after conversion : " + newDateString);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return newDateString;
//
//	}
//
//
//
//	/**
//	 * Returns the last day of month
//	 * 
//	 * @return string
//	 */
//	public static String getLastDayOfMonth() {
//		Calendar cal = Calendar.getInstance();
//		Integer maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//		return maxDay.toString();
//	}
//
//	/**
//	 * Returns the Previous month name
//	 * 
//	 * @return
//	 */
//	public static String getPreviousMonthName() {
//		Calendar cal = Calendar.getInstance();
//		Integer iMonth = cal.get(Calendar.MONTH);
//
//		if (iMonth == 0) {
//			iMonth = 12;
//		}
//
//		Integer nextMonth = iMonth - 1;
//		String month = monthName[nextMonth];
//		return month;
//	}
//
//	/**
//	 * Returns the next month name
//	 * 
//	 * @return
//	 */
//	public static String getNextMonthName() {
//
//		Calendar cal = Calendar.getInstance();
//		Integer iMonth = cal.get(Calendar.MONTH);
//
//		if (iMonth == 11) {
//			iMonth = -1;
//		}
//
//		Integer nextMonth = iMonth + 1;
//		String month = monthName[nextMonth];
//		return month;
//	}
//
//	/**
//	 * Returns current Date minus specified no of days
//	 * 
//	 * @param iReducedDays
//	 * @return String
//	 */
//	public static String getCurrentDateMinusXdays(int iReducedDays) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.DAY_OF_MONTH, -iReducedDays);
//		Date d = calendar.getTime();
//
//		if (!String.valueOf(d).substring(8, 10).startsWith("0")) {
//			return String.valueOf(d).substring(8, 10);
//		} else {
//			return (String.valueOf(d).substring(8, 10).substring(1));
//		}
//	}
//
//	/**
//	 * Returns day of the current month
//	 * 
//	 * @param cal
//	 * @return
//	 */
//	public static String getDay(Calendar cal) {
//		int date = cal.get(Calendar.DAY_OF_MONTH);
//		return Integer.toString(date);
//	}
//
//	/**
//	 * Returns the current month name as String
//	 * 
//	 * @param cal
//	 * @return String monthName
//	 */
//	public static String getMonth(Calendar cal) {
//		String[] monthName = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
//				"Aug", "Sep", "Oct", "Nov", "Dec" };
//
//		Integer iMonth = cal.get(Calendar.MONTH);
//
//		String month = monthName[iMonth];
//		return month;
//	}
//
//}

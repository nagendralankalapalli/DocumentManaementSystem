package com.example.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;

@Component
@Log4j2
public class CommonUtil {

	//private log log = log.getlog(CommonUtil.class);
//To capture the IP address of device
	public String  getDeviceIPAddress() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
       // System.out.println("IP Address:- " + inetAddress.getHostAddress());
        //System.out.println("Host Name:- " + inetAddress.getHostName());
        
        return inetAddress.getHostAddress();
    }
	

// String to Date formatter into dd-mm-yyyy	
	public String  dateFormatter(String dateObj){
		log.info("==> Entering CommonUtil - dateFormatter method---");
		
		Date date1 = null;
		Date formattedDate = null;
		 try {
			 if((dateObj.substring(2, 3)).equals("-"))
			 {
				 date1=new SimpleDateFormat("dd-mm-yyyy").parse(dateObj);
			 }
			 else  if((dateObj.substring(2, 3)).equals("/"))
			 {
				 date1=new SimpleDateFormat("dd/mm/yyyy").parse(dateObj);
			 }
			 else  if((dateObj.substring(2, 3)).equals("."))
			 {
				 date1=new SimpleDateFormat("dd.mm.yyyy").parse(dateObj);
			 }
			 else  if((dateObj.substring(4, 5)).equals("-"))
			 {
				 date1=new SimpleDateFormat("yyyy-mm-dd").parse(dateObj);
			 }
			 else  if((dateObj.substring(4, 5)).equals("/"))
			 {
				 date1=new SimpleDateFormat("yyyy/mm/dd").parse(dateObj);
			 }
			 else  if((dateObj.substring(4, 5)).equals("."))
			 {
				 date1=new SimpleDateFormat("yyyy.mm.dd").parse(dateObj);
			 }
			 if((dateObj.substring(1, 2)).equals("-"))
			 {
				 date1=new SimpleDateFormat("dd-mm-yyyy").parse(dateObj);
			 }
			 else  if((dateObj.substring(1, 2)).equals("/"))
			 {
				 date1=new SimpleDateFormat("dd/mm/yyyy").parse(dateObj);
			 }
			 else  if((dateObj.substring(1, 2)).equals("."))
			 {
				 date1=new SimpleDateFormat("dd.mm.yyyy").parse(dateObj);
			 }
			 
			 SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
			 dateObj=formatter.format(date1); 
			 
			// formattedDate = new SimpleDateFormat("yyyy-mm-dd").parse(dateObj);
			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("==> CommonUtil - dateFormatter method:" + e);
			//e.printStackTrace();
		} 
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		 
		//System.out.println("Formatted Date Obj :"+ dateObj);
		log.info("==> CommonUtil - dateFormatter method-Formatted date: " + dateObj);
		return dateObj;
		
	}
	
	
	//Age calculator from DOB
	
	/*
	public int getAgeFromDOB(String DOB)
	{
		log.info("==> Entering CommonUtil - getAgeFromDOB method---");
		int age = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date currentDate= new Date();
			String newCurrentDate = formatter.format(currentDate); 
			try {
				Date old = formatter.parse(DOB);
	            Date newOne = formatter.parse(newCurrentDate);
	            long difference_In_Time
                = old.getTime() - newOne.getTime();
	            long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
	          //long  positiveNumber = difference_In_Years * (-1);
	            long  positive = difference_In_Years * (-1);
		        Long l= new Long(positive);  
		        int positiveNumber=l.intValue(); 
		        age = positiveNumber;
			}
			catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
			log.info("==> CommonUtil - getAgeFromDOB method-calculated Age: " + age);
			return age;
	
	}
	
	*/
	
	
		//Age calculator from DOB
	
		public int getAgeFromDOB(String DOB) throws ParseException 
		{
			log.info("==> Entering CommonUtil - getAgeFromDOB method---");
			int age = 0;   
	     
	      //Converting String to Date
	      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	      Date date = formatter.parse(DOB);
	      //Converting obtained Date object to LocalDate object
	      Instant instant = date.toInstant();
	      ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
	      LocalDate givenDate = zone.toLocalDate();
	      //Calculating the difference between given date to current date.
	      Period period = Period.between(givenDate, LocalDate.now());
	      age = period.getYears();
	     // System.out.print(period.getYears()+" years "+period.getMonths()+" and "+period.getDays()+" days");
	      log.info("==> CommonUtil - getAgeFromDOB method-calculated Age: " + age);
	      return age;
	   }
	
	
	//To get remote IP address
	public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
	
	
	//Review the client’s HTTP request header, and try to identify where the IP address is stored.
	public Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key, value);
        }

        return result;
    }
	
	public boolean isValidDate(String dateStr) {
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			df.setLenient(false);
			df.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
}




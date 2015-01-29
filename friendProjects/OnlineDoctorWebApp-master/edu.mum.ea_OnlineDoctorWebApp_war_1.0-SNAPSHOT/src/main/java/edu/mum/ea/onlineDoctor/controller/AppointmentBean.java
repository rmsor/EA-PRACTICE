/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.onlineDoctor.controller;


import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author HailelulLakew
 */
@Named("AppointmentBn")
@SessionScoped
public class AppointmentBean {

    /**
     * Creates a new instance of AppointmentBean
     */
    public AppointmentBean() {
    }
    
    
    public String schedule() throws SQLException {
//        String[] timeParts = chosenTime.split(":");
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//
//        cal.set(Calendar.HOUR, Integer.parseInt(timeParts[0]));
//        cal.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
//
//        appointment.setDateTime(cal.getTime());
////        appointmentService.setAppointment(appointment);
////        appointmentService.addAppointment();
//        appointment.setId(null);
//        appointment.setAccountId(loginBn.getAccount().getId());
//        ejbAppointmentFacade.create(appointment);

        fillTimes();
        return "viewAppointments";
    }

    public void fillTimes() {
       
       // ArrayList<StartTime> examTimes = getExamTimes(date).getExamTimes();

        //times.clear();
        DecimalFormat formatter = new DecimalFormat("00");

        Calendar cal = Calendar.getInstance();
       // cal.setTime(date);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date()); 
        if (cal.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) {
            return;
        } else if (cal.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
            if (cal.get(Calendar.MONTH) < cal2.get(Calendar.MONTH)) {
                return;
            } else if (cal.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                if (cal.get(Calendar.DAY_OF_MONTH) < cal2.get(Calendar.DAY_OF_MONTH)) {
                    return;
                }
            }
        }
//        for (StartTime examTime : examTimes) {
//            String time2 = formatter.format(examTime.getHour()) + ":" + formatter.format(examTime.getMinute());
//            times.add(new SelectItem(time2, time2));
//        }

    }

//    public ExamTimes getExamTimes(Date date) {
//        ArrayList<Date> occupiedTimes = ejbAppointmentFacade.getOccupiedTimes(date);
//
//        ExamTimes examTimes = new ExamTimes();
//        //examTimes.getExamTimes().clear();
//        for (int i = 0; i < occupiedTimes.size(); i++) {
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(occupiedTimes.get(i));
//            examTimes.removeTime(cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
//            //examTimes.addTime(cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
//        }
//
//        return examTimes;
//    }

    public void retrieveAppointments() {
//        datetimes.clear();
//        account.setId(loginBn.getAccount().getId());//to be changed
//        appointments = ejbAppointmentFacade.getAppointments(account);
//        Iterator it = appointments.iterator();
//        while (it.hasNext()) {
//            //AppointmentEntity appointment = (AppointmentEntity) it.next();
//            //CustomDate customDate = new CustomDate();
//            
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(appointment.getDateTime());
//            customDate.setAppointmentDate((cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR)) ;
//            customDate.setTime(cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE));
//            datetimes.add(customDate);            
        }
    }

    
    
    


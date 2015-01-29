/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.ea.onlineDoctor.controller;


import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.facade.AppointmentFacade;
import edu.mum.ea.onlineDoctor.service.AppointmentServiceBean;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author HailelulLakew
 */
@ManagedBean
@RequestScoped
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@Named("makeAppointmentBn")
@SessionScoped
public class MakeAppointmentBn implements Serializable {
private Date date = new Date();
 private ArrayList<SelectItem> startTimes = new ArrayList();
 private ArrayList<SelectItem> endTimes = new ArrayList();
 private Appointment appointment = new Appointment();
 
 @EJB
 AppointmentServiceBean appointmentServiceBean;
 private String chosenTime;

public void fillTimes() {
       
        ArrayList<StartTime> examTimes = getExamTimes(date).getExamTimes();
         //ArrayList<SelectItem> times = new ArrayList();
        startTimes.clear();
        endTimes.clear();
        DecimalFormat formatter = new DecimalFormat("00");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

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
        for (StartTime examTime : examTimes) {
            String time2 = formatter.format(examTime.getHour()) + ":" + formatter.format(examTime.getMinute());
            startTimes.add(new SelectItem(time2, time2));
            endTimes.add(new SelectItem(time2, time2));
            
        }

    }

public ExamTimes getExamTimes() {
    return new ExamTimes();
   
}


public ExamTimes getExamTimes(Date date) {
        //ArrayList<Date> occupiedTimes= AppointmentFacade.getOccupiedTimes(date);

       ExamTimes examTimes = new ExamTimes();
//        startConversations.getExamTimes().clear();
//        for (int i = 0; i < occupiedTimes.size(); i++) {
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(occupiedTimes.get(i));
//            startConversations.removeTime(cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
//            startConversations.addTime(cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE));
//        }

        return examTimes;
    }


public String schedule() throws SQLException {
        String[] timeParts = chosenTime.split(":");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR, Integer.parseInt(timeParts[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
        Date date=cal.getTime();
        long dateInLong = date.getTime();
        appointment.setStartTime(dateInLong);
        appointment.setAppointmentDate(cal.getTime());
//        appointmentService.setAppointment(appointment);
//        appointmentService.addAppointment();
        appointment.setId(null);
        //appointment.setAccountId(loginBn.getAccount().getId());
        appointmentServiceBean.create(appointment);

        fillTimes();
        return "appointmentConfirmation";                
    }
/**
     * @return the times
     */
    public ArrayList<SelectItem> getStartTimes() {
        return startTimes;
    }

    /**
     * @param times the times to set
     */
    public void setTimes(ArrayList<SelectItem> times) {
        this.startTimes = times;
    }

    /**
     * @return the timesEnd
     */
    public ArrayList<SelectItem> getEndTimes() {
        return endTimes;
    }

    /**
     * @param timesEnd the timesEnd to set
     */
    public void setTimesEnd(ArrayList<SelectItem> timesEnd) {
        this.endTimes = timesEnd;
    }


public class ExamTimes {

    private ArrayList<StartTime> startConversations = new ArrayList();
    

    public ExamTimes() {
        startConversations.add(new StartTime(8, 30));
        startConversations.add(new StartTime(9, 00));
        startConversations.add(new StartTime(9, 30));
        startConversations.add(new StartTime(10, 00));
        startConversations.add(new StartTime(10, 30));
        startConversations.add(new StartTime(11, 00));
        startConversations.add(new StartTime(11, 30));
        startConversations.add(new StartTime(1, 00));
        startConversations.add(new StartTime(1, 30));
        startConversations.add(new StartTime(2, 00));
        startConversations.add(new StartTime(2, 30));
        startConversations.add(new StartTime(3, 00));
        startConversations.add(new StartTime(3, 30));
        startConversations.add(new StartTime(4, 00));
    }

    public ArrayList<StartTime> getExamTimes() {
        return startConversations;
    }

    public void setExamTimes(ArrayList<StartTime> examExamTimes) {
        this.startConversations = examExamTimes;
    }

    public void addTime(int hour, int minute) {
        StartTime startTime = new StartTime(hour, minute);
        startConversations.add(startTime);
    }

    public void removeTime(int hour, int minute) {

        Iterator<StartTime> it = startConversations.iterator();
        while (it.hasNext()) {
            StartTime time = it.next();
            if (time.getHour() == hour && time.getMinute() == minute) {
                it.remove();
            }
        }

    }

}






    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getTime() {
        return chosenTime;
    }

    public void setTime(String time) {
        this.chosenTime = time;
    }
    
    
    public class StartTime {

    private int hour;
    private int minute;

    public StartTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}

}


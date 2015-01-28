/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.onlineDoctor.facade;

import edu.mum.ea.onlineDoctor.entity.Appointment;
import edu.mum.ea.onlineDoctor.entity.Doctor;
import edu.mum.ea.onlineDoctor.entity.Patient;
import edu.mum.ea.onlineDoctor.service.AppointmentServiceBean;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.scene.input.KeyCode.T;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stella
 */
@Stateless
public class AppointmentFacade extends AbstractFacade<Appointment> {

    @PersistenceContext(unitName = "edu.mum.ea_OnlineDoctorWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Resource
    TimerService timerService;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentFacade() {
        super(Appointment.class);
    }

    public List<Appointment> findPatientAppointments(Patient patient) {

        Query query = em.createQuery("SELECT a FROM Appointment a WHERE a.patientInAppointment=:patient", Appointment.class);

        List<Appointment> apps;
        query.setParameter("patient", patient);
        apps = (List<Appointment>) query.getResultList();

        return apps;
    }

    public List<Appointment> findDoctorAppointments(Doctor doctor) {

        Query query = em.createQuery("SELECT a FROM Appointment a WHERE a.doctor=:doctor", Appointment.class);

        List<Appointment> apps;
        query.setParameter("doctor", doctor);
        apps = (List<Appointment>) query.getResultList();

        return apps;
    }

    public void create(Appointment entity) {

        getEntityManager().persist(entity);

//        ScheduleExpression appointmentActivation = new ScheduleExpression();
//        Calendar cal = null;
//
//        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//        Date date;
//        try {
//            date = (Date) formatter.parse((entity.getAppointmentDate()).toString());
//            cal = Calendar.getInstance();
//            cal.setTime(date);
//         //   appointmentActivation.dayOfMonth(cal.get(Calendar.DAY_OF_MONTH));
////        appointmentActivation.month(cal.get(Calendar.MONTH));
////        appointmentActivation.year(cal.get(Calendar.YEAR));
////   
//            timerService.createCalendarTimer(appointmentActivation, new TimerConfig(entity, true));
//        } catch (ParseException ex) {
//            Logger.getLogger(AppointmentFacade.class.getName()).log(Level.SEVERE, null, ex);
//        }

        //appointmentServiceBean.create(entity);
    }

    @Timeout
    public void activateChatSession(Timer timer) {

        Appointment appointment = (Appointment) timer.getInfo();
    }

}

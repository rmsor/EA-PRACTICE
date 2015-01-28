/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Category;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author MDZahidul
 */
@Named
@RequestScoped
public class ChartView implements Serializable {

    private PieChartModel model;
    private Date toDate;
    private Date fromDate;
    private String tDate;
    private String fdate;

    @PersistenceContext(unitName = "OnlinePOSPU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    @PostConstruct
    public void init() {
        generatedummy();
    }

    public ChartView() {
        //model = new PieChartModel();
//        model.set("Brand 1", 540);
//        model.set("Brand 2", 325);
//        model.set("Brand 3", 702);
//        model.set("Brand 4", 421);
    }

    public PieChartModel getModel() {
        return model;
    }

    public void setModel(PieChartModel model) {
        this.model = model;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
        System.out.println(this.toDate);
        tDate = new SimpleDateFormat("yyyy-MM-dd").format(this.toDate);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ToDate", tDate);
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
        System.out.println(this.fromDate);

        fdate = new SimpleDateFormat("yyyy-MM-dd").format(this.fromDate);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("FromDate", fdate);

    }

    public String gettDate() {
        return tDate;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public void generatedummy() {
        model = new PieChartModel();
        model.set("Click the button to generate data", 0);
//        model.set("Brand 2", 325);
//        model.set("Brand 3", 702);
//        model.set("Brand 4", 421);
    }

    public void generateModel() {
        model = new PieChartModel();

        String sql = "SELECT p.category_id, count(*) FROM orderline ol,product p where ol.product_id=p.id and ol.order_id in(SELECT order_id FROM payment WHERE TRANSACTION_DATE>='" + (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("FromDate") + "' and TRANSACTION_DATE<='" + (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ToDate") + "' ) group by p.category_id";
        System.out.println("sql:... " + sql);
        Query q = em.createNativeQuery(sql);
        //q.setParameter("username",username);
        //q.setParameter("password", password);
        //return (Employee)q.getSingleResult();
        List<Object[]> res = q.getResultList();

        Map<Long, Long> resultMap = new HashMap<Long, Long>(res.size());
        for (Object[] result : res) {
            resultMap.put((Long) result[0], (Long) result[1]);
        }
        for (Long key : resultMap.keySet()) {
            System.out.println(key + " " + resultMap.get(key));

            String sql2 = "Select * from Category c where c.id=" + key;
            System.out.println("sql:... " + sql2);
            Query q2 = em.createNativeQuery(sql2, Category.class);
            Category res2 = (Category) q2.getSingleResult();
            System.out.println(res2.getCategory_name());

            model.set(res2.getCategory_name(), resultMap.get(key));
        }

    }

    public void exportReport() {

        generateCsvFile("D:\\Report.csv");

    }

    private void generateCsvFile(String ctestcsv) {

        try {
            FileWriter writer = new FileWriter(ctestcsv);

            writer.append("Product Name");
            writer.append(',');
            writer.append("Product category");
            writer.append('\n');

            writer.append("Dove");
            writer.append(',');
            writer.append("Cosmetics");
            writer.append('\n');

            writer.append("MotherBoard");
            writer.append(',');
            writer.append("Computer Accessories");
            writer.append('\n');

	    //generate whatever data you want
            writer.flush();
            writer.close();
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Your report exported sucessfully"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

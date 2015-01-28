/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import boundaries.CategoryFacade;
import boundaries.ProductFacade;
import boundaries.SupplierFacade;
import entities.Category;
import entities.Product;
import entities.Supplier;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author MdmRahman
 */
@Named
@RequestScoped
public class ProductMB implements Serializable {

    @EJB
    private SupplierFacade supplierFacade;
    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private ProductFacade productFacade;

    private Product product;
    private Long supID;
    private Long catID;
    private UploadedFile uploadedFile;
    private UploadedFile uploadedBatchFile;
    private List<Product> prds = new ArrayList<>();
    private double sellingPriceUpdate;
    private double taxPercentage;

    public ProductMB() {
        this.product = new Product();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getSupID() {
        return supID;
    }

    public void setSupID(Long supID) {
        this.supID = supID;
    }

    public Long getCatID() {
        return catID;
    }

    public void setCatID(Long catID) {
        this.catID = catID;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getUploadedBatchFile() {
        return uploadedBatchFile;
    }

    public void setUploadedBatchFile(UploadedFile uploadedBatchFile) {
        this.uploadedBatchFile = uploadedBatchFile;
    }

    public String addProduct() throws IOException {
        Supplier sup = (Supplier) this.supplierFacade.find(supID);
        this.product.setSupplier(sup);
        Category cat = (Category) this.categoryFacade.find(catID);
        this.product.setCategory(cat);
        this.taxPercentage = this.product.getCategory().getTax().getPercentage();
        this.sellingPriceUpdate = this.product.getSupplier_unit_price()+(this.product.getSupplier_unit_price()*(this.taxPercentage / 100));
        this.product.setSelling_price(sellingPriceUpdate);

        String fileSource = "C:\\Users\\MDZahidul\\Downloads\\" + uploadedFile.getFileName();
        String fileDestination = "C:\\Users\\MDZahidul\\Documents\\NetBeansProjects\\EPOS\\src\\main\\webapp\\resources\\images\\product_images\\" + uploadedFile.getFileName();
        Files.move(Paths.get(fileSource), Paths.get(fileDestination));
        this.product.setProduct_image(uploadedFile.getFileName());

        this.productFacade.create(product);

        this.product.setProduct_name(null);
        this.product.setProduct_desc(null);
        this.product.setProduct_type(null);
        this.product.setSupplier_unit_price(0.0);
        this.product.setQuantity(0);
//        this.product.setProduct_status(null);
//        this.product.setExpire_date(null);
        this.product.setProduct_image(null);
        return "product";
    }

    public List<Product> getProducts() {
        return this.productFacade.findAll();
    }

    public String uploadBatchFileAction() {
        //System.out.println(uploadedBatchFile.getFileName());
        String fileName = "C:\\Users\\MDZahidul\\Downloads\\" + uploadedBatchFile.getFileName();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(fileName));
            int flag = 0;
            while ((line = br.readLine()) != null) {
                flag++;
                // use comma as separator
                String[] products = line.split(cvsSplitBy);

                if (flag == 1) {
                    continue;
                }
                //0,1 in the string is column in the database.
                prds.add(new Product(products[0], products[1], products[2], Double.parseDouble(products[3]), Double.parseDouble(products[4]), Integer.parseInt(products[5]), products[6], Long.parseLong(products[7])));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //System.out.println("Done");
        saveDate(prds);
        return "product";
    }

    private void saveDate(List<Product> prds) {
        Iterator it = prds.iterator();
        while (it.hasNext()) {
            Product p = (Product) it.next();
            System.out.println(p.getCat_id());
            Category cat = (Category) this.categoryFacade.find(p.getCat_id());
            System.out.println(cat.getCategory_name());
            p.setCategory(cat);
            productFacade.create(p);
        }

    }
}

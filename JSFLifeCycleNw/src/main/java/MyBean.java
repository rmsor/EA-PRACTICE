import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

public class MyBean {

    // Init ----------------------------------------------------------------------------------------
    private HtmlInputText inputComponent;
    private String inputValue;
    private HtmlOutputText outputComponent;
    private String outputValue;
    private Collection<String> strCol = new ArrayList<String>();
    private Collection<String> strCol2 = new ArrayList<String>();

    private Collection<String> strCol3 = new ArrayList<String>();
    private Collection<String> strCol4 = new ArrayList<String>();

    public Collection<String> getStrCol4() {
        return strCol4;
    }

    public void setStrCol4(Collection<String> strCol4) {
        this.strCol4 = strCol4;
    }

    public Collection<String> getStrCol3() {
        return strCol3;
    }

    public void setStrCol3(Collection<String> strCol3) {
        this.strCol3 = strCol3;
    }

    // Constructors -------------------------------------------------------------------------------
    public MyBean() {
        strCol.add("r");
        strCol.add("s");
        strCol3.add("u");
        strCol3.add("g");
        strCol4.add("4");
        strCol4.add("5");

        log("constructed");
    }

    public Collection<String> getStrCol() {
        return strCol;
    }

    public void setStrCol(Collection<String> strCol) {
        this.strCol = strCol;
    }

    public Collection<String> getStrCol2() {

        return strCol2;
    }

    public void setStrCol2(Collection<String> strCol2) {
        this.strCol2 = strCol2;
    }

    // Actions ------------------------------------------------------------------------------------
    public void action() {
        outputValue = inputValue;
        log("succes");
    }

    // Getters ------------------------------------------------------------------------------------
    public HtmlInputText getInputComponent() {

        log(inputComponent);
        return inputComponent;
    }

    public String getInputValue() {
        log(inputValue);
        return inputValue;
    }

    public String getInpValue() {
        log(inputValue);
        return inputValue;
    }

    public HtmlOutputText getOutputComponent() {
        log(outputComponent);
        return outputComponent;
    }

    public String getOutputValue() {
        log(outputValue);

        return outputValue;
    }

    // Setters ---------------------------------------------------------------------
    public void setInputComponent(HtmlInputText inputComponent) {
        log("rajesh" + inputComponent.getValue());
        log(inputComponent);
        this.inputComponent = inputComponent;
    }

    public void setInputValue(String inputValue) {
        log(inputValue);
        log("update model called od commandbutton text check");
        this.inputValue = inputValue;
    }

    public void setOutputComponent(HtmlOutputText outputComponent) {
        log(outputComponent);
        this.outputComponent = outputComponent;
    }

    // Listeners ----------------------------------------------------------------------------------
    public void inputChanged(AjaxBehaviorEvent event) {
        UIInput inp = (UIInput) event.getComponent();
        System.out.println(inp.getValue());
        strCol2 = new ArrayList<String>();
        log(" Ajax to value change rajesh");
        for (Integer i = 0; i < 10; i++) {
            strCol2.add(inp.getValue() + i.toString());
        }
    }

    public void inputChanged(ValueChangeEvent event) {
        UIInput inp = (UIInput) event.getComponent();
        System.out.println(inp.getValue());
        strCol2 = new ArrayList<String>();
        log(" vale change loistener to value change rajesh");
        for (Integer i = 0; i < 10; i++) {
            strCol2.add(inp.getValue() + i.toString());
        }
    }

    // Helpers ------------------------------------------------------------------------------------
    private void log(Object object) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("MyBean " + methodName + ": " + object);
    }

    public void foractionListener1(ActionEvent e) {
        System.out.println("action event for id ");
        log("action event for id log " + e.getComponent());
        log(e.getComponent().getAttributes().get("username"));
    }
    private int count;

    public int getCount() {
        log("ajax output called");
        return count;

    }

    public void setCount(int count) {
        this.count = count;
        log("set count listener called");
    }

    public void increment() {
        count++;
        log("ajax listener called");
    }
    private String firstname;

    public String getFirstname() {
        log("get for first namecalled" + firstname);

        return firstname;

    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
        log("set update called" + firstname);
    }

    public List<String> complete(String name) {
        log("inside complete");
        List<String> results = new ArrayList<String>();
        results.add("rajesh");

        return results;
    }
    private String materialName;

    public String getMaterialName() {
        log("inside get complete for mat");
        return materialName;

    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
        log("inside set complete for mat");
    }

    public Integer getRowSpanSize() {

        return strCol.size();
    }

}

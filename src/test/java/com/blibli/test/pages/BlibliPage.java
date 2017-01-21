package com.blibli.test.pages;

import com.sun.xml.internal.ws.api.client.SelectOptimalEncodingFeature;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Managed;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

/**
 * Created by Yunaz on 1/17/2017.
 */
@DefaultUrl("http://www.blibli.com/")
public class BlibliPage extends PageObject{

    MobileAppsPage newtab;
    //login button
    private String login = "//body//*[@id='gdn-login-registrasi']";

    private String signup = "//body//*[@id='gdn-daftar']";

    private String btn_for_login ="//body//*[@id='gdn-submit-login']";

    private String nama_user = "//body//*[@id='loginEmail']";

    private String password_user = "//body//*[@id='loginPassword']";

    private String label = "//body//*[@id='gdnloginErrorLabel']";

    private String search_field = "//body//*[@id='autocomplete-wrapper']//input[@type='text']";

    private String search_button = "//body//*[@id='gdn-search-button']";

    private String search_result_found = "//body//*[@id='blibli-main-ctrl']/section/div/div[@id='catalogViewSection']";

    private String item = "//body//*[@id='catalogProductListContentDiv']/div[2]/div";

    private String recaptcha = ".//*[@id='recaptcha-anchor']//div[@class='recaptcha-checkbox-checkmark']";

    private String verifikasi_nanti = "//body//*[@id='gdn-pnv-later']";

    private String lanjutkan_verifikasi_nanti = "//body//*[@id='gdn-pnv-later-continue']";

    private String registration_popup = "//body//*[@id='gdn-registration-form']/div";

    private String the_signed_user = "//body//*[@id='gdn-already-login-label']/strong";

    private String profile_url = "https://www.blibli.com/member/profile";

    private String full_name = "//body//*[@id='gdn-profile-name']";

    private String day_birth = "//body//*[@id='gdn-profile-day']";

    private String month_birth = "//body//*[@id='gdn-profile-month']";

    private String year_birth = "//body//*[@id='gdn-profile-year']";

    private String number_phone = "//body//*[@id='gdn-profile-phone']";

    private String klik_pria = "//body//*[@id='profileForm']/div[5]/div/div/span[1]/input";

    private String klik_wanita = "//body//*[@id='profileForm']/div[5]/div/div/span[1]/input";

    private String simpan_profile = "//body//*[@id='gdn-profile-submit']";

    public void init(){
        WebDriver webDriver = getDriver();

        webDriver.navigate().to("http://www.blibli.com/");
    }

    public void open_new_tab(){
        WebDriver webDriver = getDriver();
        Actions action = new Actions(webDriver);

        WebElement body = webDriver.findElement(By.xpath("//body//*[a[@id='gdn-logo-blibli']]"));

        action.moveToElement(body).sendKeys(Keys.CONTROL, Keys.SHIFT).click(body).perform();

        ArrayList<String> newTab = new ArrayList<>(webDriver.getWindowHandles());

        webDriver.switchTo().window(newTab.get(1));
    }

    public void user_choose_to_login(){
        WebDriver webDriver = getDriver();
        WebElement btn_login = webDriver.findElement(By.xpath(login));
        btn_login.click();
    }

    public void closePopUp(){
        WebDriver webDriver = getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        try {
            WebElement container = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body//*[@class='insider-opt-in-notification-button-container']")));

            if(container.isDisplayed()){
                WebElement element = webDriver.findElement(By.xpath("//body//div[contains(text(),'Nanti saja')]"));
                element.click();
            }else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void enter_a_username(String name){
        WebElementFacade field_nama_user =  find(By.xpath(nama_user));

        field_nama_user.type(name);
    }

    public void enter_a_password(String pass){
        WebElementFacade field_password_user = find(By.xpath(password_user));

        field_password_user.type(pass);
    }

    public Boolean login_result(){
        WebElementFacade status = find(By.xpath(label));

        return status.isDisplayed();
    }

    public void login_now(){
        WebDriver webDriver = getDriver();
        WebElement loginButton = webDriver.findElement(By.xpath(btn_for_login));
        Actions action = new Actions(webDriver);


        /*String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";


        ((JavascriptExecutor)webdriver).executeScript(javaScript, loginButton);*/

        action.moveToElement(loginButton).perform();
        loginButton.click();
    }

    public void hovering(String menu, String submenu){
        WebDriver webDriver = getDriver();
        Actions action = new Actions(webDriver);
        WebElement hover_menu;
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        if(menu.equals("Mobile")){
            hover_menu = webDriver.findElement(By.xpath("//body//*[@id='mobile-app']/span"));
            String oldTab =  webDriver.getWindowHandle();
            action.moveToElement(hover_menu).moveToElement(webDriver.findElement(By.xpath("//body//*[@id='mobile-app']//li//*[div[contains(text(),'"+submenu+"')]]"))).click().build().perform();

            ArrayList<String> newTab = new ArrayList<String>(webDriver.getWindowHandles());
            newTab.remove(oldTab);

            if(waitForNewTab(webDriver,500)){
                webDriver.switchTo().window(newTab.get(0));
            }
        }else if(menu.equals("User")){
            hover_menu = webDriver.findElement(By.xpath(".//*[@id='gdn-already-login']"));

            action.moveToElement(hover_menu).moveToElement(webDriver.findElement(By.xpath("//body//*[@id='gdn-usermenu-box']/ul//li//a[contains(text(),'"+submenu+"')]"))).click().build().perform();
        }

    }

    public void scroll_the_page(int x, int y) throws Exception {
        WebDriver webDriver = getDriver();

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("window.scrollBy("+x+","+y+")", "");
    }

    public static boolean waitForNewTab(WebDriver driver,int timeout){
        boolean check = false;
        int count = 0;
        while(!check){
            try {
                Set<String> winHandle = driver.getWindowHandles();
                if(winHandle.size() > 1){
                    check = true;
                    return check;
                }
                Thread.sleep(1000);
                count++;
                if(count > timeout){
                    return check;
                }
            } catch (Exception e) {
            }
        }
        return check;
    }

    public Boolean checkHome(){
        WebDriver webDriver = getDriver();
        return webDriver.getCurrentUrl().equals("https://www.blibli.com");
    }

    public String search_result(String def){
        WebDriver webDriver = getDriver();
        WebElement isFound = null;
        try {
            isFound = webDriver.findElement(By.xpath(search_result_found));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(isFound.isDisplayed()){
                WebElementFacade search_object = find(By.xpath("//body//*[@id='catalogProductListContentDiv']//*[div[contains(text(),'"+def+"')]]"));

                if(!search_object.isDisplayed()){
                    WebElementFacade search_is_not_found = find(By.xpath(".//*[@id='blibli-main-ctrl']/section/div//section/span[@class='search-result']//h1[contains(text(),'"+def+"')]"));

                    search_object =  search_is_not_found;
                }
                return search_object.getText();
            }else{
                return "There's something wrong with your query";
            }
    }

    public Boolean search_not_found(){
        WebElement isFound = null;
        WebDriver webDriver = getDriver();
        try {
            isFound = webDriver.findElement(By.xpath("//body//*[@id='blibli-main-ctrl']/section/div//section"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return isFound.isDisplayed();
        }
    }

    public void is_searching(String search_terms){
        WebElementFacade to_search = find(By.xpath(search_field));

        to_search.type(search_terms);
    }

    public String getDefinitionsPage(String defPage){
        //WebDriver webDriver = getDriver();

        WebElementFacade definitionsListPage = find(By.xpath("//body//*[@id='catalogProductListContentDiv']//*[div[contains(text(),'"+defPage+"')]]"));

        return definitionsListPage.getText();
    }

    public void hit_enter_when_searching(){
        WebElementFacade to_search = find(By.xpath(search_field));

        to_search.sendKeys(Keys.RETURN);
    }

    public void hit_the_search_button(){
        WebDriver webDriver = getDriver();
        WebElement btn_search = webDriver.findElement(By.xpath(search_button));

        try {
            btn_search.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click_an_item(){
        WebElementFacade klikitem = find(By.xpath(item));
        klikitem.click();
    }

    public String getDefinitionsofDescription(String descriptiontxt){
        //WebDriver webDriver = getDriver();

        WebElementFacade descriptionList = find(By.xpath("//body//*[@id='blibli-main-ctrl']//section//div//section//h1[contains(text(),'"+descriptiontxt+"')]"));

        return descriptionList.getText();
    }

    public void click_recapthca(){
        WebElementFacade captcha = find(By.xpath(recaptcha));

        captcha.click();
    }

    public void verifikasi_nanti(){
        WebDriver webDriver = getDriver();
        WebElementFacade later = find(By.xpath(verifikasi_nanti));

        later.click();

        WebDriverWait wait = new WebDriverWait(webDriver,10);

        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lanjutkan_verifikasi_nanti)));

        popup.click();
    }

    public Boolean is_in_verification_page(){
        WebDriver webDriver = getDriver();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return webDriver.getTitle().equals("Halaman Verifikasi | Blibli.com");
        }
    }

    public void user_want_to_sign_up(){
        WebDriver webDriver =  getDriver();

        WebElement btn_sign_up = webDriver.findElement(By.xpath(signup));

        btn_sign_up.click();
    }

    public void insert_identity(String email, String pass){
        WebDriver webDriver = getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        WebElement popup_register = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(registration_popup)));

        if(popup_register.isDisplayed()){
            WebElementFacade email_address = find(By.xpath("//body//*[@id='registrationFormEmailAddress']"));
            WebElementFacade password_user = find(By.xpath("//body//*[@id='registrationFormPassword']"));
            WebElementFacade btn_daftar = find(By.xpath("//body//*[@id='gdn-submit-registration']"));

            email_address.type(email);
            password_user.type(pass);
            btn_daftar.click();
        }
    }

    public Boolean check_is_signed_in(){
        WebDriver webDriver = getDriver();

        WebElement is_signed =  webDriver.findElement(By.xpath(the_signed_user));

        return is_signed.isDisplayed();
    }

    public Boolean user_is_in_the_profile_page(){
        WebDriver webDriver = getDriver();

        return webDriver.getCurrentUrl().equals(profile_url);
    }

    public void fill_fulname(String fullname){
        WebElementFacade name = find(By.xpath(full_name));

        name.type(fullname);
    }

    public void fill_date_of_birth(String day, String month, String year){
        WebDriver driver = getDriver();

        Select dayBirth = new Select(driver.findElement(By.xpath(day_birth)));
        //dayBirth.deselectAll();
        dayBirth.selectByVisibleText(day);

        Select monthBirth = new Select(driver.findElement(By.xpath(month_birth)));
        //monthBirth.deselectAll();
        monthBirth.selectByVisibleText(month);

        Select yearBirth = new Select(driver.findElement(By.xpath(year_birth)));
        //yearBirth.deselectAll();
        yearBirth.selectByVisibleText(year);
    }

    public void fill_phone_number(String phone_number){
        WebElementFacade phone = find(By.xpath(number_phone));

        phone.type(phone_number);
    }

    public void select_gender(String gender){
        if(gender.equals("male")){
            WebElementFacade klikmale = find(By.xpath(klik_pria));
            klikmale.click();
        }
        else if(gender.equals("female")){
            WebElementFacade klikfemale = find(By.xpath(klik_wanita));
            klikfemale.click();
        }
    }

    public void click_save_button(){
        WebElementFacade kliksaveprofile = find(By.xpath(simpan_profile));
        kliksaveprofile.click();
    }

    public void click_user_profile_section(String menu){
        String menu_to_click = "//body//*[@id='blibli-main-ctrl']//section//ul//li//*[span[contains(text(),'"+menu+"')]]";

        WebDriver webDriver = getDriver();

        WebDriverWait wait = new WebDriverWait(webDriver,10);

        WebElement choosen_menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(menu_to_click)));

        choosen_menu.click();
    }

    public void click_edit_alamat(String nama_lengkap, String alamat, String provinsi, String kota, String kecamatan, String kelurahan, String email, String handphone ){
        String to_click = "//body//*[@id='blibli-main-ctrl']/section//div//a[contains(text(),'Edit Alamat')]";
        String popup_edit_alamat = "//*[body[@class='modal-open']]//*[div[@class='modal fade in']]//*[@id='edit-address']/div/div";

        WebDriver webDriver = getDriver();
        Actions action = new Actions(webDriver);

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        WebElement link_toclick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(to_click)));

        link_toclick.click();

        WebElement the_popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(popup_edit_alamat)));

        ((JavascriptExecutor)webDriver).executeScript("arguments[0].checked = true;", the_popup);

            String field_nama_lengkap = "//*[body[@class='modal-open']]//*[div[@class='modal fade in']]//*[@id='edit-address']//div//input[@name='firstName']";
            String field_alamat = "//*[body[@class='modal-open']]//*[div[@class='modal fade in']]//*[@id='edit-address']//div//textarea[@name='address']";
            String field_provinsi = "//*[body[@class='modal-open']]//*[div[@class='modal fade in']]//*[@id='edit-address']//*[label[contains(text(),'Provinsi')]]//select";
            String field_kota = "//*[body[@class='modal-open']]//*[div[@class='modal fade in']]//*[@id='edit-address']//*[label[contains(text(),'Kota')]]//select";
            String field_kecamatan = "//*[body[@class='modal-open']]//*[div[@class='modal fade in']]//*[@id='edit-address']//*[label[contains(text(),'Kecamatan')]]//select";
            String field_kelurahan = "//*[body[@class='modal-open']]//*[div[@class='modal fade in']]//*[@id='edit-address']//*[label[contains(text(),'Kelurahan')]]//select";
            String field_email = "//*[body[@class='modal-open']]//*[div[@class='modal fade in']]//*[@id='edit-address']//*[label[contains(text(),'Alamat Email')]]//input";
            String field_phone = "//*[body[@class='modal-open']]//*[div[@class='modal fade in']]//*[@id='edit-address']//input[@name='phone1']";
            String btn_submit = "//*[body[@class='modal-open']]//*[div[@class='modal fade in']]//*[@id='edit-address']//input[@type='submit']";

            String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";

            WebElement input_nama_lengkap = webDriver.findElement(By.xpath(field_nama_lengkap));
            ((JavascriptExecutor) webDriver).executeScript(js, input_nama_lengkap);
//            action.moveToElement(input_nama_lengkap).click().build().perform();
            input_nama_lengkap.clear();
            input_nama_lengkap.sendKeys(nama_lengkap);

            WebElement input_alamat = webDriver.findElement(By.xpath(field_alamat));
            ((JavascriptExecutor) webDriver).executeScript(js, input_alamat);
//            action.moveToElement(input_alamat).click().build().perform();
            input_alamat.clear();
            input_alamat.sendKeys(alamat);

            Select prov = new Select(webDriver.findElement(By.xpath(field_provinsi)));
            prov.selectByVisibleText(provinsi);

            WebElement the_city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(field_kota)));
            the_city.click();
            Select city = new Select(webDriver.findElement(By.xpath(field_kota)));
            city.selectByVisibleText(kota);

            WebElement the_kec = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(field_kota)));
            the_kec.click();
            Select kec = new Select(webDriver.findElement(By.xpath(field_kecamatan)));
            kec.selectByVisibleText(kecamatan);

            WebElement the_kel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(field_kota)));
            the_kel.click();
            Select kel = new Select(webDriver.findElement(By.xpath(field_kelurahan)));
            kel.selectByVisibleText(kelurahan);

            WebElement input_email = webDriver.findElement(By.xpath(field_email));
            ((JavascriptExecutor) webDriver).executeScript(js, input_email);
//            action.moveToElement(input_email).click().build().perform();
            input_email.clear();
            input_email.sendKeys(email);

            WebElement input_phone = webDriver.findElement(By.xpath(field_phone));
            ((JavascriptExecutor) webDriver).executeScript(js, input_phone);
//            action.moveToElement(input_phone).click().build().perform();
            input_phone.clear();
            input_phone.sendKeys(handphone);

            WebElement btn_simpan = webDriver.findElement(By.xpath(btn_submit));
            btn_simpan.click();

    }

    public Boolean check_is_the_default_alamat_saved(String nama_lengkap, String alamat, String provinsi, String kota, String kecamatan, String kelurahan, String email, String handphone){
        String component_to_wait = "//body//*[@id='blibli-main-ctrl']//section//*[div[@class='single-address default-address']]//div";
        Boolean result = false;
        WebDriver webDriver = getDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        WebElement div_alamat = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(component_to_wait)));

        if(div_alamat.isDisplayed()){
            Boolean check_name, check_alamat, check_prov, check_kot, check_kec, check_kel, check_em, check_ph;

            String al_first_name = "//body//*[@id='blibli-main-ctrl']//section//div//span[@class='ng-binding'][@ng-bind='defaultAddress.firstName']";
            String al_last_name = "//body//*[@id='blibli-main-ctrl']//section//div//label[@class='ng-binding'][@ng-bind='defaultAddress.lastName']";
            String al_address = "//body//*[@id='blibli-main-ctrl']//section//div//span[@ng-bind='defaultAddress.address']";
            String al_kecamatan = "//body//*[@id='blibli-main-ctrl']//section//div//span[@ng-bind='defaultAddress.kecamatan']";
            String al_kelurahan = "//body//*[@id='blibli-main-ctrl']//section//div//span[@ng-bind='defaultAddress.kelurahan']";
            String al_city = "//body//*[@id='blibli-main-ctrl']//section//div//span[@ng-bind='defaultAddress.city']";
            String al_state = "//body//*[@id='blibli-main-ctrl']//section//div//span[@ng-bind='defaultAddress.state']";
            String al_phone = "//body//*[@id='blibli-main-ctrl']//section//div//span[@ng-bind='defaultAddress.phone2']";
            String al_email = "//body//*[@id='blibli-main-ctrl']//section//div//span[@ng-bind='defaultAddress.email']";

            WebElement field_first_name = webDriver.findElement(By.xpath(al_first_name));
            WebElement field_last_name = webDriver.findElement(By.xpath(al_last_name));
            String namanya = field_first_name.getText()+" "+field_last_name.getText();
            check_name = namanya.equals(nama_lengkap);

            WebElement field_address = webDriver.findElement(By.xpath(al_address));
            check_alamat = field_address.getText().equals(alamat);

            WebElement field_kec =  webDriver.findElement(By.xpath(al_kecamatan));
            check_kec = field_kec.getText().equals(kecamatan);

            WebElement field_kel =  webDriver.findElement(By.xpath(al_kelurahan));
            check_kel = field_kel.getText().equals(kelurahan);

            WebElement field_kota =  webDriver.findElement(By.xpath(al_city));
            check_kot = field_kota.getText().equals(kota);

            WebElement field_prov =  webDriver.findElement(By.xpath(al_state));
            check_prov = field_prov.getText().equals(provinsi);

            WebElement field_phone =  webDriver.findElement(By.xpath(al_phone));
            check_ph = field_phone.getText().equals(handphone);

            WebElement field_email =  webDriver.findElement(By.xpath(al_email));
            check_em = field_email.getText().equals(email);

            if(check_name && check_alamat && check_kec && check_kel && check_kot && check_prov && check_em && check_ph){
                result = true;
            }
        }
        return result;
    }
    // this comment use for testing commit
}

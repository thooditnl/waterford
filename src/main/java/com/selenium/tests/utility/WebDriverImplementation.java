package com.selenium.tests.utility;

import com.google.common.base.Function;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebDriverImplementation {

	public WebDriverImplementation() throws IOException{
        FileInputStream fis = new FileInputStream(GlobalData.LOCATORS_FILE_LOCATION);
        System.out.println("The Repository properties path is:" + fis);
    }
    WebDriver driver = null;
	/*    @launchApp()
	 * 	  @Parameters  : it will take two parameters of type String
	 *             : browser And Url
	 *    @functionality
	 *         it will launch the browser and open the application under test.these parameters
	 *         it taken from global data file.
	 *    @return Type : void
	 */
		public void launchApp(String browser, String url) throws IOException {
		switch (browser) {
			case "firefox":
				//Note: Use firefox version 45.0. Please Don't upgrade to latest version. (webdriver.gecko.driver, webdriver.firefox.marionette)
			//	System.setProperty("webdriver.gecko.driver", filePathLoader("geckodriver.exe"));
				this.driver = new FirefoxDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", filePathLoader("chromedriver.exe"));
				this.driver = new ChromeDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver", filePathLoader("IEDriverServer.exe"));
				this.driver = new InternetExplorerDriver();
			case "htmlunitdriver":
				this.driver = new HtmlUnitDriver(true);
				break;
			case "edgedriver":
				System.setProperty("webdriver.edge.driver", filePathLoader("MicrosoftWebDriver.exe"));
				DesiredCapabilities capabilities = DesiredCapabilities.edge();
				driver = new EdgeDriver(capabilities);
				this.driver = new EdgeDriver();
				break;
			default:
				System.out.println("browser : " + browser + " is invalid");
				driver = new FirefoxDriver();
		}
		System.out.println("The browser launched is  ************* " + browser + " ********************");
		driver.manage().window().maximize();
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(90000,TimeUnit.MILLISECONDS);
		}

	public String filePathLoader(String filename) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());
		try {
			Runtime.getRuntime().exec(file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}

/*  @clickElementById
	 *  @parameter : It Will take one parameter of String Type
	 *  			: id
	 *  @functionality
	 *   it will perform Click operation element by using locator ID
	 */
	public void clickElementById(String id){
		driver.findElement(By.id(id)).click();
	}

	/*  @getWebElementById
	 *  @parameter : It Will take one parameter of String Type
	 *  			: id
	 *  @returnType : WebElement
	 *  @functionality: It will returns web element that's matches by given parameter Id
	 */
	public WebElement getWebElementById(String id){
        return driver.findElement(By.id(id));
	}

	/*  @getWebElementByLinkText
	 *  @parameter : It Will take one parameter of String Type
	 *  			: className 
	 *  @returnType : WebElement
	 *  @functionality: It will returns web element that's matches by given parameter className
	 */
	public WebElement getWebElementByLinkText(String linkText){
        return driver.findElement(By.linkText(linkText));
	}

	/*  @clickElement
	 *  @parameter : It Will take one parameter of String Type
	 *  			: xpath 
	 *  @functionality
	 *   it will perform Click operation on element by using locater xpath
	 */
	public void clickElementByXpath(String xpath){
		driver.findElement(By.xpath(xpath)).click();
	}
	public void clickElementByCss(String Css){
		driver.findElement(By.cssSelector(Css)).click();
	}

	public WebDriver getDriver(){
		return this.driver;
	}

	/*  @getWebElementByXpath
	 *  @parameter : It Will take one parameter of String Type
	 *  			: Xpath
	 *  @returnType : WebElement
	 *  @functionality: It will returns webElement that's matches by given parameter Xpath
	 */
	public WebElement getWebElementByXpath(String Xpath)
	{
        return driver.findElement(By.xpath(Xpath));
	}

	public WebElement getWebElementByCss(String Selector){
        return driver.findElement(By.cssSelector(Selector));
	}

	/*  @clickElementByLinkText
	 *  @parameter : It Will take one parameter of String Type
	 *  			: linkText
	 *  @functionality
	 *   it will perform Click operation on element by using locator linkText
	 */
	public void clickElementByLinkText(String linkText){
		driver.findElement(By.linkText(linkText)).click();
	}

	/*  @sendTextToElementByClass
	 *  @parameter : It Will take two parameter of String Type
	 *  			: className,text
	 *  @returnType : Void
	 *  @functionality: it will send text to text filed that's matched by given className
	 */
	public void sendTextToElementById(String id,String text){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", driver.findElement(By.id(id)), "style", "border: 2px solid yellow; color: green; font-weight: bold;");
		driver.findElement(By.id(id)).sendKeys(text);
	}

	/*  @sendTextToElementByXpath
	 *  @parameter : It Will take two parameter of String Type
	 *  			: Xpath,text
	 *  @returnType : Void
	 *  @functionality: it will send text to text filed that's matched by given Xpath
	 */
	public void sendTextToElementByXpath(String Xpath,String text){
		driver.findElement(By.xpath(Xpath)).sendKeys(text);
	}


	public void sendTextToElementByCss(String Selector,String text){
		driver.findElement(By.cssSelector(Selector)).sendKeys(text);
	}

	/*  @captureScreenShot
	 *  @parameter : It Will take one parameter of String Type
	 *  			: Test_no
	 *  @returnType : Void
	 *  @functionality: it will take the screenshot of browser at point of failed And it stored in screenshots folder
	 */
	public void captureScreenShot(String Test_no) throws IOException {
		String path = GlobalData.SCREENSHOT_PATH;
		File f = new File(path);
		if(!(f.exists() && f.isDirectory()))
			f.mkdir();
		String total_path= path + File.separator + Test_no + "-failed.jpg";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(total_path));
	}


	/*  @fluentWaitByXpath
	 *  @parameter : It Will take three parameter of String Type
	 *  			: xpath, MAX_WAIT,PollWait
	 *  @returnType : web element
	 *  @functionality: it will wait  MAX_WAIT Seconds for web element that matches by locator xpath
	 *  			  : Every PollWait Seconds it Searches for Element.if element available  returns that element
	 */
	public WebElement fluentWaitByXpath(final String xpath,int maxWait,int PollWait){
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(maxWait, TimeUnit.SECONDS)
				.pollingEvery(PollWait, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement element;
        element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webdriver) {
                return driver.findElement(By.xpath(xpath));
            }
        });
        return  element;
	}

	/*    @closeApp
	 *    @functionality
	 *         it will close the application  after completion of execution of Test
	 *    @return Type : void
	 */
	public void closeApp() {
		try{
			driver.quit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*    @close
	 *    @functionality
	 *         it will close the current window application  after completion of execution of Test
	 *    @return Type : void
	 */
	public void close() {
		try{
			driver.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*    @getCurrentWindowHandle
	 * 	  @return Type : String
	 *    @functionality  : Return an window handle to this window that uniquely identifies
	 *    					 it within this driver instance.
	 */
	public String getCurrentWindowHandle() {
		String handle;
		try {
			handle= driver.getWindowHandle();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return handle;
	}

	/*    @switchToWindow
	 *    @functionality  : it will switches focus form  current window to other window that's mention in handle.
	 */
	public void switchToWindow(String handle){
		try {
			driver.switchTo().window(handle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*    @implicitWait
	 * 	@parameter : it will take one parameter of type integer
	 * 					waitTimeSec
	 *    @functionality  : it will waits the execution you specified value in parameter
	 */
	public void implicitWait(int waitTimeSec){
		try {
			driver.manage().timeouts().implicitlyWait(waitTimeSec, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* @ elementIsDisplayedByID
	 * @parameter : it will take one parameter of type String.
	 * 				Id
	 * @return Type :Boolean
	 * @functionality :  it will look for web element that matches to given parameter Id
	 * 					> if element find it returns "true"
	 * 					>if element not find it returns "false"
	 */
	public boolean elementIsDisplayedByID(String Id) {
		boolean  flag = false;
		try{
			Thread.sleep(5000);
			flag =driver.findElement(By.id(Id)).isDisplayed();
		}catch(Exception e){
			e.printStackTrace();
		}
		return  flag;
	}

	/* @ elementIsDisplayedByXpath
	 * @parameter : it will take one parameter of type String.
	 * 				xpath
	 * @return Type :Boolean
	 * @functionality :  it will look for web element that matches to given parameter xpath
	 * 					> if element find it returns "true"
	 * 					>if element not find it returns "false"
	 */
	public boolean elementIsDisplayedByXpath(String xpath) {
		boolean  flag;
		try{
		Thread.sleep(5000);
		flag = driver.findElement(By.xpath(xpath)).isDisplayed();
		}catch(Exception e){
			flag = false;
		}
		return  flag;
	}
	public boolean elementIsDisplayedByCss(String selector) {
		boolean  flag = false;
		try{
			Thread.sleep(5000);
			flag = driver.findElement(By.cssSelector(selector)).isDisplayed();
		}catch(Exception e){
			flag = false;
		}
		return  flag;
	}

	/* @ elementIsDisplayedByLinkText
	 * @parameter : it will take one parameter of type String.
	 * 				lnkText
	 * @return Type :Boolean
	 * @functionality :  it will look for Link that matches to given parameter lnkText
	 * 					> if link find it returns "true"
	 * 					>if link not find it returns "false"
	 */
	public boolean elementIsDisplayedByLinkText(String lnkText)  {
		boolean  flag;
		try {
			Thread.sleep(5000);
			flag = driver.findElement(By.linkText(lnkText)).isDisplayed();
		} catch (Exception e) {
			flag = false;
		}
		return  flag;
	}

	public void waitUntilElementIsClickable(String xpath,int time){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until( ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}


	public boolean elementIsDispalyedByName(String name)  {
		boolean  flag = false;
		try {
			Thread.sleep(5000);
			flag =driver.findElement(By.name(name)).isDisplayed();
		} catch (Exception e) {
			flag = false;
		}
		return  flag;
	}

	/* @ explictWaitForElementByID
	 * @parameter : it will take two parameter of type String.
	 * 				id,waitTime
	 * @return Type :webElemnt
	 * @functionality :  it explicitly stop the execution specified time for particular element that matches to given parameter id
	 * 					>if element not find at specified it throws Exception
	 * 					>if element  find at specified it returns matched webElement
	 */
	public WebElement explictWaitForElementByID(String id,int waitTime){
		WebElement myDynamicElement=null;
		try {
			myDynamicElement = new WebDriverWait(driver, waitTime)
					.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myDynamicElement;
	}
	/* @ explictWaitForElementByLinkText
	 *
	 * @parameter : it will take two parameter of type String.
	 * 				linkText,waitTime
	 * @return Type :webElemnt
	 * @functionality :  it explicitly stop the execution specified time for particular link that matches to given parameter linkText
	 * 					>if link not find at specified it throws Exception
	 * 					>if link  find at specified it returns matched webElement
	 */

	/* @ explicitWaitForElementByXpath
	 * @parameter : it will take two parameter of type String.
	 * 				xpath,waitTime
	 * @return Type :webElement
	 * @functionality :  it explicitly stop the execution specified time for particular element that matches to given parameter xpath
	 * 					>if element not find at specified it throws Exception
	 * 					>if element  find at specified it returns matched webElement
	 */
	public WebElement explicitWaitForElementByXpath(String xpath, int waitTime){
		WebElement myDynamicElement=null;
		try {
			myDynamicElement = new WebDriverWait(driver, waitTime)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  myDynamicElement;
	}

	/* @ explicitWaitForElementNotVisibleByXpath
	 * @parameter : it will take two parameter of type String.
	 * 				xpath,waitTime
	 * @return Type :boolean
	 * @functionality :  it will wait  specified time for element to disappear
	 * 					>if element not disappear it returns false
	 * 					>if element  disappear it returns true
	 */
	public boolean explicitWaitForElementNotVisibleByXpath(String xpath, int waitTime) {
		boolean myDynamicElement=false;
		try {
			myDynamicElement = new WebDriverWait(driver, waitTime)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  myDynamicElement;
	}

	/* @ moveToElement
	 * @parameter : it will take one parameter of type String.
	 * 				xpath
	 * @functionality :  it moves the cursor to specified location that you mention in parameter xpath
	 */
	public void moveToElementByXpath(String xpath){
		Actions Act = new Actions(driver);
		WebElement we =driver.findElement(By.xpath(xpath));
		Act.moveToElement(we).build().perform();
	}

	public void moveToElementByCss(String selector){
		Actions Act = new Actions(driver);
		WebElement we =driver.findElement(By.cssSelector(selector));
		Act.moveToElement(we).build().perform();
	}

	/* @ inLineWait
	 * @parameter : it will take one parameter of type integer.
	 * 				waitTime
	 * @functionality :  it stops the execution for specified time in parameter
	 * 					we are using this method for synchronization purpose
	 */
	public void inLineWait(int waitTime) {
		try {
			Thread.sleep(waitTime);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* @ switchToNewWindow
	 * @functionality :  it switches the focus form current window to newly opened window by driver instance
	 */
	public void switchToNewWindow(){
		Set<String> handle =driver.getWindowHandles();
		for (String winHandle : handle) {
			driver.switchTo().window(winHandle);
		}
	}

	/* @ switchToDefaultContent
	 * @functionality :  it switches the focus form current window to starting default instance
	 */
	public void switchToDefaultContent(){
		driver.switchTo().defaultContent();
	}

	/* @ scrollDown
	 * @functionality :  it will scroll down the scroll bar of page.
	 */
	public void scrollDown(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/* @getWebElementsByXpath
	 * @parameter : it will take one parameter of type String
	 *				 xpath
	 * @return Type : list of web elements
	 *@functionality :it will returns web elements that are matched by given value in parameter xpath
	 */
	public List<WebElement> getWebElementsByXpath(String xpath){
        return driver.findElements(By.xpath(xpath));
	}

	/* @selectByIdAndVisibleText
	 * @parameter : it will take two parameter of type String
	 *				 id,visibleText
	 *@functionality :it will select the value form Selection box that you mention in parameter Id by value displayed drop down
	 */
	public void selectByIdAndVisibleText(String id,String visibleText){
		Select ele = new Select(driver.findElement(By.id(id)));
		ele.selectByVisibleText(visibleText);
	}

	/* @selectByxPATHAndValue
	 * @parameter : it will take two parameter of type String
	 *				 id,visibleText
	 *@functionality :it will select the value form Selection box that you mention in parameter Id by value displayed drop down
	 */
	public void selectByXPATHAndValue(String XPATH,String value) {
		Select ele = new Select(driver.findElement(By.xpath(XPATH)));
		ele.selectByValue(value);
	}

	/* @selectByxPATHAndVisibleText
	 * @parameter : it will take two parameter of type String
	 *				 id,visibleText
	 *@functionality :it will select the value form Selection box that you mention in parameter Id by value displayed drop down
	 */
	public void selectByXPATHAndVisibleText(String XPATH,String visibleText){
		Select ele = new Select(driver.findElement(By.xpath(XPATH)));
		ele.selectByVisibleText(visibleText);
	}

	public void selectByCSSAndVisibleText(String Css,String visibleText){
		Select ele = new Select(driver.findElement(By.cssSelector(Css)));
		ele.selectByVisibleText(visibleText);
	}

	public void selectByVisibleTextForWebElement(WebElement element,String visibleText){
		Select ele = new Select(element);
		ele.selectByVisibleText(visibleText);
	}
	public void selectByVisibleText(String visibleText){
		driver.findElement(By.xpath(visibleText));
			}


	/* @clickElementByJavaScript
	 * @parameter : it will take one parameter of type web element
	 *				 element
	 *@functionality :it will click on web element that you specified in parameter by using java script object.
	 */
	public void clickElementByJavaScript(WebElement element ){
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* @popups
	 *@functionality :it will accept the alert pop up .
	 */
	public void acceptAlert(){
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	/* @clearTextById
	 * @parameter : it will take one parameter of type string
	 * 				Id
	 *@functionality :it clears the text present in text box that matched  by given parameter value.
	 */

	public void clearTextById(String id)throws Exception{
		driver.findElement(By.id(id)).clear();
	}
	/* @clearTextByXpath
	 * @parameter : it will take one parameter of type string
	 * 				xpath
	 *@functionality :it clears the text present in text box that matched  by given parameter value.
	 *
	 */

	public void clearTextByXpath(String xpath){
		driver.findElement(By.xpath(xpath)).clear();
	}


	public void waitUntilElementIsClickableByCss(String Css, int time){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(Css)));
	}

	/* @waitUntilElementIsClickableByLinkText linkText
	 * @parameter : it will take one parameter of type string/LinkText and Waiting Time(Integer)
	 *@functionality :it will wait for element to Clickable
	 */
	public void waitUntilElementIsClickableByLinkText(String linkText, int time){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
	}

	/* @waitUntilElementIsDisplayed
	 * @parameter : it will take one parameter of type string/Locator and Waiting Time(Integer)
	 *@functionality :it will wait for element to Load
	 */
	public void waitUntilElementIsDisplayed(String xpath, int time){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}

	public void printStatement(String statement){
		System.out.println(statement);
				}

	public void waitUntilElementIsDisplayedByCss(String selector, int time){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
	}

	/* @waitUntilElementsAreDisplayed
	 * @parameter : it will take one parameter of type string/Locator and Waiting Time(Integer)
	 *@functionality :it will wait for element to load
	 */
	public void waitUntilElementsAreDisplayed(String xpath, int time){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
	}

	public void waitUntilElementsAreDisplayedByCss(String css, int time){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(css)));
	}

	/* @waitUntilElementIsDisplayedByLinkText
	 * @parameter : it will take one parameter of type string/LinkText and Waiting Time(Integer)
	 *@functionality :it will wait for element to load
	 */
	public void waitUntilElementIsDisplayedByLinkText(String linkText, int time){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
	}

	/* @waitForElementToDisplayById
	 * @parameter : it will take one parameter of type string/LinkText and Waiting Time(Integer)
	 *@functionality :it will wait for element to load
	 */
	public void waitForElementToDisplayById(String id, int time){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
	}

	public void waitUntilAlertIsPresent(){
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitUntilElementDisappearsByXpath(String xpath, int timeSec){
		WebDriverWait wait = new WebDriverWait(driver,timeSec );
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
	}

	public void waitUntilElementDisappearsByCss(String css, int timeSec){
		WebDriverWait wait = new WebDriverWait(driver,timeSec );
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(css)));
	}


	public void waitUntilElementDisappearsById(String id, int timeSec){
		WebDriverWait wait = new WebDriverWait(driver,timeSec );
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));

	}

	/* @popups
	 *@functionality :it will dismiss the alert pop up .
	 */
	public void dismissAlert(){
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void switchFrameById(String frameId){
		driver.switchTo().frame(frameId);
	}

	public void waitUntilElementDisappearsByLinkText(String linkText, int timeSec){
		WebDriverWait wait = new WebDriverWait(driver,timeSec );
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(linkText)));
	}

	public WebElement findElement(String Id) {
		WebElement el = null;
		try{
			Thread.sleep(5000);
			el =driver.findElement(By.id(Id));
		}catch(Exception e){
			e.printStackTrace();
		}
		return el;
	}

	public WebElement findElementByCss(String Css) {
		WebElement el = null;
		try{
			Thread.sleep(5000);
			el =driver.findElement(By.cssSelector(Css));
		}catch(Exception e){
			e.printStackTrace();
		}
		return el;
	}

	public WebElement findElementByXpath(String Xpath) {
		WebElement el = null;
		try{
			Thread.sleep(5000);
			el =driver.findElement(By.xpath(Xpath));
		}catch(Exception e){
			e.printStackTrace();
		}
		return el;
	}

	public List<WebElement> findElementsByXpath(String Xpath) {
		List<WebElement> els = null;
		try{
			Thread.sleep(5000);
			els =driver.findElements(By.xpath(Xpath));
		}catch(Exception e){
			e.printStackTrace();
		}
		return els;
	}

	public List<WebElement> findElementsByCss(String Selector) {
		List<WebElement> els = null;
		try{
			Thread.sleep(5000);
			els =driver.findElements(By.cssSelector(Selector));
		}catch(Exception e){
			e.printStackTrace();
		}
		return els;
	}

	public List<WebElement> findElementsByTagName(String TagName) {
		List<WebElement> els = null;
		try{
			Thread.sleep(5000);
			els =driver.findElements(By.tagName(TagName));
		}catch(Exception e){
			e.printStackTrace();
		}
		return els;
	}

	public void Refresh(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("history.go(0)");
	}

	public void switchFrameByCss(String selector)
	{
		WebElement frame=driver.findElement(By.cssSelector(selector));
		driver.switchTo().frame(frame);
	}

}

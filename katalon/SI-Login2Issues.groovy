import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement
System.setProperty("webdriver.chrome.driver", "C:\\ProgramData\\chocolatey\\lib\\chromedriver\\tools\\chromedriver.exe")

//WebDriver webUiDriver = DriverFactory.getWebDriver()
WebDriver chromeDriver = new ChromeDriver()
DriverFactory.changeWebDriver(chromeDriver)

// Go to the Siteimprove login page
chromeDriver.get('https://id.siteimprove.com/account/login?returnUrl=%2Fconnect%2Fauthorize%2Flogin%3Fclient_id%3Dmy2%26redirect_uri%3Dhttps%253A%252F%252Fmy2.siteimprove.com%252FAuth%252FAuthCallback%26response_mode%3Dform_post%26response_type%3Dcode%2520id_token%26scope%3Dopenid%2520profile%2520si.profile%26state%3DOpenIdConnect.AuthenticationProperties%253DXrWL690RF0UsRb26WxT7zlsBiEaptpeCmkOLRr9PuatfNVPJYyVfXG56VIUwmmftPond5rnpMnhKTa4Z1i8uTRqtHnlgykjnY5bdqDRTsgUqPtqTl8j_LU89K_Uj8tHQd2y4M46sw3IO_Zzht2HqK9KXKdlqrd0YbsaTLa6y_RR4ngeojWet3r-udaRzDs904BCRYhlc57e2xJHkycFWy_7nydVakJNOE1J_dQ5pCpg%26nonce%3D636846457314111429.ZTA1ZWJlNWEtYWZiZi00ZGQ1LWJiMzctNjdmMzExNzY1YjgyNDY4ZDJlM2QtZDljNC00MDBlLTg2YWYtZDFlNWFiZjNlNjk0%26post_logout_redirect_uri%3Dhttps%253A%252F%252Fmy2.siteimprove.com%252FAuth%252FLogoutCallback')

// Get the username textbox and enter username
WebElement loginId = chromeDriver.findElement(By.id("Email"))
loginId.sendKeys("waltria@wwu.edu")

// Click the continue button under the username tbox
WebElement continueButton = chromeDriver.findElement(By.cssSelector("button.btn-primary"))
continueButton.click()
WebUI.delay(3)

// Set username for SSO and click on 'Next' button
WebElement wwuLoginUsername = chromeDriver.findElement(By.cssSelector('input[name="loginfmt"]'))
wwuLoginUsername.sendKeys("waltria@wwu.edu")
WebElement nextButton = chromeDriver.findElement(By.cssSelector('input[id="idSIButton9"]'))
nextButton.click()
WebUI.delay(3)

// Set the SSO password and click the 'Sign In' button
WebElement pwordTbox = chromeDriver.findElement(By.cssSelector('input[name="passwd"]'))
pwordTbox.sendKeys(".22Valhalla")
WebElement signInButton = chromeDriver.findElement(By.cssSelector('input[value="Sign in"]'))
signInButton.click()
WebUI.delay(3)

// Click the annoying 'Yes' button to stay signed in.
WebElement staySignedInButton = chromeDriver.findElement(By.cssSelector('input[id="idSIButton9"]'))
staySignedInButton.click()
WebUI.delay(3)

// Now that you're at the Siteimprove dashboard, go to the page that lists the issues we're interested in.  
chromeDriver.get('https://my2.siteimprove.com/Accessibility/468017/20180369320/Pages/Issue?CheckId=129&criterionChapter=4.1.2')
WebUI.delay(3)

// Get the links of the issues and do something with em.
List<WebElement> issues = chromeDriver.findElements(By.cssSelector('a[class="titleurl"]'))
issues.each { issue ->
	println "The issue is: " + issue.text
	processIssues(issue, chromeDriver)
}

WebUI.delay(10)
WebUI.closeBrowser()

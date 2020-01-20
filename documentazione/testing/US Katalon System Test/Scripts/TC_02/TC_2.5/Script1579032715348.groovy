import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/_comuni/login.jsp')

WebUI.click(findTestObject('Object Repository/Page_Login/label_E-Mail'))

WebUI.setText(findTestObject('Object Repository/Page_Login/input_Accedi_email'), 'm.rossi12@studenti.unisa.it')

WebUI.click(findTestObject('Object Repository/Page_Login/label_Password'))

WebUI.setText(findTestObject('Object Repository/Page_Login/input_E-Mail_password'), 'MarioRo')

WebUI.click(findTestObject('Object Repository/Page_Login/button_Accedi'))

actual = WebUI.getText(findTestObject('Page_Login/div_message'))

expected = "Il campo Password non rispetta la lunghezza"

assert actual.equals(expected)

WebUI.closeBrowser()

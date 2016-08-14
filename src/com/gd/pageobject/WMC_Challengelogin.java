package com.gd.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.browser.debug.Property;

public class WMC_Challengelogin {

	public final static String url = "https://www.walmartmoneycard.com/challengelogin";
	private WebDriverWait wait;
	
	@FindBy(css = "#lbl-txtanswer1")
	WebElement oTxtAnswer1_Label;
	
	@FindBy(css = "#TxtAnswer1")
	WebElement oTxtAnswer1_Input;
	
	@FindBy(css = "#btn-submit")
	WebElement oContinue_Button;

	public WMC_Challengelogin(WebDriver driver) {
		wait = new WebDriverWait(driver, Property.getDefault_Wait_Time());
	}
	
	public void submitChallengeAnswer()
	{	
		wait.until(ExpectedConditions.visibilityOf(oTxtAnswer1_Label));
		String question = oTxtAnswer1_Label.getText();		
		String answer = question.substring(question.lastIndexOf(" "), question.lastIndexOf("?"));
		
		oTxtAnswer1_Input.sendKeys(answer);
		oContinue_Button.click();		
	}
}
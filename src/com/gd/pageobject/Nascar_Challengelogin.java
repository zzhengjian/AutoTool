package com.gd.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.browser.debug.Property;

public class Nascar_Challengelogin {

	public final static String url = "https://www.greendot.com/racing/challengelogin";
	private WebDriverWait wait;

	@FindBy(css = ".display-label[for=TxtAnswer1]")
	WebElement oTxtAnswer1_Label;	
	
	@FindBy(css = "#TxtAnswer1")
	WebElement oTxtAnswer1_Input;

	@FindBy(css = "#CheckBoxRememberComputer")
	WebElement oCheckBoxRememberComputer_CheckBox;

	@FindBy(css = "#popRegisterComputer")
	WebElement oPopregistercomputer_Link;

	@FindBy(css = "#btnNext")
	WebElement oBtnnext_Input;
	
	public Nascar_Challengelogin(WebDriver driver) {
		wait = new WebDriverWait(driver, Property.getDefault_Wait_Time());
	}
	
	public void submitChallengeAnswer()
	{	
		wait.until(ExpectedConditions.visibilityOf(oTxtAnswer1_Label));
		String question = oTxtAnswer1_Label.getText();		
		String answer = question.substring(question.lastIndexOf(" "), question.lastIndexOf("?"));
		
		oTxtAnswer1_Input.sendKeys(answer);
		oBtnnext_Input.click();		
	}

}
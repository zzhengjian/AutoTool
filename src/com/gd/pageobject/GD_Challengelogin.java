package com.gd.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gd.driver.Property;

public class GD_Challengelogin {

	public final static String url = "https://www.greendot.com/greendot/challengelogin";
	//private WebDriver driver;
	private WebDriverWait wait;
	
	
	@FindBy(css = "[for='TxtAnswer1']")
	WebElement oTxtAnswer_Label;

	@FindBy(css = "#TxtAnswer1")
	WebElement oTxtAnswer1_Input;

	@FindBy(css = "[for='CheckBoxRememberComputer']")
	WebElement oRememberThisDevice_Label;

	@FindBy(css = "#CheckBoxRememberComputer")
	WebElement oCheckBoxRememberComputer_CheckBox;

	@FindBy(css = "#btnNext")
	WebElement oBtnnext_Input;
	
	
	
	public GD_Challengelogin(WebDriver driver)
	{	
		wait = new WebDriverWait(driver, Property.getDefault_Wait_Time());
	}
	
	public void submitChallengeAnswer()
	{	
		wait.until(ExpectedConditions.visibilityOf(oTxtAnswer_Label));
		String question = oTxtAnswer_Label.getText();		
		String answer = question.substring(question.lastIndexOf(" "), question.lastIndexOf("?"));
		
		oTxtAnswer1_Input.sendKeys(answer);
		oBtnnext_Input.click();
		
	}

}
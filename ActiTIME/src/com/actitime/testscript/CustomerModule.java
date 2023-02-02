package com.actitime.testscript;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generic.BaseClass;
import com.actitime.generic.FileLib;
import com.actitime.pom.HomePage;
import com.actitime.pom.TaskListPage;

@Listeners(com.actitime.generic.ListenerImplementation.class)
public class CustomerModule extends BaseClass {
	
	@Test
	public void createCustomer() throws EncryptedDocumentException, IOException, InterruptedException
	{
		Reporter.log("createCustomer",true);
		
		FileLib f=new FileLib();
		String customerName = f.getExcelData("CreateCustomer", 1, 2);
		String customerDesp = f.getExcelData("CreateCustomer", 1, 3);
		
		//click the taskTab
		HomePage h=new HomePage(driver);
		h.setTaskTab();
		
		TaskListPage t=new TaskListPage(driver);
		
		//click on "Add New" button
		t.getAddNewBtn().click();
		
		//select "New Customer" option
		t.getNewCustOption().click();
		
		//enter the customer name
		t.getEnterCustNameTbx().sendKeys(customerName);
		
		//enter the customer description
		t.getEnterCustDespTbx().sendKeys(customerDesp);
		
		//click "-Select Customer-" drop-down
		t.getSelectCustDD().click();
		
		//select "Our company" in the drop-down
		t.getOurCompanyTx().click();
		
		//click on "Create Customer" button
		t.getCreateCustBtn().click();
		 Thread.sleep(5000);
		//verify the customer is successfully created or not
		String actualText = t.getActualCustCreated().getText();
		Assert.assertEquals(actualText, customerName);
		
	}
}

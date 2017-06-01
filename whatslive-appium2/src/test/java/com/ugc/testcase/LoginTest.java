package com.ugc.testcase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ugc.action.LoginAction;
import com.ugc.pageObject.StartPage;
import com.ugc.utils.ElementAction;
import com.ugc.utils.TestBaseCase;

public class LoginTest extends TestBaseCase {

	ElementAction action = new ElementAction();

	@BeforeClass
	public void beforeClass() throws IOException {
		// action.click(new StartPage().微信());
		action.click(new StartPage().qq());
		action.sleep(3);
	}

	@Test(description = "qq登录")
	public void qqLogin() throws InterruptedException, IOException {
		System.out.println("---login");
		// 判断qq是否为首次登录
		Boolean isLogin = (driver.findElement(By.id("com.tencent.mobileqq:id/ivTitleBtnRightText")).isDisplayed());
		System.out.println(isLogin);
		// 设置检查点
		// Assertion.VerityTextPresent("你已对该应用授权","验证元素是否登录成功！");
		if (!isLogin)// 首次登录
			new LoginAction("1643241437", "liyong1990");
		else// 非首次登录
			driver.findElement(By.className("android.widget.Button")).click();
		// driver.findElement(By.xpath("//android.widget.Button［@Test='登录'］")).click();

		action.sleep(5);
	}

	@Test(enabled = false)
	public void weixinLogin() throws IOException {
		new LoginAction("", "");
	}
}

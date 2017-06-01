package com.ugc.utils;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;

/*
 * @2017.01.16  by cxj
 * setUp() 初始化session
 * endUp() 关闭session
 * appiumStart()  启动server
 * appiumStop()   停止server
 * */

public class TestBaseCase {

	Process p;
	String nodePath = "/usr/local/bin/node";// Set path of your node.exe file.
	String appiumJSPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/build/lib/main.js";// Set
																												// path
																												// //
																												// appium.js
																												// file.
	String cmd = nodePath + " " + appiumJSPath;

	// public AndroidDriver<WebElement> driver;
	public static AndroidDriver<WebElement> driver;
	public Log log = new Log(this.getClass().getSuperclass());

	@BeforeTest
	public void setUp() throws IOException, InterruptedException {

		log.info("－－－initial Appium server capabilities－－－");

		// appiumStop();
		// appiumStart();

		// 初始化capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "leMax2"); // 设备名称乐max2
		capabilities.setCapability("udid", "55dd55cb"); // 设备名称乐max2
		capabilities.setCapability("platformName", "Android"); // 手机平台
		capabilities.setCapability("platformVersion", "6.0.1"); // 系统版本
		capabilities.setCapability("fullReset", "true"); // 手机平台
		capabilities.setCapability("appPackage", "com.letv.whatslive"); // app包名
		capabilities.setCapability("appActivity", "com.lxsj.whatslive.activity.MainActivity"); // app启动方法

		log.info("－－－Set appium server address(http://127.0.0.1) and port number(4723) in URL string－－－");
		// Set appium server address and port number in URL string.
		// It will launch app in android device.
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// WebDriverWait wait = new WebDriverWait(driver, 300);
		// wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.ImageView")));
	}

	@AfterTest
	public void endUp() throws IOException {
		driver.quit();
		log.info("－－－driver endUp－－－");
		// appiumStop();
	}

	// This method Is responsible for starting appium server.
	public void appiumStart() throws IOException, InterruptedException {
		// Execute command string to start appium server.
		System.out.println("启动命令：" + cmd);
		p = Runtime.getRuntime().exec(cmd);

		// Provide wait time of 10 mins to start appium server properly.
		// If face any eÏrror(Could not start a new session...) then Increase
		// this time to 15 or 20 mins.
		Thread.sleep(10000);
		if (p != null) {
			System.out.println("--Appium server Is started now.");
		}
	}

	// This method Is responsible for stopping appium server.
	public void appiumStop() throws IOException {
		if (p != null) {
			p.destroy();
		}
		System.out.println("--Appium server Is stopped now.");
	}

}

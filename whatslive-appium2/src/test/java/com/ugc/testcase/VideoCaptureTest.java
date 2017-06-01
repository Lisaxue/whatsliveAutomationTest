package com.ugc.testcase;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ugc.pageObject.HomePage;
import com.ugc.pageObject.VideoPage;
import com.ugc.utils.Assertion;
import com.ugc.utils.ElementAction;
import com.ugc.utils.TestBaseCase;

/*
 * 
 */
public class VideoCaptureTest extends TestBaseCase {

	ElementAction action = new ElementAction();
	VideoPage videoPage = new VideoPage();
	int loopCount = 0; // 循环次数

	// @Test之前执行
	@BeforeMethod
	public void initialVideo() throws IOException {
		log.info("－－－点击首页拍视频，进入录视频准备页－－－");
		action.click(new HomePage().首页直播()); // 点击首页底部中间拍摄图标
		action.sleep(1);
		driver.swipe(917, 1753, 1000, 1939, 100); // 因为拍视频元素无法定位，所以根据坐标点击“拍视频”按钮
		// driver.swipe(711, 1341, 815, 1478, 100);//
		action.sleep(1);
	}

	// invocationCount控制循环次数
	@Test(description = "录制视频5秒", invocationCount = 3)
	public void recordVideo() throws IOException {
		action.click(videoPage.拍视频()); // 设置检查点
		action.sleep(5);

		action.click(videoPage.结束拍摄());
		action.sleep(3);

		action.click(videoPage.发布());
		action.sleep(2);

		action.click(videoPage.草稿箱返回());
		action.sleep(2);

		// action.click(videoPage.个人中心页返回());
		// action.sleep(2);
		Assertion.VerityTextPresent("首页", "是否返回至首页成功！");
		loopCount++;
	}

	// @Test之后执行
	@AfterMethod
	public void verifyVideoTest() {
		log.info("－－－－第" + loopCount + "次拍视频结束－－－－");
	}
}

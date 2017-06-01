package com.ugc.testcase;

import java.io.IOException;

import org.testng.annotations.Test;

import com.ugc.pageObject.HomePage;
import com.ugc.utils.Assertion;
import com.ugc.utils.ElementAction;
import com.ugc.utils.TestBaseCase;

public class HomePageTest extends TestBaseCase {

	ElementAction action = new ElementAction();

	// 首页－广场页切换操作
	@Test(description = "首页面切换")
	public void switchHomePage() throws InterruptedException, IOException {
		// 点击广场图标
		action.click(new HomePage().广场());
		action.sleep(3);
		// 设置检查点
		Assertion.VerityTextPresent("24h轮播", "验证是否切换至广场页面成功！");
		action.click(new HomePage().首页());
		action.sleep(3);

		// 设置断言。判断用例是否失败
		Assertion.VerityError();
		log.info("--首页面切换结束--");
	}

	@Test(description = "首页水平左滑")
	public void swipeToLeft() throws IOException {
		//
		for (int i = 0; i < 3; i++) {
			if (action.isElementDisplayed(new HomePage().位置())) {// 若地理位置元素显示，表明列表元素加载成功，再执行左滑操作
				action.swipeToDown(driver, 200); // 下拉刷新
				action.sleep(2);
				action.swipeToLeft(driver, 200); // 向左滑动
			} else// 若页面未加载完，等待两秒
				action.sleep(2);
		}
		action.sleep(3);

		// 设置断言。判断用例是否失败
		Assertion.VerityError();
		log.info("--首页水平左滑结束--");
	}
}

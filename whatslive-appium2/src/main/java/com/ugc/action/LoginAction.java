package com.ugc.action;

import java.io.IOException;

import com.ugc.pageObject.LoginPage;
import com.ugc.utils.ElementAction;

public class LoginAction {
	public LoginAction(String username, String password) throws IOException {
		ElementAction action = new ElementAction();
		LoginPage loginPage = new LoginPage();

		action.click(loginPage.qq账号输入框());
		action.clear(loginPage.qq账号输入框());
		action.type(loginPage.qq账号输入框(), username);
		action.click(loginPage.qq密码输入框());
		action.clear(loginPage.qq密码输入框());
		action.type(loginPage.qq密码输入框(), password);
		action.sleep(1);
		action.click(loginPage.qq登录按钮());

		/*
		 * action.click(loginPage.微信账号输入框()); action.clear(loginPage.微信账号输入框());
		 * action.type(loginPage.微信账号输入框(), username);
		 * action.click(loginPage.微信密码输入框()); action.clear(loginPage.微信密码输入框());
		 * action.type(loginPage.微信密码输入框(), password); action.sleep(1);
		 * action.click(loginPage.微信登录按钮());
		 */
	}
}

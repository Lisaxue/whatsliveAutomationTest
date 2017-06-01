package com.ugc.pageObject;

import java.io.IOException;

import com.ugc.utils.BaseAction;
import com.ugc.utils.Locator;

public class LivePage extends BaseAction {
	// 用于eclipse工程内运行查找对象库文件路径
	private String path = "/Users/chenxuejiao/StudyDocs/HiMaven/whatslive-appium/src/main/java/com/ugc/pageObjectConfig/UILibrary.yaml";

	public LivePage() {
		// 工程内读取对象库文件
		setXmlObjectPath(path);
		getLocatorMap();
	}

	/***
	 * com.letv.whatslive:id/setlive_edit
	 * 
	 * @return
	 * @throws IOException
	 */
	public Locator 标题() throws IOException {
		Locator locator = getLocator("标题");
		return locator;
	}

	/***
	 * com.letv.whatslive:id/setliveinfo_camera_iv
	 * 
	 * @return
	 * @throws IOException
	 */
	public Locator 拍摄封面() throws IOException {
		Locator locator = getLocator("拍摄封面");
		return locator;
	}

	/***
	 * com.letv.whatslive:id/privacy_toggle
	 * 
	 * @return
	 * @throws IOException
	 */
	public Locator 私密() throws IOException {
		Locator locator = getLocator("私密");
		return locator;
	}

	/***
	 * com.letv.whatslive:id/setlive_broadcast
	 * 
	 * @return
	 * @throws IOException
	 */
	public Locator 立即直播() throws IOException {
		Locator locator = getLocator("立即直播");
		return locator;
	}

	/***
	 * com.letv.whatslive:id/close_stream_iv
	 * 
	 * @return
	 * @throws IOException
	 */
	public Locator 关闭() throws IOException {
		Locator locator = getLocator("关闭");
		return locator;
	}

	/***
	 * com.letv.whatslive:id/dialog_right_btn
	 * 
	 * @return
	 * @throws IOException
	 */
	public Locator 确认关闭() throws IOException {
		Locator locator = getLocator("确认关闭");
		return locator;
	}

	/***
	 * com.letv.whatslive:id/livend_confirm_btn
	 * 
	 * @return
	 * @throws IOException
	 */
	public Locator 确定() throws IOException {
		Locator locator = getLocator("确定");
		return locator;
	}
}
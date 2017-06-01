package com.ugc.utils;

public class Locator {
	private String value;
	private int timout;
	private String locatorName;
	private ByType type;

	/*
	 * create a enum variable for By
	 */
	public enum ByType {
		xpath, id, linkText, name, className, cssSelector, partialLinkText, tagName
	}

	public Locator() {
	}

	/*
	 * defauLt Locator ,use Xpath
	 * 
	 * @param element
	 */
	public Locator(String element) {
		this.value = element;
		this.timout = 3;
		this.type = ByType.xpath;
	}

	public Locator(String element, int waitSec) {
		this.timout = waitSec;
		this.value = element;
		this.type = ByType.xpath;
	}

	public Locator(String element, int waitSec, ByType byType) {
		this.timout = waitSec;
		this.value = element;
		this.type = byType;
	}

	public Locator(String element, int waitSec, ByType byType, String locatorName) {
		this.timout = waitSec;
		this.value = element;
		this.type = byType;
		this.locatorName = locatorName;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setTimout(int timout) {
		this.timout = timout;
	}

	public int getTimout() {
		return timout;
	}

	public void setLocatorName(String locatorName) {
		this.locatorName = locatorName;
	}

	public String getLocalorName() {
		return locatorName;
	}

	public ByType getType() {
		return type;
	}

	public void setType(ByType byType) {
		this.type = byType;
	}

	public String getElement() {
		return value;
	}

	public int getWaitSec() {
		return timout;
	}

}

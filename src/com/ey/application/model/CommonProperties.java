package com.ey.application.model;

public class CommonProperties {
	private String workingDir;
	private String imageDir;
	
	public CommonProperties()
	{
		workingDir = System.getProperty("user.dir");
		imageDir = workingDir+"\\IMAGE\\";
	}

	public String getWorkingDir() {
		return workingDir;
	}

	public String getImageDir() {
		return imageDir;
	}
}

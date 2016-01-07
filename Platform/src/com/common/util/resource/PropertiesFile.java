package com.common.util.resource;

import com.common.constants.Constants;

public abstract class PropertiesFile {  
	public static final String MODEL = "model";
	public static final String JDBC = "jdbc";
	public static final String MSG = "message";
	public static final String SERVLET = "servlet";
	public static final String DBTYPE = "datatype";
	public static final String CLASS_PATH = Constants.getRootPath() +  "/WEB-INF/classes";
	
	public static final String INIT_PATH = CLASS_PATH + "/resource/init";
		
	public static final String PLUGIN_PATH = CLASS_PATH + "/resource/plugin";
}

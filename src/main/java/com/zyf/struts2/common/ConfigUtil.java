package com.zyf.struts2.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/** 属性文件读取工具 **/
public class ConfigUtil {
	public static Logger log = Logger.getLogger("ConfigUtil");
	public static String separator = File.separator;

	/*** 默认在SRC下读取，生产系统在conf下读取 **/
	public static Properties getProperties(String propertiesFileName)
			throws IOException {
		InputStream in = null;
		Properties p = null;
		File configFile = null;

		/*** 当前路径的上一级找配置文件路径 ****/
		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir");
		configFile = new File(propertiesFileName);
		log.info("Config path=" + configFile.getAbsolutePath());
		// System.out.println("Config path="+configFile.getAbsolutePath());
		if (configFile.exists() == false) {
			String path = new File(userDir).getParentFile().getAbsolutePath()
					+ separator + "conf" + separator + propertiesFileName;
			log.info("properties path:" + path);
			configFile = new File(path);
			if (configFile.exists() == false) {
				log.fatal("Config file does not exist["
						+ configFile.getAbsolutePath() + "]");
				if (configFile.createNewFile())
					log.info("Create Config File["
							+ configFile.getAbsolutePath() + "] Success");
				else {
					log.fatal("Create Config File["
							+ configFile.getAbsolutePath() + "] Failed");
					return null;
				}
			}
		}
		p = new Properties();
		in = new BufferedInputStream(new FileInputStream(configFile
				.getAbsolutePath()));
		p.load(in);
		in.close();
		return p;
	}

	public static File getConfigFile(String fileName) throws IOException {
		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir");
		File configFile = new File(userDir + separator + "src" + separator
				+ fileName);
		if (configFile.exists() == false)
			configFile = new File(new File(userDir).getParentFile()
					.getAbsolutePath()
					+ separator + "conf" + separator + fileName);
		if (configFile.exists())
			return configFile;
		else
			return null;
	}

}

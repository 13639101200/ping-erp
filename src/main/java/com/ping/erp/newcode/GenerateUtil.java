package com.ping.erp.newcode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

/**
 * 代码生成工具
 * 
 * @author Ping
 *
 */
public class GenerateUtil {

	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/ping_erp_dev";
	private final static String USER = "ping_erp_dev";
	private final static String PASSWORD = "ping_erp_dev";

	private final static String JAVA_PATH = "E:" + File.separator + "Git Repository" + File.separator + "ping-erp" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
	private final static String UI_PATH = "E:" + File.separator + "Git Repository" + File.separator + "ping-erp" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;

	private final static String VERSION = "1.2.4-RELEASE";
	private final static String TIME = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	private final static String AUTHOR = "Ping";
	private final static String PHONE = "13639101200";
	private final static String EMAIL = "13639101200@163.com";

	private final static String PACKAGE = "com.ping.erp";
	private final static String PARENT_MODULE = "system";
	private final static String CHILD_MODULE = "auth";
	private final static String TABLE = "";

	public static void main(String[] args) throws SQLException, ClassNotFoundException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		TabInfo tabInfo = getTabInfo();
		generateDomain(tabInfo);
		generateRepository(tabInfo);
		generateDao(tabInfo);
		generateDaoImpl(tabInfo);
		generateService(tabInfo);
		generateServiceImpl(tabInfo);
		generateController(tabInfo);
		generateIndex(tabInfo);
		generateEdit(tabInfo);
		generateJs(tabInfo);
		System.out.println("-- 自动生成完成 --");
	}

	/**
	 * 生成页面脚本
	 * 
	 * @param tabInfo 表信息
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateJs(TabInfo tabInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String templatePath = "ftl" + File.separator;
		String templateName = "js.ftl";
		String generatePath = UI_PATH + "static" + File.separator + "js" + File.separator + PARENT_MODULE + File.separator + tabInfo.getLowerTabName() + File.separator;
		String generateFile = "index.js";

		File file = new File(generatePath);
		file.mkdirs();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tabInfo", tabInfo);
		data.put("version", VERSION);
		data.put("time", TIME);
		data.put("author", AUTHOR);
		data.put("phone", PHONE);
		data.put("email", EMAIL);
		data.put("package", PACKAGE);
		data.put("parent_module", PARENT_MODULE);
		data.put("child_module", CHILD_MODULE);

		generateFile(templatePath, templateName, generatePath, generateFile, data);
	}

	/**
	 * 生成编辑界面
	 * 
	 * @param tabInfo 表信息
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateEdit(TabInfo tabInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String templatePath = "ftl" + File.separator;
		String templateName = "edit.ftl";
		String generatePath = UI_PATH + "templates" + File.separator + PARENT_MODULE + File.separator + tabInfo.getLowerTabName() + File.separator;
		String generateFile = "edit.html";

		File file = new File(generatePath);
		file.mkdirs();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tabInfo", tabInfo);
		data.put("version", VERSION);
		data.put("time", TIME);
		data.put("author", AUTHOR);
		data.put("phone", PHONE);
		data.put("email", EMAIL);
		data.put("package", PACKAGE);
		data.put("parent_module", PARENT_MODULE);
		data.put("child_module", CHILD_MODULE);

		generateFile(templatePath, templateName, generatePath, generateFile, data);
	}

	/**
	 * 生成分页界面
	 * 
	 * @param tabInfo 表信息
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateIndex(TabInfo tabInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String templatePath = "ftl" + File.separator;
		String templateName = "index.ftl";
		String generatePath = UI_PATH + "templates" + File.separator + PARENT_MODULE + File.separator + tabInfo.getLowerTabName() + File.separator;
		String generateFile = "index.html";

		File file = new File(generatePath);
		file.mkdirs();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tabInfo", tabInfo);
		data.put("version", VERSION);
		data.put("time", TIME);
		data.put("author", AUTHOR);
		data.put("phone", PHONE);
		data.put("email", EMAIL);
		data.put("package", PACKAGE);
		data.put("parent_module", PARENT_MODULE);
		data.put("child_module", CHILD_MODULE);

		generateFile(templatePath, templateName, generatePath, generateFile, data);
	}

	/**
	 * 生成控制器
	 * 
	 * @param tabInfo 表信息
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateController(TabInfo tabInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String templatePath = "ftl" + File.separator;
		String templateName = "controller.ftl";
		String generatePath = JAVA_PATH + PACKAGE.replace(".", File.separator) + File.separator + "web" + File.separator + PARENT_MODULE + File.separator;
		String generateFile = tabInfo.getUpperTabName() + "Controller.java";

		File file = new File(generatePath);
		file.mkdirs();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tabInfo", tabInfo);
		data.put("version", VERSION);
		data.put("time", TIME);
		data.put("author", AUTHOR);
		data.put("phone", PHONE);
		data.put("email", EMAIL);
		data.put("package", PACKAGE);
		data.put("parent_module", PARENT_MODULE);
		data.put("child_module", CHILD_MODULE);

		generateFile(templatePath, templateName, generatePath, generateFile, data);
	}

	/**
	 * 生成业务服务接口实现
	 * 
	 * @param tabInfo 表信息
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateServiceImpl(TabInfo tabInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String templatePath = "ftl" + File.separator;
		String templateName = "serviceimpl.ftl";
		String generatePath = JAVA_PATH + PACKAGE.replace(".", File.separator) + File.separator + PARENT_MODULE + File.separator + CHILD_MODULE + File.separator + "service" + File.separator + "impl" + File.separator;
		String generateFile = tabInfo.getUpperTabName() + "ServiceImpl.java";

		File file = new File(generatePath);
		file.mkdirs();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tabInfo", tabInfo);
		data.put("version", VERSION);
		data.put("time", TIME);
		data.put("author", AUTHOR);
		data.put("phone", PHONE);
		data.put("email", EMAIL);
		data.put("package", PACKAGE);
		data.put("parent_module", PARENT_MODULE);
		data.put("child_module", CHILD_MODULE);

		generateFile(templatePath, templateName, generatePath, generateFile, data);
	}

	/**
	 * 生成业务服务接口
	 * 
	 * @param tabInfo 表信息
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateService(TabInfo tabInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String templatePath = "ftl" + File.separator;
		String templateName = "service.ftl";
		String generatePath = JAVA_PATH + PACKAGE.replace(".", File.separator) + File.separator + PARENT_MODULE + File.separator + CHILD_MODULE + File.separator + "service" + File.separator;
		String generateFile = tabInfo.getUpperTabName() + "Service.java";

		File file = new File(generatePath);
		file.mkdirs();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tabInfo", tabInfo);
		data.put("version", VERSION);
		data.put("time", TIME);
		data.put("author", AUTHOR);
		data.put("phone", PHONE);
		data.put("email", EMAIL);
		data.put("package", PACKAGE);
		data.put("parent_module", PARENT_MODULE);
		data.put("child_module", CHILD_MODULE);

		generateFile(templatePath, templateName, generatePath, generateFile, data);
	}

	/**
	 * 生成数据访问接口实现
	 * 
	 * @param tabInfo 表信息
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateDaoImpl(TabInfo tabInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String templatePath = "ftl" + File.separator;
		String templateName = "daoimpl.ftl";
		String generatePath = JAVA_PATH + PACKAGE.replace(".", File.separator) + File.separator + PARENT_MODULE + File.separator + CHILD_MODULE + File.separator + "dao" + File.separator + "impl" + File.separator;
		String generateFile = tabInfo.getUpperTabName() + "DaoImpl.java";

		File file = new File(generatePath);
		file.mkdirs();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tabInfo", tabInfo);
		data.put("version", VERSION);
		data.put("time", TIME);
		data.put("author", AUTHOR);
		data.put("phone", PHONE);
		data.put("email", EMAIL);
		data.put("package", PACKAGE);
		data.put("parent_module", PARENT_MODULE);
		data.put("child_module", CHILD_MODULE);

		generateFile(templatePath, templateName, generatePath, generateFile, data);
	}

	/**
	 * 生成数据访问接口
	 * 
	 * @param tabInfo 表信息
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateDao(TabInfo tabInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String templatePath = "ftl" + File.separator;
		String templateName = "dao.ftl";
		String generatePath = JAVA_PATH + PACKAGE.replace(".", File.separator) + File.separator + PARENT_MODULE + File.separator + CHILD_MODULE + File.separator + "dao" + File.separator;
		String generateFile = tabInfo.getUpperTabName() + "Dao.java";

		File file = new File(generatePath);
		file.mkdirs();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tabInfo", tabInfo);
		data.put("version", VERSION);
		data.put("time", TIME);
		data.put("author", AUTHOR);
		data.put("phone", PHONE);
		data.put("email", EMAIL);
		data.put("package", PACKAGE);
		data.put("parent_module", PARENT_MODULE);
		data.put("child_module", CHILD_MODULE);

		generateFile(templatePath, templateName, generatePath, generateFile, data);
	}

	/**
	 * 生成数据存储接口
	 * 
	 * @param tabInfo 表信息
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateRepository(TabInfo tabInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String templatePath = "ftl" + File.separator;
		String templateName = "repository.ftl";
		String generatePath = JAVA_PATH + PACKAGE.replace(".", File.separator) + File.separator + PARENT_MODULE + File.separator + CHILD_MODULE + File.separator + "repository" + File.separator;
		String generateFile = tabInfo.getUpperTabName() + "Repository.java";

		File file = new File(generatePath);
		file.mkdirs();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tabInfo", tabInfo);
		data.put("version", VERSION);
		data.put("time", TIME);
		data.put("author", AUTHOR);
		data.put("phone", PHONE);
		data.put("email", EMAIL);
		data.put("package", PACKAGE);
		data.put("parent_module", PARENT_MODULE);
		data.put("child_module", CHILD_MODULE);

		generateFile(templatePath, templateName, generatePath, generateFile, data);
	}

	/**
	 * 生成数据实体类
	 * 
	 * @param tabInfo 表信息
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateDomain(TabInfo tabInfo) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		String templatePath = "ftl" + File.separator;
		String templateName = "domain.ftl";
		String generatePath = JAVA_PATH + PACKAGE.replace(".", File.separator) + File.separator + PARENT_MODULE + File.separator + CHILD_MODULE + File.separator + "domain" + File.separator;
		String generateFile = tabInfo.getUpperTabName() + ".java";

		File file = new File(generatePath);
		file.mkdirs();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tabInfo", tabInfo);
		data.put("version", VERSION);
		data.put("time", TIME);
		data.put("author", AUTHOR);
		data.put("phone", PHONE);
		data.put("email", EMAIL);
		data.put("package", PACKAGE);
		data.put("parent_module", PARENT_MODULE);
		data.put("child_module", CHILD_MODULE);

		generateFile(templatePath, templateName, generatePath, generateFile, data);
	}

	/**
	 * 生成文件
	 * 
	 * @param templatePath 模板文件路径（相对路径）
	 * @param templateName 模板文件名
	 * @param generatePath 生成文件路径（绝对路径）
	 * @param generateFile 生成文件名
	 * @param data         数据对像
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void generateFile(String templatePath, String templateName, String generatePath, String generateFile, Map<String, Object> data) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setCacheStorage(NullCacheStorage.INSTANCE);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setTemplateLoader(new ClassTemplateLoader(GenerateUtil.class, templatePath));
		Template template = cfg.getTemplate(templateName);

		FileOutputStream file = new FileOutputStream(generatePath + generateFile);
		OutputStreamWriter writer = new OutputStreamWriter(file, "UTF-8");
		Writer out = new BufferedWriter(writer);

		template.process(data, out);
	}

	/**
	 * 获取表信息
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static TabInfo getTabInfo() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet trs = metaData.getTables(null, null, TABLE, new String[] { "TABLE" });
		ResultSet krs = metaData.getPrimaryKeys(null, null, TABLE);
		ResultSet crs = metaData.getColumns(null, null, TABLE, null);
		TabInfo tab = new TabInfo();
		while (trs.next()) {
			tab.setTabName(trs.getString("TABLE_NAME"));
			tab.setUpperTabName(humpConverUpper(trs.getString("TABLE_NAME")));
			tab.setLowerTabName(humpConverLower(trs.getString("TABLE_NAME")));
			tab.setTabComment(trs.getString("REMARKS"));
		}
		while (krs.next()) {
			tab.setTabKey(krs.getString("COLUMN_NAME"));
			tab.setUpperTabKey(humpConverUpper(krs.getString("COLUMN_NAME")));
			tab.setLowerTabKey(humpConverLower(krs.getString("COLUMN_NAME")));
		}
		while (crs.next()) {
			ColInfo col = new ColInfo();
			col.setColName(crs.getString("COLUMN_NAME"));
			col.setUpperColName(humpConverUpper(crs.getString("COLUMN_NAME")));
			col.setLowerColName(humpConverLower(crs.getString("COLUMN_NAME")));
			col.setColComent(crs.getString("REMARKS"));
			col.setColType(crs.getString("TYPE_NAME"));
			tab.getColumns().add(col);
		}
		connection.close();
		trs.close();
		krs.close();
		crs.close();
		return tab;
	}

	/**
	 * 大驼峰转换
	 * 
	 * @param str
	 * @return
	 */
	private static String humpConverUpper(String str) {
		String[] arrStr = str.split("_");
		String newStr = "";
		for (String s : arrStr) {
			newStr += s.substring(0, 1).toUpperCase() + s.substring(1);
		}
		return newStr;
	}

	/**
	 * 小驼峰转换
	 * 
	 * @param str
	 * @return
	 */
	private static String humpConverLower(String str) {
		str = humpConverUpper(str);
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

}

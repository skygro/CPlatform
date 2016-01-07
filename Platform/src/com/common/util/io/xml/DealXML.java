package com.common.util.io.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.SystemException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.util.data.ValidateUtil;
import com.common.util.data.map.BaseDmp;
import com.common.util.data.map.Dmp;
import com.common.util.page.Pager;

public class DealXML {
	 private static final String ENCODE = "UTF-8";
	  private static Logger log = LoggerFactory.getLogger(DealXML.class);

	  public static Document getDocument(String xmlFile, String encode)
	  {
	    Document document = null;
	    try {
	      File file = new File(xmlFile);
	      if (file.exists()) {
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	          new FileInputStream(file), encode));
	        SAXReader reader = new SAXReader();
	        reader.setEncoding(encode);
	        document = reader.read(br);
	      }
	    } catch (Exception e) {
	      log.error("读取classpath下" + xmlFile + "文件发生异常，请检查CLASSPATH和文件名是否存在！");
	      e.printStackTrace();
	    }
	    return document;
	  }

	  public static Document getDocument(File file) {
	    Document document = null;
	    try {
	      if (file.exists()) {
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	          new FileInputStream(file), "UTF-8"));
	        SAXReader reader = new SAXReader();
	        reader.setEncoding("UTF-8");
	        document = reader.read(br);
	      }
	    } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (DocumentException e) {
	      e.printStackTrace();
	    }
	    return document;
	  }

	  public static Document getDocument(String xmlFile) {
	    return getDocument(xmlFile, "UTF-8");
	  }

	  public static Document getDocument(InputStream is)
	    throws DocumentException
	  {
	    SAXReader reader = new SAXReader();
	    Document doc = reader.read(is);
	    return doc;
	  }

	  public static Document parseContextToDocument(String xmlContent)
	    throws DocumentException
	  {
	    Document d = DocumentHelper.parseText(xmlContent);
	    return d;
	  }

	  public static String transformDOM(Document d)
	  {
	    String xmlContent = "";
	    xmlContent = d.asXML();
	    return xmlContent;
	  }

	  public static int nodeNum(Element parentEle, String childName)
	  {
	    if (parentEle == null)
	      return 0;
	    return parentEle.elements(childName).size();
	  }

	  public static int nodeNum(Element parentEle, String nodeName, String attrName)
	  {
	    int num = 0;
	    Iterator nodeIter = parentEle.elementIterator(nodeName);
	    Element nodeEle = null;
	    Iterator attrIter;
	    for (; nodeIter.hasNext(); 
	      attrIter.hasNext())
	    {
	      nodeEle = (Element)nodeIter.next();
	      attrIter = nodeEle.attributeIterator();
	      continue;
	    }

	    return num;
	  }

	  public static Element getNode(Document d, String eleName)
	  {
	    Element ele = (Element)d.selectSingleNode(eleName);
	    return ele;
	  }

	  public static void addNode(Element parentEle, String eleName, String eleValue)
	  {
	    Element newEle = parentEle.addElement(eleName);
	    newEle.setText(eleValue);
	  }

	  public static void addAttribute(Element ele, String attributeName, String attributeValue)
	  {
	    ele.addAttribute(attributeName, attributeValue);
	  }

	  public static void removeNode(Element parentEle, String attrName, String attrValue)
	  {
	    Iterator iter = parentEle.elementIterator();
	    Element delEle = null;
	    while (iter.hasNext()) {
	      Element tmp = (Element)iter.next();
	      if ((tmp.getName().equals(attrName)) && 
	        (tmp.getText().equals(attrValue))) {
	        delEle = tmp;
	      }
	    }
	    if (delEle != null)
	      parentEle.remove(delEle);
	  }

	  public static void removeFirstNode(Element parentEle, String nodeName, String attrName)
	  {
	    Iterator nodeIter = parentEle.elementIterator(nodeName);
	    Element delEle = null;
	    Iterator attrIter;
	    for (; nodeIter.hasNext(); 
	      attrIter.hasNext())
	    {
	      delEle = (Element)nodeIter.next();
	      attrIter = delEle.attributeIterator();
	      continue;
	    }
	  }

	  public static void removeNode(Element parentEle, String nodeName, String attrName, String attrValue)
	  {
	    Iterator nodeIter = parentEle.elementIterator(nodeName);
	    Element delEle = null;
	    Iterator attrIter;
	    for (; nodeIter.hasNext(); 
	      attrIter.hasNext())
	    {
	      delEle = (Element)nodeIter.next();
	      attrIter = delEle.attributeIterator();
	      continue;
	    }
	  }

	  public static void removeAttr(Element ele, String attributeName)
	  {
	    Attribute att = ele.attribute(attributeName);
	    ele.remove(att);
	  }

	  public static void setNodeText(Element ele, String newValue)
	  {
	    ele.setText(newValue);
	  }

	  public static void setAttribute(Element ele, String attributeName, String attributeValue)
	  {
	    Attribute att = ele.attribute(attributeName);
	    att.setText(attributeValue);
	  }

	  public static void writeToXml(Document document, String path) throws SystemException
	  {
	    writeToXml(path, "UTF-8", document);
	  }

	  public static void writeToXml(String fullFileName, String encoding, Document document)
	    throws SystemException
	  {
	    if (ValidateUtil.isEmpty(fullFileName)) {
	      throw new SystemException("参数不能为空");
	    }
	    fullFileName = fullFileName.replaceAll("/", "\\\\");
	    int index = fullFileName.lastIndexOf(File.separator);
	    writeToXml(fullFileName.substring(0, index), 
	      fullFileName.substring(index), encoding, document);
	  }

	  public static void writeToXml(String foldPath, String fileName, String encoding, Document document)
	    throws SystemException
	  {
	    if ((ValidateUtil.isEmpty(foldPath)) || (ValidateUtil.isEmpty(fileName))) {
	      throw new SystemException("参数不能为空");
	    }
	    File dirPath = new File(foldPath);
	    if (!dirPath.exists()) {
	      dirPath.mkdir();
	    }
	    XMLWriter writer = null;
	    OutputFormat format = null;
	    try {
	      format = OutputFormat.createCompactFormat();
	      format.setEncoding(encoding);
	      if (!foldPath.endsWith(File.separator)) {
	        foldPath = foldPath + File.separator;
	      }
	      if (!fileName.toLowerCase().endsWith(".xml")) {
	        fileName = fileName + ".xml";
	      }
	      FileOutputStream outstream = new FileOutputStream(foldPath + 
	        fileName);
	      writer = new XMLWriter(outstream, format);
	      writer.write(document);
	      writer.close();
	    } catch (Exception e) {
	      throw new SystemException("文件路径错误");
	    }
	  }

	  public static Document createDoc(Pager page)
	  {
	    Document doc = DocumentFactory.getInstance().createDocument("UTF-8");
	    Element root = doc.addElement("root");
	    Element totalRows = root.addElement("totalRows");
	    totalRows.setText(String.valueOf(page.getTotalRows()));
	    Element totalPages = root.addElement("totalPages");
	    totalPages.setText(String.valueOf(page.getTotalPages()));
	    Element currentPage = root.addElement("currentPage");
	    currentPage.setText(String.valueOf(page.getCurrentPage()));
	    Element startRow = root.addElement("startRow");
	    startRow.setText(String.valueOf(page.getStartRow()));
	    Element datalist = root.addElement("datalist");
	    List list = page.getList();
	    getDataListXml(datalist, list, false);
	    return doc;
	  }

	  public static Document createDoc(List list)
	  {
	    Document doc = DocumentFactory.getInstance().createDocument("UTF-8");
	    Element root = doc.addElement("root");
	    Element datalist = root.addElement("datalist");
	    getDataListXml(datalist, list, false);
	    return doc;
	  }

	  public static Document createDocAsAttribute(List list)
	  {
	    Document doc = DocumentFactory.getInstance().createDocument("UTF-8");
	    Element root = doc.addElement("root");
	    Element datalist = root.addElement("datalist");
	    getDataListXml(datalist, list, true);
	    return doc;
	  }

	  public static void getDataListXml(Element datalist, List list, boolean isattribute)
	  {
	    String title = "";
	    String value = "";
	    String methodtitle = "";
	    try {
	      for (int i = 0; i < list.size(); i++) {
	        Object obj = list.get(i);
	        Element sublist = datalist.addElement("data");
	        if ((obj instanceof HashMap)) {
	          Iterator it = ((Map)obj).entrySet().iterator();
	          while (it.hasNext()) {
	            Map.Entry entry = (Map.Entry)it.next();
	            title = (String)entry.getKey();
	            if (entry.getValue() == null) {
	              value = "";
	            }
	            else if ((entry.getValue() instanceof String)) {
	              value = (String)entry.getValue();
	            } else if ((entry.getValue() instanceof BigDecimal)) {
	              value = ((BigDecimal)entry.getValue())
	                .toString(); } else {
	              if ((entry.getValue() instanceof ArrayList)) {
	                getDataListXml(sublist, 
	                  (ArrayList)entry.getValue(), 
	                  isattribute);
	                continue;
	              }
	              value = entry.getValue().toString();
	            }

	            if (isattribute) {
	              sublist.addAttribute(title, value);
	            } else {
	              Element el = sublist.addElement(title);
	              el.setText(value);
	            }
	          }
	        } else if ((obj instanceof ArrayList)) {
	          getDataListXml(sublist, (ArrayList)obj, isattribute);
	        } else {
	          Field[] fileds = obj.getClass().getDeclaredFields();
	          for (int j = 0; j < fileds.length; j++) {
	            title = fileds[j].getName();
	            Class type = fileds[j].getType();
	            if (type.equals(Boolean.TYPE))
	              methodtitle = "is";
	            else {
	              methodtitle = "get";
	            }
	            methodtitle = methodtitle + title.substring(0, 1).toUpperCase() + 
	              title.substring(1, title.length());
	            Method method = obj.getClass().getMethod(methodtitle, 
	              null);
	            if (method.invoke(obj, new Object[0]) == null)
	              value = "";
	            else {
	              value = method.invoke(obj, new Object[0]).toString();
	            }
	            if (isattribute) {
	              sublist.addAttribute(title, value);
	            } else {
	              Element el = sublist.addElement(title);
	              el.setText(value);
	            }
	          }
	        }
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      System.out.println("title=" + title + ";methodtitle=" + methodtitle + 
	        ";value=" + value);
	    }
	  }

	  public static String maptoXML(Map map)
	  {
	    Document document = DocumentFactory.getInstance().createDocument(
	      "UTF-8");
	    Element root = document.addElement("root");
	    Iterator it = map.entrySet().iterator();
	    Element dataElement = root.addElement("data");
	    while (it.hasNext()) {
	      Map.Entry entry = (Map.Entry)it.next();
	      if ((entry.getValue() instanceof String))
	        dataElement.addAttribute((String)entry.getKey(), entry.getValue()
	          .toString());
	      else if ((entry.getValue() instanceof ArrayList)) {
	        getDataListXml(dataElement, (List)entry.getValue(), true);
	      }
	    }
	    return document.asXML();
	  }

	  public static String listtoXml(List list)
	  {
	    Document doc = DocumentFactory.getInstance().createDocument("UTF-8");
	    Element root = doc.addElement("nodes");
	    Iterator localIterator2;
	    for (Iterator localIterator1 = list.iterator(); localIterator1.hasNext(); 
	      localIterator2.hasNext())
	    {
	      Object o = localIterator1.next();
	      Element nodeElement = root.addElement("node");
	      localIterator2 = ((Map)o).keySet().iterator(); continue;
	    }

	    return doc.asXML();
	  }

	  public static Map xmltoMap(String xml)
	  {
	    try
	    {
	      Map map = new HashMap();
	      Document doc = DocumentHelper.parseText(xml);
	      Element root = doc.getRootElement();
	      List node = root.elements();
	      for (Iterator it = node.iterator(); it.hasNext(); ) {
	        Element el = (Element)it.next();
	        map.put(el.attributeValue("label"), el.getText());
	        el = null;
	      }
	      node = null;
	      root = null;
	      doc = null;
	      return map;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

	  public static List xmltoList(String xml)
	  {
	    try
	    {
	      List list = new ArrayList();
	      Document doc = DocumentHelper.parseText(xml);
	      Element root = doc.getRootElement();
	      List nodes = root.elements();
	      for (Iterator its = nodes.iterator(); its.hasNext(); ) {
	        Element nodeElement = (Element)its.next();
	        Map map = xmltoMap(nodeElement.asXML());
	        list.add(map);
	        map = null;
	      }
	      nodes = null;
	      root = null;
	      doc = null;
	      return list;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

	  public static final Dmp parseXml2DmpBasedNode(String pStrXml)
	  {
	    Dmp outDmp = new BaseDmp();
	    String strTitle = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	    Document document = null;
	    try {
	      if (pStrXml.indexOf("<?xml") < 0)
	        pStrXml = strTitle + pStrXml;
	      document = DocumentHelper.parseText(pStrXml);
	    } catch (DocumentException e) {
	      log.error("==开发人员请注意:==\n将XML格式的字符串转换为XML DOM对象时发生错误啦!\n详细错误信息如下:");

	      e.printStackTrace();
	    }

	    Element elNode = document.getRootElement();

	    for (Iterator it = elNode.elementIterator(); it.hasNext(); ) {
	      Element leaf = (Element)it.next();
	      outDmp.put(leaf.getName().toLowerCase(), leaf.getData());
	    }
	    return outDmp;
	  }

	  public static final Dmp parseXml2LMapBasedNode(String pStrXml, String pXPath)
	  {
	    Dmp out = new BaseDmp();
	    String strTitle = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	    Document document = null;
	    try {
	      if (pStrXml.indexOf("<?xml") < 0)
	        pStrXml = strTitle + pStrXml;
	      document = DocumentHelper.parseText(pStrXml);
	    } catch (DocumentException e) {
	      log.error("==开发人员请注意:==\n将XML格式的字符串转换为XML DOM对象时发生错误啦!\n详细错误信息如下:");

	      e.printStackTrace();
	    }

	    Element elNode = document.getRootElement();

	    for (Iterator it = elNode.elementIterator(); it.hasNext(); ) {
	      Element leaf = (Element)it.next();
	      out.put(leaf.getName().toLowerCase(), leaf.getData());
	    }
	    return out;
	  }

	  public static final Map parseXML2Map(Element node)
	  {
	    Map map = new HashMap();
	    if (node != null) {
	      for (Iterator it = node.elementIterator(); it.hasNext(); ) {
	        Element leaf = (Element)it.next();
	        map.put(leaf.getName().toLowerCase(), leaf.getData());
	      }
	    }
	    return map;
	  }

	  public static final Dmp parseXml2LMapBasedProperty(String pStrXml, String pXPath)
	  {
	    Dmp out = new BaseDmp();
	    String strTitle = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	    Document document = null;
	    try {
	      if (pStrXml.indexOf("<?xml") < 0)
	        pStrXml = strTitle + pStrXml;
	      document = DocumentHelper.parseText(pStrXml);
	    } catch (DocumentException e) {
	      log.error("==开发人员请注意:==\n将XML格式的字符串转换为XML DOM对象时发生错误啦!\n详细错误信息如下:");

	      e.printStackTrace();
	    }

	    Element elRoot = (Element)document.selectSingleNode(pXPath);

	    for (Iterator it = elRoot.attributeIterator(); it.hasNext(); ) {
	      Attribute attribute = (Attribute)it.next();
	      out.put(attribute.getName().toLowerCase(), attribute.getData());
	    }
	    return out;
	  }

	  public static final String parseLMap2Xml(Dmp map, String pRootNodeName)
	  {
	    if (ValidateUtil.isEmpty(map)) {
	      log.warn("传入的LMap对象为空,请确认");
	      return "<root />";
	    }
	    Document document = DocumentHelper.createDocument();

	    document.addElement(pRootNodeName);
	    Element root = document.getRootElement();
	    Iterator keyIterator = map.keySet().iterator();
	    while (keyIterator.hasNext()) {
	      String key = (String)keyIterator.next();
	      String value = map.getAsString(key);
	      Element leaf = root.addElement(key);
	      leaf.setText(value);
	    }

	    String outXml = document.asXML().substring(39);
	    return outXml;
	  }

	  public static final String parseLMap2Xml(Dmp map, String pRootNodeName, String pFirstNodeName)
	  {
	    if (ValidateUtil.isEmpty(map)) {
	      log.warn("传入的Dmp对象为空,请确认");
	      return "<root />";
	    }
	    Document document = DocumentHelper.createDocument();

	    document.addElement(pRootNodeName);
	    Element root = document.getRootElement();
	    root.addElement(pFirstNodeName);
	    Element firstEl = (Element)document.selectSingleNode("/" + 
	      pRootNodeName + "/" + pFirstNodeName);
	    Iterator keyIterator = map.keySet().iterator();
	    while (keyIterator.hasNext()) {
	      String key = (String)keyIterator.next();
	      String value = map.getAsString(key);
	      firstEl.addAttribute(key, value);
	    }

	    String outXml = document.asXML().substring(39);
	    return outXml;
	  }

	  public static final String parseList2Xml(List pList, String pRootNodeName, String pFirstNodeName)
	  {
	    Document document = DocumentHelper.createDocument();
	    Element elRoot = document.addElement(pRootNodeName);
	    for (int i = 0; i < pList.size(); i++) {
	      Dmp dmp = (Dmp)pList.get(i);
	      Element elRow = elRoot.addElement(pFirstNodeName);
	      Iterator it = dmp.entrySet().iterator();
	      while (it.hasNext()) {
	        Map.Entry entry = (Map.Entry)it.next();
	        elRow.addAttribute((String)entry.getKey(), 
	          String.valueOf(entry.getValue()));
	      }
	    }
	    String outXml = document.asXML().substring(39);
	    return outXml;
	  }

	  public static final String parseList2XmlBasedNode(List pList, String pRootNodeName, String pFirstNodeName)
	  {
	    Document document = DocumentHelper.createDocument();
	    Element output = document.addElement(pRootNodeName);
	    for (int i = 0; i < pList.size(); i++) {
	      Dmp dmp = (Dmp)pList.get(i);
	      Element elRow = output.addElement(pFirstNodeName);
	      Iterator it = dmp.entrySet().iterator();
	      while (it.hasNext()) {
	        Map.Entry entry = (Map.Entry)it.next();
	        Element leaf = elRow.addElement((String)entry.getKey());
	        leaf.setText(String.valueOf(entry.getValue()));
	      }
	    }
	    String outXml = document.asXML().substring(39);
	    return outXml;
	  }

	  public static final List parseXml2List(String pStrXml)
	  {
	    List lst = new ArrayList();
	    String strTitle = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	    Document document = null;
	    try {
	      if (pStrXml.indexOf("<?xml") < 0)
	        pStrXml = strTitle + pStrXml;
	      document = DocumentHelper.parseText(pStrXml);
	    } catch (DocumentException e) {
	      log.error("==开发人员请注意:==\n将XML格式的字符串转换为XML DOM对象时发生错误啦!\n详细错误信息如下:");

	      e.printStackTrace();
	    }

	    Element elRoot = document.getRootElement();

	    Iterator elIt = elRoot.elementIterator();
	    while (elIt.hasNext()) {
	      Element el = (Element)elIt.next();
	      Iterator attrIt = el.attributeIterator();
	      Dmp dmp = new BaseDmp();
	      while (attrIt.hasNext()) {
	        Attribute attribute = (Attribute)attrIt.next();
	        dmp.put(attribute.getName().toLowerCase(), attribute.getData());
	      }
	      lst.add(dmp);
	    }
	    return lst;
	  }

	  public static void main(String[] v) {
	    Document doc = DocumentFactory.getInstance().createDocument("UTF-8");
	    Element root = doc.addElement("root");

	    root.addText("<![CDATA[dsfdsfdsf]]>");
	    System.out.println(root.asXML());
	  }
}

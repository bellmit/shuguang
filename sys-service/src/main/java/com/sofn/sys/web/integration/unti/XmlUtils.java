package com.sofn.sys.web.integration.unti;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class XmlUtils {
	public static String jsonToXml(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		Element root = DocumentHelper.createElement("TreeMap");
		for (String key : jsonObject.keySet()) {
			jsonToElement(jsonObject, key, root);
		}
		return root.asXML();
	}

	public static Element jsonToElement(JSONObject jsonObject, String key, Element element) {
		Object jsonNode = jsonObject.get(key);
		if ((jsonNode instanceof JSONArray)) {
			for (JSONObject childrenJson : (List<JSONObject>) jsonNode) {
				Element nodeElement = element.addElement(key);
				addElement(childrenJson, nodeElement);
			}
		} else if ((jsonNode instanceof JSONObject)) {
			JSONObject childrenJson = (JSONObject) jsonNode;
			addElement(childrenJson, element);
		} else {
			Element nodeElement = element.addElement(key);
			nodeElement.setText(jsonNode.toString());
		}
		return element;
	}

	private static Element addElement(JSONObject childrenJson, Element element) {
		for (String key : childrenJson.keySet()) {
			jsonToElement(childrenJson, key, element);
		}
		return element;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject elementToJson(List<Element> elements) {
		JSONObject jsonObject = new JSONObject();
		for (Element element : elements) {
			List<Element> children = element.elements();
			if (children.size() > 0) {
				jsonObject.put(element.getName(), elementToJson(children));
			} else {
				jsonObject.put(element.getName(), element.getText());
			}
		}
		return jsonObject;
	}

	public static String xmlToJson(String xml) {
		try {
			JSONObject jsonObject = new JSONObject();
			Document document = DocumentHelper.parseText(xml);

			Element root = document.getRootElement();
			jsonObject = iterateNodes(root, jsonObject);
			return jsonObject.toJSONString();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONObject iterateNodes(Element node, JSONObject json) {
		String nodeName = node.getName();
		JSONObject newJson;
		if (json.containsKey(nodeName)) {
			Object Object = json.get(nodeName);
			JSONArray array = null;
			if ((Object instanceof JSONArray)) {
				array = (JSONArray) Object;
			} else {
				array = new JSONArray();
				array.add(Object);
			}

			List<Element> listElement = node.elements();
			if (listElement.isEmpty()) {
				String nodeValue = node.getTextTrim();
				array.add(nodeValue);
				json.put(nodeName, array);
				return null;
			}

			newJson = new JSONObject();

			for (Element e : listElement) {
				iterateNodes(e, newJson);
			}
			array.add(newJson);
			json.put(nodeName, array);
			return null;
		}

		List<Element> listElement = node.elements();
		if (listElement.isEmpty()) {
			String nodeValue = node.getTextTrim();
			json.put(nodeName, nodeValue);
			return null;
		}

		JSONObject object = new JSONObject();

		for (Element e : listElement) {
			iterateNodes(e, object);
		}

		if (!"TreeMap".equals(nodeName)) {
			json.put(nodeName, object);
			return null;
		} else {
			return object;
		}
	}
}
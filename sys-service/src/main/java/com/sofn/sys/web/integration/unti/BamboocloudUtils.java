package com.sofn.sys.web.integration.unti;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.banboocloud.Codec.BamboocloudFacade;

public abstract class BamboocloudUtils {
	public static void checkUsernamePassword(String username, String password) {
		System.out.println("username --->" + username + "  password --- >" + password + " ----ok");
	}

	public static String getPlaintext(String ciphertext, String key, String type) {
		return BamboocloudFacade.decrypt(ciphertext, key, type);
	}

	public static Boolean verify(Map<String, Object> reqmap, String type) {
		Map<String, Object> verifymap = new TreeMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = reqmap.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			verifymap.put(key, reqmap.get(key));
		}
		Iterator<String> ittree = verifymap.keySet().iterator();
		while (ittree.hasNext()) {
			String key = (String) ittree.next();
			if (!"signature".equals(key)) {
				sb.append(key).append("=").append(verifymap.get(key)).append("&");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(reqmap.get("signature") + "  now  " + sb.toString());
		return BamboocloudFacade.verify(reqmap.get("signature").toString(), sb.toString(), type);
	}

	public static String getRequestBody(HttpServletRequest request) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String str = "";
		try {
			br = request.getReader();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			if (br != null)
				try {
					br.close();
				} catch (IOException eo) {
					eo.printStackTrace();
				}
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
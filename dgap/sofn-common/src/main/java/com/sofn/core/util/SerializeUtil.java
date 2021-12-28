package com.sofn.core.util;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化辅助类
 * 
 * @author sofn
 * @version $Id: SerializableUtil.java, v 0.1 2014-6-10 下午1:30:39 sofn Exp
 *          $
 */
public final class SerializeUtil {
	private static final Logger logger = Logger.getLogger(SerializingUtil.class);

	private SerializeUtil() {

	}
	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 */
	public static final byte[] toBytes(Object object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			return baos.toByteArray();
		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			try {
				oos.close();
				baos.close();
			} catch (Exception e) {

			}
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static final Object toObject(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			try {
				ois.close();
				bais.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 功能简述: 对实体Bean进行序列化操作.
	 *
	 * @param value
	 *            待转换的实体
	 * @return 转换之后的字节数组
	 * @throws Exception
	 */
	public static byte[] serialize(Object value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		ByteArrayOutputStream byteOut = null;
		ObjectOutputStream ObjOut = null;
		try {
			byteOut = new ByteArrayOutputStream();
			ObjOut = new ObjectOutputStream(byteOut);
			ObjOut.writeObject(value);
			ObjOut.flush();
			byte[] bytes = byteOut.toByteArray();
			return bytes;
		} catch (IOException e) {
			logger.error(value.getClass().getName() + " serialized error !", e);
		} finally {
			try {
				if(byteOut!=null)byteOut.close();
				if(ObjOut!=null)ObjOut.close();
			} catch (IOException e) {
				ObjOut = null;
			}
		}
		return null;
	}

	/**
	 * 功能简述: 将字节数组反序列化为实体Bean.
	 *
	 * @param bytes
	 *            需要进行反序列化的字节数组
	 * @return 反序列化后的实体Bean
	 * @throws Exception
	 */
	public static Object deserialize(byte[] bytes) {
		if(bytes==null){
			return null;
		}
		ObjectInputStream ObjIn = null;
		Object retVal = null;
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
			ObjIn = new ObjectInputStream(byteIn);
			retVal = ObjIn.readObject();
			return retVal;
		} catch (Exception e) {
			logger.error("deserialized error  !", e);
		} finally {
			try {
				if(ObjIn!=null)ObjIn.close();
			} catch (IOException e) {
				ObjIn = null;
			}
		}
		return null;
	}

}

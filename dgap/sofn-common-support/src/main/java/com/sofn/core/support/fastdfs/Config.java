package com.sofn.core.support.fastdfs;

import java.io.Serializable;

public interface Config extends Serializable {
	public static final String FILE_DEFAULT_WIDTH = "120";
	public static final String FILE_DEFAULT_HEIGHT = "120";
	public static final String FILE_DEFAULT_AUTHOR = "sofn";

	public static final String PROTOCOL = "http://";
	public static final String SEPARATOR = "/";

	public static final String TRACKER_NGNIX_PORT = "8080";

	public static final String DFS_CLIENT_CONFIG_FILE = "fdfs_client.conf";
	public static final String DHT_CLIENT_CONFIG_FILE = "fdht_client.conf";
}

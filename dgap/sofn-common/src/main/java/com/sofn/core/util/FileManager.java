/**
 * 
 */
package com.sofn.core.util;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import com.sofn.core.config.Global;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * File Manager used to provide the services to upload / download / delete the files
 * from FastDFS.
 * 
 * <note>In this version, FileManager only support single tracker, will enhance this later...</note>
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class FileManager implements FileManagerConfig {
	private final static Logger logger = LoggerFactory.getLogger(FileManager.class);

	private static final long serialVersionUID = 1L;

	
	private static TrackerClient  trackerClient;
	private static TrackerServer  trackerServer;
	private static StorageServer  storageServer;
	private static StorageClient1 storageClient1;

	static { // Initialize Fast DFS Client configurations
		
		try {
			String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
			String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;
			logger.debug("fdfsClientConfigFilePath:"+fdfsClientConfigFilePath);

			ClientGlobal.init("/etc/fdfs/client.conf");

			TrackerGroup tg = new TrackerGroup(new InetSocketAddress[]{new InetSocketAddress("192.168.21.249", 22122)});
			trackerClient = new TrackerClient(tg);

			trackerServer = trackerClient.getConnection();
			if (trackerServer == null) {
				logger.debug("get fastDFS tracker Connection return null");
				throw new Exception("get fastDFS tracker Connection return null");
			}

			storageServer = trackerClient.getStoreStorage(trackerServer);
			if (storageServer == null) {
				logger.debug("get fastDFS storage Connection return null");
				throw new Exception("get fastDFS storage Connection return null");
			}

			storageClient1 = new StorageClient1(trackerServer, storageServer);

		} catch (Exception e) {
			logger.info("init fastDFS client error!");
			e.printStackTrace();
		}
	}


	public static Map<String,String> upload(HttpServletRequest request, HttpServletResponse response) {
		File tempPathFile=new File(Global.getConfig("fdfs.tmp.path"));
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Set factory constraints
		factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
		factory.setRepository(tempPathFile);// 设置缓冲区目录

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Set overall request size constraint
		upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

		List<FileItem> items = null;// 得到所有的文件
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		if(null==items || items.size()==0){
			return null;
		}
		Map<String,String> map=upload(items);
		return map;
	}
	public static Map<String,String> upload(List<FileItem> items ){
		Iterator<FileItem>  i   = items.iterator();
		Map<String, String> map = new HashMap<>();
		int fileCount=0;
		while (i.hasNext()) {
			try {
				FileItem fi = (FileItem) i.next();
				if(fi.isFormField()){
					//表单数据,不处理
					continue;
				}
				StorageClient1 sc1 = storageClient1;

				String fileName = fi.getName();

				NameValuePair[] metaList = new NameValuePair[3];
				metaList[0]=new NameValuePair("filename",fileName);
				byte[] byteBuffer=new byte[(int)fi.getSize()];
				fi.getInputStream().read(byteBuffer);
				String fileExtName=fileName.substring(fileName.lastIndexOf(".")+1);
				metaList[1]=new NameValuePair("fileExtName",fileExtName);
				metaList[2]=new NameValuePair("fileLength",String.valueOf(fi.getSize()));
				String fileid = sc1.upload_file1(byteBuffer, fileExtName, metaList);

				logger.info("Upload local file " + fileName + " ok, fileid=" + fileid);

				map.put("state","SUCCESS");
				map.put("original",fileName);
				map.put("size",fi.getSize()+"");
				map.put("title",fileid.substring(fileid.lastIndexOf("/")+1));
				map.put("type",fileExtName);
				map.put("url",fileid);
				fileCount++;

			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}

		}
		return map;
	}

	public static byte[] download(String fileId) throws Exception{

		StorageClient1 sc1 = storageClient1;

		byte[] downByte=sc1.download_file1(fileId);
		NameValuePair[] meta_list=sc1.get_metadata1(fileId);
		String filename=meta_list[0].getValue();

		return downByte;
		/*response.addHeader("Content-Disposition", "attachment;filename=" + filename);//设置文件名，attachment和filename之间是分号，注意！

		response.addHeader("Content-Length", (new Long(downByte.length)).toString());//设置大小
		OutputStream outputStream=response.getOutputStream();
		outputStream.write(downByte);*/
	}

	
	public static FileInfo getFile(String fileId) {
		try {
			return storageClient1.get_file_info1(fileId);
		} catch (IOException e) {
			logger.error("IO Exception: Get File from Fast DFS failed", e);
		} catch (Exception e) {
			logger.error("Non IO Exception: Get File from Fast DFS failed", e);
		}
		return null;
	}
	
	public static void deleteFile(String fileId) throws Exception {
		storageClient1.delete_file1(fileId);
	}
	
	public static StorageServer[] getStoreStorages(String groupName) throws IOException {
		return trackerClient.getStoreStorages(trackerServer, groupName);
	}
	
	public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
		return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
	}
}


package com.hening.sale.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.hening.sale.common.exception.ORSException;

@SuppressWarnings("all")
public class FtpClientUtil {
	
	 private FTPClient ftpClient = null;
	 private String server;
	 private int port;
	 private String userName;
	 private String userPassword;
	 
	 public static void main(String[] args) {
		  
		 String service = ConfigUtil.getValue("ftp.picture.service");
		 Integer port = Integer.parseInt(ConfigUtil.getValue("ftp.picture.port"));
		 String username = ConfigUtil.getValue("ftp.picture.username");
		 String password = ConfigUtil.getValue("ftp.picture.password");
			
			FtpClientUtil sc = new FtpClientUtil( service , port , username , password);
			
			 try {
				  if (sc.open()) {
					  
					  //sc.getFtpClient().sendServer("quote PASV");
					  
					  //sc.printWorkingDirectory();
					  String path = sc.currentWorkingDirectory();
					  System.out.println( "current work directory is " + path );
					  
//					  List<String> d = sc.getDirectoryNameList(path);
//					  for (int i = 0; i < d.size(); i++) {
//						System.out.println( d.get(i) );
//					  }
					  
					  sc.cd("USER");
					  String cd_path = sc.currentWorkingDirectory();
					  System.out.println( "current work directory is " + cd_path );
					  
					  
//					  List<String> f = sc.getFileNameList(cd_path);
//					  for (int i = 0; i < f.size(); i++) {
//						System.out.println( f.get(i) );
//					  }
					  
					  
					  sc.put("F:\\aa\\1.jpg", "first.jpg", "");// 远程路径为相对路径
					  //sc.get("/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/qunarlog.txt", "E:/111111111.txt");// 远程路径为相对路径
					  
					  //sc.mkDir("test6/7/8/9");
					  
					  //sc.deleteFile("first.txt");
					  //sc.deleteDirectory( "test" );
					  
//					  boolean isDir = sc.isDir(  cd_path+"/"  );
//					  System.out.println( isDir );
					  
					  sc.close();
				  }
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		  
	 }
	 
	 /**
		 * @description 此方法描述的是：得到ftp客户端
		 * @author mf [yanghaolong]
		 * @version 2014-05-06下午5:30:11
		 */
	 public static FtpClientUtil getFtpConnection() {
		 String service = ConfigUtil.getValue("ftp.picture.service");
		 Integer port = Integer.parseInt(ConfigUtil.getValue("ftp.picture.port"));
		 String username = ConfigUtil.getValue("ftp.picture.username");
		 String password = ConfigUtil.getValue("ftp.picture.password");
		 FtpClientUtil sc = new FtpClientUtil( service , port , username , password);
		 return sc;
		}

	 
	 
	 /**
	 * @description 此方法描述的是：通过ftp服务器上传tupian
	 * @param picname 本地图片名称
	 * @param newpicname 服务器图片名称
	 * @param path 根路径
	 * @param picPath 二级路径
	 * @author mf [yanghaolong]
	 * @version 2014-03-25下午5:30:11
	 */
	public static void ftpUploadPic(String picname,String newpicname,String path,String picPath) throws ORSException {
		 String service = ConfigUtil.getValue("ftp.picture.service");
		 Integer port = Integer.parseInt(ConfigUtil.getValue("ftp.picture.port"));
		 String username = ConfigUtil.getValue("ftp.picture.username");
		 String password = ConfigUtil.getValue("ftp.picture.password");
		 FtpClientUtil sc = new FtpClientUtil( service , port , username , password);
			 try {
				  if (sc.open()) {
					  String path1 = sc.currentWorkingDirectory();
					  System.out.println( "current work directory is " + path1 );
					  sc.cd(path);
					  String cd_path = sc.currentWorkingDirectory();
					  System.out.println( "current work directory is " + cd_path );
					  sc.put(picname, newpicname, picPath);// 远程路径为相对路径
					  sc.close();
				  }
			 }catch(Exception e){
				 e.printStackTrace();
			 }
	}
	 
	
	 
	 public FtpClientUtil(String server, int port, String userName,
	   String userPassword) {
		 this.server = server;
		 this.port = port;
		 this.userName = userName;
		 this.userPassword = userPassword;
	 }
	 
	 /**
	  * 链接到服务器
	  * 
	  * @return
	  * @throws Exception
	  */
	 public boolean open() {
		 if (ftpClient != null && ftpClient.isConnected()) {
			 return true;
		 }
		 try {
			 ftpClient = new FTPClient();
			 // 连接
			 ftpClient.connect(this.server, this.port);
			 ftpClient.login(this.userName, this.userPassword);
			 // 检测连接是否成功
			 int reply = ftpClient.getReplyCode();
			 if (!FTPReply.isPositiveCompletion(reply)) {
				 this.close();
				 System.err.println("FTP server refused connection.");
				 System.exit(1);
			 }
			 System.out.println("open FTP success:" + this.server+";port:"+this.port + ";name:"
					 + this.userName + ";pwd:" + this.userPassword);
			 ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE); // 设置上传模式.binally
			 ftpClient.enterLocalPassiveMode(); //当前的数据连接模式设置为PASSIVE_LOCAL_DATA_CONNECTION_MODE。
			 /*
			  * ftp client告诉ftp server开通一个端口来传输数据。
			  * 因为ftp server可能每次开启不同的端口来传输数据，但是在linux上或者其他服务器上面，
			  * 由于安全限制，可能某些端口没有开启，所以就出现阻塞。
			  * */
			 // or ascii
			 return true;
		 } catch (Exception ex) {
			 // 关闭
			 this.close();
			 ex.printStackTrace();
			 return false;
		 }
	 }
	 
	 public boolean cd(String dir) throws IOException {
		 if (ftpClient.changeWorkingDirectory(dir)) {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	 public boolean toParentDir() throws IOException {
		 if( ftpClient.changeToParentDirectory() ){
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	 public boolean isDir(String dirPath) {
		 return ( (String) parseLine(dirPath).get(0) ).indexOf("d") == -1;
	 }

	 
	 public boolean isFile(String filePath) {
		 return!isDir(filePath);
	 }
	 
	 public String currentWorkingDirectory(){
		 try {
			 return ftpClient.printWorkingDirectory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	 }

	 // 处理getFileList取得的行信息
	 private ArrayList parseLine(String path) {
		 ArrayList s1 = new ArrayList();
		 StringTokenizer st = new StringTokenizer(path, " ");
		 while (st.hasMoreTokens()) {
			 s1.add(st.nextToken());
		 }
		 return s1;
	 }
	 
	 /**
	  * 获取目录下所有的文件名称
	  * 
	  * @param filePath
	  * @return
	  * @throws IOException
	  */

	 private FTPFile[] getFileList(String filePath) throws IOException {
		 FTPFile[] list = ftpClient.listFiles();
		 return list;
	 }
	 
	 /**
	  * 循环将设置工作目录
	  */
	 public boolean changeDir(String ftpPath) {
		 if (!ftpClient.isConnected()) {
			 return false;
		 }
		 try {
			 // 将路径中的斜杠统一
			 char[] chars = ftpPath.toCharArray();
			 StringBuffer sbStr = new StringBuffer(256);
			 for (int i = 0; i < chars.length; i++) {
				 if ('\\' == chars[i]) {
					 sbStr.append('/');
				 } else {
					 sbStr.append(chars[i]);
				 }
			 }
			 ftpPath = sbStr.toString();
			 // System.out.println("ftpPath"+ftpPath);

			 if (ftpPath.indexOf('/') == -1) {
				 // 只有一层目录
				 // System.out.println("change"+ftpPath);
				 ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(), "iso-8859-1"));
			 } else {
				 // 多层目录循环创建
				 String[] paths = ftpPath.split("/");
				 // String pathTemp = "";
				 for (int i = 0; i < paths.length; i++) {
					 // System.out.println("change "+paths[i]);
					 ftpClient.changeWorkingDirectory(new String(paths[i].getBytes(), "iso-8859-1"));
				 }
			 }
			 return true;
		 } catch (Exception e) {
			 e.printStackTrace();
			 return false;
		 }
	 }
	
	 /**
	  * 循环创建目录，并且创建完目录后，设置工作目录为当前创建的目录下
	  */
	 public boolean mkDir(String ftpPath) {
		 if (!ftpClient.isConnected()) {
			 return false;
		 }
		 try {

			 // 将路径中的斜杠统一
			 char[] chars = ftpPath.toCharArray();
			 StringBuffer sbStr = new StringBuffer(256);
			 for (int i = 0; i < chars.length; i++) {
				 if ('\\' == chars[i]) {
					 sbStr.append('/');
				 } else {
					 sbStr.append(chars[i]);
				 }
			 }
			 ftpPath = sbStr.toString();
			 System.out.println("ftpPath" + ftpPath);

			 if (ftpPath.indexOf('/') == -1) {
				 // 只有一层目录
				 ftpClient.makeDirectory(new String(ftpPath.getBytes(), "iso-8859-1"));
				 ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(), "iso-8859-1"));
			 } else {
				 // 多层目录循环创建
				 String[] paths = ftpPath.split("/");
				 // String pathTemp = "";
				 for (int i = 0; i < paths.length; i++) {
	    
					 ftpClient.makeDirectory(new String(paths[i].getBytes(), "iso-8859-1"));
					 ftpClient.changeWorkingDirectory(new String(paths[i].getBytes(), "iso-8859-1"));
				 }
			 }

			 return true;
		 } catch (Exception e) {
			 e.printStackTrace();
			 return false;
		 }
	 }
	 
	 /**
	  * 上传文件到FTP服务器
	  * 
	  * @param localPathAndFileName 本地文件目录和文件名
	  * @param ftpFileName 上传后的文件名
	  * @param ftpDirectory FTP目录如:/path1/pathb2/,如果目录不存在回自动创建目录
	  * @throws Exception
	  */
	 public boolean put(String localDirectoryAndFileName, String ftpFileName,
			 String ftpDirectory) {
		 if (!ftpClient.isConnected()) {
			 return false;
		 }
		 boolean flag = false;
		 if (ftpClient != null) {
			 File srcFile = new File(localDirectoryAndFileName);
			 FileInputStream fis = null;
			 try {
				 fis = new FileInputStream(srcFile);

				 // 创建目录

				 this.mkDir(ftpDirectory);

				 ftpClient.setBufferSize(1024);
				 ftpClient.setControlEncoding("UTF-8");

				 // 设置文件类型（二进制）
				 ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				 // 上传
				 flag = ftpClient.storeFile(new String(ftpFileName.getBytes(), "iso-8859-1"), fis);
			 } catch (Exception e) {
				 this.close();
				 e.printStackTrace();
				 return false;
			 } finally {
				 IOUtils.closeQuietly(fis);
			 }
		 }

		 System.out.println("success put file " + localDirectoryAndFileName  + " to " + ftpDirectory + ftpFileName);
		 return flag;
	 }
	 
	 /**
	  * 从FTP服务器上下载文件并返回下载文件长度
	  * 
	  * @param ftpDirectoryAndFileName
	  * @param localDirectoryAndFileName
	  * @return
	  * @throws Exception
	  */
	 public long get(String ftpDirectoryAndFileName,
			 String localDirectoryAndFileName) {

		 long result = 0;
		 if (!ftpClient.isConnected()) {
			 return 0;
		 }
		 ftpClient.enterLocalPassiveMode(); // Use passive mode as default
	           // because most of us are behind
	           // firewalls these days.

		 try {
			 // 将路径中的斜杠统一
			 char[] chars = ftpDirectoryAndFileName.toCharArray();
			 StringBuffer sbStr = new StringBuffer(256);
			 for (int i = 0; i < chars.length; i++) {

				 if ('\\' == chars[i]) {
					 sbStr.append('/');
				 } else {
					 sbStr.append(chars[i]);
				 }
			 }
			 ftpDirectoryAndFileName = sbStr.toString();
			 String filePath = ftpDirectoryAndFileName.substring(0,
					 ftpDirectoryAndFileName.lastIndexOf("/"));
			 String fileName = ftpDirectoryAndFileName.substring(ftpDirectoryAndFileName.lastIndexOf("/") + 1);
			 // System.out.println("filePath | "+filePath);
			 // System.out.println("fileName | "+fileName);
			 this.changeDir(filePath);
			 ftpClient.retrieveFile( new String(fileName.getBytes(), "iso-8859-1"), new  FileOutputStream(localDirectoryAndFileName)); // download
	                 // file
			 System.out.print(ftpClient.getReplyString()); // check result

		 	} catch (IOException e) {
		 		e.printStackTrace();
		 	}
		 	System.out.println("Success get file" + ftpDirectoryAndFileName
		 			+ " from " + localDirectoryAndFileName);
		 	return result;
	 }
	 
	 /**
	  * 返回FTP 当前目录下的 文件 列表
	  * 
	  * @param ftpDirectory
	  * @return
	  */
	 public List<String> getFileNameList( String path ) {
		 List<String> rfs = new ArrayList<String>();
		 try {
			 FTPFile[] fs = ftpClient.listFiles( path );
			 for (int i = 0; i < fs.length; i++) {
				if( fs[i].isFile() ){
					rfs.add(fs[i].getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return rfs;
	 }
	 
	 /**
	  * 返回FTP 当前 目录下的  目录 列表
	  * 
	  * @param ftpDirectory
	  * @return
	  */
	 public List<String> getDirectoryNameList( String path ) {
		 List<String> rfs = new ArrayList<String>(  );
		 try {
			 FTPFile[] fs = ftpClient.listFiles( path );
			 for (int i = 0; i < fs.length; i++) {
				if( fs[i].isDirectory() ){
					rfs.add(fs[i].getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return rfs;
	 }
	 
	 
	 /**
	  * 删除FTP上的文件
	  * 
	  * @param ftpDirAndFileName
	  */
	 public boolean deleteFile(String ftpDirAndFileName) {
		 if (!ftpClient.isConnected()) {
			 return false;
		 }
		 try {
			ftpClient.deleteFile(ftpDirAndFileName);
		 } catch (IOException e) {
			e.printStackTrace();
			return false;
		 }
		 return true;
	 }
	 
	 /**
	  * 删除FTP目录    ( 文件夹是为空  )
	  * 
	  * @param ftpDirectory
	  */
	 public boolean deleteDirectory(String ftpDirectory) {
		 if (!ftpClient.isConnected()) {
			 return false;
		 }
		 try {
			 FTPFile[] fs = ftpClient.listFiles( ftpDirectory+"/" );
			 for (int i = 0; i < fs.length; i++) {
				 deleteFile( ftpDirectory+"/"+fs[i].getName() );
			}
			 ftpClient.removeDirectory(ftpDirectory);
		 } catch (IOException e) {
			 e.printStackTrace();
			 return false;
		 }
		 return true;
	 }

	 /**
	  * 关闭链接
	  */
	 public void close() {
		 try {
			 if (ftpClient != null && ftpClient.isConnected())
				 ftpClient.disconnect();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 System.out.println("Close Server Success :"+this.server+";port:"+this.port);
	 }

	 public FTPClient getFtpClient() {
		 return ftpClient;
	 }

	 public void setFtpClient(FTPClient ftpClient) {
		 this.ftpClient = ftpClient;
	 }
	 
}
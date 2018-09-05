package com.bee.devops.admin.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

public class FolderFunction {
	final static Logger log = Logger.getLogger(FolderFunction.class);

	/**
	 * 创建文件夹
	 * @author Yang Chunhai	 
	 * @param path
	 * @return 路径存在返回false 不存在返回true
	 */
	public static boolean createFolder(String path){
		File file = null;
		try {  
			file = new File(path);  
			if (!file.exists()) {  
				log.info("创建本地路径"+path);
				return file.mkdirs();  
			}  
			else{  
				return false;  
			}  
		} 
		catch (Exception e) {  
		}   
		return false;  
	}



	/**
	 * 清空文件夹
	 * @author Yang Chunhai	 
	 * @param path
	 * @return
	 */
	public static boolean deleteDir(String path){
		if (!path.endsWith(File.separator)){
			path = path + File.separator;
		}
		File file = new File(path);  
		if(!file.exists()){
			log.info("目录：" + path + "不存在！");
			return true;  
		}  
		String[] content = file.list();
		for(String name : content){  
			File temp = new File(path, name);  
			if(temp.isDirectory()){
				deleteDir(temp.getAbsolutePath());
				log.info("删除文件夹"+temp.getName()+"中" + temp.getAbsolutePath()+ "文件成功！"); 
				temp.delete(); 
				log.info("删除文件夹" + temp.getName()+ "成功！"); 

			}else{  
				if(!temp.delete()){
					log.info("删除文件" + name + "成功！"); 
				}  
			}  
		}  
		return true;  
	}  

	/**
	 * 创建文件
	 * @param filenamePath  文件路径名称
	 * @param filecontent   文件内容
	 * @return  是否创建成功，成功则返回true
	 */
	public static boolean createFile(String filecontent,String filenamePath){
		Boolean bool = false;
		File file = new File(filenamePath);
		try {
			//如果文件不存在，则创建新的文件
			if(!file.exists()){
				file.createNewFile();
				bool = true;
				log.info("success create file,the file is "+filenamePath);
				//创建文件成功后，写入内容到文件里
				writeFileContent(filenamePath, filecontent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bool;
	}

	/**
	 * 向文件中写入内容
	 * @param filepath 文件路径与名称
	 * @param newstr  写入的内容
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFileContent(String filepath,String newstr) throws IOException{
		Boolean bool = false;
		//新写入的行，换行
		String filein = newstr+"\r\n";
		String temp  = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos  = null;
		PrintWriter pw = null;
		try {
			File file = new File(filepath);
			//将文件读入输入流
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buffer = new StringBuffer();

			//文件原有内容
			for(int i=0;(temp =br.readLine())!=null;i++){
				buffer.append(temp);
				// 行与行之间的分隔符 相当于“\n”
				buffer = buffer.append(System.getProperty("line.separator"));
			}
			buffer.append(filein);
			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buffer.toString().toCharArray());
			pw.flush();
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return bool;
	}
	
	/**
	 * InputStreamReader+BufferedReader读取字符串  ， InputStreamReader类是从字节流到字符流的桥梁
     * 按行读对于要处理的格式化数据是一种读取的好方式 
	 * @author Yang Chunhai	 
	 * @param syncFolderName
	 * @return
	 */
	
    public static String readString(String syncFolderName){
        int len=0;
        StringBuffer str=new StringBuffer("");
        File file=new File(syncFolderName);
        try {
            FileInputStream is=new FileInputStream(file);
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader in= new BufferedReader(isr);
            String line=null;
            while( (line=in.readLine())!=null ){
            	// 处理换行符的问题
            	if(len != 0){
                    str.append("\r\n"+line);
                }else{
                    str.append(line);
                }
                len++;
            }
            in.close();
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str.toString();
    }
}

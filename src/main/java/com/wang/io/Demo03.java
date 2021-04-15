package com.wang.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * IO流
 * 
 * 文件夹的拷贝
 * 
 * 递归（文件夹- 创建，文件-复制）
 *
 */
public class Demo03 {

	public static void main(String[] args) {
		File ori = new File("E:\\TestFile\\1");
		String tar="E:\\TestFile\\2";
		try {
			copy(ori, tar);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finish");
	}
	
   public static void copy(File origin, String target) throws IOException {
	   if(origin.exists()) {
		   File tar =new File(target);
		   if(!tar.exists()) {
			   tar.mkdirs();
		   }
		   if(origin.isFile()) {
			   fileCopy(origin,target);
		   }
		   if(origin.isDirectory()) {
			   String secTarget = folderCopy(origin,target);
			   File[] files = origin.listFiles();
			   for(File fEntry:files) {
				   if(fEntry.isFile()) {
					   fileCopy(fEntry,secTarget);
				   }else {
					   copy(fEntry,secTarget);
				   }
			   }
		   }
	   }else {
		   System.out.println("Target file is not exist.");
	   }
   }
   
   private static void fileCopy(File origin, String target) throws IOException {
	   File tarF =new File(target);
	   if(!tarF.exists()) {
		   tarF.mkdirs();
	   }
	   File tar=new File(target,origin.getName());
	   try(FileInputStream fis=new FileInputStream(origin);
			   FileOutputStream fos=new FileOutputStream(tar)){
		   byte[] bs=new byte[1024];
		   int result =0;
		   while((result=fis.read(bs, 0, 1024))!=-1) {
			   fos.write(bs);
		   }
		   fos.flush();
	   }finally {
		   System.out.println("file "+origin.getName()+" copy completed.");
	   }
   }

   private static String folderCopy(File origin, String target) {
	   File tar =new File(target,origin.getName());
	   if(!tar.exists()) {
		   tar.mkdirs();
	   }
	   System.out.println("folder "+tar.getName()+" copy completed.");
	   return tar.getAbsolutePath();
   }
}

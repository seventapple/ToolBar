package com.wang.tool;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileDelete {

	public static void main(String[] args) throws IOException {
		String path = "E:\\TestFile\\3";
		FileDelete delete = new FileDelete();
		// make file
		File file = new File(path);
		file.mkdirs();
		File f1 = new File(path + File.separator + "1.txt");
		f1.createNewFile();
		// delete folder use File.delete()
		System.out.println(file.delete());
		// use Files.walkFileTree()
		delete.deleteFolder(path);
	}

	public void deleteFolder(String path) throws IOException {
		Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}

		});
	}
}

package com.wang.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import sun.security.util.Length;

public class Demo10 {
	private String filePath;
	// 文件名
	private String fileName;
	// 每块大小
	private long blockSize;
	// 块数
	private int size;
	// 每块名称
	private List<String> blockPath;
	// 文件大小
	private long len;

	public Demo10() {
		blockPath = new ArrayList<String>();
	}

	public Demo10(String filePath) {
		this(filePath, 1024);
	}

	public Demo10(String filePath, int blockSize) {
		this();
		this.filePath = filePath;
		this.blockSize = blockSize;
		init();
	}

	/**
	 * 初始化操作,计算块数,确定文件名
	 */
	public void init() {
		File src = null;
		// 健壮性
		if (null == filePath || !(src = new File(filePath)).exists()) {
			return;
		}
		if (src.isDirectory()) {
			return;
		}
		// 文件名
		this.fileName = src.getName();
		// 流大小
		long length = src.length();
		this.len = length;
		// 计算块数,实际大小与每块大小
		if (this.blockSize > length) {
			this.blockSize = length;
		}
		// 确定块数
		size = (int) (Math.ceil(length * 1.0 / this.blockSize));
	}

	// 文件名确认
	private void initPathName(String dest) {
		String nameBaseString = this.fileName.substring(0, this.fileName.lastIndexOf("."));
		String suffix = this.fileName.substring(this.fileName.lastIndexOf("."));
		for (int i = 0; i < size; i++) {
			this.blockPath.add(dest + "/" + nameBaseString + "_part" + i + suffix);
		}
	}

	/**
	 * 分割文件
	 * 
	 * @param destPath 分割文件存放路径
	 */
	private void split(String destPath) {
		long beginPos = 0;
		long actualBlockSize = blockSize;
		initPathName(destPath);
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				actualBlockSize = len - beginPos;
			}
			doSplit(i, beginPos, actualBlockSize);
			beginPos += actualBlockSize;
		}
	}

	/**
	 * 实际文件分割,输入输出
	 */
	private void doSplit(int idx, long beginPos, long actualBlockSize) {
		// 1.源
		File srcFile = new File(this.filePath);
		// 2.目标文件
		File dest = new File(this.blockPath.get(idx));
		// 3.选择流
		RandomAccessFile raf = null;
		BufferedOutputStream bos = null;

		try {
			raf = new RandomAccessFile(srcFile, "r");
			bos = new BufferedOutputStream(new FileOutputStream(dest));
			// 指定文件读取位置
			raf.seek(beginPos);
			// 缓冲接受
			byte[] flush = new byte[1024];
			int len = 0;
			while (-1 != (len = raf.read(flush))) {
				if (actualBlockSize - len > 0) {
					bos.write(flush, 0, len);
					actualBlockSize -= len;
				} else {
					// 最后一次剩余量
					bos.write(flush, 0, (int) actualBlockSize);
					break;
				}
			}
			bos.flush();
			FileUtil.closeAll(raf, bos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
//		randomAccessFileTest();
		Demo10 testSplit = new Demo10("E:\\TestFile\\222.txt", 5);
		testSplit.split("E:/TestFile/split");
	}

	// RandomAccessFile Test
	public static void randomAccessFileTest() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(new File("E:\\TestFile\\1.txt"), "r");
		raf.seek(78);
		byte[] flush = new byte[128];
		int len = 0;
		System.out.println(raf.length());
		while (-1 != (len = raf.read(flush))) {
			System.out.println(new String(flush, 0, len));
		}
		FileUtil.close(raf);
	}

}

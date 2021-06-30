package com.wang.internet.multiScoket.business;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Service {
	public static void main(String[] args) {
		try {
			new Service(44908).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ServerSocket server;

	public Service(int port) throws IOException {
		server = new ServerSocket(port);
	}

	public void start() throws IOException {
		while (true) {
			Socket accept = server.accept();
			WorkThread work = new WorkThread(accept);
			new Thread(work).start();
		}
	}
}

class WorkThread implements Runnable {
	private Socket accept = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private String path;
	private int size;

	public WorkThread(Socket accept) {
		this.accept = accept;
		try {
			dis = new DataInputStream(accept.getInputStream());
			dos = new DataOutputStream(accept.getOutputStream());
		} catch (IOException e) {
		}
	}

	@Override
	public void run() {
		try {
			String method = dis.readUTF();
			path = dis.readUTF();
			size = dis.readInt();
			System.out.println("method : " + method);
			if ("ADD".equalsIgnoreCase(method)) {
				doAdd1();
			} else {
				doDelete();
			}
			dos.writeUTF("OK");
		} catch (IOException e) {
			try {
				dos.writeUTF("ERROR");
			} catch (IOException e1) {
			}
			e.printStackTrace();
		} finally {
			try {
				if (dos != null) {
					dos.close();
				}
				if (dis != null) {
					dis.close();
				}
				if (accept != null) {
					accept.close();
				}
			} catch (IOException e) {
				;
			}
		}
	}

	// 主动关闭客户端输出流
	private void doAdd1() throws IOException {
		System.out.println("doAdd");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			byte[] data = new byte[128];
			int len;
			while ((len = dis.read(data)) != -1) {
				fos.write(data, 0, len);
			}
			fos.flush();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 在通信内容中附带流的长度
	private void doAdd2() throws IOException {
		System.out.println("doAdd");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			byte[] data = new byte[128];
			for (int i = 0; i < Math.floor(size / 128); i++) {
				dis.read(data);
				fos.write(data);
			}
			if (size % 128 > 0) {
				int read = dis.read(data);
				fos.write(data, 0, read);
			}
			fos.flush();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void doDelete() {
		System.out.println("doDelete");
		File file = new File(path);
		file.delete();
	}

}

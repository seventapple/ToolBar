package com.wang.GOF23.composite;

import java.util.ArrayList;
import java.util.List;

//抽象构件
public interface AbstractFile {
	void killVirus();
}

//叶组件
class TextFile implements AbstractFile {

	private String name;

	public TextFile(String name) {
		this.name = name;
	}

	@Override
	public void killVirus() {
		System.out.println("kill text : " + name);
	}

}

//叶组件
class ImageFile implements AbstractFile {

	private String name;

	public ImageFile(String name) {
		this.name = name;
	}

	@Override
	public void killVirus() {
		System.out.println("kill image : " + name);
	}

}

//容器
class Folder implements AbstractFile {
	private List<AbstractFile> files = new ArrayList<AbstractFile>();

	private String name;

	public Folder(String name) {
		this.name = name;
	}

	public void add(AbstractFile f) {
		files.add(f);
	}

	public void remove(AbstractFile f) {
		files.remove(f);
	}

	public AbstractFile get(int idx) {
		return files.get(idx);
	}

	@Override
	public void killVirus() {
		System.out.println("kill folder : " + name);
		for (AbstractFile f : files) {
			f.killVirus();
		}
	}

}

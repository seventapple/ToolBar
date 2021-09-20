package com.wang.GOF23.chainOfRes;

/**
 * 抽象类
 * 
 * @author 王李点儿
 *
 */
public abstract class Leader {
	protected Leader next;
	protected String name;

	public Leader(String name) {
		super();
		this.name = name;
	}

	/**
	 * 处理请求的核心业务方法
	 * 
	 * @param req
	 */
	public abstract void handleRequest(RequestMsg req);

	// 设定责任链后续对象
	public void setNext(Leader next) {
		this.next = next;
	}

}

class ZhuRen extends Leader {

	public ZhuRen(String name) {
		super(name);
	}

	@Override
	public void handleRequest(RequestMsg req) {
		if (req.getDays() < 5) {
			System.out.println(String.format("批准-主任:%s, 请假人:%s, 请假时间:%d, 请假原因:%s", name, req.getName(), req.getDays(),
					req.getMsg()));
		} else {
			if (this.next != null) {
				next.handleRequest(req);
			} else {
				System.out.println(String.format("%s,没办法了,等等吧", req.getName()));
			}
		}
	}

}

class Jingli extends Leader {

	public Jingli(String name) {
		super(name);
	}

	@Override
	public void handleRequest(RequestMsg req) {
		if (req.getDays() < 10) {
			System.out.println(String.format("批准-经理:%s, 请假人:%s, 请假时间:%d, 请假原因:%s", name, req.getName(), req.getDays(),
					req.getMsg()));
		} else {
			if (this.next != null) {
				next.handleRequest(req);
			} else {
				System.out.println(String.format("%s,没办法了,等等吧", req.getName()));
			}
		}
	}

}

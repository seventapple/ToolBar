package com.wang.GOF23.chainOfRes;

public class Client {
	public static void main(String[] args) {
		Leader l1 = new ZhuRen("王主任");
		Leader l2 = new Jingli("李经理");
		l1.setNext(l2);
		RequestMsg msg = new RequestMsg("李四", 4, "家里烧着水呢");
		l1.handleRequest(msg);
		RequestMsg msg2 = new RequestMsg("王五", 6, "难受");
		l1.handleRequest(msg2);
	}
}

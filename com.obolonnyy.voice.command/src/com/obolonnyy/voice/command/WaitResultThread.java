package com.obolonnyy.voice.command;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WaitResultThread extends Thread{

	private ConcurrentLinkedQueue<ArrayList<String>> queue;

	WaitResultThread (ConcurrentLinkedQueue queue){
		this.queue = queue;
	}

	@Override
	public void run() {

		ArrayList<String> messages;

		while (true) {
			messages = queue.poll();
			if (messages != null) {
				System.out.println("My Google response:= ");
				System.out.println(messages.get(0));
				System.out.println("My Other Possible Responses:= ");

				for (int i = 1; i < messages.size(); i++) {
					System.out.println("\t\t" + messages.get(i));
				}
				System.out.println("End of Google Responses. ");
				System.out.println();
			}
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
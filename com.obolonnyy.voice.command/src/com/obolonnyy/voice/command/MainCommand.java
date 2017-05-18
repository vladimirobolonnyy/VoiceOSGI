package com.obolonnyy.voice.command;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.felix.service.command.CommandProcessor;

import com.obolonnyy.voice.commandAPI.CommandAPI;


@Component(immediate = true,
			property = {
		CommandProcessor.COMMAND_SCOPE + ":String=fipro",
		CommandProcessor.COMMAND_FUNCTION + ":String=main",
		CommandProcessor.COMMAND_FUNCTION + ":String=test",
		CommandProcessor.COMMAND_FUNCTION + ":String=test2"
		},
	service = MainCommand.class
)

public class MainCommand implements CommandAPI{

	private ConcurrentLinkedQueue<String[]> queue;

	@Activate
    void activate() {
        System.out.println("Инициализация MainCommand прошла успешно");

        queue = new ConcurrentLinkedQueue<String[]>();

        WaitResultThread waitThread = new WaitResultThread(queue);
        waitThread.start();
    }

	@Override
	public void processPhrases(ArrayList<String> message){
		queue.add((String[])message.toArray());
	}


/*	private String get() {
		String[] words = queue.peek();
		String word = null;
		if (words != null)
			word = queue.peek()[0];
		return word;
	}

	private String[] gets() {
		return queue.peek();
	}

	private String remove() {
		String[] words = queue.poll();
		String word = null;
		if (words != null)
			word = queue.peek()[0];
		return word;
	}

	private String[] removes() {
		return queue.poll();
	}
*/
//	@Reference
//	void bindStringvoice(RecordingAPI voice) {
//		this.recording = voice;
//	}
//
//	void unbindStringvoice(RecordingAPI voice) {
//		this.recording = null;
//	}

//	@Reference
//	void bindMyRecognizer(RecognizerAPI recognizer) {
//		this.recognizer = recognizer;
//	}
//
//	void unbindMyRecognizer(RecognizerAPI recognizer) {
//		this.recognizer = null;
//	}

//	public void test() {
//		System.out.println("Test is working");
//		recording.startRecording();
//	}
}
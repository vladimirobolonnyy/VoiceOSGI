package com.obolonnyy.voice.recordingThread;

import java.io.File;
import java.util.ArrayList;

import com.obolonnyy.voice.RecognizerAPI.RecognizerAPI;

public class SendToGoogleTread  extends Thread {

	private File audio;
	private RecognizerAPI recognizer;

    public SendToGoogleTread(RecognizerAPI recognizer, File audio) {
    	this.recognizer = recognizer;
    	this.audio = audio;
	}

	@Override
	public void run() {

		ArrayList<String> messages = null;
		try {
			//messages = recognizer.recognizeAudioFile(this.audio);
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(this.getName() + "завершил свою работу.");
		if (!messages.isEmpty()) System.out.println("Messages:= ");
		for(String str: messages){
			System.out.println(str);
		}
	}
}
package com.obolonnyy.voice.recognizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;
import com.obolonnyy.voice.commandAPI.CommandAPI;

import java.util.concurrent.ConcurrentLinkedQueue;

public class RecognizerThread extends Thread {

	private CommandAPI command;
	private File audio;
	private final String apiKey = "AIzaSyDMRFZsdncfP2udmTbozAQ2owJuL5RRm34";
	private ArrayList<String> messages = new ArrayList<String>();

	public RecognizerThread(File audio, CommandAPI command) {
		this.audio = audio;
		this.command = command;
	}

	@Override
	public void run() {

		Recognizer recognizer = new Recognizer(Recognizer.Languages.RUSSIAN, apiKey);

		GoogleResponse response = null;
		try {
			response = recognizer.getRecognizedDataForFlac(audio, 1);
		} catch (IOException e) {
			System.out.println("error in MyRecognizer recognizeAudioFile(File file) ");
			e.printStackTrace();
		}

		// TODO
//audio.delete();

		messages.add(response.getResponse());

		if (messages.get(0) == null){
			messages.clear();
			messages.add("Null");
			}
		command.processPhrases(messages);



	}
}
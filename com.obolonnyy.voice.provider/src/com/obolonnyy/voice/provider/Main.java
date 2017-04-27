package com.obolonnyy.voice.provider;

import java.io.File;
import java.util.ArrayList;

public class Main {

	public static MyVoiceAPIImp voice = new MyVoiceAPIImp();

	public static void main (String[] args){


		voice.TestGoogle();

		ArrayList<String> phrases = new ArrayList<>();
		File audioFile;

		audioFile = voice.runRecording();
		//audioFile = new File("com.obolonnyy.voice.command/VoiceAudio/TestRec2plus2.flac");
		phrases = voice.sendRecordToGoogle(audioFile);

		for (String phrase: phrases){
			System.out.println(phrase);
		}
	}

}

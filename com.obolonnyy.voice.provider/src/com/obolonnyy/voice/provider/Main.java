package com.obolonnyy.voice.provider;

import java.io.File;
import java.util.ArrayList;

public class Main {

	public static MyVoiceAPIImp voice = new MyVoiceAPIImp();

	public static void main (String[] args){


		voice.TestGoogle();

		ArrayList<String> phrases = new ArrayList<>();
		File audioFile;
		
		//audioFile = new File(new File("").getAbsolutePath() + "/TestRec2.flac");
		audioFile = voice.runRecording();
		phrases = voice.sendRecordToGoogle(audioFile);

		for (String phrase: phrases){
			System.out.println(phrase);
		}
	}

}

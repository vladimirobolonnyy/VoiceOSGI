package com.obolonnyy.voice.provider;

import java.util.ArrayList;

public class Main {

	public static MyVoiceAPIImp voice = new MyVoiceAPIImp();

	public static void main (String[] args){

		ArrayList<String> phrases = new ArrayList<>();

		phrases = voice.runRecording();

		for (String phrase: phrases){
			System.out.println(phrase);
		}


		voice.TestGoogle();
	}

}

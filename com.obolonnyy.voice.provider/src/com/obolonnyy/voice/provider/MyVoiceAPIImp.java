package com.obolonnyy.voice.provider;

import com.obolonnyy.voice.myVoiceAPI;
import com.obolonnyy.voice.RecordingThread.RecordingThread;
import com.obolonnyy.voice.micro.Microphone;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;

import javaFlacEncoder.FLACFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;



@Component
public class MyVoiceAPIImp implements myVoiceAPI {

	private ArrayList<String> messages = new ArrayList<String>();


    @Override
	public File runRecording() {
			System.out.println("We are in the runRecording method (MyVoiceAPIImp)");

			RecordingThread rec = new RecordingThread();
			rec.start();

			try {
				rec.join();
				return (rec.getReturnedAudio());

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return (null);
	}

    @Override
	public ArrayList<String> sendRecordToGoogle(File file){


    	System.out.println("Recognizing...");


    	Microphone mic = new Microphone(FLACFileWriter.FLAC);
    	Recognizer recognizer = new Recognizer(Recognizer.Languages.RUSSIAN, "AIzaSyDMRFZsdncfP2udmTbozAQ2owJuL5RRm34");
		int maxNumOfResponses = 3;
		//file = new File("com.obolonnyy.voice.command/VoiceAudio/TestRec2plus2.flac");

		try {
			GoogleResponse response = recognizer.getRecognizedDataForFlac(file, maxNumOfResponses ,
					(int) mic.getAudioFormat().getSampleRate());

			messages.add(response.getResponse());
	        for (String s : response.getOtherPossibleResponses()) {
	        	messages.add(s);
			}


	        System.out.println("Looping back");//Restarts loops

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();

		}

    	return (messages);

    }


    @Override
	public void TestGoogle(){

    	System.out.println("Testing Google");

		@SuppressWarnings("resource")
		Microphone mic = new Microphone(FLACFileWriter.FLAC);
		File file;
		file = new File(new File("").getAbsolutePath() + "/TestRec2.flac");

		Recognizer recognizer = new Recognizer(Recognizer.Languages.RUSSIAN, "AIzaSyDMRFZsdncfP2udmTbozAQ2owJuL5RRm34");
		// Although auto-detect is available, it is recommended you select your
		// region for added accuracy.

		String returnResponse = "";
		try {
			int maxNumOfResponses = 3;
			System.out.println("Sample rate is: " + (int) mic.getAudioFormat().getSampleRate());
			GoogleResponse response = recognizer.getRecognizedDataForFlac(file, maxNumOfResponses,
					(int) mic.getAudioFormat().getSampleRate());

			returnResponse = response.getResponse();
			System.out.println("Google Response: " + returnResponse);

			System.out.println("Google is " + Double.parseDouble(response.getConfidence()) * 100 + "% confident in"
					+ " the reply");
			System.out.println("Other Possible responses are: ");
			for (String s : response.getOtherPossibleResponses()) {
				System.out.println("\t" + s);
			}

			System.out.println("Google Test successed!");
		} catch (Exception ex) {
			// TODO Handle how to respond if Google cannot be contacted
			System.out.println("ERROR: Google cannot be contacted");
			System.out.println(ex);
			//ex.printStackTrace();
		}
	}


}
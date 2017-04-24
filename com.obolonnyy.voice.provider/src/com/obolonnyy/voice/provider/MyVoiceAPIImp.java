package com.obolonnyy.voice.provider;

import com.obolonnyy.voice.myVoiceAPI;
import com.obolonnyy.voice.RecordingThread.RecordingThread;
import com.obolonnyy.voice.micro.Microphone;
import com.obolonnyy.voice.testGoogle.GoogleResponse;
import com.obolonnyy.voice.testGoogle.Recognizer;

import javaFlacEncoder.FLACFileWriter;

import java.io.File;
import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;



@Component
public class MyVoiceAPIImp implements myVoiceAPI {

/*    @Override
    public String invert(String input) {
        return new StringBuilder(input).reverse().toString();
    }*/


	public String startVoiceSpeech(){
		runRecording();
		//String message = sendToGoogle();
		String message = "startVoiceSpeech()";



		return (message);
	}


    @Override
	public ArrayList<String> runRecording() {
			System.out.println("We are in the runRecording method (MyVoiceAPIImp)");

			RecordingThread rec = new RecordingThread();
			rec.start();

			try {
				rec.join();
				return (rec.GetMessages());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return (null);
	}


    @Override
	public void TestGoogle(){

		@SuppressWarnings("resource")
		Microphone mic = new Microphone(FLACFileWriter.FLAC);
		File file;
		file = new File("com.obolonnyy.voice.command/VoiceAudio/TestRec.flac");


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
		} catch (Exception ex) {
			// TODO Handle how to respond if Google cannot be contacted
			System.out.println("ERROR: Google cannot be contacted");
			System.out.println(ex);
			//ex.printStackTrace();
		}
	}

}
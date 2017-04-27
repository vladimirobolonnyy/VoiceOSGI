package com.obolonnyy.voice.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.File;
import java.util.ArrayList;

import org.apache.felix.service.command.CommandProcessor;
import com.obolonnyy.voice.myVoiceAPI;

@Component(
	    property= {
	        CommandProcessor.COMMAND_SCOPE + ":String=fipro",
	        CommandProcessor.COMMAND_FUNCTION + ":String=main",
	        CommandProcessor.COMMAND_FUNCTION + ":String=test"
	    },
	    service=MainCommand.class
	)
public class MainCommand {
	   private myVoiceAPI voice;

	    @Reference
	    void bindStringvoice(myVoiceAPI voice) {
	        this.voice = voice;
	    }

	    void unbindStringvoice(myVoiceAPI voice) {
	    	this.voice = null;
	    }


	    public void test() {
	    	System.out.println("Test is working");
	    	voice.TestGoogle();
	    }


	public void main() {
		System.out.println("Main is working");

		ArrayList<String> phrases = new ArrayList<>();
		File audioFile;

		for (int i = 0; i < 1; i++) {

			audioFile = voice.runRecording();
			phrases = voice.sendRecordToGoogle(audioFile);

			for (String phrase : phrases) {
				System.out.println(phrase);
			}

			phrases = new ArrayList<>();
		}

	}
}

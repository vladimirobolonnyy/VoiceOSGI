package com.obolonnyy.voice.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.apache.felix.service.command.CommandProcessor;

import com.obolonny.voice.recordingAPI.recordingAPI;

import com.obolonnyy.voice.myVoiceAPI;

@Component(property = {
		CommandProcessor.COMMAND_SCOPE + ":String=fipro",
		CommandProcessor.COMMAND_FUNCTION + ":String=main",
		CommandProcessor.COMMAND_FUNCTION + ":String=test",
		CommandProcessor.COMMAND_FUNCTION + ":String=test2"
		},
	service = MainCommand.class
)

public class MainCommand {


	private recordingAPI voice2;

	@Reference
	void bindStringvoice(recordingAPI voice2) {
		this.voice2 = voice2;
	}

	void unbindStringvoice(recordingAPI voice) {
		this.voice2 = null;
	}

	public void test() {
		System.out.println("Test is working");
		voice2.startRecording();
	}


	 private myVoiceAPI voice;

	 //@Reference void bindStringvoice(myVoiceAPI voice) { this.voice = voice; }

	 //void unbindStringvoice(myVoiceAPI voice) { this.voice = null; }



/*	private ComponentContext context;
    @Activate
    void activate(ComponentContext context) {
        this.context = context;
        System.out.println("done");
    }*/

/*	public void test2() {
		System.out.println("Test2 is working");
		voice2.startRecording();
	}*/

//	public void test2() {
//		System.out.println("Test is working");
//		voice.TestGoogle();
//	}

//	public void main() {
//		System.out.println("Main is working");
//
//		ArrayList<String> phrases = new ArrayList<>();
//		File audioFile;
//
//		for (int i = 0; i < 1; i++) {
//
//			audioFile = voice.runRecording();
//			phrases = voice.sendRecordToGoogle(audioFile);
//
//			for (String phrase : phrases) {
//				System.out.println(phrase);
//			}
//
//			phrases = new ArrayList<>();
//		}
//
//	}
}
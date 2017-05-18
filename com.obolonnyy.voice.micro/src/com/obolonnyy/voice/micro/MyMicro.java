package com.obolonnyy.voice.micro;

import java.io.File;

import org.osgi.service.component.annotations.Component;

import com.obolonnyy.voice.MicroAPI.MicroAPI;
import com.obolonnyy.voice.micro.MicrophoneAnalyzer;

import javaFlacEncoder.FLACFileWriter;


@Component
public class MyMicro implements MicroAPI{

	private MicrophoneAnalyzer microphone;

	public MyMicro(){
		this.microphone = new MicrophoneAnalyzer(FLACFileWriter.FLAC);
	}

	public void testMicro(){
		System.out.println("My micro is working");
	}

	public void createMicro(){
		this.microphone = new MicrophoneAnalyzer(FLACFileWriter.FLAC);
	}

	@Override
	public void setAudioFile(File tempAudioFile) {
		microphone.setAudioFile(tempAudioFile);
	}

	@Override
	public void captureAudioToFile(File audioFile) throws Exception {
		microphone.captureAudioToFile(audioFile);
	}

	@Override
	public double magnitude(int minFreq, int maxFreq) {
		return microphone.magnitude(minFreq, maxFreq);
	}

	@Override
	public File getAudioFile() {
		return microphone.getAudioFile();
	}

	@Override
	public void open() {
		microphone.open();
	}

	@Override
	public void close() {
		microphone.close();
	}
}
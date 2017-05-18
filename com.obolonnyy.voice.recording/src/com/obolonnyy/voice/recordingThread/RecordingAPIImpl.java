package com.obolonnyy.voice.recordingThread;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.obolonny.voice.recordingAPI.RecordingAPI;
import com.obolonnyy.voice.MicroAPI.MicroAPI;
import com.obolonnyy.voice.RecognizerAPI.RecognizerAPI;

@Component (immediate = true)
public class RecordingAPIImpl implements RecordingAPI {

	private MicroAPI microphone;
	private RecognizerAPI recognizer;

	@Activate
    void activate() {
        System.out.println("Инициализация RecordingAPIImpl прошла успешно");
        startRecording();
    }

	@Reference
	void bindValidateMicro(MicroAPI microphone) {
		this.microphone = microphone;
	}

	void unbindValidateMicro(MicroAPI microphone) {
		this.microphone = null;
	}

	@Reference
	void bindValidateRecognizer(RecognizerAPI recognizer) {
		this.recognizer = recognizer;
	}

	void unbindValidateRecognizer(RecognizerAPI recognizer) {
		this.recognizer = null;
	}

	@Override
	public void startRecording() {
		microphone.testMicro();
		RecordingThread rec = new RecordingThread(microphone, recognizer);
		rec.start();
	}
}
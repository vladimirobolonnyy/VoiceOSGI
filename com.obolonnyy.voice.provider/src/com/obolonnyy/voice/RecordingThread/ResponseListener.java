package com.obolonnyy.voice.RecordingThread;

import com.darkprograms.speech.recognizer.GoogleResponse;

public interface ResponseListener {
	void onResponce(GoogleResponse response);
}

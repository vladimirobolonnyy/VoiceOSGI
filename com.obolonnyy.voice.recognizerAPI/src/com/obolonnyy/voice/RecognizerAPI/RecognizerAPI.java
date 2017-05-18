package com.obolonnyy.voice.RecognizerAPI;

import java.io.File;

import java.util.concurrent.ConcurrentLinkedQueue;

public interface RecognizerAPI {

	void recognizeAudioFile (File file);

}
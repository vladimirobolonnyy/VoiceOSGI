package com.obolonnyy.voice;

import java.io.File;
import java.util.ArrayList;

public interface myVoiceAPI {

	File runRecording();

	ArrayList<String> sendRecordToGoogle(File file);

	void TestGoogle();

}
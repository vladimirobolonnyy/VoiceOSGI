package com.obolonnyy.voice.MicroAPI;

import java.io.File;

public interface MicroAPI {

	public void setAudioFile(File file);

	public void captureAudioToFile(File audioFile) throws Exception;

	public double magnitude (int minFreq, int maxFreq);

	public File getAudioFile();

	void open();

	public void close();

	public void testMicro();

	public void createMicro();

}

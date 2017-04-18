package com.obolonnyy.voice.provider;

import com.obolonnyy.voice.StringInverter;
import com.obolonnyy.voice.RecordingThread.RecordingThread;

import org.osgi.service.component.annotations.Component;



@Component
public class StringInverterImpl implements StringInverter {

    @Override
    public String invert(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    @Override
    public void runRecording(){
    	System.out.println("We are in the runRecording method (StringInverterImpl)");
    	RecordingThread rec = new RecordingThread();
    	rec.start();

    }
}
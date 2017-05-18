package com.obolonnyy.voice.recordingThread;

import com.obolonny.voice.recordingAPI.RecordingAPI;
import com.obolonnyy.voice.MicroAPI.MicroAPI;
import com.obolonnyy.voice.RecognizerAPI.RecognizerAPI;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RecordingThread extends Thread  {

	private int numberOfFile;

    private double Silence = 0.6;
    private double CurrentNoiseLevel;

    private String apiKey = "AIzaSyDMRFZsdncfP2udmTbozAQ2owJuL5RRm34";

    private boolean debug = false;

    private int minimumVolumeToStartrecording = 40;
    private int volumeToStopRecording = 20;
    private int maxSamples = 50;
    private int checkVolumeSampleTime = 10;
    private int sampleTime = 100;

    private File tempAudioFile;
    private File AudioFileStream;
    private File ReturnedAudio;

    private ArrayList<String> messages = new ArrayList<String>();

    public File getReturnedAudio(){
    	return (ReturnedAudio);
    }


    private MicroAPI microphone;
    private RecognizerAPI recognizer;
//	@Reference
//	void bindValidateMicro(MicroAPI microphone) {
//		this.microphone = microphone;
//	}
//
//	void unbindValidateMicro(MicroAPI microphone) {
//		this.microphone = null;
//	}
//
//	@Override
//	public void startRecording() {
//		microphone.testMicro();
//		RecordingThread rec = new RecordingThread(microphone);
//		rec.start();
//	}


    public RecordingThread(MicroAPI microphone, RecognizerAPI recognizer) {
        this.microphone = microphone;
        this.recognizer = recognizer;
        int TimeInMsToCalculateNoiseLevel = 10;
        System.out.println("Start Calculating Noise Level");
        CurrentNoiseLevel =  CalculateNoiseLevel(TimeInMsToCalculateNoiseLevel);
        System.out.println("Noise Level = " + CurrentNoiseLevel);

    }


	//ToDo Удалить?
//    public RecordingThread(File AudioFile) {
//
//        //microphone.createMicro();
//
//        this.AudioFileStream = AudioFile;
//
//    }

    private double CalculateNoiseLevel(int TimeInMsToCalculateNoiseLevel) {

        tempAudioFile = new File("DisplayAllReponce.flac");
        microphone.setAudioFile(tempAudioFile);
        try {
            microphone.captureAudioToFile(microphone.getAudioFile());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int SampleTime = 100;
        int SampleCount = 0;
        double NoiseLevel = 0;
        int CurrentTimeInMs = 0;
        List<Double> NoiseArray = new ArrayList<Double>();

        while (CurrentTimeInMs <= TimeInMsToCalculateNoiseLevel) {

            double magnitude =  microphone.magnitude(120, 122);
            try {
                Thread.currentThread().sleep(SampleTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SampleCount++;
            NoiseArray.add(magnitude);
            CurrentTimeInMs += SampleTime;

        }
        NoiseLevel = this.calculateAverage(NoiseArray);

        NoiseLevel = Collections.max(NoiseArray);

        microphone.getAudioFile().delete();

        return NoiseLevel;
    }


    @Override
    public void run() {

    	numberOfFile = 0;
        int curSample = 0;
        if(this.AudioFileStream == null) {

        	boolean goThread = true;
            while (true) {
                microphone.open();


                try {
                    tempAudioFile = new File("TestRec.flac");
                    microphone.setAudioFile(tempAudioFile);
                    microphone.captureAudioToFile(microphone.getAudioFile());


                    Thread.sleep(checkVolumeSampleTime * 4);

                    double magnitude = microphone.magnitude(120, 122);

                    //int magnitude = microphone.getAudioVolume(checkVolumeSampleTime);
                    System.out.println(magnitude);
                    //boolean isSpeaking = (volume > minimumVolumeToStartrecording);
                    boolean isSpeaking = (magnitude > 150);

                    if (isSpeaking) {

                        DebugLog("Start RECORDING...");

/*                        int  counter = 0;
                        for(;;) {

                            DebugLog("RECORDING proc...");
                            Thread.sleep(sampleTime / 2);//Updates every second
                            if(microphone.magnitude(100, 102) < 400) {
                                counter++;
//                            } else {
//                                counter = 0;
                            }
                          //while (microphone.magnitude(120, 122) > 100);
                            if(counter >= 10) {
                                break;
                            }
                        }*/

                        int  counter = 0;
                        int forceExit = 0;
                        for(;;) {

                            DebugLog("RECORDING proc...");
                            forceExit++;
                            this.sleep(sampleTime / 4);//Updates every second
                            if(microphone.magnitude(100, 102) < 400) {
                                counter++;
                            } else {
                                counter = 0;
                            }
                            if(counter >= 5) {
                                break;
                            }
                            
                            if (forceExit > 10){
                            	System.out.println("forceExit");	
                            	break;
                            }
                        }



                        DebugLog("Recording Complete!");

                        String path = microphone.getAudioFile().getAbsolutePath();
                        String dest = path.substring(0, path.lastIndexOf('.')) + numberOfFile + ".flac";

                        File destFile = new File (dest);
                        destFile.delete();

                        DebugLog("path is: " + path);
                        DebugLog("dest is: " + dest);

                        Files.copy(microphone.getAudioFile().toPath(), destFile.toPath());
                        ReturnedAudio = new File(dest);

                        // run new Thread
                        // Google analyze
//                        SendToGoogleTread send = new SendToGoogleTread(recognizer, ReturnedAudio);
//                        send.setName("Thread - " + numberOfFile + " ");
//                        send.start();

                        DebugLog("Sending audio to google");
                        recognizer.recognizeAudioFile(ReturnedAudio);

                        numberOfFile++;
                        numberOfFile = numberOfFile % 1_000_000_000;

                        microphone.close();



                        /*
                        DebugLog("Recognizing...");

                        GoogleResponse response = recognizer.getRecognizedDataForFlac(microphone.getAudioFile(), 1);
                        notifyListeners(response);

                        System.out.println("My Google response:= ");
                        System.out.println(response.getResponse());
                        System.out.println("My Other Possible Responses:= ");

                        messages.add(response.getResponse());

                        for (String s : response.getOtherPossibleResponses()) {
                        	messages.add(s);
            				System.out.println("\t" + s);
            			}


            			System.out.println("Sleep for 1 sek");
            			Thread.sleep(1000);
                        DebugLog("Looping back");//Restarts loops
                        */
                        goThread = false;

                    }
                    //microphone.getAudioFile().delete();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("Error Occured");
                } finally {
                    microphone.close();//Makes sure microphone closes on exit.
                }
            }
        } else {

        	System.out.println("\n\n\nМы зашли в тот самый бесполезный else\n\n\n");
        	}
    }

    private void DebugLog(String message) {
        if (debug) {
            System.out.println(message);
        }
    }

    private double calculateAverage(List <Double> marks) {
        Double sum = Double.valueOf(0);
        if(!marks.isEmpty()) {
            for (Double mark : marks) {
                sum += mark;
            }
            return sum.doubleValue() / marks.size();
        }
        return sum;
    }


}
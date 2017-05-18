package com.obolonnyy.voice.recognizer;

import java.io.File;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.obolonnyy.voice.RecognizerAPI.RecognizerAPI;
import com.obolonnyy.voice.commandAPI.CommandAPI;

@Component (immediate = true)
public class MyRecognizer implements RecognizerAPI {

	private CommandAPI command;

	@Activate
    void activate() {
        System.out.println("Инициализация MyRecognizer прошла успешно");
    }

	@Reference
	void bindCommand(CommandAPI command) {
		this.command = command;
	}

	void unbindCommand(CommandAPI command) {
		this.command = null;
	}

	@Override
	public void recognizeAudioFile(File file) {
		RecognizerThread thread = new RecognizerThread(file, command);
		thread.start();
	}
}
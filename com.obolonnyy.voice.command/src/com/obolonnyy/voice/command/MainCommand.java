package com.obolonnyy.voice.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.apache.felix.service.command.CommandProcessor;
import com.obolonnyy.voice.StringInverter;

@Component(
	    property= {
	        CommandProcessor.COMMAND_SCOPE + ":String=fipro",
	        CommandProcessor.COMMAND_FUNCTION + ":String=invert",
	        CommandProcessor.COMMAND_FUNCTION + ":String=main",
	        CommandProcessor.COMMAND_FUNCTION + ":String=test"
	    },
	    service=MainCommand.class
	)
public class MainCommand {
	   private StringInverter inverter;

	    @Reference
	    void bindStringInverter(StringInverter inverter) {
	        this.inverter = inverter;
	    }

	    void unbindStringInverter(StringInverter inverter) {
	    	this.inverter = null;
	    }

	    public void invert(String input) {
	        System.out.println(inverter.invert(input));
	    }

	    public void test() {
	    	System.out.println("Test is working");
	    	System.out.println("Testing invert. Invert of 'String' is:");
	    	invert("String");
	    	System.out.println("Testing main");
	    	main();

	    }


	    public void main() {
	    	System.out.println("Main is working");
	    	inverter.runRecording();
	    }

}

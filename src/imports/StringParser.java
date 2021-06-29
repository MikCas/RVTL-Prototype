package imports;


public class StringParser {
	
	//Message Parser for Microsoft-Windows-DriverFrameworks-UserMode logs
    public static String[] parseWindowsDeviceMessage(String message){
    	String[] messageProperties = new String[4];
    	
    	String parseMessage1[] = message.split("\\?\\?_");
    	String parseMessage2[] = parseMessage1[1].split("\\&REV");
    	String parseMessage3[] = parseMessage2[0].split("\\&");
    	
    	//Device Type
    	messageProperties[0] = parseMessage3[0];
    	
    	//Device Name
    	messageProperties[1] = parseMessage3[1] + "&" + parseMessage3[2];
    	
    	String parseMessage4[] = parseMessage2[1].split("\\#");
    	String parseMessage5[] = parseMessage4[2].split("'");
    	
    	//Device ID
    	messageProperties[2] = parseMessage5[0];
    	
    	if(message.contains("[2003")){
    		String parseMessage6[] = parseMessage5[1].split("Computer Name: ");
    		String parseMessage7[] = parseMessage6[1].split(" Record Number:");
    		messageProperties[3] = parseMessage7[0];
    	}
    	
    	else{
    		String parseMessage6[] = parseMessage2[1].split("Computer Name: ");
    		String parseMessage7[] = parseMessage6[1].split(" Record Number:");
    		messageProperties[3] = parseMessage7[0];
    	}
    		
    	
//    	Code used to remove the curly braces from the device ID 
//    	String parseMessage6[] = parseMessage5[0].split("{");
//    	String parseMessage7[] = parseMessage6[1].split("}");
//    	deviceDetails[2] = parseMessage7[0];
    	
    	return messageProperties;
    }
    
    //Message parser for Microsoft-Windows-Security-Auditing logs
    public static String[] parseWindowsMessage(String message){
    	//Extracting the properties string from the message log
    	String messageSplit1[] = message.split("Strings: \\[");
    	String messageSplit2[] = messageSplit1[1].split("\\]");
    	
    	//Array containing all the properties with extra spaces in between
    	String messageSplit3[] = messageSplit2[0].split("'");
    	
    	//Creating an array of half length which takes the data form the parsed message which is not empty
    	int messagePropertiesLength = (int) Math.ceil(messageSplit3.length/2);
    	String messageProperties[] = new String[messagePropertiesLength];
    	
    	//Adding all the properties from the message without having any empty array elements
    	int count = 0;
    	for(int i = 1; i < messageSplit3.length; i += 2){
    		messageProperties[count] = messageSplit3[i];
    		count++;
    	}
    	
    	return messageProperties;
    }
    
    public static String[] parseFirefoxMessage(String message){
    	String properties[] = new String[5];
    	
    	//If message contains a title
    	if(message.contains(") [count:")){
    		String messageSplit1[] = message.split("\\(", 2);
        	String messageSplit2[] = messageSplit1[1].split("\\)", 2);
        	
        	properties[0] = messageSplit1[0]; //URL
        	properties[1] = messageSplit2[0]; //Title
        	
        	if(message.contains("visited from:")){
	        	String messageSplit3[] = messageSplit2[1].split("Host:");
	        	String messageSplit4[] = messageSplit3[1].split("Transition:");
	        	String messageSplit5[] = messageSplit4[0].split("visited from:");
	        	
	        	properties[2] = messageSplit5[0]; //Host
	        	properties[3] = messageSplit5[1]; //Visited From
	        	properties[4] = messageSplit4[1]; //Transition
        	}
        	
        	else{
        		String messageSplit3[] = messageSplit2[1].split("Host:");
        		String messsageSplit4[] = messageSplit3[1].split("Transition:");
        		
        		properties[2] = messsageSplit4[0]; //Host
            	properties[3] = "-"; //Visited From
            	properties[4] = messageSplit3[1]; //Transition
        	}
    	}
    	
    	//If message does not contain a title
    	else{
    		String messageSplit1[] = message.split("\\[", 2);
        	String messageSplit2[] = messageSplit1[1].split("\\]", 2);
        	
        	properties[0] = messageSplit1[0]; //URL
        	properties[1] = "-"; //Title
        	
        	if(message.contains("visited from:")){
        		String messageSplit3[] = messageSplit2[1].split("Host:");
            	String messageSplit4[] = messageSplit3[1].split("Transition:");
            	String messageSplit5[] = messageSplit4[0].split("visited from:");
            	
            	properties[2] = messageSplit5[0]; //Host
            	properties[3] = messageSplit5[1]; //Visited From
            	properties[4] = messageSplit4[1]; //Transition
        	}
        	
        	else{
        		String messageSplit3[] = messageSplit2[1].split("Host:");
        		String messsageSplit4[] = messageSplit3[1].split("Transition:");
        		
        		properties[2] = messsageSplit4[0]; //Host
            	properties[3] = "-"; //Visited From
            	properties[4] = messageSplit3[1]; //Transition
        	}
        	
    	}
    	
    	return properties;
    }
    
    public static String[] removeTimeZone(String time){
    	String parsedTime[] = time.split("\\+");
        //parseTime[0] - dateTime
        //parseTime[1] - timezone
       
    	return parsedTime;
    }
}

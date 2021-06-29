import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.Arrays;
import larva.RunningClock;

import imports.Entity;
import imports.LowLevelEvent;
import imports.StringParser;
//import larva.RunningClock;

public class Main {
	public static void main(String[] args) {
		
//		long startTime = System.currentTimeMillis();
		
		//File to be read
		String filePath = "logs/illicitFileDownload.csv";

		//Method used to create a knowledge base
		ArrayList<LowLevelEvent> knowledgeBase = createKnowledgeBase(filePath); 
		
		//Instead of using fileReader, go through all the events using the created knowledge base 
		knowledgeBaseReader(knowledgeBase);
		
//		long elapsedTime = System.currentTimeMillis() - startTime;
		
//		System.out.println("Elapsed Time: " + elapsedTime);
	}
    
    public static ArrayList<LowLevelEvent> createKnowledgeBase(String filePath){
    	//BufferedReader object used to read data within a csv file
        BufferedReader br = null;
        //Stores the current line obtained by br
        String row = ""; 
        //Knowledge base containing all the even ts of the time line input along ith the respective time, subjects and objects
        ArrayList<LowLevelEvent> knowledgeBase = new ArrayList<LowLevelEvent>();
        
        //The event ID which is given in the program is 2 less than that which is seen in excel, this is because we are starting from 0 and also because there is the initial field row in excel
        int eventId = 0;
        
        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((row = br.readLine()) != null) {
            	
            	if(row.contains("datetime")){
            		continue;
            	}
            	
            	else{
            		
		            //array storing 
		            String[] data = row.split(",");
		            
		            //ArrayList created from the data array
		            ArrayList<String> fields = new ArrayList<String>(Arrays.asList(data));                
		            
		        	//While going through each line, the knowledge base is created by obtaining the subjects, objects and temporal aspects of each low-level event
		            LowLevelEvent lowLevelEvent = createLowLevelEvent(fields.get(0), fields.get(1), fields.get(3), fields.get(4), eventId);
		            
		            //The low-level event is added to the knowledge base
		            knowledgeBase.add(lowLevelEvent);
		            
		            //Increment the event Id
		            eventId++;
            	}
            }
        } 
        
        //Error while finding the file 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        //Error while reading the file 
        catch (IOException e) {
            e.printStackTrace();
        } 
        //After file is read, br is closed 
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return knowledgeBase;
    }
    
    public static LowLevelEvent createLowLevelEvent(String dateTime, String timeStampDesc, String sourceLong, String message, int eventId){
    	
    	//New event created
    	LowLevelEvent lowLevelEvent = new LowLevelEvent(dateTime, timeStampDesc, sourceLong, message, eventId);	
    	String[] messageDetails;
    	
    	//If event is a Windows System Log event 
    	if(sourceLong.equals("WinEVTX")){
    		
    		//Events 2003 and 2102 are windows device events which are parsed differently than windows system events
    		//A device has been connected to the system
    		if(message.contains("[2003")){
    			messageDetails = StringParser.parseWindowsDeviceMessage(message);
    			lowLevelEvent.populateEvent2003(messageDetails);
    		}
    		
    		//A device has been removed from the system
    		if(message.contains("[2102")){
    			messageDetails = StringParser.parseWindowsDeviceMessage(message);
    			lowLevelEvent.populateEvent2102(messageDetails);
    		}
    		
    		//After parsing the windows event, need to determine what type of event it is
    		//Array which holds the message details 
        	messageDetails= StringParser.parseWindowsMessage(message);
        	
        	//An account was successfully logged on 
        	if(message.contains("[4624")){
        		lowLevelEvent.populateEvent4624(messageDetails);
        	}
        	
        	//An account was logged off
        	if(message.contains("[4634")){
        		lowLevelEvent.populateEvent4634(messageDetails);
        	}
        	
        	//A handle to an object was requested
        	else if(message.contains("[4656")){
        		lowLevelEvent.populateEvent4656(messageDetails);
        	}
        	
        	//The handle to an object was closed
        	else if(message.contains("[4658")){
        		lowLevelEvent.populateEvent4658(messageDetails);
        	}
        	
        	//An attempt was made to access an object
        	else if(message.contains("[4663")){
        		lowLevelEvent.populateEvent4663(messageDetails);
        	}
        	
        	//Special priveledges assigned to new logon
        	else if(message.contains("[4672")){
        		lowLevelEvent.populateEvent4672(messageDetails);
        	}
        	
        	//A new process has been created
        	else if(message.contains("[4688")){
        		lowLevelEvent.populateEvent4688(messageDetails);
        	}
        	
        	//A process has exited
        	else if(message.contains("[4689")){
        		lowLevelEvent.populateEvent4689(messageDetails);
        	}
        	
        	//An attempt was made to duplicate a handle to an object
        	else if(message.contains("[4690")){
        		lowLevelEvent.populateEvent4690(messageDetails);
        	}
        	
        	//A token right was adjusted
        	else if(message.contains("[4703")){
        		lowLevelEvent.populateEvent4703(messageDetails);
        	}
        	
        	//A user's local group membership was enumerated
        	else if(message.contains("[4798")){
        		lowLevelEvent.populateEvent4798(messageDetails);
        	}
        	
        	//5154, 5156, 5158 are not relevant
        	
        	//A new external device was recognised by the system
        	else if(message.contains("[6416")){
        		lowLevelEvent.populateEvent6416(messageDetails);
        	}
    	}
    	
    	//If event is a Mozilla Firefox Log event 
    	else if(sourceLong.contentEquals("Firefox History")){
//    		//If event is of a web site visit or file download
    		if(timeStampDesc.equals("Last Visited Time")){
    			messageDetails= StringParser.parseFirefoxMessage(message);
    			
    			//Subjects and obejcts extrated from event
    			lowLevelEvent.populateWebEvent(messageDetails);
    		}
    	}
    	
    		//Showing the properties of an event
        	System.out.println("Event ");
        	System.out.println("Time: " + lowLevelEvent.getTime());
        	System.out.println("EventID: " + eventId);
        	System.out.println("Message: " + message);
//        	for(Entity subject: lowLevelEvent.getSubjects()){
//        		System.out.println(subject.getEntityValue());
//        	}
//        	System.out.println("\nObjects: ");
//        	for(Entity object: lowLevelEvent.getObjects()){
//        		System.out.println(object.getEntityValue());
//        	}
        	
        	lowLevelEvent.showEntities();
        	
        	System.out.println();
    	
    	//If the current event is not relevant to the investigation, an event containing only its ID and the time the event occurred is placed within the knowledgebase
    	return lowLevelEvent;
    }
    
    public static void knowledgeBaseReader(ArrayList<LowLevelEvent> knowledgeBase){
    	
    	long globalTime = 0;
    	long duration;
    	
    	for(int i = 0; i < knowledgeBase.size(); i++) {
    		LowLevelEvent lowLevelEvent = knowledgeBase.get(i);
    		LowLevelEvent previousLowLevelEvent;
    		
    		if(i == 0) {
    			RunningClock.updateNow(0);
    			duration = 0;
    		}
    		
    		else {
    			
    			previousLowLevelEvent = knowledgeBase.get(i-1);
    			
    			duration = (long) previousLowLevelEvent.getDurationMilliSecs(lowLevelEvent);
    			globalTime = globalTime + duration;
    			
    			System.out.println(globalTime);
    			RunningClock.updateNow(globalTime);
    		}
    		
    		//Read the low-level event
    		lowLevelEventReader(lowLevelEvent);
    		
    		//Read the entities of each low-level event 
    		for(Entity entity: lowLevelEvent.getEntities()){
    			lowLevelEventEntityReader(lowLevelEvent, entity);
    		}
    		
//    		//Read the subjects of each low level event 
//    		for(Entity subject: lowLevelEvent.getSubjects()){
//    			
//    			lowLevelEventEntityReader(lowLevelEvent, subject);
////    			lowLevelEventEntityReader(subject);
//    		}
//    		
//    		//Read the objects of each low level event
//    		for(Entity object: lowLevelEvent.getObjects()){
//    			lowLevelEventEntityReader(lowLevelEvent, object);
////    			lowLevelEventEntityReader(object);
//    		}
    	}
    }
   
    public static void lowLevelEventReader(LowLevelEvent lowLevelEvent){
    }
    
    public static void lowLevelEventEntityReader(LowLevelEvent lowLevelEvent, Entity entity){
    }
    
}


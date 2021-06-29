package imports;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.time.Duration;

public class LowLevelEvent extends Event {
	
	//Footprints
	private LocalDateTime time;
	private String timeStampDesc;
	private String sourceLong;
	private String message;
	
	//Low-level event attributes
	private String timezone;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public LowLevelEvent(String time, String timeStampDesc, String sourceLong, String message, int id){
		super(id);
		// TODO Auto-generated constructor stub
		this.timeStampDesc = timeStampDesc;
		this.sourceLong = sourceLong;
		this.message = message;
		
		//Parse time so as to remove the time zone
		String parsedTime[] = StringParser.removeTimeZone(time);
		
		//Change time to a LocalDateTime object
		this.time = LocalDateTime.parse(parsedTime[0]);
		this.timezone = parsedTime[1];
	}
	
	public LowLevelEvent(int id, ArrayList<Entity> entites) {
		super(id);
		this.entities = entities;
	}
	
	//Getters
	public LocalDateTime getTime(){
		return time;
	}
	
	public String getTimeStampDesc(){
		return timeStampDesc;
	}
	
	public String getSourceLong(){
		return sourceLong;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getTimezone() {
		return timezone;
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
	//Setters
	public void setTime(LocalDateTime time) {
	    this.time = time;
    }
	
	public void setTimeStampDesc(String timeStampDesc){
		this.timeStampDesc = timeStampDesc;
	}
	
	public void setSourceLong(String sourceLong) {
		this.sourceLong = sourceLong;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	//Methods for Temporal Correlation
	public double getDurationSecs(LowLevelEvent event) {
		Duration duration = Duration.between(this.time, event.getTime());
		
		return Math.abs(duration.toSeconds());
	}
	
	public double getDurationMilliSecs(LowLevelEvent event) {
		Duration duration = Duration.between(this.time, event.getTime());
		
		return Math.abs(duration.toMillis());
	}
	
	//Temporal Correlation between low-level events in seconds
	public double temporalCorrelationSecs(LowLevelEvent lowLevelEvent){
		//Only calculate from SADFC before(x, y) = 1/(x-y), before(x,y) = 1 if x = y
		double correlation;
		double timeDifference = getDurationSecs(lowLevelEvent);// duration  = x - y
		
		if(timeDifference == 0){
			correlation = 1;
		}
		
		else{
			correlation = 1/(timeDifference);
		}		
		
		return correlation;
	}
	
	//Temporal Correlation between low-level events in milliseconds
	public double temporalCorrelationMillisecs(LowLevelEvent lowLevelEvent){
		//Only calculate from SADFC before(x, y) = 1/(x-y), before(x,y) = 1 if x = y
		double correlation;
		double timeDifference = getDurationMilliSecs(lowLevelEvent);// duration  = x - y
		
		if(timeDifference == 0){
			correlation = 1;
		}
		
		else{
			correlation = 1/(timeDifference);
		}		
		
		return correlation;
	}
	
	public ArrayList<Entity> entityIntersection(LowLevelEvent lowLevelEvent){
		
		ArrayList<Entity> intersection = new ArrayList<Entity>();
		for(Entity entity: entities) {
			if(lowLevelEvent.getEntities().contains(entity)) {
				intersection.add(entity);
			}
		}
		
		return intersection;
		
	}
		
	//Methods for Entity(Subject/Object) correlation
	public double entityCorrelation(LowLevelEvent lowLevelEvent){
		
		//Obtain size of intersection between low-level events
		int intersectionSize = entityIntersection(lowLevelEvent).size();
		
		//Obtain maximum size between both entity lists
		int maxSize = Math.max(entities.size(), lowLevelEvent.getEntities().size());
		
		
		double entityCorr = 0;
		
		try { 
			entityCorr = intersectionSize/maxSize;
        } 
        catch (ArithmeticException e) { 
            System.out.println( 
                "Divided by zero operation cannot possible"); 
        } 
		
		return entityCorr;
	}
	
	//Methods to add subject/object to LLE
	public void addSubject(Entity entity, ArrayList<Entity> entityList) {
		if(entity.getEntityType() == true) {
			entityList.add(entity);
		}
	}
	
	public void addObject(Entity entity, ArrayList<Entity> entityList) {
		if(entity.getEntityType() == false) {
			entityList.add(entity);
		}
	}
	
//	NEED TO CHANGE THESE SO AS TO WORK WITH THE NEW OBJECT IMPLEMENTATION
	public double subjectCorrelation(LowLevelEvent lowLevelEvent){
		
		ArrayList<Entity> subjects1 = new ArrayList<Entity>();
		ArrayList<Entity> subjects2 = new ArrayList<Entity>();
		
		//Obtain all entities which are subjects
		//Subsets of the multisets
		entities.forEach((e) -> addSubject(e, subjects1));
		lowLevelEvent.getEntities().forEach((e) -> addSubject(e, subjects2));	
		
		//Create new high-level events to hold these events
		LowLevelEvent lle1 = new LowLevelEvent(0, subjects1);
		LowLevelEvent lle2 = new LowLevelEvent(0, subjects2);
		
		return lle1.entityCorrelation(lle2);
}

	public double objectCorrelation(LowLevelEvent lowLevelEvent){
		ArrayList<Entity> objects1 = new ArrayList<Entity>();
		ArrayList<Entity> objects2 = new ArrayList<Entity>();
		
		//Obtain all entities which are subjects
		//Subsets of the multisets
		entities.forEach((e) -> addObject(e, objects1));
		lowLevelEvent.getEntities().forEach((e) -> addObject(e, objects2));	
		
		//Create new high-level events to hold these events
		LowLevelEvent lle1 = new LowLevelEvent(0, objects1);
		LowLevelEvent lle2 = new LowLevelEvent(0, objects2);
		
		return lle1.entityCorrelation(lle2);
	}
	
//	Obtains the correlation between this low-level event object and any other low-level event 
	public double correlation(LowLevelEvent lowLevelEvent){
		double correlation = 0;
		double temporalCorrelation = 0;
//		double subjectCorrelation = 0;
//		double objectCorrelation = 0;
		double entityCorrelation = 0;
		
		
		//Calculate Temporal Correlation
		temporalCorrelation = temporalCorrelationMillisecs(lowLevelEvent);
		
//		//Calculate Subject Correlation
//		subjectCorrelation = subjectCorrelation(lowLevelEvent);
//		
//		//Calculate Object Correlation
//		objectCorrelation = objectCorrelation(lowLevelEvent);
		
		//Calculate entity correlation
		entityCorrelation = entityCorrelation(lowLevelEvent);
		
		//Calculating correlation
		//Using formula from SADFC paper
//		correlation = temporalCorrelation + subjectCorrelation + objectCorrelation;
		correlation = temporalCorrelation + entityCorrelation;
		
		return correlation;
	}
	
	public void showEntities() {
		System.out.println("Subjects:");
		for(Entity entity: entities) {
			if(entity.getEntityType() == true) {
				System.out.println('\t' + entity.getEntityValue());
			}
		}
		
		System.out.println();
		System.out.println("Objects:");
		for(Entity entity: entities) {
			if(entity.getEntityType() == false) {
				System.out.println('\t' + entity.getEntityValue());
			}
		}
	}

	//WINDOWS EVENTS
	//WINDOWS DEVICE EVENTS
	public void populateEvent2003(String[] properties){
		
		//Add subjects		
//		getSubjects().add(new SubjectComputerName(properties[3])); //Computer Name
		
		//Add objects
//		getObjects().add(new ObjectDeviceType(properties[0])); //Device Type
//		getObjects().add(new ObjectDeviceName(properties[1])); //Device Name
//		getObjects().add(new ObjectDeviceID(properties[2])); //Device ID
		
		entities.add(new Entity(properties[3], true)); //Computer Name
		
		entities.add(new Entity(properties[0], true)); //Device Type
		entities.add(new Entity(properties[1], true)); //Device Name
		entities.add(new Entity(properties[2], true)); //Device ID
		
	}
	
	public void populateEvent2102(String[] properties){
		//Add subjects
//		getSubjects().add(new SubjectComputerName(properties[3])); //Computer Name 

		//Add objects
//		getObjects().add(new ObjectDeviceType(properties[0])); //Device Type
//		getObjects().add(new ObjectDeviceName(properties[1])); //Device Name
//		getObjects().add(new ObjectDeviceID(properties[2])); //Device ID
		
		entities.add(new Entity(properties[3], true)); //Computer Name
	}
	
	//WINDOWS SECURITY EVENTS
 	public void populateEvent4624(String[] properties){
		//Add subjects
// 		getSubjects().add(new SubjectSecurityID(properties[0])); //Security ID
//		getSubjects().add(new SubjectAccountName(properties[1])); //Account Name
//		getSubjects().add(new SubjectLogonID(properties[3])); //Logon ID
		
		//Add Entities
 		entities.add(new Entity(properties[0], true)); //Security ID
// 		entities.add(new Entity(properties[1], true)); //Account Name
// 		entities.add(new Entity(properties[3], true)); //Logon ID
		
	}
 	
 	public void populateEvent4634(String[] properties){
 		//Add subjects
// 		getSubjects().add(new SubjectSecurityID(properties[0])); //Security ID
//		getSubjects().add(new SubjectAccountName(properties[1])); //Account Name
//		getSubjects().add(new SubjectLogonID(properties[3])); //Logon ID
		
		//Add Entities
 		entities.add(new Entity(properties[0], true)); //Security ID
// 		entities.add(new Entity(properties[1], true)); //Account Name
// 		entities.add(new Entity(properties[3], true)); //Logon ID
 	}
	
	public void populateEvent4656(String[] properties){
		//Add subjects
//		getSubjects().add(new SubjectSecurityID(properties[0])); //Security ID
//		getSubjects().add(new SubjectAccountName(properties[1])); //Account Name
//		getSubjects().add(new SubjectLogonID(properties[3])); //Logon ID

		//Add objects
//		getObjects().add(new ObjectObjectName(properties[6])); //Object name
//		getObjects().add(new ObjectHandleID(properties[7])); //Handle ID 
//		getObjects().add(new ObjectAccessList(properties[9]));//Access List
		
		//Add Entities
		entities.add(new Entity(properties[0], true)); //Security ID
// 		entities.add(new Entity(properties[1], true)); //Account Name
// 		entities.add(new Entity(properties[3], true)); //Logon ID
 		
// 		entities.add(new Entity(properties[6], false));//Object name
// 		entities.add(new Entity(properties[7], false));//Handle ID
// 		entities.add(new Entity(properties[9], false));//Access List
	}
	
	public void populateEvent4658(String[] properties){
		//Add subjects
//		getSubjects().add(new SubjectSecurityID(properties[0])); //Security ID
//		getSubjects().add(new SubjectAccountName(properties[1])); //Account Name
//		getSubjects().add(new SubjectLogonID(properties[3])); //Logon ID

		//Add objects
//		getObjects().add(new ObjectHandleID(properties[5])); //Handle ID 
		
		//Add Entities
		entities.add(new Entity(properties[0], true)); //Security ID
 		entities.add(new Entity(properties[1], true)); //Account Name
 		entities.add(new Entity(properties[3], true)); //Logon ID
 		
 		entities.add(new Entity(properties[5], false)); //Handle ID 
	}
	
	public void populateEvent4663(String[] properties){
		//Add subjects
//		getSubjects().add(new SubjectSecurityID(properties[0])); //Security ID
//		getSubjects().add(new SubjectAccountName(properties[1])); //Account Name
//		getSubjects().add(new SubjectLogonID(properties[3])); //Logon ID

		//Add objects
//		getObjects().add(new ObjectObjectName(properties[6])); //Object name
//		getObjects().add(new ObjectHandleID(properties[7])); //Handle ID 
//		getObjects().add(new ObjectAccesses(properties[8]));//Accesses
		
		//Add Entities
		entities.add(new Entity(properties[0], true)); //Security ID
// 		entities.add(new Entity(properties[1], true)); //Account Name
// 		entities.add(new Entity(properties[3], true)); //Logon ID
 		
// 		entities.add(new Entity(properties[6], false)); //Object name
// 		entities.add(new Entity(properties[7], false)); //Handle ID
// 		entities.add(new Entity(properties[8], false)); //Accesses
	}
	
	public void populateEvent4672(String[] properties){
		//Add subjects
//		getSubjects().add(new SubjectSecurityID(properties[0])); //Security ID
//		getSubjects().add(new SubjectAccountName(properties[1])); //Account Name
//		getSubjects().add(new SubjectLogonID(properties[3])); //Logon ID
		
		//MAYBE ADD PRIVELEDGES BUT WILL SEE
		//properties[4]
		
		//Add Entities
		entities.add(new Entity(properties[0], true)); //Security ID
 		entities.add(new Entity(properties[1], true)); //Account Name
 		entities.add(new Entity(properties[3], true)); //Logon ID
	}
	
	public void populateEvent4688(String[] properties){
		//Add subjects
//		getSubjects().add(new SubjectSecurityID(properties[0])); //Security ID
//		getSubjects().add(new SubjectAccountName(properties[1])); //Account Name
//		getSubjects().add(new SubjectLogonID(properties[3])); //Logon ID

		//Add objects
//		getObjects().add(new ObjectNewProcessID(properties[4])); //New Process ID
//		getObjects().add(new ObjectNewProcessName(properties[5])); //New Process Name
//		getObjects().add(new ObjectCreatorProcessID(properties[11])); //Creator Process ID
//		getObjects().add(new ObjectCreatorProcessName(properties[12])); //Creator Process Name	
		
		//Add Entities
		entities.add(new Entity(properties[0], true)); //Security ID
 		entities.add(new Entity(properties[1], true)); //Account Name
 		entities.add(new Entity(properties[3], true)); //Logon ID
		
		entities.add(new Entity(properties[4], false)); // New Process ID
		entities.add(new Entity(properties[5], false)); // New Process Name
		entities.add(new Entity(properties[11], false)); // Creator Process ID
		entities.add(new Entity(properties[12], false)); // Creator Process Name
		
		
	}
	
	public void populateEvent4689(String[] properties){
		//Add subjects
//		getSubjects().add(new SubjectSecurityID(properties[0])); //Security ID
//		getSubjects().add(new SubjectAccountName(properties[1])); //Account Name
//		getSubjects().add(new SubjectLogonID(properties[3])); //Logon ID

		//Add objects
//		getObjects().add(new ObjectProcessID(properties[5])); //Process ID
//		getObjects().add(new ObjectProcessName(properties[6])); //Process Name
		
		//Add Entities
		entities.add(new Entity(properties[0], true)); //Security ID
 		entities.add(new Entity(properties[1], true)); //Account Name
 		entities.add(new Entity(properties[3], true)); //Logon ID
		
 		entities.add(new Entity(properties[5], false)); // Process ID
		entities.add(new Entity(properties[6], false)); // Process Name
	}
	
	public void populateEvent4690(String[] properties){
		//Add subjects
//		getSubjects().add(new SubjectSecurityID(properties[0])); //Security ID
//		getSubjects().add(new SubjectAccountName(properties[1])); //Account Name
//		getSubjects().add(new SubjectLogonID(properties[3])); //Logon ID

		//Add objects
//		getObjects().add(new ObjectSourceHandleID(properties[4])); //Source Handle ID
//		getObjects().add(new ObjectSourceHandleName(properties[5])); //Source Handle Name
//		getObjects().add(new ObjectTargetHandleID(properties[6])); //Target Handle ID
//		getObjects().add(new ObjectTargetHandleName(properties[7])); //Target Handle Name	
		
		//Add Entities
		entities.add(new Entity(properties[0], true)); //Security ID
 		entities.add(new Entity(properties[1], true)); //Account Name
 		entities.add(new Entity(properties[3], true)); //Logon ID
 		
 		entities.add(new Entity(properties[5], false)); // Source Handle ID
		entities.add(new Entity(properties[6], false)); // Source Handle Name
		entities.add(new Entity(properties[5], false)); // Target Handle ID
		entities.add(new Entity(properties[6], false)); // Target Handle Name
	}
	
	public void populateEvent4703(String[] properties){
		//NOT VERY USEFUL
	}
	
	public void populateEvent4798(String[] properties){
		//Add subjects
//		getSubjects().add(new SubjectSecurityID(properties[0])); //Security ID
//		getSubjects().add(new SubjectAccountName(properties[1])); //Account Name
//		getSubjects().add(new SubjectLogonID(properties[3])); //Logon ID

		//Add objects
//		getObjects().add(new ObjectSecurityID(properties[4])); //Security ID
//		getObjects().add(new ObjectAccountName(properties[5])); //Account Name
//		getObjects().add(new ObjectAccountDomain(properties[6])); //Account Domain
//		getObjects().add(new ObjectProcessID(properties[7])); //Process ID	
//		getObjects().add(new ObjectProcessName(properties[8])); //Process Name	
		
		//Add Entities
		entities.add(new Entity(properties[0], true)); //Security ID
// 		entities.add(new Entity(properties[1], true)); //Account Name
// 		entities.add(new Entity(properties[3], true)); //Logon ID
	}
	
	public void populateEvent5154(String[] properties){
		//NOT VERY USEFUL
	}
	
	public void populateEvent5156(String[] properties){
		//NOT VERY USEFUL
	}
	
	public void populateEvent5158(String[] properties){
		//NOT VERY USEFUL
	}
	
	public void populateEvent6416(String[] properties){
		//Add subjects
//		getSubjects().add(properties[1]); //Account Name
//		getSubjects().add(properties[3]); //Logon ID
//		getSubjects().add(properties[4]); //Device ID
//		getSubjects().add(properties[5]); //Device Name
//		getSubjects().add(properties[7]); //Class Name
//		getSubjects().add(properties[8]); //Vendor ID
	}
	
	//WEB EVENTS
	public void populateWebEvent(String[] properties){
//		getObjects().add(properties[0]); //URL
//		getObjects().add(properties[1]); //Title
//		getObjects().add(properties[2]); //Host
//		getObjects().add(properties[3]); //Transition
		
		//Also add the subject for the web event
		entities.add(new Entity("C:\\Program Files\\Mozilla Firefox\\firefox.exe", true));//Process which creates a firefox events
		//Added myself so as to be able to uniquely identify firefox events and be able to correlate these events with that of windows
		
		entities.add(new Entity(properties[0], false)); //URL
 		entities.add(new Entity(properties[1], false)); //Title
 		entities.add(new Entity(properties[2], false)); //Host
 		entities.add(new Entity(properties[3], false)); //Transition
	}
}

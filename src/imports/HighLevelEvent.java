package imports;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import imports.Multiset;

public class HighLevelEvent extends Event{
	
	//Each HLE contains a list of LLEs
	private ArrayList<LowLevelEvent> lowLevelEventSubTimeline = new ArrayList<LowLevelEvent>();
	private Multiset<Entity> entityMultiSet = new Multiset<Entity>();
	private Multiset<String> anchorEvents = new Multiset<String>();
	private LocalDateTime start;
	private LocalDateTime end;
	
	public HighLevelEvent(int id, ArrayList<LowLevelEvent> lowLevelEventSubTimeline, Multiset<Entity> entityMultiSet, Multiset<String> anchorEvents) {
		super(id);
		// TODO Auto-generated constructor stub
		
		this.lowLevelEventSubTimeline = lowLevelEventSubTimeline;
		this.entityMultiSet = entityMultiSet;
		this.anchorEvents = anchorEvents;
		
		//Obtaining start and end time of high-level event
		start = lowLevelEventSubTimeline.get(0).getTime();
		end = lowLevelEventSubTimeline.get(lowLevelEventSubTimeline.size() - 1).getTime();
	}
	
	public HighLevelEvent(int id, Multiset<Entity> entityMultiSet) {
		super(id);
		// TODO Auto-generated constructor stub
		
		this.entityMultiSet = entityMultiSet;
	}
	
	//Getters
	public ArrayList<LowLevelEvent> getLowLevelEventSubTimeline() {
		return lowLevelEventSubTimeline;
	}
	
	public Multiset<Entity> getEntityMultiSet() {
		return entityMultiSet;
	}
	
	public Multiset<String> getAnchorEvents(){
		return anchorEvents;
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	
	public LocalDateTime getEnd() {
		return end;
	}
	
	//Setters
	public void setLowLevelEventSubTimeline(ArrayList<LowLevelEvent> lowLevelEventSubTimeline) {
		this.lowLevelEventSubTimeline = lowLevelEventSubTimeline;
	}

	public void setEntityMultiSet(Multiset<Entity> entityMultiSet) {
		this.entityMultiSet = entityMultiSet;
	}
	
	public void setAnchorEvents(Multiset<String> anchorEvents) {
		this.anchorEvents = anchorEvents;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	//Method which determines whether high-level event contains a specific entity
	public boolean containsEntity(Entity entity) {
		return entityMultiSet.contains(entity);
	}
	
	//Method which determines whether the high-level event matched a given test event
	public boolean containsAnchorEvent(String testEvent) {
		return anchorEvents.contains(testEvent);
	}
	
	//Methods to perform Allen Algebra
	//1/(y_start - x_end)
	public double allenAlgebraBeforeMillisecs(HighLevelEvent highLevelEvent) {
	//Only calculate from SADFC before(x, y) = 1/(x-y), before(x,y) = 1 if x = y
		double correlation;
		double timeDifference = Math.abs(Duration.between(highLevelEvent.getStart(), end).toMillis());
		
		if(timeDifference == 0){
			correlation = 1;
		}
		
		else{
			correlation = 1/(timeDifference);
		}		
		
		return correlation;
	}
	
	public double allenAlgebraBeforeSecs(HighLevelEvent highLevelEvent) {
		//Only calculate from SADFC before(x, y) = 1/(x-y), before(x,y) = 1 if x = y
			double correlation;
			double timeDifference = Math.abs(Duration.between(highLevelEvent.getStart(), end).toSeconds());
			
			if(timeDifference == 0){
				correlation = 1;
			}
			
			else{
				correlation = 1/(timeDifference);
			}		
			
			return correlation;
		}
	
	//x_start == y_start && x_end == y_end
	public double allenAlgebraEqual(HighLevelEvent highLevelEvent) {
		if(start.isEqual(highLevelEvent.getStart()) && end.isEqual(highLevelEvent.getEnd())) {
			return 1;
		}
		
		return 0;
	}
	
	//x_end == y_start
	public double allenAlgebraMeets(HighLevelEvent highLevelEvent) {
		if(end.isEqual(highLevelEvent.getStart())) {
			return 1;
		}
		
		return 0;
	}
	
	//x_start < y_start && x_end > y_start
	public double allenAlgebraOverlaps(HighLevelEvent highLevelEvent) {
		if(start.isBefore(highLevelEvent.getStart()) && end.isAfter(highLevelEvent.getStart())) {
			return 1;
		}
		
		return 0;
	}
	
	//x_start > y_start && x_end < y_end
	public double allenAlgebraDuring(HighLevelEvent highLevelEvent) {
		if(start.isAfter(highLevelEvent.getStart()) && end.isBefore(highLevelEvent.getStart())) {
			return 1;
		}
		
		return 0;
	}
	
	//x_start == y_start
	public double allenAlgebraStarts(HighLevelEvent highLevelEvent) {
		if(start.isEqual(highLevelEvent.getStart())) {
			return 1;
		}
		
		return 0;
	}
	
	//x_end == y_end 
	public double allenAlgebraFinishes(HighLevelEvent highLevelEvent) {
		if(end.isEqual(highLevelEvent.getEnd())) {
			return 1;
		}
		
		return 0;
	}
	
	//Temporal Correlation
	public double temporalCorrelationMillisecs(HighLevelEvent highLevelEvent, double alpha){		
		double correlation = alpha*allenAlgebraStarts(highLevelEvent) + alpha*allenAlgebraEqual(highLevelEvent) + allenAlgebraMeets(highLevelEvent) 
		+ allenAlgebraOverlaps(highLevelEvent) + allenAlgebraDuring(highLevelEvent) + allenAlgebraFinishes(highLevelEvent) + allenAlgebraBeforeMillisecs(highLevelEvent); 
		
		return correlation;
	}
	
	//Entity Correlation 
	public double entityCorrelation(HighLevelEvent highLevelEvent) {
		
		double entityCorr = 0;
		
		// E1 \cap E2
		//Obtain the size of the intersection between the multisets of the high level events in question
		Multiset<Entity> entityIntersection = entityMultiSet.intersection(highLevelEvent.getEntityMultiSet());
		
		// |E1 \cap E2|
		//Obtained the number of elements in the intersection, including duplicates
		int intersectionSize = entityIntersection.size();
		
		// max(|E1|, |E2|)
		//Obtaining the maximum size between the multisets
		int maxSize = Math.max(entityMultiSet.size(), highLevelEvent.getEntityMultiSet().size());
		
		if(maxSize == 0) {
			System.out.println();
		}
		
		try { 
			entityCorr = intersectionSize/maxSize;
        } 
        catch (ArithmeticException e) { 
            System.out.println( 
                "Divided by zero operation cannot possible"); 
        } 
		
		return entityCorr;
		
	}
	
	public void addSubject(Entity entity, Multiset<Entity> multiset) {
		if(entity.getEntityType() == true) {
			multiset.add(entity);
		}
	}
	
	public void addObject(Entity entity, Multiset<Entity> multiset) {
		if(entity.getEntityType() == false) {
			multiset.add(entity);
		}
	}
	
	//PERFORM TESTING ON ENTITY CORRELATIONS
	public double subjectCorrelation(HighLevelEvent highLevelEvent) {
		
		Multiset<Entity> subjects1 = new Multiset<Entity>();
		Multiset<Entity> subjects2 = new Multiset<Entity>();
		
		//Obtain all entities which are subjects
		//Subsets of the multisets
		entityMultiSet.forEach((e) -> addSubject(e, subjects1));
		highLevelEvent.getEntityMultiSet().forEach((e) -> addSubject(e, subjects2));	
		
		//Create new high-level events to hold these events
		HighLevelEvent hle1 = new HighLevelEvent(0, subjects1);
		HighLevelEvent hle2 = new HighLevelEvent(0, subjects2);
		
		return hle1.entityCorrelation(hle2);
	}
	
	public double objectCorrelation(HighLevelEvent highLevelEvent) {
		Multiset<Entity> objects1 = new Multiset<Entity>();
		Multiset<Entity> objects2 = new Multiset<Entity>();
		
		//Obtain all entities which are subjects
		//Subsets of the multisets
		entityMultiSet.forEach((e) -> addSubject(e, objects1));
		highLevelEvent.getEntityMultiSet().forEach((e) -> addSubject(e, objects2));	
		
		//Create new high-level events to hold these events
		HighLevelEvent hle1 = new HighLevelEvent(0, objects1);
		HighLevelEvent hle2 = new HighLevelEvent(0, objects2);
		
		return hle1.entityCorrelation(hle2);
	}
	
	public double correlation(HighLevelEvent highLevelEvent) {
		double correlation = 0;
		double temporalCorrelation = 0;
//		double subjectCorrelation = 0;
//		double objectCorrelation = 0;
		double entityCorrelation = 0;
		
		//ADD ANCHOR CORRELATION HERE WHICH IS VERY SIMILAR TO ENTITY CORRELATION
		
		
		//Calculate Temporal Correlation
		temporalCorrelation = temporalCorrelationMillisecs(highLevelEvent, 1.5);
		
//		//Calculate Subject Correlation
//		subjectCorrelation = subjectCorrelation(lowLevelEvent);
//		
//		//Calculate Object Correlation
//		objectCorrelation = objectCorrelation(lowLevelEvent);
		
		//Calculate entity correlation
		entityCorrelation = entityCorrelation(highLevelEvent);
		
		//Calculating correlation
		//Using formula from SADFC paper
//		correlation = temporalCorrelation + subjectCorrelation + objectCorrelation;
		correlation = temporalCorrelation + entityCorrelation;
		
		return correlation;
	}
	
	   @Override
	   public String toString() {
	      	   
		   return String.valueOf(id);
	   }
}

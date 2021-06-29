package imports;

public class HleEntityPair {
	
	private HighLevelEvent highLevelEvent;
	private Entity entity;
	
	public HleEntityPair(HighLevelEvent highLevelEvent, Entity entity) {
		this.highLevelEvent = highLevelEvent;
		this.entity = entity;
	}

	//Getters and Setters
	public HighLevelEvent getHighLevelEvent() {
		return highLevelEvent;
	}
	
	public void setHighLevelEvent(HighLevelEvent highLevelEvent) {
		this.highLevelEvent = highLevelEvent;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}

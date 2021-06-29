package imports;

public class Entity {
	
	//The value of the subject 
	private String entityValue;
	
	//Determines whether the entity is a subject or an object
	private boolean entityType;
	
	
	//Constructor to create a Subject object
	public Entity(String entityValue, boolean entityType){
		this.entityValue = entityValue;
		
		//true = subject
		//false = object
		this.entityType = entityType;
	}

	//Getters
	public String getEntityValue(){
		return entityValue;
	}
	
	public boolean getEntityType() {
		return entityType;
	}
	
	//Setters
	public void setEntityValue(String entityValue) {
		this.entityValue = entityValue;
	}
	
	public void setEntityType(boolean entityType) {
		this.entityType = entityType;
	}
	
	//Overriding the equality of Entities
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityValue == null) ? 0 : entityValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (entityValue == null) {
			if (other.entityValue != null)
				return false;
		} else if (!entityValue.equals(other.entityValue))
			return false;
		return true;
	}  
	
	@Override
	   public String toString() {
	      	   
		   return entityValue;
	   }

}

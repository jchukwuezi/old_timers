import com.opensymphony.xwork2.ActionSupport;

public class Question extends ActionSupport {
	private double temperature;
	private String breatheEasy;
	private String haveCough;
	private String senseOfSmell;
	
	public Question() {
		
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public String getBreatheEasy() {
		return breatheEasy;
	}

	public void setBreatheEasy(String breatheEasy) {
		this.breatheEasy = breatheEasy;
	}

	public String getHaveCough() {
		return haveCough;
	}

	public void setHaveCough(String haveCough) {
		this.haveCough = haveCough;
	}

	public String getSenseOfSmell() {
		return senseOfSmell;
	}

	public void setSenseOfSmell(String senseOfSmell) {
		this.senseOfSmell = senseOfSmell;
	}
	
	public String waitingRoom() {
		String result = "FAILURE";
		
		if(temperature > 38.0) {
			return result;
		}
		
		else if(breatheEasy.equalsIgnoreCase("NO")) {
			return result;
		}
		
		else if(haveCough.equalsIgnoreCase("YES")) {
			return result;
		}
		
		else if(senseOfSmell.equalsIgnoreCase("YES")) {
			return result;
		}
		
		else {
			result = "SUCCESS";
		}
		
		return result;
	}
	
	
	
	
}

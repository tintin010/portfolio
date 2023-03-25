package week6.jykim.konkuk;

public abstract  class HomeAppliance {
	protected String haName;
	protected boolean haPower = false;
	public HomeAppliance(String haName) {
		super();
		this.haName = haName;
	}
	public String getHaName() {
		return haName;
	}
	public void setHaName(String haName) {
		this.haName = haName;
	}
	public boolean isHaPower() {
		return haPower;
	}
	public void setHaPower(boolean haPower) {
		this.haPower = haPower;
	}
	
	public abstract void showStatus();
	
	public abstract void menu();

}

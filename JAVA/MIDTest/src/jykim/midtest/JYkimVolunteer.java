package jykim.midtest;

public abstract class JYkimVolunteer {
	

	private String volname;
	private int memnum;
	
	public JYkimVolunteer(String volname, int memnum) {
		super();
		this.volname = volname;
		this.memnum = memnum;
	}

	public String getVolname() {
		return volname;
	}

	public void setVolname(String volname) {
		this.volname = volname;
	}

	public int getMemnum() {
		return memnum;
	}

	public void setMemnum(int memnum) {
		this.memnum = memnum;
	}

}

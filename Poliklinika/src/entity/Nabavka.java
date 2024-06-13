package entity;

public class Nabavka {
	private StringBuilder sb;

	public Nabavka(StringBuilder sb) {
		this.sb = sb;
	}
	
	public StringBuilder getSb() {
		return sb;
	}
	
	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}

	@Override
	public String toString() {
		return sb.toString();
	}
}

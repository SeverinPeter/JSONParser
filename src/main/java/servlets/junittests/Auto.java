package junittests;

import java.util.ArrayList;
import java.util.List;

public class Auto
{
	
	private int kennnumer;
	private String typ;
	private List<String> ausstattung = new ArrayList<>();
	private Person besitzer = new Person();
	
	public int getKennnumer()
	{
		return kennnumer;
	}
	public void setKennnumer(int kennnumer)
	{
		this.kennnumer = kennnumer;
	}
	public String getTyp()
	{
		return typ;
	}
	public void setTyp(String typ)
	{
		this.typ = typ;
	}
	public List<String> getAusstattung()
	{
		return ausstattung;
	}
	public void setAusstattung(List<String> ausstattung)
	{
		this.ausstattung = ausstattung;
	}
	public Person getBesitzer()
	{
		return besitzer;
	}
	public void setBesitzer(Person besitzer)
	{
		this.besitzer = besitzer;
	}
	

}

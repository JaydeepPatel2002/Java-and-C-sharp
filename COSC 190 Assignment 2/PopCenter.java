package assign2;

public class PopCenter
{
	private String sName;
	private String sSizeGroup;
	private int nLatestPop;
	private int nEarlierPop;
	
	

	public PopCenter(String sName, String sSizeGroup, int nLatestPop, int nEarlierPop) 
	{
		super();
		this.sName = sName;
		this.sSizeGroup = sSizeGroup;
		this.nLatestPop = nLatestPop;
		this.nEarlierPop = nEarlierPop;
	}
	
	

	public String getsName() 
	{
		return sName;
	}





	public String getsSizeGroup()
	{
		return sSizeGroup;
	}





	public int getnLatestPop() 
	{
		return nLatestPop;
	}





	public int getnEarlierPop()
	{
		return nEarlierPop;
	}
	
	





	@Override
	public String toString() 
	{
		return "PopCenter [sName=" + sName + ", sSizeGroup=" + sSizeGroup + ", nLatestPop=" + nLatestPop
				+ ", nEarlierPop=" + nEarlierPop + "]";
	}

}

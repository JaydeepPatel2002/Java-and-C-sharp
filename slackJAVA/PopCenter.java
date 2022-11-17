import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PopCenter {
	private String sName, sSizeGroup;
	private int nLatestPop, nEarlierPop;
	
	public PopCenter(String Name, String Group, int lSize, int eSize)
	{
		this.sName = Name;
		this.sSizeGroup = Group;
		this.nLatestPop = lSize;
		this.nEarlierPop = eSize;
		
	}
	
	/**
	 * Additional constructor given to allow for comma seperated lsit.
	 * @param saVals
	 */
	public PopCenter(String[] saVals)
	{
		this.sName = saVals[1];
		this.sSizeGroup = saVals[2];
		this.nEarlierPop = Integer.parseInt(saVals[4]);
		this.nLatestPop = Integer.parseInt(saVals[3]);
		
		
	}
	
	public static String[] parseCSVLine(String sLine)
	{
		String sPattern = ",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))";
		String[]  sFields = sLine.split(sPattern);
		for (int i = 0; i < sFields.length; i++) {
            // Get rid of residual double quotes
            sFields[i] = sFields[i].replace("\"", "");
        }
        return sFields;
	}
	
	public static ArrayList<PopCenter> loadPopCenters(String sFileName)
	{
		ArrayList<PopCenter> arr = new ArrayList<PopCenter>();
		try(BufferedReader oReader =  new BufferedReader(new FileReader(sFileName)))
		{
			oReader.lines().skip(1).map(x-> new PopCenter(parseCSVLine(x))).forEach(x->arr.add(x));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
		
	}
	
	

	//Required Getters
	public String getName()
	{
		return sName;
	}

	public String getGroup()
	{
		return sSizeGroup;
	}

	public int getLatest()
	{
		return nLatestPop;
	}

	public int getEarliest()
	{
		return nEarlierPop;
	}
	
	
	@Override public String toString()
	{
		return String.format("City: %s  Last Population %d", this.getName(), this.getLatest());
	}
}

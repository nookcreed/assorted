import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


public class InputDriver {

	private String fileName;
	private HashMap<String, HashSet<String>> personWineRequests;
	private HashMap<String, HashSet<String>> winePersonList;
	
	static class InputFields {
		public static int personId = 0;
		public static int wineId = 1;
	}
	
	public InputDriver(String _fileName) {
		this.fileName = _fileName;
		this.personWineRequests = new HashMap<String, HashSet<String>>();
		this.winePersonList = new HashMap<String, HashSet<String>>();
	}
	
	public boolean parseInput() {
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		}

		try {
			String strLine;
			while ((strLine = in.readLine()) != null)   {
				String[] tokens = strLine.split("\t");
				if(this.personWineRequests.get(tokens[InputFields.personId]) == null) {
					HashSet<String> wineList = new HashSet<String>();
					wineList.add(tokens[InputFields.wineId]);
					this.personWineRequests.put(tokens[InputFields.personId], wineList);
				}
				else
				{	
					HashSet<String> personWineList = this.personWineRequests.get(tokens[InputFields.personId]);
					personWineList.add(tokens[InputFields.wineId]);
					this.personWineRequests.put(tokens[InputFields.personId],personWineList);
				}
				
				if(this.winePersonList.get(tokens[InputFields.wineId]) == null) {
					HashSet<String> personList = new HashSet<String>();
					personList.add(tokens[InputFields.personId]);
					this.winePersonList.put(tokens[InputFields.wineId], personList);
				}
				else
				{	
					HashSet<String> winePersonList = this.winePersonList.get(tokens[InputFields.wineId]);
					winePersonList.add(tokens[InputFields.personId]);
					this.winePersonList.put(tokens[InputFields.wineId],winePersonList);
				}
			}
		} catch (IOException e) {
			//Do nothing, continue with processing
			//Could be cleaner. Someday....
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
		
	}
	
	public HashMap<String, HashSet<String>> getPersonWineRequests() {
		return personWineRequests;
	}

	public void setPersonWineRequests(
			HashMap<String, HashSet<String>> personWineRequests) {
		this.personWineRequests = personWineRequests;
	}

	public HashMap<String, HashSet<String>> getWinePersonList() {
		return winePersonList;
	}

	public void setWinePersonList(HashMap<String, HashSet<String>> winePersonList) {
		this.winePersonList = winePersonList;
	}

}

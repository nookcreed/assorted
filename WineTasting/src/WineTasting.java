import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class WineTasting {
	private static HashMap<String, HashSet<String>> wineListAssignments = new HashMap<String, HashSet<String>>();

	private static HashMap<String, HashSet<String>> generateWineListAssignments() {
		InputDriver driver = new InputDriver("/home/varun/person_wine_3.txt");
		driver.parseInput();

		HashMap<String, HashSet<String>> personWineRequests = driver.getPersonWineRequests();
		HashMap<String, HashSet<String>> winePersonList = driver.getWinePersonList();
		Set<String> persons = personWineRequests.keySet();
		Set<String> wines = driver.getWinePersonList().keySet();

		for (Map.Entry<String, HashSet<String>> entry : personWineRequests.entrySet()) {
			if(entry.getValue().size() == 1) {
				wineListAssignments.put(entry.getKey(), entry.getValue());
				personWineRequests.remove(entry.getKey());
				wines.remove(entry.getValue().iterator().next());
				persons.remove(entry.getKey());
			}
		}

		for (String wine : wines) {
			HashSet<String> personsForThisWine = winePersonList.get(wine);
			for (String per : personsForThisWine) {
				HashSet<String> wineList;
				if(wineListAssignments.get(per) == null) {
					wineList = new HashSet<String>();
					wineList.add(wine);
					wineListAssignments.put(per, wineList);
				}
				else if(wineListAssignments.get(per).size() < 3) {
					wineList = wineListAssignments.get(per);
					wineList.add(wine);
					wineListAssignments.put(per, wineList);
					//wines.remove(wine);
					if(wineListAssignments.get(per).size() == 3) {
						//persons.remove(per);
					}
					break;
				}
			}
		}

		return wineListAssignments;
	}

	public static void outputWineListAssignments() {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter("/home/varun/assignments.txt"));
			for (Map.Entry<String, HashSet<String>> entry : WineTasting.generateWineListAssignments().entrySet()) {
				for (String e : entry.getValue()) {
					out.write(entry.getKey() + "\t" + e);
					out.newLine();
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		WineTasting.outputWineListAssignments();
	}

}

package in.sivaa.personal.tools.mobile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Sivasubramaniam Arunachalam
 * 
 *         Snippet to remove duplicate vcard entries and remove all the facebook
 *         associations
 * 
 */
public class DuplicateRemover {

    public static void main(String[] args) throws IOException {

	BufferedReader bf = new BufferedReader(new FileReader("myvcf.vcf"));
	Set<String> contactSet = new HashSet<String>();
	String line;
	String currentRecord = "";
	boolean isDuplicate = false;

	while ((line = bf.readLine()) != null) {

	    if (line.startsWith("BEGIN:VCARD")) {
		if (!isDuplicate) {
		    System.out.println(currentRecord);
		}
		currentRecord = line;
		isDuplicate = false;
	    } else {
		if (!line.startsWith("NOTE:<HTCData>")) {
		    currentRecord += "\n" + line;
		}
	    }

	    if (line.startsWith("FN:")) {
		if (!contactSet.add(line)) {
		    isDuplicate = true;
		}
	    }

	}

	bf.close();
    }
}

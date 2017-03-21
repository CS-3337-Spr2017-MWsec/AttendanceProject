import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Administrator test = new Administrator("Name","Mr","Richards","email","richards","ilovejose");
		//example: student card scan
		String student = "%B6048880000654321^PERSON/THREE 4912120000000000000000000000000?;6048880000419099=4912120303452468?";
		
		String trash;
		String trash_2;
		String firstName;
		String lastName;
		String trash_3;
		String trash_4;
		String midName;
		String temp_id;
		String id;
		
		int number = 1;
		String delims = "[%,/,?,=,;,^, ]+";
		String[] tokens = student.split(delims);
		for (int i = 0; i < tokens.length; i++)
			System.out.println(number++ + ") " + tokens[i]);
			trash = tokens[0];
			trash_2 = tokens[1];
			firstName = tokens[2];
			lastName = tokens[3];
			trash_3 = tokens[4];
			trash_4 = tokens[5];
			temp_id = tokens[6];
		System.out.println("trash: " + trash);
		System.out.println("trash_2: " + trash_2);
		System.out.println("firstName: " + firstName);
		System.out.println("lastName: " + lastName);
		System.out.println("trash_3: " + trash_3);
		System.out.println("trash_4: " + trash_4);
		System.out.println("id: " + temp_id);
		ArrayList<String> strings = new ArrayList<String>();
		int index = 0;
		while (index < temp_id.length()) {
			strings.add(temp_id.substring(index, Math.min(index + 7, temp_id.length())));
			index += 7;
		}
		System.out.println("id: " + strings);
		/*output:
		 *  1) 
			2) B6048880000654321
			3) PERSON
			4) THREE
			5) 4912120000000000000000000000000
			6) 6048880000419099
			7) 4912120303452468
			trash: 
			trash_2: B6048880000654321
			firstName: PERSON
			lastName: THREE
			trash_3: 4912120000000000000000000000000
			trash_4: 6048880000419099
			id: 4912120303452468
			above string is parsed incorrectly below
			student ID should be 303452468
			id: [4912120, 3034524, 68]
		 */
	}

}

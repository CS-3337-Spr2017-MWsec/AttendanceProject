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
		String midName;
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
			id = tokens[5];
		System.out.println("trash: " + trash);
		System.out.println("trash_2: " + trash_2);
		System.out.println("firstName: " + firstName);
		System.out.println("lastName: " + lastName);
		System.out.println("trash_3: " + trash_3);
		System.out.println("id: " + id);
		
		
	}

}

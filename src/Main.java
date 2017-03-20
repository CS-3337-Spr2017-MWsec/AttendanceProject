import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Administrator test = new Administrator("Name","Mr","Richards","email","richards","ilovejose");
		//example: student card scan
		String student = "%B6048880000654321^PERSON/THREE 4912120000000000000000000000000?;6048880000419099=4912120303452468?";
		
		String firstName;
		String lastName;
		String midName;
		String id;
		
		int number = 1;
		String delims = "[%,/,?,=,;,^, ]+";
		String[] tokens = student.split(delims);
		for (int i = 0; i < tokens.length; i++)
			System.out.println(number++ + ") " + tokens[i]);
		/* output:
		 1) 
		 2) B6048880000654321
		 3) PERSON
		 4) THREE
		 5) 4912120000000000000000000000000
		 6) 6048880000419099
		 7) 4912120303452468
			*/
	}

}

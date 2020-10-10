import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class EmailChallenge {

	public static void main(String[] args) throws MalformedURLException {

		// Asks for the email ID and appends it to the web address
		String emailID = formatID(getEmailID());
		String webAddress = "https://www.ecs.soton.ac.uk/people/" + emailID;

		URL url = new URL(webAddress);
		String source = getSourceCode(url);

		String name = getName(source);
		System.out.println(name);

	}

	private static String getEmailID() {

		System.out.println("Enter user ID:");

		// Opens a scanner on the console and stores the input in a string id
		Scanner scan = new Scanner(System.in);
		String id = scan.nextLine();
		scan.close();

		return id;

	}

	private static String formatID(String emailID) {

		// Makes the input lower case
		char chars[] = emailID.toLowerCase().toCharArray();
		String text = "";

		// Goes through each character and if it is a number or lower case character
		// adds it to the text
		for (int i = 0; i < chars.length; i++) {

			if ((chars[i] > 47 && chars[i] < 58) || (chars[i] > 96 && chars[i] < 123)) {

				text += chars[i];

			}

		}

		return text;

	}

	private static String getSourceCode(URL url) {

		String text = "";

		// Tries to access the url and get an input stream
		try {

			// Creates a reader to access the source code for the url
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			// The name is on the 8th line so the first 7 can be ignored
			for (int i = 0; i < 7; i++) {

				reader.readLine();

			}

			// The 8th line is stored in text
			text = reader.readLine();

			reader.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return text;

	}

	private static String getName(String source) {

		String text = "";

		// Takes the line and finds the string between <title> and | which is the name
		text = source.substring(source.indexOf(">") + 1, source.indexOf(" | "));

		if (text.equals("People")) {

			text = "Unknown user";

		}

		return text;

	}

}

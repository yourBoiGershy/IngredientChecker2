import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Main {

	public static String result1 = "", result2 = "", result3 = "", result4 = "", result5 = "";


	public static String GetIngredients(String search){

		result1 = "";
		result2 = "";
		result3 = "";
		result4 = "";
		result5 = "";

		String content = "", output = "", result = "";
		URLConnection connection = null;
		int i = 0;
		try {
			String items = GetItemBarcode.getBarcode(search), item = "";
			int counter = 0;
			while(items.indexOf(", ")>-1&&counter<5){
				item = items.substring(0, items.indexOf(", "));
				//System.out.println(item);
				if(item.length()+2!=items.length()) {
					items = items.substring(items.indexOf(", ")+2);
					//System.out.println(items);
				} else
					items = "";
				connection = new URL("https://world.openfoodfacts.org/api/v0/product/"+item+".json").openConnection();
				Scanner scanner = new Scanner(connection.getInputStream());
				scanner.useDelimiter("\\Z");
				content = scanner.next();
				scanner.close();
				int delimeter = content.indexOf("ingredients_text_with_allergens_en");

				if(delimeter>0&&content.indexOf("\",", delimeter+39)>-1&&content.indexOf(("\"\""), delimeter)-(delimeter+35)>5) {


					output = content.substring(delimeter + 37, content.indexOf("\",", delimeter+39));
//						GetItemBarcode.name = GetItemBarcode.name.substring(GetItemBarcode.name.indexOf("  "));
					while(output.indexOf("<span class=")>-1){
						String b = output;
						output = b.substring(0,b.indexOf("<span class="));
						output += b.substring(b.indexOf("<span class=")+25, b.indexOf("</span>"));
						output += b.substring(b.indexOf("</span>")+7);
					}

					System.out.println(output);

					if(output.length()>300) {
						output = output.substring(output.indexOf(" ", 100), output.indexOf(" ", 220)) + "\n"
						+output.substring(output.indexOf(" ", 220), output.indexOf(" ", 300))+"\n"
						+output.substring(output.indexOf(" ", 300));
//						output = output.substring(0,140)+"\n"+output.substring(140,280)+"\n"+output.substring(280);
					}else if(output.length()>220){
						output = output.substring(0, output.indexOf(" ",100))
								+"\n"+output.substring(output.indexOf(" ", 100), output.indexOf(" ", 200))
								+"\n"+output.substring(output.indexOf(" ", 200));
					}else if(output.length()>120){
						output = output.substring(0, output.indexOf(" ", 100))
								+"\n"+output.substring(output.indexOf(" ", 100));
					}


					switch(counter){
						case 0: result1 = output; System.out.println(result1); break;
						case 1: result2 = output; System.out.println(result2); break;
						case 2: result3 = output; System.out.println(result3); break;
						case 3: result4 = output; System.out.println(result4); break;
						case 4: result5 = output; System.out.println(result5); break;
					}

					result += output+"\n";

				}else{
					output = "no added ingredients";
					result += output + "\n\n";
					switch(counter){
						case 0: result1 = "no added ingredients"; System.out.println(result1); break;
						case 1: result2 = "no added ingredients"; System.out.println(result2); break;
						case 2: result3 = "no added ingredients"; System.out.println(result3); break;
						case 3: result4 = "no added ingredients"; System.out.println(result4); break;
						case 4: result5 = "no added ingredients"; System.out.println(result5); break;
					}
				}
				output = "";
				counter++;
			}

		}catch ( Exception ex ) {
			ex.printStackTrace();
		}

		return result;
	}

	public static boolean netIsAvailable() {
		try {
			final URL url = new URL("http://www.google.com");
			final URLConnection conn = url.openConnection();
			conn.connect();
			conn.getInputStream().close();
			return true;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			return false;
		}
	}
}
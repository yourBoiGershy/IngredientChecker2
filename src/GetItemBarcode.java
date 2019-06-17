import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GetItemBarcode {

	public static String name, string, name1, name2, name3, name4, name5, link, link1, link2, link3, link4, link5;

	public static String getBarcode(String a){
		//initialize and declare bunch of variables
		String product = a, filepath = "ICS4U/openfoodfactsSearch.txt", output = "";
		name = ""; 
		name1 = "";
		name2 = "";
		name3 = "";
		name4 = "";
		name5 = "";
		link = "";
		link1 = "";
		link2 = "";
		link3 = "";
		link4 = "";
		//open the file
		try{
			IO.openInputFile(filepath);
			int counter = 0;
			String buffer = "";
			//read the second line since the first is useless
			buffer = IO.readLine();
			buffer = IO.readLine();
			//check if the buffer is not null
			while(buffer!=null){
				if(buffer.indexOf(product)>0&&counter<5){
					//look for index of fifth tab
					output += buffer.substring(0, buffer.indexOf("\t"))+", ";
					int b = buffer.indexOf("\t");
					b = buffer.indexOf("\t", b+1);
					b = buffer.indexOf("\t", b+1);
					b = buffer.indexOf("\t", b+1);
					b = buffer.indexOf("\t", b+1);
					if (b > -1&&counter < 5) {

						string = buffer.substring(b, buffer.indexOf("\t", b+1));
						if(string.length()>50){
							string = string.substring(0, string.indexOf(" ", 40))+"\n      "
									+string.substring(string.indexOf(" ", 40));
						}
						//initialize names
						switch (counter){
							case 0: name1 = string; break;
							case 1: name2 = string; break;
							case 2: name3 = string; break;
							case 3: name4 = string; break;
							case 4: name5 = string; break;
						}
					}

					name += "\n\n\n";

					//set links if they are provided
					int c = buffer.indexOf("https://", 25);
					if(c > -1&&counter<5){
						switch(counter){
							case 0: link = buffer.substring(c, buffer.indexOf("\t", c + 1)); System.out.println(link); break;
							case 1: link1 = buffer.substring(c, buffer.indexOf("\t", c + 1)); System.out.println(link1); break;
							case 2: link2 = buffer.substring(c, buffer.indexOf("\t", c + 1)); System.out.println(link2); break;
							case 3: link3 = buffer.substring(c, buffer.indexOf("\t", c + 1)); System.out.println(link3); break;
							case 4: link4 = buffer.substring(c, buffer.indexOf("\t", c + 1)); System.out.println(link4); break;

						}
					}else if (c == -1 && counter < 5){ //if links are not provided, set the image to question mark
						switch(counter){
							case 0: link = "Images/abc.jpg"; System.out.println("Images/abc.jpg"); break;
							case 1: link1 = "Images/abc.jpg"; System.out.println("Images/abc.jpg"); break;
							case 2: link2 = "Images/abc.jpg"; System.out.println("Images/abc.jpg"); break;
							case 3: link3 = "Images/abc.jpg"; System.out.println("Images/abc.jpg"); break;
							case 4: link4 = "Images/abc.jpg"; System.out.println("Images/abc.jpg"); break;
						}

					}
					counter++;
				}
				buffer = IO.readLine();
			}

			IO.closeInputFile();
			while(counter < 5){ //error trapping for searches with less than 5 searches
				switch(counter) {
					case 0:
						link = "Images/abc.jpg";
						System.out.println("Images/abc.jpg");
						break;
					case 1:
						link1 = "Images/abc.jpg";
						System.out.println("Images/abc.jpg");
						break;
					case 2:
						link2 = "Images/abc.jpg";
						System.out.println("Images/abc.jpg");
						break;
					case 3:
						link3 = "Images/abc.jpg";
						System.out.println("Images/abc.jpg");
						break;
					case 4:
						link4 = "Images/abc.jpg";
						System.out.println("Images/abc.jpg");
						break;
				}
				counter++;
			}
		}

		catch(IOException e){
			System.out.println("Couldn't read the text file");
		}
		if(output!=null) {
			System.out.println(output);
			return output;
		}else{
			return "failed";
		}
	}
}

package algo.builder;

import java.io.IOException;

public class Application {

	public static void main(String[] args) {
		MapBuilder mb = new MapBuilder();
		try {
			mb.load("C:\\wamp\\www\\ProjetAlgo\\src\\algo\\builder\\test1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
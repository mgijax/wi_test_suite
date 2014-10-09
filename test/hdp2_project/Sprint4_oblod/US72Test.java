package hdp2_project.Sprint4_oblod;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import nu.xom.Document;

import org.concordion.internal.XMLParser;
import org.jax.mgi.fewi.test.concordion.BaseConcordionTest;

public class US72Test extends BaseConcordionTest {
	public String getPass() {
		return "PASS";
	}

	public String getFail() {
		return "FAIL";
	}

	public String testGrid() throws Exception {

		URL url = new URL("http://localhost.jax.org/diseasePortal/grid");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		con.setDoOutput(true);
		
		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes("phenotypes=311300");
		wr.flush();
		wr.close();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		String ret = response.toString();
		try {
			Document d = XMLParser.parse(ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return ret;
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LKExamResult extends Thread {
    public static void main(String args[]) {
        String loginUrl = "http://www.doenets.lk/exam/exam_OL.jsp";

        java.util.ArrayList<String> arr = new java.util.ArrayList<String>();
        try {

            String indexNumbers = "11059443";
            String numArr[] = indexNumbers.split(",");
            for (String num : numArr) {
                callUrl(loginUrl, num);
                Thread.sleep(3000);
            }
        } catch (Exception e) {// Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

    public static void callUrl(String loginUrl, String number) {
        try {
            System.out.println(loginUrl + " " + number);
            URL url = new URL(loginUrl);

            HttpURLConnection urlConnection;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(Integer.parseInt("20000"));
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write("indexno=" + URLEncoder.encode(number, "UTF-8"));
            out.write("&action=" + URLEncoder.encode("result", "UTF-8"));
            out.write("&exyear=" + URLEncoder.encode("2011", "UTF-8"));

            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String sLine = "";
            String sXmlReply = "";
            while ((sLine = in.readLine()) != null) {

                sXmlReply += sLine;
            }

            in.close();
            System.out.println("Result for Number : " + number);
            System.out.println(sXmlReply);

            } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
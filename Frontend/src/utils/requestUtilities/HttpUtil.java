package utils.requestUtilities;

import org.json.JSONObject;
import player.Player;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUtil {


    public static JSONObject sendPostRequestWithResponse(String urlString, String jsonRequestBody) {
        try {
            // Setup HTTP connection
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Send request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonRequestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get response code
            int responseCode = connection.getResponseCode();

            // Handle successful response (HTTP 200 or HTTP_CREATED)
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    // Parse and return the JSON response
//                    System.out.println(response.toString());
                    return new JSONObject(response.toString());
                }
            } else {
                // Handle non-success response
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Error: " + responseCode, "Error", JOptionPane.ERROR_MESSAGE);
                });
                return null;
            }
        } catch (Exception e) {
            // Handle exceptions
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            });
            return null;
        }
    }


    public static boolean sendPostRequest(String urlString, String jsonRequestBody) {
        try {
            // Setup HTTP connection
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Send request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonRequestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get response code
            int responseCode = connection.getResponseCode();

            // Handle successful response (HTTP 200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Update UI with the response message
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Success");
                });
                return true;
            } else {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Error: " + responseCode, "Error", JOptionPane.ERROR_MESSAGE);
                });
                return false;
            }

        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            });
            return false;
        }
    }

    public static String sendGetRequest(String urlString) {
        try {
            // Setup HTTP connection
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            // Get response code
            int responseCode = connection.getResponseCode();

            // Handle successful response (HTTP 200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("OK");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString(); // Return the raw response

            } else {
                System.out.println("NO");
                return null; // Handle error response
            }

        } catch (Exception e) {

            e.printStackTrace();
            return null; // Handle exception
        }
    }
}

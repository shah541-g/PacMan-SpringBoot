package pages;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static widgetFactories.FrameFactory.getFrame;

public class SinglePlayerGameDetailsPage {

    public static void showSinglePlayerGameDetailsPage(String jsonResponse) {
        // Create a new JFrame for displaying game details
        JFrame detailsFrame = getFrame("Single Player Game Details", 800,600, JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to hold the game details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout()); // Use BorderLayout for proper arrangement
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around the panel

        // Create a combo box for filtering by status (win/lose)
        String[] statusOptions = {"All", "WIN", "LOSE"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);
        statusComboBox.setSelectedIndex(0); // Default to "All"

        // Panel for the filter controls
        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter by Status:"));
        filterPanel.add(statusComboBox);
        detailsPanel.add(filterPanel, BorderLayout.NORTH);

        // Parse the JSON response and display game details
        try {
            // Assuming the response is a JSON array of game details
            JSONArray gameArray = new JSONArray(jsonResponse);

            // Column names for the table
            String[] columnNames = {"Game ID", "Status", "Score", "Timestamp"};

            // Prepare data for the table
            ArrayList<Object> filteredData = new ArrayList<>();

            // Filter games based on selected status
            statusComboBox.addActionListener(e -> {
                String selectedStatus = (String) statusComboBox.getSelectedItem();
                filteredData.clear(); // Clear the previous filter data

                // Iterate through the gameArray and filter by status
                for (int i = 0; i < gameArray.length(); i++) {
                    JSONObject game = gameArray.getJSONObject(i);

                    // Apply filter if status matches or "All" is selected
                    String gameStatus = game.getString("status");
                    if (selectedStatus.equals("All") || selectedStatus.equals(gameStatus)) {
                        Object[] rowData = new Object[4];
                        rowData[0] = game.getInt("gameId");
                        rowData[1] = gameStatus;
                        rowData[2] = game.getInt("score");
                        rowData[3] = game.getString("timestamp"); // You might want to format this timestamp
                        filteredData.add(rowData);
                    }
                }
                // After filtering, update the table with the new data
                updateTable(detailsPanel, filteredData.toArray(new Object[0][]), columnNames);
            });

            // Initialize the table with all games by default
            for (int i = 0; i < gameArray.length(); i++) {
                JSONObject game = gameArray.getJSONObject(i);
                Object[] rowData = new Object[4];
                rowData[0] = game.getInt("gameId");
                rowData[1] = game.getString("status");
                rowData[2] = game.getInt("score");
                rowData[3] = game.getString("timestamp"); // You might want to format this timestamp
                filteredData.add(rowData);
            }

            // Create a JTable to display the filtered game details
            JTable gameTable = new JTable(filteredData.toArray(new Object[0][]), columnNames);
            gameTable.setFillsViewportHeight(true); // Make the table fill the available space

            // Create a JScrollPane to add scroll functionality
            JScrollPane scrollPane = new JScrollPane(gameTable);
            detailsPanel.add(scrollPane, BorderLayout.CENTER); // Add table to the panel in the center

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error parsing game details", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Create a close button to close the frame
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> detailsFrame.dispose());

        // Set background color for the panel
        detailsPanel.setBackground(Color.BLACK);

        // Add the close button at the bottom of the panel
        detailsPanel.add(closeButton, BorderLayout.SOUTH);

        // Add the details panel to the frame
        detailsFrame.add(detailsPanel);

        // Make the frame visible
        detailsFrame.setVisible(true);
    }

    // Method to update the table with filtered data
    private static void updateTable(JPanel detailsPanel, Object[][] filteredData, String[] columnNames) {
        // Create a new JTable with updated filtered data
        JTable updatedTable = new JTable(filteredData, columnNames);
        JScrollPane scrollPane = new JScrollPane(updatedTable);

        // Remove all existing content and re-add the updated table
        detailsPanel.removeAll(); // Remove the old content
        detailsPanel.add(scrollPane, BorderLayout.CENTER); // Add updated table in the center

        // Add the close button again after updating the table
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> ((JFrame) SwingUtilities.getWindowAncestor(detailsPanel)).dispose());
        detailsPanel.add(closeButton, BorderLayout.SOUTH); // Add close button at the bottom

        // Revalidate and repaint to update the UI
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

}

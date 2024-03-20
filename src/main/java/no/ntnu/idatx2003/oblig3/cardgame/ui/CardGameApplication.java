package no.ntnu.idatx2003.oblig3.cardgame.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.List;
import no.ntnu.idatx2003.oblig3.cardgame.logic.DeckOfCards;
import no.ntnu.idatx2003.oblig3.cardgame.data.PlayingCard;

/**
 * The main application class for the card game.
 * This class sets up the user interface and handles user interactions.
 *
 */
public class CardGameApplication extends Application {
    private DeckOfCards deck;
    private TextField handResultTextField;

    /**
     * Starts the JavaFX application.
     *
     * @param primaryStage The primary stage for this application, onto which the application scene can be set.
     * @throws Exception If an error occurs during the start of the application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane rootNode = new BorderPane();
        rootNode.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null))); // Set green background for the entire scene

        GridPane centerPane = new GridPane();
        deck = new DeckOfCards();

        Button dealHandButton = new Button("Deal Hand");
        dealHandButton.setOnAction(event -> {
            List<PlayingCard> hand = deck.dealHand();
            System.out.println("Dealt hand: " + hand);
            centerPane.getChildren().clear(); // Clear previous images

            HBox cardBox = new HBox(); // Create an HBox to contain the card images
            cardBox.setSpacing(20); // Set spacing between images in the HBox
            cardBox.setPadding(new Insets(0, 20, 0, 20)); // Add padding before the first card

            for (PlayingCard card : hand) {
                System.out.println(card.getAsString());
                Image image = new Image(getClass().getResource("/cards_images/" + card.getAsString() + ".png").toExternalForm());

                ImageView imageView = new ImageView(image);
                double desiredWidth = 120; // Set the desired width in pixels
                double desiredHeight = 190; // Set the desired height in pixels
                imageView.setFitWidth(desiredWidth);
                imageView.setFitHeight(desiredHeight);

                cardBox.getChildren().add(imageView); // Add the ImageView to the HBox
            }

            VBox vbox = new VBox(); // Create a VBox to center the HBox vertically
            vbox.getChildren().add(cardBox);
            vbox.setAlignment(Pos.CENTER);

            // Set green background for the VBox
            vbox.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

            // Set preferred size for the VBox
            vbox.setPrefSize(500, 450);

            centerPane.add(vbox, 0, 0); // Add the VBox to the GridPane
        });

        Button checkHeartButton = new Button("Check Hearts");
        checkHeartButton.setOnAction(event -> {
            List<PlayingCard> hand = deck.dealHand();
            String result = getHearts(hand);
            handResultTextField.setText(result);
        });

        Button checkQueenOfSpadesButton = new Button("Check Queen of Spades");
        checkQueenOfSpadesButton.setOnAction(event -> {
            List<PlayingCard> hand = deck.dealHand();
            String result = checkQueenOfSpades(hand);
            handResultTextField.setText(result);
        });

        Button checkFiveFlushButton = new Button("Check 5-Flush");
        checkFiveFlushButton.setOnAction(event -> {
            List<PlayingCard> hand = deck.dealHand();
            String result = checkFiveFlush(hand);
            handResultTextField.setText(result);
        });

        handResultTextField = new TextField();
        handResultTextField.setEditable(false);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(dealHandButton, checkHeartButton, checkQueenOfSpadesButton, checkFiveFlushButton);

        VBox controlBox = new VBox(10);
        controlBox.setAlignment(Pos.CENTER);
        controlBox.getChildren().addAll(handResultTextField, buttonBox);

        rootNode.setBottom(controlBox);
        rootNode.setCenter(centerPane);

        // Place the "Deal Hand" button in the bottom-right corner
        BorderPane.setAlignment(controlBox, Pos.BOTTOM_CENTER);

        Scene scene = new Scene(rootNode, 800, 600);
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Returns a string containing all the hearts in the hand.
     * @param hand The hand to check
     * @return A string containing all the hearts in the hand, or "No Hearts" if no hearts are found
     */
    private String getHearts(List<PlayingCard> hand) {
        StringBuilder result = new StringBuilder();
        boolean foundHearts = false;
        for (PlayingCard card : hand) {
            if (card.getSuit() == 'H') {
                result.append(card.getAsString()).append(" ");
                foundHearts = true;
            }
        }
        if (!foundHearts) {
            result.append("No Hearts");
        }
        return result.toString().trim();
    }

    /**
     * Returns a string indicating whether the Queen of Spades is found in the hand.
     * @param hand The hand to check
     * @return "Queen of Spades found" if the Queen of Spades is found, or "Queen of Spades not found" if not
     */
    private String checkQueenOfSpades(List<PlayingCard> hand) {
        for (PlayingCard card : hand) {
            if (card.getSuit() == 'S' && card.getFace() == 12) { // Checking for Queen of Spades
                return "Queen of Spades found";
            }
        }
        return "Queen of Spades not found";
    }


    /**
     * Returns a string indicating whether a 5-Flush is found in the hand.
     * @param hand The hand to check
     * @return "5-Flush found" if a 5-Flush is found, or "No 5-Flush" if not
     */
    private String checkFiveFlush(List<PlayingCard> hand) {
        int heartsCount = 0, diamondsCount = 0, clubsCount = 0, spadesCount = 0;
        for (PlayingCard card : hand) {
            switch (card.getSuit()) {
                case 'H':
                    heartsCount++;
                    break;
                case 'D':
                    diamondsCount++;
                    break;
                case 'C':
                    clubsCount++;
                    break;
                case 'S':
                    spadesCount++;
                    break;
            }
        }
        if (heartsCount >= 5 || diamondsCount >= 5 || clubsCount >= 5 || spadesCount >= 5) {
            return "5-Flush found";
        } else {
            return "No 5-Flush";
        }
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}

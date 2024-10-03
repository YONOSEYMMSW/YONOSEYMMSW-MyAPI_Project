package BSU.EDU.cs;

import java.util.Scanner;

public class UI {

    public static void main(String[] args) {
        UI ui = new UI();
        String userArticle = ui.getUserRequest();
        ArticleDataFetcher requestData = new ArticleDataFetcher();

        try {
            // Fetch article data by passing the userArticle
            String result = requestData.fetchArticleData(userArticle);
            ui.displayDataRequested(result);
        } catch (Exception e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
    }

    // Method to get the article name from the user
    public String getUserRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME USER: ");
        System.out.println();
        System.out.println("What is the article name for your request?");
        return scanner.nextLine();
    }

    // Method to display the data requested
    public void displayDataRequested(String result) {
        System.out.println("Article Data: ");
        System.out.println(result);  // Printing the result here
    }
}

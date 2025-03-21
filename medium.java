import java.util.*;

class CardCollection {
    private Map<String, List<String>> cardMap;
    private Scanner scanner;

    public CardCollection() {
        cardMap = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addCard(String symbol, String card) {
        cardMap.putIfAbsent(symbol, new ArrayList<>());
        cardMap.get(symbol).add(card);
    }

    public void searchCardsBySymbol(String symbol) {
        if (cardMap.containsKey(symbol)) {
            System.out.println("Cards with symbol " + symbol + ": " + cardMap.get(symbol));
        } else {
            System.out.println("No cards found for symbol: " + symbol);
        }
    }

    public void displayAllCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards in the collection.");
            return;
        }
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println("Symbol: " + entry.getKey() + " -> Cards: " + entry.getValue());
        }
    }

    public void run() {
        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter card symbol (e.g., Hearts, Spades): ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter card name (e.g., Ace, King, 2): ");
                    String card = scanner.nextLine();
                    addCard(symbol, card);
                    System.out.println("Card added successfully!");
                    break;
                case 2:
                    System.out.print("Enter symbol to search: ");
                    String searchSymbol = scanner.nextLine();
                    searchCardsBySymbol(searchSymbol);
                    break;
                case 3:
                    displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        CardCollection system = new CardCollection();
        system.run();
    }
}

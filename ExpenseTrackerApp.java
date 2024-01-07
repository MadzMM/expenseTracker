import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class ExpenseTrackerApp {
	
	private static final Scanner scanner = new Scanner(System.in);
	private static final DataHandler dataHandler = new DataHandler();
		
	public static void main(String[] args) {
		showMenu();
	}
		
	private static void showMenu() {
		int choice;
		do{
			System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Categories");
            System.out.println("2. Add Transactions");
            System.out.println("3. Edit/Delete Transactions");
            System.out.println("4. View Recent Transactions");
            System.out.println("5. Set Budgets");
            System.out.println("6. Track Spending against Budgets");
			System.out.println("7. View Budgets");
            System.out.println("0. Exit");
			
			System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
			
			switch (choice) {
				case 1:
					addCategories();
					break;
				
				case 2:
					addTransactions();
					break;
					
				case 3:
					editOrDeleteTransactions();
					break;
					
				case 4:
					recentTransactions();
					break;
					
				case 5:
					setBudget();
					break;
					
				case 6:
					spendingAgainstBudgets();
					break;
					
				case 7:
					viewBudgets();
					break;
					
				case 0:
					System.out.println("Exiting the Expense Tracker. Goodbye!");
                    break;
					
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
			}
		} while(choice !=0);
	}	
	
	private static void addTransactions() {
		System.out.println("Adding a new transaction:");
		
		System.out.print("Enter the amount: ");
		double amount = scanner.nextDouble();
		scanner.nextLine();
		
		System.out.print("Enter 'expense' or 'income': ");
		String type = scanner.nextLine().trim();
		
		System.out.print("Enter the category: ");
		String category = scanner.nextLine().trim();

		System.out.print("Enter a note (if any): ");
		String note = scanner.nextLine().trim();
		
		System.out.print("Is it recurring? (true/false): ");
		boolean isRecurring = scanner.nextBoolean();
		scanner.nextLine(); // Consume newline character
		
		Transaction transaction = new Transaction(amount, type, category, note, isRecurring);
		dataHandler.addTransaction(transaction);
		System.out.println("Transaction added successfully.");
	}
	
	
	private static void addCategories(){
		System.out.println("Adding a new category: ");
		System.out.print("Enter the category name: ");
		String categoryName = scanner.nextLine().trim();
		
		Category category = new Category(categoryName);
		dataHandler.addCategory(category);
		System.out.println("Category: " + categoryName + " added.");
	}
	
	private static void editOrDeleteTransactions() {
		
	}
	
	private static void recentTransactions() {
		//taking the user input count to retrive the recent transactions
		System.out.print("Enter the number of recent transactions to view: ");
        int count = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Recent Transactions: ");
		
		//Retrive recent transactions from the DataHandler
		List<Transaction> recentTransactions = dataHandler.getRecentTransactions(count);
		
		if(recentTransactions.isEmpty()){
			System.out.println("No Transactions available.");
		} else {
			for (Transaction transaction: recentTransactions){
				System.out.println(transaction.toString());
			}
		}
	}
	
	private static void setBudget() {
		System.out.println("Set Budget for Categories:");
		
		List<Category> categories = dataHandler.getCategories();

		if (categories.isEmpty()) {
			System.out.println("No categories available. Add categories first.");
			return;
		}
		 System.out.println("Categories:");
		for (int i = 0; i < categories.size(); i++) {
			System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
		}
		
		//Prompt user to select a category
		System.out.print("Enter the category name to set the budget: ");
		String categoryName = scanner.nextLine().trim();
		
		boolean categoryFound = false;
		for(Category category : categories){
			if(category.getCategoryName().equalsIgnoreCase(categoryName)){
				System.out.print("Enter the budget for " + categoryName + ": " );
				double budgetAmount = scanner.nextDouble();
				scanner.nextLine();
				
				dataHandler.setBudgetForCategory(category, budgetAmount);
				categoryFound = true;
				
				System.out.println("Budget set successfully for " + category.getCategoryName());
				
				break;
			}
		}
		if (!categoryFound) {
			System.out.println("Category not found.");
		}
	}
	
	private static void spendingAgainstBudgets(){
		
	}	
	
	//view budgets
	private static void viewBudgets(){
		System.out.println("Category-wise Budgets:");
		
		Map<String, Double> categoryBudgets = dataHandler.getCategoryBudgets();
		
		if(categoryBudgets.isEmpty()){
			System.out.println("No budgets available. Set Budget first.");
		} else {
			for (Map.Entry<String, Double> entry: categoryBudgets.entrySet()) {
				String categoryName = entry.getKey();
				double budgetAmount = entry.getValue();
				System.out.println(categoryName + ": " + budgetAmount);
			}
		}
		
	}
}


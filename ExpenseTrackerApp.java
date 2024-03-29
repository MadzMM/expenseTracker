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
            System.out.println("3. Delete Transactions");
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
					deleteTransaction();
					break;
					
				case 4:
					recentTransactions();
					break;
					
				case 5:
					setBudget();
					break;
					
				case 6:
					viewCategorySums();
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
	
	//add new transaction
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
	
	
	//add categories
	private static void addCategories(){
		
		//display existing categories
		System.out.println("Existing Categories:");
		List<Category> categories = dataHandler.getCategories();
		for(Category category : categories) {
			System.out.println(category.getCategoryName());
		}
		
		//add new category
		System.out.println("Adding a new category: ");
		System.out.print("Enter the category name: ");
		String categoryName = scanner.nextLine().trim();
		
		Category category = new Category(categoryName);
		dataHandler.addCategory(category);
		System.out.println("Category: " + categoryName + " added.");
	}
	
	//delete transaction
	private static void deleteTransaction() {
		System.out.print("Enter the number of recent transactions that you want to view, from that list you can select which transaction to delete: ");
		int count = scanner.nextInt();
		scanner.nextLine();
		
		List<Transaction> recentTransactions = dataHandler.getRecentTransactions(count);
		
		if(recentTransactions.isEmpty()){
			System.out.println("No transactions avaiable.");
			return;
		} else {
			System.out.println("Transactions available:");
			
			for(int i=0; i< recentTransactions.size(); i++){
				System.out.println((i+1) + ". " + recentTransactions.get(i).toString());
			}
			
			System.out.print("Select the index of the transaction that you want to delete:");
			int index = scanner.nextInt();
			scanner.nextLine();
			
			if(index>0 && index<=recentTransactions.size()) {
				boolean isDeleted = dataHandler.deleteTransaction(index-1);
				
				if(isDeleted) {
					System.out.println("Transaction deleted successfully.");
				} else {
					System.out.println("Failed to deleted the transaction. Please try again!");
				}
			} else {
				System.out.println("Invalid transaction number.");
			}
		}
	}
	
	//view recent transactions
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
	
	//Set budget for a category
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
	
	//view category sum
	private static void viewCategorySums(){
		System.out.println("Category-wise Transaction Sums and Budgets:");
		
		Map<String, Double> categorySums = dataHandler.calculateCategorySums();
		
		if(categorySums.isEmpty()){
			System.out.println("No transactions available.");
		} else {
			for (Map.Entry<String, Double> entry: categorySums.entrySet()) {
				String categoryName = entry.getKey();
				double sum = entry.getValue();
				
				double budget = dataHandler.getBudgetForCategory(categoryName);
				
				System.out.println(categoryName + " - Total Transactions: " + sum);
				
				if (budget != 0.0) {
					double balance = budget - sum;
					double percentage = (sum / budget) * 100;
					
					System.out.println("Budget for " + categoryName + ": " + budget);
					System.out.println("Balance: " + balance);
					System.out.println("Percentage of Balance: " + percentage + "%");
				} else {
					System.out.println("Budget not set for " + categoryName);
				}
				
				System.out.println();
			}
		}
	}
}


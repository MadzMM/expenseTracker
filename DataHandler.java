import java.util.ArrayList;
import java.util.List;

//import java.util.HashMap;
//import.java.util.Map;

public class DataHandler {
	private List<Transaction> transactions;
	private List<Category> categories;
	
	public DataHandler() {
		transactions = new ArrayList<>();
		categories = new ArrayList<>();
		
		//initialize categories with preset values
		Category foodCategory = new Category("Food");
		Category transportationCategory = new Category("Transportation");
		
		categories.add(foodCategory);
		categories.add(transportationCategory);
	
	}
	
	//methods to simulate data storage and retrieval
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	
	public void addCategory(Category category) {
		categories.add(category);
	}
	
	  // Implementing getRecentTransactions method
		public List<Transaction> getRecentTransactions(int count) {
			int size = transactions.size();
			int startIndex = Math.max(0, size - count);

			List<Transaction> recentTransactions = new ArrayList<>();

			for (int i = startIndex; i < size; i++) {
				recentTransactions.add(transactions.get(i));
			}
			return recentTransactions;
		}
}
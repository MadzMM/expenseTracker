import java.util.HashMap;
import java.util.Map;

public class Budget {
	private Map<String, Double> categoryBudgets;
	
	public Budget(){
		categoryBudgets = new HashMap<>();
	}
	
	public void setCategoryBudgets(String category, double amount) {
		categoryBudgets.put(category, amount);
	}
	
	public double getCategoryBudgets(String category) {
		return categoryBudgets.getOrDefault(category, 0.0);
	}
}
public class Transaction {
	private double amount;
	private String type; //income or expense
	private String category;
	private String note;
	private boolean isRecurring;
	
	//contructors, getters, setters
	public Transaction(double amount, String type, String category, String note, boolean isRecurring) {
		this.amount = amount;
		this.type = type;
		this.category = category;
		this.note = note;
		this.isRecurring = isRecurring;
	}
	
	public double getAmount(){
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public boolean isRecurring(){
		return isRecurring;
	}
	
	public void setRecurring(boolean recurring){
		isRecurring = recurring;
	}
	
	@Override
	public String toString(){
		return "Transaction details : Amount = " + amount + ", Type = " + type + ", Category = " + category + ", Note = " + note + ", Recurring = " + isRecurring;
	}
}
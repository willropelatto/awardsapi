package br.com.goldenraspberryawards.exceptions;

public class ErrorAwardInterval extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8425690065269212063L;
	private EnumCategory category;

	public ErrorAwardInterval(EnumCategory category, String message, Throwable cause) {
		super(message, cause);
		this.setCategory(category);
	}

	public EnumCategory getCategory() {
		return category;
	}

	public void setCategory(EnumCategory category) {
		this.category = category;
	}

}

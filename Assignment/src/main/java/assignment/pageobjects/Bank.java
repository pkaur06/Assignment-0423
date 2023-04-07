package assignment.pageobjects;

public class Bank {
	// Initial custom balance
	int total = 0;

	// Money withdrawal method. Withdraw only if total money
	// greater than or equal to the money requested for
	// withdrawal
	void withdrawn(String name, int withdrawal)
	{

		if (total >= withdrawal) {
			System.out.println(name + " withdrawn "
					+ withdrawal);
			total = total - withdrawal;

			System.out.println(total);

		}

		// Else if the money requested for withdrawal is
		// greater than the balance then deny transaction
		else {

			System.out.println(name
					+ " you can not withdraw "
					+ withdrawal);
			System.out.println("your balance is: " + total);

		}
	}

	// Method - To deposit money
	// Accepting money whenever deposited
	void deposit(String name, int deposit)
	{
		//driver.findElement(By.cssSelector("input[placeholder='amount']"));
		System.out.println(name + " deposited " + deposit);
		total = total + deposit;
		System.out.println("Balance after deposit: "
				+ total);

	}

}

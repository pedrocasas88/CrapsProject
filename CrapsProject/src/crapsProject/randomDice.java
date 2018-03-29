package crapsProject;

public class randomDice 
{
	
	public static int die1;
	public static int die2;
	public static int sumOfDice;
	
	public randomDice()
	{
		//the random numbers for the dice
		die1 = (int)(Math.random()*6) + 1;
		die2 = (int)(Math.random()*6) + 1;
		
		System.out.println("Die1: " + die1);
		System.out.println("Die2: " + die2);
	
	}
	public static int getDie1()
	{
		return die1;
	}
	
	public static int getDie2()
	{
		return die2;
	}
	
	
	public static int getSumOfDice()
	{
		sumOfDice = die1 + die2;	
		
		return sumOfDice;
		
	}
}

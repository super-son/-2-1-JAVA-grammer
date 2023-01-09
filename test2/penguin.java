package test2;

import test.Bird;

public class penguin extends Bird{
	
	public void move(){
		System.out.println("slide...");
	}
	
	public static void main(String[] args) {
		penguin p = new penguin();
		System.out.println ("wings: " + p.numberwing);
		p.move();

	}

}

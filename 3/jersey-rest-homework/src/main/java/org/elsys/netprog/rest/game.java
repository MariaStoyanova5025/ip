package org.elsys.netprog.rest;

import java.util.Random;

public class game {
	public Integer gameId;
	public Integer number;
	public Integer count;
	public Boolean success;
	
	public game(Integer gameId) {
		this.gameId = gameId;
		this.count = 0;
		this.success = false;
		this.number = random();
	}
	public Integer random() {
		Integer a, b, c, d;
		Random rand = new Random();

		a = rand.nextInt(9) + 1;
		while((b = rand.nextInt(9) + 0) == a) {}
		while((c = rand.nextInt(9) + 0) == b || (c = rand.nextInt(9) + 0) == a) {}
		while((d = rand.nextInt(9) + 0) == c || (d = rand.nextInt(9) + 0) == b || (d = rand.nextInt(9) + 0) == a) {}
		
		return a*1000 + b*100 + c*10 + d;
		
	}
	public Integer cowsAndBulls(Integer guess) {
		count++;
		Integer cowsAndBulls = 0;
		Integer bulls = 0;
		Integer cows = 0;
		int a, b;
		int number1 = number, number2 = guess;
		if(guess.equals(number)) {
			success = true;
			return 4;}
		for(int i = 0;i < 4; i++)
		{
			a = number1 % 10;
			b = number2 % 10;
			if(a == b) bulls++;
			number1 = number1 / 10;
			number2 = number2 / 10;
		}
		number1 = number;
		number2 = guess;
		Integer d1[] = new Integer[4];
		Integer d2[] = new Integer[4];
		for(int i = 0;i < 4; i++)
		{
			d1[i] = number1 % 10;
			d2[i] = number2 % 10;
			number1 = number1 / 10;
			number2 = number2 / 10;
		}
		
		for(int i = 0;i < 4; i++)
		{
			for(int k = 0;k < 4; k++) {
				if(k == i) continue;
				if(d1[i] == d2[k])
				{
					cows++;
				}
			}
		}
		cowsAndBulls = cows*10 + bulls;
		return cowsAndBulls;
	}
}

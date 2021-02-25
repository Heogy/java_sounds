package edu.heog.sound;

import javax.sound.sampled.LineUnavailableException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GuessTone
{

	private static final Map<String, Float> notes = new HashMap<>();

	static
	{
		notes.put("C4", 261.63F);
		notes.put("E4", 329.63F);
		notes.put("G4", 392.00F);
		notes.put("A4", 440.00F);
		notes.put("C5", 523.25F);
		notes.put("E5", 659.25F);
	}

	public static void main(String[] args)
	{

		try
		{
			Random random = new Random();

			while (true)
			{
				int key= random.nextInt(notes.size());
				String toGuess = new ArrayList<>(notes.keySet()).get(key);
				Synth.createTone(notes.get(toGuess), 100);

				System.out.print("what'toGuess this note (A4-E5) ? ");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String guess = reader.readLine();

				if (guess.equals(toGuess))
				{
					System.out.println("nice !!");
				}
				else
				{
					System.out.println("Oups it was : " + toGuess);
				}
			}} catch (LineUnavailableException | IOException lue)
		{
			System.out.println(lue);
		}
	}

}

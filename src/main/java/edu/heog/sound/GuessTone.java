package edu.heog.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
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
				int key = random.nextInt(notes.size());
//				System.out.println(key);
				String toGuess = new ArrayList<>(notes.keySet()).get(key);
//				System.out.println("to guess : " + toGuess);
				Float aFloat = notes.get(toGuess);
//				System.out.println(aFloat);
				GuessTone.createTone(notes.get(toGuess), 100);

				System.out.print("what'toGuess this note (A4-E5) ? ");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String guess = reader.readLine();

				if (guess.equals(toGuess))
				{
					System.out.println("nice !!");
				}
				else
				{
					System.out.println("40H pratice! every day");
				}
			}
		} catch (LineUnavailableException | IOException lue)
		{
			System.out.println(lue);
		}
	}

	/**
	 * parameters are frequency in frequency and volume
	 **/
	public static void createTone(float frequency, int volume)
			throws LineUnavailableException
	{
		/** Exception is thrown when line cannot be opened */

		float rate = 44100;
		byte[] buf;
		AudioFormat audioF;

		buf = new byte[1];
		audioF = new AudioFormat(rate, 8, 1, true, false);
		//sampleRate, sampleSizeInBits,channels,signed,bigEndian

		SourceDataLine sourceDL = AudioSystem.getSourceDataLine(audioF);
		sourceDL = AudioSystem.getSourceDataLine(audioF);
		sourceDL.open(audioF);
		sourceDL.start();


		for (int i = 0; i < rate; i++)
		{
			double angle = (i / rate) * frequency * 2.0 * Math.PI;
			buf[0] = (byte) (Math.sin(angle) * volume);
			sourceDL.write(buf, 0, 1);
		}

		sourceDL.drain();
		sourceDL.stop();
		sourceDL.close();
	}
}
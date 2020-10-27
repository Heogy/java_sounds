package edu.heog.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Synth
{

	public static void createTone(float frequency, int volume)
			throws LineUnavailableException
	{
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


	public static void createOctave(float frequency, int volume)
			throws LineUnavailableException
	{
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
			double angle2 = (i / rate) * frequency * 2.0 * 2.0 * Math.PI;
			buf[0] = (byte) ((Math.sin(angle) * volume) + (Math.sin(angle2) * volume));
			sourceDL.write(buf, 0, 1);
		}

		sourceDL.drain();
		sourceDL.stop();
		sourceDL.close();
	}

	public static void createMultiTone(List<Float> frequencies, int volume)
			throws LineUnavailableException
	{
		float rate = 44100;
		byte[] buf;
		AudioFormat audioF;

		buf = new byte[8];
		audioF = new AudioFormat(rate, 8, 1, true, false);
		//sampleRate, sampleSizeInBits,channels,signed,bigEndian

		SourceDataLine sourceDL = AudioSystem.getSourceDataLine(audioF);
		sourceDL = AudioSystem.getSourceDataLine(audioF);
		sourceDL.open(audioF);
		sourceDL.start();


		for (int i = 0; i < rate; i++)
		{
			List<Double> angles = new ArrayList<>();
			for (Float frequency : frequencies)
			{
				angles.add((i / rate) * frequency * 2.0 * Math.PI);
			}
			double angleResult = 0;
			for (Double angle : angles)
			{
				angleResult += Math.sin(angle) * volume;
			}

			buf[0] = (byte) angleResult;
			sourceDL.write(buf, 0, 1);
		}

		sourceDL.drain();
		sourceDL.stop();
		sourceDL.close();
	}

	public static byte[] doubleToBytes(double d){

		return null;

	}

	public static void createMultiNotes(List<Notes> frequencies, int volume)
			throws LineUnavailableException
	{
		createMultiTone(frequencies.stream().map(Notes::getFreq).collect(Collectors.toList()), volume);
	}

	public static void main(String[] args) throws LineUnavailableException
	{
//		createTone(Notes.C4.getFreq(), 50);
//		createTone(Notes.E4.getFreq(), 50);
//		createTone(Notes.G4.getFreq(), 50);
//		createMultiNotes(Arrays.asList(Notes.C4), 50);
//		createMultiNotes(Arrays.asList(Notes.C4, Notes.E4), 50);
//		createMultiNotes(Arrays.asList(Notes.E4, Notes.G4), 50);
		createMultiNotes(Arrays.asList(Notes.C4, Notes.E4, Notes.G4), 42);
	}

}

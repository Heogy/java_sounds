package edu.heog.sound;

public enum Notes
{
	C4(261.63F),
	E4(329.63F),
	G4(392.00F),
	A4(440.00F),
	C5(523.25F),
	E5(659.25F);

	public float freq;

	Notes(float freq)
	{
		this.freq = freq;
	}

	public float getFreq()
	{
		return freq;
	}
}

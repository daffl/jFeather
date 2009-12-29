package de.neyeon.feathry.beans;

public class ChoiceException extends Exception
{
	private static final long serialVersionUID = -5017292165827404810L;

	public ChoiceException()
	{
		super();
	}

	public ChoiceException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ChoiceException(String message)
	{
		super(message);
	}

	public ChoiceException(Throwable cause)
	{
		super(cause);
	}

}

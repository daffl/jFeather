package org.feathry.beans;

/**
 * A Choice exception is thrown by the {@link ExtendedDynaBean} if the algorithm can not
 * determine a distinct choice or no choice at all for a specific class. 
 * @author David Luecke (daff@neyeon.de)
 */
public class ChoiceException extends Exception
{
	private static final long serialVersionUID = -5017292165827404810L;

	/**
	 * Default constructor
	 */
	public ChoiceException()
	{
		super();
	}

	/**
	 * Create new ChoiceException with message and cause.
	 * @param message The message for this exception
	 * @param cause Root cause exception
	 */
	public ChoiceException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Create new ChoiceException with given message.
	 * @param message The message for this exception
	 */
	public ChoiceException(String message)
	{
		super(message);
	}

	/**
	 * Create new ChoiceException with given root cause.
	 * @param cause Root cause exception
	 */
	public ChoiceException(Throwable cause)
	{
		super(cause);
	}

}

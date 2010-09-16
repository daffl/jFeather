package org.feathry.dispatcher.rest;

import java.io.InputStream;
import java.io.OutputStream;

public interface Representation
{
	public ContentType[] getContentTypes();
	public Object deserialize(InputStream is);
	public OutputStream serialize(Object arg);
}

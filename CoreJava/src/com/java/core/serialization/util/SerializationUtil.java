package com.java.core.serialization.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil 
{
	// serialize the given object and save it to file
	public static void serialize(Object obj, String fileName)
	{
		try(FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);)
		{
			oos.writeObject(obj);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	// deserialize to Object from given file
	public static Object deserialize(String fileName) 
	{
		Object obj = null;
				
		try(FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);)
		{
			obj = ois.readObject();
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return obj;
	}
}

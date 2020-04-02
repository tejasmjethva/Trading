package com.java.core.serialization.inheritance;

public class Car extends Vehicle// implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String type;
	
	private int height;
	
	private int width;

	public Car()
	{
		System.out.println("Car's class constructor called"); 
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Custom Serialization
	 * 
	 * Order should be maintained while writing and reading, it should b eexactly same.
	 */
	// By implementing writeObject method,  
    // we can prevent 
    // subclass from serialization 
//    private void writeObject(ObjectOutputStream out) throws IOException 
//    { 
//        out.writeUTF(this.type);
//        out.writeInt(this.getSpeed());
//    } 
//      
//    // By implementing readObject method,  
//    // we can prevent 
//    // subclass from de-serialization 
//    private void readObject(ObjectInputStream in) throws IOException 
//    { 
//        this.type = in.readUTF();
//        this.setSpeed(in.readInt());
//    } 

	@Override
	public String toString() {
		return "Car [type=" + type + ", height=" + height + ", width=" + width + ", getSpeed()=" + getSpeed() + "]";
	}
	
}

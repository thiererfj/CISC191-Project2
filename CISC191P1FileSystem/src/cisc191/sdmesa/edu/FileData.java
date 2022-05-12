package cisc191.sdmesa.edu;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * <<add more references here>>
 * 
 * 
 *  
 * Version/date: 2.4 04/05/2022
 * 
 * Responsibilities of class:
 * FileData is designed so that instances of the class act as computer files, which have a name and content for 
 * File System program user's to read from and write to.   
 */
public class FileData 
{
	private String fileName = "Empty";    //I changed this, originally it wasn't initialized
	private String contents;
	
	/**
	 * Single arg constructor to create Files objects
	 * 
	 * @param newFileName
	 */
	public FileData(String newFileName) 
	{
		this.fileName = newFileName;
	}
	
	/**
	 * @param fileName
	 */
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}
	
	/**
	 * @param new contents
	 */
	public void setContents(StringBuilder newContents) 
	{
		this.contents = newContents.toString();
	}
	
	/**
	 * @return files' name
	 */
	public String getFileName() 
	{
		return fileName;
	}
	
	/**
	 * @return files' contents
	 */
	public String getContents() 
	{
		return contents;
	}
	
}

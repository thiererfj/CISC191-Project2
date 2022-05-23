package cisc191.sdmesa.edu;

/**
 * Lead Author(s):
 * @author Anthony Mayoral
 * @author Francis Thierer
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *  
 * Version/date: 4.4 05/22/22
 * 
 * Responsibilities of class:
 * FileData is designed so that instances of it behave like computer files, which have a name and content.
 * The file system program will read from and write to these objects. 
 */
public class FileData 
{
	// Name of file
	private String fileName = "Empty";
	
	// Contents of file
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

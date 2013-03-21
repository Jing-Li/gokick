import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MatchUtil {
	
	private static final String MATCH_FILE = "match";
	
	public static boolean isFileExist(){
		return new File(MATCH_FILE).exists();
	}
	public static void removeFile(){
		if(isFileExist()){
			new File(MATCH_FILE).delete();
		}
	}
	public static Match readMatch(){
		Match match = null;
		
		FileInputStream freader = null;  
		ObjectInputStream objectInputStream = null;
        try {  
            freader = new FileInputStream(MATCH_FILE);  
            objectInputStream = new ObjectInputStream(freader);  
            match = (Match) objectInputStream.readObject();  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  finally {
        	try {
        		if(freader!=null){
        			freader.close();
        		}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return match;
	}
	
	public static void writeMatch(Match match){
		try {  
            
            FileOutputStream outStream = new FileOutputStream(MATCH_FILE);  
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);  
              
            objectOutputStream.writeObject(match);  
            outStream.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
	}
}


import java.io.File;
        import java.io.IOException;
        import java.util.*;

/**
 * Create a java program to search through the home directory and look for files that match a regular expression.
 * The program should be able to take inputs repeatedly and should print out the full absolute path
 * of the matching files found.
 */

public class FileMatcher {

    public ArrayList<File> getAllFilesInDirectories(String directory)throws Exception{
        ArrayList<File> result = new ArrayList<File>();
        Stack<File> stack = new Stack<File>();
        File dir = new File(directory);
        if(!dir.isDirectory()) {
            result.add(dir);
            return result;
        }

        stack.add(dir);
        while(!stack.empty()) {
            File sub_directory = stack.pop();
            File[] arr = sub_directory.listFiles();
            for(int i=0;i<arr.length;i++) {
                if(arr[i].isDirectory()) {  //checking if the file is directory
                    stack.add(arr[i]);
                }else {
                    result.add(arr[i]);   // adding file to list
                }
            }
        }


        return result;
    }

    /**
     * @param directory User Input directory
     * @param expression
     * @return
     * @throws Exception
     */
    public List<String> getFileMatchingList(String directory,String expression)throws Exception{
        List<String> result = new ArrayList<String>();
        List<File> allFiles = getAllFilesInDirectories(directory);
        Iterator i = allFiles.iterator();
        while(i.hasNext()) {
            File file = (File)i.next();
            String fileName = file.getName();

            if(fileName.matches(expression)) {
                result.add(file.getAbsolutePath());
            }
        }


        return result;
    }





    public static void main(String args[]) throws Exception {
        FileMatcher fs = new FileMatcher();

        List<String> list = fs.getFileMatchingList("/home/sandea",".*\\.java");
        list.forEach(System.out::println);


 /*       FileMatcher fs = new FileMatcher();
        String homeDirectory=System.getProperty("user.home");
        File homeDir = new File(homeDirectory);
        fs.takeInput(homeDir);*/

    }

}
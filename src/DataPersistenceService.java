import java.io.*;

public class DataPersistenceService {

    public static void saveData(String fileName, Object data){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(data);
            System.out.println("Data saved to " + fileName);

        } catch(IOException e){
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static Object loadData(String filename){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            return ois.readObject();
        } catch (FileNotFoundException e){
            System.out.println("File not found: " + filename + ". Starting fresh.");
        } catch(IOException | ClassNotFoundException e){
            System.out.println("Error loading data: " + e.getMessage());
        }
        return null;
    }
}

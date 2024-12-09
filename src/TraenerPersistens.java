import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TraenerPersistens
{

    private ArrayList<Traener> traeners;

    public TraenerPersistens(ArrayList<Traener> traeners)
    {
        this.traeners = traeners;
    }

    // Skriv trænere til fil
    public void writeTraenerToFile()
    {
        File traenerFile = new File("/Users/peytonhunter/Library/CloudStorage/OneDrive-Personal/Documents/Datamatiker/1 Semester/Programmering/InteliJ/Delfinen/src/traenerPersistens.txt"); // Path to file

        try (FileWriter writer = new FileWriter(traenerFile))
        {
            writer.write("LISTE OVER TRÆNERE\n\n");
            for (int i = 0; i < traeners.size(); i++)
            {
                Traener t = traeners.get(i);
                writer.write(t.toString() + "\n");
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
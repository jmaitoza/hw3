import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Question2 implements Runnable
{
    private int value = 0;
    private int character = 0;
    public final FileReader fr = new FileReader("input.txt");
    public  FileWriter fw = new FileWriter("output.txt");

    public Question2() throws IOException
    {
    }

    private synchronized void incrementAndWrite() throws IOException
    {
        value++;
        character = fr.read();
        //System.out.println((char)character);
        fw.write(character);
        if (value == 4)
        {
            fw.close();
            fr.close();
        }
    }

    @Override
    public void run()
    {
        try
        {
            incrementAndWrite();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws IOException
    {
        Question2 q = new Question2();
        new Thread(q, "A").start();
        new Thread(q, "B").start();
        new Thread(q, "C").start();
        new Thread(q, "D").start();
    }
}

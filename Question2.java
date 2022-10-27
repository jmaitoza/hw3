import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Question2 implements Runnable
{
    private int value = 0;
    private int character = 0;
    public final FileReader fr = new FileReader("input.txt");
    public final FileWriter fw = new FileWriter("output.txt");

    public Question2() throws IOException
    {
        //This just keeps the compiler happy about FileReader and FileWriter
    }

    private synchronized void incrementAndWrite() throws IOException
    {
        value++;
        character = fr.read();
        fw.write(character);
        if (value == 4) // check if 4 characters have been written and close the files
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

    public static void main(String[] args) throws IOException, InterruptedException
    {
        Question2 q = new Question2();
        System.out.println("Starting threads and reading input.txt, please wait...");
        new Thread(q, "A").start();
        //join is used to make sure that thread A is finished before thread B starts
        Thread.currentThread().join(500);
        new Thread(q, "B").start();
        Thread.currentThread().join(500);
        new Thread(q, "C").start();
        Thread.currentThread().join(500);
        new Thread(q, "D").start();
        Thread.currentThread().join(500);
        System.out.println("Threads finished, please check output.txt");
    }
}

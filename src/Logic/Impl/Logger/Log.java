package Logic.Impl.Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    /* Design Pattern - Singleton */
    private static final String FILE_NAME = "Elevators.log";
    private final Object IOLock = new Object();
    private boolean printToConsole = false;
    
    private Log() throws IOException {
        try {
            File logFile = new File(FILE_NAME);
            if(logFile.delete()) {
                logFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static Log Instance = null;

    public static synchronized Log getInstance() {
        if(Instance == null) {
            try {
                Instance = new Log();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Instance;
    }

    private String getFormattedTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    public void setPrintToConsole(boolean printToConsole) {
        this.printToConsole = printToConsole;
    }

    public void PrintAndWriteToLog(String msg) {
        synchronized (IOLock) {
            try {
                FileWriter myWriter = new FileWriter(FILE_NAME, true);
                myWriter.write(getFormattedTime() + " | " + msg + "\n");
                myWriter.close();
                if(printToConsole) {
                    System.out.println(getFormattedTime() + " | " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readFromLog() {
        String logOutput = "";
        synchronized (IOLock) {
            try {
                FileInputStream fstream = new FileInputStream(FILE_NAME);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    logOutput += strLine + "\n";
                }
                fstream.close();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        return logOutput;
    }

}

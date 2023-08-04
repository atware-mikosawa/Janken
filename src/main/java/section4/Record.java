package section4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Record {

//    public void saveRecords(int winRecordPlayer1, int loseRecordPlayer1, int winRecordPlayer2, int loseRecordPlayer2) {
//        String data = winRecordPlayer1 + "," + loseRecordPlayer1 + "," + winRecordPlayer2 + "," + loseRecordPlayer2;

//            Files.write(Paths.get("jankenRecords.txt"), data.getBytes());

//            System.out.println("エラーが発生しました：" + e.getMessage());

//    }

//    public void loadRecord() {
//        try {
//            String data = new String(Files.readAllBytes(Paths.get("jankenRecords.txt")));
//            String[] parts = data.split(",");
//        } catch (IOException e) {
//            System.out.println("エラーが発生しました：" + e.getMessage());
//        }
//    }

//    public void loadRecord(int winRecordPlayer1, int loseRecordPlayer1, int winRecordPlayer2, int loseRecordPlayer2) throws IOException {
//    String data = new String(Files.readAllBytes(Paths.get("jankenRecords.txt")));
//    String[] parts = data.split(",");
//    winRecordPlayer1 = Integer.parseInt(parts[0]);
//    }
}

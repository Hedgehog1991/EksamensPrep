package main_project;

import bookRecords.AcousticB;
import bookRecords.NormalB;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookInserter extends Library {


    public static void BookMixer(){
        HashMap<String, NormalB> normalBookMap;
        HashMap<String, AcousticB> acousticBookMap;
        try{

            normalBookMap = new HashMap<>();
            acousticBookMap = new HashMap<>();

            NormalB book1 = new NormalB("bobob","Bob", 2005, "english",10,new Date(2010-1900, 10, 8));
            NormalB book2 = new NormalB("MEGGGG","meg", 2015, "french",15, new Date(2010-1900, 10, 8));
            NormalB book3 = new NormalB("TYYYLL","tyler", 2025, "english",160, new Date(2012-1900, 6, 19));
            NormalB book4 = new NormalB("PAPAPAP","pat", 1992, "english",112, new Date(2012 - 1900, 1, 2));
            NormalB book5 = new NormalB("The Republic","Plato", -375, "greek", 50, new Date(2022 - 1900, 1,10));

            AcousticB abook1 = new AcousticB(book1.getId(), book1.getAuthor(),  book1.getYear(), book1.getLanguage(),true);
            AcousticB abook2 = new AcousticB(book4.getId(), book4.getAuthor(), book4.getYear() ,"italian", true);
            AcousticB abook3 = new AcousticB("DODODO","dostojevsky", 1948,"russian", false);
            AcousticB abook4 = new AcousticB("TITLTE JENNY","jenny",2020 ,"english", true);

            //# Brief with some sexy lamda.
            final List<NormalB> bL = Arrays.asList(book1, book2, book3, book4, book5);

            bL.forEach(book ->
                    normalBookMap.put(book.getId(), book));

            final List<AcousticB> aL = Arrays.asList(abook1,abook2,abook3, abook4);
            aL.forEach(book ->
                    acousticBookMap.put(book.getId(), book));

            for(Map.Entry<String, NormalB> e : normalBookMap.entrySet()) {
                String k = e.getKey();
                NormalB v = e.getValue();
                nB.setString(1,k);
                nB.setString(2, v.getAuthor());
                nB.setInt(3, v.getYear());
                nB.setString(4, v.getLanguage());
                nB.setInt(5, v.getNumberOfHardCopies());
                nB.setDate(6, v.getLoanPeriod());

                nB.addBatch();
            }
            int[] bookInserted = nB.executeBatch();
            System.out.println("Inserted " + bookInserted.length + " rows");


            for(Map.Entry<String, AcousticB> e : acousticBookMap.entrySet()){
                String k = e.getKey();
                AcousticB v = e.getValue();

                aB.setString(1,k);
                aB.setString(2, v.getAuthor());
                aB.setString(3, v.getLanguage());
                aB.setBoolean(4, v.getFreeTrailPeriod());

                aB.addBatch();
            }
            int[] acousticInserted = aB.executeBatch();
            System.out.println("Inserted " + acousticInserted.length + " rows");
        }catch(SQLException e){e.printStackTrace();}
    }

}

package main_project;

import Universal_DB_Connector.DatabaseSetup;
import bookRecords.*;


import java.awt.print.Book;
import java.sql.*;

import java.util.*;

import static main_project.BookInserter.BookMixer;
import static main_project.PreStat.*;


public abstract class Library {

    //#1  server Connection
      static final  DatabaseSetup dbSet = new DatabaseSetup( "bookDb", "Tester", "tester12345");

//#2 Fields
    static Scanner scanner = new Scanner(System.in);
    protected static Statement statM;
    protected static ResultSet rS;
    private static ResultSetMetaData rSMeta;
    public static PreparedStatement pS;
    public static PreparedStatement nB;
    public static PreparedStatement aB;
    public static PreparedStatement displayNormal;
    public static PreparedStatement displayAcoustic;
    public static PreparedStatement displayUsers;
    public static PreparedStatement userLoanRecord;
    public static PreparedStatement subtractNumberOfBooks;
    public static PreparedStatement increaseNumberOfBooks;

    //DATABASE JAVA ORCHESTRA
      static{
        try{
            Connection conn = dbSet.getConnection();

            System.out.println("Connection successful!");
            statM = conn.createStatement(rS.TYPE_SCROLL_INSENSITIVE, rS.CONCUR_READ_ONLY);

            String[] tableSetup = {
                    createNormalBookTable,
                    createAcousticBookTable,
                    createLoanRecordTable
            };
            Arrays.stream(tableSetup).forEach(table -> {
                try {
                pS = dbSet.getConnection().prepareStatement(table);
                    pS.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
             nB = dbSet.getConnection().prepareStatement(bookInserts, Statement.RETURN_GENERATED_KEYS);
            aB = dbSet.getConnection().prepareStatement(audioBookInsert, Statement.RETURN_GENERATED_KEYS);
            userLoanRecord = dbSet.getConnection().prepareStatement(loanRecordInsert, Statement.RETURN_GENERATED_KEYS);
            System.out.println("tables implemented");


            displayNormal = dbSet.getConnection().prepareStatement(displayNormalBooks);
            displayAcoustic = dbSet.getConnection().prepareStatement(displayAcousticBooks);
            displayUsers = dbSet.getConnection().prepareStatement(displayLoanRecord);

            subtractNumberOfBooks = dbSet.getConnection().prepareStatement(subtractBooks);
            increaseNumberOfBooks = dbSet.getConnection().prepareStatement(increaseBooks);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    //DISPLAY CONTENT OF NORMALBOOK TABLE
    public static void displayNormalBook(){
          try{
              System.out.println(ANSI_GREEN+"          -----PHYSICAL BOOKS-----"+ANSI_RESET);
              rS = statM.executeQuery(displayNormalBooks);
                rSMeta = rS.getMetaData();
                printResultSet(rSMeta,rS, 1,2,3,4,5,6);
          }catch (SQLException e){e.printStackTrace();}
    }
    public static void displayAcousticBook(){
        try{
            System.out.println(ANSI_GREEN+"          -----AUDIO BOOKS-----"+ANSI_RESET);
            rS = statM.executeQuery(displayAcousticBooks);
            rSMeta = rS.getMetaData();
            printResultSet(rSMeta,rS, 1,2,3,4);
        }catch (SQLException e){e.printStackTrace();}
    }

    public static void displayUsers(){
        try{
            System.out.println(ANSI_GREEN+"          -----LOAN RECORD-----"+ANSI_RESET);
            rS = statM.executeQuery(displayLoanRecord);
            rSMeta = rS.getMetaData();
            printResultSet(rSMeta,rS, 1,2,3, 4);
        }catch (SQLException e){e.printStackTrace();}
    }



    public static void addOrRemoveUser() {
        scanner = new Scanner(System.in);
        try {
            System.out.println("write your username");
            String user = scanner.nextLine();
            System.out.println("write acoustic or normal");
            String bookType = scanner.nextLine();
            System.out.println("write the name of the book");
            String bookId = scanner.nextLine();

            userLoanRecord.setString(1, user);
            userLoanRecord.setString(2, bookType);
            userLoanRecord.setString(3, bookId);

            System.out.println("If you want to borrow a book enter borrow, return enter return");
            String choice = scanner.next();
            if(choice.equalsIgnoreCase("borrow")){
                subtractNumberOfBooks.execute(bookId);
                System.out.println("Book Borrowed successfully!");
            }else if(choice.equalsIgnoreCase("return")){
                increaseNumberOfBooks.execute(bookId);
                System.out.println("Book returned successfully!");
            }else {
                System.out.println("Error wrong choice!");
            }

            userLoanRecord.addBatch();


            System.out.println("batch inserted");
            userLoanRecord.executeBatch();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    // DISPLAY COLUMN NAMES AND TABLE CONTENT
    protected static void printResultSet(ResultSetMetaData rSMeta, ResultSet rS, int... columnIndex) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : columnIndex) {
            stringBuilder.append(rSMeta.getColumnLabel(i)).append(" |§| ");
        }
        System.out.println(ANSI_YELLOW+stringBuilder.toString().trim().toUpperCase()+ANSI_RESET);

        while (rS.next()){
            stringBuilder = new StringBuilder();
            for(int i : columnIndex){
                stringBuilder.append(rS.getString(i)).append(" | ");
            }

            System.out.println(ANSI_CYAN+stringBuilder.toString().trim()+ANSI_RESET);

        }
        rS.last();
    }


    public static void main(String[] args) {
         // BookMixer();
         displayAcousticBook();
         displayNormalBook();
        displayUsers();
        //RESULTSET IF ELSE FOR BOOK.
        addOrRemoveUser();
    }



    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
}

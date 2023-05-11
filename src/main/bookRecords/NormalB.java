package bookRecords;

import java.sql.Date;

public class NormalB extends Books {

        int numberOfHardCopies;
        Date loanPeriod;


        public NormalB(String title, String author, int year, String language, int numberOfHardCopies, Date loanPeriod) {
            super(title, author, year,language );
            this.numberOfHardCopies = numberOfHardCopies;
            this.loanPeriod = new Date(loanPeriod.getTime());
        }

    public int getNumberOfHardCopies() {
        return numberOfHardCopies;
    }

    public void setLoanPeriod(Date loanPeriod) {
        this.loanPeriod = new Date(loanPeriod.getTime());
    }
    public Date getLoanPeriod() {
        return new Date(loanPeriod.getTime());
    }
}



package bookRecords;

public class AcousticB extends Books {


        boolean freeTrailPeriod;

        public AcousticB(String title, String author, int year, String language, boolean freeTrailPeriod) {
            super(title, author, year, language);
            this.freeTrailPeriod = freeTrailPeriod;
        }

    public boolean getFreeTrailPeriod() {
        return freeTrailPeriod;
    }
}


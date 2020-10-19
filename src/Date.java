// --== CS400 File Header Information ==--
// Name: Letong Dai
// Email: ldai29@wisc.edu
// Team: ED
// Role: Data Wrangler
// TA: Keren Chen
// Lecturer: Florian Heimerl

public class Date implements Comparable<Date>{
    private int day;
    private int month;
    private int year;

    public Date() {
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /*
     * This constructor receive a String which contains all date information
     * 
     * @param date - format: month / day / year
     */
    public Date(String date) {
        String[] info = date.split(" / ");
        this.day = Integer.valueOf(info[1]);
        this.month = Integer.valueOf(info[0]);
        this.year = Integer.valueOf(info[2]);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /*
     * @return format: month / day / year
     */
    @Override
    public String toString() {
        return month + " / " + day + " / " + year;
    }

    @Override
    public int compareTo(Date arg0) {
        int c = ((Integer)this.year).compareTo((Integer) arg0.year);
        if (c == 0) {
            c = ((Integer)this.month).compareTo((Integer) arg0.month);
            if (c == 0) {
                c = ((Integer)this.day).compareTo((Integer) arg0.day);
                if (c == 0) {
                    return 0;
                } else {
                    return c;
                }
            } else {
                return c;
            }
        } else {
            return c;
        }
    }
}
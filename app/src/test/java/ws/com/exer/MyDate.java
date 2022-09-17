package ws.com.exer;

public class MyDate implements Comparable<MyDate> {
    int year;
    int month;
    int day;

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public int compareTo(MyDate myDate) {
        int minusYear = this.getYear() - myDate.getYear();
        if (minusYear != 0) {
            return minusYear;
        }
        int minusMonth = this.getMonth() - myDate.getMonth();
        if (minusMonth != 0) {
            return minusMonth;
        }
        return this.getDay()-myDate.getDay();
    }
}

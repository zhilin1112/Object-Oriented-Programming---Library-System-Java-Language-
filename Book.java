package holoLib;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Book implements Penalty {
    /********** Properties **********/
    private String bookTitle;
    private String bookID;
    private String bookAuthor;
    private String bookPublisher;
    private GregorianCalendar bookPublicationDate;
    private double bookPrice;
    private boolean isBorrowed;
    private double borrowFees;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private static int totalBooks = 0;
    private static final double PENALTY_RATE = 1.0;
    private static final int MAX_GRACE_PERIOD_IN_DAY = 30;

    /********** Constructors **********/
    public Book() {
        this("", "", "", null, 0.0);
        isBorrowed = false;
        calBorrowFees();
        totalBooks++;
    }

    public Book(String bookTitle, String bookAuthor, String bookPublisher, GregorianCalendar bookPublicationDate,
            double bookPrice) {
        this.bookTitle = bookTitle;
        bookID = String.format("BK%03d", totalBooks + 1);
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookPublicationDate = bookPublicationDate;
        this.bookPrice = bookPrice;
        isBorrowed = false;
        calBorrowFees();
        totalBooks++;
    }

    /********** Accessors & Mutators **********/
    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookID() {
        return bookID;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public double getBorrowFees() {
        return borrowFees;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getMaxGracePeriodInDay() {
        return MAX_GRACE_PERIOD_IN_DAY;
    }

    /********** Methods **********/
    public void displayBookDetails() {
        System.out.println("================================================");
        System.out.println("                  Book Details                  ");
        System.out.println("================================================");
        System.out.println("Book Title            : " + bookTitle);
        System.out.println("Book ID               : " + bookID);
        System.out.println("Book Author           : " + bookAuthor);
        System.out.println("Book Publisher        : " + bookPublisher);
        System.out.println("Book Publication Date : " + publisherDateToString());
        System.out.printf("Book Price            : %.2f\n", bookPrice);
        System.out.println("Book Borrow Status    : " + isBorrowed);
        System.out.printf("Borrow Fee            : %.2f\n", borrowFees);
        System.out.println("================================================");
    }

    public String publisherDateToString() {
        return String.format("%02d", bookPublicationDate.get(Calendar.DATE)) + "/"
                + String.format("%02d", bookPublicationDate.get(Calendar.MONTH)) + "/"
                + bookPublicationDate.get(Calendar.YEAR);
    }

    public void calBorrowFees() {
        borrowFees = bookPrice * 0.1;
    }

    @Override
    public double calPenalty(int dayBorrowed) {
        return PENALTY_RATE * (dayBorrowed - MAX_GRACE_PERIOD_IN_DAY);
    }
}

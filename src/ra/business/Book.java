package ra.business;

import java.util.Scanner;

public class Book {
    //    Fields
    private static int nextId = 0;
    private int bookId; // tự động tăng
    private String bookName; // không trống
    private String author; // không trống
    private String description; // không trống, > 10 ký tự
    private double importPrice; // > 0
    private double exportPrice; // > 1.2 * importPrice
    private float interest; // => TODO: ép kiểu về float => Float.parseFloat kết quả exportPrice - importPrice
    private boolean bookStatus = true; // giá trị mặc định là true


//    Constructors
    //intialize bookId = 0;

    public Book() {
//        bookId tự động tăng
        this.bookId = nextId;
        nextId++;
    }

    public Book(String bookName, String author, String description, double importPrice, double exportPrice, boolean bookStatus) {
//        bookId tự động tăng
        this.bookId = nextId;
        nextId++;
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.bookStatus = bookStatus;
    }

// Methods
//    Getter/Setter

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    //    Methods
    public void inputData(Scanner sc, Book[] arrBook, int currentIndex) {
        boolean isExit = true;
//        bookId
        this.bookId = currentIndex;
//        bookName
        System.out.println("Nhập vào tên sách:");
        do {
            this.bookName = sc.nextLine();
//            Kiểm tra không trống
            if (!this.bookName.trim().isEmpty()) {
                break;
            } else {
                System.err.println("Tên sách không được để trống!!!");
            }
        } while (isExit);
//        author
        System.out.println("Nhập vào tên tác giả:");
        do {
            this.author = sc.nextLine();
//            Kiểm tra không trống
            if (!this.author.trim().isEmpty()) {
                break;
            } else {
                System.err.println("Tên tác giả không được để trống!!!");
            }
        } while (isExit);
//        descriptions
        System.out.println("Nhập vào mô tả sách:");
        do {
            this.description = sc.nextLine();
//            Kiểm tra không trống
            if (!this.description.trim().isEmpty()) {
//                Kiểm tra > 10 ký tự
                if(this.description.trim().length() <= 10){
                    System.err.println("Phần mô tả sách không được ngắn hơn 10 ký tự");
                } else {
                    break;
                }
            } else {
                System.err.println("Phần mô tả sách không được để trống!!!");
            }
        } while (isExit);
//        importPrice
        System.out.println("Nhập vào giá nhập:");
        do {
            this.importPrice = Double.parseDouble(sc.nextLine());
//            Kiểm tra > 0
            if (this.importPrice > 0){
                break;
            } else {
                System.err.println("Giá nhập phải lớn hơn 0");
            }
        } while (isExit);
//        exportPrice
        System.out.println("Nhập vào giá xuất:");
        do {
            this.exportPrice = Double.parseDouble(sc.nextLine());
//            Kiểm tra > 1.2 x importPrice
            if (this.exportPrice > this.importPrice * 1.2){
                break;
            } else {
                System.err.println("Giá xuất phải lớn hơn 1.2 lần giá nhập");
            }
        } while (isExit);
//        bookStatus
        System.out.println("Nhập vào trạng thái:");
        do {
            String inputBookStatus = sc.nextLine();
            if(inputBookStatus.trim().equals("true") || inputBookStatus.trim().equals("false")){
                this.bookStatus = Boolean.parseBoolean(inputBookStatus.trim());
                break;
            } else {
                System.err.println("Vui lòng nhập vào 'true' hoặc 'false'");
            }
        } while (isExit);

    }

    public void displayData() {
        System.out.printf("Mã sách: %-3d  Tên sách: %-10s  Tác giả: %-10s\n", this.bookId, this.bookName, this.author);
        System.out.printf("Mô tả: %-50s\n", this.description);
        System.out.printf("Giá nhập: %-10.2f Giá xuất: %-10.2f Tình trạng: %b\n", this.importPrice, this.exportPrice, this.bookStatus);

    }
    public void calInterest(){
//        tính toán và ép kiểu sang float
        this.interest = (float) (this.exportPrice - this.importPrice);
    }
}



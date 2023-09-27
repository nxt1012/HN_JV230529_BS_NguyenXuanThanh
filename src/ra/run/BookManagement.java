package ra.run;

import ra.business.Book;

import java.util.Scanner;

public class BookManagement {
    public static Book[] arrBook = new Book[100];
    public static int currentIndex = 0;

    public static void main(String[] args) {

        BookManagement bookManagement = new BookManagement();

        Scanner sc = new Scanner(System.in);
        int choice, numberOfBook, bookId, deleteIndex, changeIndex;
        String searchTerm;

//        MENU
        do {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
            System.out.println("1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách");
            System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện");
            System.out.println("3. Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối theo tên sách hoặc mô tả");
            System.out.println("6. Thay đổi thông tin theo mã sách");
            System.out.println("7. Thoát");
            System.out.print("Nhập vào lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
//                    Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách
                    System.out.println("Vui lòng nhập vào số lượng sách muốn thêm mới:");
                    numberOfBook = Integer.parseInt(sc.nextLine());
//                    Nếu số lượng sách < số vị trí còn trống trong mảng thì mới cho phép nhập
                    if (numberOfBook < arrBook.length - currentIndex) {
                        for (int i = 0; i < numberOfBook; i++) {
//                            Nhập thông tin cho từng cuốn sách
                            bookManagement.addBook(sc, currentIndex);
                            currentIndex++;
                        }
                    } else {
                        System.err.println("Số vị trí còn trống không đủ.");

                    }
                    break;
                case 2:
//                    Hiển thị thông tin tất cả sách trong thư viện
                    for (int i = 0; i < currentIndex; i++) {
                        arrBook[i].displayData();
                    }
                    break;
                case 3:
//                    Sắp xếp sách theo lợi nhuận tăng dần
//                    Tính toán lợi nhuận cho tất cả các đầu sách
                    for (int i = 0; i < currentIndex; i++) {
                        arrBook[i].calInterest();
                    }
//                    Sắp xếp lại sách theo lợi nhuận tăng dần
                    for (int i = 0; i < currentIndex - 1; i++) {
                        for (int j = i + 1; j < currentIndex; j++) {
                            if (arrBook[i].getInterest() > arrBook[j].getInterest()) {
//                                đảo sách có lợi nhuận lớn hơn ra sau
                                Book temp = arrBook[i];
                                arrBook[i] = arrBook[j];
                                arrBook[j] = arrBook[i];
                            }
                        }
                    }
//                    Hiển thị kết quả để check
                    //                    TODO: xóa sau
                    for (int i = 0; i < currentIndex; i++) {
                        arrBook[i].displayData();
                        System.out.println("Lợi nhuận: "+arrBook[i].getInterest());
                    }
                    break;
                case 4:
//                    Xóa sách theo mã sách
                    System.out.println("Vui lòng nhập vào mã sách muốn xóa:");
                    bookId = Integer.parseInt(sc.nextLine());
//                    Lấy vị trí sách cần xóa
                    deleteIndex = bookManagement.getBookIndexById(bookId, arrBook, currentIndex);
//                    Xóa sách dựa vào vị trí
                    if (deleteIndex != -1) {
                        bookManagement.deleteBookByIndex(deleteIndex, arrBook, currentIndex);
                        currentIndex--;
                    } else {
                        System.err.println("Mã sách không tồn tại!!!");
                    }
                    break;
                case 5:
//                    Tìm kiếm tương đối theo tên sách hoặc mô tả
                    System.out.println("Vui lòng nhập vào tên sách hoặc mô tả sách muốn tìm kiếm:");
                    searchTerm = sc.nextLine();
                    bookManagement.searchBookByBookNameOrDescriptions(searchTerm, arrBook, currentIndex);
                    break;
                case 6:
//                    Thay đổi thông tin theo mã sách
                    System.out.println("Vui lòng nhập vào mã sách muốn thay đổi thông tin:");
                    bookId = Integer.parseInt(sc.nextLine());
//                    Lấy vị trí sách cần thay đổi thông tin
                    changeIndex = bookManagement.getBookIndexById(bookId, arrBook, currentIndex);
                    bookManagement.changeBookDataByIndex(sc, changeIndex, arrBook, currentIndex);
                    break;
                case 7:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng nhập vào lựa chọn là số nguyên từ 1-7");
            }
        } while (true);

    }

    public void addBook(Scanner sc, int currentIndex) {
        arrBook[currentIndex] = new Book();
        arrBook[currentIndex].inputData(sc, arrBook, currentIndex);
    }

    public int getBookIndexById(int bookId, Book[] arrBook, int currentIndex) {
        int bookIndex = -1;
        for (int i = 0; i < currentIndex; i++) {
            if (arrBook[i].getBookId() == bookId) {
                bookIndex = i;
            }
        }
        return bookIndex;
    }

    public void deleteBookByIndex(int deleteIndex, Book[] arrBook, int currentIndex) {
        for (int i = deleteIndex; i < currentIndex - 1; i++) {
            arrBook[i] = arrBook[i + 1];
        }
        arrBook[currentIndex - 1] = null;
    }

    public void searchBookByBookNameOrDescriptions(String searchTerm, Book[] arrBook, int currentIndex) {
        System.out.println();
        int searchResultsCount = 0;
        for (int i = 0; i < currentIndex; i++) {
            if (arrBook[i].getBookName().toLowerCase().contains(searchTerm.trim().toLowerCase()) || arrBook[i].getDescription().toLowerCase().contains(searchTerm.trim().toLowerCase())) {
                arrBook[i].displayData();
                searchResultsCount++;
            }
        }
        if (searchResultsCount == 0) {
            System.err.println("Không tìm thấy sách phù hợp!!!");
        }
    }

    public void changeBookDataByIndex(Scanner sc, int changeIndex, Book[] arrBook, int currentIndex) {

        boolean isExit = true;
//        bookName
        System.out.println("Nhập vào tên sách mới (Enter để bỏ qua):");
        do {
            String newBookName = sc.nextLine();
            if (newBookName.trim().isEmpty()) {
                break;
            } else {
                arrBook[changeIndex].setBookName(newBookName);
            }
        } while (isExit);
//        author
        System.out.println("Nhập vào tên tác giả mới (Enter để bỏ qua):");
        do {
            String newAuthor = sc.nextLine();
            if (newAuthor.trim().isEmpty()) {
                break;
            } else {
                arrBook[changeIndex].setAuthor(newAuthor);
            }
        } while (isExit);
//        descriptions
        System.out.println("Nhập vào mô tả sách mới (Enter để bỏ qua):");
        do {
            String newDescription = sc.nextLine();
            if (newDescription.trim().isEmpty()) {
                break;
            } else {
//                Kiểm tra > 10 ký tự
                if(newDescription.trim().length() <= 10){
                    System.err.println("Phần mô tả sách không được ngắn hơn 10 ký tự");
                } else {
                    arrBook[changeIndex].setDescription(newDescription);
                    break;
                }
            }
        } while (isExit);
//        importPrice
        System.out.println("Nhập vào giá nhập mới (Enter để bỏ qua):");
        do {
            String newImportPrice = sc.nextLine();
            if (newImportPrice.trim().isEmpty()) {
                break;
            } else {
//                Kiểm tra > 0
                if (Double.parseDouble(newImportPrice) > 0){
                    arrBook[changeIndex].setImportPrice(Double.parseDouble(newImportPrice));
                    break;
                } else {
                    System.err.println("Giá nhập phải lớn hơn 0");
                }
            }
        } while (isExit);
//        exportPrice
        System.out.println("Nhập vào giá xuất mới (Enter để bỏ qua):");
        do {
            String newExportPrice = sc.nextLine();
            if (newExportPrice.trim().isEmpty()) {
                break;
            } else {
//                Kiểm tra > 1.2 x importPrice
                if (Double.parseDouble(newExportPrice) > 1.2 * arrBook[changeIndex].getImportPrice()){
                    arrBook[changeIndex].setImportPrice(Double.parseDouble(newExportPrice));
                    break;
                } else {
                    System.err.println("Giá xuất phải lớn hơn 1.2 lần giá nhập");
                }
            }
        } while (isExit);
//        bookStatus
        System.out.println("Nhập vào trạng thái mới (Enter để bỏ qua):");
        do {
            String newBookStatus = sc.nextLine();
            if (newBookStatus.trim().isEmpty()) {
                break;
            } else {
                if(newBookStatus.trim().equals("true") || newBookStatus.trim().equals("false")){
                    arrBook[changeIndex].setBookStatus(Boolean.parseBoolean(newBookStatus));
                    break;
                } else {
                    System.err.println("Vui lòng nhập vào 'true' hoặc 'false'");
                }
            }
        } while (isExit);

    }
}
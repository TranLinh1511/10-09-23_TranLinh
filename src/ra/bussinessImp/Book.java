package ra.bussinessImp;

import jdk.internal.util.xml.impl.Input;
import ra.bussiness.IBook;
import ra.config.InputMethods;

import static ra.run.BookManagement.books;


public class Book implements IBook {
    /* Các thuộc tính:
▪ bookId – mã sách – int
▪ bookName – tên sách – String
▪ title – tiêu đề sách – String
▪ numberOfPages – Số trang sách
▪ importPrice – giá nhập sách – float
▪ exportPrice – giá bán sách – float
▪ interest – lợi nhuận – float
▪ bookStatus – trạng thái – Boolean
➢ Các constructor
➢ Các phương thức:
▪ Các phương thức getter/setter
▪ Triển khai phương thức inputData():
• Nhập thông tin sách từ bàn phím (trừ interest)
• Thực hiện tính interest = exportPrice – importPrice
▪ Triển khai phương thức displayData() cho phép hiển thị tất cả thông tin sách*/
    private int bookId, numberOfPages;
    private String bookName, title;
    private float importPrice, exportPrice, interest;
    private boolean bookStatus;

    // get/set
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }
    // contructor

    public Book(int bookId, int numberOfPages, String bookName, String title, float importPrice, float exportPrice, float interest, boolean bookStatus) {
        this.bookId = bookId;
        this.numberOfPages = numberOfPages;
        this.bookName = bookName;
        this.title = title;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    public Book() {
    }

    // methods
    @Override
    public void inputData() {
        System.out.println("<---------------------------------------------------->");
        boolean isExit = true;
        // id
        do {
            System.out.print("Nhập id sách: ");
            int id = InputMethods.getInteger();
            Boolean isExist = false;
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).bookId == id) {
                    isExist = true;
                }
            }
            if (isExist) {
                System.out.println("Id đã tồn tại! Vui lòng nhập lại...!!");
            } else {
                this.bookId = id;
                break;
            }
        } while (isExit);
        // name
        do {
            System.out.print("Nhập tên sách: ");
            this.bookName = InputMethods.getString();
            break;
        } while (isExit);
        // title
        do {
            System.out.print("Nhập tiêu đề sách: ");
            String title = InputMethods.getString();
            if (title.length() <= 10) {
                System.out.println("Tiêu đề không được dưới 10 kí tự! Vui lòng nhập lại...");
            } else {
                this.title = title;
                break;
            }
        } while (isExit);
        // number of pages
        do {
            System.out.print("Nhập số trang sách: ");
            this.numberOfPages = InputMethods.checkNum();
            break;
        } while (isExit);
        // importPrice
        do {
            System.out.print("Nhập đầu vào: ");
            this.importPrice = InputMethods.checkNum();
            break;
        } while (isExit);
        // exportPrice
        do {
            System.out.print("Nhập đầu ra: ");
            float exportPrice1 = InputMethods.checkNum();
            if (exportPrice1 < this.importPrice * (0.2)) {
                System.out.println("Đầu ra không được nhỏ hơn 20% đầu vào!! Vui lòng nhập lại...");
            } else {
                this.exportPrice = exportPrice1;
                break;
            }
        } while (isExit);
        this.interest = this.exportPrice - this.importPrice;
        // statusBook
        do {
            System.out.print("Nhập trạng thái sách(true or false): ");
            String status = InputMethods.getString();
            if (status.equals("true") || status.equals("false")) {
                this.bookStatus = Boolean.parseBoolean(status);
                break;
            } else {
                System.out.println("Yêu cầu nhập đúng trường! Vui lòng nhập lại...");
            }
        } while (isExit);
        System.out.println("<---------------------------------------------------->");

    }

    @Override
    public void displayData() {
        System.out.println("<-----------------" + bookName.toUpperCase() + "----------------->");
        System.out.println("Id sách: " + bookId);
        System.out.println("Tên sách: " + bookName);
        System.out.println("Tên tiêu đề: " + title);
        System.out.println("Số trang sách: " + numberOfPages);
        System.out.println("Giá nhập sách: " + importPrice);
        System.out.println("Giá xuất sách: " + exportPrice);
        System.out.println("Lợi nhuận: " + interest);
        System.out.println("Trạng thái: " + (bookStatus ? "Đang được bán" : "Lưu trữ"));
        System.out.println("<------------------------------------------------>");

    }
}

package ra.run;

import jdk.internal.util.xml.impl.Input;
import ra.bussinessImp.Book;
import ra.config.InputMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static List<Book> books = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("<-----------------MENU----------------->");
            System.out.println("1. Nhập số sách và nhập thông tin sách\n" +
                    "2. Hiển thị thông tin các sách\n" +
                    "3. Sắp xếp sách theo lợi nhuận giảm dần\n" +
                    "4. Xóa sách theo mã sách\n" +
                    "5. Tìm kiếm sách theo tên sách\n" +
                    "6. Thay đổi trạng thái của sách theo mã sách\n" +
                    "7. Thoát");
            System.out.print("Nhập chức năng: ");
            int choose = InputMethods.getByte();
            switch (choose) {
                case 1:
                    add();
                    break;
                case 2:
                    show();
                    break;
                case 3:
                    sortByInteres();
                    break;
                case 4:
                    deleteById();
                    break;
                case 5:
                    searchByName();
                    break;
                case 6:
                    updateStatusById();
                    break;
                case 7:
                    boolean confirm = confirm("Bạn có chắc muốn thoát chương trình(y/n): ");
                    if (confirm) {
                        System.out.println("Đã thoát chương trình!!");
                        return;
                    } else {
                        break;
                    }

                default:
                    System.out.println("Yêu cầu nhập đúng trường!!");
                    break;
            }


        } while (true);
    }

    private static boolean confirm(String note) {
        do {
            System.out.print(note);
            String confirm = InputMethods.getString().toLowerCase();
            if (confirm.equals("y")) {
                return true;
            } else if (confirm.equals("n")) {
                return false;
            } else {
                System.out.println("Yêu cầu nhập đúng trường y/n!!");
            }
        } while (true);

    }

    private static int getIndexById(String note) {
        System.out.print(note);
        int id = InputMethods.getInteger();
        int index = -1;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId() == id) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("Id không tồn tại!!");
        }
        return index;
    }

    private static void insertionSort(List<Book> arr) {
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            double key = arr.get(i).getInterest();
            int j = i - 1;
            while (j >= 0 && arr.get(j).getInterest() > key) {
                swap(arr, j, j + 1);
                j = j - 1;
            }
        }
    }

    private static void swap(List<Book> arr, int i, int j) {

        Book temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    // main methods
    private static void updateStatusById() {
        if (books.size() == 0){
            System.out.println("Không có sách trong thư viện, yêu cập nhập thêm!!");
            add();
        } else {
            int index = getIndexById("Nhập id muốn cập nhập trạng thái: ");
            if (index != -1) {
                books.get(index).setBookStatus(!books.get(index).isBookStatus());
                System.out.println("Sửa đổi trạng thái thành công!!");
                books.get(index).displayData();
            }
        }
    }

    private static void sortByInteres() {
        if (books.size() == 0) {
            System.out.println("Không có sách trong thư viện, yêu cập nhập thêm!!");
            add();
        } else {
            insertionSort(books);
            System.out.println("Sau đây là danh sách sau khi đã sắp xếp theo lợi nhuận tăng dần: ");
            for (Book book : books) {
                System.out.println(book.getBookName() + " - lợi nhuận: " + book.getInterest());
            }
        }
    }

    private static void searchByName() {
        if (books.size() == 0) {
            System.out.println("Không có sách trong thư viện, yêu cập nhập thêm!!");
            add();
        } else {
            System.out.print("Nhập tên sách muốn tìm kiếm: ");
            String nameBook = InputMethods.getString();
            int index = -1;
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getBookName().equals(nameBook)) {
                    index = i;
                }
            }
            if (index != -1) {
                books.get(index).displayData();
            } else {
                System.out.println("Không có trong danh sách!!");
            }
        }
    }

    private static void deleteById() {
        if (books.size() == 0) {
            System.out.println("Không có sách trong thư viện, yêu cập nhập thêm!!");
            add();
        } else {
            do {
                int index = getIndexById("Nhập id sách muốn xóa: ");
                if (index != -1) {
                    boolean confirm = confirm("Bạn có chắc muốn xóa sách " + books.get(index).getBookName() + " không(y/n): ");
                    if (confirm) {
                        books.remove(index);
                        System.out.println("Xóa thành công!!");
                        break;
                    } else {
                        break;
                    }
                }
            } while (true);
        }
    }

    private static void show() {
        if (books.size() == 0) {
            System.out.println("Không có sách trong thư viện, yêu cập nhập thêm!!");
            add();
        } else {
            for (Book book : books) {
                book.displayData();
            }
        }
    }

    private static void add() {
        System.out.print("Nhập số lượng sách muốn nhập: ");
        int nImport = InputMethods.getInteger();
        for (int i = 0; i < nImport; i++) {
            System.out.println("NHẬP THÔNG TIN SÁCH THỨ " + (i + 1));
            Book book = new Book();
            book.inputData();
            books.add(book);
        }
        System.out.println("Nhập thành công " + nImport + " sách!");
    }
}

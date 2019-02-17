package screens.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

class MainPresenter {

    private MainView mainView;

    MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    void initCategories() throws FileNotFoundException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("assets/categories.csv")).getFile());
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(";");
        while(scanner.hasNext()){
            System.out.print(scanner.next());
        }
        scanner.close();

    }
}

package pl.jasiek.project.config;

import pl.jasiek.project.model.Products.Groups;
import pl.jasiek.project.repository.csv.ProductCsvRepo;

import java.util.List;
import java.util.stream.Collectors;

public class MenuToBuy extends KfcMenu {
    @Override
    public void displayMenuKfc(ProductCsvRepo repo) {
        System.out.println("KFC MENU:");
        List<String> kfcMenuList;
        for (int i = 0; i < Groups.values().length; i++) {
            int finalI = i;
            kfcMenuList = repo.findAll().stream()
                    .filter(p -> p.getGroup() == Groups.values()[finalI])
                    .map(p -> "\t\t[" + p.getId() + "] " + p.getName() + "  ")
                    .collect(Collectors.toList());
            System.out.println("\n\t" + Groups.values()[finalI] + ":\n");
            kfcMenuList.forEach(System.out::print);
            System.out.println();
        }
    }
}

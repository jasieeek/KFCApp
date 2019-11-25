package pl.jasiek.project.config;

import pl.jasiek.project.model.Products.*;
import pl.jasiek.project.repository.csv.ProductCsvRepo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuWithDetails extends KfcMenu{
    @Override
    public void displayMenuKfc(ProductCsvRepo repo) {
        System.out.println("KFC MENU WITH DETAILS:");
        List<String> kfcMenuList;
        for (int i = 0; i < Groups.values().length; i++) {
            Groups tmpGroup = Groups.values()[i];
            switch (tmpGroup) {
                case SIMPLE_PRODUCT:
                    kfcMenuList = repo.findAllByGroup(tmpGroup).stream()
                            .map(p -> "\t\t" + p.getName() + "\n\t\t\tPrice: " + p.getPrice() + "\n")
                            .collect(Collectors.toList());
                    break;
                case BOX:
                    kfcMenuList = repo.findAllByGroup(tmpGroup).stream()
                            .map(p -> (Box) p)
                            .map(box -> "\t\t" + box.getName() + "\n\t\t\tContains: " + box.getContains() + "\tAdditions: " + box.getAdditions() + "\tPrice: " + box.getPrice() + "\n")
                            .collect(Collectors.toList());
                    break;
                case BUCKET:
                    kfcMenuList = repo.findAllByGroup(tmpGroup).stream()
                            .map(p -> (Bucket) p)
                            .map(bucket -> "\t\t" + bucket.getName() + "\n\t\t\tContains: " + bucket.getContains() + "\tAdditions: " + bucket.getAdditions() + "\tPrice: " + bucket.getPrice() + "\n")
                            .collect(Collectors.toList());
                    break;
                case SALAD:
                    kfcMenuList = repo.findAllByGroup(tmpGroup).stream()
                            .map(p -> (Salad) p)
                            .map(salad -> "\t\t" + salad.getName() + "\n\t\t\tChicken: " + salad.getChicken() + "\tVegetables: " + salad.getVegetables() + "\tPrice: " + salad.getSauce() + " " + salad.getPrice() + "\n")
                            .collect(Collectors.toList());
                    break;
                case SHAKE:
                    kfcMenuList = repo.findAllByGroup(tmpGroup).stream()
                            .map(p -> (Shake) p)
                            .map(shake -> "\t\t" + shake.getName() + "\n\t\t\tCapacity: " + shake.getCapacity() + "\tPrice: " + shake.getPrice() + "\n")
                            .collect(Collectors.toList());
                    break;
                default:
                    kfcMenuList = new LinkedList<>();
            }
            System.out.println("\t" + Groups.values()[i] + ":\n");
            kfcMenuList.forEach(System.out::print);
            System.out.println();
        }
    }
}

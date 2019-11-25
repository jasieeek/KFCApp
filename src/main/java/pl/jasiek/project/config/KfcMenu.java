package pl.jasiek.project.config;

import pl.jasiek.project.model.Products.Box;
import pl.jasiek.project.model.Products.Groups;
import pl.jasiek.project.model.Products.Product;
import pl.jasiek.project.model.Products.Shake;
import pl.jasiek.project.repository.csv.ProductCsvRepo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class KfcMenu {

    public abstract void displayMenuKfc(ProductCsvRepo repo);
}

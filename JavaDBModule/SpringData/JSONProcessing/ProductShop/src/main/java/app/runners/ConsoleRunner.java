package app.runners;

import app.entites.Category;
import app.entites.Product;
import app.entites.User;
import app.entites.dtos.CategoryDto;
import app.entites.dtos.ProductDto;
import app.entites.dtos.UserDto;
import app.entites.mappers.JsonMapper;
import app.entites.mappers.ObjectMapper;
import app.services.CategoryService;
import app.services.ProductService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {


    private UserService userService;

    private ProductService productService;

    private CategoryService categoryService;


    @Autowired
    public ConsoleRunner(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {


        FileReader usersReader = new
                FileReader(ClassLoader.getSystemClassLoader().getResource("users.json").getFile());
        FileReader productsReader = new
                FileReader(ClassLoader.getSystemClassLoader().getResource("products.json").getFile());
        FileReader categoriesReader = new
                FileReader(ClassLoader.getSystemClassLoader().getResource("categories.json").getFile());

        UserDto[] userDtos = (UserDto[]) JsonMapper.
                mapFromJsonArray(usersReader, UserDto[].class);

        ProductDto[] productDtos = (ProductDto[]) JsonMapper.
                mapFromJsonArray(productsReader, ProductDto[].class);

        CategoryDto[] categoryDtos = (CategoryDto[]) JsonMapper.
                mapFromJsonArray(categoriesReader, CategoryDto[].class);

        System.out.println(userDtos[0]);
        System.out.println(productDtos[0]);
        System.out.println(categoryDtos[0]);

        User[] users;
        users = ObjectMapper.getInstance().map(userDtos, User[].class);

        Product[] products;
        products = ObjectMapper.getInstance().map(productDtos, Product[].class);

        Category[] categories;
        categories = ObjectMapper.getInstance().map(categoryDtos, Category[].class);

        seedData(users, products, categories);
    }

    private void seedData(User[] users, Product[] products, Category[] categories) {
        Random random = new Random();
        for (int i = 0; i < users.length; i++) {

            int nextProductRandIndex = random.nextInt(products.length-1);

            Product product = products[nextProductRandIndex];

            User user = users[i];

            user.setProducts(new HashSet(){{add(product);}});
            product.setUsers(new HashSet(){{add(user);}});

            int nextCategoryRandIndex = random.nextInt(categories.length-1);
            Category category = categories[nextCategoryRandIndex];

            product.setCategories(new HashSet(){{add(category);}});
            category.setProducts(new HashSet(){{add(product);}});
        }

//        categoryService.saveAll(categories);
//
//        productService.saveAll(products);

        userService.saveAll(users);
    }
}

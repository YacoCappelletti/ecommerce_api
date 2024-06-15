package com.ecommerce.ecommerce_api.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseSeeder {
    /* 

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository,
                                   ProductRepository productRepository,
                                   SaleRepository saleRepository,
                                   BrandRepository brandRepository) {
        return args -> {
            Faker faker = new Faker(new Locale("en-US"));
            Random random = new Random();

            // Define categories and related products
            Map<String, List<String>> categoryProducts = new HashMap<>();
            categoryProducts.put("Pants", Arrays.asList("Blue long pants", "Red short pants", "Blue jeans", "Black trousers", "Cargo pants", "Sweatpants", "Chinos", "Dress pants", "Joggers", "Yoga pants", "Capri pants", "Khaki pants", "Plaid pants", "Cropped pants", "Slim fit jeans", "Flare jeans", "Skinny jeans", "Straight leg jeans", "Wide leg pants", "Corduroy pants"));
            categoryProducts.put("Shoes", Arrays.asList("Running shoes", "Leather boots", "Sandals", "High heels", "Sneakers", "Loafers", "Flip flops", "Wedges", "Espadrilles", "Ankle boots", "Combat boots", "Ballet flats", "Oxford shoes", "Moccasins", "Slip-ons", "Boat shoes", "Hiking boots", "Platform shoes", "Slippers", "Dress shoes"));
            categoryProducts.put("Shirts", Arrays.asList("White t-shirt", "Black polo", "Blue denim shirt", "Red flannel shirt", "Striped shirt", "Checked shirt", "Plain t-shirt", "Graphic tee", "Hawaiian shirt", "Henley shirt", "Rugby shirt", "V-neck t-shirt", "Crew neck t-shirt", "Button-down shirt", "Dress shirt", "Long sleeve shirt", "Short sleeve shirt", "Tank top", "Sleeveless shirt", "Peasant top"));
            categoryProducts.put("Hats", Arrays.asList("Baseball cap", "Beanie", "Fedora", "Sun hat", "Cowboy hat", "Panama hat", "Bucket hat", "Flat cap", "Trilby", "Beret", "Newsboy cap", "Snapback", "Visor", "Boater hat", "Cloche hat", "Straw hat", "Top hat", "Bowler hat", "Pork pie hat", "Golf cap"));
            categoryProducts.put("Jackets", Arrays.asList("Leather jacket", "Denim jacket", "Winter coat", "Windbreaker", "Bomber jacket", "Parka", "Blazer", "Peacoat", "Trench coat", "Raincoat", "Hooded jacket", "Quilted jacket", "Puffer jacket", "Field jacket", "Down jacket", "Fleece jacket", "Motorcycle jacket", "Varsity jacket", "Track jacket", "Safari jacket"));
            categoryProducts.put("Accessories", Arrays.asList("Sunglasses", "Watch", "Belt", "Scarf", "Necklace", "Bracelet", "Ring", "Earrings", "Handbag", "Wallet", "Tie", "Bow tie", "Pocket square", "Gloves", "Hat", "Beanie", "Hairband", "Brooch", "Cufflinks", "Keychain"));
            categoryProducts.put("Dresses", Arrays.asList("Evening gown", "Summer dress", "Cocktail dress", "Maxi dress", "Mini dress", "Midi dress", "Wrap dress", "Bodycon dress", "Shift dress", "A-line dress", "Sheath dress", "T-shirt dress", "Shirt dress", "Off-shoulder dress", "One-shoulder dress", "Empire waist dress", "Peplum dress", "High-low dress", "Sundress", "Slip dress"));
            categoryProducts.put("Skirts", Arrays.asList("Mini skirt", "Pencil skirt", "A-line skirt", "Pleated skirt", "Maxi skirt", "Midi skirt", "Denim skirt", "Wrap skirt", "Circle skirt", "Asymmetrical skirt", "Tiered skirt", "Tulip skirt", "Mermaid skirt", "Leather skirt", "Tulle skirt", "High-waisted skirt", "Skater skirt", "Peplum skirt", "Sarong", "Godet skirt"));
            categoryProducts.put("Suits", Arrays.asList("Business suit", "Tuxedo", "Blazer", "Vest", "Pantsuit", "Skirt suit", "Three-piece suit", "Two-piece suit", "Dinner jacket", "Morning coat", "Double-breasted suit", "Single-breasted suit", "Cutaway coat", "Smoking jacket", "Sports jacket", "Waistcoat", "Tailcoat", "Lounge suit", "Mandarin suit", "Zoot suit"));
            categoryProducts.put("Sportswear", Arrays.asList("Tracksuit", "Yoga pants", "Sports bra", "Athletic shorts", "Running tights", "Gym tank top", "Training jacket", "Sweatshirt", "Compression shirt", "Base layer", "Hiking pants", "Cycling shorts", "Rash guard", "Wrestling singlet", "Tennis dress", "Golf polo", "Cricket pants", "Basketball jersey", "Soccer jersey", "Baseball pants"));
            categoryProducts.put("Swimwear", Arrays.asList("Bikini", "Swimsuit", "Swim trunks", "Board shorts", "Swim dress", "Tankini", "Monokini", "Rash guard", "Swim cap", "Wet suit", "Speedo", "Swim skirt", "Swim leggings", "Swim cover-up", "Swim tee", "High-waisted bikini", "One-piece swimsuit", "Surf suit", "Swimming briefs", "Square leg suit"));
            categoryProducts.put("Underwear", Arrays.asList("Boxers", "Briefs", "Bralette", "Thong", "Panties", "G-string", "Boxer briefs", "Trunks", "Undershirt", "Camisole", "Slip", "Shapewear", "Bikini briefs", "Hipster panties", "Long underwear", "Thermal shirt", "Thermal pants", "Sports bra", "Lingerie", "Nightgown"));
            categoryProducts.put("Sleepwear", Arrays.asList("Pajamas", "Nightgown", "Robe", "Sleep shorts", "Sleep pants", "Sleep shirt", "Slippers", "Eye mask", "Sleep cap", "Onesie", "Camisole", "Sleep dress", "Housecoat", "Thermal pajamas", "Chemise", "Nightshirt", "Sleep bralette", "Footed pajamas", "Bed socks", "Sleep set"));
            categoryProducts.put("Socks", Arrays.asList("Ankle socks", "Crew socks", "Knee-high socks", "No-show socks", "Over-the-calf socks", "Dress socks", "Athletic socks", "Compression socks", "Wool socks", "Cotton socks", "Toe socks", "Tab socks", "Slipper socks", "Patterned socks", "Striped socks", "Solid color socks", "Fuzzy socks", "Running socks", "Hiking socks", "Work socks"));
            categoryProducts.put("Gloves", Arrays.asList("Winter gloves", "Leather gloves", "Mittens", "Fingerless gloves", "Driving gloves", "Ski gloves", "Snowboard gloves", "Work gloves", "Gardening gloves", "Cycling gloves", "Golf gloves", "Lined gloves", "Touchscreen gloves", "Motorcycle gloves", "Boxing gloves", "Gym gloves", "Latex gloves", "Nitrile gloves", "Disposable gloves", "Heat resistant gloves"));
            categoryProducts.put("Bags", Arrays.asList("Backpack", "Handbag", "Messenger bag", "Tote bag", "Duffel bag", "Satchel", "Briefcase", "Clutch", "Hobo bag", "Sling bag", "Fanny pack", "Gym bag", "Laptop bag", "Drawstring bag", "Camera bag", "Cooler bag", "Rolling suitcase", "Diaper bag", "Shoulder bag", "Crossbody bag"));
            categoryProducts.put("Jewelry", Arrays.asList("Necklace", "Bracelet", "Earrings", "Ring", "Watch", "Anklet", "Brooch", "Cufflinks", "Tie clip", "Lapel pin", "Bangle", "Charm bracelet", "Choker", "Pendant", "Signet ring", "Wedding band", "Engagement ring", "Friendship bracelet", "Beaded necklace", "Jewelry set"));
            categoryProducts.put("Watches", Arrays.asList("Analog watch", "Digital watch", "Smartwatch", "Sports watch", "Dress watch", "Chronograph watch", "Diving watch", "Pilot watch", "Field watch", "Skeleton watch", "Tactical watch", "Pocket watch", "Luxury watch", "Casual watch", "Military watch", "Antique watch", "Solar-powered watch", "Automatic watch", "Mechanical watch", "Hybrid watch"));
            categoryProducts.put("Belts", Arrays.asList("Leather belt", "Canvas belt", "Reversible belt", "Dress belt", "Casual belt", "Braided belt", "Chain belt", "Elastic belt", "Garter belt", "Sash belt", "Suspender belt", "Utility belt", "Studded belt", "Designer belt", "Cummerbund", "Safety belt", "Weightlifting belt", "Martial arts belt", "Military belt", "Tool belt"));

            // Ensure there are unique categories
            Set<String> uniqueCategories = categoryProducts.keySet();

            // Create unique categories
            List<Category> categories = uniqueCategories.stream()
                    .map(name -> new Category(null, name, faker.lorem().sentence(), null))
                    .collect(Collectors.toList());
            categoryRepository.saveAll(categories);

            // Create unique brands
            Set<String> brandNames = new HashSet<>();
            while (brandNames.size() < 20) {
                brandNames.add(faker.company().name());
            }
            List<Brand> brands = brandNames.stream()
                    .map(name -> new Brand(null, name, faker.lorem().sentence(), null))
                    .collect(Collectors.toList());
            brandRepository.saveAll(brands);

            // Create products with names related to their category
            List<Product> products = new ArrayList<>();
            for (Category category : categories) {
                List<String> productNames = categoryProducts.get(category.getName());
                if (productNames != null) {
                    for (String productName : productNames) {
                        Brand brand = brands.get(random.nextInt(brands.size()));
                        Product product = new Product(
                                null,
                                productName,
                                faker.lorem().sentence(),
                                Double.parseDouble(faker.commerce().price().replace(",", ".")),
                                faker.number().numberBetween(1, 100),
                                faker.internet().image(),
                                brand,
                                category,
                                null
                        );
                        products.add(product);
                    }
                }
            }
            productRepository.saveAll(products);

            // Create sales
            List<Sale> sales = new ArrayList<>();
            LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
            LocalDate endDate = LocalDate.now();

            for (int i = 0; i < 10000; i++) {
                List<Product> saleProducts = new ArrayList<>();
                int numProducts = faker.number().numberBetween(1, 5);
                for (int j = 0; j < numProducts; j++) {
                    saleProducts.add(products.get(random.nextInt(products.size())));
                }
                long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
                LocalDate saleDate = startDate.plusDays(faker.number().numberBetween(0, (int) daysBetween + 1));

                Sale sale = new Sale(
                        null,
                        saleProducts.stream().mapToDouble(Product::getPrice).sum(),
                        saleProducts.size(),
                        saleDate,
                        faker.number().numberBetween(1L, 500L),
                        saleProducts
                );
                sales.add(sale);
            }
            saleRepository.saveAll(sales);
        };
    }
*/

}

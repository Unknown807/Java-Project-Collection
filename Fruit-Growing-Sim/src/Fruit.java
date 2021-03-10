public enum Fruit {
    Strawberries, Cherries, Watermelon, Apples,
    Blackberries, Bananas, Raspberries, Lemons,
    Pears, Apricots, Peaches, Nectarines, INVALIDFRUIT;

    public int price;
    public boolean growth;

    static {
        Strawberries.price = 10;
        Cherries.price = 20;
        Watermelon.price = 50;
        Apples.price = 45;
        Blackberries.price = 15;
        Bananas.price = 25;
        Raspberries.price = 12;
        Lemons.price = 30;
        Pears.price = 80;
        Apricots.price = 85;
        Peaches.price = 75;
        Nectarines.price = 120;

        for (Fruit fruit: values()) {
            fruit.growth = false;
        }

    }

    public static Fruit getFruit(String name) {
        Fruit f = INVALIDFRUIT;
        for (Fruit fruit: values()) {
            if (name.equals(fruit.name())) {
                f=fruit;
                break;
            }
        }
        return f;
    }

    public static void showFruit() {
        for (int i=0; i<values().length-1; i++){
            System.out.print("~"+values()[i]+"~ - "+values()[i].price+" | ");
            if (i == 3 || i == 7 || i == 11) {
                System.out.println();
            }
        }
    }
}
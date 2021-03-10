import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class FruitGame {

    public static Fruit chooseFruit(Scanner userInput) {
        Fruit chosenFruit;
        do {
            System.out.println("Enter Fruit Name:");
            chosenFruit = Fruit.getFruit(userInput.nextLine());
        } while(chosenFruit==Fruit.INVALIDFRUIT);
        return chosenFruit;
    }

    public static int initGame(ArrayList<Fruit> tempfruits, int money) {
        try {
            File fruits = new File("fruits.txt");
            if (fruits.createNewFile()) {
                System.out.println("Looks like its your first time playing!\n" +
                        "pick a starter fruit!");
                Fruit.showFruit();
                Scanner userInput = new Scanner(System.in);
                tempfruits.add(chooseFruit(userInput));
            } else {
                Scanner fileReader = new Scanner(fruits);
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    if (line.startsWith("money")) {
                        money=Integer.valueOf(line.split(" ")[1]);
                    } else {
                        Fruit fruit = Fruit.getFruit(line.split(" ")[0]);
                        Boolean growth = Boolean.valueOf(line.split(" ")[1]);
                        fruit.growth = growth;
                        tempfruits.add(fruit);
                    }
                }
                fileReader.close();
            }
        } catch (Exception e) {
            System.out.println("An error occurred in reading the fruits " +
                    "file\nLooks like you'll have to start again, sorry\n" +
                    "Your starter fruit will start as strawberries");
            tempfruits.add(Fruit.Strawberries);
            e.printStackTrace();
        }
        return money;
    }

    public static void showPlot(ArrayList<Fruit> fruits) {
        for (int i=0; i<fruits.size(); i++) {
            Fruit temp = fruits.get(i);
            System.out.print("|"+temp.name()+" Grown:"+temp.growth+"|");
            if (i == 2 || i == 5) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public static int buyFruit(ArrayList<Fruit> fruits, int money) {
        System.out.println("Which Fruit do you wish to buy?");
        Scanner userInput = new Scanner(System.in);
        Fruit.showFruit();
        System.out.println("Your money is - " + money);
        Fruit chosenFruit = chooseFruit(userInput);
        if (fruits.contains(chosenFruit)) {
            System.out.println("Sorry but you can't buy the same fruit");
        } else if (money < chosenFruit.price) {
            System.out.println("Sorry you don't have enough money to buy this");
        } else if ( fruits.size() == 9 ) {
            System.out.println("Sorry but you don't have enough space for any more fruit");
        } else {
            money -= chosenFruit.price;
            fruits.add(chosenFruit);
            System.out.println("You have successfully bought "+chosenFruit.name()+"!");
        }
        return money;
    }

    public static int sellFruit(ArrayList<Fruit> fruits, int money) {
        System.out.println("Which Fruit(s) do you wish to sell?");
        showPlot(fruits);
        Scanner userInput = new Scanner(System.in);
        Fruit chosenFruit = chooseFruit(userInput);
        Iterator<Fruit> it = fruits.iterator();
        while (it.hasNext()) {
            Fruit current = it.next();
            if (current.equals(chosenFruit) && current.growth) {
                int profit = current.price*2;
                money+=profit;
                chosenFruit.growth = false;
                System.out.println("Successfully sold fruit for "+profit);
                it.remove();
                break;
            } else if (current.equals(chosenFruit) && !current.growth) {
                System.out.println("You can't sell fruit that hasn't grown yet");
                break;
            }
        }
        return money;
    }

    public static void growFruit(ArrayList<Fruit> fruits) {
        for (Fruit fruit: fruits) {
            fruit.growth = true;
        }
        System.out.println("All the fruit in your plot have been grown!");
    }

    public static void main(String[] args) {
        System.out.println("Welcome To The Fruit Growing Simulator!");
        ArrayList<Fruit> fruits = new ArrayList<Fruit>();
        int money = 0;
        money = initGame(fruits, money);
        String option;
        do {
            Scanner userInput = new Scanner(System.in);
            System.out.println("(show/buy/sell/wait/qs):");
            option = userInput.nextLine();
            switch(option) {
                case "show":
                    showPlot(fruits);
                    break;
                case "buy":
                    money=buyFruit(fruits, money);
                    break;
                case "sell":
                    money=sellFruit(fruits, money);
                    break;
                case "wait":
                    growFruit(fruits);
                    break;
            }
        } while(!option.equals("qs"));
        try {
            FileWriter fileWriter = new FileWriter("fruits.txt");
            for (Fruit fruit: fruits) {
                fileWriter.write(fruit.name()+" "+fruit.growth+"\n");
            }
            fileWriter.write("money "+money);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("There was an error saving your fruits!");
        }
    }
}

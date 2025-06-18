import com.sun.tools.javac.Main;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {

        //testsStreams();
        //playingStringStreams();
        //iterateLimitStream();
        //reduceStream();
        collect();
    }

    public static void testsStreams() {
        StreamClass streamClass = new StreamClass();
        List<String> listString = Arrays.asList("teste", "str", "lalala");

        //Empty Stream
        Stream<String> resultEmpty = streamClass.streamOf(new ArrayList<>());
        System.out.println(resultEmpty);

        //Stream builder
        Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

        //Generate
        Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);
        System.out.println(streamGenerated);

        //Iterate
        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

        Stream<String> stream = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
        Optional<String> anyElement = stream.findAny();
    }

    public static void iterateLimitStream() {
        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();

        IntStream.iterate(1, n -> n + 1)
                .filter(Application::isPrime)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();

        IntStream.iterate(1, n -> n + 1)
                .limit(100)
                .filter(Application::isPrime)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();

        IntStream.iterate(1, n -> n <= 100, n -> n + 1)
                .filter(Application::isPrime)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();

        IntStream.range(1, 100)
                .filter(Application::isPrime)
                .forEach(s -> System.out.print(s + " "));

    }

    public static boolean isPrime(int number) {
        if (number <= 2) {
            return (number == 2);
        }
        for (int divisor = 2; divisor < number; divisor++) {
            if (number % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static void playingStringStreams() {
        String[] strings = {"One", "Two", "Three"};
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
        //.forEach(System.out::println);

        var secondStream = Stream.of("Six", "Five", "Four")
                .map(String::toUpperCase);
        //.forEach(System.out::println);

        Stream.concat(secondStream, firstStream)
                //.map(String::toLowerCase)
                .map(s -> s.charAt(0) + " - " + s)
                .forEach(System.out::println);
    }

    public static void reduceStream() {
        OptionalInt reduced = IntStream.range(1, 4).reduce(Integer::sum);
        System.out.println("Reduced Stream: " + reduced);

        int reducedTwoParams = IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
        System.out.println("Reduced Two Parameters Stream: " + reducedTwoParams);

        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called");
                    return a + b;
                });
        System.out.println("Reduced Paralel Stream " + reducedParallel);
    }

    public static void collect() {

        List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
                new Product(14, "orange"), new Product(13, "lemon"),
                new Product(23, "bread"), new Product(13, "sugar"));

        List<String> collectorCollection =
                productList.stream().map(Product::getName).toList();

        System.out.println("Collector List: " + collectorCollection);

        String listToString = productList.stream().map(Product::getName)
                .collect(Collectors.joining(", ", "[", "]"));

        //Same result than collectorCollection
        System.out.println("List to String: " + listToString);

        double averagePrice = productList.stream()
                .collect(Collectors.averagingInt(Product::getPrice));

        System.out.println("Average Price: " + averagePrice);

        int summingPrice = productList.stream().mapToInt(Product::getPrice).sum();
        System.out.println("Summing Price: " + summingPrice);

        Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
                .collect(Collectors.groupingBy(Product::getPrice));

        System.out.println("Collector Map List: " + collectorMapOfLists);

        Map<Boolean, List<Product>> mapPartioned = productList.stream()
                .collect(Collectors.partitioningBy(element -> element.getPrice() > 15));

        System.out.println("Map Partioned: " + mapPartioned);

    }
}

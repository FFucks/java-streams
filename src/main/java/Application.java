import com.sun.tools.javac.Main;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {

        testsStreams();
        //playingStringStreams();
        //iterateLimitStream();

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
}

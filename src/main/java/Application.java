import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {

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

    }
}

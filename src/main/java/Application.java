import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {

        StreamClass streamClass = new StreamClass();
        List<String> listString = Arrays.asList("teste", "str", "lalala");

        Stream<String> resultEmpty = streamClass.streamOf(new ArrayList<>());
        System.out.println(resultEmpty);




    }
}

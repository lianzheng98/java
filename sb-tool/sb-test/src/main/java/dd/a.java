package dd;

import com.pg.cc.sb.core.util.JSONUtils;
import io.vavr.API;
import io.vavr.Function5;
import io.vavr.Lazy;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@Slf4j
public class a {

    @Test
    public void func1() {
        Option<String> strings = storageGetById(2);
        System.out.println(strings.getOrElse("失败的才是 222"));
        // filter 过滤掉满足条件的
        strings.filter(s -> s.equals("123")).peek(s -> System.out.println(s));
        //flat map 相当于 option -> option
        Option<String> strings1 = strings.flatMap(s -> Option.of(s + "123"));
        // .peek(s -> System.out.println(s));

        // map 相当于 代码里帮我们做了 option.of
        Option<String> map = strings.map(s -> s + "123");
        // .peek(s -> System.out.println(s));

        System.out.println(
                strings.toString()
        );
        // fold 当为空执行第一个函数 当不为空执行第二个函数
        String fold = strings.fold(() -> "3333", s -> s + "5555");
        System.out.println(fold);

        // peek 执行一次
        strings.peek(s -> System.out.println(s));

        // transform 转换
        String transform = strings.transform(s -> s + "123");
        System.out.println(transform);
    }


    private Option<String> storageGetById(int i) {
        if (i == 1) {
            return Option.none();
        } else {
            return Option.of("222");
        }
    }


    @Test
    public void whenCreatesTuple_thenCorrect2() {
        Tuple3<String, Integer, Double> java8 = Tuple.of("Java", 8, 1.8);
        String element1 = java8._1;
        int element2 = java8._2();
        double element3 = java8._3();

        assertEquals("Java", element1);
        assertEquals(8, element2);
        assertEquals(1.8, element3, 0.1);


        Map<Integer, Tuple3<Integer, Long, String>> maps = new HashMap<>();
        maps.put(1, Tuple.of(1, 1L, "1"));
        System.out.println(maps);
        String s = JSONUtils.writeValue(maps);
        System.out.println(s);

        Map<Integer, Tuple3<Integer, Long, String>> ext = (Map<Integer, Tuple3<Integer, Long, String>>) JSONUtils.readValue(s, Map.class);
        System.out.println(
                ext
        );
    }


    @Test
    public void givenBadCode_whenTryHandles_thenCorrect() {
        Try<Integer> result = Try.of(() -> 1 / 0);

        assertTrue(result.isFailure());
    }

    @Test
    public void givenBadCode_whenTryHandles_thenCorrect2() {
        Try<Integer> computation = Try.of(() -> 1 / 0);
        int errorSentinel = computation.getOrElse(-1);

        assertEquals(-1, errorSentinel);
    }

    @Test(expected = ArithmeticException.class)
    public void givenBadCode_whenTryHandles_thenCorrect3() {
        Try<Integer> result = Try.of(() -> 1 / 1);

        Try<Integer> t1 = Try.ofSupplier(() -> 1 / 1);
        Try<Integer> integers = t1.andThen(i -> System.out.println(i));


        Try<Integer> t2 = t1.map(x -> x + 2);

        System.out.println(t2.get());
        t2.andThen(i -> System.out.println(i));


    }

    @Test
    public void whenCreatesFunction_thenCorrect5() {
        Function5<String, String, String, String, String, String> concat =
                (a, b, c, d, e) -> a + b + c + d + e;
        String finalString = concat.apply(
                "Hello ", "world", "! ", "Learn ", "Vavr");

        assertEquals("Hello world! Learn Vavr", finalString);
    }


    @Test(expected = UnsupportedOperationException.class)
    public void whenImmutableCollectionThrows_thenCorrect() {
        java.util.List<String> wordList = Arrays.asList("abracadabra");
        java.util.List<String> list = Collections.unmodifiableList(wordList);
        list.add("boom");
    }

    @Test
    public void whenCreatesVavrList_thenCorrect() {
        List<Integer> intList = List.of(1, 2, 3);

        assertEquals(3, intList.length());
        assertEquals(new Integer(1), intList.get(0));
        assertEquals(new Integer(2), intList.get(1));
        assertEquals(new Integer(3), intList.get(2));
    }

    @Test
    public void whenSumsVavrList_thenCorrect() {
        int sum = List.of(1, 2, 3).sum().intValue();

        assertEquals(6, sum);
    }

    @Test
    public void givenFunction_whenEvaluatesWithLazy_thenCorrect() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        assertFalse(lazy.isEvaluated());

        double val1 = lazy.get();
        assertTrue(lazy.isEvaluated());

        double val2 = lazy.get();
        assertEquals(val1, val2, 0.1);
    }

    @Test
    public void whenMatchworks_thenCorrect() {
        int input = 2;
        String output = API.Match(input).of(
                API.Case(API.$(1), "one"),
                API.Case(API.$(2), "two"),
                API.Case(API.$(3), "three"),
                API.Case(API.$(), "?"));

        assertEquals("two", output);

        String arg = "-h";
        String output2 = API.Match(arg).of(
                API.Case(API.$("-h"), o -> h1(o)),
                API.Case(API.$("-v"), o -> h2(o)),
                API.Case(API.$(), "unknown")
        );




        System.out.println(output2);
    }

    private String h2(String o) {
        return "11";
    }

    private String h1(String o) {
        return "222";


    }

    private void displayVersion() {


    }

    private void displayHelp() {


    }


}

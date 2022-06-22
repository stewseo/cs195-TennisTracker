//import org.assertj.core.groups.Tuple;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TestConsumer {
//
//    int result;
//
//    @Test
//    public void testConsumers() {
//        Consumer0 c0 = () -> { result = 1; };
//        Runnable r = c0.toRunnable();
//        Consumer0 c0a = Consumer0.from(r);
//
//        result = 0;
//        c0.accept();
//        assertEquals(1, result);
//
//        result = 0;
//        c0.accept(Tuple.tuple());
//        assertEquals(1, result);
//
//        result = 0;
//        r.run();
//        assertEquals(1, result);
//
//        result = 0;
//        c0a.accept();
//        assertEquals(1, result);
//
//
//
//        Consumer1<Integer> c1 = i -> { result = i; };
//        Consumer<Integer> c1a = c1.toConsumer();
//        Consumer1<Integer> c1b = Consumer1.from(c1a);
//
//        result = 0;
//        c1.accept(1);
//        assertEquals(1, result);
//
//        result = 0;
//        c1.accept(Tuple.tuple(1));
//        assertEquals(1, result);
//
//        result = 0;
//        c1a.accept(1);
//        assertEquals(1, result);
//
//        result = 0;
//        c1b.accept(1);
//        assertEquals(1, result);
//
//
//
//        Consumer2<Integer, Integer> c2 = (i, j) -> { result = i + j; };
//        BiConsumer<Integer, Integer> c2a = c2.toBiConsumer();
//        Consumer2<Integer, Integer> c2b = Consumer2.from(c2a);
//
//        result = 0;
//        c2.accept(1, 2);
//        assertEquals(3, result);
//
//        result = 0;
//        c2.accept(Tuple.tuple(1, 2));
//        assertEquals(3, result);
//
//        result = 0;
//        c2a.accept(1, 2);
//        assertEquals(3, result);
//
//        result = 0;
//        c2b.accept(1, 2);
//        assertEquals(3, result);
//    }
//
//    @Test
//    public void testTupleConsumers() {
//        int[] number = new int[1];
//        String[] string = new String[1];
//
//        number[0] = 0;
//        Seq.of(Tuple.tuple(), Tuple.tuple(), Tuple.tuple())
//                .forEach(Tuple.consumer(() -> number[0] = number[0] + 1));
//        assertEquals(3, number[0]);
//
//        number[0] = 0;
//        Seq.of(Tuple.tuple(1), Tuple.tuple(2), Tuple.tuple(3))
//                .forEach(Tuple.consumer(t -> number[0] = number[0] + t));
//        assertEquals(6, number[0]);
//
//        number[0] = 0;
//        string[0] = "";
//        Seq.of(Tuple.tuple(1, "A"), Tuple.tuple(2, "B"), Tuple.tuple(3, "C"))
//                .forEach(Tuple.consumer((i, s) -> {
//                    number[0] = number[0] + i;
//                    string[0] = string[0] + s;
//                }));
//
//        assertEquals(6, number[0]);
//        assertEquals("ABC", string[0]);
//    }
//}

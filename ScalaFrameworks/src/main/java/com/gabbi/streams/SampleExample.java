package com.gabbi.streams;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by gabbi on 28/12/14.
 */
public class SampleExample {
    public static void main(String[] args) {
        RandomStream randomStream = new RandomStream();
        randomStream.stream().limit(5).forEach(System.out::println);
    }
}

class RandomStream implements Iterable<Integer>{
    public Stream<Integer> stream(){
        return StreamSupport.stream(this.spliterator(),false);
    }
    @Override
    public Iterator<Integer> iterator() {
        final Random random = new Random(System.currentTimeMillis());
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return random.nextInt();
            }
        };
    }
}

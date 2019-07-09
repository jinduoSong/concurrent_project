package netty;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 15:18 2019/7/8.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class JMHSample_02_Map {

    static Map hashMap = new HashMap();
    static Map syncHashMap = Collections.synchronizedMap(new HashMap());
    static Map concurrentHashMap = new ConcurrentHashMap();

    @Setup
    public void setup() {
        for (int j = 0; j < 10000; j++) {
            hashMap.put(Integer.toString(j), Integer.toString(j));
            syncHashMap.put(Integer.toString(j), Integer.toString(j));
            concurrentHashMap.put(Integer.toString(j), Integer.toString(j));
        }
    }

    @Benchmark
    public void hashMapGet(){
        hashMap.get("4");
    }

    @Benchmark
    public void syncHashMapGet(){
        syncHashMap.get("4");
    }

    @Benchmark
    public void concurrentHashMapGet(){
        concurrentHashMap.get("4");
    }

    @Benchmark
    public void hashMapSize(){
        hashMap.size();
    }

    @Benchmark
    public void syncHashMapSize(){
        syncHashMap.size();
    }

    @Benchmark
    public void concurrentHashMapSize(){
        concurrentHashMap.size();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(JMHSample_02_Map.class.getSimpleName()).forks(1).build();
        new Runner(options).run();
    }


}

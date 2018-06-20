package com.example.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Optional;

public class TestOption implements TestInterFace{

    private static boolean test(Object o) {
        return true;
    }

    @Override
    public boolean get() {
        return false;
    }


    @Test
    public void test1(){
//        System.out.println(Optional.ofNullable(null).orElse("dexuan"));
//        Optional<String> dong = Optional.ofNullable("dong").filter(o -> o.equals(""));
////        System.out.println(dong.get());
//        HashMap<String, Optional<String>> stringOptionalHashMap = new HashMap<>();
//        System.out.println(Optional.ofNullable("dong").flatMap(s -> stringOptionalHashMap.get(s)));
//        System.out.println(Optional.ofNullable("dong").map(s -> stringOptionalHashMap.get(s)));
//        Optional<String> dong1 = Optional.ofNullable("dong").flatMap(s -> Optional.of(s.toUpperCase()));
//        System.out.println(dong1.get());
        String a = "1";
        Optional<String> s2 = Optional.ofNullable(a).map(s -> s.isEmpty() ? null : s);
        s2.orElseThrow(() -> new EmptyStackException());
        System.out.println(s2.isPresent());

        String s1 = Optional.ofNullable(new User()).map(user -> user.name).map(s -> s.toUpperCase()).orElse(null);

        ArrayList<String> strings = new ArrayList<>();
        Optional<String> s3 = Optional.ofNullable(strings).map(s -> s.size() > 0 ? s.get(0) : null);
//        strings.get(0);

        Optional<String> empty = Optional.empty();
        empty.orElseThrow(() -> new EmptyStackException());

//        System.out.println(O);
//        TestInterFace.testt();
//        TestInterFace testInterFace = new TestInterFace() {
//            @Override
//            public boolean get() {
//                return false;
//            }
//        };
////        System.out.println(testInterFace.a);
////
////        System.out.println(a);
//        List<String> arrayList = new ArrayList<>();
//        arrayList.add("2");
//        arrayList.add("1");
//        arrayList.add("5");
//        arrayList.add("3");
//        arrayList.sort(String::compareTo);
//        System.out.println(arrayList);
//        arrayList.sort((o1, o2) -> -o2.compareTo(o1));
//        System.out.println(arrayList);
//        arrayList.sort((o1, o2) -> -o1.compareTo(o2));
//        System.out.println(arrayList);
    }

    class User{
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}

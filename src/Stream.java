import java.util.*;
import java.util.stream.*;

public class Stream {

    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("Anna", 23, "Warsaw"),
                new Person("Bartek", 31, "Krakow"),
                new Person("Celina", 19, "Warsaw"),
                new Person("Darek", 40, "Gdansk"),
                new Person("Ela", 31, "Krakow"),
                new Person("Filip", 28, "Warsaw"),
                new Person("Gosia", 35, "Gdansk")
        );

        List<Person> warsawPeople = people.stream()
                .filter(p -> p.city.equals("Warsaw"))
                .collect(Collectors.toList());

        System.out.println("Osoby z Warszawy:");
        warsawPeople.forEach(System.out::println);

        Map<String, List<Person>> grouped =
                people.stream().collect(Collectors.groupingBy(p -> p.city));

        System.out.println("\nGrupowanie po mieście:");
        grouped.forEach((city, persons) -> {
            System.out.println(city + ":");
            persons.forEach(System.out::println);
        });


        people.stream()
                .max(Comparator.comparingInt(p -> p.age))
                .ifPresent(p -> System.out.println("\nNajstarsza osoba: " + p));

        List<String> cities = people.stream()
                .map(p -> p.city)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("\nUnikalne miasta:");
        cities.forEach(System.out::println);

        double avgKrakow = people.stream()
                .filter(p -> p.city.equals("Krakow"))
                .mapToInt(p -> p.age)
                .average()
                .orElse(0);

        System.out.println("\nŚredni wiek w Krakowie: " + avgKrakow);
    }
}

class Person {
    String name;
    int age;
    String city;

    Person(String n, int a, String c) {
        this.name = n;
        this.age = a;
        this.city = c;
    }

    @Override
    public String toString() {
        return name + " (" + age + "), " + city;
    }
}
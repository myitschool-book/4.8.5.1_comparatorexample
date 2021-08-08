package ru.samsung.itschool.mdev;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Person {
    private final String name;
    private final String surname;
    private final int age;

    /**
     * @return возраст этого человека
     */
    public int getAge() {
        return age;
    }

    /**
     * @return имя этого человека
     */
    public String getName() {
        return name;
    }

    /**
     * @return фамилия этого человека
     */
    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    /**
     * Позволяет создать нового человека
     * @param age возраст
     * @param name имя
     * @param surname фамилия
     */
    public Person(int age, String name, String surname) {
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Экземпляр компаратора, который сравнивает двух людей по именам
     */
    public static final Comparator<Person> NAME_COMPARATOR =
            new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    // возвращаем результат сравнения имен
                    return o1.getName().compareTo(o2.getName());
                }
            };

    /**
     * Экземпляр компаратора, который сравнивает двух людей по фамилиям,
     * а в случае равенства фамилий - по именам
     */
    public static final Comparator<Person> SURNAME_NAME_COMPARATOR =
            new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    // сравним фамилии
                    int result = o1.getSurname().compareTo(o2.getSurname());
                    // если фамилии не одинаковы - вернем результат сравнения
                    if (result != 0)
                        return result;
                    // для одинаковых фамилий, результат сравнения - сравнение имен
                    return o1.getName().compareTo(o2.getName());
                }
            };

    /**
     * Экземпляр компаратора, который сравнивает двух людей по возрасту,
     * в случе равенства возрастов - по фамилиям, а в случае равенства
     * фамилий - по именам
     */
    public static final Comparator<Person> AGE_SURNAME_NAME_COMPARATOR =
            new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    // сравним возраст
                    int result = Integer.compare(o1.getAge(), o2.getAge());
                    // если возраст не одинаков - вернем результат сравнения
                    if (result != 0)
                        return result;

                    // сравним фамилии
                    result = o1.getSurname().compareTo(o2.getSurname());
                    // если фамилии не одинаковы - вернем результат сравнения
                    if (result != 0)
                        return result;

                    // для одинаковых возрастов и фамилий, результат
                    // сравнения - сравнение имен
                    return o1.getName().compareTo(o2.getName());
                }
            };

    public static void main(String[] args) {
        List<Person> people = new ArrayList<Person>();
        people.add(new Person(22, "Eikichi", "Onizuka"));
        people.add(new Person(72, "Edsger", "Dijkstra"));
        people.add(new Person(41, "Alan", "Turing"));
        people.add(new Person(41, "Sergey", "Brin"));
        people.add(new Person(41, "Impersonator", "Brin"));
        people.add(new Person(28, "Dmitry", "Karamazov"));
        people.add(new Person(23, "Ivan", "Karamazov"));
        people.add(new Person(16, "Alex", "Karamazov"));

        // отсортируем людей по именам
        Collections.sort(people, Person.NAME_COMPARATOR);
        // напечатаем их
        System.out.println("By name:");
        for (Person p: people)
            System.out.println(p);
        System.out.println();

        // отсортируем людей по фамилиям, а затем по именам
        Collections.sort(people, Person.SURNAME_NAME_COMPARATOR);
        // напечатаем их
        System.out.println("By surname and then by name:");
        for (Person p: people)
            System.out.println(p);
        System.out.println();

        // отсортируем людей по возрасту, а затем по фамилиям, а затем по именам
        Collections.sort(people, Person.AGE_SURNAME_NAME_COMPARATOR);
        // напечатаем их
        System.out.println("By age and then by surname and then by name:");
        for (Person p: people)
            System.out.println(p);
    }

}
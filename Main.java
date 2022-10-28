import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних " + count);

        List<String> inductees = persons.stream()
                .filter(value -> value.getSex().equals(Sex.MAN))
                .filter(value -> (value.getAge() >= 18 & value.getAge() <= 27))
                .map(Person::getFamily).toList();
        System.out.println("Призывники: " + inductees);

        List<Person> higherEducatedWorkables = persons.stream()
                .filter(value -> value.getEducation().equals(Education.HIGHER))
                .filter(value -> value.getAge() >= 18)
                .filter(
                        value -> {
                            if (value.getSex().equals(Sex.MAN) & value.getAge() < 60) {
                                return true;
                            } else return value.getSex().equals(Sex.WOMAN) & value.getAge() < 65;
                        }
                )
                .sorted(Comparator.comparing(Person::getFamily)).toList();
        System.out.println("Потенциально работоспособные люди с высшим образованием: " + higherEducatedWorkables);
    }
}
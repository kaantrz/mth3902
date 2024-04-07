import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.concurrent.TimeUnit;

public class HazelcastExample {

    public static void main(String[] args) {
        // Configure Hazelcast client
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("localhost:5701"); // Adjust port if necessary

        // Create Hazelcast client instance
        HazelcastInstance hazelcastClient = HazelcastClient.newHazelcastClient(clientConfig);

        // Get the distributed map from Hazelcast
        IMap<Integer, Person> personMap = hazelcastClient.getMap("persons");

        // Dummy Person object
        Person dummyPerson = new Person("John", "Doe", 30); // Example person

        // Put dummy Person objects into the map
        for (int i = 0; i < 10000; i++) {
            personMap.put(i, dummyPerson);
        }

        System.out.println("Putting 10,000 dummy Person objects into Hazelcast map.");

        // Retrieve a dummy Person object from the map
        Person retrievedPerson = personMap.get(0); // Retrieving the first entry

        System.out.println("Retrieved Person: " + retrievedPerson);

        // Shutdown Hazelcast client
        hazelcastClient.shutdown();
    }

    static class Person {
        private String firstName;
        private String lastName;
        private int age;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

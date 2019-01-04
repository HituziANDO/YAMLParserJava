import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        HashTypeYaml yaml = new HashTypeYaml();

        try {
            HashTypeYaml.dump(new FileInputStream("test1.yml"));

            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Map<String, Object> map1 = yaml.decode(new FileInputStream("test1.yml"));

            for (String key : map1.keySet()) {
                System.out.println(key + ": " + map1.get(key));
            }

            System.out.println();

            // For example, get `id`, `title` and `users`.
            System.out.println(map1.get("test1/id"));   // => 1234
            System.out.println(map1.get("test1/data/title"));   // => ABC Team
            List users = (List) map1.get("test1/data/users");
            System.out.println(users.get(1));   // => Bob

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }


        HashTypeYaml.dump("test2.yml");

        System.out.println();

        Map<String, Object> map2 = yaml.decodeOrEmpty("test2.yml");
        // Get top level list.
        System.out.println(map2.get(""));

        System.out.println();

        // Because not found test3.yml, `map3` goes empty.
        Map<String, Object> map3 = yaml.decodeOrEmpty("test3.yml");
        System.out.println("isEmpty: " + map3.isEmpty());
    }
}

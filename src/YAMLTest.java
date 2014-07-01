import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;


public class YAMLTest {

	public static void main (String[] args) throws IOException {
		HashTypeYAML.dump(new FileInputStream("test.yml"));

		HashTypeYAML yaml = new HashTypeYAML();

		Map<String, Object> hash = yaml.decode(new FileInputStream("test.yml"));
		for (String key : hash.keySet()) {
			System.out.println("(key, value)=(" + key + ", " + hash.get(key) + ")");
		}
	}
}

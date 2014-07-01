import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;


public class YAMLTest {

	public static void main (String[] args) throws IOException {
		HashTypeYAML.dump(new FileInputStream("ja.yml"));

		HashTypeYAML yaml = new HashTypeYAML();

		Map<String, Object> hash = yaml.decode(new FileInputStream("ja.yml"));
		for (String key : hash.keySet()) {
			System.out.println(key + ":" + hash.get(key));
		}

//		Map<String, Map<String, Object>> hash1 = yaml.decode(new FileInputStream("ja.yml"));
//		Map<String, Object> hash2;
//		for (String key1 : hash1.keySet()) {
//			hash2 = hash1.get(key1);
//			for (String key2 : hash2.keySet()) {
//				System.out.println(key2 + ":" + hash2.get(key2));
//			}
//		}
	}
}

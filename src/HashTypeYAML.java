import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * YAML Parser
 * 
 * @author Hituzi ANDO
 *
 */
public class HashTypeYAML {

	private String mIndent;

	/**
	 * Use two white spaces for one indent.
	 */
	public HashTypeYAML () {
		this(2);
	}
	/**
	 * @param indentSpaceNum Number of white space for one indent.
	 */
	public HashTypeYAML (int indentSpaceNum) {
		if (indentSpaceNum > 0) {
			String space = "";
			for (int i = 0; i < indentSpaceNum; i++) {
				space += " ";
			}
			mIndent = space;
		}
	}
	/**
	 * System.out.println(<em>each line of YAML file</em>)
	 * @param is
	 * @throws IOException
	 */
	public static void dump (InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String line;
		while ((line = reader.readLine()) != null) {
			// Ignore comment line and empty line.
			if (line.startsWith("#") || "".equals(line) || !line.contains(":")) continue;
			System.out.println(line);
		}
	}

//	public Map<String, Map<String, Object>> decode (InputStream is) throws IOException {
//		String line;
//		String key;
//		String val;
//		String keyChain = "";
//		Map<String, Map<String, Object>> hash1 = new HashMap<String, Map<String, Object>>();
//		Map<String, Object> hash2 = new HashMap<String, Object>();
//		KeyChainFactory kcFactory = new KeyChainFactory();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//
//		while ((line = reader.readLine()) != null) {
//
//			// Ignore comment line and empty line.
//			if (line.startsWith("#") || "".equals(line) || !line.contains(":")) continue;
//
//			String[] str = line.split(":");
//			key = str[0];
//			int index = (int) (Math.floor(key.lastIndexOf(mIndent) * 0.5) + 1);
//			keyChain = kcFactory.createKeyChain(index, key);
//
//			// Add sub-hash to hash if only key.
//			if (str.length == 1) {
//				if (hash1.containsKey(keyChain)) {
//					hash2 = (Map<String, Object>) hash1.get(keyChain);
//				} else {
//					hash2 = new HashMap<String, Object>();
//				}
//				hash1.put(keyChain, hash2);
//			}
//			// Add key-value.
//			else {
//				// Remove unnecessary strings.
//				val = str[1].trim().replaceAll("\"", "");
//				hash2.put(keyChain, val);
//			}
//		}
//
//		return hash1;
//	}

	public Map<String, Object> decode (InputStream is) throws IOException {
		String line;
		String key;
		String val;
		String keyChain = "";
		Map<String, Object> hash = new HashMap<String, Object>();
		KeyChainFactory kcFactory = new KeyChainFactory();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		while ((line = reader.readLine()) != null) {

			// Ignore comment line and empty line.
			if (line.startsWith("#") || "".equals(line) || !line.contains(":")) continue;

			String[] str = line.split(":");
			key = str[0];
			int index = (int) (Math.floor(key.lastIndexOf(mIndent) * 0.5) + 1);
			keyChain = kcFactory.createKeyChain(index, key);

			// Add key-value if not only key.
			if (str.length > 1) {
				// Remove unnecessary strings.
				val = str[1].trim().replaceAll("\"", "");
				hash.put(keyChain, val);
			}
		}

		return hash;
	}

	private class KeyChainFactory {

		private List<String> mKeys = new ArrayList<String>();

		public String createKeyChain (int index, String key) {
			// Remove indents.
			key = key.trim();
			try {
				// Replace the key at index.
				mKeys.set(index, key);
				// Remove the keys after specified index.
				int len = mKeys.size();
				if (index < len) {
					for (int i = index + 1; i < len; i++) {
						mKeys.remove(i);
					}
				}
			} catch (Exception e) {
				// Add the key at last of key-chain.
				mKeys.add(key);
			}
			// Create a key-chain.
			String keyChain = mKeys.get(0);
			for (int i = 1, len = mKeys.size(); i < len; i++) {
				keyChain = keyChain + "." + mKeys.get(i);
			}
			return keyChain;
		}
	}

}

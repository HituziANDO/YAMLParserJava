# YAMLParserJava

***YAML decoder in Java.***

## Include in your project

1. Puts `src/HashTypeYaml.java` in your project
1. Sets any package to HashTypeYaml.java

## Usage

For example, decodes following YAML as test1.yml file.

```yaml
test1:
  id: 1234
  country: "Japan"
  code:
  data:
    users:
      - Alice
      - Bob
      - Chris
    title: "ABC Team"
    description: "ABC Team is the best!"
```

1. Create instance
	
	```java
	HashTypeYaml yaml = new HashTypeYaml();
	```

1. Decode YAML and convert to Map object
	
	```java
	try {
	    Map<String, Object> map = yaml.decode(new FileInputStream("test1.yml"));
	} catch (IOException e) {
	    // Error handling
	}
	```
	
	Or
	
	```java
	Map<String, Object> map = yaml.decodeOrEmpty("test1.yml");
	```
	
	`decodeOrEmpty` does not raise the exception and returns empty map if I/O error is occurred.

1. Get value
	
	```java
	// Get id.
	System.out.println(map.get("test1/id"));   // => 1234
	
	// Get title.
	System.out.println(map.get("test1/data/title"));   // => ABC Team
	
	// Get users.
	List users = (List) map.get("test1/data/users");
	System.out.println(users.get(1));   // => Bob
	```

More info, see my [sample code](https://github.com/HituziANDO/YAMLParserJava/blob/master/src/Main.java).

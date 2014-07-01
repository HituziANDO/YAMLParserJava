YAMLParser
==========

YAML形式のデータをデコードしMapオブジェクトへ変換します

How to use
==========

    HashTypeYAML yaml = new HashTypeYAML();
    Map<String, Object> hash = yaml.decode(new FileInputStream("test.yml"));
    
    // 値を取り出す
    String country = (String) hash.get("test.country");
    String title = (String) hash.get("test.data.public.title");

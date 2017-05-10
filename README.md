YAMLParser
==========

YAML形式のデータをデコードしMapオブジェクトへ変換します.

How to use
==========

以下のYAMLファイルを読込むとき

##### YAMLのサンプル
test.yml

    test:
      id: 1234
      country: "Japan"
      data:
        public:
          title: "XYZ"
          description: "xyz"
        private:
          title: "ABC"
          description: "abc"

##### Javaコード

    HashTypeYAML yaml = new HashTypeYAML();
    Map<String, Object> hash = yaml.decode(new FileInputStream("test.yml"));
    
    // 値を取り出す(値までのkeyをドット"."でつなげる)
    String country = (String) hash.get("test.country"); // -> "Japan"
    String title = (String) hash.get("test.data.public.title"); // -> "XYZ"

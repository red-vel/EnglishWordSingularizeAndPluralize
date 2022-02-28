import java.util.HashMap;

// EnglishWordUtil测试类
public class EnglishWordUtilTest {
    public static void main(String[] args) {
        //0、单复数同形
        HashMap<String, String> map0 = new HashMap<>();
        map0.put("deer", "deer");
        map0.put("shoes", "shoes");
        map0.put("sheep", "sheep");
        map0.put("fish", "fish");
        map0.put("Chinese", "Chinese");
        map0.put("English", "English");
        map0.put("Portuguese", "Portuguese");
        map0.put("Lebanese", "Lebanese");
        map0.put("Swiss", "Swiss");
        map0.put("Vietnamese", "Vietnamese");
        map0.put("Japanese", "Japanese");
        map0.put("economics", "economics");
        map0.put("news", "news");
        map0.put("human", "human");
        //1、单数复数的不规则变化
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("person", "people");
        map1.put("man", "men");
        map1.put("child", "children");
        map1.put("foot", "feet");
        map1.put("tooth", "teeth");
        map1.put("mouse", "mice");
        map1.put("louse", "lice");
        map1.put("matrix", "matrices");
        map1.put("vertex", "vertices");
        map1.put("ox", "oxen");
        map1.put("goose", "geese");
        map1.put("basis", "bases");
        //2、直接加"-s"的x国人
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("German", "Germans");
        map2.put("Russian", "Russians");
        map2.put("American", "Americans");
        map2.put("Italian", "Italians");
        map2.put("Indian", "Indians");
        map2.put("Canadian", "Canadians");
        map2.put("Australian", "Australians");
        map2.put("Swede", "Swedes");
        //3、以s, x, ch, sh结尾，加"-es"
        HashMap<String, String> map3 = new HashMap<>();
        map3.put("bus", "buses");
        map3.put("class", "classes");
        map3.put("box", "boxes");
        map3.put("sex", "sexes");
        map3.put("watch", "watches");
        map3.put("brush", "brushes");
        //4、以元音字母+o结尾，后面加"-s"
        HashMap<String, String> map4 = new HashMap<>();
        map4.put("zoo", "zoos");
        map4.put("photo", "photos");
        map4.put("kangaroo", "kangaroos");
        map4.put("bamboo", "bamboos");
        map4.put("radio", "radios");
        map4.put("piano", "pianos");
        map4.put("video", "videos");
        map4.put("kilo", "kilos");
        map4.put("studio", "studios");
        //5、以辅音字母+o结尾（除studio,piano,kilo,photo)，后面加"-es"
        HashMap<String, String> map5 = new HashMap<>();
        map5.put("hero", "heroes");
        map5.put("Negro", "Negroes");
        map5.put("tomato", "tomatoes");
        map5.put("potato", "potatoes");
        map5.put("mango", "mangoes");
        //6、以辅音字母加y结尾的名词，变y为i加"-es "
        HashMap<String, String> map6 = new HashMap<>();
        map6.put("baby", "babies");
        map6.put("city", "cities");
        map6.put("factory", "factories");
        map6.put("family", "families");
        //7、以元音字母加y结尾的名词直接加"-s"
        HashMap<String, String> map7 = new HashMap<>();
        map7.put("boy", "boys");
        map7.put("toy", "toys");
        map7.put("monkey", "monkeys");
        //8、除了roof，gulf，proof，beef，staff，belief，cliff
        //以fe或f结尾的名词，把fe或f变为v加"-es"
        HashMap<String, String> map8 = new HashMap<>();
        map8.put("roof", "roofs");
        map8.put("gulf", "gulfs");
        map8.put("proof", "proofs");
        map8.put("beef", "beefs");
        map8.put("staff", "staffs");
        map8.put("belief", "beliefs");
        map8.put("cliff", "cliffs");
        map8.put("knife", "knives");
        map8.put("wife", "wives");
        map8.put("wolf", "wolves");
        map8.put("life", "lives");
        //9、无连字号复合名词，后面名词变复数
        HashMap<String, String> map9 = new HashMap<>();
        map9.put("mooncake", "mooncakes");
        map9.put("stopwatch", "stopwatches");
        map9.put("armchair", "armchairs");
        map9.put("gentleman", "gentlemen");
        map9.put("housewife", "housewives");
        map9.put("wineglass", "wineglasses");
        map9.put("greenhouse", "greenhouses");
        //10、普通名词，加s
        HashMap<String, String> map10 = new HashMap<>();
        map10.put("map", "maps");
        map10.put("orange", "oranges");
        map10.put("German", "Germans");
        map10.put("Russian", "Russians");
        map10.put("American", "Americans");
        map10.put("Italian", "Italians");
        map10.put("Indian", "Indians");
        map10.put("Canadian", "Canadians");
        map10.put("Australian", "Australians");
        map10.put("Swede", "Swedes");

        testPluralize(map0, 0);
        testPluralize(map1, 1);
        testPluralize(map2, 2);
        testPluralize(map3, 3);
        testPluralize(map4, 4);
        testPluralize(map5, 5);
        testPluralize(map6, 6);
        testPluralize(map7, 7);
        testPluralize(map8, 8);
        testPluralize(map9, 9);
        testPluralize(map10, 10);

        testSingularize(map0, 0);
        testSingularize(map1, 1);
        testSingularize(map2, 2);
        testSingularize(map3, 3);
        testSingularize(map4, 4);
        testSingularize(map5, 5);
        testSingularize(map6, 6);
        testSingularize(map7, 7);
        testSingularize(map8, 8);
        testSingularize(map9, 9);
        testSingularize(map10, 10);
    }

    private static void testPluralize(HashMap<String, String> map, int order) {
        System.out.println("\n----------------单数变复数 测试第" + order + "种类型------------------");
        for (String singular : map.keySet()) {
            String plural = EnglishWordUtil.pluralize(singular);
            String correctPlural = map.get(singular);
            if (plural.equals(correctPlural)) {
                System.out.println("第" + order + "种类型 单数：" + singular + "，复数：" + plural + "，判断正确");
            } else {
                System.err.println("第" + order + "种类型 单数：" + singular + "，复数：" + plural + "，判断错误，正确复数为：" + correctPlural);
            }
        }
    }

    private static void testSingularize(HashMap<String, String> map, int order) {
        System.out.println("\n----------------复数变单数 测试第" + order + "种类型------------------");
        for (String correctSingular : map.keySet()) {
            String plural = map.get(correctSingular);
            String singular = EnglishWordUtil.singularize(plural);
            if (singular.equals(correctSingular)) {
                System.out.println("第" + order + "种类型 复数：" + plural + "，单数：" + singular + "，判断正确");
            } else {
                System.err.println("第" + order + "种类型 复数：" + plural + "，单数：" + singular + "，判断错误，正确单数为：" + correctSingular);
            }
        }
    }
}

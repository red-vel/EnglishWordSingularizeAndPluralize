import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 正则模式和对应的替换字符串
class PatternAndReplacement {

    private Pattern pattern;
    private String replacement;

    public PatternAndReplacement(Pattern pattern, String replacement) {
        this.pattern = pattern;
        this.replacement = replacement;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }
}

// 英文单词单数、复数形式转换
public class EnglishWordUtil {

    //一、单复数同形
    private static List<String> uncountables = new ArrayList<>();
    //二、单数复数的不规则变化
    private static List<PatternAndReplacement> unregulars = new ArrayList<>();
    //三、单数 -> 复数
    private static List<PatternAndReplacement> plurals = new ArrayList<>();
    //四、复数 -> 单数
    private static List<PatternAndReplacement> singulars = new ArrayList<>();

    static {
        //0、单复数同形
        uncountables.add("deer");
        uncountables.add("shoes");
        uncountables.add("sheep");
        uncountables.add("fish");
        uncountables.add("Chinese");
        uncountables.add("English");
        uncountables.add("Portuguese");
        uncountables.add("Lebanese");
        uncountables.add("Swiss");
        uncountables.add("Vietnamese");
        uncountables.add("Japanese");
        uncountables.add("economics");
        uncountables.add("news");
        uncountables.add("human");
        //三、单数 -> 复数
        //1、单数复数的不规则变化
        unregulars.add(new PatternAndReplacement(Pattern.compile("^person$", Pattern.CASE_INSENSITIVE), "people"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("([^(Ger)])man", Pattern.CASE_INSENSITIVE), "$1men"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^man$", Pattern.CASE_INSENSITIVE), "men"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^child$", Pattern.CASE_INSENSITIVE), "children"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^foot$", Pattern.CASE_INSENSITIVE), "feet"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^tooth$", Pattern.CASE_INSENSITIVE), "teeth"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^(m|l)ouse$", Pattern.CASE_INSENSITIVE), "$1ice"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^matrix$", Pattern.CASE_INSENSITIVE), "matrices"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^vertex$", Pattern.CASE_INSENSITIVE), "vertices"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^ox$", Pattern.CASE_INSENSITIVE), "oxen"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^goose$", Pattern.CASE_INSENSITIVE), "geese"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^basis$", Pattern.CASE_INSENSITIVE), "bases"));
        //2、直接加"-s"的x国人
        plurals.add(new PatternAndReplacement(Pattern.compile("^((German)|(Russian)|(American)|(Italian)|(Indian)|(Canadian)|(Australian)|(Swede))$", Pattern.CASE_INSENSITIVE), "$1s"));
        //3、以s, ss，x, ch, sh结尾的名词加"-es"
        plurals.add(new PatternAndReplacement(Pattern.compile("(s|ss|x|ch|sh)$", Pattern.CASE_INSENSITIVE), "$1es"));
        //4、以元音字母+o结尾（除studio），后面加"-s"
        plurals.add(new PatternAndReplacement(Pattern.compile("^studio$", Pattern.CASE_INSENSITIVE), "studios"));
        plurals.add(new PatternAndReplacement(Pattern.compile("([aeiou])o$", Pattern.CASE_INSENSITIVE), "$1os"));
        //5、以辅音字母+o结尾（除studio,piano,kilo,photo)，后面加"-es"
        plurals.add(new PatternAndReplacement(Pattern.compile("^((pian)|(kil)|(phot))o$", Pattern.CASE_INSENSITIVE), "$1os"));
        plurals.add(new PatternAndReplacement(Pattern.compile("([^aeiou])o$", Pattern.CASE_INSENSITIVE), "$1oes"));
        //6、以辅音字母加y结尾的名词，变y为i加"-es "
        plurals.add(new PatternAndReplacement(Pattern.compile("([^aeiou])y$", Pattern.CASE_INSENSITIVE), "$1ies"));
        //7、以元音字母加y结尾的名词直接加"-s"
        plurals.add(new PatternAndReplacement(Pattern.compile("([aeiou])y$", Pattern.CASE_INSENSITIVE), "$1ys"));
        //8、除了roof，gulf，proof，beef，staff，belief，cliff
        //以fe或f结尾的名词，把fe或f变为v加"-es"
        plurals.add(new PatternAndReplacement(Pattern.compile("^((roo)|(gul)|(proo)|(bee)|(staf)|(belie)|(clif))f$", Pattern.CASE_INSENSITIVE), "$1fs"));
        plurals.add(new PatternAndReplacement(Pattern.compile("(fe|f)$", Pattern.CASE_INSENSITIVE), "ves"));
        //9、无连字号复合名词，后面名词变复数
        plurals.add(new PatternAndReplacement(Pattern.compile("(cake)$", Pattern.CASE_INSENSITIVE), "cakes"));
        plurals.add(new PatternAndReplacement(Pattern.compile("(watch)$", Pattern.CASE_INSENSITIVE), "watches"));
        plurals.add(new PatternAndReplacement(Pattern.compile("(chair)$", Pattern.CASE_INSENSITIVE), "chairs"));
        plurals.add(new PatternAndReplacement(Pattern.compile("(man)$", Pattern.CASE_INSENSITIVE), "men"));
        plurals.add(new PatternAndReplacement(Pattern.compile("(wife)$", Pattern.CASE_INSENSITIVE), "wives"));
        plurals.add(new PatternAndReplacement(Pattern.compile("(glass)$", Pattern.CASE_INSENSITIVE), "glasses"));
        plurals.add(new PatternAndReplacement(Pattern.compile("(house)$", Pattern.CASE_INSENSITIVE), "houses"));

        //四、复数 -> 单数
        //9、无连字号复合名词，后面名词变复数
        singulars.add(new PatternAndReplacement(Pattern.compile("(cakes)$", Pattern.CASE_INSENSITIVE), "cake"));
        singulars.add(new PatternAndReplacement(Pattern.compile("(watches)$", Pattern.CASE_INSENSITIVE), "watch"));
        singulars.add(new PatternAndReplacement(Pattern.compile("(chairs)$", Pattern.CASE_INSENSITIVE), "chair"));
        singulars.add(new PatternAndReplacement(Pattern.compile("(men)$", Pattern.CASE_INSENSITIVE), "man"));
        singulars.add(new PatternAndReplacement(Pattern.compile("(wives)$", Pattern.CASE_INSENSITIVE), "wife"));
        singulars.add(new PatternAndReplacement(Pattern.compile("(glasses)$", Pattern.CASE_INSENSITIVE), "glass"));
        singulars.add(new PatternAndReplacement(Pattern.compile("(houses)$", Pattern.CASE_INSENSITIVE), "house"));
        //1、单数复数的不规则变化
        singulars.add(new PatternAndReplacement(Pattern.compile("^people$", Pattern.CASE_INSENSITIVE), "person"));
        singulars.add(new PatternAndReplacement(Pattern.compile("([^(Ger)])men", Pattern.CASE_INSENSITIVE), "$1man"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^men$", Pattern.CASE_INSENSITIVE), "man"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^children$", Pattern.CASE_INSENSITIVE), "child"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^feet$", Pattern.CASE_INSENSITIVE), "foot"));
        unregulars.add(new PatternAndReplacement(Pattern.compile("^teeth$", Pattern.CASE_INSENSITIVE), "tooth"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^(m|l)ice$", Pattern.CASE_INSENSITIVE), "$1ouse"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^matrices$", Pattern.CASE_INSENSITIVE), "matrix"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^vertices$", Pattern.CASE_INSENSITIVE), "vertex"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^oxen$", Pattern.CASE_INSENSITIVE), "ox"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^geese$", Pattern.CASE_INSENSITIVE), "goose"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^bases$", Pattern.CASE_INSENSITIVE), "basis"));
        //2、直接加"-s"的x国人
        singulars.add(new PatternAndReplacement(Pattern.compile("^((German)|(Russian)|(American)|(Italian)|(Indian)|(Canadian)|(Australian)|(Swede))s$", Pattern.CASE_INSENSITIVE), "$1"));
        //3、以s, ss，x, ch, sh结尾的名词加"-es"
        singulars.add(new PatternAndReplacement(Pattern.compile("houses$", Pattern.CASE_INSENSITIVE), "house"));
        singulars.add(new PatternAndReplacement(Pattern.compile("(s|ss|x|ch|sh)es$", Pattern.CASE_INSENSITIVE), "$1"));
        //4、以元音字母+o结尾（除studio），后面加"-s"
        singulars.add(new PatternAndReplacement(Pattern.compile("^studios", Pattern.CASE_INSENSITIVE), "studio"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^([aeiou])os$", Pattern.CASE_INSENSITIVE), "$1"));
        //5、以辅音字母+o结尾（除studio,piano,kilo,photo)，后面加"-es"
        singulars.add(new PatternAndReplacement(Pattern.compile("^((pian)|(kil)|(phot))os$", Pattern.CASE_INSENSITIVE), "$1o"));
        singulars.add(new PatternAndReplacement(Pattern.compile("([^aeiou])oes$", Pattern.CASE_INSENSITIVE), "$1o"));
        //6、以辅音字母加y结尾的名词，变y为i加"-es "
        singulars.add(new PatternAndReplacement(Pattern.compile("([^aeiou])ies$", Pattern.CASE_INSENSITIVE), "$1y"));
        //7、以元音字母加y结尾的名词直接加"-s"
        singulars.add(new PatternAndReplacement(Pattern.compile("^([aeiou])ys$", Pattern.CASE_INSENSITIVE), "$1"));
        //8、除了roof，gulf，proof，beef，staff，belief，cliff
        //以fe或f结尾的名词，把fe或f变为v加"-es"
        singulars.add(new PatternAndReplacement(Pattern.compile("^((roo)|(gul)|(proo)|(bee)|(staf)|(belie)|(clif))fs$", Pattern.CASE_INSENSITIVE), "$1f"));
        singulars.add(new PatternAndReplacement(Pattern.compile("^((kni)|(wi)|(li))ves$", Pattern.CASE_INSENSITIVE), "$1fe"));
        singulars.add(new PatternAndReplacement(Pattern.compile("ves$", Pattern.CASE_INSENSITIVE), "f"));
    }

    // 单数 -> 复数
    public static String pluralize(String word) {
        //1、单复数同形
        if (uncountables.contains(word)) {
            return word;
        }
        //2、不规则变化
        for (PatternAndReplacement unregular : unregulars) {
            Matcher matcher = unregular.getPattern().matcher(word);
            if (matcher.find()) {
                return matcher.replaceAll(unregular.getReplacement());
            }
        }
        //3、规则变化
        for (PatternAndReplacement regular : plurals) {
            Matcher matcher = regular.getPattern().matcher(word);
            if (matcher.find()) {
                return matcher.replaceAll(regular.getReplacement());
            }
        }
        return word + "s";
    }

    //复数 -> 单数
    public static String singularize(String word) {
        //1、单复数同形
        if (uncountables.contains(word)) {
            return word;
        }
        //2、不规则变化
        for (PatternAndReplacement unregular : unregulars) {
            Matcher matcher = unregular.getPattern().matcher(word);
            if (matcher.find()) {
                return matcher.replaceAll(unregular.getReplacement());
            }
        }
        //3、规则变化
        for (PatternAndReplacement regular : singulars) {
            Matcher matcher = regular.getPattern().matcher(word);
            if (matcher.find()) {
                return matcher.replaceAll(regular.getReplacement());
            }
        }
        return word.substring(0, word.length()-1);
    }
}

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
    //二、单数 -> 复数的不规则变化
    private static List<PatternAndReplacement> unregularSingularToPlural = new ArrayList<>();
    //三、单数 -> 复数
    private static List<PatternAndReplacement> singularToPlural = new ArrayList<>();
    //四、复数 -> 单数的不规则变化
    private static List<PatternAndReplacement> unregularPluralToSingular = new ArrayList<>();
    //五、复数 -> 单数
    private static List<PatternAndReplacement> pluralToSingular = new ArrayList<>();

    static {
        //一、单复数同形
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
        //二、单数 -> 复数的不规则变化
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^person$", Pattern.CASE_INSENSITIVE), "people"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("([^(Ger)])man", Pattern.CASE_INSENSITIVE), "$1men"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^man$", Pattern.CASE_INSENSITIVE), "men"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^child$", Pattern.CASE_INSENSITIVE), "children"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^foot$", Pattern.CASE_INSENSITIVE), "feet"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^tooth$", Pattern.CASE_INSENSITIVE), "teeth"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^(m|l)ouse$", Pattern.CASE_INSENSITIVE), "$1ice"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^matrix$", Pattern.CASE_INSENSITIVE), "matrices"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^vertex$", Pattern.CASE_INSENSITIVE), "vertices"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^ox$", Pattern.CASE_INSENSITIVE), "oxen"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^goose$", Pattern.CASE_INSENSITIVE), "geese"));
        unregularSingularToPlural.add(new PatternAndReplacement(Pattern.compile("^basis$", Pattern.CASE_INSENSITIVE), "bases"));
        //三、单数 -> 复数
        //2、直接加"-s"的x国人
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("^((German)|(Russian)|(American)|(Italian)|(Indian)|(Canadian)|(Australian)|(Swede))$", Pattern.CASE_INSENSITIVE), "$1s"));
        //3、以s, ss，x, ch, sh结尾的名词加"-es"
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("(s|ss|x|ch|sh)$", Pattern.CASE_INSENSITIVE), "$1es"));
        //4、以元音字母+o结尾（除studio），后面加"-s"
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("^studio$", Pattern.CASE_INSENSITIVE), "studios"));
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("([aeiou])o$", Pattern.CASE_INSENSITIVE), "$1os"));
        //5、以辅音字母+o结尾（除studio,piano,kilo,photo)，后面加"-es"
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("^((pian)|(kil)|(phot))o$", Pattern.CASE_INSENSITIVE), "$1os"));
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("([^aeiou])o$", Pattern.CASE_INSENSITIVE), "$1oes"));
        //6、以辅音字母加y结尾的名词，变y为i加"-es "
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("([^aeiou])y$", Pattern.CASE_INSENSITIVE), "$1ies"));
        //7、以元音字母加y结尾的名词直接加"-s"
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("([aeiou])y$", Pattern.CASE_INSENSITIVE), "$1ys"));
        //8、除了roof，gulf，proof，beef，staff，belief，cliff
        //以fe或f结尾的名词，把fe或f变为v加"-es"
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("^((roo)|(gul)|(proo)|(bee)|(staf)|(belie)|(clif))f$", Pattern.CASE_INSENSITIVE), "$1fs"));
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("(fe|f)$", Pattern.CASE_INSENSITIVE), "ves"));
        //9、无连字号复合名词，后面名词变复数
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("(cake)$", Pattern.CASE_INSENSITIVE), "cakes"));
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("(watch)$", Pattern.CASE_INSENSITIVE), "watches"));
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("(chair)$", Pattern.CASE_INSENSITIVE), "chairs"));
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("(man)$", Pattern.CASE_INSENSITIVE), "men"));
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("(wife)$", Pattern.CASE_INSENSITIVE), "wives"));
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("(glass)$", Pattern.CASE_INSENSITIVE), "glasses"));
        singularToPlural.add(new PatternAndReplacement(Pattern.compile("(house)$", Pattern.CASE_INSENSITIVE), "houses"));

        //四、复数 -> 单数的不规则变化
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^people$", Pattern.CASE_INSENSITIVE), "person"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("([^(Ger)])men", Pattern.CASE_INSENSITIVE), "$1man"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^men$", Pattern.CASE_INSENSITIVE), "man"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^children$", Pattern.CASE_INSENSITIVE), "child"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^feet$", Pattern.CASE_INSENSITIVE), "foot"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^teeth$", Pattern.CASE_INSENSITIVE), "tooth"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^(m|l)ice$", Pattern.CASE_INSENSITIVE), "$1ouse"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^matrices$", Pattern.CASE_INSENSITIVE), "matrix"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^vertices$", Pattern.CASE_INSENSITIVE), "vertex"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^oxen$", Pattern.CASE_INSENSITIVE), "ox"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^geese$", Pattern.CASE_INSENSITIVE), "goose"));
        unregularPluralToSingular.add(new PatternAndReplacement(Pattern.compile("^bases$", Pattern.CASE_INSENSITIVE), "basis"));

        //五、复数 -> 单数
        //9、无连字号复合名词，后面名词变复数
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("(cakes)$", Pattern.CASE_INSENSITIVE), "cake"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("(watches)$", Pattern.CASE_INSENSITIVE), "watch"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("(chairs)$", Pattern.CASE_INSENSITIVE), "chair"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("(men)$", Pattern.CASE_INSENSITIVE), "man"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("(wives)$", Pattern.CASE_INSENSITIVE), "wife"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("(glasses)$", Pattern.CASE_INSENSITIVE), "glass"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("(houses)$", Pattern.CASE_INSENSITIVE), "house"));
        //2、直接加"-s"的x国人
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("^((German)|(Russian)|(American)|(Italian)|(Indian)|(Canadian)|(Australian)|(Swede))s$", Pattern.CASE_INSENSITIVE), "$1"));
        //3、以s, ss，x, ch, sh结尾的名词加"-es"
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("houses$", Pattern.CASE_INSENSITIVE), "house"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("(s|ss|x|ch|sh)es$", Pattern.CASE_INSENSITIVE), "$1"));
        //4、以元音字母+o结尾（除studio），后面加"-s"
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("^studios", Pattern.CASE_INSENSITIVE), "studio"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("^([aeiou])os$", Pattern.CASE_INSENSITIVE), "$1"));
        //5、以辅音字母+o结尾（除studio,piano,kilo,photo)，后面加"-es"
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("^((pian)|(kil)|(phot))os$", Pattern.CASE_INSENSITIVE), "$1o"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("([^aeiou])oes$", Pattern.CASE_INSENSITIVE), "$1o"));
        //6、以辅音字母加y结尾的名词，变y为i加"-es "
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("([^aeiou])ies$", Pattern.CASE_INSENSITIVE), "$1y"));
        //7、以元音字母加y结尾的名词直接加"-s"
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("^([aeiou])ys$", Pattern.CASE_INSENSITIVE), "$1"));
        //8、除了roof，gulf，proof，beef，staff，belief，cliff
        //以fe或f结尾的名词，把fe或f变为v加"-es"
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("^((roo)|(gul)|(proo)|(bee)|(staf)|(belie)|(clif))fs$", Pattern.CASE_INSENSITIVE), "$1f"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("^((kni)|(wi)|(li))ves$", Pattern.CASE_INSENSITIVE), "$1fe"));
        pluralToSingular.add(new PatternAndReplacement(Pattern.compile("ves$", Pattern.CASE_INSENSITIVE), "f"));
    }

    // 是否为单数形式
    public static boolean isSingular(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        // 1、单复数同形
        if (uncountables.contains(word)) {
            return true;
        }
        //2、不规则变化
        for (PatternAndReplacement unregular : unregularSingularToPlural) {
            Matcher matcher = unregular.getPattern().matcher(word);
            if (matcher.find()) {
                return true;
            }
        }
        //3、规则变化
        for (PatternAndReplacement regular : singularToPlural) {
            Matcher matcher = regular.getPattern().matcher(word);
            if (matcher.find()) {
                return true;
            }
        }
        return word.charAt(word.length()-1) != 's';
    }

    // 单数 -> 复数
    public static String pluralize(String word) {
        if (word == null || word.length() == 0) {
            return "";
        }
        //1、单复数同形
        if (uncountables.contains(word)) {
            return word;
        }
        //2、不规则变化
        for (PatternAndReplacement unregular : unregularSingularToPlural) {
            Matcher matcher = unregular.getPattern().matcher(word);
            if (matcher.find()) {
                return matcher.replaceAll(unregular.getReplacement());
            }
        }
        //3、规则变化
        for (PatternAndReplacement regular : singularToPlural) {
            Matcher matcher = regular.getPattern().matcher(word);
            if (matcher.find()) {
                return matcher.replaceAll(regular.getReplacement());
            }
        }
        return word + "s";
    }

    // 是否为复数形式
    public static boolean isPlural(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        // 1、单复数同形
        if (uncountables.contains(word)) {
            return true;
        }
        //2、不规则变化
        for (PatternAndReplacement unregular : unregularPluralToSingular) {
            Matcher matcher = unregular.getPattern().matcher(word);
            if (matcher.find()) {
                return true;
            }
        }
        //3、规则变化
        for (PatternAndReplacement regular : pluralToSingular) {
            Matcher matcher = regular.getPattern().matcher(word);
            if (matcher.find()) {
                return true;
            }
        }
        return word.charAt(word.length()-1) == 's';
    }

    // 复数 -> 单数
    public static String singularize(String word) {
        if (word == null || word.length() == 0) {
            return "";
        }
        //1、单复数同形
        if (uncountables.contains(word)) {
            return word;
        }
        //2、不规则变化
        for (PatternAndReplacement unregular : unregularPluralToSingular) {
            Matcher matcher = unregular.getPattern().matcher(word);
            if (matcher.find()) {
                return matcher.replaceAll(unregular.getReplacement());
            }
        }
        //3、规则变化
        for (PatternAndReplacement regular : pluralToSingular) {
            Matcher matcher = regular.getPattern().matcher(word);
            if (matcher.find()) {
                return matcher.replaceAll(regular.getReplacement());
            }
        }
        //去掉最后一个字母s
        return word.substring(0, word.length()-1);
    }

}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName: Three
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2019/9/28 13:37
 * @Version: 1.0
 */
public class Three {

    public static void main(String[] args) throws IOException {
        StringBuffer sb = getBufferedReader("F:\\IDEA_workspaces\\Individualproject\\out\\file\\test.txt");
        sentenceCountOnSb(sb, Integer.valueOf(args[1]));
        // sentenceCountOnSb(sb, 3);
    }

    public static StringBuffer getBufferedReader(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        fr.close();
        return sb;
    }

    /*统计单词*/
    public static void sentenceCountOnSb(StringBuffer sb, Integer p) throws IOException {

        String str = sb.toString().toLowerCase();
        String[] sentences = str.split("[^(a-zA-Z+\\s)]+");
        List<String> shortSentences = new ArrayList<>();
        for (String sentence : sentences) {
            sentence = sentence.trim();
            String[] words = sentence.split("[^(a-zA-Z)]+");
            for (int i = 0; i < words.length - p + 1; i++) {
                String string = "";
                for (int j = i; j < i + p; j++) {
                    string += " " + words[j];
                }
                string = string.trim();
                shortSentences.add(string);
            }
        }
        Map<String, Integer> map = new HashMap<>();
        for (String shortSentence : shortSentences) {
            if (map.get(shortSentence) == null) {
                map.put(shortSentence, 1);
            } else {
                map.put(shortSentence, map.get(shortSentence) + 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Comparator<Map.Entry<String, Integer>> comparator = (o1, o2) -> {
            int i = o1.getValue() - o2.getValue();
            if (i == 0) {
                return o2.getKey().compareTo(o1.getKey());
            }
            return o1.getValue().compareTo(o2.getValue());
        };

        Collections.sort(list, comparator);
        int n = list.size();
        System.out.println("一共有" + n + "种短语");
        for (int i = 0; i < n; i++) {
            System.out.println(list.get(list.size() - i - 1).getKey() + ":" + list.get(list.size() - i - 1).getValue());
        }
    }
}

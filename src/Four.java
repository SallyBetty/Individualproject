import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName: Four
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2019/9/28 15:23
 * @Version: 1.0
 */
public class Four {

    public static void main(String[] args) throws IOException {

        StringBuffer sb = getBufferedReader("file\\test.txt");
        List<List<String>> verbList = getVerbList(args[1]);
        wordCountOnSb(sb, verbList);
    }

    public static List<List<String>> getVerbList(String fileName) throws IOException {

        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        List<List<String>> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            line = line.toLowerCase();
            String[] words = line.split("[^(a-zA-Z)]+");
            List<String> wordsList = Arrays.asList(words);
            list.add(wordsList);
        }
        fr.close();
        return list;
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

    public static void wordCountOnSb(StringBuffer sb, List<List<String>> verbList) throws IOException {

        String str = sb.toString().toLowerCase();
        String[] words = str.split("[^(a-zA-Z)]+");
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {

            int flag = 0;
            for (List<String> verbWordList : verbList) {
                for (int i = 1; i < verbWordList.size(); i++) {
                    if (word.equals(verbWordList.get(i))) {
                        word = verbWordList.get(0);
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) {
                    break;
                }
            }

            if (map.get(word) == null) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
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
        System.out.println("一共有" + n + "种单词");
        for (int i = 0; i < n; i++) {
            System.out.println(list.get(list.size() - i - 1).getKey() + ":" + list.get(list.size() - i - 1).getValue());
        }
    }
}

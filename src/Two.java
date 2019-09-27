import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName: Two
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2019/9/27 18:17
 * @Version: 1.0
 */
public class Two {

    public static void main(String[] args) throws IOException {
        File file = new File(args[1]);
        StringBuffer sb = getBufferedReader(args[3]);
        wordCountOnSb(sb, args[1]);

    }


    public static String[] getStopWord(String fileName) throws IOException {
        StringBuffer sb = getBufferedReader(fileName);
        String str = sb.toString().toLowerCase();
        String[] StopWords = str.split("[^(a-zA-Z)]+");
        return StopWords;
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
    public static void wordCountOnSb(StringBuffer sb, String stopFileName) throws IOException {

        String str = sb.toString().toLowerCase();
        String[] words = str.split("[^(a-zA-Z)]+");
        Map<String, Integer> map = new HashMap<>();
        String[] stopWords = getStopWord(stopFileName);
        for (String word : words) {

            int flag = 0;

            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 1) {
                continue;
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

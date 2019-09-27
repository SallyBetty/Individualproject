import javax.annotation.processing.Filer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName: One
 * @Description: TODO
 * @Author: Lenovo
 * @Date: 2019/9/27 15:51
 * @Version: 1.0
 */
public class One {

    public static void main(String[] args) throws IOException {
        if (Arrays.asList(args).contains("-f")) {
            StringBuffer sb = getBufferedReader(args[args.length - 1]);
            if (Arrays.asList(args).contains("-n")) {
                int N = 0;
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-n")) {
                        N = Integer.parseInt(args[i + 1]);
                    }
                }
                wordCountOnSb(sb, N);
            } else {
                wordCountOnSb(sb);
            }
        } else if (Arrays.asList(args).contains("-d")) {
            if (Arrays.asList(args).contains("-s")) {

                List<String> fileList = One.getAllFile(args[args.length - 1]);
                StringBuffer sb = getBufferedReader(fileList);

                if (Arrays.asList(args).contains("-n")) {
                    int N = 0;
                    for (int i = 0; i < args.length; i++) {
                        if (args[i].equals("-n")) {
                            N = Integer.parseInt(args[i + 1]);
                        }
                    }
                    wordCountOnSb(sb, N);
                } else {
                    wordCountOnSb(sb);
                }
            } else {

                if (Arrays.asList(args).contains("-n")) {
                    int N = 0;
                    for (int i = 0; i < args.length; i++) {
                        if (args[i].equals("-n")) {
                            N = Integer.parseInt(args[i + 1]);
                        }
                    }
                    List<String> allFile = getAllFile(args[args.length - 1]);
                    for (String fileName : allFile) {
                        StringBuffer sb = getBufferedReader(fileName);
                        wordCountOnSb(sb, N);
                        System.out.println("--------------------------");
                    }
                } else {
                    List<String> allFile = getAllFile(args[args.length - 1]);
                    for (String fileName : allFile) {
                        StringBuffer sb = getBufferedReader(fileName);
                        wordCountOnSb(sb);
                        System.out.println("--------------------------");
                    }
                }


            }

        } else if (Arrays.asList(args).contains("-d") && Arrays.asList(args).contains("-s")) {

        } else if ("-n".equals(args[2])) {
            List<String> fileList = One.getAllFile(args[1]);
            StringBuffer sb = getBufferedReader(fileList);
            wordCountOnSb(sb);
        }
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

    public static StringBuffer getBufferedReader(List<String> fileList) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (String fileName : fileList) {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            fr.close();
        }
        return sb;
    }

    /*统计单词*/
    public static void wordCountOnSb(StringBuffer sb) throws IOException {

        String str = sb.toString().toLowerCase();
        String[] words = str.split("[^(a-zA-Z)]+");
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
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

    public static void wordCountOnSb(StringBuffer sb, int N) throws IOException {

        String str = sb.toString().toLowerCase();
        String[] words = str.split("[^(a-zA-Z)]+");
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
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
        for (int i = 0; i < N; i++) {
            System.out.println(list.get(list.size() - i - 1).getKey() + ":" + list.get(list.size() - i - 1).getValue());
        }
    }


    public static List<String> getAllFile(String directoryPath) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                list.add(file.getAbsolutePath());
                list.addAll(getAllFile(file.getAbsolutePath()));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }
}

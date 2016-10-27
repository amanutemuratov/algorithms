package string;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrieTest {

    @Test
    public void wordExists() {
        List<String> list = Arrays.asList("test1", "test2", "test3");

        Trie trie = new Trie();
        for (String s : list) {
            trie.insert(s);
        }

        for (String s : list) {
            assertTrue(trie.exists(s));
        }
    }

    @Test
    public void wordNotExists() {
        List<String> list = Arrays.asList("test1", "test2", "test3");

        Trie trie = new Trie();
        for (String s : list) {
            trie.insert(s);
        }

        assertFalse(trie.exists("test4"));
        assertFalse(trie.exists("test5"));

    }

    @Test
    public void nullWord() {
        List<String> list = Arrays.asList("test1", "test2", "test3");

        Trie trie = new Trie();
        trie.insert(null);

        for (String s : list) {
            assertFalse(trie.exists(s));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullCheckWord() {
        List<String> list = Arrays.asList("test1", "test2", "test3");

        Trie trie = new Trie();

        for (String s : list) {
            trie.insert(s);
        }

        trie.exists(null);
    }
}

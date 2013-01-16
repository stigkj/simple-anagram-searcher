package net.nisgits.anagram;

import java.util.Collection;
import java.util.Set;
import static java.util.Arrays.sort;

import com.google.common.base.Joiner;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeMultimap;

/**
 * Searches for anagrams in a given set of words.
 *
 * @author Stig Kleppe-Jorgensen, 2013.01.15
 */
public class AnagramSearcher {
    private final Set<String> words;

    private AnagramSearcher(Set<String> words) {
        this.words = words;
    }

    public static AnagramSearcher on(Set<String> words) {
        return new AnagramSearcher(words);
    }

    public Set<String> search() {
        final Multimap<String, String> keyToWords = TreeMultimap.create();

        for (String word : words) {
            String key = createKeyFrom(word);
            keyToWords.put(key, word);
        }

        final Set<String> anagrams = Sets.newHashSet();

        for (Collection<String> words : keyToWords.asMap().values()) {
            if (words.size() > 1) {
                final String anagram = Joiner.on(" ").join(words);
                anagrams.add(anagram);
            }
        }

        return anagrams;
    }

    private String createKeyFrom(String word) {
        final char[] chars = word.toCharArray();
        sort(chars);

        return String.valueOf(chars);
    }
}

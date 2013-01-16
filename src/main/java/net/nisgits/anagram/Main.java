package net.nisgits.anagram;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import static com.google.common.collect.Sets.newHashSet;

/**
 * Starter class that reads in file with anagrams, solves them with <code>AnagramSearcher</code> and outputs the result.
 *
 * @author Stig Kleppe-Jorgensen, 2013.01.15
 */
public class Main {
    private final String filename;

    public static void main(String[] args) {
        new Main(args[0]).run();
    }

    public Main(String filename) {
        this.filename = filename;
    }

    private void run() {
        Set<String> words = readFile();

        final Set<String> anagrams = AnagramSearcher.on(words).search();

        for (String anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    private Set<String> readFile() {
        List<String> words;

        try {
            words = Files.readLines(new File(filename), Charsets.ISO_8859_1);
        } catch (IOException e) {
            throw new IllegalArgumentException(format("Could not load file %s", filename), e);
        }

        return newHashSet(words);
    }
}

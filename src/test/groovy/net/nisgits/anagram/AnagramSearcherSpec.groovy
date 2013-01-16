package net.nisgits.anagram

import spock.lang.Specification

/**
 * Unit test of {@link AnagramSearcher}.
 *
 * @author Stig Kleppe-Jorgensen, 2013.01.15
 */
class AnagramSearcherSpec extends Specification {
    def 'should list anagrams from given words'() {
        given: 'a list of words to make anagrams from'
        def words = ['akte', 'aldri', 'teak', 'ralle', 'kate', 'arild'] as Set

        when: 'running the searcher'
        def anagrams = AnagramSearcher.on(words).search()

        then: 'the list of anagrams are correct'
        anagrams == ['aldri arild', 'akte kate teak'] as Set
    }
}

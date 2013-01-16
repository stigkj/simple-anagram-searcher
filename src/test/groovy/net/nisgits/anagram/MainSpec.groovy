package net.nisgits.anagram

import org.codehaus.groovy.reflection.ReflectionUtils
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

/**
 * Unit test for {@link Main}.
 *
 * @author Stig Kleppe-Jorgensen, 2013.01.15
 */
class MainSpec extends Specification {
    def CONTENT = """
arild
test
akte
aldri
teak
"""

    def oldStdOut
    def baoStream

    @Rule
    TemporaryFolder tempFolder = new TemporaryFolder();

    def 'should make anagrams of test text written to a file'() {
        given: 'a temporary file with test content'
        def file = tempFolder.newFile()
        file << CONTENT

        when: 'processing this file'
        Main.main(file.absolutePath)

        then: 'the anagrams will be printed to stdout'
        baoStream.toString() == """akte teak
aldri arild
"""
    }

    def 'should make correct number of anagrams for the real file'() {
        given: 'the real test file'
        def resource = ReflectionUtils.getResource('/eventyr.txt')
        def file = new File(resource.toURI())

        when: 'processing this file'
        Main.main(file.absolutePath)

        then: 'the number of anagrams is correct'
        baoStream.toString().readLines().size() == 29
    }

    def setup() {
        oldStdOut = System.out

        baoStream = new ByteArrayOutputStream()
        System.out = new PrintStream(baoStream)
    }

    def cleanup() {
        System.out = oldStdOut
    }
}

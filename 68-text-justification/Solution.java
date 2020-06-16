import java.util.List;
import java.util.ArrayList;
import static java.util.Objects.requireNonNull;

/*
    Problem Description:

        Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters
        and is fully (left and right) justified.
        You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra
        spaces ' ' when necessary so that each line has exactly maxWidth characters.
        Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not
        divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
        For the last line of text, it should be left justified and no extra space is inserted between words.
            A word is defined as a character sequence consisting of non-space characters only.
            Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
            The input array words contains at least one word.

        Examples:

            Input: words = ["This", "is", "an", "example", "of", "text", "justification."] maxWidth = 16
            Output: [
                       "This    is    an",
                       "example  of text",
                       "justification.  "
                    ]

            Input: words = ["What","must","be","acknowledgment","shall","be"] maxWidth = 16
            Output: [
                      "What   must   be",
                      "acknowledgment  ",
                      "shall be        "
                    ]

            Input: words = ["Science","is","what","we","understand","well","enough","to","explain",
                            "to","a","computer.","Art","is","everything","else","we","do"] maxWidth = 20
            Output:[
                      "Science  is  what we",
                      "understand      well",
                      "enough to explain to",
                      "a  computer.  Art is",
                      "everything  else  we",
                      "do                  "
                    ]

    Time  complexity: O(n)
    Space complexity: O(n), including result
*/
public final class Solution {

    public List<String> solve(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        List<String> line = new ArrayList<>();
        String word;
        int length = 0;

        requireNonNull(words, "words is null");
        if (maxWidth <= 0) {
            throw new IllegalArgumentException("maxWidth value " + maxWidth + " is non-positive");
        }
        for (int i = 0; i < words.length; i++) {
            word = words[i];
            // append length with at least 1 space between words
            length = length + word.length() + ((length > 0) ? 1 : 0);
            line.add(word);
            if (i < (words.length - 1)) {
                // have more words
                if ((length + words[i + 1].length() + 1) > maxWidth) {
                    // next word doesn't fit
                    result.add(buildLine(line, maxWidth - length));
                    line.clear();
                    length = 0;
                }
            } else {
                // last word
                result.add(buildLastLine(line, maxWidth - length));
            }
        }
        return result;
    }

    private String buildLine(List<String> words, int extraSpaceCount) {
        StringBuilder builder = new StringBuilder();
        int totalSpaceCount;
        int count;
        int extra;

        if (words.size() > 1) {
            totalSpaceCount = extraSpaceCount + words.size() - 1;
            count = totalSpaceCount / (words.size() - 1);
            extra = totalSpaceCount % (words.size() - 1);
            builder.append(words.get(0));
            for (int i = 1; i < words.size(); i++) {
                appendSpaces(builder, count);
                if (extra > 0) {
                    appendSpaces(builder, 1);
                    extra--;
                }
                builder.append(words.get(i));
            }
        } else {
            builder.append(words.get(0));
            appendSpaces(builder, extraSpaceCount);
        }
        return builder.toString();
    }

    private String buildLastLine(List<String> words, int extraSpaceCount) {
        StringBuilder builder = new StringBuilder();

        builder.append(words.get(0));
        for (int i = 1; i < words.size(); i++) {
            appendSpaces(builder, 1);
            builder.append(words.get(i));
        }
        appendSpaces(builder, extraSpaceCount);
        return builder.toString();
    }

    private void appendSpaces(StringBuilder builder, int count) {
        for (int i = 0; i < count; i++) {
            builder.append(" ");
        }
    }

}
import static java.lang.Math.min;


/*
    Problem Description:

        Given a file and assume that you can only read the file using a given method read4, implement a method to read n
        characters.
        Method read4
        The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.
        The return value is the number of actual characters read.
        Note that read4() has its own file pointer, much like FILE *fp in C.
        Definition of read4:
            Parameter:  char[] buf4
            Returns:    int
        Note: buf4[] is destination not source, the results from read4 will be copied to buf4[]
        Below is a high level example of how read4 works:
        File file("abcde"); // File is "abcde", initially file pointer (fp) points to 'a'
        char[] buf4 = new char[4]; // Create buffer with enough space to store characters
        read4(buf4); // read4 returns 4. Now buf = "abcd", fp points to 'e'
        read4(buf4); // read4 returns 1. Now buf = "e", fp points to end of file
        read4(buf4); // read4 returns 0. Now buf = "", fp points to end of file
        Method read:
        By using the read4 method, implement the method read that reads n characters from the file and store it in
        the buffer array buf. Consider that you cannot manipulate the file directly.
        The return value is the number of actual characters read.
        Definition of read:
            Parameters: char[] buf, int n
            Returns:    int
        Note: buf[] is destination not source, you will need to write the results to buf[]
            Consider that you cannot manipulate the file directly, the file is only accesible for read4 but not for read.
            The read function will only be called once for each test case.
            You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n characters.

    Time  complexity: O(n)
    Space complexity: O(1)
*/
public final class Solution {

    private static int BATCH_SIZE = 4;

    public int solve(char[] buffer, int count) {
        char[] batch = new char[BATCH_SIZE];
        int remainingCount = count;
        int batchCount;

        while (remainingCount > 0) {
            batchCount = read4(batch);
            if (batchCount == 0) {
                break;
            }
            batchCount = min(batchCount, remainingCount);
            System.arraycopy(batch, 0, buffer, count - remainingCount, batchCount);
            remainingCount -= batchCount;
        }
        return (count - remainingCount);
    }

    public int read4(char[] buffer) {
        // implemented by leetcode
        return 0;
    }

}
package splitter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TelegramMessagesIteratorTest {

    private static final int INFINITLY_LARGE_MAX_MESSAGE_LENGTH = 1000;

    @Test
    public void hasNextShouldReturnTrueForNotEmptyMessage() {
        TelegramMessagesIterator underTest = underTest("Hello");
        assertTrue(underTest.hasNext());
    }

    private TelegramMessagesIterator underTest(String message) {
        return new TelegramMessagesIterator(message, INFINITLY_LARGE_MAX_MESSAGE_LENGTH);
    }

    private TelegramMessagesIterator underTest(String message, int maxMessageLength) {
        return new TelegramMessagesIterator(message, maxMessageLength);
    }

    @Test
    public void hasNextShouldReturnFalseForEmptyMessage() {
        assertFalse(underTest("").hasNext());
    }

    @Test
    public void nextShouldReturnFirstPartOfMessage() {
        assertEquals("Hello", underTest("Hello").next());
    }

    @Test
    public void hasNextShouldReturnTrueThenFalseForShortMessageIfNextIsCalled() {
        TelegramMessagesIterator underTest = underTest("Hello");
        assertTrue(underTest.hasNext());
        underTest.next();
        assertFalse(underTest.hasNext());
    }

    @Test
    public void nextShouldReturnPartsOfMessageSplitByNewLine() {
        TelegramMessagesIterator underTest = underTest("Hello\nWorld", 6);
        assertEquals("Hello\n", underTest.next());
        assertEquals("World", underTest.next());
    }

    @Test
    public void nextShouldNotDoTheSplitIfMaxMessageLengthIsNotReached() {
        TelegramMessagesIterator underTest = underTest("Hello\nWorld", 100);
        assertEquals("Hello\nWorld", underTest.next());
        assertFalse(underTest.hasNext());
    }
}
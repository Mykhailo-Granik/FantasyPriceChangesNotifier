package splitter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelegramMessageSplitterTest {

    @Test
    public void shouldNotSplitMessageIfItsLengthIsSmallerThanLimit() {
        TelegramMessageSplitter underTest = new TelegramMessageSplitter(10);
        List<String> result = underTest.split("Hello");
        assertEquals(List.of("Hello"), result);
    }

    @Test
    public void shouldSplitMessageIfItsLengthIsBiggerThanLimit() {
        TelegramMessageSplitter underTest = new TelegramMessageSplitter(10);
        List<String> result = underTest.split("Hello\nWorld");
        assertEquals(2, result.size());
    }

    @Test
    public void shouldSplitMessageToThreePartsIfItsLengthIsBiggerThanLimitTimes2() {
        TelegramMessageSplitter underTest = new TelegramMessageSplitter(7);
        List<String> result = underTest.split("Hello\nMy\nWorld");
        assertEquals(3, result.size());
    }

    @Test
    public void shouldNotSplitMessageIfItsLengthIsEqualToLimit() {
        TelegramMessageSplitter underTest = new TelegramMessageSplitter(5);
        List<String> result = underTest.split("Hello");
        assertEquals(List.of("Hello"), result);
    }

    @Test
    public void shouldSplitAtNewLine() {
        TelegramMessageSplitter underTest = new TelegramMessageSplitter(5);
        List<String> result = underTest.split("Hi\nWorld");
        assertEquals(List.of("Hi\n", "World"), result);
    }

    @Test
    public void shouldPerformSplitIfTheFirstLineLengthIsExactlyTheLimit() {
        TelegramMessageSplitter underTest = new TelegramMessageSplitter(6);
        List<String> result = underTest.split("Hello\nWorld");
        assertEquals(List.of("Hello\n", "World"), result);
    }

    @Test
    public void shouldPerformSplitIfTheLastLineLengthIsExactlyTheLimit() {
        TelegramMessageSplitter underTest = new TelegramMessageSplitter(6);
        List<String> result = underTest.split("Hello\nWorld!");
        assertEquals(List.of("Hello\n", "World!"), result);
    }

    @Test
    public void shouldCreateTwoMessagesIfTheFirstLineLengthIsExactlyTheLimitAndTheSumOfSecondAndThirdLineIsExactlyTheLimit() {
        TelegramMessageSplitter underTest = new TelegramMessageSplitter(6);
        List<String> result = underTest.split("Hello\nHi\ncat");
        assertEquals(List.of("Hello\n", "Hi\ncat"), result);
    }

}
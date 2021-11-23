package org.example;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfinateLoopTest
{
    @Test
    void test() throws IOException
    {
        var in = new ByteArrayInputStream( "hello\nworld\n".getBytes( StandardCharsets.UTF_8 ) );
        var out = new ByteArrayOutputStream();

        var terminal = TerminalBuilder.builder().dumb( true ).type( Terminal.TYPE_DUMB ).streams( in, out ).build();
        var lineReader = LineReaderBuilder.builder()
                .terminal( terminal )
                .variable( LineReader.SECONDARY_PROMPT_PATTERN, "%P " )
                .build();

        System.err.println(terminal.getClass().getName());

        var read1 = lineReader.readLine("Input1: ");
        var read2 = lineReader.readLine("Input2: ");

        assertEquals( "hello", read1 );
        assertEquals( "world", read2 );
    }
}
